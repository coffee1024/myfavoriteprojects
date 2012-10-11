package com.travel.action;
/**
 *@author  刘光强
 *@Date    2011-12-16 下午02:07:52
 *@ersion  1.0
 */
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,SessionAware{

	protected HttpServletRequest request;
	protected Map<String,Object> session;
	protected final String SUCCESS="success";
	protected final String ERROR="error";
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}

