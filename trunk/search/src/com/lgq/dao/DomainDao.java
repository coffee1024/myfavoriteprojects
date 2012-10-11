package com.lgq.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;

import com.lgq.domain.Domain;

/**
 *@author  liuguangqiang
 *@date    2012-10-10 下午05:20:35
 *@version 1.0
 **/
public class DomainDao {
	public void addDomain(Domain domain){
		FullTextSession fullTextSession =HibernateSessionFactory.getFullTextSession();
		Transaction transaction= fullTextSession.beginTransaction();
		try {
			fullTextSession.save(domain);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally{
			fullTextSession.close();
//			HibernateSessionFactory.closeSession();
		}
		
	}
}
