package com.travel.entity;

import org.apache.lucene.document.Field;

/**
 *@author  刘光强
 *@date    2012-4-25 下午2:52:12
 *@version 1.0
 **/
public class LuceneField {
	private String fieldName;
	private Field.Store store;
	private Field.Index index;
	private Field.TermVector termVector;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Field.Store getStore() {
		return store;
	}
	public void setStore(Field.Store store) {
		this.store = store;
	}
	public Field.Index getIndex() {
		return index;
	}
	public void setIndex(Field.Index index) {
		this.index = index;
	}
	public Field.TermVector getTermVector() {
		return termVector;
	}
	public void setTermVector(Field.TermVector termVector) {
		this.termVector = termVector;
	}
}
