package com.travel.action.admin;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.TravelAgency;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class AddAgency extends BaseAction {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private TravelAgency agency;
	private String createTime;
	public String execute() {
		logger.debug("用户登陆 "+agency);
		if (agency==null) {
			return ERROR;
		}
		createTime=StringUtils.trim(createTime);
		DateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		try {
			date=fmt.parse(createTime);
		} catch (Exception e) {
			logger.error("添加旅行社时间转换异常，使用当前时间");
		}
		Timestamp timestamp=new Timestamp(date.getTime());
		agency.setCreateTime(timestamp);
		DaoFactory.getAgencyDao().addAgency(agency);
		return SUCCESS;
	}
	public TravelAgency getAgency() {
		return agency;
	}
	public void setAgency(TravelAgency agency) {
		this.agency = agency;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
