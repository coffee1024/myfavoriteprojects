package com.coffee.zw.core.orm;

import com.coffee.zw.core.orm.Page;

/**
 * trs检索结果
 * 
 * @author wanglei
 */
public class BasePage<T> extends Page<T> {

	/**
	 * trs检索翻页优化用
	 */
	private String licenseCode = null;

	/**
	 * trs特有的排序字段
	 */
	private String trsOrderby = null;

	/**
	 * trs是否二次检索
	 */
	private boolean secondSearch = false;

	/**
	 * trs结果是否标红
	 */
	private boolean highlight = true;
	
	/**
	 * 检索表达式
	 */
	private String searchExp = null;

	public BasePage(int pageSize) {
		super(pageSize);
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getTrsOrderby() {
		return trsOrderby;
	}

	public void setTrsOrderby(String trsOrderby) {
		this.trsOrderby = trsOrderby;
	}

	public boolean isSecondSearch() {
		return secondSearch;
	}

	public void setSecondSearch(boolean secondSearch) {
		this.secondSearch = secondSearch;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
	
	public String getSearchExp() {
		return searchExp;
	}

	public void setSearchExp(String searchExp) {
		this.searchExp = searchExp;
	}
}