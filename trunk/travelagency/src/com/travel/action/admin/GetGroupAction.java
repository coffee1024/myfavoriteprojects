package com.travel.action.admin;

import java.util.List;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminGroup;
import com.travel.entity.AdminUser;
import com.travel.utils.ConfigUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class GetGroupAction extends BaseAction {
	private List<AdminGroup> adminGroup;
	public String execute() {
		adminGroup=DaoFactory.getGroupDao().getAllGroups();
		return SUCCESS;
	}
	public List<AdminGroup> getAdminGroup() {
		return adminGroup;
	}
	public void setAdminGroup(List<AdminGroup> adminGroup) {
		this.adminGroup = adminGroup;
	}
}
