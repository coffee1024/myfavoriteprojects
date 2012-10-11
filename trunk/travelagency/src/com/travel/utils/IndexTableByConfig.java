package com.travel.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.List;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.dao.JdbcDaoSupport;
import com.travel.entity.LuceneField;
import com.travel.entity.LuceneTable;


/**
 * @author 刘光强
 * @date 2012-4-4 上午11:43:08
 * @version 1.0
 **/
public class IndexTableByConfig extends JdbcDaoSupport {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private String IDNEX_PATH = ConfigUtil.get("indexdir");
	public  void init() throws Exception{
		// 获取Paoding中文分词器
		Analyzer analyzer = new PaodingAnalyzer();
		//获取根元素tables
		long a=System.currentTimeMillis();
		List<LuceneTable> tables=LuceneConfig.getTables();
		for (int z = 0; z < tables.size(); z++) {
			//獲取table元素
			LuceneTable table=tables.get(z);
			String name=table.getTableName();
			String indexdirString=IDNEX_PATH+"/"+name;
			Directory directory= new SimpleFSDirectory(new File(indexdirString));
			IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_35, analyzer);
			File file1=new File(indexdirString+"/lastnum.txt");
			String sql="select * from "+name;
			indexWriterConfig.setOpenMode(OpenMode.CREATE);
			if (file1.exists()) {
				BufferedReader b=new BufferedReader(new FileReader(file1));
				String str;
				while ((str=b.readLine())!=null) {
					logger.debug("对"+name+"表进行增量索引，起始id为:"+str);
					sql="select * from "+name+" where id >"+str;
					indexWriterConfig.setOpenMode(OpenMode.APPEND);
				}
			}else {
				logger.debug("对 "+name+" 表进行全表索引......");
			}
			IndexWriter writer = new IndexWriter(directory,indexWriterConfig);
			ResultSet rs=super.query(sql);
			List<LuceneField> fields=table.getFields();
			Object lastnum="";
			while (rs.next()) {
			org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
			for (int i = 0; i < fields.size(); i++) {
			//获取field元素
			LuceneField field=fields.get(i);
			Object temp=rs.getObject(field.getFieldName());
			Field field1 = new Field(field.getFieldName(), temp==null?"":temp.toString(),field.getStore(),field.getIndex(),field.getTermVector());
			doc.add(field1);
			}
			writer.addDocument(doc);
			lastnum=rs.getObject("id");
			}
			File file=new File(indexdirString+"/lastnum.txt");
			FileWriter fileWriter=new FileWriter(file);
			fileWriter.write(lastnum.toString());
			fileWriter.flush();
			fileWriter.close();
			writer.forceMerge(1);
			writer.close();
		}
		//这个方法在新增索引的情况会很有用，就是讲原来散落的索引文件重新进行整理合并！
		long b=System.currentTimeMillis();
		logger.debug("对所有表索引结束。。。。。耗时"+(b-a)+"毫秒");
	}
}
