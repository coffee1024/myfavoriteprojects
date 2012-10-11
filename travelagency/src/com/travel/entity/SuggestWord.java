package com.travel.entity;
/**
 *@author  刘光强
 *@date    2012-4-25 上午11:39:05
 *@version 1.0
 **/
public class SuggestWord {
	private Integer id;
	private String pinyin;
	private String words;
	private Integer searchnum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public Integer getSearchnum() {
		return searchnum;
	}
	public void setSearchnum(Integer searchnum) {
		this.searchnum = searchnum;
	}
}
