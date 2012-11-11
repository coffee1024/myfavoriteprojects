package com.lgq.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

/**
 *@author  liuguangqiang
 *@date    2012-8-1 下午11:37:44
 *@version 1.0
 **/
public class Xlrenzheng extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code=request.getParameter("code");
		Oauth oauth = new Oauth();
		if (StringUtils.isEmpty(code)) {
			String url="";
			try {
				url=oauth.authorize("code");
			} catch (WeiboException e) {
				System.out.println("获取url code失败");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(url);
		}else {
			AccessToken at;
			try {
				at=oauth.getAccessTokenByCode(code);
				request.getSession().setAttribute("xlat", at);
				response.sendRedirect("xlsuccess");
			} catch (WeiboException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
