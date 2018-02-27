<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>今天吃什么v1.0</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="expires" content="0">    
	<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/mui/mui.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/mui/app.css">
	<script type="text/javascript" src="<%=basePath %>js/weui/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/mui/mui.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	
	.mui-content img{
		width:45%;
		height:20$;
	}
	</style>
	
</head>
  
  <body style="background-color:white;">
 <div class="mui-content" style="width:100%;height:30%;background-color:white;">
		    <div class="mui-slider">
		        <div class="mui-slider-group mui-slider-loop"> 
		        	ai<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一个图文表格) -->
		            <div class="mui-slider-item mui-slider-item-duplicate">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="<%=basePath %>images/dinner/shaokao.jpg">
		                            <div class="mui-media-body">没有什么是一顿烧烤不能解决的</div></a></li>
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="<%=basePath %>images/dinner/jipai.jpg">
		                            <div class="mui-media-body">如果有，就两顿</div></a></li>
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="<%=basePath %>images/dinner/malatan.jpg">
		                            <div class="mui-media-body">麻辣烫！</div></a></li>
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="<%=basePath %>images/dinner/maocai.jpg">
		                            <div class="mui-media-body">冒菜！</div></a></li>
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="<%=basePath %>images/dinner/shaokao.jpg">
		                            <div class="mui-media-body">没有什么是一顿烧烤不能解决的</div></a></li>
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="<%=basePath %>images/dinner/jipai.jpg">
		                            <div class="mui-media-body">如果有，就两顿</div></a></li>
		                </ul>
		            </div>
		            <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一个图文表格) -->
		            <div class="mui-slider-item mui-slider-item-duplicate">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="<%=basePath %>images/dinner/malatan.jpg">
		                            <div class="mui-media-body">麻辣烫</div></a></li>
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="<%=basePath %>images/dinner/maocai.jpg">
		                            <div class="mui-media-body">冒菜</div></a></li>
		                </ul>
		            </div>
		        </div>
		        <div class="mui-slider-indicator" style="position: static;background-color: #fff;margin-top:12%;">
		            <span class="mui-action mui-action-previous mui-icon mui-icon-back"></span>
		            <div class="mui-number">
		                <span>1</span> / 2
		            </div>
		            <span class="mui-action mui-action-next mui-icon mui-icon-forward"></span>
		        </div>
		    </div>
		    
		</div>
		 <h5 style="background-color:#efeff4;margin-top:25%;height:30px;line-height:30px;"><a href="#" style="color:black;margin-left:6%;">胖不死你啊！</a></h5>
		<div id="checked" class="mui-input-row mui-checkbox mui-left">
						<input id="check" style="margin-top:10px;" name="checkbox" value="Item 1" type="checkbox" >
						<label>本人承诺尊重结果,在摇号过程中作弊我就是张浩！</label>
					</div> 
					<div>
						<div style="width:40%;height:200px;float:left;background:url(<%=basePath %>images/dinner/zhangzhe.jpg) no-repeat;" >
						<br/>
						<label style="font-size:50px;color:black;margin-top:100px;;margin-left:15%;">钦定</label>
						<br/><br/>	<label id="food" style="font-size:50px;color:red;padding-top:200px;;margin-left:15%;"></label>
						</div>
						<div style="width:50%;height:200px;float:right;margin-top:50px;">
						<!--<label style="margin-left:5%;">←_←结果</label>
						
						-->
						<button type="button" id="random" class="mui-btn" style="margin-top:10px;">
							小哥哥点我抽一发！
						</button>
						</div>
					
					</div>
 		
  </body>
  <script type="text/javascript">
   mui.init();
   $("#random").click(function (){
   		if($("#check").attr("checked")==null){
   			alert("请同意协议啊你个张浩!");
   			return;
   		}
   		$.post("randomToEat",function(result){
   			ob = $.parseJSON(result);
   			$("#food").text(ob);
   		});
   });
   $("#checked").click(function(){
   		$("#check").attr("checked","checked");
   });
  </script>
</html>
