package com.travel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.entity.Customer;
import com.travel.utils.HibernateSessionFactory;


/**
 *@author  刘光强
 *@date：2012-3-6 下午12:55:54
 *@version 1.0
 **/
public class CustomerDao implements ICustomerDao{
	private Logger logger=LoggerFactory.getLogger(CustomerDao.class);
	public Customer getCustomerById(Integer uid) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from Customer u where u.id= ? ");
		query.setParameter(0,uid);
		List<Customer> list=query.list();
		Customer Customer=null;
		if (!list.isEmpty()) {
			Customer=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return Customer;
	}
	public Customer getCustomerByName(String userName){
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from Customer u where u.userName= ? ");
		query.setParameter(0,userName);
		List<Customer> list=query.list();
		Customer Customer=null;
		if (!list.isEmpty()) {
			Customer=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return Customer;
	}
	public List<Customer> getAllCustomers() {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from Customer");
		List<Customer> list=query.list();
		HibernateSessionFactory.closeSession();
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}
	public List<Customer> getCustomersByPage(int pageNo,int count) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from Customer");
		query.setFirstResult((pageNo-1)*count);
		query.setMaxResults(count);
		List<Customer> list=query.list();
		HibernateSessionFactory.closeSession();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
	public boolean addCustomer(Customer user){
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
	public boolean updateCustomer(Customer user){
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
	public boolean deleteCustomer(Customer user){
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
		if (getCustomerByName(userName)==null) {
			return false;
		}
		return true;
	}
	public int getCount() {
		int count=0;
		try {
			count= getAllCustomers().size();
		} catch (Exception e) {
			logger.debug("用户数量为空");
			return 0;
		}
		return count;
	}
	
}
