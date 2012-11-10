package com.coffee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.util.Version;
import org.hibernate.Query;
import org.hibernate.search.FullTextQuery;
import org.springframework.stereotype.Repository;

import com.coffee.core.orm.hibernate.HibernateDao;
import com.coffee.domain.FavoritesFolder;

/**
 */
@Repository
public class FavoritesFolderDao extends HibernateDao<FavoritesFolder, Long> {
	
	public List<FavoritesFolder> fullTextQuary(String content) throws ParseException{
		QueryParser parser =  new  QueryParser(Version.LUCENE_CURRENT, "content", new PaodingAnalyzer()); 
		FullTextQuery query=getFullTextSession().createFullTextQuery(parser.parse(content), FavoritesFolder.class);
		List<FavoritesFolder> list=query.list();
		return list;
	}
	
}
