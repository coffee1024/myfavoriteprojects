package com.travel.action.admin;


import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;
import com.travel.entity.Customer;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class CustomerInfo extends BaseAction {
	private int uid;
	private Customer user;
	public String execute() {
		user=DaoFactory.getCustomerDao().getCustomerById(uid);
		return SUCCESS;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUid() {
		return uid;
	}
	public Customer getUser() {
		return user;
	}
	public void setUser(Customer user) {
		this.user = user;
	}
}
