package com.travel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.entity.Route;
import com.travel.entity.AdminUser;
import com.travel.utils.HibernateSessionFactory;

	/**
	 *@author  刘光强
	 *@date    2012-3-28 下午10:53:05
	 *@version 1.0
	 **/
public class RouteDao implements IRouteDao {
private Logger logger=LoggerFactory.getLogger(this.getClass());
	public Route getRouteByName(String routeName) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from Route g where g.routeName= ? ");
		query.setParameter(0,routeName);
		List<Route> list=query.list();
		Route adminRoute=null;
		if (!list.isEmpty()) {
			adminRoute=list.get(0);	
		}
		return adminRoute;
	}

	public Route getRouteById(Integer gid) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from Route g where g.id= ? ");
		query.setParameter(0,gid);
		List<Route> list=query.list();
		Route adminRoute=null;
		if (!list.isEmpty()) {
			adminRoute=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return adminRoute;
	}

	public List<Route> getAllRoutes() {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from Route");
		List<Route> list=query.list();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	public boolean addRoute(Route route) {
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		try {
			session.save(route);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
		return true;
	}

	public boolean updateRoute(Route route) {
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		try {
			session.update(route);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
			return true;
	}

	public boolean deleteRoute(Route route) {
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		try {
			session.delete(route);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			return false;
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return true;
	}
	public int getCount() {
		int count=0;
		try {
			count= getAllRoutes().size();
		} catch (Exception e) {
			logger.error("线路数量为空");
			return 0;
		}
		return count;
	}
	
	public List<Route> getRoutesByPage(int pageNo,int count) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from Route");
		query.setFirstResult((pageNo-1)*count);
		query.setMaxResults(count);
		List<Route> list=query.list();
		HibernateSessionFactory.closeSession();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
}
