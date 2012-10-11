<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="com.travel.utils.DBUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	String path = request.getContextPath();
%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<body>
	<%	
		Logger logger=LoggerFactory.getLogger(this.getClass());
		long a=System.currentTimeMillis();
		out.write("数据库备份开始<br/>");
		String str= DBUtils.backup();
		logger.debug("进行数据库备份");
		long b=System.currentTimeMillis();
		out.write("数据库备份成功---耗时"+(b-a)+"毫秒<br/>");
		out.write("备份文件为"+str);
	 %>
</body>
</html>
