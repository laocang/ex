function openit(target){
	var flowId=$(target).find("[name=flowId]").val();
	var applyKind=$(target).find("[name=applyKind]").val();
	var rootUrl=$(target).find("[name=rootUrl]").val();
	var dbName=$(target).find("[name=dbName]").val();
	var flowName=$(target).find("[name=flowName]").text();
	window.location.href=rootUrl+"/index?flowName="+flowName+"&flowId="+flowId+"&status=H&userId="+$("#userId").val()+"&url="+rootUrl+"&t="+$("#t").val()+"&stup="+$("#stup").val()+"&dbName="+dbName+"&browserUrl="+document.location.href;
}