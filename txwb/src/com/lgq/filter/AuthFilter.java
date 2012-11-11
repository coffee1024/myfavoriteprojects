package com.lgq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
 * 拦截器：防止没有sessionKey的用户访问
 * @author Jessica
 *
 */
public class AuthFilter implements Filter {

	public void destroy() {
		System.out.println("authfilter destroy");
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession(false);
		//如果session中不存在sessionKey则断定为无授权访问，跳转到首页
		if(session == null || (String)session.getAttribute("sessionKey") == null) {
			response.sendRedirect( request.getContextPath() + "/index.jsp");
			return;
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("authfilter init");
	}

}
