<%@page import="java.io.File"%>
<%@page import="com.travel.utils.ConfigUtil"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="com.travel.utils.DBUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	String path = request.getContextPath();
%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<body>
<h2>请选择一个备份文件</h2>
	<%	
		String dir=ConfigUtil.get("dbbackdir");
		File file=new File(dir);
		String[] strs=null;
		if(file.isDirectory()){
			strs=file.list();
		}
	 %>
	<form action="loaddb" method="post">
	<%
		for(int i=0;i<strs.length;i++){
		%>
	 	<input type="radio" name="filename" value="<%=strs[i]%>"><%=strs[i]%><br>
		<%}%>
	<input type="submit" value="提交">
	</form>
</body>
</html>
