var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $scope.submitRemark = function() {
		  $("#signoutButton").hide();
		  
		  $http.get(_serverPath + "/Commander?beanId=attendanceService&bizType=edit", {
			  params: {
				  attendance: encodeURI(encodeURI(JSON.stringify($scope.attendance)))
				  
		  }}).success(function (response) {
			  if(response.success) {
				  alert("保存成功");
			  }
	    	 
		  }).error(function(a, b, c, d){
	    	 alert("error: " + b);
		  });
	  };
	  
	  refresh = function() {
		  $http.get(_serverPath + "/Commander?beanId=attendanceService&bizType=queryAttendanceByUserId", {
			  params: {
				  userId: userId
				  
		  }}).success(function (response) {
			  if(response.success) {
				  $scope.attendance = response.data;
				  $scope.attendance.starttime = new Date(Date.parse($scope.attendance.starttime)).Format("hh:mm");
				  $scope.attendance.endtime = new Date(Date.parse($scope.attendance.endtime)).Format("hh:mm");
				  
				  if($scope.attendance.starttime != "aN:aN")
					  $("#signinButton").hide();
				  else
					  $scope.attendance.starttime = "";
				  
				  if($scope.attendance.endtime != "aN:aN")
					  $("#signoutButton").hide();
				  else
					  $scope.attendance.endtime = "";
			  }
	    	 
		  }).error(function(a, b, c, d){
	    	 alert("error: " + b);
		  });
	  };
	  
	  $scope.signout = function() {
		  $("#signoutButton").hide();
		  
		  $http.get(_serverPath + "/Commander?beanId=attendanceService&bizType=signout", {
			  params: {
				  userId: userId
				  
		  }}).success(function (response) {
			  if(response.success) {
				  refresh();
			  }
	    	 
		  }).error(function(a, b, c, d){
	    	 alert("error: " + b);
		  });
	  };
	  
	  $scope.signin = function() {
		  $http.get(_serverPath + "/Commander?beanId=attendanceService&bizType=signin", {
			  params: {
				  userId: userId
				  
		  }}).success(function (response) {
			  if(response.success) {
				  refresh();
				  $("#signinButton").hide();
			  }
	    	 
		  }).error(function(a, b, c, d){
	    	 alert("error: " + b);
		  });
	  };
	  
	  $http.get(_serverPath + "/Commander?beanId=attendanceService&bizType=querySinginDataByUserId", {
		  params: {
			  userId: userId
			  
	  }}).success(function (response) {
		  if(response.success) {
			  $scope.today_yearMonth = response.today_yearMonth;
			  $scope.today_day = response.today_day;
			  $scope.today_week = response.today_week;
			  
			  $scope.workTime = response.data;
			  $scope.workTime.starttime = new Date(Date.parse($scope.workTime.starttime)).Format("hh:mm");
			  $scope.workTime.endtime = new Date(Date.parse($scope.workTime.endtime)).Format("hh:mm");
		  }
    	 
	  }).error(function(a, b, c, d){
    	 alert("error: " + b);
	  });
	  
	  refresh();
     
  });