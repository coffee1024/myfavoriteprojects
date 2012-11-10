package com.coffee.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;


/**
 * @Title 收藏夹实体类
 * 
 * @Author dulei
 * @CreateTime 2012-11-7
 * @Version 3.0
 */
@Indexed
@Entity
@SelectBeforeUpdate(true)
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "TEST")
public class FavoritesFolder implements Serializable{
	
	private static final long serialVersionUID = -4283203759378469944L;

	@Id
//	@GeneratedValue(generator = "paymentableGenerator")  
//	@GenericGenerator(name = "paymentableGenerator", strategy = "sequence",parameters = { @Parameter(name = "sequence", value = "SEQ_TEST") })
	@GeneratedValue(generator = "paymentableGenerator")     
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")   
	@Column(name = "ID")
	@DocumentId
	private Long id;
	@Field(name="content",store= Store.YES,index =Index.YES,analyze=Analyze.YES,analyzer=@Analyzer(impl = PaodingAnalyzer.class ))
	@Column(name="CONTENT")
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
