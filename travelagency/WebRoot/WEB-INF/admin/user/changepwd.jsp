<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	String path = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>top</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</head>
<body>
<form action="<%=path %>/admin/changepwd.action" method="post">
<table>
<tr>
<td>旧密码</td>
<td><input type="password" name="oldpwd" /></td>
</tr>
<tr>
<td>新密码</td>
<td><input type="password" name="newpwd"/></td>
</tr>
<tr>
<td>确认密码</td>
<td><input  type="password" id="newpwdconfirm" /></td>
</tr>
<tr>
<td colspan="2" align="right""><input type="submit" value="提交" ></input> </td>
</tr>
</table>
</form>
</body>
</html>
