$(function(){
	var navs =$(".weui_navbar").children();
	$.each(navs,function(i,nav){
		$(nav).click(function(){
			var hrefs=$(nav).attr("hrefs");
			var divs=$(".weui_tab_bd").children();
			$.each(divs,function(i,div){
				if($(div).attr("id")==hrefs){
					$(div).show();
				}else{
					$(div).hide();
				}
			});
			$.each(navs,function(i,obj){
				if(hrefs==$(obj).attr("hrefs")){
					$(obj).attr("class","weui_navbar_item weui_bar_item_on");
				}else{
					$(obj).attr("class","weui_navbar_item");
				}
			});
		});
	});
})

function query(){
	$("#tab").show();
	var searchKey =$("#searchKey").val();
	var applyKind=$("#applyKind").val();
	var array=[];
	if(applyKind=="0"){
		$.each($("#applyKind").children(),function(i,obj){
			if($(obj).val()!="0"){
				array.push($(obj).val());
			}
		});
	}else{
		array.push(applyKind);
	}
	var data={
			businessTypes:array,
			searchKey:searchKey,
			t:$("#t").val()
	};
	$.post("flow/count",data,function(result){
		if(result){
			var map=$.parseJSON(result);
			$("#wait").empty();
			$("#finish").empty();
			$("#finalize").empty();
			var head=$("[name='temp_cell']").clone().attr("name","").css("display","");
			$(head.children().get(0)).text("业务类型");
			$(head.children().get(1)).text("件数");
			$("#wait").append(head);
			$("#finish").append(head.clone());
			$("#finalize").append(head.clone());
			$.each(map,function(j,obj){
				if (obj) {
					$.each(obj,function(i,type){
						var cell=head.clone();//$("[name='temp_cell']").clone().attr("name","").css("display","");
						$(cell.children().get(0)).text(i);
						$(cell.children().get(1)).text(type);
						if(j=="W"){
							$("#wait").append(cell);
						}else if(j=="F"){
							$("#finish").append(cell);
						}else if(j=="E"){
							$("#finalize").append(cell);
						}
					})
				}
				
			});
		}
	});
}

