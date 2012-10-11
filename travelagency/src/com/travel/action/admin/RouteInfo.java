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
import com.travel.dao.DaoFactory;
import com.travel.entity.Route;
import com.travel.entity.TravelAgency;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class RouteInfo extends BaseAction {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private Route route;
	private Integer rid;
	public String execute() {
		route=DaoFactory.getRouteDao().getRouteById(rid);
		return SUCCESS;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	
}
