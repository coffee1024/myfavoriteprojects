<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML>
<HEAD><TITLE>后台管理系统</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="<%=path%>/css/login.css" type=text/css rel=stylesheet>
</HEAD>
<frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
	<frame src="totop.action" name="topFrame" scrolling="No"
		noresize="noresize" id="topFrame" />
	<frame src="tocenter.action" name="mainFrame" id="mainFrame" />
	<frame src="todown.action" name="bottomFrame" scrolling="No"
		noresize="noresize" id="bottomFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>