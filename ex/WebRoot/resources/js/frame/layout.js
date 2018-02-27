$(function(){
	var actionsheetlist=[];
	var tableCols;
	var htmlCode;
	
	$.post("engine/form/layout_get",function(result){
		var map = jQuery.parseJSON(result);
		if(map){
			initCenter(map.efieldTypes);
			initPreviewInstances(map.einstances);
		}
	});
	
	$("#initDataTable").click(function(){
		var einstanceName = $("#einstanceName").val();
		var tenantId = $("#tenantId").val();
		var dbName = $("#dbName").val();
		var tableName = $("#tableName").val();
		if(!einstanceName || !tenantId || !tableName){
			FA.tipError("请认真填写以下必填值");
		}else{
			var data={
					dbName:dbName,
					tableName:tableName
			}
			$.post("engine/form/getColumnsByTableName",data,function(result){
				if (result) {
					tableCols = $.parseJSON(result);
					if(tableCols.length<=0){
						FA.tipError("请确定你填写的数据源"+dbName+"是否有"+tableName+"表");
					}else{
						$("select[name=temp_dataname]").empty();
						$("select[name=temp_dataname]").append("<option selected='' value=''>请选择</option>");
						$.each(tableCols,function(i,col){
							var option = $("<option value='"+col+"'>"+col+"</option>");
							$("select[name=temp_dataname]").append(option);
						});
						$("#center").show();
						$("#operate").width($("#center_right").width()-$("#form").width()-20);
					}
				}else{
					FA.tipError("请确定你填写的数据源"+dbName+"是否有"+tableName+"表");
				}
			}).error(function(){
				FA.tipError("请确定你填写的数据源"+dbName+"是否有"+tableName+"表");
			});
		}
	});
	
	$("#preview").click(function(){
		var einstance=getEInstance();
		var eform=getEForm();
		var efieldss=[];
		var efields=getEFields();
		efieldss.push(efields);
		eform["efieldsList"]=efieldss;
		var eforms=[];
		eforms.push(eform);
		einstance["eforms"]=eforms;
		var div =FA.getInstanceDiv(einstance);
		$("#dialog_preview_phone").empty().append(div);
		$("#dialog_preview").show();
		htmlCode=$("#dialog_preview_phone").html();
	});
	
	$("#dialog_preview_close").click(function(){
		$("#dialog_preview").hide();
	});
	
	$("#save").click(function(){
		var einstance=getEInstance();
		var eform=getEForm();
		var efields=getEFields();
		var data={
				einstance:JSON.stringify(einstance),
				eform:JSON.stringify(eform),
				efields:JSON.stringify(efields)
		}
		$.post("engine/form/layout_save",data,function(result){
			if (result) {
				FA.tipSuccess("保存成功");
				var clone=$("#tip_call").clone().show();
				clone.find("[name=tip_call_id]").replaceWith(result);
				var tiphtml=clone.html();
				$.alert(tiphtml, "你可以使用以下代码在你的脚本中完成调用");
			}
		});
		
	});
	
	/**
	 * 初始化手机表单和操作
	 */
	function initCenter(efieldTypes){
		var cells=FA.getWeCells();
		cells.css("margin-top","0");
		$("#form").append(cells);//先直接做简单表单的
		$.each(efieldTypes,function(i,type){
			actionsheetlist[i]={text:type.remark,value:type.name};
			var li=$("<li name='"+type.name+"'>"+type.remark+"</li>");
			li.click(function(){
				addRow(type.name,false,cells,null);
			});
			$("#ctrllist").append(li);
		});
	}
	
	/**
	 * 初始化预览时的实例列表
	 */
	function initPreviewInstances(einstances){
		$.each(einstances,function(i,inst){
			var option = $("<option value='"+inst.id+"'>"+inst.name+"</option>");
			$("#preview_einstances").append(option);
		})
		$("#preview_einstances").change(function(){
			var einstanceId=$(this).val();
			if (einstanceId) {
				FA.init({
					id:einstanceId,
					success:function(o){
						var div =FA.getInstanceDiv();
						$("#dialog_preview_phone").empty().append(div);
					}
				});
			}else{
				$("#dialog_preview_phone").empty().append(htmlCode);
			}
		});
	}
	
	/**
	 * 获取初始化的实例数据（预览和保存时可用）
	 */
	function getEInstance(){
		var einstanceName = $("#einstanceName").val();
		var tenantId = $("#tenantId").val();
		var dbName = $("#dbName").val();
		var einstance={
				name:einstanceName,
				tenantId:tenantId,
				dbName:dbName
		}
		return einstance;
	}
	
	/**
	 * 获取初始化的表单数据（预览和保存时可用）
	 */
	function getEForm(){
		var tableName = $("#tableName").val();
		var eform={
				tableName:tableName,
				type:"COMMON",
				peformId:"0"
		}
		return eform;
	}

	/**
	 * 获取初始化的表单域集合数据（预览和保存时可用）
	 */
	function getEFields(){
		var cells = $("#form").find("div[class='weui_cells weui_cells_form']").children();
		var ops=$("#operate").children();
		var array=[];
		$.each(cells,function(i,cell){
			var op= $(ops[i]) ;
			var field={
					name:op.find("[field=name]").val(),
					cname:$(cell).find("[field=cname]").val(),
					visibled:op.find("[field=visibled]")[0].checked,
					readOnly:op.find("[field=readonly]")[0].checked,
					required:op.find("[field=required]")[0].checked,
					remark:op.find("[field=remark]").val(),
					type:$(cell).attr("type"),
					sortOrder:i*10
			}
			array[i]=field;
		});
		return array;
	}
	
	
	/**
	 * 表单和操作组成行
	 */
	function addRow(type,isAppend,cells,ptemp){
		//index++;
		var cell =GLOBAL.fieldHandler.initField({type:type,value:'',cname:'<input class="weui_input" style="width:80px;" field=cname />',visibled:true},cells);
		cell.attr("type",type);
		//cell.attr("cell",index);
		if(isAppend){
			cells.after(cell);
		}else{
			cells.append(cell);
		}
		if (cell.outerHeight()<44) {
			cell.outerHeight(44);
		}
		var temp=$("[name=temp_operate]").clone().show().attr("name","");
		//temp.attr("operate",index);
		temp.height(cell.outerHeight());
		//模板中控件事件
		temp.find("[name='temp_append']").click(function(){
			$.each(actionsheetlist,function(i,obj){
				obj.onClick=function(){
					addRow(this.value,true,cell,temp);
				}
			});
			$.actions({
				title:"请选择表单域类型",
				actions: actionsheetlist
			});
			//addRow('TEXT',true,cell,temp);
		});
		temp.find("[name='temp_delete']").click(function(){
			temp.remove();
			cell.remove();
		});
		if(isAppend){
			ptemp.after(temp);
		}else{
			$("#operate").append(temp);
		}
		
	}
	
})