package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ch.qos.logback.classic.Logger;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
public class MyFilter implements Filter {

	public void destroy() {
		try{
			
		}catch(Exception e){
			
		}
		
	}

	public String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        if(str != null) {
            //用默认字符编码解码字符串。与系统相关，中文windows默认为GB2312
            byte[] bs = str.getBytes();
            return new String(bs, newCharset);    //用新的字符编码生成字符串
        }
        return null;
    }
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try{
    		HttpServletRequest hreq = (HttpServletRequest)request;
    		hreq.setCharacterEncoding("UTF-8");
    		HttpServletResponse hres=(HttpServletResponse)response;
    		String url=hreq.getRequestURI();
    		//hres.encodeURL(url);
	       Map map = hreq.getParameterMap();
	        Iterator itr = map.keySet().iterator();
	        while( itr.hasNext() )
	        {
	            String key = itr.next().toString();
//	            System.out.println("req key is ===================>"+key);
	            String [] values = hreq.getParameterValues(key);
	            if(values != null)
	            {
	                for( int i = 0; i < values.length; i++ )
	                {
//	                	System.out.println("values[i]1======================================>"+values[i]);
	                    values[i] = cleanXSS(values[i]);
//	                    System.out.println("values[i]2======================================>"+values[i]);
	                	hreq.setAttribute(key, values[i]);
	                }
	            }
	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		try{
    			
    			chain.doFilter(request, response);
    		}catch(Exception e1){
    			e1.printStackTrace();
    		}
    		
    	}
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	  private String cleanXSS(String value)
	    {
		  	//System.out.println("value 1=================> is:"+value);
//	        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
//	        alue = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
//	        value = value.replaceAll("'", "& #39;");
	        value = value.replaceAll("eval\\((.*)\\)", "");
	        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
	        //System.out.println("value 2========================> is:"+value);
	        return value;
	    }
	
	

}
