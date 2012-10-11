package com.travel.entity;

import java.util.List;

/**
 *@author  刘光强
 *@date    2012-4-25 下午2:52:12
 *@version 1.0
 **/
public class LuceneTable {
	private String tableName;
	private List<LuceneField> fields;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<LuceneField> getFields() {
		return fields;
	}
	public void setFields(List<LuceneField> fields) {
		this.fields = fields;
	}
}
