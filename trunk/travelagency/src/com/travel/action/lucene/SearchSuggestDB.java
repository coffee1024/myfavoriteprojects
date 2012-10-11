package com.travel.action.lucene;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.dao.DaoFactory;
import com.travel.dao.JdbcDaoSupport;
import com.travel.dao.SuggestWordDao;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class SearchSuggestDB extends JdbcDaoSupport {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private List<String> suggestwords =new ArrayList<String>();
	private String searchword="";
	private int num=10;
	public String execute() {
		try {
			suggestwords=DaoFactory.getSuggestWordDao().getSuggest(searchword.trim(), num);
		} catch (Exception e) {
			logger.error("获取检索提示词失败");
		}
		return "success";
	}
	public List<String> getSuggestwords() {
		return suggestwords;
	}
	public void setSuggestwords(List<String> suggestwords) {
		this.suggestwords = suggestwords;
	}
	public String getSearchword() {
		return searchword;
	}
	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}
	public static void main(String[] args) {
		SearchSuggestDB searchSuggest=new SearchSuggestDB();
		searchSuggest.setSearchword("nihao");
		searchSuggest.execute();
	}
	
}
