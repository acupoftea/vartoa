var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $scope.showDetail = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=queryAuditData", {
			  params: {
				   wfiId: rows[0].id
		  }})
		     .success(function (response) {
		    	if(response.success) {
		    		$("#auditForm").empty();
		    		
		    		for(var obj in response.data) {
		    			var obj2 = response.data[obj];
			    		var widget = "<input type=\"text\" class=\"form-control\" value=\"" + obj2.value + "\"/>";
			    		if(obj2.type == 8)
			    			widget = "<a href=\"" + obj2.value + "\">附件</a>";
			    		
			    		$("#auditForm").append("<div class=\"form-group\"><label for=\"inputPassword3\" class=\"col-sm-2 control-label\">" + obj2.label + "</label><div class=\"col-sm-10\">" +
								  widget + "</div></div>");
		    		}
		    		
		    		$("#myModal4Detail").modal({backdrop: 'static'});
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
     
  });