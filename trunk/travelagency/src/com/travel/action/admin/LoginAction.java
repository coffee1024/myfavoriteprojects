package com.travel.action.admin;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.tools.tree.ThisExpression;

import com.travel.action.BaseAction;
import com.travel.dao.DaoFactory;
import com.travel.entity.AdminUser;
import com.travel.utils.DESUtil;
import com.travel.utils.StringUtil;

/**
 * @author 刘光强
 * @date 2012-3-19 下午5:06:22
 * @version 1.0
 **/
public class LoginAction extends BaseAction {
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	private String username;
	private String password;
	private String msg;
	private boolean codeok;
	private String code;
	public String logout() {
		session.clear();
		return SUCCESS;
		
	}
	public String login() {
		logger.debug("登陆,用户名为  ：  "+username);
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(username)) {
			return ERROR;
		}
		try {
			DESUtil desUtil = new DESUtil();
			password=desUtil.encrypt(password);
		} catch (Exception e) {
			logger.error("登陆时加密用户名失败"+e);
			return ERROR;
		}
		AdminUser user = DaoFactory.getUserDao().getUserByName(username);
		if (user == null) {
			return ERROR;
		} else {
			if (password.equals(user.getPassword())) {
				String ip=StringUtil.getIPPosition(request);
				user.setLastIP(ip);
				user.setLastTime(new Timestamp(System.currentTimeMillis()));
				DaoFactory.getUserDao().updateUser(user);
				session.put("loginUser", user);
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
	}
	public String checkcode() {
		logger.debug("校验验证码开始");
		String rand=session.get("rand").toString();
		logger.debug("输入的验证码为"+code);
		logger.debug("session的验证码为"+rand);
		if (rand.equals(code)) {
			codeok=true;
		}else {
			codeok=false;
		}
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isCodeok() {
		return codeok;
	}
	public void setCodeok(boolean codeok) {
		this.codeok = codeok;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
