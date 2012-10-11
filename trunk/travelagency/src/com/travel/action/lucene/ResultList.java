package com.travel.action.lucene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.entity.SearchField;
import com.travel.utils.ConfigUtil;

/**
 *@author  刘光强
 *@date    2012-4-19 下午1:00:09
 *@version 1.0
 **/
class ResultList{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
    private int pageNo=1;
    private int pageSize=10;
    private int recoredCount;
    private int totalPage;
    private List<SearchField> results = new ArrayList<SearchField>();
     public ResultList(){
    	 try {
 			pageSize=Integer.parseInt(ConfigUtil.get("pagenum"));
 		} catch (Exception e) {
 			logger.error("查询时转换每页数量异常，使用默认配置"+pageSize);
 		}
     }

     public int getTotalPage() {
		return totalPage;
	}

	public int getRecoredCount() {
		return recoredCount;
	}

	public void setRecoredCount(int recoredCount) {
		this.recoredCount = recoredCount;
		int tmp=recoredCount%pageSize;
		if (tmp==0) {
			totalPage=recoredCount/pageSize;
		}else {
			totalPage=recoredCount/pageSize+1;
		}
		if (totalPage==0) {
			totalPage=1;
		}
	}

	

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public List<SearchField> getResults() {
		return results;
	}

	public void setResults(List<SearchField> results) {
		this.results = results;
	}

}
