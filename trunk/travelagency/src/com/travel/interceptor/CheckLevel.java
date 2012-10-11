package com.travel.interceptor;


import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.travel.entity.AdminUser;

public class CheckLevel implements Interceptor {

	/*
	 * 销毁拦截器
	 */
	public void destroy() {
	}

	/*
	 * 
	 * 初始化拦截器
	 */
	public void init() {
	}

	public String intercept(ActionInvocation ai) throws Exception {
		Logger logger  =  LoggerFactory.getLogger(CheckLevel. class );
		ActionContext actionContext = ai.getInvocationContext();   
        HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST); 
		AdminUser user=(AdminUser)ai.getInvocationContext().getSession().get("loginUser");
		if (user==null) {
			return "error";
		}
		ai.invoke();			
		return "success";
	}

	
}
