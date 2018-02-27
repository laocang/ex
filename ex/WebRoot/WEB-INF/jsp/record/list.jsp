<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />


    <title> - 时间轴</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="<%=basePath%>hadmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath%>hadmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>hadmin/css/animate.css" rel="stylesheet">
    <link href="<%=basePath%>hadmin/css/style.css?v=4.1.0" rel="stylesheet">

  </head>
  
 <body class="gray-bg">
    <div class="row">
        <div class="col-sm-12">
            <div class="wrapper wrapper-content">
                <div class="row animated fadeInRight">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                        <!--     <div class="text-center float-e-margins p-md">
                                <span>预览：</span>
                                <a href="#" class="btn btn-xs btn-primary" id="lightVersion">浅色</a>
                                <a href="#" class="btn btn-xs btn-primary" id="darkVersion">深色</a>
                                <a href="#" class="btn btn-xs btn-primary" id="leftVersion">布局切换</a>
                            </div> -->
                            <div class="" id="ibox-content">
 							 <div id="vertical-timeline" class="vertical-container light-timeline">
 							 	<c:if test="${fn:length(records)<1}">
 							 		<h3>还没有记录哦！</h3>
 							 	</c:if>
                            	<c:forEach items="${records }" var="record">
                                     <div class="vertical-timeline-block">
                                        <div class="vertical-timeline-icon blue-bg">
                                            <i class="fa fa-file-text"></i>
                                        </div>

                                        <div class="vertical-timeline-content">
                                            <h2>${record.subject }</h2>
                                           	<p>${record.content }</p>
                                            <a href="/ex/love" class="btn btn-sm btn-success">进入记录 </a>
                                            <span class="vertical-date">
                                        	今天 <br>
                                        <small>${record.date }</small>
                                    </span>
                                        </div>
                                    </div>
                            	</c:forEach> 
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <!-- 全局js -->
    <script src="<%=basePath%>hadmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=basePath%>hadmin/js/bootstrap.min.js?v=3.3.6"></script>



    <!-- 自定义js -->
    <script src="<%=basePath%>hadmin/js/content.js?v=1.0.0"></script>


    <script>
        $(document).ready(function () {
        	$('#vertical-timeline').toggleClass('center-orientation');

            // Local script for demo purpose only
            $('#lightVersion').click(function (event) {
                event.preventDefault()
                $('#ibox-content').removeClass('ibox-content');
                $('#vertical-timeline').removeClass('dark-timeline');
                $('#vertical-timeline').addClass('light-timeline');
            });

            $('#darkVersion').click(function (event) {
                event.preventDefault()
                $('#ibox-content').addClass('ibox-content');
                $('#vertical-timeline').removeClass('light-timeline');
                $('#vertical-timeline').addClass('dark-timeline');
            });

            $('#leftVersion').click(function (event) {
                event.preventDefault()
                $('#vertical-timeline').toggleClass('center-orientation');
            });


        });
    </script>
</body>
</html>
