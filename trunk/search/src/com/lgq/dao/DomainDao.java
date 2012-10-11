package com.lgq.dao;

import java.util.List;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.util.Version;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextQuery;
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
	public List<Domain> getDomainByContent(String content){
		FullTextSession fullTextSession =HibernateSessionFactory.getFullTextSession();
		try {
			QueryParser parser =  new  QueryParser(Version.LUCENE_CURRENT, "content", new PaodingAnalyzer()); 
			FullTextQuery query=fullTextSession.createFullTextQuery(parser.parse(content), Domain.class);
			List<Domain> list=query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			fullTextSession.close();
//			HibernateSessionFactory.closeSession();
		}
	}
}
