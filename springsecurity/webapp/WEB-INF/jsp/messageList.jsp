<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Message List</title>
</head>
<body>
<h2>Welcome! <security:authentication property="name" /></h2>
<security:authentication property="authorities" var="authorities" />
<ul>
	<c:forEach items="${authorities}" var="authority">
		<li>${authority.authority}</li>
	</c:forEach>
</ul>
<hr />
<c:forEach items="${messages}" var="message">
	<table>
		<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
			<tr>
				<td>Author</td>
				<td>${message.author}</td>
			</tr>
		</security:authorize>
		<tr>
			<td>Title</td>
			<td>${message.title}</td>
		</tr>
		<tr>
			<td>Body</td>
			<td>${message.body}</td>
		</tr>
		<tr>
			<td colspan="2"><a
				href="messageDelete.htm?messageId=${message.id}">Delete</a></td>
		</tr>
	</table>
	<hr />
</c:forEach>
<a href="messagePost.htm">Post</a>
<a href="<c:url value="/j_spring_security_logout" />">Logout</a>
</body>
</html>