package com.lgq.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
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
@Entity
@Table(name = "TEST")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
public class Domain{ 
	@Id
	@GeneratedValue(generator = "paymentableGenerator")  
	@GenericGenerator(name = "paymentableGenerator", strategy = "sequence",parameters = { @Parameter(name = "sequence", value = "SEQ_TEST") })
	@Column(name = "ID")
	@DocumentId
	private Integer id;
	@Field(name="content",store= Store.YES,index =Index.YES,analyze=Analyze.YES,analyzer=@Analyzer(impl = PaodingAnalyzer.class ))
	@Column(name="content")
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
