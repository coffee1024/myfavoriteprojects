package com.travel.action.lucene;

import java.io.File;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *@author  刘光强
 *@date    2012-4-23 下午4:33:40
 *@version 1.0
 **/
public class DeleteIndex {
	private static Logger logger=LoggerFactory.getLogger(DeleteIndex.class);
	public static void delete(String indexpath,String id,String fieldName) throws Exception{
		logger.debug("根据id删除索引"+"路径为"+indexpath+"id为"+id+"字段名为"+fieldName);
		Analyzer analyzer = new PaodingAnalyzer();
		Directory directory= new SimpleFSDirectory(new File(indexpath));
		IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
		indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		IndexWriter writer = new IndexWriter(directory,indexWriterConfig);
		QueryParser parser = new QueryParser(Version.LUCENE_CURRENT,fieldName, analyzer);
		Query query = parser.parse(id);
		writer.deleteDocuments(query);
		writer.forceMerge(1);
		writer.close();
	}

}
