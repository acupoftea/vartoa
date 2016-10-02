var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  
	  $scope.showCreate = function() {
		  $scope.calendar = {};
		  $("#reservation").val("");
		  $("#myModalCreate").modal();
	  };
	  
	  $http.get(_serverPath + "/Commander?beanId=resourcesService&bizType=query", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }})
	     .success(function (response) {
	    	$scope.resourcesList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  showDetail = function(event) {
		  $scope.eventId = event.id;
		  $http.get(_serverPath + "/Commander?beanId=calendarService&bizType=queryCalendarById", {
			  params: {
				  eventId: $scope.eventId
				  
		  }}).success(function (response) {
			  if(response.success) {
				  $scope.calendar = response.data;
				  $("#myModal4detail").modal();
				  $('#reservation2').daterangepicker({
					  "timePicker": true,
					  "timePickerIncrement": 10,
					    "timePicker24Hour": true,
					    "locale": {
					        "format": "YYYY/MM/DD HH:mm",
					        "separator": " - ",
					        "applyLabel": "确认",
					        "cancelLabel": "取消",
					        "fromLabel": "从",
					        "toLabel": "到",
					        "customRangeLabel": "Custom",
					        "weekLabel": "W",
					        "daysOfWeek": [
					            "日",
					            "一",
					            "二",
					            "三",
					            "四",
					            "五",
					            "六"
					        ],
					        "monthNames": [
					            "一月",
					            "二月",
					            "三月",
					            "四月",
					            "五月",
					            "六月",
					            "七月",
					            "八月",
					            "九月",
					            "十月",
					            "十一月",
					            "十二月"
					        ],
					        "firstDay": 1
					    }
				  });
			  }
	    	 
		  }).error(function(a, b, c, d){
			  alert("读取服务端失败: " + b);
		  });
		  
	  };
	  
	  $http.get(_serverPath + "/Commander?beanId=calendarService&bizType=query4Calendar", {
		  params: {
			  userId: userId,
			  //calendarType: calendarType
			  
	  }}).success(function (response) {
		  var jsonList = response.data;
		  
		  $('#calendar').fullCalendar('removeEvents');  
          $('#calendar').fullCalendar('addEventSource', jsonList);  
		    
    	 
	  }).error(function(a, b, c, d){
		  alert("读取服务端失败: " + b);
	  });
	  
	  //Date range picker
	  $('#reservation').daterangepicker({
		  "timePicker": true,
		  "timePickerIncrement": 10,
		    "timePicker24Hour": true,
		    "locale": {
		        "format": "YYYY/MM/DD HH:mm",
		        "separator": " - ",
		        "applyLabel": "确认",
		        "cancelLabel": "取消",
		        "fromLabel": "从",
		        "toLabel": "到",
		        "customRangeLabel": "Custom",
		        "weekLabel": "W",
		        "daysOfWeek": [
		            "日",
		            "一",
		            "二",
		            "三",
		            "四",
		            "五",
		            "六"
		        ],
		        "monthNames": [
		            "一月",
		            "二月",
		            "三月",
		            "四月",
		            "五月",
		            "六月",
		            "七月",
		            "八月",
		            "九月",
		            "十月",
		            "十一月",
		            "十二月"
		        ],
		        "firstDay": 1
		    }
	  });
	  
	  $http.get(_serverPath + "/Commander?beanId=calendarService&bizType=query4calendarTypeList", {
		  params: {
			  length: 100,
			  companyId: companyId
			  
	  }})
	     .success(function (response) {
	    	$scope.calendarTypeList = response.data;
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  
	  $scope.calendar = {};
	  
	  $scope.create = function() {
		  $scope.calendar.userId = userId;
		  $scope.calendar.eventStartEndTime = $('#reservation').val();
		  
		  $http.get(_serverPath + "/Commander?beanId=calendarService&bizType=create", {
			  params: {
				  calendar: encodeURI(encodeURI(JSON.stringify($scope.calendar)))
		  }})
		     .success(function (response) {
		    	if(response.success) {
		    		alert("新建成功");
		    		 $http.get(_serverPath + "/Commander?beanId=calendarService&bizType=query4Calendar", {
		    			  params: {
		    				  userId: userId,
		    				  calendarType: calendarType
		    				  
		    		  }}).success(function (response) {
		    			  var jsonList = response.data;
		    			  
		    			  $('#calendar').fullCalendar('removeEvents');  
		    	          $('#calendar').fullCalendar('addEventSource', jsonList);  
		    			    
		    	    	 
		    		  }).error(function(a, b, c, d){
		    			  alert("读取服务端失败: " + b);
		    		  });
		    	}
		    	
		    	$("#myModalCreate").modal('hide');
		    	 
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
		  $http.get(_serverPath + "/Commander?beanId=calendarService&bizType=edit", {
			  params: {
				  calendar: encodeURI(encodeURI(JSON.stringify($scope.calendar)))
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		alert("操作成功");
		    		 $('#example2').DataTable().draw();
		    		 $("#myModal4detail").modal('hide');
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
		  $http.get(_serverPath + "/Commander?beanId=calendarService&bizType=delete", {
			  params: {
				  id: $scope.eventId
			  }
		  })
		     .success(function (response) {
		    	if(response.success) {
		    		//alert("删除成功");
		    		 $http.get(_serverPath + "/Commander?beanId=calendarService&bizType=query4Calendar", {
		    			  params: {
		    				  userId: userId,
		    				  calendarType: calendarType
		    				  
		    		  }}).success(function (response) {
		    			  var jsonList = response.data;
		    			  
		    			  $('#calendar').fullCalendar('removeEvents');  
		    	          $('#calendar').fullCalendar('addEventSource', jsonList);
		    	          
		    	          $("#myModal4detail").modal('hide');
		    			    
		    	    	 
		    		  }).error(function(a, b, c, d){
		    			  alert("读取服务端失败: " + b);
		    		  });
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
     
  });