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
public class CustomerExist extends BaseAction {
	private String userName;
	private boolean userExist;
	public String execute() {
		Customer user=DaoFactory.getCustomerDao().getCustomerByName(userName);
		if (user==null) {
			userExist=false;
		}else {
			userExist=true;
		}
		return SUCCESS;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isUserExist() {
		return userExist;
	}
	public void setUserExist(boolean userExist) {
		this.userExist = userExist;
	}
	
}
