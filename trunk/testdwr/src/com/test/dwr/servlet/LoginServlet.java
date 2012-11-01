package com.test.dwr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@author  liuguangqiang
 *@date    2012-11-1 下午3:40:54
 *@version 1.0
 **/
public class LoginServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userName=request.getParameter("username");
		request.getSession().setAttribute("user", userName);
		System.out.println(userName);
		response.sendRedirect(request.getContextPath()+"/sendMsg.jsp");
	}

}
