package com.travel.action.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.action.BaseAction;
import com.travel.action.lucene.DeleteIndex;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;
import com.travel.entity.TravelAgency;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class DeleteAgency extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private int aid;
	private int pageNo;
	public String execute() {
		logger.debug("删除旅行社");
		try {
			DeleteIndex.delete("E:/travel/travelagency", aid+"", "id");
		} catch (Exception e) {
			logger.error("删除旅行社索引失败"+e);
		}
		TravelAgency agency=DaoFactory.getAgencyDao().getAgencyById(aid);
		if (agency!=null) {
			DaoFactory.getAgencyDao().deleteAgency(agency);
		}
		return SUCCESS;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}
