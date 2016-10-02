var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  showDetail = function(event) {
		  $scope.eventId = event.id;
		  $("#myModal4detail").modal();
	  }
	  
	  $scope.queryStatisticsData = function() {
		  $http.get(_serverPath + "/Commander?beanId=attendanceService&bizType=statisticsAttendanceDetail4Month", {
			  params: {
				  userId: userId,
				  selectedDate: $("#reservationtime").val()
				  
		  }}).success(function (response) {
			  if(response.success) {
				  $scope.attendance = response.data;
			  }
	    	 
		  }).error(function(a, b, c, d){
	    	 alert("error: " + b);
		  });
		  
		  
	  }
	  
	  $scope.queryStatisticsData();
	  
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
	  
	  $http.get(_serverPath + "/Commander?beanId=attendanceService&bizType=query4Calendar", {
		  params: {
			  userId: userId,
			  selectedDate: $("#datepicker").val()
			  
	  }}).success(function (response) {
		  var jsonList = response.data;
		  
		  $('#calendar').fullCalendar('removeEvents');  
          $('#calendar').fullCalendar('addEventSource', jsonList);  
		    
    	 
	  }).error(function(a, b, c, d){
		  alert("读取服务端失败: " + b);
	  });
     
  });