<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>登录页</title>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</head>

<body>
	<form id="loginForm" action="${ctx}/login.jsp" method="post" class="form-horizontal">
	<%
	 Object obj = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
           boolean flag = false;
           String msg = "";                        
           if( obj != null ){
                if( "org.apache.shiro.authc.UnknownAccountException".equals( obj ) )
                      msg = "登录失败";
                else if("org.apache.shiro.authc.IncorrectCredentialsException".equals( obj ))
                      msg = "登录失败";
                else if("org.springside.examples.quickstart.service.account.IncorrectCaptchaException".equals( obj ))
                      msg = "验证码错误";
                else if( "org.apache.shiro.authc.AuthenticationException".equals( obj ))
                      msg = "登录失败，请检查用户名密码";
                 flag = !"".equals(msg);
           }
	if(flag){
	%>
		<div class="alert alert-error input-medium controls">
			<button class="close" data-dismiss="alert">×</button><%=msg %>.
		</div>
	<%
	}
	%>
		<div class="control-group">
			<label for="username" class="control-label">名称:</label>
			<div class="controls">
				<input type="text" id="username" name="username"  value="${username}" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我</label>
				<input id="submit_btn" class="btn btn-primary" type="submit" value="登录"/> <a class="btn" href="${ctx}/register">注册</a>
			 	<span class="help-block">(管理员: <b>admin/admin</b>, 普通用户: <b>user/user</b>)</span>
			</div>
		</div>
	</form>
</body>
</html>
