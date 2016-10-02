<div ng-app="myApp" ng-controller="myCtrl">  
<div class="modal" id="modalChangePsw" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               修改密码
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
              <div class="box-body">
                
                <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">旧密码</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" ng-model="oldPsw">
	              </div>
	            </div>
              	
              	 <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">新密码</label>
	              <div class="col-sm-10">
	                	<input type="textarea" class="form-control" ng-model="newPsw">
	              </div>
	            </div>
	            
              </div>
            </form>
         </div>
         
         <div class="modal-footer">
            <button type="button" class="btn btn-primary" ng-click="changePsw()">保存</button>
         </div>
      </div>
   </div>
</div>
</div>

<script>
	<#if userMap??>
		userId = ${userMap.userId?if_exists};
	</#if>
	
	var _app = angular.module('myApp', []);
 	_app.controller('myCtrl', function($scope, $http) {
 		$scope.changePsw = function() {
 			$http.get("/vartoa/Commander?beanId=userService&bizType=changePsw", {
			  params: {
				  userId: userId,
				  newPsw: $scope.newPsw
			  }
		  }).success(function (response) {
		    	if(response.success) {
		    		alert("操作成功");
		    	}
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
 		}
 	});
 	
</script>