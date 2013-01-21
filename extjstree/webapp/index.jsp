<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>树测试</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="extjs/css/ext-all.css">
	<script type="text/javascript" src="extjs/ext-all.js"></script>
	<script type="text/javascript" src="extjs/test.js"></script>
  </head>
  <body>
  <h3>ExtJs树结构测试页面</h3>
   <div id="tree-div"></div>
  </body>
</html>
