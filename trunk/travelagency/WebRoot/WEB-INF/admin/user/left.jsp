<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
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
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easing.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.dimensions.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function() {
		jQuery('#navigation').accordion({
			header : '.head',
			navigation1 : true,
			event : 'click',
			fillSpace : true,
			animated : 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin: 0px;
	padding: 0px;
	font-size: 12px;
}

#navigation {
	margin: 0px;
	padding: 0px;
	width: 147px;
}

#navigation a.head {
	cursor: pointer;
	background: url(<%=path%>/images/main_34.gif) no-repeat scroll;
	display: block;
	font-weight: bold;
	margin: 0px;
	padding: 5px 0 5px;
	text-align: center;
	font-size: 12px;
	text-decoration: none;
}

#navigation ul {
	border-width: 0px;
	margin: 0px;
	padding: 0px;
	text-indent: 0px;
}

#navigation li {
	list-style: none;
	display: inline;
}

#navigation li li a {
	display: block;
	font-size: 12px;
	text-decoration: none;
	text-align: center;
	padding: 3px;
}

#navigation li li a:hover {
	background: url(<%=path%>/images/tab_bg.gif) repeat-x;
	border: solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
	<div style="height:100%;">
		<ul id="navigation">
		<s:if test="#session.loginUser.adminGroup.level<=1 ">
			<li><a class="head">系统管理</a>
				<ul>
					<li><a href="getalluser" target="rightFrame">员工信息</a>
					</li>
					<li><a href="toadduser" target="rightFrame">添加员工</a>
					</li>
				</ul></li>
		</s:if>
		<s:if test="#session.loginUser.adminGroup.level<=1 ">
			<li><a class="head">基础信息</a>
				<ul>
					<li><a href="getallcustomer" target="rightFrame">客户信息</a>
					</li>
					<li><a href="toaddcustomer" target="rightFrame">添加客户</a>
					</li>
					<li><a href="getallagency" target="rightFrame">旅行社信息</a>
					</li>
					<li><a href="<%=path %>/admin/toaddagency" target="rightFrame">添加旅行社</a>
					</li>
					<li><a href="getallroute" target="rightFrame">线路信息</a>
					</li>
					<li><a href="<%=path %>/admin/toaddroute" target="rightFrame">添加线路</a>
					</li>
				</ul></li>
				</s:if>
			<li><a class="head">信息查询</a>
				<ul>
					<li><a href="tosearchagency" target="rightFrame">旅行社信息查询</a>
					</li>
					<li><a href="tosearchroute" target="rightFrame">线路信息查询</a>
					</li>
<!-- 					<li><a href="tosearchuser" target="rightFrame">员工信息查询</a> -->
<!-- 					</li> -->
<!-- 					<li><a href="tosearchcustomer" target="rightFrame">客户信息查询</a> -->
<!-- 					</li> -->
				</ul></li>
		<s:if test="#session.loginUser.adminGroup.level<=0 ">
			<li><a class="head">系统安全管理</a>
				<ul>
					<li><a onclick="javascript:return window.confirm('进行数据库备份吗')"  href="tobackup" target="rightFrame">数据库备份</a>
					</li>
					<li><a href="toload" target="rightFrame">数据库恢复</a>
					</li>
				</ul></li>
		</s:if>
			<li><a class="head">版本信息</a>
				<ul>
					<li><a href="http://www.baidu.com" target="_blank">by 刘光强
							@ v1.0 </a>
					</li>
				</ul></li>
		</ul>
	</div>
</body>
</html>
