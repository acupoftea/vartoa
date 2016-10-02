var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $scope.create = function() {
		  $scope.workflowType = {};
		  $scope.workflowType.companyId = companyId;
		  $scope.workflowType.name = $scope.workflowTypeName;
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowTypeService&bizType=create", {
			  params: {
				  workflowType: encodeURI(encodeURI(JSON.stringify($scope.workflowType)))
		  }})
		     .success(function (response) {
		    	if(response.success) {
		    		alert("新建成功");
		    		 $('#example2').DataTable().draw();
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.showEdit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  $scope.workflowType = {};
		  $scope.workflowType.id = rows[0].id;
		  $scope.workflowType.name = rows[0].name;
		  
		  $("#myModal4edit").modal();
	  };
	  
	  $scope.edit = function() {
		  $http.get(_serverPath + "/Commander?beanId=workflowTypeService&bizType=edit", {
			  params: {
				  workflowType: encodeURI(encodeURI(JSON.stringify($scope.workflowType)))
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
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowTypeService&bizType=delete", {
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