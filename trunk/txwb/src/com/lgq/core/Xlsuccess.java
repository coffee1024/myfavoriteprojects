package com.lgq.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo4j.Account;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

import com.tencent.weibo.oauthv1.OAuthV1;

/**
 *@author  liuguangqiang
 *@date    2012-8-1 下午11:49:30
 *@version 1.0
 **/
public class Xlsuccess extends HttpServlet {

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
		String uid=request.getSession().getAttribute("uid").toString();
		AccessToken at=(AccessToken)request.getSession().getAttribute(uid+"_at");
	Timer timer =new Timer();
	System.out.println("xlsuccess");
	try {
		timer.schedule(new XlFabiao(at), new Date(System.currentTimeMillis()), 1800000)	;
		request.getSession().setAttribute(uid+"_timer", timer);
		timer.cancel();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.getServletContext().getRequestDispatcher("/success.jsp")
	   .forward(request, response);
	}
}