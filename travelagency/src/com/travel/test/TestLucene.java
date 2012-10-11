package com.travel.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;


/**
 * @author 刘光强
 * @date 2012-4-3 下午5:03:18
 * @version 1.0
 **/
public class TestLucene {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String IDNEX_PATH = "E:/travel/travelagency";
//		// 获取Paoding中文分词器
		Analyzer analyzer = new PaodingAnalyzer();
//		// 建立索引
		Directory directory= new SimpleFSDirectory(new File(IDNEX_PATH));
//		IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_35, analyzer);
//		indexWriterConfig.setOpenMode(OpenMode.CREATE);
//		IndexWriter writer = new IndexWriter(directory,indexWriterConfig);
//		Document doc;
//		BufferedReader b=new BufferedReader((new InputStreamReader(new FileInputStream("H:/各种词典/pinyin_new_new.txt"),"UTF-8")));
//		String[] strs;
//		String string;
//		while ((string=b.readLine())!=null) {
//			System.out.println(string);
//			doc = new Document();
//			strs=string.split(" ");
//			Field field = new Field("pinyin", strs[0], Field.Store.YES,Field.Index.NOT_ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS);
//			Field field1 = new Field("word", strs[1], Field.Store.YES,Field.Index.NOT_ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS);
//			doc.add(field);
//			doc.add(field1);
//			writer.addDocument(doc);
//		}
//		//这个方法在新增索引的情况会很有用，就是讲原来散落的索引文件重新进行整理合并！
//		writer.forceMerge(1);
//		writer.close();
//		b.close();
//		System.out.println("Indexed success!");
		// 检索
		String string1="很好";
		System.out.println("检索条件为："+string1);
		System.out.println("检索结果为：");
		IndexReader reader = IndexReader.open(directory);
		QueryParser parser = new QueryParser(Version.LUCENE_35,"description", analyzer);
		Query query = parser.parse(string1);
//		BooleanClause.Occur[] flags = {BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
//		Query query = MultiFieldQueryParser.parse(Version.LUCENE_36,new String[] {"123","山东","你好"}, new String[] {"id","name","description"},flags, analyzer);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs results=searcher.search(query,1000);
		ScoreDoc[] hits = results.scoreDocs;
		if (hits.length == 0) {
			System.out.println("hits.length=0");
			return;
		}
		System.out.println("共找到："+hits.length+"条记录");
//		for (int ii = 0; ii < hits.length; ii++) {
//			Document doc2 =searcher.doc(hits[ii].doc);
//			// 高亮处理
//			String text = doc2.get("pinyin");
//			System.out.println(text);
//			String field1=doc2.get("word");
//	        System.out.println(field1); 
//		}
		reader.close();
	}
	
}
