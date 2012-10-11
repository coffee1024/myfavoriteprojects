package com.travel.action.admin;

import java.util.List;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;
import com.travel.utils.ConfigUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class ListUserAction extends BaseAction {
	private List<AdminUser> adminUsers;
	private int pageNo=1;
	private int count;
	private int pagenum=10;
	private int totalpage;
	public String execute() {
		count=DaoFactory.getUserDao().getCount();
		try {
			pagenum=Integer.parseInt(ConfigUtil.get("pagenum"));
		} catch (Exception e) {
			LOG.debug("获取每页个数时出现异常，使用默认配置10条");
		}
		int tmp=count%pagenum;
		if (tmp==0) {
			totalpage=count/pagenum;
		}else {
			totalpage=count/pagenum+1;
		}
		if (totalpage==0) {
			totalpage=1;
		}
		if (pageNo>=totalpage) {
			pageNo=totalpage;
		}
		if (pageNo<=1) {
			pageNo=1;
		}
		adminUsers=DaoFactory.getUserDao().getUsersByPage(pageNo, pagenum);
		return SUCCESS;
	}
	public List<AdminUser> getAdminUsers() {
		return adminUsers;
	}
	public void setAdminUsers(List<AdminUser> adminUsers) {
		this.adminUsers = adminUsers;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	
	
}
