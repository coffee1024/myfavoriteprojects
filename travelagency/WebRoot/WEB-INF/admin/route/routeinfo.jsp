<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	String path = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>top</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</head>
<table>
<tr>
<td>名称</td>
<td><s:property value="route.name"/> </td>
</tr>
<tr>
<td>天数</td>
<td><s:property value="route.days"/></td>
</tr>
<tr>
<td>开始时间</td>
<td><s:date name="route.beginTime" format="yyyy-MM-dd"/></td>
</tr>
<tr>
<td>结束时间</td>
<td><s:date name="route.endTime" format="yyyy-MM-dd"/></td>
</tr>
<tr>
<td>关键词</td>
<td><s:property value="route.keywords"/></td>
</tr>
<tr>
<td>详细介绍</td>
<td><s:property value="route.description"/></td>
</tr>
</table>
</body>
</html>