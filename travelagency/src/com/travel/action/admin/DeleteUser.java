package com.travel.action.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travel.action.BaseAction;
import com.travel.action.lucene.DeleteIndex;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class DeleteUser extends BaseAction {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private int uid;
	private int pageNo;
	public String execute() {
		logger.debug("删除员工");
		try {
			DeleteIndex.delete("E:/travel/adminuser", uid+"", "id");
		} catch (Exception e) {
			logger.error("删除员工索引失败"+e);
		}
		AdminUser user=DaoFactory.getUserDao().getUserById(uid);
		if (user!=null) {
			DaoFactory.getUserDao().deleteUser(user);
		}
		return SUCCESS;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}
