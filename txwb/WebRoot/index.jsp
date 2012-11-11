<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>欢迎来到刘光强个人空间</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="首页">
  </head>
  
  <body>
  <center>
    <h1>欢迎使用疼讯围脖定时发送系统</h1><br>
    <h3>请选择功能</h3><br>
<!--     <a href="http://652874002.x6.fjjsp01.com/core/renzheng" target="_blank">微博自动发送系统</a><br> -->
     <a href="http://127.0.0.1:8081/txwb/core/renzheng" target="_blank">腾讯微博自动发送系统</a><br>
   <a href="http://127.0.0.1:8081/txwb/core/xlrenzheng" target="_blank">新浪微博自动发送系统</a><br>
    <font>开发人员:漠然回首</font><br>
    <a href="http://lovechina1314.tao.com" target="_blank">他的淘宝店</a><br>
    <a href="http://t.qq.com/lgq652874007" target="_blank">他的疼讯围脖</a><br>
    <a href="http://weibo.com/u/2640659693" target="_blank">他的新浪围脖</a><br>
    <a href="tencent://message/?uin=652874007&Site=652874001.x9.fjjsp01.com&Menu=yes">和他QQ聊天</a>
   </center>
  </body>
</html>
