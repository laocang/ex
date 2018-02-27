<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath %>resources/js/webui/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/echarts/echarts.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/js/echarts/echarts.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	

  </head>
  
  <body>
	 <div id="main" style="width: 600px;height:400px;"></div>
  </body>
  <script type="text/javascript">
	 var myChart = echarts.init(document.getElementById('main'));
	option = {
	    title: {
	        text: '基础体温变化',
	        subtext: ''
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['基础体温']
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            dataView: {readOnly: false},
	            magicType: {type: ['line', 'bar']},
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    xAxis:  {
	        type: 'category',
	        boundaryGap: false,
	        //'一号','二号','三号','四号','五号','六号','七号','八号','九号','十号','十一号','十二号','十三号',,'十八号','十九号','二十号','二十一号','二十二号','二十三号','二十四号','二十五号','二十六号','二十七号','二十八号','二十九号','三十号','三十一号'
	        data: ['十四号','十五号','十六号','十七号','十八号','十九号','二十号']
	    },
	    yAxis: {
	        type: 'value',
            scale: true, 
            precision: 1, 
            splitNumber: 8, 
            boundaryGap: [0.1], 
	        axisLabel: {
	            formatter: '{value} °C'
	        }
	    },
	    series: [
	        {
	            name:'基础体温折线图',
	            type:'line',
	            data:[36.4,36.3,36.6,36.4,36.6,36.3,36.7],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'},
	                    {type: 'min', name: '最小值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'}
	                ]
	            }
	        }
	    ]
	};
    myChart.setOption(option);
	
	</script>
</html>
