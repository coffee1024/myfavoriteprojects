package com.travel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.entity.AdminUser;
import com.travel.utils.HibernateSessionFactory;


/**
 *@author  刘光强
 *@date：2012-3-6 下午12:55:54
 *@version 1.0
 **/
public class UserDao implements IUserDao{
	private Logger logger=LoggerFactory.getLogger(UserDao.class);
	public AdminUser getUserById(Integer uid) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from AdminUser u where u.id= ? ");
		query.setParameter(0,uid);
		List<AdminUser> list=query.list();
		AdminUser AdminUser=null;
		if (!list.isEmpty()) {
			AdminUser=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return AdminUser;
	}
	public AdminUser getUserByName(String userName){
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from AdminUser u where u.userName= ? ");
		query.setParameter(0,userName);
		List<AdminUser> list=query.list();
		AdminUser AdminUser=null;
		if (!list.isEmpty()) {
			AdminUser=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return AdminUser;
	}
	public List<AdminUser> getAllUsers() {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from AdminUser");
		List<AdminUser> list=query.list();
		if (!list.isEmpty()) {
			return list;
		}
		HibernateSessionFactory.closeSession();
		return null;
	}
	public List<AdminUser> getUsersByPage(int pageNo,int count) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from AdminUser");
		query.setFirstResult((pageNo-1)*count);
		query.setMaxResults(count);
		List<AdminUser> list=query.list();
		HibernateSessionFactory.closeSession();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
	public boolean addUser(AdminUser user){
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		try {
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			logger.error("添加用户失败："+e.getMessage());
			transaction.rollback();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
		return true;
	}
	public boolean updateUser(AdminUser user){
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		try {
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			logger.error("更新用户失败："+e.getMessage());
			transaction.rollback();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
			return true;
	}
	public boolean deleteUser(AdminUser user){
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		try {
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			logger.error("删除用户失败："+e.getMessage());
			transaction.rollback();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return true;
	}
	public boolean userIsExist(String userName) {
		if (getUserByName(userName)==null) {
			return false;
		}
		return true;
	}
	public int getCount() {
		int count=0;
		try {
			count= getAllUsers().size();
		} catch (Exception e) {
			logger.debug("用户数量为空");
			return 0;
		}
		return count;
	}
	
}
