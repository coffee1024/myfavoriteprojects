package com.travel.action.admin;

import java.util.List;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminGroup;
import com.travel.entity.AdminUser;
import com.travel.entity.CustomerGroup;
import com.travel.utils.ConfigUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class GetCusGroupAction extends BaseAction {
	private List<CustomerGroup> customerGroup;
	public String execute() {
		customerGroup=DaoFactory.getCustomerGroupDao().getAllGroups();
		return SUCCESS;
	}
	public List<CustomerGroup> getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(List<CustomerGroup> customerGroup) {
		this.customerGroup = customerGroup;
	}
	
}
