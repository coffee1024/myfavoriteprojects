<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
	<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
  </head>
  
  <body>
    <h1>test</h1>
<div id="my_chart"></div>
 	
<script type="text/javascript">
swfobject.embedSWF(
  "open-flash-chart.swf", "my_chart", "1000", "500",
  "9.0.0", "expressInstall.swf",
//   {"data-file":"/ofc/data"}
{"data-file":"data.txt"}
  );


</script>
  </body>
</html>
