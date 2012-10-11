package com.travel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.travel.entity.CustomerGroup;
import com.travel.entity.CustomerGroup;
import com.travel.utils.HibernateSessionFactory;

	/**
	 *@author  刘光强
	 *@date    2012-3-28 下午10:53:05
	 *@version 1.0
	 **/
public class CustomerGroupDao implements ICustomerGroupDao {

	public CustomerGroup getGroupByName(String groupName) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from CustomerGroup g where g.groupName= ? ");
		query.setParameter(0,groupName);
		List<CustomerGroup> list=query.list();
		CustomerGroup customerGroup=null;
		if (!list.isEmpty()) {
			customerGroup=list.get(0);	
		}
		return customerGroup;
	}

	public CustomerGroup getGroupById(Integer gid) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from CustomerGroup g where g.id= ? ");
		query.setParameter(0,gid);
		List<CustomerGroup> list=query.list();
		CustomerGroup CustomerGroup=null;
		if (!list.isEmpty()) {
			CustomerGroup=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return CustomerGroup;
	}

	public List<CustomerGroup> getAllGroups() {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from CustomerGroup");
		List<CustomerGroup> list=query.list();
		return list;
	}

	public boolean addGroup(CustomerGroup group) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateGroup(CustomerGroup group) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteGroup(CustomerGroup group) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
