<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title /></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/styles/admin.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.easing.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.dimensions.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.accordion.js"></script>
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
<sitemesh:head />
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/layouts/admintop.jsp"%>
		<div id="content">
			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td width="8" bgcolor="#353c44">&nbsp;</td>
					<td width="147" valign="top">
						<div style="height: 100%;">
							<ul id="navigation">
								<s:if test="#session.loginUser.adminGroup.level<=1 ">
									<li><a class="head">系统管理</a>
										<ul>
											<li><a href="getalluser" target="rightFrame">员工信息</a></li>
											<li><a href="toadduser" target="rightFrame">添加员工</a></li>
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
											<li><a href="${ctx}/admin/toaddagency"
												target="rightFrame">添加旅行社</a></li>
											<li><a href="getallroute" target="rightFrame">线路信息</a></li>
											<li><a href="${ctx}/admin/toaddroute"
												target="rightFrame">添加线路</a></li>
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
											<li><a
												onclick="javascript:return window.confirm('进行数据库备份吗')"
												href="tobackup" target="rightFrame">数据库备份</a></li>
											<li><a href="toload" target="rightFrame">数据库恢复</a></li>
										</ul></li>
								</s:if>
								<li><a class="head">版本信息</a>
									<ul>
										<li><a href="http://www.baidu.com" target="_blank">by
												刘光强 @ v1.0 </a></li>
									</ul></li>
							</ul>
						</div>
					</td>
					<td width="10" bgcolor="#add2da">&nbsp;</td>
					<td valign="top"><sitemesh:body /></td>
					<td width="8" bgcolor="#353c44">&nbsp;</td>
				</tr>
			</table>
		</div>
		<%@ include file="/WEB-INF/layouts/adminbottom.jsp"%>
	</div>
</body>
</html>