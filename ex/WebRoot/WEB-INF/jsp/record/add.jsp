<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />


    <title>添加记录</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="<%=basePath%>hadmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath%>hadmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>hadmin/css/animate.css" rel="stylesheet">
    <link href="<%=basePath%>hadmin/css/style.css?v=4.1.0" rel="stylesheet">
	<script src="<%=basePath%>hadmin/js/common.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		function add(){
			var title = $("#title").val();
			var content = $("#content").val();
			var date = $("#date").val();
			if(title==null||title==''){
				return;
			}
			if(content==null||content==''){
				return;
			}
			if(date==null||date==''){
				return;
			}
			$.post('addrecord',{title :title,content:content,date:date},function(result){ 
				if(result==1){
					history.back();
				}else{
					alert("添加失败！");
				}
			})
		}
	</script>
  </head>
  
 <body class="gray-bg">
 <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>添加记录</h5>
                    </div>
                    <div class="ibox-content">
                    <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">标题：</label>

                                <div class="col-sm-8">
                                    <input placeholder="输入标题" id="title" class="form-control" required> 
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">内容：</label>
                                <div class="col-sm-8">
                                    <textarea placeholder="内容" id="content" class="form-control" required></textarea>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">日期：</label>
                                <div class="col-sm-8">
                                    <input type="date" placeholder="请选择日期" id="date" class="form-control" required />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button class="btn btn-sm btn-info" type="sumbit" onclick="add()">保存</button>
                                </div>
                            </div>
                            </div>
                    </div>
                </div>
            </div>

    <!-- 全局js -->
    <script src="<%=basePath%>hadmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=basePath%>hadmin/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=basePath%>hadmin/js/plugins/validate/jquery.validate.min.js"></script>

    <script>
    </script>
</body>
</html>
