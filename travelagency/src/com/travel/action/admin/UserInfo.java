package com.travel.action.admin;


import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class UserInfo extends BaseAction {
	private int uid;
	private AdminUser user;
	public String execute() {
		user=DaoFactory.getUserDao().getUserById(uid);
		return SUCCESS;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public AdminUser getUser() {
		return user;
	}
	public int getUid() {
		return uid;
	}
	public void setUser(AdminUser user) {
		this.user = user;
	}
}
