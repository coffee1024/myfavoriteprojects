package com.travel.action.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.action.BaseAction;
import com.travel.action.lucene.DeleteIndex;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;
import com.travel.entity.Route;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class DeleteRoute extends BaseAction {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private int rid;
	private int pageNo;
	public String execute() {
		logger.debug("删除客户");
		try {
			DeleteIndex.delete("E:/travel/route", rid+"", "id");
		} catch (Exception e) {
			logger.error("删除路线索引失败"+e);
		}
		Route route=DaoFactory.getRouteDao().getRouteById(rid);
		if (route!=null) {
			DaoFactory.getRouteDao().deleteRoute(route);
		}
		return SUCCESS;
	}
	
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}
