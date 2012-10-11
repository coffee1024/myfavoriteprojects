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
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript">
var flaga=true;
var flagb=true;
function checkusername(){
		var username=$("#username").val();
		$.ajax({type: "post",
						url: "<%=path%>/admin/customerexist.action", 
						data: ({userName: username}),
						dataType: "json",
						async:false,
						success: function(data){
						var f=data.userExist;
						   if(f==true){
						   	$("#errormsg").text("用户名已存在");
						   	flaga= false;
						  }else{
						  	$("#errormsg").text(" ");
						  	flaga= true;
						  }
					  }
				});
}
function checkpwd(){
var pwd=$("#password").val();
var pwdconfirm=$("#pwdconfirm").val();
if(pwd==pwdconfirm){
	$("#pwderrormsg").text(" ");
	flagb= true;
	}else{
	$("#pwderrormsg").text("两次密码不一致");
	flagb=false;
	}
}
function checkall(){
if(flaga&&flagb){
return true;
}else{
return false;
}
}
</script>
</head>
<form onsubmit="return checkall()" action="<%=path %>/admin/addcustomer.action" method="post">
<table>
<tr>
<td>用户名</td>
<td><input type="text" id="username" name="user.userName" onblur="checkusername()"/><font color="red" id="errormsg"></font></td>
</tr>
<tr>
<td>密码</td>
<td><input type="password" id="password" name="user.password"/></td>
</tr>
<tr>
<td>确认密码</td>
<td><input  type="password" id="pwdconfirm" onblur="checkpwd()"/><font color="red" id="pwderrormsg"></font></td>
</tr>
<tr>
<td>电话</td>
<td><input  type="text" name="user.phone"/></td>
</tr>
<tr>
<td>用户组</td>
<td>
<select name="user.groupId">

<s:iterator value="customerGroup" var="group">
<option value="<s:property value='#group.id'/>"><s:property value='#group.groupName'/></option>
</s:iterator>
</select>
</td>
</tr>
<tr>
<td>详细描述</td>
<td><textarea  rows="3" cols="15" name="user.description"></textarea></td>
</tr>
<tr>
<td colspan="2" align="right""><input type="submit" value="提交" ></input> </td>
</tr>
</table>
</form>
</body>
</html>