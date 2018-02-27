var Flow = (function($) {
	/* 生成控件标识 */
	var CTRL_FORM = "form";//表单
	var CTRL_FIELD = "field";//表单域
	var CTRL_MR_ADD = "mradd";//多行表单添加按钮
	var CTRL_MR_BLOCK = "mrblock";//多行添加块
	var CTRL_MR_DEL = "mrdel";//多行表单删除按钮
	var CTRL_MR_FIELD = "mr";//多行表单域
	var CTRL_ACTION = "action";//动作
	
	/* 操作按钮标识 */
	var SFORMLISTACTION="SFL";//表单列表按钮标识
	var RATIFYACTION="RFY";//办理过程按钮标识
	var SAVEACTION="SAVE";//保存按钮标识
	var ATTACHACTION="ATTACH";//附件列表按钮
	var OTHER="OTHER";//转发
	
	/* 提示信息 */
	var TIPS_LOADING="加载中...";
	var TIPS_SAVING="保存中...";
	var TIPS_SENDING="发送中...";
	var TIPS_SIGNING="签收中...";
	var TIPS_SUCCESS_SEND="发送成功";//20161102去掉该提示
	var TIPS_ERROR_SEND="发送失败";
	var TIPS_SUCCESS_UPLOAD="上传成功";
	var TIPS_ERROR_UPLOAD="上传失败";
	var TIPS_SUCCESS_SAVE="保存成功";
	var TIPS_ERROR_SAVE="保存失败";
	var TIPS_GET_SELECT_ACTOR="正在获取办理人";
	var TIPS_SELECT_ACTOR="请选择办理人";
	var TIPS_SAVE_FORM="请保存表单";
	var TIPS_FIELD_EMPTY="请填写";
	var TIPS_NO_ATTACH="无相关附件";
	var TIPS_NO_CURRENTATTACH="无相关附件";
	
	/* 全局参数 */
	var FLOWNAME;
	var DBNAME;
	var FLOWID;
	var STATUS;
	var CREATETIME;
	var DEPTID;
	var DEPTNAME;
	var APPLYKIND;
	var FLOWSN;
	var ORGID;
	var ORGNAME;
	var USERID;
	var USERNAME;
	var ROOTURL;
	var PREACTIONRESULT;
	var CURRENTSTEPID;
	var CURRENTSTEPNAME;
	var T;
	var M;
	var BROWSERURL;
	var COMMONWORD;
	var DOWITHATTACH;
	var TOKEN;
	
	var ratifyopMap={};//存放ratifyId和多行意见内容的map,key=ratifyId value=内容（简单意见模式下，初始化办理过程使用）
	var ratifySignatureMap={};//存放ratifyId和手写签批base64code的map,key=ratifyId value=base64code（简单意见模式下，初始化办理过程使用）
	//pkFieldMap和fieldMap都是存放表单域对象的map（流程在初始化表单时，往map中放表单域对象）
	var pkFieldMap={};//存放多行表单和多行意见表单的主键表单域对象的map容器，key=表单域控件ID value=主键表单域对象（用于拟办第一次保存后返回flowsn给主键表单域赋值，多行表单删除操作后相关的主键表单域也要删除）
	var fieldMap = {};//存放表单域对象的map容器，key=表单域控件ID value=表单域对象（用于保存表单时，验证赋值使用）
	var mrFormMap = {};//存放多行表单模板的map容器，key=formId value=多行表单的一组表单域集合（用于多行表单添加一个块时作为模板数据使用）
	var flowsnfields=[];//存放flowsn表单域的集合
	
	var forms;//表单集合对象
	var attaches;
	var subject;//主题值
	var opTimeFieldCtrls=[];//如果当前环节需要填写意见，存放意见日期控件
	var isNeedSign=false;//是否需要签收
	var isSave=false;//是否点击了保存按钮
	var isSimpleMode=false;//是否为简单表单模式
	var formSize=0;//表单个数，用来描述导航上有多少个表单
	var formLoadingIndex=0;
	
	var obj = {
		init : function() {
			Flow.getFlowCtrl(Flow.initFlowCtrl);
		},
		/**
		 * 初始化全局参数
		 */
		initGlobalParams : function(flowCtrl) {
			Flow.subject=flowCtrl.subject;
			Flow.APPLYKIND=flowCtrl.applyKind;
			Flow.COMMONWORD=flowCtrl.commonWord;
			Flow.DOWITHATTACH=flowCtrl.doWithAttach;
			Flow.DBNAME=flowCtrl.dbName;
			Flow.PREACTIONRESULT=flowCtrl.preActionResult;
			Flow.FLOWNAME=flowCtrl.flowName;
			Flow.FLOWSN=flowCtrl.flowsn;
			Flow.FLOWID=flowCtrl.flowId;
			Flow.RATIFYID=flowCtrl.ratifyId;
			Flow.CURRENTSTEPID=flowCtrl.currentStepId;
			Flow.CURRENTSTEPNAME=flowCtrl.currentStepName;
			Flow.DEPTID=flowCtrl.currentAccount.deptId;
			Flow.DEPTNAME=flowCtrl.currentAccount.deptName;
			Flow.ORGID=flowCtrl.currentAccount.orgId;
			Flow.ORGNAME=flowCtrl.currentAccount.orgFullName;
			Flow.USERID=flowCtrl.currentAccount.userId;
			Flow.USERNAME=flowCtrl.currentAccount.userName;
		},
		/**
		 * 初始化报件信息
		 */
		initFlowCtrl:function(flowCtrl){
			Flow.initFlowCtrlBefore(flowCtrl);
			Flow.initGlobalParams(flowCtrl);
			Flow.handleSign(flowCtrl.codeNodes);
			Flow.initAttachs(flowCtrl);
			Flow.initMode(flowCtrl);
			Flow.handleFormTab();
			Flow.initActions(flowCtrl);//初始化动作列表
			Flow.initProcesses(flowCtrl.processes);
			Flow.initFlowCtrlAfter(flowCtrl);
			Flow.hideLoadingToast();
			Flow.endfirstLoading();
		},
		/**
		 * 根据各种模式初始化
		 */
		initMode:function(flowCtrl){
			if(Flow.getInitMode()){
				if(Flow.getInitMode()==1){//表单使用平台配置开发
					if(Flow.isNeedSign){
						//如果有签收，禁用所有控件
						$.each(flowCtrl.forms,function(i, form) {
				            var formFieldsList = form.formFieldsList;
				            if(formFieldsList){
				              $.each(formFieldsList,function(i,formFields){
				                if(formFields){
				                  $.each(formFields,function(i,formField){
				                    formField.readOnly=true;
				                    formField.required=false;
				                  });
				                }
				              });
				            }
				          });
					}
					Flow.initForms(flowCtrl.forms);//初始化表单
				}else if(Flow.getInitMode()==2){//表单自定义开发
					Flow.saveForms=function(save_success){
						if(Flow.saveBefore() && save_success){
							save_success();
						}
					}
				}
			}else{
				isSimpleMode=true;
				Flow.initSimpleForms(flowCtrl);//初始化表单（只填写意见版本）
			}
		},
		/**
		 * 处理表单选项卡，大于等于2个表单时才显示
		 */
		handleFormTab:function(){
			if(formSize<=1){
				$("#forms_tabselect").hide();
				$("#forms_main").css("top","0");
			}
			//如果有多个表单，则优先切换至具有必填表单域控件的表单
			var ctrls=$("#forms").find("[r=true]");
			if(ctrls && ctrls.length>0){
				var ctrl=ctrls[0];
				var tab = $(ctrl).parents("div[tab]");
				if(tab){
					var index=tab.attr("tab");
					if(index){
						Flow.tabForm(index);
					}
				}
			}
		},
		/**
		 * 初始化附件全局变量Flow.attaches
		 */
		initAttachs:function(flowCtrl){
			var ass=flowCtrl.attaches;
			if(ass && ass.length>0){
				Flow.attaches=ass;
			}
		},
		/**
		 * 附件名称特殊字符处理
		 */
		URLencode:function(str){
			return str.replace(/\%/g,'%25').replace(/\+/g, '%2B').replace(/\#/g, '%23').replace(/\&/g,'%26');
		},
		/**
		 * 获取流程数据
		 */
		getFlowCtrl:function(func){
				Flow.firstLoading();
				var fc = $("#FlowCtrl").text();
				var userId=$("#UserId").text();
				var signRatifyId=$("#SignRatifyId").text();
				Flow.STATUS = $("#Status").text();
				Flow.ROOTURL=$("#Url").text();
				Flow.T=$("#T").text();
				Flow.M=$("#M").text();
				Flow.BROWSERURL=$("#BrowserUrl").text();
				var data={
						"flowCtrlStr":fc,
						"signRatifyId":signRatifyId,
						"status":Flow.STATUS,
						"userId":userId,
						"t":Flow.T
				}
				Flow.mtpost(Flow.ROOTURL+"/getFlowCtrl",data,function(result){
					if(result){
						func(result);
					}
				});
		},
		/**
		 * 初始化办理过程弹出框数据
		 */
		initProcesses:function(processes){
			if(processes){
				var process_list=$("#dialog_process_content_list");
				var process_temp=process_list.find("[name=process_temp]");
				$.each(processes,function(i,process){
					var clone=process_temp.clone().show();
					var process_icon=clone.find("[name=process_icon]");
					var process_stepName=clone.find("[name=process_stepName]");
					var process_userName=clone.find("[name=process_userName]");
					var process_doDate=clone.find("[name=process_doDate]");
					var process_actionResultCn=clone.find("[name=process_actionResultCn]");
					process_stepName.text(process.stepName);
					if(process.userName==null){
						process.userName="";
					}
					process_userName.text("办理人："+process.userName);
					//处理日期，之所以用以下方式处理，是应对ios系统显示日期时出现的NaN  
					var getDate=process.getDate;
			          if(getDate){
			        	getDate = getDate.replace(/\-/g, "/");  
			            getDate=new Date(getDate);
			            var month=getDate.getMonth()+1;
			            var day = getDate.getDate(); 
						var hour = getDate.getHours(); 
						getDate=month+"-"+day+" "+hour+"时"; 
						process_stepName.text(process.stepName+"["+getDate+"]");
					}
					var doDate=process.doDate;
					if(doDate){
						doDate = doDate.replace(/\-/g, "/");  
						doDate=new Date(doDate);
						var month=doDate.getMonth()+1;
						var day = doDate.getDate(); 
						var hour = doDate.getHours(); 
						doDate=month+"-"+day+" "+hour+"时"; 
						process_doDate.text("["+doDate+"]");
					}else{
						process_userName.css("float","none");
					}
					process_actionResultCn.text("办理结果："+process.actionResultCn);
					if(process.ratifyId==Flow.RATIFYID){
						process_icon.attr("style","border: .2em solid #04BE02;");
						clone.attr("style","color: #000000; display:block;");
					}
					process_list.append(clone);
				});
			}else{
				$("#dialog_process_content").append("<div name='noratifylog' style='color:#cbcbcb; padding-top:20px;'>您当前没有办理过程</div>");
			}
		},
		/**
		 * 初始化操作按钮
		 */
		initActions : function(flowCtrl) {
		 	var actions= flowCtrl.codeNodes;
		 	if(actions){
		 		Flow.initActionsBefore(actions);
		 	}
			$("#actions").parent().show();
			//操作按钮处三块区域
			var cellone=$("#actions").children()[0];
			var celltwo=$("#actions").children()[1];
			var cellthree=$("#actions").children()[2];
			var div=$("#actions");
			var acs=[];
			//拟办和待办，第一块和第二块区域分别显示保存和流转按钮
			if(Flow.STATUS=="W" || Flow.STATUS=="H"){
				var a=Flow.getActionWeuiButton(CTRL_ACTION + SAVEACTION,"保存");
				$(cellone).append(a);
				a.click(function(){
					isSave=true;
					Flow.saveForms();
				});
				var other=Flow.getActionWeuiButton(CTRL_ACTION + OTHER,"流转");
				var others={title:"选择操作",actions:acs};
				other.click(function(){
					$.actions(others);
				});
				$(celltwo).append(other);
			}
			if (actions) {
				var isSign=false;
				var signText;
				$.each(actions, function(i, action) {
					if(action.value=="2" || action.value=="3" || action.value=="4"){
						signText=action.name;
						isSign=true;
					}else{
						//为底部流转按钮点击弹出框添加按钮
						var ac={
							text:action.name,
							onClick:function(){
								isSave=false;
								Flow.saveForms(save_success);
								function save_success(){
									Flow.handleSelStep(action.value);
								}
							}
						};
						acs.push(ac);
					}
				});
				if(isSign){
					//如果上面遍历时找到签收按钮，则此处初始化签收按钮
					if(signText){
						Flow.initSignButton(signText);
					}else{
						Flow.initSignButton("签收");
					}
					Flow.isNeedSign=true;
				}else{
					Flow.isNeedSign=false;
				}
			}
			//初始化按钮扩展，目前主要应用在图形查看这块
			var actionexts= flowCtrl.actionExts;
			if(actionexts){
				Flow.initActionExts(actionexts,acs);
			}
			Flow.initActionsAfter(acs);
			if(Flow.getInitMode()){
				if(Flow.STATUS=="F" || Flow.STATUS=="E"){
					//如果是办结或者已办报件，则底部只给他办理过程按钮
					Flow.initActionsOneButton("办理过程");
					$("#actions").click(function(){
						$('#dialog_process').show();
					});
				}else{
					var ratac=Flow.getActionWeuiButton(CTRL_ACTION+ATTACHACTION,"办理过程");
					ratac.click(function(){
						$('#dialog_process').show();
					});
					$(cellthree).append(ratac);
				}
				$("#dialog_process_close").click(function(){
					$('#dialog_process').hide();
				});
			}else{
				//var attaches=Flow.getActionWeuiButton(CTRL_ACTION+ATTACHACTION,"附件列表");//早期附件列表是底部有个附件按钮点击后弹出框显示
				var as=flowCtrl.attaches;
				//现版本将附件改成放在主页可展开显示
				if (as && as.length>0) {
					$("#simpleforms_access_attaches").click(function(){
						if($("#simpleforms_attaches").is(":visible")){
							$("#simpleforms_attaches").hide();
							$("#simpleforms_access_attaches").find(".weui_cell_ft").attr("class","weui_cell_ft weui_cell_ft_rotate_90");
						}else{
							$("#simpleforms_attaches").show();
							$("#simpleforms_access_attaches").find(".weui_cell_ft").attr("class","weui_cell_ft weui_cell_ft_rotate90");
						}
					});
					$.each(as,function(i,attach){
						if (attach.attachFile) {
							var down=Flow.getAttachAlink(attach);
							var p=$("<p style='margin:5px 0;'></p>").append(down);;
							$("#simpleforms_attaches").append(p);
						}
					});
				}else{
					$("#simpleforms_access_attaches").hide();
				}
			}
		},
		/**
		 * 如果操作按钮中带签收，则Flow.isNeedSign=true; 该属性后续会使用到
		 */
		handleSign:function(actions){
			if(actions){
				$.each(actions, function(i, action) {
					if(action.value=="2" || action.value=="3" || action.value=="4"){
						Flow.isNeedSign=true;
						return false;
					}
				});
			}
		},
		/**
		 * 初始化按钮扩展，目前主要应用在图形查看这块
		 */
		initActionExts:function(actionexts,acs){
			$.each(actionexts,function(i,ae){
				switch(ae.actionCode)
				{
					case "FA_ONEMAP":
						var action=$("#actions").children()[1];
						if(!action){
							action=$("#actions").children()[0];
						}
						if(action){
							var ac=Flow.getActionWeuiButton(null,ae.name);
							ac.click(function(){
								var obj = $.parseJSON(ae.param);
								if(obj){
									try {
										obj.typeName=(obj.typeName=="Flow.APPLYKIND"?Flow.APPLYKIND : obj.typeName) ;
										obj.flowsn=(obj.flowsn=="Flow.FLOWSN"?Flow.FLOWSN : obj.flowsn);
										obj.titleName=(obj.titleName=="Flow.subject"?Flow.subject :obj.titleName);
							            window.mobile_x.toOneMapAnalysis(obj.applyKind,obj.titleName,obj.typeName,obj.flowsn,obj.analysisId,obj.parameJson,obj.coordinateJson);
							          } catch (e) {
							            window.webkit.messageHandlers.toOneMapAnalysis.postMessage(
							                { "applyKind": "一张图" ,
							                  "titleName": Flow.subject,
							                  "typeName":Flow.APPLYKIND,
											  "flowsn":Flow.FLOWSN,
											  "analysisId":"","parameJson":"","coordinateJson":""});
									}
								}
							});
							var div=$("<div class='action'></div>").append(ac);
							$(action).after(div);
						}
					  break;
					default:
						break;
				}
			});
		},
		/**
		 * 初始化签收按钮
		 */
		initSignButton:function(text){
			Flow.initActionsOneButton(text);
	        $("#actions").click(function(){
	        	if(Flow.signBefore()){
	        		var url=Flow.ROOTURL+"/sign?ratifyId="+Flow.RATIFYID+"&userId="+Flow.USERID+"&userName="+Flow.USERNAME+"&t="+Flow.T+"&dbName="+Flow.DBNAME+"&flowName="+Flow.FLOWNAME+"&subject="+Flow.subject;
					Flow.showLoadingToast(TIPS_SIGNING);
					Flow.mtpost( url ,null,function(result){
						if(result){
							var ratifyId=$.parseJSON(result);
							var href =location.href+"&signRatifyId="+ratifyId;
							 location.replace(href);
						}
						Flow.hideLoadingToast();
					});
	        	}
			});
		},
		initActionsOneButton:function(text){
			$("#actions").empty();
			$("#actions").append("<div actionsOneButton=true class='button_boot' style='font-size:1em; line-height:30px; height:30px; width:100%'>"+text+"</div>");
		},
		/**
		 * 处理保存表单时，更新填写的意见控件对应的时间控件的值为当前时间（该方法暂时不用）
		 */
		handleOpTimeCtrl:function(){
			$.each(opTimeFieldCtrls,function(i,ctrl){
				var fieldType=ctrl.attr("fieldType");
				if(fieldType=="5"){
					ctrl.val(new Date().Format("yyyy-MM-dd HH:mm:ss"));
				}else if(fieldType=="4"){
					ctrl.val(new Date().Format("yyyy-MM-dd"));
				}
			});
		},
		/**
		 * 保存表单
		 */
		saveForms:function(func_save_success){
			if(isSimpleMode){
				$("#simpleforms").slideDown();$("#"+CTRL_ACTION + SFORMLISTACTION).text("表单列表"); 
			}
			//Flow.handleOpTimeCtrl();
			var flag=true;
			var subjectFormField;
			var tab;
	        if(Flow.getInitMode()){
	        	//如果是公司OA流程那种的全开发模式，则保存表单，验证之前，需要将所有的表单显示出来。验证完后，再将相应的表单隐藏掉。
	        	tab= $("#forms_tab").children("option:selected").val();
				$("#forms_main [tab]").show();
			}
	        //验证必填且可视的表单域控件是否有值
			$.each(fieldMap, function(i, obj) {
				if(obj.name=="TXTSUBJECT"){
					subjectFormField=obj;
				}
				var value = $("#" + i).val();
				if(obj.required){
					//有些表单域是隐藏的但是又是必填的，可以这里只验证可视且必填的控件
					if($("#" + i).is(":visible[r=true]") &&(value==null || value=="" || $.trim(value)=="")){
						flag=false;
						Flow.showToast(TIPS_FIELD_EMPTY+obj.cname, "cancel");
						return false;
					}
				}
			});
	        if(Flow.getInitMode()){
	        	//隐藏相应的表单
	        	$("#forms_main [tab]").hide();
				$("#forms_main [tab="+tab+"]").show();
			}
			if(flag){
				if(Flow.saveBefore()){
					$.each(fieldMap, function(i, obj) {
						var value = $("#" + i).val();
						if(obj.required){
							if(!($("#" + i).is(":visible[r=true]") &&(value==null || value==""))){
								obj.value=value;//给必填的表单域对象赋值
							}
						}
						else{
							var pkField=pkFieldMap[i];
							if((!pkField || !pkField.value) && obj.remark!="FLOWSN"){
								//20161025保存多次，多行表单会出现多条记录的bug处理
								obj.value = value==null?"":value;//给非必填的表单域对象赋值
							}
						}
					});
					if(isSave){
						Flow.showLoadingToast(TIPS_SAVING);
					}else{
						Flow.showLoadingToast(TIPS_GET_SELECT_ACTOR);
					}
			    	if(!isSimpleMode){
			    		var subjectCtrl = $("div[ismain=true]").find("[name=TXTSUBJECT]");
				    	Flow.subject=subjectCtrl.val()?subjectCtrl.val():"";//Flow.USERNAME+" "+subjectCtrl.val();
				    	Flow.saveHandleSubject();
			    	}
			    	if(subjectFormField){
			    		subjectFormField.value=Flow.subject;//给标题表单域对象赋值
			    	}
/*			    	var signObjs=[];
			    	var signs=$("[SignatureContent]");
			    	if(signs){
			    		$.each(signs,function(i,sign){
			    			var obj={
			    				id:$(sign).attr("SignatureId"),
			    				opId:$(sign).attr("SignatureOpId"),
			    				content:$(sign).attr("SignatureContent")
			    			}
			    			signObjs.push(obj);
			    		});
			    	}*/
			    	var data = {
			    			//signs:JSON.stringify(signObjs),
							forms : JSON.stringify(Flow.forms),
							flowId:Flow.FLOWID,
							flowsn:Flow.FLOWSN,
							userId:Flow.USERID,
							userName:Flow.USERNAME,
							deptId:Flow.DEPTID,
							deptName:Flow.DEPTNAME,
							flowName:Flow.FLOWNAME,
							subject:Flow.subject,
							t:Flow.T,
							m:Flow.M,
							dbName:Flow.DBNAME,
							stepId:Flow.CURRENTSTEPID
						};
					if(!Flow.getInitMode()){
						data.opMode=true;
					}
					//保存表单数据
					Flow.mtpost(Flow.ROOTURL+"/save",data,function(result){
						if(result){
							var r=result;
							var saveSuccess=r.saveSuccess;
							if(saveSuccess){
								var ratifyId=r.ratifyId;
								var flowsn=r.flowsn;
								var map=r.result;
								var processes=r.processes;
								
								if(map.length>0){
									$.each(map,function(i,obj){
										//保存成功后为主键表单域赋值
										$.each(pkFieldMap,function(j,field){
											if(!field.value && j==obj.ctrlId){
													field.value=obj.value;
													return false;
											}
										});
									});
								}
								if(flowsn && ratifyId){
									//保存表单后返回的flowsn和ratifyId有值，但是全局变量Flow.FLOWSN与Flow.RATIFYID无值的情况，通常是拟办流程保存表单时才会发生的
									if(!Flow.FLOWSN && !Flow.RATIFYID){
										Flow.FLOWSN=flowsn;
										$.each(flowsnfields,function(i,obj){
											obj.value=flowsn;
										});
										Flow.RATIFYID=ratifyId;
									}
								}
								if(processes){
									//保存成功后重新初始化办理过程弹出框信息
									var ctrl=$("#dialog_process_content").find("[name=noratifylog]");
									if(ctrl){
										ctrl.empty();
									}
									Flow.initProcesses(processes);
								}
								if(map){
									Flow.saveAfter();
									if(func_save_success){
										func_save_success();
									}
								}
								if(isSave){
									Flow.hideLoadingToast();
									Flow.showToast(TIPS_SUCCESS_SAVE);
								}
							}else{
								Flow.hideLoadingToast();
								Flow.showToast(TIPS_ERROR_SAVE, "cancel");
							}
						}
					},function(){
						Flow.hideLoadingToast();
						Flow.showToast(TIPS_ERROR_SAVE, "cancel");
					});
				}
			}
		},
		/**
		 * 初始化简单表单（简单）
		 */
		initSimpleForms:function(flowCtrl){
			var forms=flowCtrl.forms;
			var eformId;
			$("#simpleforms").show();
			Flow.initFormsBefore(forms);
			if (forms) {
				if(forms.length<=0){
					$("#simpleforms_cell_sender").css("border-top","none");
				}
				Flow.initFormsTab();
				Flow.forms=forms;
				//初始化首页意见表单
				$.each(forms,function(i, form) {
					if(form.type==0 && form.remark=="OP" && Flow.STATUS=="W"){
						//初始化单行意见填写表单
						Flow.initSimpleOneOpinion(form);
					}else if(form.type==2){
						//初始化多行意见表单
						Flow.initSimpleOpinion(form);
					}
				});
				if(!Flow.customForm()){//如果不是自定义表单，则表单列表中的表单由表单引擎生成
					//初始化选项卡表单
					$.each(forms,function(i, form) {
						var formFieldsList = form.formFieldsList;
						if(formFieldsList){
							$.each(formFieldsList,function(i,formFields){
								if(formFields){
									$.each(formFields,function(i,formField){
										formField.readOnly=true;
										formField.required=false;
									});
								}
							});
						}
					});
					Flow.initForms(forms);
				}
			}
			Flow.initSimpleInfo(flowCtrl,eformId);
			Flow.initFormsAfter(forms);
		},
		/**
		 * 初始化简单流程相关信息（简单）
		 */
		initSimpleInfo:function(flowCtrl,eformId){
			$("#simpleforms_title").text(flowCtrl.subject);//初始化标题
			if(Flow.STATUS=='F' || Flow.STATUS=='E'){
				$("#simpleforms_cell_sender").css("border-top","none");
			}
			$("#simpleforms_sender").text(flowCtrl.sender);//初始化发送人
			$("#simpleforms_sendtime").text(flowCtrl.getDate);//初始化发送时间
			$("#simpleforms_access_ratify").click(function(){//处理办理过程点击事件，控制办理过程列表展开与隐藏
				if($("#simpleforms_ratifylogs").is(":hidden")){
					$("#simpleforms_access_ratify").find(".weui_cell_ft").attr("class","weui_cell_ft weui_cell_ft_rotate90");
					$("#simpleforms_ratifylogs").show();
				}else{
					$("#simpleforms_access_ratify").find(".weui_cell_ft").attr("class","weui_cell_ft weui_cell_ft_rotate_90");
					$("#simpleforms_ratifylogs").hide();
				}
			});
			if(flowCtrl.processes){
				Flow.initSimpleRatifylog(flowCtrl.processes);//初始化办理过程列表数据
			}
			var formList=Flow.getActionWeuiButton(CTRL_ACTION + SFORMLISTACTION,"表单列表");
			formList.click(function(){
				Flow.toggleFormList(formList);
			});
			var cellthree=$("#actions").children()[2];
			if(Flow.STATUS=="W"){
				$(cellthree).append(formList);
			}else{
					Flow.initActionsOneButton("表单列表");
					var button=$("#actions").find("[actionsonebutton=true]");
					$("#actions").click(function(){
						if(button){
							Flow.toggleFormList(button);
						}
					});
			}
		},
		/**
		 * 处理表单显示时，底部表单按钮显示返回；表单隐藏时，底部表单按钮显示表单列表
		 */
		toggleFormList:function(ctrl){
			$("#simpleforms").slideToggle(function(){
				if(ctrl.text()=="返回"){
					ctrl.text("表单列表");
				}else{
					ctrl.text("返回");
				}
			});
		},
		/**
		 * 初始化简单意见模式的办理过程信息（简单）
		 */
		initSimpleRatifylog:function(processes){
			var simpleforms_ratifylogs=$("#simpleforms_ratifylogs");
			var currentStepMap={};
			var currentStepName=Flow.CURRENTSTEPNAME;
			if(currentStepName){
				currentStepMap.currentStepName="";
				$("#simpleforms_currentStep").text(Flow.CURRENTSTEPNAME);
			}else{
				$("#simpleforms_currentStep").parent().hide();
			}
			var simpleforms_ratifylog=simpleforms_ratifylogs.find("[name=simpleforms_ratifylog]");
			$.each(processes,function(i, process) {
				var simpleforms_ratifylog_clone=simpleforms_ratifylog.clone().show();
				if(process.ratifyId==Flow.RATIFYID && currentStepName && process.stepName==currentStepName){
					currentStepMap.currentStepName=simpleforms_ratifylog_clone;
				}
				var simpleforms_ratifylog_stepname=simpleforms_ratifylog_clone.find("[name='simpleforms_ratifylog_stepname']");
				var simpleforms_ratifylog_username=simpleforms_ratifylog_clone.find("[name=simpleforms_ratifylog_username]");
				var simpleforms_ratifylog_content=simpleforms_ratifylog_clone.find("[name=simpleforms_ratifylog_content]");
				var simpleforms_ratifylog_result=simpleforms_ratifylog_clone.find("[name=simpleforms_ratifylog_result]");
				
				simpleforms_ratifylogs.append(simpleforms_ratifylog_clone);
				simpleforms_ratifylog_stepname.text(process.stepName);
				if(process.userName==null){
					process.userName="";
				}
				if(process.actionResultCn==null){
					process.actionResultCn="";
				}
				simpleforms_ratifylog_username.text("办理人："+process.userName);
				simpleforms_ratifylog_result.text("办理结果："+process.actionResultCn);
				if(process.content){
					$.each(process.content,function(i,obj){
						if(obj.remark=="OPINION_CONTENT"){
							var p=$("<p>"+obj.cname+"："+obj.value+"</p>");
							simpleforms_ratifylog_content.append(p);
						}
					});
				}else{
					if(ratifyopMap[process.ratifyId]){
						simpleforms_ratifylog_content.text("意见："+ratifyopMap[process.ratifyId]);
					}
					if(ratifySignatureMap[process.ratifyId]){
						var img=$("<img></img>");
						img.attr("src", "data:image/png;base64,"+ratifySignatureMap[process.ratifyId]);
						img.css("max-height","150px");
						simpleforms_ratifylog_content.append("<br>").append(img);
					}
				}
			})
			if(currentStepMap && currentStepMap.currentStepName){
				var clone=currentStepMap.currentStepName;
				clone.css("color","#000000");
				var icon=clone.find(".flowIcon");
				icon.css("border",".2em solid #04BE02");
			}
		},
		/**
		 * 初始化简单单行意见表单（简单）
		 */
		initSimpleOneOpinion:function(form){
			var formFieldsList=form.formFieldsList;
			if(formFieldsList && formFieldsList[0]){
				var needWriteOp=false;
				//循环检测单行意见表单的表单域列表
				$.each(formFieldsList[0],function(i,formField){
					if(formField.onerowop){
						//需要验证的意见表单域
						if(formField.onerowop=="CONTENT" && formField.required){
							needWriteOp=true;//需要填写意见
							var fieldCtrlId = form.id + CTRL_FIELD + formField.id;// 表单域控件Id
							if(!fieldMap[fieldCtrlId]){
								fieldMap[fieldCtrlId] = formField;// 容器装对象
								formField.ctrlId=fieldCtrlId;
							}
							var contentField = fieldMap[formField.ctrlId];
							if(contentField){
								$("#"+formField.ctrlId).attr("id","");
								contentField.required=true;
							}
							Flow.initOpinionToolbar(contentField,null,Flow.FLOWSN,Flow.T,Flow.DBNAME,contentField.name);
						}
					}
				});
				if(!needWriteOp){
					$("#simpleforms_cell_sender").css("border-top","none");
				}
			}
		},
		/**
		 * 初始化简单多行意见表单（简单）
		 */
		initSimpleOpinion:function(form){
			var formFieldsList=form.formFieldsList;
			if(formFieldsList){
				var formFields_edit;
				var formFields_opi;
				$.each(formFieldsList,function(opi, opformFields) {
						if (opformFields != null) {
							var ratifyId;
							var content;
							var signature;
							$.each(opformFields,function(i,opFormField) {
								if (opFormField.remark == "OPINION_CONTENT") {//处理意见内容
									content=opFormField.value;
									if (opFormField.required) {
										//将需要编辑的这一组意见表单域抽取出来
										formFields_edit=opformFields;
										formFields_opi=opi;
										return false;
									}
								}else if(opFormField.remark == "OPINION_SIGNATURE"){
									signature=opFormField.value;
								}else if(opFormField.remark == "RATIFYID"){
									ratifyId=opFormField.value;
								}
							});
							if(ratifyId){
								if(content){
									ratifyopMap[ratifyId]=content;//存放各个办理过程对应的意见值
								}
								if(signature){
									ratifySignatureMap[ratifyId]=signature;//存放各个办理过程对应的手机签批图片内容
								}
							}
						}
				});
				if(formFields_edit){
					Flow.initSimpleEditOpinion(form,formFields_edit,formFields_opi);
				}else{
					$("#simpleforms_cell_sender").css("border-top","none");
				}
			}
		},
		/**
		 * 初始化简单意见填写框（简单）
		 */
		initOpinionToolbar:function(formField,signatureField,flowsn,t,dbName,opId){
			var agree=Flow.getActionWeuiButton("","同意");
			agree.attr("name","optoolbar_agree");
			var disagree=Flow.getActionWeuiButton("","不同意");
			disagree.attr("name","optoolbar_disagree");
			disagree.css("margin-bottom","3px");
			var simpleforms_editopinion = $("#simpleforms_editopinion").show();
			simpleforms_editopinion.insertAfter($("#simpleforms_title"));
			var simpleforms_editopinion_buttons=$("<div name='optoolbar_buttons' style='margin: 5px 0;'></div>");
			simpleforms_editopinion.append(simpleforms_editopinion_buttons);
			simpleforms_editopinion_buttons.append(agree).append(disagree);
			var textarea=Flow.getTextarea(formField.ctrlId,3);
			textarea.attr("placeholder","请输入(必填)");
			textarea.attr("name",formField.name);
			textarea.attr("optoolbar_op",true);
			textarea.attr("r",true);
			textarea.val(formField.value);
			textarea.insertAfter(simpleforms_editopinion_buttons);
			var commonButton=Flow.getCommonWordButton(textarea);//常用语
			commonButton.attr("name","optoolbar_commonword");
			simpleforms_editopinion_buttons.append(commonButton);
			agree.click(function(){
				textarea.val("同意。");
			});
			disagree.click(function(){
				textarea.val("不同意。");
			});
			var select = commonButton.find("select");
			select.change(function(){
				textarea.val(select.val());
			});
			if(signatureField){//初始化手写签批控件
				Flow.initOpinionSignature(simpleforms_editopinion_buttons,signatureField,formField);
			}
			Flow.initOpinionToolbarAfter(simpleforms_editopinion_buttons,textarea,formField,signatureField);
		},
		initOpinionSignature:function(simpleforms_editopinion_buttons,signatureField,opField){
			var base64code=signatureField.value;
			if(base64code){
				signatureField.required=true;
				//如果有手写签批图片存在，则意见填写框设置为不必填
				if(opField.required){
					opField.required=false;
					$("#"+opField.ctrlId).attr("placeholder","");
				}
				var imgcode="data:image/png;base64,"+base64code;
				var img=$("<img></img>");
				img.attr("src", imgcode);
				img.height("100");	
				simpleforms_editopinion_buttons.prepend("<br>").prepend(img);
			}
			//手写签批保存事件
			var func= function(){
				var imgcode= Paint.getDataURL($("#dialog_signature_content")); 
				var oldImg=simpleforms_editopinion_buttons.find("img").get(0);
				var nowImg=$("<img></img>");
				nowImg.attr("src",imgcode);
				nowImg.height("150");	
				if (oldImg) {
					$(oldImg).replaceWith(nowImg);
				}else{
					simpleforms_editopinion_buttons.prepend("<br>").prepend(nowImg);
				}
				//手写签批表单域控件赋值
				signatureField.required=true;
				var value=imgcode.substring(22,imgcode.length);
				$("#"+signatureField.ctrlId).val(value);
				//处理意见框为不必填
				opField.required=false;
				$("#"+opField.ctrlId).attr("placeholder","");
				$("#dialog_signature").hide(); 
			};
			var button=Flow.getSignatureCanvasButton(signatureField,func);
			simpleforms_editopinion_buttons.append(button);
		},
		/**
		 * 处理简单多行意见表单的表单域（简单）
		 */
		initSimpleEditOpinion:function(form,formFields_edit,formFields_opi){
			//处理需要编辑的意见表单域
			var contentField;
			var signatureField;
			var pkField;
			$.each(formFields_edit,function(i,opFormField) {
				var opFormFieldId = form.id+ CTRL_MR_FIELD+ formFields_opi+ "_"+ opFormField.id;
				opFormField.ctrlId=opFormFieldId;
				fieldMap[opFormFieldId] = opFormField;//容器装对象
				if (opFormField.remark == "OPINION_CONTENT") {//处理意见内容
					contentField=opFormField;
				}else if(opFormField.remark == "OPINION_SIGNATURE"){
					signatureField=opFormField;
					var hideInput = Flow.getInput(opFormFieldId)
					.attr("name",opFormField.name)
					.val(opFormField.value).hide();
					$("#simpleforms_editopinion").append(hideInput);
				}else if(opFormField.remark == "PK"){
					pkField=opFormField;
					var hideInput = Flow.getInput(opFormFieldId)
					.attr("name",opFormField.name)
					.val(opFormField.value).hide();
					$("#simpleforms_editopinion").append(hideInput);
					pkFieldMap[opFormFieldId] = opFormField;
				}else{
					var hideInput = Flow.getInput(opFormFieldId)
					.attr("name",opFormField.name)
					.val(opFormField.value).hide();
					$("#simpleforms_editopinion").append(hideInput);
				}
			});
			Flow.initOpinionToolbar(contentField,signatureField,Flow.FLOWSN,Flow.T,Flow.DBNAME,pkField.value);
		},
		/**
		 * 初始化表单
		 */
		initForms : function(forms) {
			Flow.initFormsBefore(forms);
			if (forms) {
				Flow.initFormsTab();
				Flow.forms=forms;//全局变量Flow.forms赋值
				$.each(forms,function(i, obj) {
					if (obj.pid == 0) {
						// 初始化表单选项卡
						var formDiv=Flow.getFormDiv(obj);
						formDiv.attr("tab",i);
						$("#forms").show();
						Flow.addTabForm( obj.name,formDiv,i);
						//主表的表单域列表先生成，之后生成该主表下所有子表的表单域，如果子表下还有子表，则按照上述的方式逐层生成
						Flow.initFormFields(obj, formDiv);// 根据表单类型,表单域控件需要根据类型生成
						Flow.initFormsDiv(forms, obj.id, formDiv);
					}
				});
			}
			Flow.initFormsAfter(forms);
		},
		/**
		 * 初始化表单选项卡
		 */
		initFormsTab:function(){
			var formsHeight=$("#forms").height();
			var tabHeight=$("#forms_tab").height();
			$("#forms_tab").change(function(){ 
				var tab=$(this).children("option:selected").val();
				$("#forms_main [tab]").hide();
				$("#forms_main [tab="+tab+"]").show();
			}) 
		},
		/**
		 * 生成子表单div，一个div对应一个数据表
		 */
		initFormsDiv : function(forms, pid, div) {
			var childForms = Flow.getChildForms(forms, pid);
			if (childForms.length > 0) {
				$.each(childForms, function(i, childForm) {
					var childFormDiv=Flow.getFormDiv(childForm);
					Flow.initFormFields(childForm, childFormDiv);
					div.append(childFormDiv);
					Flow.initFormsDiv(forms, childForm.id, childFormDiv);
				});
			}
		},

		/**
		 * 初始化一个表单中的表单域列表，表单div中的内容根据表单的类型来生成，表单域根据表单域的类型生成
		 */
		initFormFields : function(form, div) {
			div.attr("tableName",form.tableName);
			var formFieldsList = form.formFieldsList;
			if (formFieldsList != null) {
				if (form.type == 0) {
					Flow.initCommonForm(div,form);//普通表单的处理
				} else if (form.type == 1) {
					Flow.initMoreRowForm(div,form);//多行表单的处理
				} else if (form.type == 2) {
					Flow.initOpinionForm(div,form);//意见表单的处理
				}
			}
		},
		delFromMap : function(map, id) {
			delete map[id];
		},
		/**
		 * 初始化普通表单
		 */
		initCommonForm:function(div,form){
			//div-eformId对应的大div，一个eformId里面还有许多个由tableName组成的小div
			var formFieldsList=form.formFieldsList;
			if (formFieldsList[0].length > 0) {
				var table = $("<div class='weui_cells weui_cells_form' isMain=true tableName="+form.tableName+" eformId="+form.eformId+"></div>");
				$.each(formFieldsList[0], function(i, obj) {
					var fieldCtrlId = form.id + CTRL_FIELD + obj.id;// 表单域控件Id
					Flow.initFormField(fieldCtrlId, obj, table);
					fieldMap[fieldCtrlId] = obj;// 容器装对象
				});
				div.append(table);
				Flow.handleSpacingsAfterCell();
			}
		},
		handleSpacingsAfterCell:function(){
			var spas = $(".form_row_spacing");
			if(spas){
				$.each(spas,function(i,spacing){
					 var cell = $(spacing).next(".weui_cell");
			          if(cell){
			            cell.addClass("weui_cell_no_top_border");
			          }
				});
			}
		},
		/**
		 * 初始化简单表单（简单）
		 */
		initSimpleForm:function(form,index){
			var formFieldsList=form.formFieldsList;
			if (formFieldsList && formFieldsList[0].length > 0) {
				var formDiv=Flow.getFormDiv(form);
				formDiv.attr("tab",index);
				formDiv.addClass("weui_cells");
				$("#forms").show();
				Flow.addTabForm(form.name,formDiv,index);
				var row = $("body").find(".weui_cell[remark=simpleforms_row_temp]");
				$.each(formFieldsList[0], function(i, obj) {
					//表单域除了隐藏域其他显示
					if(obj.cname && obj.type!=0){
						var clone=row.clone();//.show().css("display","flex");
						if(Flow.getSimpleFormCellType()){
							clone.css("display","block");//单元格两行显示
						}
						clone.removeAttr("remark");
						formDiv.append(clone);
						var divs=clone.find("div");
						$(divs[0]).text(obj.cname);
						var fieldCtrlId = form.id + CTRL_FIELD + obj.id;// 表单域控件Id
						$(divs[1]).attr("id",fieldCtrlId);
						if(obj.type==7){
							//附件单独处理
							var eformId=form.eformId;
							var remark=obj.remark;
							var catalog="";
							if(remark){
								catalog=remark.substring(remark.indexOf("CATALOG=")+8,remark.length);
							}
							var attachCtrl=Flow.getAttach(fieldCtrlId,eformId,catalog);
							attachCtrl.attr("name",obj.name);
							$(divs[1]).append(attachCtrl);
						}else if(obj.type==10){
							var spacing=$("<div class='form_row_spacing'>"+obj.cname+"</div>");
							clone.replaceWith(spacing);
						}else{
							if(obj.value){
								$(divs[1]).text(obj.value);
							}
						}
					}
				});
			}
		},
		/**
		 * 获取多行表单每个块头部div
		 */
		getMoreRowTopDiv:function(text){
			var opDiv=$("<div class='form_row_spacing' style='display:flex; padding:5 15;'>");
			var topldiv=$("<div name='"+text+"' style='font-size:15px;line-height:27px;flex: 1;-webkit-box-flex: 1;-webkit-flex: 1; color:black'>"+text+"</div>");
			var toprdiv=$("<div style='color:black'></div>");
			opDiv.append(topldiv).append(toprdiv);
			return opDiv;
		},
		/**
		 * 初始化多行表单
		 */
		initMoreRowForm:function(div,form){
			div.attr("displayName",form.name);
			// 多行表单的处理
			var formFieldsList=form.formFieldsList;
			var mrIndex = 0;
			var opinionStepIds=form.opinionStepIds;
			var addButton=false;
			//如果是拟办或者待办，且当前环节多行表单具有添加事件，则给它显示新增按钮
			if(opinionStepIds && (Flow.STATUS=="W" || Flow.STATUS=="H")){
				var array=opinionStepIds.split(";");
				$.each(array,function(i,obj){
					if(obj==Flow.CURRENTSTEPID){
						addButton=true;
					}
				});
			}
			if(addButton){
				div.attr("addButton","true");
				var addButton=$("<div class='button_boot' style='margin:10px 15px;line-height:30px;height:30px; font-size:1em'>新增</div>");
				addButton.attr("id",CTRL_MR_ADD + form.id);
				addButton.attr("mr",CTRL_MR_ADD);
				addButton.attr("tableName",form.tableName);
				// 多行表单添加按钮事件
				addButton.click(function() {
					var mrformFields =$.extend(true, [], mrFormMap[form.id]); //mrFormMap[form.id];
					if (mrformFields != null) {
						Flow.addMRBlock(true,form.id, mrformFields, div, mrIndex);
						mrIndex++;
					}
				});
				div.append(addButton);
			}
			$.each(formFieldsList, function(mri, mrformFields) {
				if (mrformFields != null) {
					Flow.addMRFormFieldsModel(mrFormMap, form.id,
							mrformFields);// 初始化模型map
					Flow.addMRBlock(false,form.id, mrformFields, div, mrIndex);
					mrIndex++;
				}
			});
		},
		/**
		 * 初始化意见表单
		 */
		initOpinionForm:function(div,form){
			var formFieldsList=form.formFieldsList;
			formFieldsList.sort(function(a,b){
				var result=0;
				var ratifyFielda=Flow.getFieldByRemark(a,"RATIFYID");
				var ratifyFieldb=Flow.getFieldByRemark(b,"RATIFYID");
				if(ratifyFielda && ratifyFieldb && ratifyFielda.value && ratifyFieldb.value){
					result= ratifyFielda.value-ratifyFieldb.value;
				}
				return result;
			});
			var opTable = $("<div class='weui_cells weui_cells_form'></div>");
			$.each(formFieldsList,function(opi, opformFields) {
				if (opformFields != null) {
					var isWriteOp=true;//是否需要填写意见
					var opTimeFieldCtrl;
					var opDiv=$("<div remark='moreRowOpDiv'></div>");
					opTable.append(opDiv);
					var spacing=$("<div class='form_row_spacing'></div>");
					var hideDiv=$("<div style='display:none'></div>");
					opDiv.append(hideDiv);
					opDiv.append(spacing);
					$.each(opformFields,function(i,opFormField) {
						var opFormFieldId = form.id+ CTRL_MR_FIELD+ opi+ "_"+ opFormField.id;// 多行表单中表单域Id的处理
						opFormField.ctrlId=opFormFieldId;
						fieldMap[opFormFieldId] = opFormField;// 容器装对象
						if (opFormField.remark == "OPINION_CONTENT") {// 处理意见内容
							var tr = $("<div class='weui_cell'></div>");
							var leftTd = $("<div class='weui_cell_hd'><label class='weui_label'>"+opFormField.cname+"</label></div>");
							var rightTd = $("<div class='weui_cell_bd weui_cell_primary'></div>");
							tr.append(leftTd).append(rightTd);
							opDiv.append(tr);	
							var textarea = Flow.getTextarea(opFormFieldId,3);
							textarea.attr("name",opFormField.name);
							textarea.val(opFormField.value);
							if (!opFormField.required) {
								textarea.attr("disabled","disabled");
								isWriteOp=false;
							}else{
								textarea.attr("r",true);
								textarea.attr("placeholder","请填写意见(必填)");
							}
							rightTd.append(textarea);
						} else if (opFormField.remark == "USERNAME" || opFormField.remark == "OPINION_TIME") {// 处理意见填写人与时间
							var tr = $("<div class='weui_cell'></div>");
							var leftTd = $("<div class='weui_cell_hd'><label class='weui_label'>"+opFormField.cname+"</label></div>");
							var rightTd = $("<div class='weui_cell_bd weui_cell_primary'></div>");
							tr.append(leftTd).append(rightTd);
							var input = Flow.getWeInputText(opFormField.value,"");
							input.attr("id",opFormFieldId);
							input.attr("name",opFormField.name);
							input.attr("disabled","disabled");
							input.attr("fieldType",opFormField.type);
							rightTd.append(input);
							opDiv.append(tr);	
							if(opFormField.remark == "OPINION_TIME"){
								opTimeFieldCtrl=input;
							}
						}  else if(opFormField.remark == "PK"){
							pkFieldMap[opFormFieldId] = opFormField;
						}
						else {
							var hideInput = Flow.getInput(opFormFieldId,"weui_input");
							hideInput.attr("name",opFormField.name);
							hideInput.val(opFormField.value);
							hideInput.hide();
							hideDiv.append(hideInput);
							//spacing.append(hideInput);
							if (opFormField.remark == "STEPNAME") {
								opDiv.attr("stepName",opFormField.value);
								//spacing.attr("stepName",opFormField.value);
								spacing.append(opFormField.value);
							}else if(opFormField.remark == "STEPID"){
								opDiv.attr("stepId",opFormField.value);
							}else if(opFormField.remark == "RATIFYID"){
								opDiv.attr("ratifyId",opFormField.value);
							}
						}
					});
					if(isWriteOp){
						opTimeFieldCtrls.push(opTimeFieldCtrl);
					}
				}
			});
			div.append(opTable);
		},
		/**
		 * 添加一个多行表单的块（块包括删除按钮和单行表单编辑框）
		 */
		addMRBlock : function(flag,formId, mrformFields, div, mrIndex) {
			var mrFieldss=Flow.getFormById(formId).formFieldsList;
			if(flag){
				mrFieldss.push(mrformFields);
			}
			var attr_tableName=div.attr("tableName");
			var attr_eformId=div.attr("eformId");
			var blockTable = $("<div class='weui_cells weui_cells_form'></div>");
			var delButton2;
			var addBlock = $("<div id=" + formId + CTRL_MR_BLOCK + mrIndex
					+ " style='width:100%' remark='addBlock'></div>");
			addBlock.attr("blockTableName",attr_tableName);
			addBlock.attr("blockEformId",attr_eformId);
			var addButton=div.attr("addButton");
			var displayName=$(div).attr("displayName");
			var mrtop=Flow.getMoreRowTopDiv(displayName);
			addBlock.append(mrtop);
			var aBlocks=$(div).find("[remark=addBlock]");//如果还没有添加的块存在，就不添加删除按钮
			if(addButton && addButton=="true"){
				var delButton=mrtop.children().eq(1).append("删除");
				delButton2 = delButton;
				if(aBlocks.length==0){
					delButton.hide();
				}
				delButton.attr("id",formId + CTRL_MR_DEL + mrIndex);
				delButton.attr("mrIndex",mrIndex);
				delButton.attr("mr",CTRL_MR_DEL);
				delButton.attr("tableName",div.attr("tableName"));
				delButton.click(function() {
					var addBlocks=$(div).find("[remark=addBlock]");
					if(addBlocks.length==1){
						Flow.showToast("请至少填写其中一项");
					}else{
						addBlock.remove();
						// 容器中删对象
						var mri = $(this).attr("mrIndex");
						$.each(mrformFields, function(i, mrformField) {
							Flow.delFromMap(fieldMap, formId + CTRL_MR_FIELD + mri + "_"
									+ mrformField.id);
							Flow.delFromMap(pkFieldMap, formId + CTRL_MR_FIELD + mri + "_"
									+ mrformField.id);
						});
						mrFieldss.splice($.inArray(mrformFields,mrFieldss),1);
						//处理序号
						var mrdels=$(div).find("[mr="+CTRL_MR_DEL+"]");
						$.each(mrdels,function(i,del){
							var displayDiv=$(del).prev();
							var text=$(displayDiv).attr("name");
							displayDiv.text(text+"("+(i+1)+")");
						});
					}
				});
			}
			$.each(mrformFields, function(i, mrFormField) {
				var mrFormFieldId = formId + CTRL_MR_FIELD + mrIndex + "_"
						+ mrFormField.id;// 多行表单中表单域Id的处理
				Flow.initFormField(mrFormFieldId, mrFormField, blockTable);
				fieldMap[mrFormFieldId] = mrFormField;// 容器装对象
				if(mrFormField.remark=="PK"){
					pkFieldMap[mrFormFieldId] = mrFormField;
				}
			});
			addBlock.append(blockTable);
			//div.append(addBlock);
			var mradd=div.find("[mr="+CTRL_MR_ADD+"]");
			if(mradd.length>0){
				mradd.before(addBlock);
			}else{
				div.append(addBlock);
			}
			//处理序号
			var mrdels=$(div).find("[mr="+CTRL_MR_DEL+"]");
			if(mrdels && mrdels.length>0){
				var lastdel =mrdels.eq(mrdels.length-1);
				var displayDiv=lastdel.prev();
				var text=$(displayDiv).attr("name");
				displayDiv.text(text+"("+(mrdels.length)+")");
			}else{
				if(mradd.length>0){
					mrtop.children().eq(0).text(displayName+"(1)");
				}else{
					mrtop.children().eq(0).text(displayName+"("+(mrIndex+1)+")");
				}
			}
			Flow.addMRBlockAfter(addBlock,delButton2);//增加删除按钮的对象，用于删除多行表单块
		},
		/**
		 * 向多行表单控件中添加多行表单域模板
		 */
		addMRFormFieldsModel : function(map, formId, mrformFields) {
			var flag = false;
			$.each(map, function(i, value) {
				if (i == formId) {
					flag = true;
					return false;
				}
			});
			if (!flag) {
				var clone=$.extend(true, [], mrformFields);
				Flow.setMRFormFieldsModel(clone)
				map[formId] = clone;
			}
		},

		/**
		 * 获取多行表单域模板
		 */
		setMRFormFieldsModel : function(mrformFields) {
			$.each(mrformFields, function(i, mrformField) {
				mrformField.value = null;
			});
		},
		/**
		 * 根据表单域类型初始化表单域控件
		 */
		initFormField : function(fieldCtrlId, formField, table) {
			var tableName=$(table).attr("tableName");
			formField.ctrlId=fieldCtrlId;
			var isMain=$(table).attr("isMain");
			if(isMain){
				Flow.saveGlobalParams(formField);
			}
			var tr = $("<div class='weui_cell'></div>");
			var leftTd = $("<div class='weui_cell_hd'><label class='weui_label'>"+formField.cname+"</label></div>");
			var rightTd = $("<div class='weui_cell_bd weui_cell_primary'></div>");
			tr.append(leftTd).append(rightTd);
			table.append(tr);
			// 按照类型初始化表单域控件
			switch (formField.type) {
			case 0:
			default:
				var input = Flow.getInput(fieldCtrlId);
				Flow.formFieldVal(input,formField);//20170104 input.val(formField.value);
				input.attr("name",formField.name);
				input.attr("tableName",tableName);
				rightTd.append(input);
				tr.hide();
				break;
			case 1:
			case 8:
				var input = Flow.getInput(fieldCtrlId,"weui_input");
				Flow.formFieldVal(input,formField);//20170104 input.val(formField.value);
				input.attr("name",formField.name);
				input.attr("tableName",tableName);
				rightTd.append(input);
				Flow.setFormFieldAuthority(formField, tr, input, null);
				break;
			case 12:
			case 13:
				var input = Flow.getInput(fieldCtrlId,"weui_input");
				Flow.formFieldVal(input,formField);//20170104 input.val(formField.value);
				input.attr("name",formField.name);
				input.attr("tableName",tableName);
				rightTd.append(input);
				Flow.handleInputWithFt(formField,tr,input);
				Flow.setFormFieldAuthority(formField, tr, input, null);
				break;
			case 2:
				var input = Flow.getNumberInput(fieldCtrlId);
				input.attr("class","weui_input");
				input.attr("name",formField.name);
				input.attr("tableName",tableName);
				Flow.formFieldVal(input,formField);//20170104 input.val(formField.value);
				rightTd.append(input);
				Flow.setFormFieldAuthority(formField, tr, input, null);
				break;
			case 3:
				var input = Flow.getDecimalInput(fieldCtrlId);
				input.attr("class","weui_input");
				input.attr("name",formField.name);
				input.attr("tableName",tableName);
				Flow.formFieldVal(input,formField);//20170104 input.val(formField.value);
				rightTd.append(input);
				Flow.setFormFieldAuthority(formField, tr, input, null);
				break;
			case 4:
				// 格式如：2015-11-11
				var input=Flow.getWeInputText(formField.value,"");
				input.calendar();
				input.attr("id",fieldCtrlId);
				input.attr("name",formField.name);
				input.attr("tableName",tableName);
				rightTd.append(input);
				Flow.handleInputWithFt(formField,tr,input);
				Flow.setFormFieldAuthority(formField, tr, input, null);
				break;
			case 5:
				// 格式如：2015-11-11 11:11:11
				var input=Flow.getWeInputText(formField.value,"");
				input.datetimePicker();
				input.attr("id",fieldCtrlId);
				input.attr("name",formField.name);
				input.attr("tableName",tableName);
				rightTd.append(input);
				Flow.handleInputWithFt(formField,tr,input);
				Flow.setFormFieldAuthority(formField, tr, input, null);
				break;
			case 6:
				var input = Flow.getInput(fieldCtrlId,"weui_input");
				var remark=formField.remark;
				if(remark){
					var items = remark.split('|');
				    var obj={title:"选择",items:items};
					$(input).select(obj);
				}
				input.attr("name",formField.name);
				input.attr("readonly","readonly");
				input.attr("tableName",tableName);
				rightTd.append(input);
				Flow.handleInputWithFt(formField,tr,input);
				if(formField.value){
					Flow.formFieldVal(input,formField);//20170104 input.val(formField.value);
				}
				Flow.setFormFieldAuthority(formField, tr, input, null);
				break;
			case 7:
				//附件
				var eformId=table.attr("eformId");
				if(eformId){
					var remark=formField.remark;
					var catalog=remark.substring(remark.indexOf("CATALOG=")+8,remark.length);
					var attachCtrl=Flow.getAttach(fieldCtrlId,eformId,catalog);
					attachCtrl.attr("name",formField.name);
					attachCtrl.attr("tableName",tableName);
					rightTd.append(attachCtrl);
				}
				break;
			case 9:
				var textArea =Flow.getTextarea(fieldCtrlId,3);
				textArea.attr("name",formField.name);
				textArea.attr("tableName",tableName);
				if (formField.remark == "OPINION_CONTENT"){
					var spacing=$("<div class='form_row_spacing'>"+formField.cname+"</div>");
					tr.empty().before(spacing);
					if(formField.required){
						var agree=Flow.getActionWeuiButton("","同意");
						agree.click(function(){
							textArea.val("同意");
						});
						var disagree=Flow.getActionWeuiButton("","不同意");
						disagree.click(function(){
							textArea.val("不同意");
						});
						var table=$("<table style='width:100%'></table>");
						var tr1=$("<tr style='height:32px;'><td></td></tr>");
						var tr2=tr1.clone();
						table.append(tr1).append(tr2);
						tr1.append(agree).append(disagree);
						textArea.css("border","1px solid red");
						tr2.append(textArea);
						tr.append(table);
					}else{
						tr.append(textArea);
					}
				}else{
					rightTd.append(textArea);
				}
				Flow.formFieldVal(textArea,formField);//20170104 textArea.val(formField.value);
				Flow.setFormFieldAuthority(formField, tr, textArea, null);
				break;
			case 14:
				var textArea =Flow.getTextarea(fieldCtrlId,3);
				textArea.attr("name",formField.name);
				textArea.attr("tableName",tableName);
				rightTd.append(textArea);
				Flow.formFieldVal(textArea,formField);//20170104 textArea.val(formField.value);
				Flow.handleInputWithFt(formField,tr,textArea);
				Flow.setFormFieldAuthority(formField, tr, textArea, null);
				break;
			case 10:
				var spacing;
				if(formField.cname){
					spacing=$("<div class='form_row_spacing'>"+formField.cname+"</div>");
				}else{
					spacing=$("<div class='form_row_spacing' style='height:10px;'></div>");
				}
				tr.replaceWith(spacing);
			    break;
			case 100:
				Flow.initCustomFormFieldCtrl(fieldCtrlId,formField,tr,rightTd);
				break;
			}
		},
		formFieldVal:function(ctrl,formField){
			if(formField && ctrl){
				ctrl.val(formField.value);
				if(formField.showDisplayName){
					ctrl.attr("showDisplayName",true);
					ctrl.attr("displayName",formField.displayName);
				}
			}
		},
		/**
		 * 处理右边带箭头的控件
		 */
		handleInputWithFt:function(formField,tr,ctrl){
			if(formField.visible){
				if (formField.required) {
					tr.append("<div class='flow_cell_ft'></div>");
				}
			}
			if(ctrl){
				ctrl.click(function(){
					ctrl.focus();
					ctrl.blur();
				});
			}
		},
		handleInputPlaceholder:function(formField,ctrl){
			if(ctrl.get(0).tagName=="INPUT" || ctrl.get(0).tagName=="TEXTAREA"){
				var placeholder_text="请输入(必填)";
				if(formField.required){
					if(formField.type=="4" || formField.type=="5" || formField.type=="6"|| formField.type=="12"|| formField.type=="13"|| formField.type=="14"){
						placeholder_text="请选择(必填)";
					}
					ctrl.attr("placeholder",placeholder_text);
				}
			}
		},
		/**
		 * 处理通过或者退回后办理人选择界面的一些相关事宜
		 */
		handleSelStep:function(actionResult){
			var selNextStepName;
			var nextSteps;
			var actorCheckStepId={};//返回的办理环节下的办理人有默认选择的环节Id对象
			Flow.getSelSteps(actionResult,init);
			function init(steps,rselNextStepName){
				selNextStepName=rselNextStepName;
  				initSteps(steps);
  				bindEvent();
  				bindButton();
  				$("#dialog_selstep").show();
  				Flow.initStepsAfter(actionResult);
  			}
			function initSteps(steps){
				nextSteps=steps;
				$("#dialog_selstep_content").empty();
  				var pdiv=getDiv("weui_cells weui_cells_checkbox");
					pdiv.attr("style","width:100%; height:auto;overflow-y: auto; ");
					$("#dialog_selstep_content").append(pdiv);
					$.each(steps,function(i,step){
	  				if(step!=null){
	  					var actors=step.stepActor;
	  					var stepLabel=getStepLabel(step,i);
	  					pdiv.append(stepLabel);
	  					var ul=$("<ul step='step"+i+"' stepId="+step.stepId+" stepName="+step.stepName+" style='list-style:none; margin-top: 1px; padding-left:29px'></ul>");
	  					pdiv.append(ul);
	  					if(step.stepName=="结束"){
	  						stepLabel.attr("end",true);
  							if(steps.length==1){
  								var stepCheckBox=stepLabel.find(":checkbox");
  								if(stepCheckBox){
  									stepCheckBox.get(0).checked=true;
  								}
  		  					}
  							var li=$("<li></li>");
  							var label=getLabel("weui_cell weui_check_label","endsteplabel");
  							var ccheckBox=$("<input id='endsteplabel' stepId="+step.stepId+" type='checkbox' checked='checked' class='weui_check' flowhandle=true remark='actor'></input>");
  							var hd=getDiv("weui_cell_hd");
  							hd.append(ccheckBox).append(getIcoCheck());
  							label.append(hd).append("<div class='weui_cell_bd weui_cell_primary'><p>结束</p></div>");
  							label.hide();
  							li.append(label);
  							ul.append(li);
	  					}else{
	  						if(actors && actors.length>0){
		  						$.each(actors,function(i,actor){
			  						var li=$("<li></li>");
			  						li.append(getActorLabel(step,actor));
			  						ul.append(li);
			  					});
		  					}
	  					}
	  				}
	  			});
  			}
			function getDiv(clas){
  				return $("<div class='"+clas+"'></div>");
  			}
  			
  			function getLabel(clas,f){
  				return $("<label class='"+clas+"' for='"+f+"'>");
  			}
  			function getStepLabel(step,i){
  				var label=getLabel("weui_cell weui_check_label",step.stepId);
  				label.attr("remark","steplabel");
  				label.attr("stepId",step.stepId);
  				label.attr("stepName",step.stepName);
  				label.attr("step","step"+i);
  				var pcheckBox=$("<input id="+step.stepId+" stepId="+step.stepId+" stepName="+step.stepName+" step='step"+i+"' type='checkbox' class='weui_check'  flowhandle=true remark='step'></input>");
				var hd=getDiv("weui_cell_hd");
				var icon=getIcoCheck();
				//icon.addClass("step");//左边为箭头
				hd.append(pcheckBox).append(icon);
				label.append(hd).append("<div class='weui_cell_bd weui_cell_primary'><p>"+step.stepName+"</p></div>");
				return label;
  			}
  			function getActorLabel(step,actor){
  				var label=getLabel("weui_cell weui_check_label",step.stepId+"_"+actor.actorId);
				var ccheckBox=$("<input id="+step.stepId+"_"+actor.actorId+" actorId="+actor.actorId+" actorName="+actor.actorName+" actorType="+actor.actorType+" stepId="+step.stepId+" stepName="+step.stepName+" type='checkbox' class='weui_check' flowhandle=true remark='actor'></input>");
				if(actor["default"]){
					ccheckBox.attr("default",true);
						ccheckBox.get(0).checked=true;
						actorCheckStepId[step.stepId]=true;
					}else{
						ccheckBox.attr("default",false);
					}
				if(actor["need"]){
					ccheckBox.attr("need",true);
				}else{
					ccheckBox.attr("need",false);
				}
				if(!step.canSelActor){
					//canSelActor是该环节下的办理人是否可以选择，但是用户发送时，该环节也是可选的
					ccheckBox.attr("canSelActor",false);
					//ccheckBox.attr("disabled","disabled");
				}else{
					if(!actor["default"]){
						ccheckBox.attr("canSelActor",true);
					}
				}
				var hd=getDiv("weui_cell_hd");
				hd.append(ccheckBox).append(getIcoCheck());
				label.append(hd).append("<div class='weui_cell_bd weui_cell_primary'><p>"+actor.actorName+"</p></div>");
				return label;
  			}
  			
  			//办理环节单选
  			function setStepSingleCheck(stepCheckBox){
  				if(Flow.openStepSingleCheck()){
  					if(selNextStepName.indexOf("多选") == -1){
  	  					var stepId=stepCheckBox.attr("stepId");
  	  					var checkboxs = $("#dialog_selstep_content :checkbox[step][flowhandle=true][remark=step]");
  	  					$.each(checkboxs,function(i,checkbox){
  	  						if($(checkbox).attr("stepId")!=stepId){
  	  							checkbox.checked=false;
  	  							var uls = $("ul[step][stepId!="+stepId+"]");
  	  							uls.hide();
  	  						}
  	  					});
  	  				}
				}
  			}
  			
  			function getIcoCheck(){
  				return $("<i class='weui_icon_checked'></i>");
  			}
  			
  			function bindEvent(){
  				var pcheckBoxs = $("input[step][flowhandle=true]");
				$.each(pcheckBoxs,function(i,pcheckBox){
					$(pcheckBox).change( function() {
						var attr=$(this).attr("step");
						var uls = $("ul[step="+attr+"]");
						if($(pcheckBox).is(":checked")){
							uls.show();
							var ccheckboxs=uls.find("input:checkbox[remark=actor]");
							if(ccheckboxs && ccheckboxs.length==1){
								//展开时，如果该环节下就一个办理人，则默认选中
								ccheckboxs[0].checked=true;
							}
							setStepSingleCheck($(pcheckBox));
						}else{
							uls.hide();
							//收起来后对应的办理人取消选中
							var ccheckboxs=uls.find("input:checkbox[remark=actor]");
							if(ccheckboxs){
								var attrdefault;
								$.each(ccheckboxs,function(i,cb){
									attrdefault=$(cb).attr("default");
									if(!attrdefault){
										//如果是默认选中的人，则收起来后办理人依然选中
										cb.checked=false;
									}
								});
							}
						}
					});
				});
				
				
				var uls = $("ul[step]");
				var firstCheck=false;
				$.each(uls,function(i,ul){
					var ccheckbox=$(ul).find("input");
					//20161116办理环节是否展开，看平台返回来的办理环节下的办理人是否选中，如果办理环节下的办理人有一个选中则展开
					//20161217上面的问题做出调整，改为只展开第一个环节
					var stepId=$(ul).attr("stepId");
					if(!actorCheckStepId[stepId]){
						$(ul).hide();
					}else{
						if(!firstCheck){
							firstCheck=true;
							$("input:checkbox[remark=step][step][stepId="+stepId+"]").get(0).checked=true;
						}else{
							$(ul).hide();
						}
					}
				});
  			}
			//判读一个ul中的checkbox是否全部选中
			function ulAllCheck(uls){
				var flag=true;
				var lis=$(uls).find("li");
				$.each(lis,function(i,li){
					var ccheckbox=$(li).find("input");
					 if(!$(ccheckbox).is(":")){
						flag=false;
						return false;
					 }	
				}); 
				return flag;
			}
			function bindButton(){
				$("#dialog_selstep_ok").unbind();
				$("#dialog_selstep_return").unbind();
  				$("#dialog_selstep_ok").click(function(){
  					send();
  				});
  				$("#dialog_selstep_return").click(function(){
  					$("#dialog_selstep").hide();
  				});
  			}
			function getStepById(stepId){
				if(nextSteps){
					var step;
					$.each(nextSteps,function(i,obj){
						if(obj.stepId==stepId){
							step=obj;
							return false;
						}
					});
					return step;
				}
			}
			
			function getActorById(step,actorId){
				if(step){
					var stepActor=step.stepActor;
					if(stepActor){
						var actor;
						$.each(stepActor,function(i,obj){
							if(obj.actorId==actorId){
								actor=obj;
								return false;
							}
						});
						return actor;
					}
				}
			}
			
			//发送事件
			function send(){
				var imselactors=[];
				var checkActor=true;
				var checkStepIds=[];//选中环节的id
				var stepcbs=$("input:checkbox[flowhandle=true][remark='step']:checked");
				if(stepcbs.length>0){
					$.each(stepcbs,function(i,stepcb){
						var stepId=$(stepcb).attr("stepId");
						var actorcbs=$("input:checkbox[flowhandle=true][remark=actor][stepId="+stepId+"]:checked");
						if(actorcbs.length>0){
							checkStepIds.push(stepId);
						}else{
							checkActor=false;
							var stepName=$(stepcb).attr("stepName");
							Flow.showToast("请在"+stepName+"环节下至少选择一个办理人", "cancel");
							return false;
						}
					});
				}else{
					var steps=$("input:checkbox[flowhandle=true][remark='step']");
					if(steps && steps.length==1){
						var endstep=steps.get(0);
						if($(endstep).attr("stepName")=="结束"){
							Flow.showToast("请选择结束环节", "cancel");
						}else{
							Flow.showToast(TIPS_SELECT_ACTOR, "cancel");
						}
					}else{
						Flow.showToast(TIPS_SELECT_ACTOR, "cancel");
					}
				}
				if(checkStepIds.length>0 && checkActor){
					//有选择环节，才可以往下发送
					var checkboxs=$("input:checkbox[flowhandle=true][remark='actor']:checked");
	  					var steps=[];
	  				    //获取选中的办理环节以及办理环节下对应的办理人
	  					$.each(checkStepIds,function(index,i){
	  						var step=getStepById(i);
	  						var actors=[];
	  						if(step){
	  							steps.push(step);
	  						}
	  						$.each(checkboxs,function(j,checkbox){
	  							var stepId=$(checkbox).attr("stepId");
	  							var remark=$(checkbox).attr("remark");
	  							var actorId=$(checkbox).attr("actorId");
	  							if(remark=="actor" && stepId==i  && actorId && actorId!="-1"){
	  								var actorName=$(checkbox).attr("actorName");
	  								var actortype=$(checkbox).attr("actortype");
	  								var need=$(checkbox).attr("need");
	  								var def=$(checkbox).attr("default");
	  								var actor={
	  									"actorId":actorId,
	  									"actorName":actorName,
	  									"actorType":actortype,
	  									"isNeed":need,
	  									"idDefault":def
	  								}
	  									actors.push(actor);
	  								if(actorId!=Flow.USERID){
	  									imselactors.push(actor);
	  								}
	  							}
	  						});
	  						step.stepActor=actors;
	  					});
	  					if(Flow.sendBefore(steps)){
	  						var data={
	  								userName:Flow.USERNAME,
	  								flowName:Flow.FLOWNAME,
	  								subject:Flow.subject,
	  								t:Flow.T,
	  								dbName:Flow.DBNAME,
	  	  	  		  				ratifyId:Flow.RATIFYID,
	  	  	  		  				actionResult:actionResult,
	  	  	  		  				userId:Flow.USERID,
	  	  	  		  				steps:JSON.stringify(steps)
	  	  	  		  		}
	  						Flow.showLoadingToast(TIPS_SENDING);
	  		  				$("#dialog_selstep").hide();
	  						Flow.mtpost(Flow.ROOTURL+"/send",data,function(result){
	  							Flow.hideLoadingToast();
	  	  						if(result){
		  	  						var r= result;
		  	  						if(r.result){
		  	  							if(Flow.sendAfter()){
			  	  							if(Flow.BROWSERURL){
			  	  								//发送成功后退到待办界面
			  	  								var url=Flow.BROWSERURL+"&userId="+Flow.USERID+"&deptId="+Flow.DEPTID;
		  	  									location.replace(url);
		  	  								}
		  	  							}
		  	  							//发送im消息
		  	  							var immessage={"selActors":imselactors,"subject":Flow.subject};
		  	  							Flow.sendIMMessage(immessage);
		  	  						}else{
		  	  							Flow.showToast(TIPS_ERROR_SEND);
		  	  						}
	  	  						}
	  						},function(){
	  							Flow.hideLoadingToast();
								Flow.showToast(TIPS_ERROR_SEND, "cancel");
	  						});
	  					}
				}else{
					return false;
				}
  			}
		},
		sendIMMessage:function(immessage){
			var selActors=immessage.selActors;
			if(selActors && selActors.length>0){
				try {
					//发送报件成功后发送im消息
					var json=JSON.stringify(immessage);
		            window.mobile_x.sendFlowIMMessage(json);
		          } catch (e) {
		            window.webkit.messageHandlers.sendFlowIMMessage.postMessage(json);
				}
			}
		},
		getSelSteps : function(actionResult,func) {
			var data={
		  				ratifyId:Flow.RATIFYID,
		  				actionResult:actionResult,
		  				userId:Flow.USERID,
		  				currentStepId:Flow.CURRENTSTEPID,
		  				dbName:Flow.DBNAME,
		  				t:Flow.T
		  		}
				Flow.mtpost(Flow.ROOTURL+"/getSelSteps",data,function(result){
					if(result){
						Flow.hideLoadingToast();
						var map=result;
						func(map.selSteps,map.currentStep.selNextStep.name);
					}
				},function(){
					Flow.hideLoadingToast();
				});
		},
		saveGlobalParams:function(formField){
			//保存全局参数
			switch(formField.remark)
			{
				case "CREATETIME":Flow.CREATETIME=formField.value;break;
				case "FLOWSN":
					if(formField.value){
						Flow.FLOWSN=formField.value; 
					}
					flowsnfields.push(formField); break;
			}
		},
		// 设置表单域权限 func是对控件的只读处理
		setFormFieldAuthority : function(formField, tr, ctrl, func) {
			if (formField.visible) {
				Flow.handleInputPlaceholder(formField,ctrl);
				if (formField.required) {
					var leftTd = $(tr).children().get(0);
					ctrl.attr("r", true);
				}
				if (formField.readOnly) {
					if (func != null) {
						func();
					}else{
						ctrl.attr("disabled", 'disabled');
					}
				}
			} else {
				tr.hide();
			}
		},
		getFormDiv:function(form){
			return $("<div id=" + CTRL_FORM + form.id+ " eformId="+form.eformId+"></div>");
		},
		// 单行输入控件
		getInput : function(id) {
			return $("<input id=" + id + "></input>");
		},
		getNoBorderInput:function(id){
			return $("<input id=" + id + " style='border-width: 0; background-color: transparent;'></input>");
		},
		// 单行输入控件
		getInput : function(id,clas) {
			return $("<input id=" + id + " class="+clas+"></input>");
		},
		// 按钮
		getButton:function(id, text) {
			return $("<Button id=" + id + ">" + text + "</Button>");
		},
		// 多行输入控件
		getTextarea : function(id, row) {
			return $("<textarea class='weui_textarea' id=" + id + " rows=" + row + " style=' border:none'></textarea>");
		},
		// 只能输入数字
		getNumberInput : function(id) {
			var ctrl=$("<input id="+id+" type='number' pattern='[0-9]*' />");
			return ctrl;
		},
		// 只能输入输入数字和小数点
		getDecimalInput : function(id) {
			var ctrl=$("<input id="+id+" type='number' />");
			ctrl.bind('input propertychange', function() {
				var value=ctrl.val();
				var result=value.replace(/[^(\d+(\\.\\d+)?)]/g,'');
				ctrl.val(result);
			});
			return ctrl;
		},
		getALink:function(id,value){
			return $("<a id="+id+">"+value+"</a>");
		},
		getALink:function(id,value,clas){
			return $("<a id="+id+" class='"+clas+"'>"+value+"</a>");
		},
		getWeuiButton:function(id,value){
			var button=$("<a class='weui_btn weui_btn_mini weui_btn_plain_default'>"+value+"</a>");;
			if(id){
				button.attr("id",id);
			}
			return button;
		},
		getWeInputText:function(value,placeholder){
			if(!value){value="";}
			if(!placeholder){placeholder="";}
			var input=$("<input value='"+value+"' class='weui_input' placeholder='"+placeholder+"'>");
			return input;
		},
		getActionWeuiButton:function(id,value){
			var button = Flow.getButton(id,value);
			button.addClass("button_boot");
			button.attr("style","margin: 0 7 0 0");
			return button;
		},
		getSelect : function(id, values) {
			var select = $("<select></select>");// 下拉
			if (values != null) {
				var attr = values.split('|');
				$.each(attr, function(i, value) {
					select.append($("<option value=" + value + ">" + value
							+ "</option>"));
				});
			}
			return select;
		},
		//附件
		getAttach:function(id,eformId,catalog){
			var div=$("<div></div>");
			div.attr("id",id);
			if(Flow.attaches){
				$.each(Flow.attaches,function(i,attach){
					if(eformId==attach.eformId && catalog==attach.catalog && attach.attachFile){
						var down=Flow.getAttachAlink(attach);
						div.append(down).append("<br>");
					}
				});
			}
			return div;
		},
		getAttachAlink:function(attach){
			  var down;
				var editFlag="R";
				if(Flow.DOWITHATTACH){
					editFlag="E";
				}
				if(attach.fileKind){
					attach.fileKind=attach.fileKind.toUpperCase();
				}
				if(attach.storeKind=="0"){
					//attach.href = Flow.URLencode(attach.href);
					var encodeHref=Flow.URLencode(attach.href);
					var encodeAttachFile=Flow.URLencode(attach.attachFile);
					if(attach.fileKind=="DOC" || attach.fileKind=="DOCX" ||attach.fileKind=="PDF" ||attach.fileKind=="XLS" ||attach.fileKind=="XLSX" ||attach.fileKind=="PPT" || attach.fileKind=="PPTX" || attach.fileKind=="TXT" ){
						down=$("<a style='color:black;' catalog="+attach.catalog+" href='"+Flow.ROOTURL+"/attach/downByUrl?href="+encodeHref+"&fileName="+attach.attachId+"$"+Flow.DBNAME+"$"+editFlag+"$wps$"+encodeAttachFile+"&applyKind="+Flow.DBNAME+"&attachId="+attach.attachId+"&editstaue="+editFlag+"&openfile=wps' attach=download>"+attach.attachFile+"</a>");
					}else{
						down=$("<a style='color:black;' catalog="+attach.catalog+" href='"+Flow.ROOTURL+"/attach/downByUrl?href="+encodeHref+"&fileName="+encodeAttachFile+"' attach=download>"+attach.attachFile+"</a>");
					}
					down.click(function(){
						var data={
							url:encodeHref
						};
						 var result = $.ajax({
							 type: "POST",
							  url: Flow.ROOTURL+"/attach/check",
							  data:data,
							  dataType: "json",
							  async: false
							 }).responseText;
						 if(result!="true"){
							 Flow.showToast(TIPS_NO_CURRENTATTACH, "cancel");
							 return false;
						 }
					});
				}else{
					if(attach.fileKind=="DOC" || attach.fileKind=="DOCX" ||attach.fileKind=="PDF" ||attach.fileKind=="XLS" ||attach.fileKind=="XLSX" ||attach.fileKind=="PPT" || attach.fileKind=="PPTX" || attach.fileKind=="TXT" ){
						down=$("<a style='color:black;' catalog="+attach.catalog+" href='"+attach.href+"&fileName="+attach.attachId+"$"+Flow.DBNAME+"$"+editFlag+"$wps$"+attach.attachFile+"&applyKind="+Flow.DBNAME+"&editstaue="+editFlag+"&openfile=wps' attach=download>"+attach.attachFile+"</a>");
					}else{
						down=$("<a style='color:black;' catalog="+attach.catalog+" href='"+attach.href+"' attach=download>"+attach.attachFile+"</a>");
					}
				}
				return down;
		},
		getSignatureCanvasButton:function(signatureField,func){
			var button=Flow.getActionWeuiButton("","手写签批");//手写签批按钮
			button.attr("name",signatureField.name);
			button.attr("remark","signature");
			button.click(function(){
				$("#dialog_signature").show();
				if($("#dialog_signature_content").find("canvas").length==0){
					Paint.init($("#dialog_signature_content"));
				}
			});
			var clear=$("#dialog_signature_clear");
			var ok=$("#dialog_signature_ok") ;
			var ret=$("#dialog_signature_return");
			var paint=$("#dialog_signature_paint");
			var eraser=$("#dialog_signature_eraser");
			clear.unbind();ok.unbind();ret.unbind();paint.unbind();eraser.unbind();
			paint.click(function(){
				Paint.paint();
				paint.attr("class","weui_btn weui_btn_mini weui_btn_plain_default");
				eraser.attr("class","weui_btn weui_btn_mini weui_btn_default");
			});
			eraser.click(function(){
				Paint.eraser();
				paint.attr("class","weui_btn weui_btn_mini weui_btn_default");
				eraser.attr("class","weui_btn weui_btn_mini weui_btn_plain_default");
			});
			clear.click(function(){
				Paint.clear();
			});
			ok.click(function(){
				func();//开放确定后的事件
			});
			ret.click(function(){
				$("#dialog_signature").hide();
			});
			return button;
		},
		getCommonWordButton:function(textarea,values){
			var button=Flow.getActionWeuiButton("","常用语");
			var acs=[];
			if(!values){
				if(!Flow.COMMONWORD){
					values="同意。;不同意。;已阅。";
				}else{
					values=Flow.COMMONWORD;
				}
			}
			var attr = values.split(';')
			$.each(attr, function(i, value) {
				var ac={
						text:value,
						onClick:function(){
							textarea.val(value);
						}
					};
					acs.push(ac);
			});
			var commonWord={title:"常用语",actions:acs};
			button.click(function(){
				$.actions(commonWord);
			});
			return button;
		},
		/* 获取流程中的数据 */
		getFormById:function(formId){
			var rform;
			if(Flow.forms){
				$.each(Flow.forms,function(i,form){
					if(formId==form.id){
						rform=form;
						return false;
					}
				});
			}
			return rform;
		},
		/**
		 * 获取一个父表单旗下的子表单
		 */
		getChildForms : function(forms, pid) {
			var childForms = [];
			$.each(forms, function(i, obj) {
				if (obj.pid == pid) {
					childForms.push(obj);
				}
			});
			return childForms;
		},
		getFieldByRemark:function(fields,remark){
			var result;
			if(fields){
		         $.each(fields,function(i,field){
		           if(field && field.remark == remark){
		        	   result=field;
		             return false;
		           }
		         });
		     }
			return result;
		},
		/* 获取界面上控件的方法  */
		/* 20161020  */
		form:function(tableName,eformId){
			if(eformId){
				if(tableName){
					return $("[eformId="+eformId+"][tableName="+tableName+"]:last");
				}
				return $("[eformId="+eformId+"][tableName]:last");
			}else{
				if(tableName){
					return $("[tableName="+tableName+"][eformId]:last");
				}
			}
		},
		field:function(name,tableName,eformId){
			var formDiv=Flow.form(tableName,eformId);
			var ctrl;
			if(formDiv){
				ctrl=formDiv.find("[name="+name+"]");
			}else{
				ctrl=$("[name=forms_main]").find("[name="+name+"]")
			}
			return ctrl;
		},
		hide:function(name,tableName,eformId){
			var ctrl=Flow.field(name,tableName,eformId);
			if(ctrl){
				ctrl.parent().parent().hide();
			}
		},
		show:function(name,tableName,eformId){
			var ctrl=Flow.field(name,tableName,eformId);
			if(ctrl){
				ctrl.parent().parent().show();
			}
		},
		edit:function(name,tableName,eformId){
			var ctrl=Flow.field(name,tableName,eformId);
			if(ctrl){
				ctrl.removeAttr("readonly");
				ctrl.removeAttr("disabled");
				ctrl.css("color","black");
			}
		},
		rq:function(name,tableName,eformId,stepId,withFt){
			var ctrl=Flow.field(name,tableName,eformId);
			Flow.rqc(ctrl,stepId,withFt);
		},
		rqc:function(ctrl,stepId,withFt){
			if(ctrl && stepId && (Flow.STATUS == "H" || Flow.STATUS =="W") && Flow.CURRENTSTEPID==stepId){
				if(withFt){
					ctrl.parent().parent().append("<div class='flow_cell_ft'></div>");
				}
				var ctrlId=ctrl.get(0).id;
				var formField=fieldMap[ctrlId];
				if(formField){
					formField.required=true;
					ctrl.attr("r",true);
					Flow.handleInputPlaceholder(formField,ctrl);
				}
			}
		},
		nrq:function(name,tableName,eformId){
			var ctrl=Flow.field(name,tableName,eformId);
			Flow.nrqc(ctrl);
		},
		nrqc:function(ctrl){// not require ctrl
			if(ctrl){
				ctrl.removeAttr("placeholder");
				ctrl.removeAttr("r");
			}
		},
		readonly:function(name,tableName,eformId){
			var ctrl=Flow.field(name,tableName,eformId);
			Flow.readonlyc(ctrl);
		},
		readonlyc:function(ctrl){
			if(ctrl){
				ctrl.attr("disabled", 'disabled');
			}
		},
		getDisplayName:function(ctrl){
			var displayName="";
			if(ctrl){
				var showDisplayName = ctrl.attr("showDisplayName");
				if(showDisplayName){
					displayName = ctrl.attr("displayName");
				}
			}
			return displayName;
		},
		mtpost:function(url,param,function_result,function_error){
			var token=Flow.TOKEN;
			if(!token){
				var tk=$("#TK").text();
				token=tk;
			}
			$.ajax({
				  url:url,
				  type: "POST",
				  dataType: "json",
				  data: param,
				  headers: {
				    'APMT': token
				  },
				  success: function(data, status, xhr){
					  var dsd = xhr.getResponseHeader("APMT");
					  Flow.TOKEN=dsd;
					  function_result(data,status,xhr);
				   },
				   error:function(xhr, textStatus, errorThrown){
					   $("#firstLoadingdiv").text("访问服务器出现异常，请重新登录系统，或联系管理员。");
					   if(function_error){
						   function_error(xhr,textStatus,errorThrown);
					   }
				   }
			  });
		},
		/* 20161110  */
		stepSingleCheck:function(array){//办理环节有多个情况下，只能选择一个环节中的办理人进行发送    （单选）
	        //小项单选
			$.each(array,function(i,obj){
				//小项 单选
				if(obj.sigleCheck){
					var radiochecks = $("input:checkbox[flowhandle=true][remark=actor][stepid="+obj.stepId+"]");
					$.each(radiochecks,function(i,radiocheck){
						$(radiocheck).change(function(){
							if(radiocheck.checked){
								var actorId= $(radiocheck).attr("actorid");
								$.each(radiochecks,function(i,radiocheck){
									if(actorId!=$(radiocheck).attr("actorid")){
										radiocheck.checked=false;
									}
			  					});
							}
						});
					});
				}
			});
			var checkboxs=[] ;
			$.each(array,function(i,obj){
				var stepId=obj.stepId;
				var cb = $("input:checkbox[flowhandle=true][remark=step][stepId="+stepId+"]");
				if(cb && cb.length>0){
					checkboxs.push(cb);
				}
			});
			if(checkboxs.length>0){
				$.each(checkboxs,function(i,checkbox){
					$(checkbox).change(function(){
						if($(checkbox).is(":checked")){
							var stepId= $(checkbox).attr("stepId");
							$.each(checkboxs,function(i,cb){
								if(stepId!=$(cb).attr("stepId")){
									$(cb).get(0).checked=false;
									var uls=$("ul[step][stepId="+$(cb).attr("stepId")+"]");
									uls.hide();
									var ccheckboxs=uls.find("input:checkbox[remark=actor]");
									if(ccheckboxs){
										var attrdefault;
										$.each(ccheckboxs,function(i,ccb){
											attrdefault=$(ccb).attr("default");
											if(!attrdefault){
												//如果是默认选中的人，则收起来后办理人依然选中
												$(ccb).get(0).checked=false;
											}
										});
									}
								}
							});
						}
					});
				});
			}
		},
		/* 20161110  */
		getCtrlForm:function(formId){
			return $("#"+CTRL_FORM+formId);
		},
		getCtrlField:function(formId,fieldId){
			return $("#"+formId+CTRL_FIELD+fieldId);
		},
		getCtrlFieldByName:function(formId,name){
			var form = Flow.getCtrlForm(formId);
			return form.find("[name="+name+"]");
		},
		getCtrlMAdd:function(formId){
			return $("#"+CTRL_MR_ADD+formId);
		},
		getCtrlAction:function(actionResult){
			return $("#"+CTRL_ACTION+actionResult);
		},
		getCtrlAttachFile:function(attachCtrl){
			return attachCtrl.find("input[attach=file]");
		},
		getCtrlAttachDeletes:function(attachCtrl){
			return attachCtrl.find("[attach=delete]");
		},
		getCtrlAttachDownloads:function(attachCtrl){
			return attachCtrl.find("[attach=download]");
		},
		getCtrlAttachEdits:function(attachCtrl){
			return attachCtrl.find("[attach=edit]");
		},
		/* 其他工具方法 */
		addTabUrlForm:function(url,formName,index){
			var div=$("<div style='padding:5px'></div>").load(url);
			Flow.addTabForm(formName,div,index);
		},
		addTabForm:function(title,formDiv,index){
			formSize+=1;
			if(!index){
				index=$("#forms_tab option").size();
			}
			if(index!=0){
				formDiv.hide();
			}
			$("#forms_tab").append("<option value="+index+">"+title+"</option>");
			formDiv.attr("tab",index);
			$("#forms_main").append(formDiv);
		},
		tabForm:function(index){
			$("#forms_tab").val(index);
			$("#forms_main [tab]").hide();
			$("#forms_main [tab="+index+"]").show();
		},
		disable:function(ctrl){
			ctrl.attr("disabled","disabled");
		},
		firstLoading:function(){
		  	var line = "." ;  
            var amount ="" ;
            var num = 0;
           $("<div id=\"firstLoadingdiv\" style=\"color:#cbcbcb;width:100%;text-align:center;position: absolute;top:47%;\">加载中<label id=\"firstLoading\"></label></div>").appendTo(document.body);
            count();  
			  function count(){   
	                  
	                $("#firstLoading").text(amount);
	                num= num+1;  
	                amount =amount + line; 
	                if (num<5)   
	                    {
	                	setTimeout(count,300);
	                	}   
	                else   
	                    {
	                    	amount="";
	                    	num=0;
	                    	count();
	                    }

	            }
		},
		endfirstLoading:function(){
			$("#firstLoadingdiv").remove();
		},
		showToast:function(text,op){
			if(op){
		        $.toast(text,op);
			}else{
		        $.toast(text);
			}    
		},
		showLoadingForm:function(){
			//如果要去掉该进度，把show和hide两个方法以及formLoadingIndex变量去掉即可。
			if(formLoadingIndex<50){
				setTimeout("Flow.showLoadingForm()",50);
				formLoadingIndex+= Math.floor(Math.random()*10+1);
			}else if(formLoadingIndex>=50 && formLoadingIndex<80){
				setTimeout("Flow.showLoadingForm()",100);
				formLoadingIndex+= Math.floor(Math.random()*3+1);
			}else if(formLoadingIndex>=80 && formLoadingIndex<92){
				setTimeout("Flow.showLoadingForm()",150);
				formLoadingIndex++;
			}else if(formLoadingIndex>=92 && formLoadingIndex<=98){
				setTimeout("Flow.showLoadingForm()",300);
				formLoadingIndex++;
			}
			$("#loadingForm").text(formLoadingIndex + "%");
		},
		hideLoadingForm:function(){
			$("#loadingForm").text("100%");
			$("#loadingForm").hide();
		},
		showLoadingToast:function(text){
			  $.showLoading(text);
		},
		hideLoadingToast:function(){
			$.hideLoading();
		},
		/* 流程生成生命周期开放接口 */
		getInitMode:function(){},
		customForm:function(){},//如果该方法有值，则是使用自定义表单，自己做jsp
		getSimpleFormCellType:function(){},//详细表单中的单元格，默认是单行左右的方式，复写该接口后则是两行
		getCommonWord:function(){},
		initFlowCtrlBefore:function(flowCtrl){},
		initFlowCtrlAfter:function(flowCtrl){},
		initFormsBefore:function(forms){},
		initFormsAfter:function(forms){},
		initActionsBefore:function(actions){},
		initActionsAfter:function(buttons){},
		initOpinionToolbarAfter:function(simpleforms_editopinion_buttons,textarea,formField,signatureField){},
		addMRBlockAfter:function(block){},
		initStepsAfter:function(actionResult){},
		openStepSingleCheck:function(){return true},
		initCustomFormFieldCtrl:function(fieldCtrlId,formField,tr,rightTd){},
		initAttach:function(eformId,attachCtrl,catalog){},
		signBefore:function(){return true},
		saveBefore:function(){return true},
		saveHandleSubject:function(){},
		saveAfter:function(){},
		sendBefore:function(selSteps){return true},
		sendAfter:function(){return true}
	};
	return obj;
})(jQuery);

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

$(function() {
	Flow.init();
});