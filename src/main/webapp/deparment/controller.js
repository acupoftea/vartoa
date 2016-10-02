var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $scope.create = function() {
		  $scope.deparment = {};
		  $scope.deparment.companyId = companyId;
		  $scope.deparment.name = $scope.deparmentName;
		  
		  $http.get(_serverPath + "/Commander?beanId=deparmentService&bizType=create", {
			  params: {
				  deparment: encodeURI(encodeURI(JSON.stringify($scope.deparment)))
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
		  
		  $scope.deparment = {};
		  $scope.deparment.companyId = companyId;
		  $scope.deparment.id = rows[0].id;
		  $scope.deparmentName = rows[0].name;
		  
		  $("#myModal4edit").modal();
	  };
	  
	  $scope.edit = function() {
		  $scope.deparment.name = $scope.deparmentName;
		  
		  $http.get(_serverPath + "/Commander?beanId=deparmentService&bizType=edit", {
			  params: {
				  deparment: encodeURI(encodeURI(JSON.stringify($scope.deparment)))
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
		  
		  $http.get(_serverPath + "/Commander?beanId=deparmentService&bizType=delete", {
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