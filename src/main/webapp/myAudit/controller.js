var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $scope.auditList = [{key: "通过", value: 1}, {key: "驳回", value: 0},{key: "已执行", value: 2}];
	  
	  downloadFile = function(downloadPath, fileName) {
		var aLink = document.createElement('a');
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", false, false);
		aLink.href = downloadPath;
		aLink.download = fileName;
		aLink.target="_blank";
		aLink.dispatchEvent(evt);
	  };
	  
	  $scope.showAudit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $scope.audit = {};
		  $scope.audit.id = rows[0].id;
		  $scope.audit.auditer = userId;
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=queryAuditData", {
			  params: {
				   wfiId: rows[0].id
		  }})
		     .success(function (response) {
		    	if(response.success) {
		    		$scope.audit.title = response.wfi.workflowName;
		    		$("#auditForm").empty();
		    		
		    		for(var obj in response.data) {
		    			var obj2 = response.data[obj];
			    		var widget = "<input type=\"text\" class=\"form-control\" value=\"" + obj2.value + "\"/>";
			    		if(obj2.type == 8) {
			    			if(obj2.value == null)
			    				widget = "";
			    			else {
			    				widget = "<ul>"
			    					
			    				var data = obj2.value;
			    				var data2 = data.split(";");
			    				for(var i in data2) {
			    					if(data2[i].split(",")[0] == "")
			    						continue;
			    					
			    					var data3 = data2[i].split(",");
			    					//widget += "<li><a href=\"javascript:downloadFile('" + data3[1].toUpperCase() + "', '" + data3[0] + "')\" target=\"_blank\">" + data3[0] + "</a></li>";
			    					if(data3.length > 1)
			    						widget += "<li><a href=\"" + data3[1].toUpperCase() + "\" target=\"_blank\">" + data3[0] + "</a></li>";
			    				}
			    				
			    				widget += "</ul>";
			    			}
			    		}
			    		
			    		$("#auditForm").append("<div class=\"form-group\"><label for=\"inputPassword3\" class=\"col-sm-2 control-label\">" + obj2.label + "</label><div class=\"col-sm-10\">" +
								  widget + "</div></div>");
		    		}
		    		
		    		$("#myModal4audit").modal({backdrop: 'static'});
		    	} else
		    		alert("查询表单数据失败");
		    	 
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
	  
	  $scope.startAudit = function() {
		  showLoading(true);
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=audit", {
			  params: {
				   audit: encodeURI(encodeURI(JSON.stringify($scope.audit)))
		  }})
		     .success(function (response) {
		    	 showLoading(false);
		    	if(response.success) {
		    		
		    		layer.alert("审核成功");
		    		 $('#example2').DataTable().draw();
		    		 $("#myModal4audit").modal("hide"); 
		    	}
		    	else
		    		layer.alert("审核失败, 检查必填字段");
		    	 
		     }).error(function(a, b, c, d){
		    	 showLoading(false);
		    	 layer.alert("读取服务端失败: " + b);
		     });
	  };
     
  });