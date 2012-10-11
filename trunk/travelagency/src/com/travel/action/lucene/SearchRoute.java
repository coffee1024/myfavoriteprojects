package com.travel.action.lucene;


import java.util.List;

import org.apache.lucene.search.BooleanClause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.dao.SuggestWordDao;
import com.travel.utils.ConfigUtil;
import com.travel.utils.StringUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class SearchRoute extends BaseAction {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private String IDNEX_PATH = ConfigUtil.get("indexdir")+"/route";
	private BooleanClause.Occur[] flags = {BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
	private ResultList results;
	private String searchword="";
	private List<String> searchsuggest;
	private int pageNo=1;
	public String execute() {
		searchword=StringUtil.transformSolrMetacharactor(searchword);
		try {
			results =BaseSearch.search(IDNEX_PATH,new String[] {searchword,searchword,searchword,searchword,searchword}, new String[] {"id","name","days","keywords","description"}, new String[] {"id","name","days","keywords","description"},flags,pageNo,true);
			if (results!=null) {
				pageNo=results.getPageNo();
			}
			searchsuggest=DaoFactory.getSuggestWordDao().getResultSuggest(searchword, 5);
			DaoFactory.getSuggestWordDao().insertResultSuggest(searchword);
		} catch (Exception e) {
			logger.error("检索异常");
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public ResultList getResults() {
		return results;
	}
	public void setResults(ResultList results) {
		this.results = results;
	}
	public String getSearchword() {
		return searchword;
	}
	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}
	public List<String> getSearchsuggest() {
		return searchsuggest;
	}
	public void setSearchsuggest(List<String> searchsuggest) {
		this.searchsuggest = searchsuggest;
	}
}
