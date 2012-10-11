<%@page import="com.travel.dao.DaoFactory"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
%>
<html>
<head>
</head>
<body>
</body>
<center>
<h1>欢迎来到齐风旅行社信息管理系统</h1>
<span><a href="<%=path %>/tologin">管理员登陆</a></span><br>
<span><a href="<%=path %>/tologin">普通用户登陆</a></span>
</center>
</html>
