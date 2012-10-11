package com.travel.action.lucene;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.lucene.search.BooleanClause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.Route;
import com.travel.entity.TravelAgency;
import com.travel.utils.ConfigUtil;
import com.travel.utils.StringUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class CutKeyWordsAction extends BaseAction {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private String context;
	private String keywords;
	private int keywordsnum=5;
	public String execute() {
		try {
			keywordsnum=Integer.parseInt(ConfigUtil.get("keywordnum"));
		} catch (Exception e) {
			logger.error("从配置文件中读取关键词个数出错，使用默认配置"+keywordsnum);
		}
		logger.debug("原文："+context);
		keywords=GetKeyWords.cutKeyWordsString(context, 5);
		return SUCCESS;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
}
