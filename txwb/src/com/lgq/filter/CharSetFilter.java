package com.lgq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 拦截器：处理字符编码的问题
 * @author Jessica
 *
 */
public class CharSetFilter implements Filter {

	public void destroy() {
		System.out.println("charsetfilter destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//设置请求和相应的字符集为utf-8
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("charsetfilter init");
	}

}
