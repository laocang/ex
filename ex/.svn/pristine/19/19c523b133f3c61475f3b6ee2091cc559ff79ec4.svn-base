
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
	
	initFinishWorkMore();
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
	if(subject){
		subject=encodeURIComponent(subject);
	}
	var flowName=$(target).find("#flowName").text();
	var rootUrl=$(target).find("#rootUrl").text();
	var dbName=$(target).find("#dbName").text();
	if(rootUrl){
		window.location.href=rootUrl+"/index?dbName="+dbName+"&applyKind="+applyKind+"&flowName="+flowName+"&flowsn="
		+flowsn+"&flowId="+flowId+"&ratifyId="+ratifyId+"&sender="+sender+"&getDate="+getDate+"&subject="+subject+
		"&status=F&userId="+$("#userId").val()+"&t="+$("#t").val()+"&m="+$("#m").val()+"&url="+rootUrl+"&browserUrl="+document.location.href;
	}
/*	var data={
		applyKind:applyKind,
		flowId:flowId,
		t:$("#t").val()
	};
	$.post("flow/getRootUrl",data,function(url){
		url = jQuery.parseJSON(url);
		if(url!="" && url!=undefined){
			window.location.href=url+"/index?flowName="+flowName+"&flowsn="
			+flowsn+"&flowId="+flowId+"&ratifyId="+ratifyId+"&sender="+sender+"&getDate="+getDate+"&subject="+subject+
			"&status=F&userId="+$("#userId").val()+"&t="+$("#t").val()+"&m="+$("#m").val()+"&url="+url;
		}
	});*/
}

/*function btngetMoreData(){
	showLoadingToast("正在获取...");
	var searchword = $("#search_input").val();
	var urlPath = "flow/finishworkmore";
	num = parseInt(num)+1;
	var currentPage = num;
	var params = {"pageIndex":currentPage,"searchword":searchword,"t":$("#t").val(),"businessType":$("#businessType").val()};
	
	$.post(urlPath,params,function(data){
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
          			li.find(".contime").text("时　　间：["+json[i].doDate+"]");
          			li.find(".content").text(json[i].subject);
          			$("#list").append(li);
   			 }
          		$("#btnMoredataId").css("display","block");
          		$("#nomoredataId").css("display","none");
          		if(json.length < 10){
          			$("#btnMoredataId").css("display","none");
              		$("#nomoredataId").css("display","block");
          		}
          	 }else{
          		$("#btnMoredataId").css("display","none");
          		$("#nomoredataId").css("display","block");
          	 }
          	hideLoadingToast();
	}).error(function(o) { 
		hideLoadingToast();
	});
}
*/
//查询功能的实现
function searchflow(){
	$("#nomore").hide();
	showLoadingToast("正在获取...");
	num=1;
	var searchword = $("#search_input").val();
	searchworkWhole = searchword;
	var params = {"searchword":searchword,"t":$("#t").val(),"businessType":$("#businessType").val()};
	$.post("flow/finishworkmore",params,function(data){
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
	       			li.find("#sender").text(json[i].sender);
	       			li.find("#getDate").text(json[i].getDate);
	       			li.find("#subject").text(json[i].subject);
	       			li.find("#flowName").text(json[i].flowName);
	       			li.find(".header").text("业务类型："+json[i].flowName);
	       			li.find(".contime").text("时　　间：["+json[i].doDate+"]");
	       			li.find(".content").text(json[i].subject);
	       			$("#list").append(li);
				 }
	       		$("#btnMoredataId").css("display","block");
	       		$("#nomoredataId").css("display","none");
	       		$("#serachnodataId").css("display","none");
	       		if(json.length < 10){
	       			$("#btnMoredataId").css("display","none");
	       		}
	      	 }else{
	      		$("#list").empty();
	      		$("#btnMoredataId").css("display","none");
	      		$("#nomoredataId").css("display","none");
	      		$("#serachnodataId").css("display","block");
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
function initFinishWorkMore(){
    /*加载更多*/
	$(document.body).infinite().on("infinite", function() {
		if (loading) return;
		loading = true;
		var len = 0;
		
		var urlPath = "flow/finishworkmore";
		pageIndex = parseInt(pageIndex)+1;
		var currentPage = pageIndex;
		var searchKey=$("#searchKey").val();
	    if(searchKey){
	    	searchworkWhole=searchKey;
	    }
		var params = {"pageIndex":currentPage,"searchword":searchworkWhole,"t":$("#t").val(),"businessType":$("#businessType").val()};
		var orderIndex= 10*(pageIndex - 1)+1 ;//10这里从服务端读取
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
	 					 if(json[i].doDate==null||json[i].doDate==''){
	 						json[i].doDate='';
	 					 }
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
	          			li.find(".contime").text("时　　间：["+json[i].doDate+"]");
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






