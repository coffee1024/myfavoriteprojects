package com.coffee.dao;

import java.util.List;

import org.ansj.lucene3.AnsjAnalysis;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.util.Version;
import org.hibernate.search.FullTextQuery;
import org.springframework.stereotype.Repository;

import com.coffee.core.orm.hibernate.HibernateDao;
import com.coffee.domain.TestDomain;

/**
 */
@Repository
public class FavoritesFolderDao extends HibernateDao<TestDomain, Long> {
	
	public List<TestDomain> fullTextQuary(String content) throws ParseException{
		QueryParser parser =  new  QueryParser(Version.LUCENE_36, "content", new AnsjAnalysis()); 
		FullTextQuery query=getFullTextSession().createFullTextQuery(parser.parse(content), TestDomain.class);
		
		List<TestDomain> list=query.list();
		return list;
	}
	
}
