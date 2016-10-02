<div class="modal" id="myModalCreate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建排班
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
              <div class="box-body">
              	
              	<div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">排班名称</label>
	              <div class="col-sm-10">
	                <input type="text" class="form-control" ng-model="workTime.name">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">上班时间</label>
	              <div class="col-sm-10">
	                	<input type="time" class="form-control" ng-model="workTime.starttime">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">下班时间</label>
	              <div class="col-sm-10">
	                	<input type="time" class="form-control" ng-model="workTime.endtime">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">时间范围</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" ng-model="workTime.range">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">生效日期</label>
	              <div class="col-sm-10">
	                	<input type="date" class="form-control" ng-model="workTime.startdate">
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