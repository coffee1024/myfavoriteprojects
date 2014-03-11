<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>登录页</title>
	<link href="${ctx}/static/styles/signin.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<div class="container">
	<form  class="form-signin" id="loginForm" action="${ctx}/login" method="post">
	<%
	String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	if(error != null){
	%>
		<div class="alert alert-error input-medium controls">
			<button class="close" data-dismiss="alert">×</button>登录失败，请重试.
		</div>
	<%
	}
	%>
        <h2 class="form-signin-heading">请登录</h2>
        <input type="text" class="form-control" placeholder="用户名" name="username" required autofocus>
        <input type="password" class="form-control" placeholder="密码"  name="password" required>
        <label class="checkbox" for="rememberMe">
        	<input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button> <a class="btn btn-lg btn-primary btn-block" href="${ctx}/register">注册</a>
      </form>
	</div>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</body>
</html>
