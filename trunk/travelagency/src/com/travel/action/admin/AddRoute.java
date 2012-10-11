package com.travel.action.admin;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.action.BaseAction;
import com.travel.action.lucene.GetKeyWords;
import com.travel.dao.DaoFactory;
import com.travel.entity.Route;
import com.travel.entity.TravelAgency;
import com.travel.utils.ConfigUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class AddRoute extends BaseAction {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private Route route;
	private String beginTime;
	private String endTime;
	private int keywordsnum=5;
	private String keywords;
	public String execute() {
		logger.debug("添加路线 "+route);
		if (route==null) {
			return ERROR;
		}
		DateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		beginTime=StringUtils.trim(beginTime);
		Date begin=new Date();
		Date end=new Date();
		try {
			begin=fmt.parse(beginTime);
			end=fmt.parse(endTime);
		} catch (Exception e) {
			logger.error("添加线路时间转换异常，使用当前时间");
		}
		if (StringUtils.isEmpty(keywords)) {
			logger.debug("输入的关键词为空，使用系统自动抽取功能");
			try {
				keywordsnum=Integer.parseInt(ConfigUtil.get("keywordnum"));
			} catch (Exception e) {
				logger.error("从配置文件中读取关键词个数出错，使用默认配置"+keywordsnum);
			}
			keywords=GetKeyWords.cutKeyWordsString(route.getDescription(), keywordsnum);
		}
		route.setKeywords(keywords);
		Long days = (end.getTime()-begin.getTime())/ (1000 * 60 * 60 * 24);
		Integer i=Integer.parseInt(days.toString());
		route.setDays(i);
		route.setBeginTime(new Timestamp(begin.getTime()));
		route.setEndTime(new Timestamp(end.getTime()));
		DaoFactory.getRouteDao().addRoute(route);
		return SUCCESS;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
