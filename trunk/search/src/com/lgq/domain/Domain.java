package com.lgq.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
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
@Entity
@Indexed
@Table(name="TEST")
public class Domain{ 
	@GeneratedValue(generator = "paymentableGenerator" )  
	@GenericGenerator(name = "paymentableGenerator", strategy = "sequence",parameters = { @Parameter(name = "sequence", value = "SEQ_TEST") })
	@Column(name = "ID")
	@DocumentId
	private Integer id;
	@Column(name="CONTENT")
	@Field(name= "content" ,store= Store.YES, index =Index.YES,analyzer=@Analyzer(impl = ChineseAnalyzer.class ))
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
