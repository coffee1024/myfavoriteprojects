package com.coffee.dao;

import java.util.List;

import org.ansj.lucene3.AnsjAnalysis;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.util.Version;
import org.hibernate.search.FullTextQuery;
import org.springframework.stereotype.Repository;

import com.coffee.core.orm.hibernate.HibernateDao;
import com.coffee.domain.FavoritesFolder;

/**
 */
@Repository
public class FavoritesFolderDao extends HibernateDao<FavoritesFolder, Long> {
	
	public List<FavoritesFolder> fullTextQuary(String content) throws ParseException{
		QueryParser parser =  new  QueryParser(Version.LUCENE_36, "content", new AnsjAnalysis()); 
		FullTextQuery query=getFullTextSession().createFullTextQuery(parser.parse(content), FavoritesFolder.class);
		
		List<FavoritesFolder> list=query.list();
		return list;
	}
	
}
