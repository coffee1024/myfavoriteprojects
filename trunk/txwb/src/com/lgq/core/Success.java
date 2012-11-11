package com.lgq.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tencent.weibo.oauthv1.OAuthV1;

/**
 *@author  Administrator
 *@date    2012-6-22 下午11:58:33
 *@version 1.0
 **/
public class Success extends HttpServlet {

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
	OAuthV1 oauth=(OAuthV1)request.getSession().getAttribute("oauth");
	Timer timer =new Timer();
	System.out.println("success");
	timer.schedule(new Fabiao(oauth), new Date(System.currentTimeMillis()), 1800000)	;
	this.getServletContext().getRequestDispatcher("/success.jsp")
	   .forward(request, response);
	}
}
