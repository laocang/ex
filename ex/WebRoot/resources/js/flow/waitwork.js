var searchworkWhole = "";

$(function(){
	$("#search_input").val("");
	$("#search_input").focus(function(){
	  		var weuiSearchBar = $("#search_bar");
        weuiSearchBar.addClass("weui_search_focusing");
        $("#search_confirm").show();
	});
	$("#search_clear").click(function(){
        $("#search_input").val("");
    });
	var temp=$("li[name=temp]").first();
	if(temp){
		rowtemp=temp.clone();
	}

	initWaitworkMore();
});


	var num = 1;
	var rowtemp;//报件列表行的模板
	function openit(target){
		var flowsn=$(target).find("#flowsn").text();
		var flowId=$(target).find("#flowId").text();
		var ratifyId=$(target).find("#ratifyId").text();
		var applyKind=$(target).find("#applyKind").text();
		var sender=$(target).find("#sender").text();
		var getDate=$(target).find("#getDate").text();
		var subject=$(target).find("#subject").text();
		var preActionResult=$(target).find("#preActionResult").text();
		if(subject){
			subject=encodeURIComponent(subject);
		}
		var flowName=$(target).find("#flowName").text();
		var rootUrl=$(target).find("#rootUrl").text();
		var dbName=$(target).find("#dbName").text();
		if(rootUrl){
			var href=rootUrl+"/index?preActionResult="+preActionResult+"&dbName="+dbName+"&applyKind="+applyKind+"&flowName="+flowName+"&flowsn="
			+flowsn+"&flowId="+flowId+"&ratifyId="+ratifyId+"&sender="+sender+"&getDate="+getDate+"&subject="+subject+
			"&status=W&userId="+$("#userId").val()+"&t="+$("#t").val()+"&stup="+$("#stup").val()+"&m="+$("#m").val()+"&url="+rootUrl+"&browserUrl="+document.location.href;
			window.location.href=href;
		}
/*		var data={
			applyKind:applyKind,
			flowId:flowId,
			t:$("#t").val()
		};
		$.post("flow/getRootUrl",data,function(url){
			url = jQuery.parseJSON(url);
			if(url!="" && url!=undefined){
				window.location.href=url+"/index?flowName="+flowName+"&flowsn="
				+flowsn+"&flowId="+flowId+"&ratifyId="+ratifyId+"&sender="+sender+"&getDate="+getDate+"&subject="+subject+
				"&status=W&userId="+$("#userId").val()+"&t="+$("#t").val()+"&m="+$("#m").val()+"&url="+url+"&browserUrl="+document.location.href;
			}
		});*/
	 	
	}

