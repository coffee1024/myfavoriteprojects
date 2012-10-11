package com.travel.action.lucene;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

/**
 *@author  刘光强
 *@date    2012-4-19 上午11:51:40
 *@version 1.0
 **/
public class CacheSearch {
		
	public static CacheList refreshCache (String IDNEX_PATH,String[] searchword,String[] colum,BooleanClause.Occur[] flags) throws Exception{
		
		Analyzer analyzer = new PaodingAnalyzer();
		Directory directory= new SimpleFSDirectory(new File(IDNEX_PATH));
		IndexReader reader = IndexReader.open(directory);
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_CURRENT,searchword, colum,flags, analyzer);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs results=searcher.search(query,100000);
		ScoreDoc[] hits = results.scoreDocs;
		CacheList cacheList=new CacheList();
		if (hits.length == 0) {
			System.out.println("hits.length=0");
			return null;
		}
		 cacheList.flushCache();
		 cacheList.setRecoredCount(hits.length);
		 int pageSize=cacheList.getPageSize();
         int startIndex = (cacheList.getPageIndex() - 1) * pageSize;
         int EndIndex = (cacheList.getPageIndex() - 1) * pageSize +(pageSize*cacheList.getCacheSize());
        if (EndIndex > hits.length){
            EndIndex = hits.length;
        	}
		List<List<String>> list=new ArrayList<List<String>>();
        for (int i = startIndex; i < EndIndex; i++){
			List<String> list2=new ArrayList<String>();
			Document doc2 =searcher.doc(hits[i].doc);
			// 高亮处理
			SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");  
			Highlighter highlighter = new Highlighter(simpleHtmlFormatter,new QueryScorer(query));  
			for (int j = 0; j < colum.length; j++) {
				String field=colum[j];
		        TokenStream tokenStream1 = analyzer.tokenStream(field,new StringReader(doc2.get(field)));  
		        String highlighterStr1 = highlighter.getBestFragment(tokenStream1, doc2.get(field));  
		        list2.add(highlighterStr1 == null ? doc2.get(field) : highlighterStr1); 
			}
			list.add(list2);
		}
		reader.close();
		cacheList.setPageIndex(cacheList.getPageIndex()+cacheList.getCacheSize());
		cacheList.setCachePage(list);
		cacheList.setFirst(false);
		return cacheList;
	}
}
