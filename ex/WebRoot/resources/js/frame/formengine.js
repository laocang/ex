$(function(){
	FE.initHandlers();
	FE.initGlobal();
})

function Handler(type) {
	this.type=type;
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

Handler.prototype = {
	setNextHandler:function(handler){
		if (this.handler) {
			this.handler.setNextHandler(handler);
		}else{
			this.handler=handler;
		}
	},	
    matchForm: function (eform) {
        return this.type == eform.type;
    },
    initForm:function(eform,container){
    	if(this.matchForm(eform)){
    		this.doHandleForm(eform,container);
		}else{
			if(this.handler!=null)
				this.handler.initForm(type,container);
		}
    },
    doHandleForm:function(eform,container){},
    matchField: function (efield) {
        return this.type == efield.type;
    },
    initField:function(efield,container){
    	if(this.matchField(efield)){
    		return this.doHandleField(efield,container);
		}else{
			if(this.handler!=null)
				return this.handler.initField(efield,container);
		}
    },
    doHandleField:function(efield,container){}
};

var GLOBAL={}//全局参数

var FE = (function($) {
	var obj={
			initGlobal:function(){
				GLOBAL.efieldMap={};
				
				//GLOBAL.PREFIX_FORM="FORM_";
				//GLOBAL.PREFIX_INSTANCE="INSTANCE_";
				//GLOBAL.PREFIX_FIELD="FIELD_";
				
				GLOBAL.TIP_ERROR_INITINSTANCE="实例化数据失败";
				GLOBAL.TIP_ERROR_FIELDNULL="不能为空";
				GLOBAL.TIP_ERROR_SAVE="保存失败";
				GLOBAL.TIP_SUCCESS_SAVE="保存成功";
			},
			getChildForms : function(eforms, peformId) {
				var childForms = [];
				$.each(eforms, function(i, obj) {
					if (obj.peformId == peformId) {
						childForms.push(obj);
					}
				});
				return childForms;
			},
			getFirstFields:function(eform){
				var efidlds;
				var efieldsList=eform.efieldsList;
		        if(efieldsList && efieldsList.length>0 && efieldsList[0].length>0){
		        	efidlds=efieldsList[0];
		        }
		        return efidlds;
			},
			initSortFields:function(eforms,peform,ceform){
				var efields=FE.getFirstFields(ceform);
				if(efields){
					if(peform.sortFields){
						var array=peform.sortFields;
						$.each(efields,function(i,efield){
							efield.peformInstId=peform.id;//主表单的实例ID
							efield.tableName=ceform.tableName;
							array.push(efield);
						});
						peform.sortFields=array;
					}else{
						peform.sortFields=[];
						$.each(efields,function(i,efield){
							efield.peformInstId=peform.id;//主表单的实例ID
							efield.tableName=ceform.tableName;
							peform.sortFields.push(efield);
						});
						//peform.sortFields=efields;
					}
				}
				var childForms = FE.getChildForms(eforms, ceform.eformId);
				if(childForms){
					$.each(childForms, function(i, childForm) {
						childForm.peformInstId=peform.id;//主表单的实例ID
						FE.initSortFields(eforms,peform,childForm);
					});
				}
			},
			sortFields:function(efields){
				if(efields){
					efields.sort(function(a,b){
			            return a.sortOrder-b.sortOrder});
				}
			},
//			initFormsDiv : function(eforms, peformId, div) {
//				var childForms = FE.getChildForms(eforms, peformId);
//				if (childForms.length > 0) {
//					$.each(childForms, function(i, childForm) {
//						var childFormDiv=FA.getFormDiv(childForm);
//						div.append(childFormDiv);
//						FE.initFormsDiv(eforms, childForm.eformId, childFormDiv);
//					});
//				}
//			},
			setCtrlInfo:function(efield,ctrl,fn_hide,fn_readOnly,fn_required){
				if(ctrl){
					ctrl.attr("id",efield.ctrlId);
					ctrl.attr("name",efield.name);
					ctrl.attr("tableName",efield.tableName);
					ctrl.attr("peformInstId",efield.peformInstId);
					//下面是设置表单域权限，特殊控件可以在自己的handler中自行处理
					if(!efield.visibled){
						fn_hide();
					}else{
						if(efield.readOnly){
							fn_readOnly();
						}else{
							if(efield.required){
								fn_required();
							}
						}
					}
				}
			},
			validateSetValue:function(fn_tip){
				var flag=true;
				$.each(GLOBAL.efieldMap, function(i, obj) {
					if($("#" + i).length>0){
						var value = $("#" + i).val();
						if(obj.required){
							if(value==null || value==""){
								flag=false;
								$("#" + i).focus();
								fn_tip(obj);
								return false;
							}else{
								obj.value=value;
							}
						}else{
							obj.value = value==null?"":value;
						}
					}
				});
				return flag;
			},
			initHandlers:function(){
				//加载链条
				$.each(FormHandlerExt,function(i,handler){
					if(i==0){
						GLOBAL.formHandler=handler;
					}else{
						GLOBAL.formHandler.setNextHandler(handler);
					}
				});
				$.each(FieldHandlerExt,function(i,handler){
					if(i==0){
						GLOBAL.fieldHandler=handler;
					}else{
						GLOBAL.fieldHandler.setNextHandler(handler);
					}
				});
			},	
			getFormHandler:function(obj){
				var handler = new Handler(obj.type);
				handler.doHandleForm=function(eform,container){
					obj.doHandleForm(eform,container);
				} 
				return handler;
			},
			getFieldHandler:function(obj){
				var handler = new Handler(obj.type);
				handler.doHandleField=function(efield,container){
					return obj.doHandleField(efield,container);
				} 
				return handler;
			}
		}
	return obj;
})(jQuery);

/*对外提供的api*/
var FA={
	init:function(obj){
		if(obj){
			if(obj.url){
				GLOBAL.rootRequestUrl=obj.url;
			}else{
				GLOBAL.rootRequestUrl="engine/form";
			}
			var data={
				einstanceId:obj.id,
				pkValue:obj.pkValue
			}
			FA.getInstance(GLOBAL.rootRequestUrl,data,function(result){
				var inst=$.parseJSON(result);
				if(inst){
					GLOBAL.einstance=inst;
					$("body").append(FA.getInstanceDiv(inst));
					if(obj.success){
						obj.success(inst);
					}
				}
			},function(){
				FA.tipError(GLOBAL.TIP_ERROR_INITINSTANCE);
			});
		}
	},	
	getInstance:function(url,data,fn_success,fn_error){
		$.post(url+"/getInstance",data,function(result){
			fn_success(result);
		}).error(function() { 
			fn_error();
		});
	},
	save:function(fn_success,fn_error){
		if(FA.saveBefore()===undefined || FA.saveBefore()){
			if(FE.validateSetValue(function(efield){
				//验证错误提示
				FA.tipError(efield.cname+"　"+GLOBAL.TIP_ERROR_FIELDNULL);
			})){
				//保存表单
				var data={
						einstance:JSON.stringify(GLOBAL.einstance)
				}
				$.post(GLOBAL.rootRequestUrl+"/save",data,function(result){
					fn_success(result);
				}).error(function() { 
					fn_error();
				});
			}
		}
	},	
	/*以下返回类型都是控件*/
//	getFieldCtrl:function(fieldName,eformInstanceId){
//		if(eformInstanceId){
//			var formDiv=$("#"+GLOBAL.PREFIX_FORM+eformInstanceId);
//			if(formDiv){
//				return formDiv.find("[name='"+fieldName+"']");
//			}
//		}
//		return $("body").find("[name='"+fieldName+"']");
//	},
	getInstanceDiv:function(instance){
		if(!instance){
			return $("#"+GLOBAL.einstance.id);
		}
		var divinst=FA.getDiv(instance.id);
		divinst.addClass("fe_instance");
		var eforms=instance.eforms;
		var notCommonEForms=[];
		$.each(eforms,function(i,eform){
			if(eform.peformId=="0" && eform.type=="COMMON"){//主表单的表单类型为COMMON，其他表单单独初始化
				FE.initSortFields(eforms,eform,eform);//将所有COMMON表单的表单域集合合并赋给主表单
				FE.sortFields(eform.sortFields);
				//FE.initFormsDiv(eforms,eform.eformId,divform);
				//初始化主表单,此处可以做导航
				var divform=FA.getFormDiv(eform);
				divform.attr("peformId","0");
				divinst.append(divform);
			}else{
				//非COMMON表单域单独处理
				notCommonEForms.push(eform);
			}
		});
		return divinst;
	},	
	getFormDiv:function(eform){//单数据表，常用于大控件使用，当然也可以当做表单使用
		var div=FA.getDiv(eform.id+"_"+eform.tableName);
		div.attr("eformInstId",eform.id);
		//div.attr("peformInstId",eform.peformId);
		div.attr("tableName",eform.tableName);
		GLOBAL.formHandler.initForm(eform,div);
		return div;
	},	
	getDiv:function(id){
		var div=$("<div id="+id+"></div>");
		return div;
	},
	getCellDivider:function(name){
		var div =$("<div class='fe_cell_divider'></div>");
		if(name){
			div.height(25);
			div.append(name);
		}
		return div;
	},
	/*以下是基于weui封装的控件调用*/
	tipSuccess:function(msg){
		$.toptip(msg, 1500, 'success'); 
	},
	tipError:function(msg){
		$.toptip(msg, 1500, 'error'); 
	},
	getWeCells:function(){
		var div=$("<div class='weui_cells weui_cells_form'></div>");
		return div;
	},
	getWeCell:function(label,primary,nohd){
		var div=$("<div class='weui_cell'></div>");
		var hddiv=$("<div class='weui_cell_hd'></div>");
		hddiv.append(label);
		var bddiv=$("<div class='weui_cell_bd weui_cell_primary'></div>");
		bddiv.append(primary);
		if(nohd){
			div.append(bddiv);
		}else{
			div.append(hddiv).append(bddiv);
		}
		return div;
	},
	getWeCellEfield:function(efield,ctrl,nohd){
		var cell=FA.getWeCell(efield.cname,ctrl,nohd);
		FE.setCtrlInfo(efield,ctrl,
		function(){
			cell.hide();
		},function(){
			ctrl.attr("disabled","disabled");
		},function(){
			ctrl.attr("required","required");
			if(cell.find(".weui_cell_hd")){
				cell.find(".weui_cell_hd").addClass("fe_cell_required");
			}
		});
/*		if(ctrl){
			ctrl.attr("id",efield.ctrlId);
			ctrl.attr("name",efield.name);
			if(!efield.visibled){
				cell.hide();
			}else{
				if(efield.readOnly){
					ctrl.attr("disabled","disabled");
				}else{
					if(efield.required){
						ctrl.attr("required","required");
						cell.find(".weui_cell_hd").addClass("fe_cell_required");
					}
				}
			}
		}*/
		return cell;
	},
	getWeInputText:function(value,placeholder){
		if(!value){value="";}
		if(!placeholder){placeholder="";}
		var input=$("<input value='"+value+"' class='weui_input' placeholder='"+placeholder+"'>");
		return input;
	},
	getWeDate:function(value,placeholder){
		var input=FA.getWeInputText(value,placeholder);
		input.click(function(){
			input.focus();
		});
		input.attr("readonly","readonly");
		input.calendar();
		return input;
	},
	getWeDateTime:function(value,placeholder){
		var input=FA.getWeInputText(value,placeholder);
		input.click(function(){
			input.focus();
		});
		input.attr("readonly","readonly");
		input.datetimePicker();
		return input;
	},
	getWeTextarea:function(value,placeholder,rows){
		if(!placeholder){
			placeholder="";
		}
		if(!rows){
			rows="3";
		}
		if(!value){
			value="";
		}
		//var div=FA.getWeCells();
		//var weui_cell=$("<div class='weui_cell'></div>");
		//var weui_cell_primary=$("<div class='weui_cell_bd weui_cell_primary'></div>");
		var weui_textarea=$("<textarea class='weui_textarea' placeholder='"+placeholder+"' rows='"+rows+"'></textarea>")
		weui_textarea.val(value);
		//weui_cell_primary.append(weui_textarea);
		//weui_cell.append(weui_cell_primary);
		//div.append(weui_cell);
		return weui_textarea;
	},
	/*开放的生命周期接口*/
	saveBefore:function(){}
}

var FormHandlerExt=[
	FE.getFormHandler({
		type:"COMMON",
		doHandleForm:function(eform,container){
//	    	var efieldsList=eform.efieldsList;
//	    	if(efieldsList && efieldsList.length>0 && efieldsList[0].length>0){
//	    		var cells=FA.getWeCells();
//				container.append(cells);
//	    		$.each(efieldsList[0],function(i,efield){
//	    			efield.ctrlId=GLOBAL.PREFIX_FIELD+eform.id+"_"+efield.id;//普通表单中表单域ID的命名格式
//	    			GLOBAL.efieldMap[efield.ctrlId]=efield;
//	    			var cell = GLOBAL.fieldHandler.initField(efield,cells);
//	    			cells.append(cell);
//	    		});
//	    	}
			var sortFields=eform.sortFields;
	    	if(sortFields){
	    		var cells=FA.getWeCells();
				container.append(cells);
	    		$.each(sortFields,function(i,efield){
	    			efield.ctrlId=efield.peformInstId+"_"+efield.tableName+"_"+efield.name;//普通表单中表单域ID的命名格式
	    			GLOBAL.efieldMap[efield.ctrlId]=efield;
	    			var cell = GLOBAL.fieldHandler.initField(efield,cells);
	    			cells.append(cell);
	    		});
	    	}
		}
	})
]

var FieldHandlerExt=[
	FE.getFieldHandler({
		type:"TEXT",
		doHandleField:function(efield,container){
			var input =FA.getWeInputText(efield.value,efield.remark);
			//container.append(FA.getWeCellEfield(efield,input));
			return FA.getWeCellEfield(efield,input);
		}
	}),
	FE.getFieldHandler({
		type:"DATE",
		doHandleField:function(efield,container){
			var input =FA.getWeDate(efield.value,efield.remark);
			var cell=FA.getWeCellEfield(efield,input);
			cell.append("<div class='fe_cell_ft'></div>");
			//container.append(cell);
			return cell;
		}
	}),
	FE.getFieldHandler({
		type:"DATETIME",
		doHandleField:function(efield,container){
			var input =FA.getWeDateTime(efield.value,efield.remark);
			var cell=FA.getWeCellEfield(efield,input);
			cell.append("<div class='fe_cell_ft'></div>");
			//container.append(cell);
			return cell;
		}
	}),
	FE.getFieldHandler({
		type:"TEXTS",
		doHandleField:function(efield,container){
			var texts =FA.getWeTextarea(efield.value,efield.remark,3);
			//container.append(FA.getWeCellEfield(efield,texts,true));
			return FA.getWeCellEfield(efield,texts,true);
		}
	}),
	FE.getFieldHandler({
		type:"NOBIND",
		doHandleField:function(efield,container){
			var input =FA.getWeInputText(efield.value,efield.remark);
			//container.append(FA.getWeCellEfield(efield,input));
			return FA.getWeCellEfield(efield,input);
		}
	}),
	FE.getFieldHandler({
		type:"DIVIDER",
		doHandleField:function(efield,container){
			var div = FA.getCellDivider(efield.cname);
			//container.append(div);
			return div;
		}
	})
]

