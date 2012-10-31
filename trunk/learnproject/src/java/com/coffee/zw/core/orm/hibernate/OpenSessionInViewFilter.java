package com.coffee.zw.core.orm.hibernate;
/**
 * @Title 
 * @Author ZhouQiang
 * @CrTime 2010-4-9 上午11:17:10
 * @Version 1.0
 */
/** 
 * Copyright (c) 2005-2009 springside.org.cn 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *  
 * $Id: OpenSessionInViewFilter.java 506 2009-10-01 06:23:55Z calvinxiu $ 
 */ 
 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest; 
 
import org.apache.commons.lang.StringUtils; 
 
/** 
 * 缩窄OpenSessionInViewFilter的过滤范围,请求css、js、图片等静态内容时不创建/关闭Hibernate Session连接. 
 *  
 * 在web.xml中可配置excludeSuffixs参数,多个过滤文件后缀名以','分割. 
 * <filter>
        <filter-name>hibernateOpenSessionInViewFilter</filter-name>
        <filter-class>com.trs.photo.core.orm.hibernate.OpenSessionInViewFilter</filter-class>
        <init-param>
            <param-name>excludeSuffixs</param-name>
            <param-value>js,css,jpg,gif</param-value>
        </init-param>
        <init-param>      
               <param-name>sessionFactoryBeanName</param-name>
            <param-value>sessionFactoryIndex</param-value>   
        </init-param>    
    </filter>
 *  
 * @author calvin 
 */ 
public class OpenSessionInViewFilter extends org.springframework.orm.hibernate3.support.OpenSessionInViewFilter { 
 
        public static final String EXCLUDE_SUFFIXS_NAME = "excludeSuffixs"; 
 
        public static final String INCLUDE_SUFFIXS_NAME = "includeSuffixs"; 
 
        private static final String[] DEFAULT_EXCLUDE_SUFFIXS = { ".js", ".css", ".jpg", ".gif" }; 
 
        private static final String[] DEFAULT_INCLUDE_SUFFIXS = { ".faces" }; 
 
        private String[] excludeSuffixs = DEFAULT_EXCLUDE_SUFFIXS; 
 
        private String[] includeSuffixs = DEFAULT_INCLUDE_SUFFIXS; 
 
        /** 
         * 重载过滤控制函数,忽略特定后缀名的请求. 
         */ 
        @Override 
        protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException { 
                String fullPath = request.getServletPath(); 
                String path = StringUtils.substringBefore(fullPath, "?"); 
 
                for (String suffix : includeSuffixs) { 
                        if (path.endsWith(suffix)) 
                                return false; 
                } 
 
                for (String suffix : excludeSuffixs) { 
                        if (path.endsWith(suffix)) 
                                return true; 
                } 
 
                return false; 
        } 
 
        /** 
         * 初始化excludeSuffixs参数. 
         */ 
        @Override 
        protected void initFilterBean() throws ServletException { 
 
                String includeSuffixStr = getFilterConfig().getInitParameter(INCLUDE_SUFFIXS_NAME); 
 
                if (StringUtils.isNotBlank(includeSuffixStr)) { 
                        includeSuffixs = includeSuffixStr.split(","); 
                        //为匹配字符串加上"."使匹配更精确，如.jpg 
                        for (int i = 0; i < includeSuffixs.length; i++) { 
                                includeSuffixs[i] = "." + includeSuffixs[i]; 
                        } 
                } 
 
                String excludeSuffixStr = getFilterConfig().getInitParameter(EXCLUDE_SUFFIXS_NAME); 
 
                if (StringUtils.isNotBlank(excludeSuffixStr)) { 
                        excludeSuffixs = excludeSuffixStr.split(","); 
                        for (int i = 0; i < excludeSuffixs.length; i++) { 
                                excludeSuffixs[i] = "." + excludeSuffixs[i]; 
                        } 
                } 
        } 
} 

