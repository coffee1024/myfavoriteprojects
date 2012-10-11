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
<script type="text/javascript" src="<%=path%>/js/calendar.js"></script>
</head>
<form action="<%=path %>/admin/addagency.action" name="agency" method="post">
<table>
<tr>
<td>名称</td>
<td><input type="text" name="agency.name" /></td>
</tr>
<tr>
<td>成立时间</td>
<td><input type="text"  name="createTime"/><img  onclick="new Calendar().show(agency.createTime);return false;" src="<%=path %>/images/calendar.png" width=16 height=16 border=0></td>
</tr>
<tr>
<td>地址</td>
<td><input  type="text" name="agency.address" /></td>
</tr>
<tr>
<td>详细介绍</td>
<td><textarea  rows="3" cols="15" name="agency.description"></textarea></td>
</tr>
<tr>
<td colspan="2" align="right"><input type="submit" value="提交" ></input> </td>
</tr>
</table>
</form>
</body>
</html>