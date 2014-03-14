<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>上传页</title>
</head>

<body>
	<div class="container">
	<form  class="form-signin" id="uploadForm" action="${ctx}/upload" method="post" enctype="multipart/form-data">
        <h2 class="form-signin-heading">请选择文件</h2>
        <input type="file" name="file">
        <button class="btn btn-lg btn-primary btn-block" type="submit">上传</button>
      </form>
	</div>
</body>
</html>
