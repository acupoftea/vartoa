<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<style>
tr.selected{background-color:#FFFFCC}
</style>

<div ng-app="myApp" ng-controller="myCtrl">


<!-- 模态框（Modal） -->
<div class="modal" id="myModalConfrim" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            提示
         </h4>
      </div>
      
      <div class="modal-body">
      	<h3><font color="red">确认执行么?</font></h3>
      </div>
      
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="delete()" data-dismiss="modal">
            确认
         </button>
         <button type="button" class="btn btn-primary" data-dismiss="modal">
            取消
         </button>
      </div>
   </div>
</div>
</div>

<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建角色
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
              <div class="box-body">
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">名称</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" value="1" ng-model="roleName">
	              </div>
	            </div>
	            
              </div>
            </form>
         </div>
         
         <div class="modal-footer">
            
            <button type="button" class="btn btn-primary" ng-click="create()">
               保存
            </button>
         </div>
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal" id="myModal4edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            编辑角色
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
	           <div class="form-group">
	           <label for="inputPassword3" class="col-sm-2 control-label">名称</label>
	           <div class="col-sm-10">
	             	<input type="text" class="form-control" value="1" ng-model="roleName">
	           </div>
         </div>
           </div>
         </form>
      </div>
      
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="edit()">
            保存
         </button>
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>角色管理</b></h3>
            </div>
            <a class="btn btn-app" ng-click="showCreate()" >
                <i class="fa fa-save"></i> 新建
              </a>
     <a class="btn btn-app" ng-click="showEdit()">
                <i class="fa fa-edit"></i> 编辑
              </a>
      <a class="btn btn-app" data-toggle="modal" data-target="#myModalConfrim">
                <i class="fa fa-remove"></i> 删除
              </a>
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover" data-page-length='25'>
                <thead>
                <tr>
                  <th width="1%"><input type="checkbox" g-model="selectAllCheckbox" ng-click="checkBoxSelectAllOrNot()"></th>	
                  <th width="3%"></th>
                  <th>角色名称</th>
                </tr>
                </thead>
              </table>
            </div>
          </div>
      </div>
</div>

<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="plugins/fastclick/fastclick.js"></script>
<script src="dist/js/app.min.js"></script>

<!-- page script -->
<script>
var table = null;
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": false,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=roleService&bizType=query&companyId=" + companyId,
      "columns": [
                  { 
                	  "data": null,
                	  "defaultContent": "<input type='checkbox'>"
                  },
                  {"data": "index"},
                  {"data": "rolename" ,"defaultContent": ""}
              ],
              
    });
	 
	 $('#example2 tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	    } );
    
  });
  
  var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	  $http.get(_serverPath + "/Commander?beanId=modelService&bizType=query", {
		  params: {
			  length: 100
			  
	  }})
	     .success(function (response) {
	    	$scope.modelList = response.data
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $http.get(_serverPath + "/Commander?beanId=roleService&bizType=query", {
		  params: {
			  length: 100
			  
	  }})
	     .success(function (response) {
	    	$scope.powerList = response.data
	    	 
	     }).error(function(a, b, c, d){
	    	 alert("读取服务端失败: " + b);
	     });
	  
	  $scope.create = function() {
		  $scope.role = {
			  roleName: $scope.roleName,
			  companyId: companyId,
		  };
		  
		  $http.get(_serverPath + "/Commander?beanId=roleService&bizType=create", {
			  params: {
				  role: encodeURI(encodeURI(JSON.stringify($scope.role)))
				  
		  }})
		     .success(function (response) {
		    	if(response.success) {
		    		alert("新建成功");
		    		 $('#example2').DataTable().draw();
		    	}
		    	else
		    		alert("失败, " + response.msg);
		    	 
		     }).error(function(a, b, c, d){
		    	 alert("读取服务端失败: " + b);
		     });
	  };
	  
	  $scope.showCreate = function() {
		  $scope.roleName = "";
		  $("#myModal").modal();
	  };
	  
	  $scope.showEdit = function() {
		  var rows = table.rows('.selected').data();
		  if(rows.length <= 0) {
			  alert("请选择要操作的记录");
			  return;
		  }
		  
		  $scope.roleName = rows[0].rolename;
		  $scope.id = rows[0].id;
		  
		  $("#myModal4edit").modal();
	  };
	  
	  $scope.edit = function() {
		  $http.get(_serverPath + "/Commander?beanId=roleService&bizType=edit", {
			  params: {
				  id: $scope.id,
				  roleName: encodeURI(encodeURI($scope.roleName)), 
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
		  
		  $http.get(_serverPath + "/Commander?beanId=roleService&bizType=delete", {
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
  
  
</script>