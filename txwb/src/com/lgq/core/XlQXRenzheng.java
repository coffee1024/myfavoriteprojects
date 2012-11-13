package com.lgq.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@author  liuguangqiang
 *@date    2012-11-13 上午11:47:18
 *@version 1.0
 **/
public class XlQXRenzheng extends HttpServlet {

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

		String uid=request.getParameter("uid");
		((Timer)request.getSession().getAttribute(uid+"_timer")).cancel();
		System.out.println("取消新浪认证");
	}

}
