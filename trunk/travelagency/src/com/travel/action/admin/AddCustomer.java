package com.travel.action.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.tools.tree.ThisExpression;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;
import com.travel.entity.Customer;
import com.travel.utils.DESUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class AddCustomer extends BaseAction {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	private Customer user;
	public String execute() {
		logger.debug("用户登陆 "+user);
		if (user==null) {
			return ERROR;
		}
		try {
			DESUtil desUtil=new DESUtil();
			user.setPassword(desUtil.encrypt(user.getPassword()));
		} catch (Exception e) {
			logger.error("添加客户时加密密码出错"+e);
		}
		user.setCustomerGroup(DaoFactory.getCustomerGroupDao().getGroupById(user.getGroupId()));
		DaoFactory.getCustomerDao().addCustomer(user);
		return SUCCESS;
	}
	public Customer getUser() {
		return user;
	}
	public void setUser(Customer user) {
		this.user = user;
	}
	
}
