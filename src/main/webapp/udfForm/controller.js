var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $scope.initAddControl = function() {
		  $scope.isRequireField = -1;
		  $scope.controlLabel = "";
		  $scope.selectedControl = -1;
		  $scope.controlValue = "";
	  };
	  
	  $scope.deleteAttachment = function(from) {
		  
		  $http.get(_serverPath + "/Commander?beanId=userDefinedFormService&bizType=deleteAttachment", {
			  params: {
				  id: $scope.udiForm.id
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		layer.alert("删除成功");
		    		$scope.showHideDownloadLink = false;
		    		$('#progress .progress-bar').css('width',0 + '%');
	        		//$scope.$apply();
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.showHideDownloadLink = false;
	  
	  $('#fileupload').fileupload({
	        url: "UploadifyServlet",
	        dataType: 'json',
	        done: function (e, data) {
	        	if(data.result.success) {
	        		$scope.showHideDownloadLink = true;
	        		$scope.$apply();
	        		layer.alert("上传成功");
	        		$("#udfEdit_downloadUrl").attr("href", data.result.downloadUrl);
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
	  
	  $('#fileupload4create').fileupload({
	        url: "UploadifyServlet",
	        dataType: 'json',
	        done: function (e, data) {
	        	if(data.result.success) {
	        		$scope.showHideDownloadLink = true;
	        		$scope.$apply();
	        		layer.alert("上传成功");
	        		$("#udfCreate_downloadUrl").attr("href", data.result.downloadUrl);
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
  
	  showChangeControlWin = function(arg0, arg1) {
		  $scope.selectControlNameId = arg0;
		  $scope.selectControlValueId = arg1;
		  
		  $("#myModalChangeContrlType").modal();
	  };
	  
	  $scope.changeControlType = function() {
		  var selectControl = "单行文本";
		  var controlId = 1;
		  switch(parseInt($scope.selectedControl)) {
		  case 1:
			  selectControl = "单行文本";
			  controlId = 1;
			  break;
			  
		  case 2:
			  selectControl = "多行文本";
			  controlId = 2;
			  break;
			  
		  case 3:
			  selectControl = "单选框";
			  controlId = 3;
			  break;
			  
		  case 4:
			  selectControl = "复选框";
			  controlId = 4;
			  break;
			  
		  case 5:
			  selectControl = "下拉菜单";
			  controlId = 5;
			  break;
			  
		  case 6:
			  selectControl = "日期";
			  controlId = 6;
			  break;
			  
		  case 7:
			  selectControl = "日期区间";
			  controlId = 7;
			  break;
			  
		  case 8:
			  selectControl = "附件";
			  controlId = 8;
			  break;
		  }
		  
		  $($scope.selectControlNameId).val(selectControl);
		  $($scope.selectControlValueId).val(controlId);
	  };
	  
	  $scope.create = function() {
		  var arryObj = new Array();
		  
		  $("#udfTable").find("tr").each(function() {
			  var index = 0;
			  var inputObj = {};
			  
			  $(this).find("td").each(function() {
				  $(this).find("input").each(function() {
					  console.log("index: " + index);
					  console.log("val: " + $(this).val());
					  
					  if(index == 0) {
						  inputObj.sequence = $(this).val();
					  }
					  if(index == 1) {
						  inputObj.isRequireField = $(this).val();
					  }
					  if(index == 2) {
						  inputObj.controlLabel = $(this).val();
					  }
					  if(index == 4) {
						  inputObj.controlType = $(this).val();
					  }
					  if(index == 5) {
						  inputObj.controlValue = $(this).val();
					  }
					  
					  index++;
					  
				  });
			  });
			  
			  if(inputObj.isRequireField != null)
				  arryObj.push(inputObj);
		  });
		  
		  $scope.udiForm = {};
		  $scope.udiForm.companyId = companyId;
		  $scope.udiForm.name = $scope.formName;
		  $scope.udiForm.formList = arryObj;
		  $scope.udiForm.attachmentUrl = $scope.attachmentUrl;
		  
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=userDefinedFormService&bizType=create", {
			  params: {
				  formList: encodeURI(encodeURI(JSON.stringify($scope.udiForm)))
			  }
		  }).success(function (response) {
			  showLoading(false);
		    	if(response.success) {
		    		layer.alert("操作成功");
		    		$('#example2').DataTable().draw();
		    		$("#myModalCreate").modal("hide");
		    	}
		    	else
		    		layer.alert("操作失败!");
		    	 
		     }).error(function(a, b, c, d){
		    	 showLoading(false);
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.addControl = function() {
		  var requireField = "<font color=\"red\">*</font>";
		  var isRequireField = 1;
		  
		  if($scope.isRequireField == 2) {
			  requireField = "&nbsp;"
			  isRequireField = 2;
		  }
			  
		  var selectControl = "单行文本";
		  var controlId = 1;
		  switch(parseInt($scope.selectedControl)) {
		  case 1:
			  selectControl = "单行文本";
			  controlId = 1;
			  break;
			  
		  case 2:
			  selectControl = "多行文本";
			  controlId = 2;
			  break;
			  
		  case 3:
			  selectControl = "单选框";
			  controlId = 3;
			  break;
			  
		  case 4:
			  selectControl = "复选框";
			  controlId = 4;
			  break;
			  
		  case 5:
			  selectControl = "下拉菜单";
			  controlId = 5;
			  break;
			  
		  case 6:
			  selectControl = "日期";
			  controlId = 6;
			  break;
			  
		  case 7:
			  selectControl = "日期区间";
			  controlId = 7;
			  break;
			  
		  case 8:
			  selectControl = "附件";
			  controlId = 8;
			  break;
		  }
			  
		  
		  if($("#selectControlSrc2").data("src") == "edit") {
			  $("#udfTable4Edit").append("" +
		  		"<tr id=tr_" + index + ">" +
                	"<td><input style=\"width:50px\" type=\"text\" value=\"" + index + "\"></td>" +
                	"<td><input type=\"hidden\" value=\"" + isRequireField + "\" />" + requireField + "</td>" +
                	"<td><input type=\"text\" value=\"" + $scope.controlLabel + "\" /></td>" +
                	"<td><input type=\"text\" id=\"selectControlNameId_" + index + "\" value=\"" + selectControl + "\">" +
					"<input type=\"hidden\" id=\"selectControlValueId_" + index + "\" value=\"" + controlId + "\" />" +
						"<a href=\"#\" onClick=\"showChangeControlWin(selectControlNameId_" + index + ", selectControlValueId_" + index + ")\">&nbsp;修改</a></td>" +
        			"<td><input type=\"text\" value=\"" + $scope.controlValue + "\">" +
                	"<td><a href=\"#\" onClick=\"deltr(" + index + ")\">删除</a></td>" +
                "</tr>");
			  
		  } 
		  
		  if($("#selectControlSrc").data("src") == "create") {
			  $("#udfTable").append("" +
				  "<tr id=tr_" + index + ">" +
                	"<td><input style=\"width:50px\" type=\"text\" value=\"" + index + "\"></td>" +
                	"<td><input type=\"hidden\" value=\"" + isRequireField + "\" />" + requireField + "</td>" +
                	"<td><input type=\"text\" value=\"" + $scope.controlLabel + "\" /></td>" +
                	"<td><input type=\"text\" id=\"selectControlNameId_" + index + "\" value=\"" + selectControl + "\">" +
					"<input type=\"hidden\" id=\"selectControlValueId_" + index + "\" value=\"" + controlId + "\" />" +
						"<a href=\"#\" onClick=\"showChangeControlWin(selectControlNameId_" + index + ", selectControlValueId_" + index + ")\">&nbsp;修改</a></td>" +
        			"<td><input type=\"text\" value=\"" + $scope.controlValue + "\">" +
                	"<td><a href=\"#\" onClick=\"deltr(" + index + ")\">删除</a></td>" +
                "</tr>");
		  }
		  
		  index++;
		  
	  };
	  
	  $scope.selectYesNo = [{
	      id: '1',
	      name: '是'
	    },
	    {
	      id: '2',
	      name: '否'
	    }];
	  
	  $scope.controlList = [{
	      id: '1',
	      name: '单行文本'
	    },
	    {
	      id: '2',
	      name: '多行文本'
	    },
	    {
	      id: '3',
	      name: '单选框'
	    },
	    {
	      id: '4',
	      name: '复选框'
	    },
	    {
	      id: '5',
	      name: '下拉菜单'
	    },
	    {
	      id: '6',
	      name: '日期'
	    },
	    {
	      id: '7',
	      name: '日期区间'
	    },
	    {
	      id: '8',
	      name: '附件'
	    }];
	  
	  deltr = function(index) {
		  var tr = $("tr[id=\"tr_" + index + "\"]");
		  tr.remove();
		  index--;
	  };
	  
	  $scope.showEdit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $scope.formName = rows[0].name;
		  $("#udfTable4Edit tbody").empty();
		  $('#progress .progress-bar').css('width',0 + '%');
		  
		  $scope.udiForm = {};
		  $scope.udiForm.id = rows[0].id;
		  $scope.attachmentUrl = "";
		  
		  if(rows[0].attachmenturl != null) {
			  $scope.showHideDownloadLink = true;
	  		  $("#udfEdit_downloadUrl").attr("href", rows[0].attachmenturl);
	  		  $scope.attachmentUrl = rows[0].attachmenturl;
		  } else
			  $scope.showHideDownloadLink = false;
		  
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=userDefinedFormService&bizType=queryUdfList", {
			  params: {
				  udfId: rows[0].id
			  }
		  }).success(function (response) {
			  showLoading(false);
		    	if(response.success) {
		    		
		    		json = eval(response.data)
		    		index = 1;
		    		for(var i=0; i<json.length; i++) {
		    			var obj = json[i];
		    			
		    			 var requireField = "<font color=\"red\">*</font>";
		    			 if(obj.requirefield  == 2)
		    				 requireField = "&nbsp;"
		    					 
		    					 var selectControl = "单行文本";
		    				  var controlId = 1;
		    				  switch(parseInt(obj.widgettype)) {
		    				  case 1:
		    					  selectControl = "单行文本";
		    					  controlId = 1;
		    					  break;
		    					  
		    				  case 2:
		    					  selectControl = "多行文本";
		    					  controlId = 2;
		    					  break;
		    					  
		    				  case 3:
		    					  selectControl = "单选框";
		    					  controlId = 3;
		    					  break;
		    					  
		    				  case 4:
		    					  selectControl = "复选框";
		    					  controlId = 4;
		    					  break;
		    					  
		    				  case 5:
		    					  selectControl = "下拉菜单";
		    					  controlId = 5;
		    					  break;
		    					  
		    				  case 6:
		    					  selectControl = "日期";
		    					  controlId = 6;
		    					  break;
		    					  
		    				  case 7:
		    					  selectControl = "日期区间";
		    					  controlId = 7;
		    					  break;
		    					  
		    				  case 8:
		    					  selectControl = "附件";
		    					  controlId = 8;
		    					  break;
		    				  }
		    			
		    			$("#udfTable4Edit").append("" +
						  		"<tr id=tr_" + index + ">" +
				                	"<td><input style=\"width:50px\" type=\"text\" value=\"" + obj.sequence + "\"></td>" +
				                	"<td><input type=\"hidden\" value=\"" + obj.requirefield + "\" />" + requireField + "</td>" +
				                	"<td><input type=\"text\" value=\"" + obj.label + "\" /></td>" +
				        			"<td><input type=\"text\" id=\"selectControlNameId_" + index + "\" value=\"" + selectControl + "\">" +
				        					"<input type=\"hidden\" id=\"selectControlValueId_" + index + "\" value=\"" + controlId + "\" />" +
				        			"<a href=\"#\" onClick=\"showChangeControlWin(selectControlNameId_" + index + ", selectControlValueId_" + index + ")\">&nbsp;修改</a></td>" +
				        			"<td><input type=\"text\" value=\"" + obj.controlvalue + "\">" +
				                	"<td><a href=\"#\" onClick=\"deltr(" + index + ")\">删除</a></td>" +
				                "</tr>");
		    			index++; 
		    		}
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 showLoading(false);
		    	 alert("读取服务端失败: " + b);
		     });
		  
		  $("#myModal4edit").modal();
	  };
	  
	  $scope.showCreate = function() {
		  index = 0;
		  $scope.formName = "";
		  $("#udfTable tbody").empty();
		  $('#progress .progress-bar').css('width',0 + '%');
		  $("#myModalCreate").modal();
	  };
	  
	  $scope.edit = function() {
		  var arryObj = new Array();
		  
		  $("#udfTable4Edit").find("tr").each(function() {
			  var index = 0;
			  var inputObj = {};
			  
			  $(this).find("td").each(function() {
				  $(this).find("input").each(function() {
					  console.log("index: " + index);
					  console.log("val: " + $(this).val());
					  
					  if(index == 0) {
						  inputObj.sequence = $(this).val();
					  }
					  if(index == 1) {
						  inputObj.isRequireField = $(this).val();
					  }
					  if(index == 2) {
						  inputObj.controlLabel = $(this).val();
					  }
					  if(index == 4) {
						  inputObj.controlType = $(this).val();
					  }
					  if(index == 5) {
						  inputObj.controlValue = $(this).val();
					  }
					  
					  index++;
					  
				  });
			  });
			  
			  if(inputObj.isRequireField != null)
				  arryObj.push(inputObj);
		  });
		  
		  
		  $scope.udiForm.name = $scope.formName;
		  $scope.udiForm.formList = arryObj;
		  $scope.udiForm.attachmentUrl = $scope.attachmentUrl;
		  
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=userDefinedFormService&bizType=edit", {
			  params: {
				  formList: encodeURI(encodeURI(JSON.stringify($scope.udiForm)))
			  }
		  }).success(function (response) {
			  showLoading(false);
		    	if(response.success) {
		    		layer.alert("操作成功");
		    		$('#example2').DataTable().draw();
		    		//$("#myModal4edit").modal("hide");
		    	}
		    	else
		    		layer.alert("操作失败!");
		    	 
		     }).error(function(a, b, c, d){
		    	 showLoading(false);
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.checkBoxSelectAllOrNot = function() {
		  if($scope.selectAllCheckbox) {
			  
		  } else {
			  
		  }
			  
	  };
	  
	  $scope.del = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  } 
		  
		  $http.get(_serverPath + "/Commander?beanId=userDefinedFormService&bizType=delete", {
			  params: {
				  id: rows[0].id
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		//alert("删除成功");
		    		$('#example2').DataTable().draw();
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
     
  });