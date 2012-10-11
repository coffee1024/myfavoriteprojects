<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	String path = request.getContextPath();
%>
<HTML>
<HEAD>
<TITLE>top</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="<%=path%>/css/login.css" type=text/css rel=stylesheet>
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
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="<%=path%>/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 线路基本信息列表</span></td>
              </tr>
            </table></td>
            <td>
            	<div align="right"><span class="STYLE1">
              <img src="<%=path%>/images/add.gif" width="10" height="10" /><a href="toaddroute" target="rightFrame"> 添加</a>&nbsp;&nbsp;&nbsp; </span> </div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">线路名</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">待定</span></div></td>
        <td width="14%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">天数</span></div></td>
        <td width="16%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">关键词</span></div></td>
        <td width="27%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">详细描述</span></div></td>
        <td width="14%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <s:iterator value="routes" var="route">
      <tr>
        <td width="5%" height="20"  bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><s:property value="#route.name"/></span></div></td>
        <td width="5%" height="20"  bgcolor="#FFFFFF" class="STYLE19"><div align="center">待定</div></td>
        <td width="5%" height="20"  bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#route.days"/></div></td>
       	<td width="5%" height="20"  bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#route.keywords"/></div></td>
        <td width="15%" height="20"  bgcolor="#FFFFFF" class="STYLE19"><div align="center"><s:property value="#route.description"/></div></td>
      	<td width="5%" height="20"  bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="<%=path%>/admin/deleteroute?rid=<s:property value='#route.id'/>&pageNo=<s:property value='pageNo'/>">删除</a> | <a href="<%=path%>/admin/torouteinfo?rid=<s:property value='#route.id'/>">查看</a></div></td>
      </tr>
      </s:iterator>
    </table></td>
  </tr>
   <s:if test="routes!=null">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> <s:property value="count"/></strong> 条记录，当前第<strong> <s:property value="pageNo"/></strong> 页，共 <strong><s:property value="totalpage"/></strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
          	<s:if test="pageNo==1"> 
           <td width="49"><div align="center"><img src="<%=path%>/images/main_54_b.gif" width="40" height="15" /></div></td>
            <td width="49"><div align="center"><img src="<%=path%>/images/main_56_b.gif" width="45" height="15" /></div></td>
         	</s:if>
          	<s:else>          	
            <td width="49"><div align="center"><a href="<%=path %>/admin/getallroute?pageNo=1"><img src="<%=path%>/images/main_54.gif" width="40" height="15" /></a></div></td>
            <td width="49"><div align="center"><a href="<%=path %>/admin/getallroute?pageNo=<s:property value='pageNo-1'/>"><img src="<%=path%>/images/main_56.gif" width="45" height="15" /></a></div></td>
            </s:else>
            <s:if test="pageNo==totalpage"> 
          	<td width="49"><div align="center"><img src="<%=path%>/images/main_58_b.gif" width="45" height="15" /></div></td>
            <td width="49"><div align="center"><img src="<%=path%>/images/main_60_b.gif" width="40" height="15" /></div></td>
            </s:if>
          	<s:else>
            <td width="49"><div align="center"><a href="<%=path %>/admin/getallroute?pageNo=<s:property value='pageNo+1'/>"><img src="<%=path%>/images/main_58.gif" width="45" height="15" /></a></div></td>
            <td width="49"><div align="center"><a href="<%=path %>/admin/getallroute?pageNo=<s:property value='totalpage'/>"><img src="<%=path%>/images/main_60.gif" width="40" height="15" /></a></div></td>
            </s:else> 
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <form action="<%=path %>/admin/getallroute" method="post">
            <td width="22"><div align="center">
              <input type="text" name="pageNo" id="textfield"  style="width:20px; height:15px; font-size:12px; border:solid 1px #7aaebd;"/>
            
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
            <td width="35"><input type="image" src="<%=path%>/images/main_62.gif" width="26" height="15" /></td>
            </form>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  </s:if>
</table>
</body>
</html>
