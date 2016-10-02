var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  
	  $http.get(_serverPath + "/Commander?beanId=userDefinedFormService&bizType=query", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }}).success(function (response) {
    	$scope.udfList = response.data;
    	 
	  }).error(function(a, b, c, d){
    	 alert("异常: " + b);
	  });
	  
	  showSelectUserWin = function(userName, userId) {
		  $scope.userNameControlId = userName;
		  $scope.userIdControlId = userId;
		  
		  $("#myModalUserList2").draggable({
			handle: '.modal-header'
		  });
		  
		  $scope.employeeList2 = [{id: "1", username: "张三"}, {id: "2", username: "李四"},{id: "3", username: "王二麻子"}];
		  $scope.apply();
	  };
	  
	  $scope.selectAuditer = function() {
		  var selectUserId = $scope.selectedUser;
		  
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=userService&bizType=queryUserById", {
			  params: {
				  selectUserId: selectUserId
				  
		  }}).success(function (response) {
			  showLoading(false);
			  $($scope.userNameControlId).val(response.data.username);
			  $($scope.userIdControlId).val($scope.selectedUser);
	    	 
		  }).error(function(a, b, c, d){
			  showLoading(false);
	    	 alert("异常: " + b);
		  });
	  };
	  
	  $scope.selectCCer = function() {
		  var ids = "";
		  $("#select2 option").each(function() {
			  //console.log($(this).attr("value"));
			  //ids += $(this).attr("value") + ",";
			  ids += eval($(this).val()) + ",";
			  
		  });
		  
		  // alert(selectUsersId);
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=userService&bizType=queryUsersByIds", {
			  params: {
				  ids: ids
				  
		  }}).success(function (response) {
			  showLoading(false);
			  $($scope.userNameControlId).val(response.data);
			  $($scope.userIdControlId).val(ids);
	    	 
		  }).error(function(a, b, c, d){
			  showLoading(false);
	    	 alert("异常: " + b);
		  });
	  };
	  
	  $http.get(_serverPath + "/Commander?beanId=userService&bizType=query4Company", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }}).success(function (response) {
    	$scope.employeeList = response.data;
    	 
	  }).error(function(a, b, c, d){
    	 alert("异常: " + b);
	  });
	  
	  var index = 1;
	  $scope.addOneRow = function() {
		  index++;
		  $("#workflowNodeTable").append("" +
		  		"<tr id=tr_" + index + ">" +
                	"<td>" + index + "</td>" +
                	"<td><input type=\"text\" value=\"第" + index + "级审核\"/></td>" +
                	"<td><input type=\"text\" id=\"auditer_" + index + "\" /><input type=\"hidden\" id=\"auditerId_" + index + "\" />" +
                			"<a data-toggle=\"modal\" data-target=\"#myModalUserList\" onClick=\"showSelectUserWin(auditer_" + index + ", auditerId_" + index + ")\">&nbsp;修改</a></td>" +
        			"<td><input type=\"text\" id=\"ccName_" + index + "\" readonly /><input type=\"hidden\" id=\"ccId_" + index + "\" />" +
        			"<a data-toggle=\"modal\" data-target=\"#myModalUserList2\" onClick=\"showSelectUserWin(ccName_" + index + ", ccId_" + index + ")\">&nbsp;修改</a></td>" +
                	"<td><a href=\"#\" onClick=\"deltr(" + index + ")\">删除</a></td>" +
                "</tr>");
	  };
	  
	  $scope.addOneRow4edit = function() {
		  $("#workflowNodeTable4edit").append("" +
		  		"<tr id=tr_" + index + ">" +
                	"<td>" + index + "</td>" +
                	"<td><input type=\"text\" value=\"第" + index + "级审核\"/></td>" +
                	"<td><input type=\"text\" id=\"auditer_" + index + "\" /><input type=\"hidden\" id=\"auditerId_" + index + "\" />" +
                			"<a data-toggle=\"modal\" data-target=\"#myModalUserList\" onClick=\"showSelectUserWin(auditer_" + index + ", auditerId_" + index + ")\">&nbsp;修改</a></td>" +
        			"<td><input type=\"text\" id=\"ccName_" + index + "\" readonly /><input type=\"hidden\" id=\"ccId_" + index + "\" />" +
        			"<a data-toggle=\"modal\" data-target=\"#myModalUserList2\" onClick=\"showSelectUserWin(ccName_" + index + ", ccId_" + index + ")\">&nbsp;修改</a></td>" +
                	"<td><a href=\"#\" onClick=\"deltr(" + index + ")\">删除</a></td>" +
                "</tr>");
		  index++;
	  };
	  
	  deltr = function(index) {
		  var tr = $("tr[id=\"tr_" + index + "\"]");
		  tr.remove();
		  index--;
	  };
	  
	  $http.get(_serverPath + "/Commander?beanId=workflowTypeService&bizType=query", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }}).success(function (response) {
    	$scope.workflowTypeList = response.data;
    	 
	  }).error(function(a, b, c, d){
    	 alert("异常: " + b);
	  });
	  
	  $scope.create = function() {
		  var arryObj = new Array();
		  
		  $("#workflowNodeTable").find("tr").each(function() {
			  var index = 0;
			  var inputObj = {};
			  
			  $(this).find("td").each(function() {
				  $(this).find("input").each(function() {
					  //console.log(index);
					  //console.log($(this).val());
					  
					  if(index == 0) {
						  inputObj.nodeName = $(this).val();
					  }
					  if(index == 2) {
						  inputObj.auditerId = $(this).val();
					  }
					  if(index == 4) {
						  inputObj.ccerId = $(this).val();
					  }
					  
					  index++;
					  
				  });
			  });
			  
			  if(inputObj.nodeName != null)
				  arryObj.push(inputObj);
		  });
		  
		  $scope.workflow = {};
		  $scope.workflow.companyId = companyId;
		  $scope.workflow.type = $scope.workflowType;
		  $scope.workflow.name = $scope.workflowName;
		  $scope.workflow.udfid = $scope.workflowForm;
		  $scope.workflow.nodeJson = arryObj;
		  
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=workflowService&bizType=create", {
			  params: {
				  workflow: encodeURI(encodeURI(JSON.stringify($scope.workflow)))
			  }
		  }).success(function (response) {
			  showLoading(false);
		    	if(response.success) {
		    		//layer.alert("操作成功");
		    		 $('#example2').DataTable().draw();
		    		 $("#myModalCreate").modal("hide");
		    	}
		    	else
		    		layer.alert("操作失败; " + response.msg);
		    	 
		     }).error(function(a, b, c, d){
		    	 showLoading(false);
		    	 layer.alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.showEdit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $scope.workflow = {};
		  $scope.workflow.id = rows[0].id;
		  
		  $scope.workflowName = rows[0].name;
		  $scope.workflowForm = rows[0].udfid;
		  
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=workflowService&bizType=queryworkflowNodeList", {
			  params: {
				  workflowId: rows[0].id
			  }
		  }).success(function (response) {
			  showLoading(false);
		    	if(response.success) {
		    		json = eval(response.data)
		    		$("#workflowNodeTable4edit tbody").empty();
		    		index = 1;
		    		for(var i=0; i<json.length; i++) {
		    			var obj = json[i];
		    			if(obj.sequence == 0 || obj.sequence == 100)
		    				continue;
		    			
		    			$("#workflowNodeTable4edit").append("" +
		    			  		"<tr id=tr_" + index + ">" +
		    	                	"<td>" + index + "</td>" +
		    	                	"<td><input type=\"text\" value=\"" + obj.nodename + "\"/></td>" +
		    	                	"<td><input type=\"text\" id=\"auditer_" + index + "\" value=\"" + obj.receiverName + "\" /><input type=\"hidden\" id=\"auditerId_" + index + "\" value=\"" + obj.receiver + "\" />" +
		    	                			"<a data-toggle=\"modal\" data-target=\"#myModalUserList\" onClick=\"showSelectUserWin(auditer_" + index + ", auditerId_" + index + ")\">&nbsp;修改</a></td>" +
		    	        			"<td><input type=\"text\" id=\"ccName_" + index + "\" value=\"" + ((obj.ccName == undefined) ? "" : obj.ccName) + "\" readonly /><input type=\"hidden\" id=\"ccId_" + index + "\" value=\"" + obj.ccReceiverId + "\" />" +
		    	        			"<a data-toggle=\"modal\" data-target=\"#myModalUserList2\" onClick=\"showSelectUserWin(ccName_" + index + ", ccId_" + index + ")\">&nbsp;修改</a></td>" +
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
	  
	  $scope.edit = function() {
		  var arryObj = new Array();
		  
		  $("#workflowNodeTable4edit").find("tr").each(function() {
			  var index = 0;
			  var inputObj = {};
			  
			  $(this).find("td").each(function() {
				  $(this).find("input").each(function() {
					  //console.log(index);
					  //console.log($(this).val());
					  
					  if(index == 0) {
						  inputObj.nodeName = $(this).val();
					  }
					  if(index == 2) {
						  inputObj.auditerId = $(this).val();
					  }
					  if(index == 3) {
						  inputObj.ccerList = $(this).val();
					  }
					  if(index == 4) {
						  inputObj.ccerId = $(this).val();
						  if(inputObj.ccerList == "")
							  inputObj.ccerId = "";
					  }
					  
					  index++;
					  
				  });
			  });
			  
			  if(inputObj.nodeName != null)
				  arryObj.push(inputObj);
		  });
		  
		  
		  $scope.workflow.name = $scope.workflowName;
		  $scope.workflow.udfid = $scope.workflowForm;
		  $scope.workflow.nodeJson = arryObj;
		  
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=workflowService&bizType=edit", {
			  params: {
				  workflow: encodeURI(encodeURI(JSON.stringify($scope.workflow)))
			  }
		  }).success(function (response) {
			  showLoading(false);
		    	if(response.success) {
		    		layer.alert("操作成功");
		    		 $('#example2').DataTable().draw(false);
		    		 $("#myModal4edit").modal("hide");
		    	}
		    	else
		    		layer.alert("操作失败; " + response.msg);
		    	 
		     }).error(function(a, b, c, d){
		    	 showLoading(false);
		    	 layer.alert("读取服务端失败: " + b);
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
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowService&bizType=delete", {
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