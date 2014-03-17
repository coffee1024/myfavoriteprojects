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
	<object data="data:application/x-silverlight-2," type="application/x-silverlight-2" width="100%" height="300">
		<param name="source" value="http://load2.nipic.com/act/upload7.xap" />
		<param name="onerror" value="onSilverlightError" />
		<param name="background" value="white" />
		<param name="minRuntimeVersion" value="2.0.31005.0" />
		<param name="autoUpgrade" value="true" />
		<param name="InitParams" value="UserId=10763280,BlockSize=200,MaxUploads=3,DefaultColor=#EBEEF1,FileFilter=*.*|*.*|*.jpg|*.jpg|*.gif|*.gif|*.bmp|*.bmp|*.png|*.png|*.tif|*.tif|*.tiff|*.tiff|*.psd|*.psd|*.ai|*.ai|*.eps|*.eps|*.cdr|*.cdr|*.rar|*.rar|*.zip|*.zip|*.swf|*.swf|*.fla|*.fla|*.ppt|*.ppt|*.pptx|*.pptx|*.swf|*.swf|*.fla|*.fla|*.mov|*.mov|*.avi|*.avi|*.dvd|*.dvd|*.mpg|*.mpg|*.flv|*.flv|*.mp4|*.mp4|*.mp3|*.mp3|*.AE|*.AE|*.rm|*.rm|*.rmvb|*.rmvb|*.ppsx|*.ppsx|*.pdf|*.pdf|*.aep|*.aep" /><br />
	    <table class="microsoftTip">
			<tr>
		    	<td width="54" align="center" rowspan="2"><img border="0" src="http://static.nipic.com/img/notice.gif" /></td>
		        <td>您还没有安装，需要安装后才能使用，<a target="_blank" href="http://go.microsoft.com/fwlink/?LinkID=124807" class="blueu">点击击下载并安装</a></td>
		    </tr>
		    <tr>
		    	<td>若不能下载请点击本站下载 (<a href="http://load2.nipic.com/tool/Silverlight.exe" target="_blank" class="blueu">windows平台</a>)、(<a href="http://load2.nipic.com/tool/Silverlight-mac.zip" target="_blank" class="blueu">mac平台</a>) 或到<a target="_blank" href="http://www.microsoft.com/silverlight/" class="blueu">微软官方网站下载</a></td>
		    </tr>
		</table>
	</object>
</body>
</html>
