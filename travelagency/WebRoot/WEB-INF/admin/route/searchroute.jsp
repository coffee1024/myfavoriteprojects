<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="false" isELIgnored="false"%>
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
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/search.js"></script>
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
                <td width="94%" valign="bottom"><span class="STYLE1">线路检索页面</span></td>
              </tr>
            </table></td>
            <td>
            	<div align="right"><span class="STYLE1">
              <img src="<%=path%>/images/add.gif" width="10" height="10" />&nbsp;&nbsp;&nbsp; </span> </div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tr>
    	<form action="<%=path %>/searchroute.action" method="post">
        <td colspan="6" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">
        <font>请输入检索词:</font>&nbsp;<input autocomplete="off" id="search" type="text" name="searchword" value="<s:property value='searchword'/>">
        <input type="submit" value="搜索">
        </span></div></td>
      	</form>
      </tr>
      <s:if test="searchsuggest.size!=0">
			<tr>
			<td colspan="6" height="20" bgcolor="d3eaef" class="STYLE6">
						<div>
						您要查找的是不是：
						<s:iterator value="searchsuggest" var="suggest"> 
							<a href="#"><s:property value="#suggest" /></a>
						</s:iterator>
						</div>
			</td>
			</tr>
		</s:if>
      <tr>
        <td height="20" width="5%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE19">编号</span></div></td>
        <td height="20" width="10%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE19">名称</span></div></td>
        <td height="20" width="10%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE19">天数</span></div></td>
        <td height="20" width="10%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE19">关键词</span></div></td>
        <td height="20" width="25%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE19">介绍</span></div></td>
        <td height="20" width="25%" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE19">操作</span></div></td>
      </tr>
      <s:iterator value="results.results" var="searchfield">
      <tr>
      	<s:iterator value="#searchfield.list" var="field">
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${field}</span></div></td>
        </s:iterator>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><a href="<%=path%>/admin/torouteinfo?rid=<s:property value='#searchfield.id'/>">查看详情</a></span></div></td>
      </tr>
      </s:iterator>
    </table></td>
  </tr>
  <s:if test="results!=null">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> <s:property value="results.recoredCount"/></strong> 条记录，当前第<strong> <s:property value="results.pageNo"/></strong> 页，共 <strong><s:property value="results.totalPage"/></strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
          	<s:if test="pageNo==1"> 
           <td width="49"><div align="center"><img src="<%=path%>/images/main_54_b.gif" width="40" height="15" /></div></td>
            <td width="49"><div align="center"><img src="<%=path%>/images/main_56_b.gif" width="45" height="15" /></div></td>
         	</s:if>
          	<s:else>          	
            <td width="49"><div align="center">
            <form action="<%=path %>/searchroute.action" method="post">
            <input type="hidden" name="searchword" value="<s:property value='searchword'/>">
            <input type="hidden" name="pageNo" value="1">
            <input type="image" src="<%=path%>/images/main_54.gif" width="40" height="15"/>
            </form>
            </div></td>
            <td width="49"><div align="center">
            <form action="<%=path %>/searchroute.action" method="post">
            <input type="hidden" name="searchword" value="<s:property value='searchword'/>">
            <input type="hidden" name="pageNo" value="<s:property value='results.pageNo-1'/>">
            <input type="image" src="<%=path%>/images/main_56.gif" width="40" height="15"/>
            </form>
            </div></td>
            </s:else>
            <s:if test="pageNo==results.totalPage"> 
          	<td width="49"><div align="center"><img src="<%=path%>/images/main_58_b.gif" width="45" height="15" /></div></td>
            <td width="49"><div align="center"><img src="<%=path%>/images/main_60_b.gif" width="40" height="15" /></div></td>
            </s:if>
          	<s:else>
             <td width="49"><div align="center">
            <form action="<%=path %>/searchroute.action" method="post">
            <input type="hidden" name="searchword" value="<s:property value='searchword'/>">
            <input type="hidden" name="pageNo" value="<s:property value='results.pageNo+1'/>">
            <input type="image" src="<%=path%>/images/main_58.gif" width="40" height="15"/>
            </form>
            </div></td>
            <td width="49"><div align="center">
            <form action="<%=path %>/searchroute.action" method="post">
            <input type="hidden" name="searchword" value="<s:property value='searchword'/>">
            <input type="hidden" name="pageNo" value="<s:property value='results.totalPage'/>">
            <input type="image" src="<%=path%>/images/main_60.gif" width="40" height="15"/>
            </form>
            </div></td>
            </s:else> 
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <form action="<%=path %>/searchroute.action" method="post">
            <input type="hidden" name="searchword" value="<s:property value='searchword'/>">
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
