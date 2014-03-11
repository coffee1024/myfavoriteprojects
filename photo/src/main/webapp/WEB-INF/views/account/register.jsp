<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户注册</title>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#loginName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate({
				rules: {
					loginName: {
						remote: "${ctx}/register/checkLoginName"
					}
				},
				messages: {
					loginName: {
						remote: "用户登录名已存在"
					}
				}
			});
		});
	</script>
</head>

<body>
	<form id="inputForm" action="${ctx}/register" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>用户注册</small></legend>
			<div class="container">
			<div class="portlet box green">
				<div class="portlet-body form">
					<!-- BEGIN FORM-->
						<div class="form-body">
							<div class="form-group">
								<label  class="col-md-3 control-label">用户名</label>
								<div class="col-md-4">
									<input type="text" id="loginName" name="loginName" class="form-control" placeholder="请输入用户名">
								</div>
							</div>
							<div class="form-group">
								<label  class="col-md-3 control-label">密码</label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-user"></i></span>
										<input type="password" id="password" name="plainPassword" class="form-control" placeholder="请输入密码">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label  class="col-md-3 control-label">昵称</label>
								<div class="col-md-4">
									<input type="text" id="nickName" name="nickName" class="form-control" placeholder="请输入昵称">
								</div>
							</div>
							<div class="form-group">
								<label  class="col-md-3 control-label">电子邮箱</label>
								<div class="col-md-4">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
										<input type="email" id="email" name="email" class="form-control" placeholder="请输入电子邮箱">
									</div>
								</div>
							</div>
							<div class="form-group last">
								<label  class="col-md-3 control-label">手机号</label>
								<div class="col-md-4">
									<div class="input-icon">
										<i class="fa fa-bell-o"></i>
										<input type="text" id="mobile" name="mobile" class="form-control" placeholder="请输入手机号">
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn blue btn-primary">Submit</button>
								<button type="button" class="btn default btn-primary">Cancel</button>                              
							</div>
						</div>
					<!-- END FORM--> 
				</div>
			</div>
		 </div>
		</fieldset>
	</form>
</body>
</html>
