var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http, $sce) {
	  $scope.trustAsHtml = function(html) {
	      return $sce.trustAsHtml(html);
	  }; 
	  
	  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=queryAuditList", {
		  params: {
			  length: 10,
			  userId: userId
	  }})
	     .success(function (response) {
	    	$scope.auditList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $http.get(_serverPath + "/Commander?beanId=noticeService&bizType=query", {
		  params: {
			  length: 5,
			  companyId: companyId
	  }})
	     .success(function (response) {
	    	$scope.noticeList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $http.get(_serverPath + "/Commander?beanId=deparmentService&bizType=query", {
		  params: {
			  length: 100
			  
	  }})
	     .success(function (response) {
	    	$scope.deparmentList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $scope.create = function() {
		  $scope.user.companyId = companyId;
		  $http.get(_serverPath + "/Commander?beanId=userService&bizType=create", {
			  params: {
				  user: encodeURI(encodeURI(JSON.stringify($scope.user)))
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
		  
		  $scope.name = rows[0].name;
		  $scope.url = rows[0].url;
		  $scope.id = rows[0].id;
		  
		  $("#myModal4edit").modal();
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
	  
	  $scope.checkBoxSelectAllOrNot = function() {
		  if($scope.selectAllCheckbox) {
			  
		  } else {
			  
		  }
			  
	  };
	  
	  $scope.delete = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $http.get(_serverPath + "/Commander?beanId=workflowInstanceService&bizType=delete", {
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