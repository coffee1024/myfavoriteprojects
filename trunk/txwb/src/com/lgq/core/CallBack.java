package com.lgq.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lgq.common.Util;

/**
 *@author  admin
 *@date    2012-7-6 下午9:50:57
 *@version 1.0
 **/
public class CallBack extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到sessionKey
				String topSession = request.getParameter("top_session");
				//得到nick
				String topParameters = request.getParameter("top_parameters");
				String nick = Util.ParametersName(topParameters);
				//把sessionKey和nick写到session中，并设置session的有效期为10分钟
				request.getSession().setAttribute("sessionKey", topSession);
				request.getSession().setAttribute("nick", nick);
				request.getSession().setMaxInactiveInterval(600);
				response.setContentType("text/html");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<HTML>");
				out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
				out.println("  <BODY>");
				out.println("欢迎使用数据批量处理工具");
				out.println("授权参数获取成功，请选择相应的功能");
				out.println("  </BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();
	}

}
