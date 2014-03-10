package com.coffee.photo.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coffee.photo.entity.account.User;

public interface UserDao extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
	User findByLoginName(String loginName);

	User findByEmail(String email);

	List<User> findByAppId(Long appId);

}
