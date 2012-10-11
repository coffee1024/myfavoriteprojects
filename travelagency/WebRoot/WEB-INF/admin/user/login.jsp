<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	String path = request.getContextPath();
%>
<HTML>
<HEAD><TITLE>后台管理登录</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>BODY {
	MARGIN: 0px
}
</STYLE>
<LINK href="<%=path%>/css/login.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=path %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.validate.min.js"></script>
<script type="text/javascript">
function refreshVerifyCode(){
	$("#verifyCodeId").attr("src","<%=path %>/rand?a="+new Date().getMilliseconds());
}

function codeok(){
	var code=$("#verifycode").val();
	if(code==""){
		alert("请输入验证码");
		return false;
	}
	$.ajax({type: "post",
				url: "<%=path%>/admin/checkcode.action", 
				data: ({code: code}),
				dataType: "json",
				success: function(data){
				var f=data.codeok;
				   if(f==true){
				   	$("#logon").submit();
				  }else{
				  	alert("请输入正确的验证码");
				  }
			  }
		});

}
function nameok(){
	var name=$("#username").val();
	if(name==""){
		alert("请输入用户名");
		return false;
	}
	return true;
}
function passwordok(){
	var password=$("#password").val();
	if(password==""){
		alert("请输入密码");
		return false;
	}
	return true;
}

function checkall(){
	if (nameok() && passwordok()) {
		 codeok();
	}
}
</script>
</HEAD>
<body >
<form id="logon" name="logon" action="<%=path%>/admin/login.action" method='post'>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="518" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="100%" background="<%=path%>/images/loginmpbg.jpg" style="border-left: 1px solid #3571C7;border-top: 1px solid #3571C7;border-right: 1px solid #3571C7;">
		<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0" style="border-left: 1px solid #608FD3;border-top: 1px solid #608FD3;border-right: 1px solid #174B94;">
          <tr>
            <td width="247">&nbsp;</td>
            <td height="108">&nbsp;</td>
          </tr>
          <tr>
            <td height="152">&nbsp;</td>
            <td width="267">
			  <table width="257"  border="0" cellspacing="5" cellpadding="0">
			  <tr>
	                <td width="56" class="font_white">用户名</td>			  
			  		<td width="186"><input id="username" name="username" style="width:180"></td>
              </tr>
              <tr>
                <td class="font_white">密　码：</td>
                <td><input type=password maxlength=200 id="password" name="password" style="width:180"></td>
              </tr>
	             <tr>
	                <td class="font_white" valign="middle">验证码：</td>
	                <td><input id="verifycode" name="verifycode" style="width:120" maxlength=20  >&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:refreshVerifyCode();" style="cursor:pointer;" ><span class="font_white" ><u>看不清?</u></span></a>
	                </td>
	              </tr>
                 <tr>
                 <td></td>
                    <td class="font_white" valign="bottom" align="left">
                        <div> <img id="verifyCodeId" src="<%=path %>/rand"/></div>
                    </td>
                  </tr>
              <tr>
                <td>&nbsp;</td>
                <td align="right">
                <img src="<%=path %>/images/denglu.jpg"  onclick="checkall()"/></td>
              </tr>
              </table></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</FORM>
</body>
</html>