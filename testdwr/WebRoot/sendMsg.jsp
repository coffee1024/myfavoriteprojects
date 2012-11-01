<%@ page language="java" pageEncoding="UTF-8"%>    
<%@ page isELIgnored="false" %>     
<html>      
  <head>      
    <title>聊天室</title>      
    <meta http-equiv="pragma" content="no-cache">      
    <meta http-equiv="cache-control" content="no-cache">      
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/SendMsg.js'></script>     
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>      
    <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>     
    <script type="text/javascript">      
        function sendMessage()      
        {      
            var msg = $("msg").value;              
            SendMsg.sendMsg(msg);    
        }      
    </script>      
  </head>      
  <body  onload="dwr.engine.setActiveReverseAjax(true);">       
  欢迎光临聊天室<br>      
        输入信息内容:<input  type="text" id="msg" name="msg" onkeypress="dwr.util.onReturn(event,sendMessage)"> 
  <br>
   <ul id="ul">  
        
     </ul>  
  </body>      
</html> 