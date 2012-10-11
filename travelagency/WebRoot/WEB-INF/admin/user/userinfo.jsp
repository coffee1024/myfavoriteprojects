<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	String path = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>用户信息</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</head>
<body>
<table>
<tr>
<td>用户名</td>
<td><s:property value="user.userName"/></td>
</tr>
<tr>
<td>电话</td>
<td><s:property value="user.phone"/></td>
</tr>
<tr>
<td>上次登录时间</td>
<td><s:date name="user.lastTime" format="yyyy-MM-dd HH:mm:ss"/></td>
</tr>
<tr>
<td>上次登录IP</td>
<td><s:property value="user.lastIP"/></td>
</tr>
<tr>
<td>用户组</td>
<td><s:property value="user.adminGroup.groupName"/></td>
</tr>
<tr>
<td>详细描述</td>
<td><s:property value="user.description"/></td>
</tr>
</table>
</body>
</html>
