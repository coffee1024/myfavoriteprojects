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
<td><s:property value="agency.name"/> </td>
</tr>
<tr>
<td>地址</td>
<td><s:property value="agency.address"/></td>
</tr>
<tr>
<td>成立时间</td>
<td><s:date name="agency.createTime" format="yyyy-MM-dd"/></td>
</tr>
<tr>
<td>详细介绍</td>
<td><s:property value="agency.description"/></td>
</tr>
</table>
</body>
</html>