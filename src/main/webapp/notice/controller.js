var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http, $sce) {
	  $scope.trustAsHtml = function(html) {
	      return $sce.trustAsHtml(html);
	  }; 
	  
	  $scope.showDetail = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  $scope.notice = {};
		  $scope.notice.title = rows[0].title;
		  $scope.notice.content = rows[0].content;
		  
		  $("#myModal4detail").modal();
	  };
	  
	  $http.get(_serverPath + "/Commander?beanId=noticeService&bizType=query4NoticeTypeList", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }})
	     .success(function (response) {
	    	$scope.noticeTypeList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  
	  $scope.notice = {};
	  
	  $scope.create = function() {
		  $scope.notice.companyId = companyId;
		  $scope.notice.content = encodeURI(encodeURI($("#elm1").val()));
		  
		  /*
		  $http.get(_serverPath + "/Commander?beanId=noticeService&bizType=create", {
			  params: {
				  notice: encodeURI(encodeURI(JSON.stringify($scope.notice)))
		  }})
		     .success(function (response) {
		    	if(response.success) {
		    		alert("新建成功");
		    		 $('#example2').DataTable().draw();
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
		  */
		  $http({
		        method  : 'POST',
		        url     : _serverPath + "/Commander?beanId=noticeService&bizType=create",
		        data: "notice=" + encodeURI(encodeURI(JSON.stringify($scope.notice))),
		        headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
		    })
		        .success(function(response) {
		        	if(response.success) {
			    		alert("新建成功");
			    		 $('#example2').DataTable().draw();
			    	}
		        }).error(function(a, b, c, d){
			    	 alert("读取服务端失败: " + b);
			     });;
		  
	  };
	  
	  $scope.showEdit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $scope.notice = {};
		  $scope.notice.id = rows[0].id;
		  $scope.notice.noticetype = rows[0].noticetype;
		  $scope.notice.title = rows[0].title;
		  $scope.notice.content = rows[0].content;
		  $("#elm2").val(rows[0].content);
//		  var editor=$('#elm2').xheditor();
//		  editer.appendHTML("dddd");
		  
		  $("#myModal4edit").modal();
	  };
	  
	  $scope.edit = function() {
		  //$scope.notice.content = encodeURIComponent (encodeURIComponent ($("#elm2").val()));
		  $scope.notice.content = encodeURIComponent($("#elm2").val());
		  /*
		  $http.post(_serverPath + "/Commander?beanId=noticeService&bizType=edit", {
			  params: {
				  notice: encodeURI(encodeURI(JSON.stringify($scope.notice)))
			  }
		  })*/
		   $http({
		        method  : 'POST',
		        url     : _serverPath + "/Commander?beanId=noticeService&bizType=edit",
		        data: "notice=" + encodeURI(encodeURI(JSON.stringify($scope.notice))),
		        headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
		    })
		     .success(function (response) {
		    	if(response.success) {
		    		alert("操作成功");
		    		 $('#example2').DataTable().draw();
		    	} else
		    		alert("操作失败");
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.del = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  } 
		  
		  $http.get(_serverPath + "/Commander?beanId=noticeService&bizType=delete", {
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