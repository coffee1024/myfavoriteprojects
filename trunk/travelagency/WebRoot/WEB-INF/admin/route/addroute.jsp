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
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/calendar.js"></script>
<script type="text/javascript">
	$(function(){
	$('#getketwords').click(function(){
	var context=$('#context').val();
		if(context==""){
			alert("请先输入详细描述");
			return;
		}
		$.ajax({type: "post",
				url: "cutkeywords.action", 
				data: ({context: context}),
				dataType: "json",
				success: function(data){
				var key=data.keywords;
				$('#keywords').val(key);
			  }
		});
	  });
	});
</script>
</head>
<form action="<%=path %>/admin/addroute.action" name="route" method="post">
<table>
<tr>
<td>名称</td>
<td colspan="2"><input type="text" name="route.name" /></td>
</tr>
<tr>
<td>开始时间</td>
<td><input type="text"  name="beginTime"/></td>
<td><img  onclick="new Calendar().show(route.beginTime);return false;" src="<%=path %>/images/calendar.png" width=16 height=16 border=0></td>
</tr>
<tr>
<td>结束时间</td>
<td><input type="text"  name="endTime"/></td>
<td><img  onclick="new Calendar().show(route.endTime);return false;" src="<%=path %>/images/calendar.png" width=16 height=16 border=0></td>
</tr>
<tr>
<td>关键词</td>
<td colspan="2"><input type="text" name="keywords" id="keywords"></td>

</tr>
<tr>
<td>详细介绍</td>
<td><textarea  rows="3" cols="15" name="route.description" id="context"></textarea></td>
<td><a id="getketwords" href="javascript:;">抽取关键词</a></td>
</tr>
<tr>
<td colspan="2" align="right""><input type="submit" value="提交" ></input> </td>
</tr>
</table>
</form>
</body>
</html>