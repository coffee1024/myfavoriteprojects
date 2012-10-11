package com.travel.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 刘光强
 * @date 2012-4-4 上午11:43:08
 * @version 1.0
 **/
public class IndexSuggestWords{
	private static Logger logger=LoggerFactory.getLogger(IndexSuggestWords.class);
	private static String IDNEX_PATH = ConfigUtil.get("indexdir");
	public static void init() throws Exception{
		long a=System.currentTimeMillis();
		IDNEX_PATH =IDNEX_PATH+ "/searchsuggest";
		File file=new File(IDNEX_PATH+"/isexist.dat");
		if (file.exists()) {
			logger.debug("提示词文件已存在，跳过索引");
		}else {
			// 获取Paoding中文分词器
			Analyzer analyzer = new PaodingAnalyzer();
			// 建立索引
			Directory directory= new SimpleFSDirectory(new File(IDNEX_PATH));
			IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_35, analyzer);
			indexWriterConfig.setOpenMode(OpenMode.CREATE);
			IndexWriter writer = new IndexWriter(directory,indexWriterConfig);
			BufferedReader b=new BufferedReader((new InputStreamReader(new FileInputStream("H:/各种词典/pinyin_new_new.txt"),"UTF-8")));
			String[] strs;
			String string;
			while ((string=b.readLine())!=null) {
				Document doc = new Document();
				strs=string.split(" ");
				Field field = new Field("pinyin", strs[0], Field.Store.YES,Field.Index.NOT_ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS);
				Field field1 = new Field("word", strs[1], Field.Store.YES,Field.Index.NOT_ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS);
				doc.add(field);
				doc.add(field1);
				writer.addDocument(doc);
			}
			//这个方法在新增索引的情况会很有用，就是讲原来散落的索引文件重新进行整理合并！
			writer.forceMerge(1);
			writer.close();
			b.close();
			file.createNewFile();
			long c=System.currentTimeMillis();
			logger.debug("对检索提示词索引结束，耗时："+(c-a)+"毫秒");
		}
	}
}
