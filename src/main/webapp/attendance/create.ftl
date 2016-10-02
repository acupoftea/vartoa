<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建考勤
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
	                	<input type="textarea" class="form-control" value="1" ng-model="user.remark">
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