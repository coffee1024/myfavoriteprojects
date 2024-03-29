<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<HTML>
<HEAD>
<TITLE>top</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="${ctx}/css/login.css" type=text/css rel=stylesheet>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
-->
</style>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${ctx}/static/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 用户列表</span></td>
              </tr>
            </table></td>
            <td>
            	<div align="right"><span class="STYLE1">
              <img src="${ctx}/static/images/add.gif" width="10" height="10" /><a href="${ctx}/admin/user/add"> 添加</a>&nbsp;&nbsp;&nbsp; </span> </div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ID</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">登录名</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">昵称</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">手机</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">邮箱</span></div></td>
        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">注册时间</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <c:forEach items="${page.content}" var="user">
      <tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${user.id}</span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><a href="${ctx}/admin/user/update/${user.id}">${user.loginName}</a></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${user.nickName}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${user.mobile}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${user.email}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" /></div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="${ctx}/admin/user/delete/${user.id}">删除</a> | <a href="${ctx}/admin/touserinfo?uid=">查看</a></div></td>
      </tr>
      </c:forEach>
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> <s:property value="count"/></strong> 条记录，当前第<strong> <s:property value="pageNo"/></strong> 页，共 <strong><s:property value="totalpage"/></strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
          	<c:if test="${page.firstPage }"> 
           <td width="49"><div align="center"><img src="${ctx}/static/images/main_54_b.gif" width="40" height="15" /></div></td>
            <td width="49"><div align="center"><img src="${ctx}/static/images/main_56_b.gif" width="45" height="15" /></div></td>
         	</c:if>
          	<c:if test="${!page.firstPage }">         	
            <td width="49"><div align="center"><a href="${ctx}/admin/user?pageNo=1"><img src="${ctx}/static/images/main_54.gif" width="40" height="15" /></a></div></td>
            <td width="49"><div align="center"><a href="${ctx}/admin/user?pageNo=${page.number }"><img src="${ctx}/static/images/main_56.gif" width="45" height="15" /></a></div></td>
            </c:if>
            <c:if test="${page.lastPage }"> 
          	<td width="49"><div align="center"><img src="${ctx}/static/images/main_58_b.gif" width="45" height="15" /></div></td>
            <td width="49"><div align="center"><img src="${ctx}/static/images/main_60_b.gif" width="40" height="15" /></div></td>
            </c:if>
          	<c:if test="${!page.lastPage }"> 
            <td width="49"><div align="center"><a href="${ctx}/admin/user?pageNo=${page.number+2 }"><img src="${ctx}/static/images/main_58.gif" width="45" height="15" /></a></div></td>
            <td width="49"><div align="center"><a href="${ctx}/admin/user?pageNo=${page.totalPages }"><img src="${ctx}/static/images/main_60.gif" width="40" height="15" /></a></div></td>
            </c:if>
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <form action="${ctx}/admin/user" method="post">
            <td width="22"><div align="center">
              <input type="text" name="pageNo" id="textfield"  style="width:20px; height:15px; font-size:12px; border:solid 1px #7aaebd;"/>
            
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
            <td width="35"><input type="image" src="${ctx}/static/images/main_62.gif" width="26" height="15" /></td>
            </form>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
