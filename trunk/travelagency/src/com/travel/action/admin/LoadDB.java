package com.travel.action.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.tools.tree.ThisExpression;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;
import com.travel.utils.ConfigUtil;
import com.travel.utils.DBUtils;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class LoadDB extends BaseAction {
	private String filename;
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	public String execute() {
		filename=ConfigUtil.get("dbbackdir")+"/"+filename;
		logger.debug("还原数据库，文件为"+filename);
		if (DBUtils.load(filename)) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
