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
               新建用户
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
              <div class="box-body">
              	
              	<div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" value="1" ng-model="user.userName">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">登录名</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" value="1" ng-model="user.loginName">
	              </div>
	            </div>
            
                <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">公司</label>
                  <div class="col-sm-10">
                  		<select class="form-control" ng-model="user.companyId" ng-options="x.id as x.companyname for x in companyList"></select>
                  </div>
                </div>
                
                <div class="form-group">
	                <label for="inputPassword3" class="col-sm-2 control-label">角色</label>
	                <div class="col-sm-10">
	                	<select class="form-control" ng-model="user.roleId" ng-options="x.id as x.rolename for x in roleList"></select>
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
            编辑用户
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
	         <div class="form-group">
	           <label for="inputEmail3" class="col-sm-2 control-label">id</label>
	           <div class="col-sm-10">
	             <input type="text" class="form-control" placeholder="" ng-model="id">
	           </div>
	         </div>
             <div class="form-group">
               <label for="inputEmail3" class="col-sm-2 control-label">用户名称</label>
               <div class="col-sm-10">
               <select ng-model="selectedName" ng-options="x for x in modelList">
               </select>
               </div>
             </div>
             <div class="form-group">
               <label for="inputPassword3" class="col-sm-2 control-label">用户地址</label>

               <div class="col-sm-10">
                 <input type="text" class="form-control" placeholder="" ng-model="url">
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
              <h3 class="box-title"><b>用户管理</b></h3>
            </div>
            <a class="btn btn-app" data-toggle="modal" data-target="#myModal" >
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
                  <th width="5%">id</th>
                  <th>用户名</th>
                  <th>登录名</th>
                  <th>角色</th>
                  <th>公司</th>
                  <th>创建时间</th>
                  <th>修改时间</th>
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
      "ajax": _serverPath + "/Commander?beanId=userService&bizType=query",
      "columns": [
                  { 
                	  "data": null,
                	  "defaultContent": "<input type='checkbox'>"
                  },
                  {"data": "index"},
                  {"data": "id"},
                  {"data": "username" ,"defaultContent": ""},
                  {"data": "loginname" ,"defaultContent": ""},
                  {"data": "roleName" ,"defaultContent": ""},
                  {"data": "companyName" ,"defaultContent": ""},
                  {"data": "createtime" ,"defaultContent": "", 
                	  "render": function ( data, type, full, meta ) {
                		  return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
                  }},
                  {"data": "updatetime" ,"defaultContent": "", 
                	  "render": function ( data, type, full, meta ) {
                		  return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
                  }},
              ],
              "columnDefs": [ {
                  "orderable": false,
                  "targets": 0
              } ,{
                  "orderable": false,
                  "targets": 1
              } , {
                  "visible": false,
                  "targets": 2
              } ],
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
  
</script>

<script src="user/controller.js"></script>