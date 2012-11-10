package com.coffee.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title Cookie/Session常用类
 * @Author ZhouQiang
 * @CrTime 2010-6-29 下午05:55:37
 * @Version 1.0
 */
public class ContextUtils {
	protected static Logger logger = LoggerFactory.getLogger(ContextUtils.class);

	/**
	 * 简单Cookie设置方法
	 */
	public static void setCookie(HttpServletResponse res, String key,
			String value) {
		Cookie cookie = new Cookie(key, null);
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			if(StringUtils.isBlank(value)){
				cookie = new Cookie(key, value);//清空key的cookie内容
				cookie.setMaxAge(0); 
				res.addCookie(cookie);
			}
			else{
			logger.info("Cookie设置出错，尝试对空值进行设置。");
			}
			return;
		}
		else{
		cookie = new Cookie(key, value);
		cookie.setMaxAge(60 * 60 * 24 * 7);
		res.addCookie(cookie);
		}
		
//		System.out.println(cookie.getName());
	}

	/**
	 * 简单Cookie获取方法
	 */
	public static String getCookie(HttpServletRequest req, String key) {
		String value = "";
		if(StringUtils.isBlank(key)){
			logger.info("获取Cookie出错，参数为空。");
			return value;
		}
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(key)) {
					value = c.getValue();
					break;
				}
			}
		}
		if(StringUtils.isBlank(value)){
			logger.info("键值为 [{}] 的Cookie值为空。", key);
		}
		return value;
	}
	
	/**
	 * 删除一个cookie
	 */
	public static String delCookie(HttpServletRequest req, String key) {
		String value = "";
		if(StringUtils.isBlank(key)){
			logger.info("获取Cookie出错，参数为空。");
			return value;
		}
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(key)) {
					cookies[0].setMaxAge(0);       
//					 res.addCookie(cookies[i]);
					break;
				}
			}
		}
		if(StringUtils.isBlank(value)){
			logger.info("找不到键值为 [{}] 的Cookie值。", key);
		}
		return value;
	}
	
	/**
	 * liudawei
	 * 2010-8-11
	 * 删除一个cookie
	 */
	public static void delCookie(HttpServletResponse res,HttpServletRequest req, String key) {
		if(StringUtils.isBlank(key)){
			logger.info("获取Cookie出错，参数为空。");
		}
		Cookie cookie = new Cookie(key, null); 
		cookie.setMaxAge(0);
		res.addCookie(cookie); 
	}
	
	
	/**
	 * session设置方法
	 */
	public static void setSession(HttpServletRequest request,String key,
			String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			logger.info("Session设置出错，尝试对空值进行设置。");
			return;
		}
		HttpSession session = request.getSession();
		
		
		session.setAttribute(key,value);
		session.setMaxInactiveInterval(3600);//session有效时间3600秒
//		System.out.println(session.getMaxInactiveInterval());
//		System.out.println(session.getAttribute(key));
	}
	
	/**
	 * 获得session中username
	 */
	public static String getValueFromSesson(HttpServletRequest request,String key){
		String value = "";
		if(StringUtils.isBlank(key)){
			logger.info("获取用户名出错，参数为空。");
			return value;
		}
		HttpSession session = request.getSession();
		value = (String)session.getAttribute(key);
//		System.out.println(value);
		if(StringUtils.isBlank(value)){
			logger.info("找不到键值为 [{}] 的用户名。", key);
		}
//		value="admin";
		return value;
	}

	
	/**
	 * 删除session
	 */
	public static void delValueFromSesson(HttpServletRequest request,String key){
		if(StringUtils.isBlank(key)){
			logger.info("获取用户名出错，参数为空。");
		}
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(0);
//		session.removeAttribute(key);
	}

}
