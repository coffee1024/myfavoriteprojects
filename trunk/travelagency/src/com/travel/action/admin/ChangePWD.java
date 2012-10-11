package com.travel.action.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.tools.tree.ThisExpression;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;
import com.travel.utils.DESUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class ChangePWD extends BaseAction {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	private String oldpwd;
	private String newpwd;
	public String execute() {
		AdminUser user=null;
		try {
			user=(AdminUser)session.get("loginUser");
		} catch (Exception e) {
			logger.error("修改密码时获取用户失败");
			return ERROR;
		}
		logger.debug("修改密码"+user);
		if (user==null) {
			return ERROR;
		}
		try {
			DESUtil desUtil=new DESUtil();
			String tmp=desUtil.decrypt(user.getPassword());
			if (tmp.equals(oldpwd)) {
				user.setPassword(desUtil.encrypt(newpwd));
			}else {
				return ERROR;
			}
		} catch (Exception e) {
			logger.error("添加用户时加密密码出错"+e);
			return ERROR;
		}
		DaoFactory.getUserDao().updateUser(user);
		return SUCCESS;
	}
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	
	
	}
