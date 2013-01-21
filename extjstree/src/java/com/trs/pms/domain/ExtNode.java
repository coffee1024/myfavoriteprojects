package com.trs.pms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 *@author  liuguangqiang
 *@date    2012-11-21 下午12:02:16
 *@version 1.0
 **/
@Entity
@SelectBeforeUpdate(true)
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "treenode")
public class ExtNode {
		 
		 /**  树节点的id */
		@GeneratedValue(generator = "paymentableGenerator")     
		@GenericGenerator(name = "paymentableGenerator", strategy = "native")   
		@Column(name = "tid")
		@Id
		 private Long id;
		 
		 /** 
		  * 树节点的父id 
		  */
		@Column(name="parentid")
		 private Long parentId;
		@Column(name="text")
		 private String text = "";
		 /** 
		  * 权限树的权限节点选中属性
		   */
		@Column(name="href")
		private String href;
		@Column(name="leaf")
		private Boolean leaf = true;

		 public ExtNode() {
		 }

		 /**
		  * 构造extjs标准树和菜单树的节点.
		  * 
		  * @param id
		  * @param parentId
		  * @param text
		  */
		 public ExtNode(Long id, Long parentId, String text) {
		  this.id = id;
		  this.parentId = parentId;
		  this.text = text;
		 }

		 /**
		  * 构造extjs权限树的节点.
		  * 
		  * @param id
		  * @param parentId
		  * @param text
		  * @param leaf
		  */
		 public ExtNode(Long id, Long parentId, String text, boolean leaf) {
		  this.id = id;
		  this.parentId = parentId;
		  this.text = text;
		  this.leaf = leaf;
		 }


		 public Boolean getLeaf() {
			return leaf;
		}

		public void setLeaf(Boolean leaf) {
			this.leaf = leaf;
		}

		public Long getId() {
		  return id;
		 }

		 public void setId(Long id) {
		  this.id = id;
		 }

		 public Long getParentId() {
		  return parentId;
		 }

		 public void setParentId(Long parentId) {
		  this.parentId = parentId;
		 }

		 public String getText() {
		  return text;
		 }

		 public void setText(String text) {
		  this.text = text;
		 }

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}
		
		}

