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
      	<h4><font color="red">确认执行么?</font></h4>
      </div>
      
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="del()" data-dismiss="modal">
            确认
         </button>
         <button type="button" class="btn btn-primary" data-dismiss="modal">
            取消
         </button>
      </div>
   </div>
</div>
</div>

<div class="modal" id="myModal4import" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            导入员工
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
           	
           	<div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">上传文件</label>
	              <div class="col-sm-10">
	                	<input type="file" class="form-control" value="1" ng-model="user.userName">
	              </div>
	            </div>
           </div>
         </form>
      </div>
      
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="import()">
            导入
         </button>
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog" style="width:800px">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建员工
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal" name="testFormValid" novalidate>
              <div class="box-body">
              	
              	<div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;&nbsp;&nbsp;姓名</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" value="1" ng-model="user.userName" name="userName" required >
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;&nbsp;&nbsp;登录名</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" value="1" ng-model="user.loginName" required >
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;&nbsp;&nbsp;员工编号</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" value="1" ng-model="user.employNu" required >
	              </div>
	            </div>
                
                <div class="form-group">
	                <label for="inputPassword3" class="col-sm-2 control-label">角色</label>
	                <div class="col-sm-10">
	                	<select class="form-control" ng-model="user.roleId" ng-options="x.id as x.rolename for x in roleList"></select>
	                </div>
                </div>
                
                <div class="form-group">
	                <label for="inputPassword3" class="col-sm-2 control-label">部门</label>
	                <div class="col-sm-10">
	                	<select class="form-control" ng-model="user.departmentid" ng-options="x.id as x.name for x in deparmentList"></select>
	                </div>
	            </div>
	            
	            <div class="form-group">
	                <label for="inputPassword3" class="col-sm-2 control-label">排班</label>
	                <div class="col-sm-10">
	                	<select class="form-control" ng-model="user.worktimeid" ng-options="x.id as x.name for x in workTimeList"></select>
	                </div>
	            </div>
                
                <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">电话</label>
	              <div class="col-sm-10">
	                	<input type="number" class="form-control" value="1" ng-model="user.mobile">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">邮箱</label>
	              <div class="col-sm-10">
	                	<input type="email" class="form-control" value="1" ng-model="user.email">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">备注</label>
	              <div class="col-sm-10">
	              	<textarea rows="3" cols="20" class="form-control" ng-model="user.remark"></textarea>
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
            编辑员工
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
	         <div class="form-group">
	           <label for="inputEmail3" class="col-sm-2 control-label">id</label>
	           <div class="col-sm-10">
	             <input type="text" class="form-control" placeholder="" ng-model="user.id">
	           </div>
	         </div>
             
	         <div class="form-group">
             <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
             <div class="col-sm-10">
               	<input type="text" class="form-control" value="1" ng-model="user.username">
             </div>
           </div>
           
           <div class="form-group">
             <label for="inputPassword3" class="col-sm-2 control-label">登录名</label>
             <div class="col-sm-10">
               	<input type="text" class="form-control" value="1" ng-model="user.loginname">
             </div>
           </div>
           
           <div class="form-group">
	           <label for="inputPassword3" class="col-sm-2 control-label">员工编号</label>
	           <div class="col-sm-10">
	             	<input type="text" class="form-control" value="1" ng-model="user.employnu">
	           </div>
	         </div>
           
           <div class="form-group">
               <label for="inputPassword3" class="col-sm-2 control-label">角色</label>
               <div class="col-sm-10">
               	<select class="form-control" ng-model="user.roleid" ng-options="x.id as x.rolename for x in roleList"></select>
               </div>
           </div>
           
           <div class="form-group">
               <label for="inputPassword3" class="col-sm-2 control-label">部门</label>
               <div class="col-sm-10">
               	<select class="form-control" ng-model="user.departmentid" ng-options="x.id as x.name for x in deparmentList"></select>
               </div>
           </div>
           
           <div class="form-group">
               <label for="inputPassword3" class="col-sm-2 control-label">排班</label>
               <div class="col-sm-10">
               	<select class="form-control" ng-model="user.worktimeid" ng-options="x.id as x.name for x in workTimeList"></select>
               </div>
           </div>
           
           <div class="form-group">
             <label for="inputPassword3" class="col-sm-2 control-label">电话</label>
             <div class="col-sm-10">
               	<input type="text" class="form-control" value="1" ng-model="user.mobile">
             </div>
           </div>
           
           <div class="form-group">
             <label for="inputPassword3" class="col-sm-2 control-label">邮箱</label>
             <div class="col-sm-10">
               	<input type="text" class="form-control" value="1" ng-model="user.email">
             </div>
           </div>
           
           <div class="form-group">
             <label for="inputPassword3" class="col-sm-2 control-label">备注</label>
             <div class="col-sm-10">
             	<textarea rows="3" cols="20" class="form-control" ng-model="user.remark"></textarea>
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
              <h3 class="box-title"><b>员工管理</b></h3>
            </div>
            <a class="btn btn-app" data-toggle="modal" data-target="#myModal4import" >
            <i class="fa fa-file-excel-o"></i> 导入
          </a>
            <a class="btn btn-app" ng-click="showCreate()">
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
                  <th>员工编号</th>
                  <th>员工名</th>
                  <th>登录名</th>
                  <th>角色</th>
                  <th>部门</th>
                  <th>排班</th>
                  <th>电话</th>
                  <th>邮箱</th>
                  <th>备注</th>
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

<!-- 遮罩loading -->
<script src="plugins/layer/layer.js"></script>

<!-- page script -->
<script>

//遮罩loading
var layerIndex, layerIndex2;
function showLoading(showOrHide) {
	if(showOrHide) {
		layerIndex = layer.load(2, {
		    shade: [0.3, '#000'],
		    offset: '250px'
		});
		  
		layerIndex2 = layer.msg('处理中...', {
			icon: 16,
			time: 9 * 10000,
			offset: '250px'
		});
	}
	else {
		layer.close(layerIndex);
    	layer.close(layerIndex2);
	}
}

var table = null;
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": false,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=userService&bizType=query4Company&companyId=" + companyId,
      "columns": [
                  { 
                	  "data": null,
                	  "defaultContent": "<input type='checkbox'>"
                  },
                  {"data": "index"},
                  {"data": "id"},
                  {"data": "employnu" ,"defaultContent": ""},
                  {"data": "username" ,"defaultContent": ""},
                  {"data": "loginname" ,"defaultContent": ""},
                  {"data": "roleName" ,"defaultContent": ""},
                  {"data": "departmentName" ,"defaultContent": ""},
                  {"data": "workTimeName" ,"defaultContent": ""},
                  {"data": "mobile" ,"defaultContent": ""},
                  {"data": "email" ,"defaultContent": ""},
                  {"data": "remark" ,"defaultContent": ""},
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

<script src="employee/controller.js"></script>