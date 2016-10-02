var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  
	  $http.get(_serverPath + "/Commander?beanId=workTimeService&bizType=query", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }})
	     .success(function (response) {
	    	$scope.workTimeList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $http.get(_serverPath + "/Commander?beanId=roleService&bizType=query", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }})
	     .success(function (response) {
	    	$scope.roleList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $http.get(_serverPath + "/Commander?beanId=deparmentService&bizType=query", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }})
	     .success(function (response) {
	    	$scope.deparmentList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $scope.showCreate = function() {
		  $scope.user = {};
		  $("#myModal").modal();
	  };
	  
	  $scope.create = function() {
		  //var abc = testFormValid.userName.$valid;
		  
		  $scope.user.companyId = companyId;
		  
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=userService&bizType=create", {
			  params: {
				  user: encodeURI(encodeURI(JSON.stringify($scope.user)))
		  }})
		     .success(function (response) {
		    	 showLoading(false);
		    	if(response.success) {
		    		layer.alert("新建成功, 默认密码123456");
		    		 $('#example2').DataTable().draw();
		    		 $("#myModal").modal("hide");
		    	}
		    	else
		    		layer.alert("失败: " + response.msg);
		    	 
		     }).error(function(a, b, c, d){
		    	 showLoading(false);
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.showEdit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $scope.user = rows[0];
		  
		  $("#myModal4edit").modal();
	  };
	  
	  $scope.edit = function() {
		  showLoading(true);
		  $http.get(_serverPath + "/Commander?beanId=userService&bizType=edit", {
			  params: {
				  user: encodeURI(encodeURI(JSON.stringify($scope.user)))
			  }
		  })
		     .success(function (response) {
		    	 showLoading(false);
		    	if(response.success) {
		    		layer.alert("操作成功");
		    		 $('#example2').DataTable().draw();
		    		 $("#myModal4edit").modal("hide");
		    	} else
		    		layer.alert(response.msg);
		    	 
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
		  
		  $http.get(_serverPath + "/Commander?beanId=userService&bizType=delete", {
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