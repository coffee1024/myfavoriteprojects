package com.lgq.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lgq.dao.WeiboDao;

/**
 *@author  liuguangqiang
 *@date    2012-11-14 上午11:38:47
 *@version 1.0
 **/
public class AddWeibo extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		String string=request.getParameter("weibo");
		System.out.println(string);
		if (null!=string) {
			WeiboDao weiboDao=new WeiboDao();
			weiboDao.add(string);
			response.sendRedirect("addsuccess.jsp");
		}else {
			response.sendRedirect("adderror.jsp");
		}
	}

}
