<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>record</title>
      <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/weui1.0/weui.min.css" rel="stylesheet" />
  	<link href="<%=basePath%>css/weui1.0/jquery-weui.css" rel="stylesheet" />
    <script src="<%=basePath%>js/weui/fastclick.js" type="text/javascript"></script>
    <script src="<%=basePath%>hadmin/js/common.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath %>js/weui/jquery-2.1.4.js"></script>
	<script type="text/javascript">
		$(function(){
			var error = '${error}';
			if(error!=null&&error!=''){
				alert(error);
			}
		})
		function login(){
			var logonId = $("#logonId").val();
			var password = $("#password").val();
			      
			post('record/login', {logonId :logonId,password:password});  
		}
	</script>
  </head>
  
  <body>
  <div class="weui-cells weui-cells_form">
    <div class="weui-cell">
    <div class="weui-cell__hd"><label class="weui-label">账户:</label></div>
    <div class="weui-cell__bd">
      <input class="weui-input" type="text" id="logonId" placeholder="请输入账户">
    </div>
  </div>
  <div class="weui-cell">
    <div class="weui-cell__hd"><label class="weui-label">密码:</label></div>
    <div class="weui-cell__bd">
      <input class="weui-input" type="text" id="password" placeholder="请输入密码">
    </div>
  </div>
</div>
<a href="javascript:;" style="width:90%;margin-top:15px;" onclick="login()" class="weui-btn weui-btn_primary">登录</a>
  </body>
</html>
