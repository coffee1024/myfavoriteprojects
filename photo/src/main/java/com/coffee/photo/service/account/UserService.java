package com.coffee.photo.service.account;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coffee.photo.entity.account.User;
import com.coffee.photo.repository.account.UserDao;
import com.coffee.photo.service.ServiceException;
import com.coffee.photo.service.account.ShiroDbRealm.ShiroUser;
import com.coffee.photo.utils.Clock;
import com.coffee.photo.utils.Digests;
import com.coffee.photo.utils.Encodes;
import com.google.common.collect.Lists;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class UserService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	private UserDao userDao;
	private Clock clock = Clock.DEFAULT;

	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public void registerUser(User user) {
		entryptPassword(user);
		user.setRoles("user");
		user.setRegisterDate(clock.getCurrentDate());

		userDao.save(user);
	}

	public void updateUser(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userDao.save(user);
	}

	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userDao.delete(id);

	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	public String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}
	/**
	 * 取出Shiro中的当前用户.
	 */
	public ShiroUser getCurrentUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public Page<User> search(String loginName, Integer userType, Integer status, String email, String nickName,
			Date startDate, Date endDate, Integer searchType, Pageable pageRequest) {
		Page<User> userList = userDao.findAll(getSpec(loginName, userType, status, email, nickName, startDate, endDate,
				searchType), pageRequest);
		return userList;
	}

	public Specification<User> getSpec(final String loginName, final Integer userType, final Integer status,
			final String email, final String nickName, final Date startDate, final Date endDate,
			final Integer searchType) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = Lists.newArrayList();
				Path<String> loginNamePath = root.get("loginName");
				Path<Integer> userTypePath = root.get("userType");
				Path<String> emailPath = root.get("email");
				Path<String> statusPath = root.get("status");
				Path<String> namePath = root.get("nickName");
				Path<Date> registerDatePath = root.get("registerDate");
				if (StringUtils.isNotBlank(loginName)) {
					if ((searchType != null) && (searchType.intValue() == 0)) {
						predicates.add(cb.equal(loginNamePath, loginName));
					} else {
						predicates.add(cb.like(loginNamePath, "%" + loginName + "%"));
					}
				}
				if (userType != null) {
					predicates.add(cb.equal(userTypePath, userType));
				}
				if (status != null) {
					predicates.add(cb.equal(statusPath, status));
				}
				if (StringUtils.isNotBlank(email)) {
					if ((searchType != null) && (searchType.intValue() == 0)) {
						predicates.add(cb.equal(emailPath, email));
					} else {
						predicates.add(cb.like(emailPath, "%" + email + "%"));
					}
				}
				if (StringUtils.isNotBlank(nickName)) {
					if ((searchType != null) && (searchType.intValue() == 0)) {
						predicates.add(cb.equal(namePath, nickName));
					} else {
						predicates.add(cb.like(namePath, "%" + nickName + "%"));
					}
				}
				if (startDate != null) {
					predicates.add(cb.greaterThanOrEqualTo(registerDatePath, startDate));
				}
				if (endDate != null) {
					predicates.add(cb.lessThanOrEqualTo(registerDatePath, endDate));
				}

				Predicate[] arr = predicates.toArray(new Predicate[predicates.size()]);
				return query.where(arr).getRestriction();
			}
		};
	}
}
