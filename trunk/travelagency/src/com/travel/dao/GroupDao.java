package com.travel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.travel.entity.AdminGroup;
import com.travel.entity.AdminUser;
import com.travel.utils.HibernateSessionFactory;

	/**
	 *@author  刘光强
	 *@date    2012-3-28 下午10:53:05
	 *@version 1.0
	 **/
public class GroupDao implements IGroupDao {

	public AdminGroup getGroupByName(String groupName) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from AdminGroup g where g.groupName= ? ");
		query.setParameter(0,groupName);
		List<AdminGroup> list=query.list();
		AdminGroup adminGroup=null;
		if (!list.isEmpty()) {
			adminGroup=list.get(0);	
		}
		return adminGroup;
	}

	public AdminGroup getGroupById(Integer gid) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from AdminGroup g where g.id= ? ");
		query.setParameter(0,gid);
		List<AdminGroup> list=query.list();
		AdminGroup adminGroup=null;
		if (!list.isEmpty()) {
			adminGroup=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return adminGroup;
	}

	public List<AdminGroup> getAllGroups() {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from AdminGroup");
		List<AdminGroup> list=query.list();
		return list;
	}

	public boolean addGroup(AdminGroup group) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateGroup(AdminGroup group) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteGroup(AdminGroup group) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
