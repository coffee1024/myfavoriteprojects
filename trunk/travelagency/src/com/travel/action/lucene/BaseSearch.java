package com.travel.action.lucene;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.entity.SearchField;

/**
 *@author  刘光强
 *@date    2012-4-19 上午11:51:40
 *@version 1.0
 **/
public class BaseSearch {
		private static Logger logger=LoggerFactory.getLogger(BaseSearch.class);
	public static ResultList search (String IDNEX_PATH,String[] searchword,String[] colum,String[] getcolum,BooleanClause.Occur[] flags,int pageNo,boolean highlight) throws Exception{
		Analyzer analyzer = new PaodingAnalyzer();
		Directory directory= new SimpleFSDirectory(new File(IDNEX_PATH));
		IndexReader reader = IndexReader.open(directory);
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_CURRENT,searchword, colum,flags, analyzer);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs results=searcher.search(query,100000);
		ScoreDoc[] hits = results.scoreDocs;
		ResultList resultList=new ResultList();
		if (hits.length == 0) {
			logger.debug("hits.length=0");
			return null;
		}
		logger.debug("基础检索的数量为:"+hits.length);
		resultList.setRecoredCount(hits.length);
		if (pageNo>=resultList.getTotalPage()) {
			pageNo=resultList.getTotalPage();
		}
		if (pageNo<=1) {
			pageNo=1;
		}
		resultList.setPageNo(pageNo);
		int pageSize=resultList.getPageSize();
		int startIndex = (pageNo - 1) * pageSize;
        int EndIndex = (pageNo - 1) * pageSize +pageSize;
        if (EndIndex > hits.length)
        {
            EndIndex = hits.length;
        }
		List<SearchField> list=new ArrayList<SearchField>();
        for (int i = startIndex; i < EndIndex; i++){
			List<String> list2=new ArrayList<String>();
			Document doc2 =searcher.doc(hits[i].doc);
			// 高亮处理
			SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");  
			Highlighter highlighter = new Highlighter(simpleHtmlFormatter,new QueryScorer(query));  
			for (int j = 0; j < getcolum.length; j++) {
				String field=getcolum[j];
				if (highlight) {
					TokenStream tokenStream1 = analyzer.tokenStream(field,new StringReader(doc2.get(field)));  
					String highlighterStr1 = highlighter.getBestFragment(tokenStream1, doc2.get(field));  
					list2.add(highlighterStr1 == null ? doc2.get(field) : highlighterStr1); 
				}else {
					list2.add(doc2.get(field));
				}
			}
			SearchField searchField=new SearchField();
			searchField.setId(doc2.get("id"));
			searchField.setList(list2);
			list.add(searchField);
		}
		reader.close();
		resultList.setResults(list);
		return resultList;
	}
}