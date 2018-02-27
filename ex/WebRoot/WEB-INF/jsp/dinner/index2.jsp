<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
 <html>
  <head>
   <title></title>
	<script type="text/javascript" src="<%=basePath %>js/weui/jquery-2.1.4.js"></script>
	  </head>
   <script type="text/javascript"><!--
   	$(function(){
   		var time = new Date();
   		time = formatDate(time);
   		var times = DateDiff(time,"2017-06-30");
   		$("#time").text(times);
   		
   		var times2 = DateDiff(time,"2016-12-24");
   		$("#time2").text(times2);
   		
   	});
   	
   	   function  DateDiff(sDate1,sDate2){    //sDate1和sDate2是2006-12-18格式  
      	var date1 = new Date(sDate1);
      	var date2 = new Date(sDate2);
      var iDays  =  parseInt(Math.abs(date1-date2)/1000/60/60/24);//把相差的毫秒数转换为天数  
       return  iDays; 
   	}    
 
	 var formatDate = function (date) {  
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? '0' + m : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    return y + '-' + m + '-' + d;  
};  
   --></script>
  <body>
  <h1>距离藏獒毕业倒计时:</h1>
	<div id="time" style="font-size:50px;"></div>
	<h1>天！</h1>
	
	<br/>
	<br/>
	
	  <h1>距离孙俊海考研倒计时:</h1>
	<div id="time2" style="font-size:50px;"></div>
	<h1>天！</h1>
  </body>
</html>