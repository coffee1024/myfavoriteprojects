<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="false"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #000000;
}

.STYLE5 {
	font-size: 12
}

.STYLE7 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE7 a {
	font-size: 12px;
	color: #FFFFFF;
}

a img {
	border: none;
}
-->
</style>
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="57" background="<%=path%>/images/main_03.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="378" height="57" background="<%=path%>/images/main_01.gif">&nbsp;</td>
						<td>&nbsp;</td>
						<td width="281" valign="bottom"><table width="100%"
								border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="33" height="27"><img src="<%=path%>/images/main_05.gif"
										width="33" height="27" />
									</td>
									<td width="248" background="<%=path%>/images/main_06.gif"><table
											width="225" border="0" align="center" cellpadding="0"
											cellspacing="0">
											<tr>
												<td height="17"><div align="right">
														<a href="tochangepwd" target="rightFrame"><img
															src="<%=path%>/images/pass.gif" width="69" height="17" />
														</a>
													</div>
												</td>
												<td><div align="right">
														<a href="touserinfo?uid=<s:property value='#session.loginUser.id'/>" target="rightFrame"><img
															src="<%=path%>/images/user.gif" width="69" height="17" />
														</a>
													</div>
												</td>
												<td><div align="right">
														<a href="logout" target="_parent"><img
															src="<%=path%>/images/quit.gif" alt=" " width="69" height="17" />
														</a>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="40" background="<%=path%>/images/main_10.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="194" height="40" background="<%=path%>/images/main_07.gif">&nbsp;</td>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="21"><img src="<%=path%>/images/main_13.gif" width="19"
										height="14" />
									</td>
									<td width="35" class="STYLE7"><div align="center">
											<a href="towelcome" target="rightFrame">首页</a>
										</div>
									</td>
									<td width="21" class="STYLE7"><img
										src="<%=path%>/images/main_15.gif" width="19" height="14" />
									</td>
									<td width="35" class="STYLE7"><div align="center">
											<a href="javascript:history.go(-1);">后退</a>
										</div>
									</td>
									<td width="21" class="STYLE7"><img
										src="<%=path%>/images/main_17.gif" width="19" height="14" />
									</td>
									<td width="35" class="STYLE7"><div align="center">
											<a href="javascript:history.go(1);">前进</a>
										</div>
									</td>
									<td width="21" class="STYLE7"><img
										src="<%=path%>/images/main_19.gif" width="19" height="14" />
									</td>
									<td width="35" class="STYLE7"><div align="center">
											<a href="javascript:window.parent.location.reload();">刷新</a>
										</div>
									</td>
									<td width="21" class="STYLE7"><img
										src="<%=path%>/images/main_21.gif" width="19" height="14" />
									</td>
									<td width="35" class="STYLE7"><div align="center">
											<a href="www.baidu.com" target="_parent">帮助</a>
										</div>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</td>
						<td width="248" background="<%=path%>/images/main_11.gif"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="16%"><span class="STYLE5"></span>
									</td>
									<td width="75%"><div align="center">
											<span class="STYLE7">By 刘光强 (<a
												href="http://www.baidu.com" target="_blank">www.baidu.com</a>)</span>
										</div>
									</td>
									<td width="9%">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="30" background="<%=path%>/images/main_31.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="8" height="30"><img src="<%=path%>/images/main_28.gif"
							width="8" height="30" />
						</td>
						<td width="147" background="<%=path%>/images/main_29.gif"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="24%">&nbsp;</td>
									<td width="43%" height="20" valign="bottom" class="STYLE1">管理菜单</td>
									<td width="33%">&nbsp;</td>
								</tr>
							</table>
						</td>
						<td width="39"><img src="<%=path%>/images/main_30.gif" width="39"
							height="30" />
						</td>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td height="20" valign="bottom"><span class="STYLE1">当前登录用户：<s:property value="#session.loginUser.userName" />
											&nbsp;用户角色：<s:property value="#session.loginUser.adminGroup.groupName" /></span>
									</td>
									<td valign="bottom" class="STYLE1"><div align="right"></div>
									</td>
								</tr>
							</table>
						</td>
						<td width="17"><img src="<%=path%>/images/main_32.gif" width="17"
							height="30" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>