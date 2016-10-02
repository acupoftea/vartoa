var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $scope.reSubmit = function() {
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=reSubmit", {
			  params: {
				  wfiId: $scope.wfiId
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		alert("操作成功");
		    		 $('#example2').DataTable().draw();
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.showHideDownloadLink = false;
	  $scope.auditList = [{key: "通过", value: 1}, {key: "驳回", value: 0}];
	  $scope.showAudit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $scope.id = rows[0].id;
		  
		  $("#myModal4edit").modal({backdrop: 'static'});
	  };
	  
	  $scope.createApply = function() {
		  //$scope.apply = {};
		  $scope.apply.summary = $scope.applySummary;
		  $scope.apply.userId = userId;
		  $scope.apply.workflowId = $scope.workflowId;
		  
		  var array = new Array();
		  var map = {};
		  for(var i in $scope.controlArray) {
			  console.log("i: " + i);
			  console.log("controlArray: " + $scope.controlArray[i]);
			  
			  //var v = $("#" + $scope.controlArray[i]).val();
			  var t = $("[name=" + $scope.controlArray[i] + "]").attr("type");
			  console.log("t: " + t);
			  var v = null;
			  if(t == "radio" || t == "checkBox") {
				  var control = $("[name=" + $scope.controlArray[i] + "]:checked");
				  if(t == "checkBox") {
					  $("[name=" + $scope.controlArray[i] + "]:checked").each(function() {
						  if($(this).val() != null) {
							  v += $(this).val();
							  v += ",";
						  }
					  });
				  }
				  else
					  v = control.val();
			  }
			  else
				  v = $("[name=" + $scope.controlArray[i] + "]").val();
			  console.log("v: " + v);
			  
			  eval("map.arg" + i + "=\"" + v + "\"");
		  }
		  
		  $scope.apply.formData = map;
		  
		  var index = layer.load(2, {
		    shade: [0.3, '#000'],
		    offset: '250px'
		  });
		  
		  var index2 = layer.msg('处理中...', {
			  icon: 16,
			  time: 9 * 10000,
			  offset: '250px'
			});
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=create", {
			  params: {
				   data: encodeURI(encodeURI(JSON.stringify($scope.apply)))
		  }})
		     .success(function (response) {
		    	layer.close(index);
		    	layer.close(index2);
		    	
		    	if(response.success) {
		    		layer.alert("新建成功");
		    		$('#example2').DataTable().draw();
		    		$("#myModalDynamicApply").modal("hide");
		    	}
		    	else
		    		layer.alert('新建失败, 检查必填字段是否填写?');
		    	 
		     }).error(function(a, b, c, d){
		    	 layer.close(index);
		    	 layer.close(index2);
		    	 
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  delli = function(index) {
		  var obj = $("li[id=\"uploadId_" + index + "\"]");
		  obj.remove();
		  
		  var temp = "";
  		$("ul li a[name=\"fileDetail\"]").each(function() {
  			if($(this).val() != null) {
  				temp += $(this).html() + "," + $(this).attr("href");
  				temp += ";";
  			}
  		});
  		
  		$("#attachmentSet").val(temp);
	  };
	  
	  $scope.selectWorkflow = function() {
		  var wfId = $(".select2").val();
		  wfId = wfId.split(":")[1];
		  $scope.workflowId = wfId;
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowService&bizType=queryFromListByWorkflowId", {
			  params: {
				  workflowId: $scope.workflowId
				  
		  }}).success(function (response) {
			  if(response.success) {
				  $scope.apply = {};
				  $scope.apply.udfid = response.workflow.udfid;
				  $scope.workflowName = response.workflow.name;
				  $scope.auditStep = response.auditStep;
				  
				  $("#vessel").empty();
				  if(response.udf_attachment != null)
					  $("#vessel").append("<div class=\"form-group\"><label for=\"inputPassword3\" class=\"col-sm-2 control-label\">流程附件</label><div class=\"col-sm-10\">" +
						  "<a href=\"" + response.udf_attachment + "\" target=\"_blank\">模板</a></div></div>");
				  
				  var controlArray = new Array();
				  var jsonList = response.data;
				  
				  for(var obj in jsonList) {
					  var obj2 = jsonList[obj];
					  
					  console.log(obj2.label);
					  var requirefield = "";
					  if(obj2.requirefield == 1)
						  requirefield = "<font color=\"red\">*</font>&nbsp;&nbsp;&nbsp;";
					  
					  var widget = "<input type=\"text\" class=\"form-control\" />";
					  var widgetType = parseInt(obj2.widgettype);
					  console.log("widgetType: " + widgetType);
					  switch(widgetType) {
					  case 1:
						  widget = "<input tye=\"text\" class=\"form-control\" name=\"control_" + obj2.id + "\" />";
						  if(obj2.requirefield == 1)
							  widget = "<input tye=\"text\" class=\"form-control\" name=\"control_" + obj2.id + "\" required />";
						  
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 2:
						  widget = "<textarea rows=\"3\" cols=\"20\" class=\"form-control\" name=\"control_" + obj2.id + "\"></textarea>";
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 3:
						  var controlValue = obj2.controlvalue.split(";");
						  var temp = "";
						  for(var v in controlValue) {
							  temp += "<input type=\"radio\" name=\"control_" + obj2.id + "\" value=\"" + controlValue[v] + "\" />" +  controlValue[v] ;
							  temp += "&nbsp;&nbsp;&nbsp;&nbsp;";
						  }
						  widget = temp;
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 4:
						  var controlValue = obj2.controlvalue.split(";");
						  var temp = "";
						  for(var v in controlValue) {
							  temp += "<input type=\"checkBox\" name=\"control_" + obj2.id + "\" value=\"" + controlValue[v] + "\" />" +  controlValue[v] ;
							  temp += "&nbsp;&nbsp;&nbsp;&nbsp;";
						  }
						  widget = temp;
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 5:
						  if(obj2.controlvalue == undefined)
							  break;
						  
						  var controlValue = obj2.controlvalue.split(";");
						  var temp = "<select class=\"form-control\" name=\"control_" + obj2.id + "\">";
						  
						  for(var v in controlValue) {
							  temp += "<option value=\"" + controlValue[v] + "\">" + controlValue[v] + "</option>";
						  }
						  temp += "</select>"; 
						  
						  widget = temp;
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 6:
						  widget = "<input type=\"text\" class=\"form-control pull-right\" id=\"datepicker\" name=\"control_" + obj2.id + "\">";
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 7:
						  widget = "<input type=\"text\" class=\"form-control pull-right\" id=\"reservation\" name=\"control_" + obj2.id + "\">";
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 8:
						  widget = "<span class=\"btn btn-success fileinput-button\"><i class=\"glyphicon glyphicon-plus\"></i><span>选择文件...</span><input id=\"fileupload\" type=\"file\" name=\"files[]\"></span><br><br><div id=\"progress\" class=\"progress\"><div class=\"progress-bar progress-bar-success\"></div></div><div id=\"files\" class=\"files\"></div><div ng-show=\"showHideDownloadLink\"><ul id=\"attachmentList\"></ul><input type=\"hidden\" class=\"form-control pull-right\" id=\"attachmentSet\" name=\"control_" + obj2.id + "\"></div>";
						  controlArray.push("control_" + obj2.id);
						  break;
					  }
					  
					  $scope.controlArray = controlArray;
					  
					  $("#vessel").append("<div class=\"form-group\"><label for=\"inputPassword3\" class=\"col-sm-2 control-label\">" + requirefield + obj2.label + "</label><div class=\"col-sm-10\">" +
							  widget + "</div></div>");
					  
					  $scope.applySummary = "";
					  $("#myModalDynamicApply").modal({backdrop: 'static'});
					  
					  //Date picker
					  $('#datepicker').datepicker({
						  autoclose: true,
						  //todayBtn: true,
						  language: "zh-CN"
					  });
					  
					  //Date range picker
					  $('#reservation').daterangepicker({
						  "timePicker": true,
						    "timePicker24Hour": true,
						    "locale": {
						        "format": "YYYY/MM/DD HH:mm",
						        "separator": " - ",
						        "applyLabel": "确认",
						        "cancelLabel": "取消",
						        "fromLabel": "从",
						        "toLabel": "到",
						        "customRangeLabel": "Custom",
						        "weekLabel": "W",
						        "daysOfWeek": [
						            "日",
						            "一",
						            "二",
						            "三",
						            "四",
						            "五",
						            "六"
						        ],
						        "monthNames": [
						            "一月",
						            "二月",
						            "三月",
						            "四月",
						            "五月",
						            "六月",
						            "七月",
						            "八月",
						            "九月",
						            "十月",
						            "十一月",
						            "十二月"
						        ],
						        "firstDay": 1
						    }
					  });
					  $('#reservation').val("");
					  
					  var uploadId = 0;
					  $('#fileupload').fileupload({
					        url: "UploadifyServlet",
					        dataType: 'json',
					        add: function (e, data) {
					        	showLoading(true);
					        	data.submit();
					        },
					        done: function (e, data) {
					        	showLoading(false);
					        	if(data.result.success) {
					        		$scope.showHideDownloadLink = true;
					        		$scope.$apply();
					        		
					        		$("#attachmentList").append("<li id=\"uploadId_" + uploadId + "\"><a name=\"fileDetail\" href=\"" + data.result.downloadUrl + "\" target=\"_blank\">" + data.result.fileName + "</a>&nbsp;&nbsp;&nbsp;<a href=\"#\" onClick=\"delli(" + uploadId + ")\">删除</a></li>");
					        		uploadId ++;
					        		
					        		var temp = "";
					        		$("ul li a[name=\"fileDetail\"]").each(function() {
					        			if($(this).val() != null) {
					        				temp += $(this).html() + "," + $(this).attr("href");
					        				temp += ";";
					        			}
					        		});
					        		
					        		$("#attachmentSet").val(temp);
					        		
					        	} else {
					        		layer.alert("上传失败: " + data.result.msg);
					        		$('#progress .progress-bar').css('width', 0 + '%');
					        	}
					        },
					        progressall: function (e, data) {
					            var progress = parseInt(data.loaded / data.total * 100, 10);
					            $('#progress .progress-bar').css(
					                'width',
					                progress + '%'
					            );
					        }
					    });
				  }
			  }
			  else
				  alert("查询自定义表单失败!");
			  
	    	 
		  }).error(function(a, b, c, d){
			  alert("读取服务端失败: " + b);
		  });
	  },
	  
	  $http.get(_serverPath + "/Commander?beanId=workflowService&bizType=query", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }})
	     .success(function (response) {
	    	$scope.workflowList = response.data;
	    	
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $scope.showEdit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $scope.wfiId = rows[0].id;
		  $http.get(_serverPath + "/Commander?beanId=workflowService&bizType=queryFromAndData4Edit", {
			  params: {
				  wfiId: $scope.wfiId
				  
		  }}).success(function (response) {
			  if(response.success) {
				  $scope.apply = {};
				  $scope.apply.udfid = response.workflow.udfid;
				  $scope.workflowName = response.workflow.name;
				  $("#editFromAndData").empty();
				  $scope.auditStep = response.auditStep;
				  
				  $scope.applySummary = rows[0].summary;	
				  var controlArray = new Array();
				  var jsonList = response.data;
				  
				  for(var obj in jsonList) {
					  var obj2 = jsonList[obj];
					  
					  console.log(obj2.label);
					  var requirefield = "";
					  if(obj2.requirefield == 1)
						  requirefield = "<font color=\"red\">*</font>&nbsp;&nbsp;&nbsp;";
					  
					  var widget = "<input type=\"text\" class=\"form-control\" />";
					  var widgetType = parseInt(obj2.widgettype);
					  console.log("widgetType: " + widgetType);
					  switch(widgetType) {
					  case 1:
						  widget = "<input tye=\"text\" class=\"form-control\" name=\"control_" + obj2.id + "\" />";
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 2:
						  widget = "<textarea rows=\"3\" cols=\"20\" class=\"form-control\" name=\"control_" + obj2.id + "\"></textarea>";
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 3:
						  var controlValue = obj2.controlvalue.split(";");
						  var temp = "";
						  for(var v in controlValue) {
							  temp += "<input type=\"radio\" name=\"control_" + obj2.id + "\" value=\"" + controlValue[v] + "\" />" +  controlValue[v] ;
							  temp += "&nbsp;&nbsp;&nbsp;&nbsp;";
						  }
						  widget = temp;
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 4:
						  var controlValue = obj2.controlvalue.split(";");
						  var temp = "";
						  for(var v in controlValue) {
							  temp += "<input type=\"checkBox\" name=\"control_" + obj2.id + "\" value=\"" + controlValue[v] + "\" />" +  controlValue[v] ;
							  temp += "&nbsp;&nbsp;&nbsp;&nbsp;";
						  }
						  widget = temp;
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 5:
						  if(obj2.controlvalue == undefined)
							  break;
						  
						  var controlValue = obj2.controlvalue.split(";");
						  var temp = "<select class=\"form-control\" name=\"control_" + obj2.id + "\">";
						  
						  for(var v in controlValue) {
							  temp += "<option value=\"" + controlValue[v] + "\">" + controlValue[v] + "</option>";
						  }
						  temp += "</select>"; 
						  
						  widget = temp;
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 6:
						  widget = "<input type=\"text\" class=\"form-control pull-right\" id=\"datepicker\" name=\"control_" + obj2.id + "\">";
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 7:
						  widget = "<input type=\"text\" class=\"form-control pull-right\" id=\"reservation\" name=\"control_" + obj2.id + "\">";
						  controlArray.push("control_" + obj2.id);
						  break;
						  
					  case 8:
						  widget = "<span class=\"btn btn-success fileinput-button\"><i class=\"glyphicon glyphicon-plus\"></i><span>选择文件...</span><input id=\"fileupload\" type=\"file\" name=\"control_" + obj2.id + "\" multiple></span><br><br><div id=\"progress\" class=\"progress\"><div class=\"progress-bar progress-bar-success\"></div></div><div id=\"files\" class=\"files\"></div><div ng-show=\"showHideDownloadLink\"><a href=\"#\" id=\"applyCreate_downloadUrl\" target=\"_blank\" >查看上传文件</a></div>";
						  controlArray.push("control_" + obj2.id);
						  break;
					  }
					  
					  $scope.controlArray = controlArray;
					  
					  $("#editFromAndData").append("<div class=\"form-group\"><label for=\"inputPassword3\" class=\"col-sm-2 control-label\">" + requirefield + obj2.label + "</label><div class=\"col-sm-10\">" +
							  widget + "</div></div>");
					  
					  $("#myModal4edit").modal({backdrop: 'static'});
					  
					  //Date picker
					  $('#datepicker').datepicker({
						  autoclose: true,
						  //todayBtn: true,
						  language: "zh-CN"
					  });
					  
					  //Date range picker
					  $('#reservation').daterangepicker({
						  "timePicker": true,
						    "timePicker24Hour": true,
						    "locale": {
						        "format": "YYYY/MM/DD HH:mm",
						        "separator": " - ",
						        "applyLabel": "确认",
						        "cancelLabel": "取消",
						        "fromLabel": "从",
						        "toLabel": "到",
						        "customRangeLabel": "Custom",
						        "weekLabel": "W",
						        "daysOfWeek": [
						            "日",
						            "一",
						            "二",
						            "三",
						            "四",
						            "五",
						            "六"
						        ],
						        "monthNames": [
						            "一月",
						            "二月",
						            "三月",
						            "四月",
						            "五月",
						            "六月",
						            "七月",
						            "八月",
						            "九月",
						            "十月",
						            "十一月",
						            "十二月"
						        ],
						        "firstDay": 1
						    }
					  });
					  $('#reservation').val("");
					  
					  $('#fileupload').fileupload({
					        url: "UploadifyServlet",
					        dataType: 'json',
					        done: function (e, data) {
					        	if(data.result.success) {
					        		$scope.showHideDownloadLink = true;
					        		$scope.$apply();
					        		layer.alert("上传成功");
					        		//$scope.downloadUrl = data.result.downloadUrl;
					        		$("#applyCreate_downloadUrl").attr("href", data.result.downloadUrl);
					        		$scope.attachmentUrl = data.result.downloadUrl;
					        	}
					        },
					        progressall: function (e, data) {
					            var progress = parseInt(data.loaded / data.total * 100, 10);
					            $('#progress .progress-bar').css(
					                'width',
					                progress + '%'
					            );
					        }
					    }).prop('disabled', !$.support.fileInput)
					        .parent().addClass($.support.fileInput ? undefined : 'disabled');
				  }
			  }
			  else
				  alert("查询自定义表单失败!");
			  
	    	 
		  }).error(function(a, b, c, d){
			  alert("读取服务端失败: " + b);
		  });
		  
		  var table2 = $('#historyAuditTable').DataTable({
  			"ordering": false,
  	        "info":     false,
  		  "paging":   false,
		      "lengthChange": false,
		      "searching": false,
		      "processing": true,
		      "serverSide": true,
		      "ajax": _serverPath + "/Commander?beanId=workflowInstanceService&bizType=queryHistoryAuditData&wfiId=" + rows[0].id,
		      "columns": [
		                  {"data": "index"},
		                  {"data": "nodeName" ,"defaultContent": ""},
		                  {"data": "auditer" ,"defaultContent": ""},
		                  {"data": "action" ,"defaultContent": ""},
		                  {"data": "remark" ,"defaultContent": ""},
		                  {"data": "createtime" ,"defaultContent": "", 
		                	  "render": function ( data, type, full, meta ) {
		                		  return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
		                  }}
		              ]
		    });
  		
  			table2.destroy();
	  };
	  
	  $scope.edit = function() {
		  $http.get(_serverPath + "/Commander?beanId=userService&bizType=edit", {
			  params: {
				  id: $scope.id,
				  name: encodeURI(encodeURI($scope.name)), 
				  url: $scope.url
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		alert("操作成功");
		    		 $('#example2').DataTable().draw();
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.showDeleteConfrim = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $("#myModalConfrim").modal({backdrop: 'static'});
		 
	  };
	  
	  $scope.move2recycleBin = function() {
		  var rows = table.rows('.selected').data();
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=move2recycleBin", {
			  params: {
				  id: rows[0].id
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		alert("删除成功");
		    		$('#example2').DataTable().draw();
		    	}
		    	else
		    		alert("删除失败, 流程已开始审批!");
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("异常: " + b);
		     });
	  };
	  
	  $scope.del = function() {
		  var rows = table.rows('.selected').data();
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=delete", {
			  params: {
				  id: rows[0].id
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		alert("删除成功");
		    		$('#example2').DataTable().draw();
		    	}
		    	else
		    		alert("删除失败");
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("异常: " + b);
		     });
	  };
	  
	  $scope.onFileSelect = function($files) {    //$files: an array of files selected, each file has name, size, and type.
	    for (var i = 0; i < $files.length; i++) {      var file = $files[i];
	      $scope.upload = $upload.upload({
	        url: 'server/upload/url', //upload.php script, node.js route, or servlet url
	        //method: 'POST' or 'PUT',
	        //headers: {'header-key': 'header-value'},
	        //withCredentials: true,
	        data: {myObj: $scope.myModelObj},
	        file: file, // or list of files ($files) for html5 only
	        //fileName: 'doc.jpg' or ['1.jpg', '2.jpg', ...] // to modify the name of the file(s)
	        // customize file formData name ('Content-Disposition'), server side file variable name. 
	        //fileFormDataName: myFile, //or a list of names for multiple files (html5). Default is 'file' 
	        // customize how data is added to formData. See #40#issuecomment-28612000 for sample code
	        //formDataAppender: function(formData, key, val){}
	      }).progress(function(evt) {        console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
	      }).success(function(data, status, headers, config) {        // file is uploaded successfully
	        console.log(data);
	      });      //.error(...)
	      //.then(success, error, progress); 
	      // access or attach event listeners to the underlying XMLHttpRequest.
	      //.xhr(function(xhr){xhr.upload.addEventListener(...)})
	    }    /* alternative way of uploading, send the file binary with the file's content-type.       Could be used to upload files to CouchDB, imgur, etc... html5 FileReader is needed.        It could also be used to monitor the progress of a normal http post/put request with large data*/
	    // $scope.upload = $upload.http({...})  see 88#issuecomment-31366487 for sample code.
	  };
	  
	  $scope.archiving = function() {
		  var rows = table.rows('.selected').data();
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=archiving", {
			  params: {
				  id: rows[0].id
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		layer.alert("操作成功");
		    		 $('#example2').DataTable().draw();
		    		 $("#myModal4edit").modal("hide");
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
     
  });