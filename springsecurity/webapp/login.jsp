<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login</title>
</head>
<body>
<c:if test="${not empty param.error}">
	<font color="red">Login error.<br />
	</font>
	Reason:${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</c:if>
<form method="POST" action="<c:url value="/login"/>">
<table>
	<tr>
		<td align="right">Username</td>
		<td><input type="text" name="j_username" /></td>
	</tr>
	<tr>
		<td align="right">Password</td>
		<td><input type="password" name="j_password" /></td>
	</tr>
	<tr>
		<td colspan="2" align="right"><input type="submit" value="Login" />
		<input type="reset" value="Reset" /></td>
	</tr>
</table>
</form>
</body>
</html>