/*	function btngetMoreData(){
		showLoadingToast("请稍后");
		var searchword = $("#search_input").val();
		var urlPath = "flow/waitworkmore";
		num = parseInt(num)+1;
		var currentPage = num;
		var params = {"pageIndex":currentPage,"searchword":searchword,"t":$("#t").val(),"businessType":$("#businessType").val()};
		$.ajax({  
	        url:urlPath,  
	        type:"post",  
	        dataType:"json",  
	        data:params,
	        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
	        success:function(data){//回调函数
	       	 var json = eval(data);
	       	 if(json && json.length > 0){
	       		 //var temp=$("li[name=temp]").first();
	       		 for ( var i = 0; i < json.length; i++) {
	       			var li=rowtemp.clone();
	       			li.find("#rootUrl").text(json[i].rootUrl);
	       			li.find("#dbName").text(json[i].dbName);
	       			li.find("#flowsn").text(json[i].flowsn);
	       			li.find("#flowId").text(json[i].flowId);
	       			li.find("#ratifyId").text(json[i].ratifyId);
	       			li.find("#applyKind").text(json[i].applyKind);
	       			li.find("#sender").text(json[i].sender);
	       			li.find("#getDate").text(json[i].getDate);
	       			li.find("#subject").text(json[i].subject);
	       			li.find("#flowName").text(json[i].flowName);
	       			li.find(".header").text("业务类型："+json[i].flowName);
	       			li.find(".contime").text("时　　间：["+json[i].getDate+"]");
	       			li.find(".content").text(json[i].subject);
	       			$("#list").append(li);
				 }
	       		$("#btnMoredataId").show();
	       		$("#nomoredataId").hide();
	       		if(json.length < 10){
	       			$("#btnMoredataId").hide();
	           		$("#nomoredataId").show();
	       		}
	       	 }else{
	       		$("#btnMoredataId").hide();
	       		$("#nomoredataId").show();
	       	 }
	       	hideLoadingToast();
	        },
	        error:function(errormsg){
	        	hideLoadingToast();
	       	 $.messager.alert("提示","出现异常错误信息："+errormsg,"error");
	        }
	    });  
	}*/

	//查询功能的实现
	function searchflow(){
		$("#nomore").hide();
		showLoadingToast("正在获取...");
		num=1;
		var searchword = $("#search_input").val();
		searchworkWhole = searchword;
		var params = {"searchword":searchword,"t":$("#t").val(),"businessType":$("#businessType").val()};
		$.post("flow/waitworkmore",params,function(data){
			var json = eval(data);

			 if(json && json.length > 0){
				 //var temp=$("li[name=temp]").first();
				 $("#list").empty();
	       		 for ( var i = 0; i < json.length; i++) {
	       			var li=rowtemp.clone();
	       			li.find("#rootUrl").text(json[i].rootUrl);
	       			li.find("#dbName").text(json[i].dbName);
	       			li.find("#flowsn").text(json[i].flowsn);
	       			li.find("#flowId").text(json[i].flowId);
	       			li.find("#ratifyId").text(json[i].ratifyId);
	       			li.find("#applyKind").text(json[i].applyKind);
	       			li.find("#preActionResult").text(json[i].preActionResult);
	       			li.find("#sender").text(json[i].sender);
	       			li.find("#getDate").text(json[i].getDate);
	       			li.find("#subject").text(json[i].subject);
	       			li.find("#flowName").text(json[i].flowName);
	       			li.find(".header").text("业务类型："+json[i].flowName);
	       			li.find(".contime").text("时　　间：["+json[i].getDate+"]");
	       			li.find(".content").text(json[i].subject);
	       			$("#list").append(li);
				 }
		       		
		       		$("#serachnodataId").hide();
	       		/*$("#btnMoredataId").show();
	       		$("#nomoredataId").hide();

	       		if(json.length < 10){
	       			$("#btnMoredataId").hide();
	       		}*/
	      	 }else{
	      		$("#list").empty();
	      		/*$("#btnMoredataId").hide();
	      		$("#nomoredataId").hide();*/
	      		$("#serachnodataId").show();
	      		$("#noFlow").hide();
	      	 }
			 hideLoadingToast();
		}).error(function(o) { 
			hideLoadingToast();
		});
	}
	
	function showLoadingToast(text){
		$("#loadingToast_content").text(text);
		  $('#loadingToast').show();
	}
	
	function hideLoadingToast(){
		  $('#loadingToast').hide();
	}
	
	
	/************************更多*****************************/
	
	var loading=false;
	var pageIndex = 1;
	var pageSize = 10;
	function initWaitworkMore(){
	    /*加载更多*/
		$(document.body).infinite().on("infinite", function() {
			if (loading) return;
			loading = true;
			var len = 0;
			var urlPath = "flow/waitworkmore";
			pageIndex = parseInt(pageIndex)+1;
			var currentPage = pageIndex;
		    var searchKey=$("#searchKey").val();
		    if(searchKey){
		    	searchworkWhole=searchKey;
		    }
			var params = {"pageIndex":currentPage,"searchword":searchworkWhole,"t":$("#t").val(),"businessType":$("#businessType").val()};
			
			$.ajax({  
		        url:urlPath,  
		        type:"post",  
		        dataType:"json",  
		        data:params,
		        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		        success:function(data){//回调函数
		       	 var json = eval(data);
		       	 len = json.length;
		       	 if(len>0){
	    			$("#infinite-1").css('display', 'block');
	    		 }
		       	 if(json.length > 0){
		       		setTimeout(function() {
		 				 for ( var i = 0; i < json.length; i++) {
		 					 if(json[i].getDate==null||json[i].getDate==''){
			 						json[i].getDate='';
			 					 }
		 					var li=rowtemp.clone();
			       			li.find("#rootUrl").text(json[i].rootUrl);
			       			li.find("#dbName").text(json[i].dbName);
			       			li.find("#flowsn").text(json[i].flowsn);
			       			li.find("#flowId").text(json[i].flowId);
			       			li.find("#ratifyId").text(json[i].ratifyId);
			       			li.find("#applyKind").text(json[i].applyKind);
			       			li.find("#sender").text(json[i].sender);
			       			li.find("#preActionResult").text(json[i].preActionResult);
			       			li.find("#getDate").text(json[i].getDate);
			       			li.find("#subject").text(json[i].subject);
			       			li.find("#flowName").text(json[i].flowName);
			       			li.find(".header").text("业务类型："+json[i].flowName);
			       			li.find(".contime").text("时　　间：["+json[i].getDate+"]");
			       			li.find(".content").text(json[i].subject);
			       			$("#list").append(li);
		 				 }
		 		          loading = false;
		 		        }, 1000);
		       	
		       	 }else{
		       		$("#infinite-1").css('display', 'none');
		       		var w = $("#noFlow").text();
		       		if(!(w!=null&&w!="")){
		       			$("#nomore").show();
		       		}
		       	 }
		        },
		        error:function(errormsg){
		       	 $.messager.alert("提示","出现异常错误信息："+errormsg,"error");
		        }
		    });  
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	