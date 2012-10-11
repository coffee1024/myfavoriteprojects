package com.lgq.domain;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;



/**
 *@author  liuguangqiang
 *@date    2012-9-24 下午05:38:34
 *@version 1.0
 **/
@Indexed
public class Domain{ 
	@DocumentId
	private Integer id;
	@Field(name="content",store= Store.YES,index =Index.YES,analyze=Analyze.YES,analyzer=@Analyzer(impl = PaodingAnalyzer.class ))
	private String content;
	
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}  
