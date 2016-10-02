var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $scope.showDeleteConfrim = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $("#myModalConfrim").modal();
		 
	  };
	  
	  $scope.edit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=recover4recycleBin", {
			  params: {
				  id: rows[0].id
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
     
  });