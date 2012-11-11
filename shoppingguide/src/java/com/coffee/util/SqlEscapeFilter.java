package com.coffee.util;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class SqlEscapeFilter implements Filter{
	 public SqlEscapeFilter() {
	        // TODO Auto-generated constructor stub
	    }
	 private boolean threadContextInheritable = false;

	  public void setThreadContextInheritable(boolean threadContextInheritable)
	  {
	    this.threadContextInheritable = threadContextInheritable;
	  }
	  public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException {
//		 	System.out.println("过滤特殊字符开始");
			chain.doFilter(new SqlEscapeWrapper((HttpServletRequest)request), response);
//			System.out.println("过滤特殊字符结束");
	 }
	 
	    /**
	     * @see Filter#destroy()
	     */
	    public void destroy() {
	        // TODO Auto-generated method stub
	    }


		public void init(FilterConfig arg0) throws ServletException {
			System.out.println("初始化数据库过滤器");
		}
	
}
