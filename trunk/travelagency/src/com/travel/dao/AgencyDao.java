package com.travel.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.entity.Route;
import com.travel.entity.TravelAgency;
import com.travel.utils.HibernateSessionFactory;


/**
 *@author  刘光强
 *@date：2012-3-6 下午12:55:54
 *@version 1.0
 **/
public class AgencyDao implements IAgencyDao{
	private Logger logger=LoggerFactory.getLogger(AgencyDao.class);
	public TravelAgency getAgencyById(Integer uid) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from TravelAgency u where u.id= ? ");
		query.setParameter(0,uid);
		List<TravelAgency> list=query.list();
		TravelAgency TravelAgency=null;
		if (!list.isEmpty()) {
			TravelAgency=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return TravelAgency;
	}
	public TravelAgency getAgencyByName(String userName){
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from TravelAgency u where u.userName= ? ");
		query.setParameter(0,userName);
		List<TravelAgency> list=query.list();
		TravelAgency TravelAgency=null;
		if (!list.isEmpty()) {
			TravelAgency=list.get(0);	
		}
		HibernateSessionFactory.closeSession();
		return TravelAgency;
	}
	public List<TravelAgency> getAllAgencys() {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from TravelAgency");
		List<TravelAgency> list=query.list();
		if (!list.isEmpty()) {
			return list;
		}
		HibernateSessionFactory.closeSession();
		return null;
	}
	public List<TravelAgency> getAgencysByPage(int pageNo,int count) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("from TravelAgency");
		query.setFirstResult((pageNo-1)*count);
		query.setMaxResults(count);
		List<TravelAgency> list=query.list();
		HibernateSessionFactory.closeSession();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
	public boolean addAgency(TravelAgency user){
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
	public boolean updateAgency(TravelAgency user){
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
	public boolean deleteAgency(TravelAgency user){
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
		if (getAgencyByName(userName)==null) {
			return false;
		}
		return true;
	}
	public int getCount() {
		int count=0;
		try {
			count= getAllAgencys().size();
		} catch (Exception e) {
			logger.debug("用户数量为空");
		}
		return count;
	}
}
