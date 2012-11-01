<%@ page language="java" pageEncoding="UTF-8"%>      
<%@ page isELIgnored="false" %>  
<html>      
  <head>      
    <title>登录</title>      
    <meta http-equiv="pragma" content="no-cache">      
    <meta http-equiv="cache-control" content="no-cache">      
       
  </head>      
  <body onload="dwr.engine.setActiveReverseAjax(true);">       
 
    <form action="servlet/login" method="post">
    	请输入您的名字：<input type="text" name="username">
    	<input type="submit" value="登录">
    </form>    
  </body>     
    
</html>   