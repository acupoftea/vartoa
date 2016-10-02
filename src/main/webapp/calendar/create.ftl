<div class="modal" id="myModalCreate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建事件
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
              <div class="box-body">
              	
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">日历</label>
	              <div class="col-sm-10">
	                <select class="form-control" ng-model="calendar.type" ng-options="x.id as x.name for x in calendarTypeList"></select>
	              </div>
	            </div>
            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;&nbsp;&nbsp;主题</label>
	              <div class="col-sm-10">
	                <input type="text" class="form-control" ng-model="calendar.eventName">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">事件详情</label>
	              <div class="col-sm-10">
	              	<textarea rows=3 cols=20 class="form-control" ng-model="calendar.eventDetail"></textarea>
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">资源</label>
	              <div class="col-sm-10">
	                <select class="form-control" ng-model="calendar.resourcesId" ng-options="x.id as x.name for x in resourcesList"></select>
	              </div>
	            </div>
	            
	            <#--
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">共享日历</label>
	              <div class="col-sm-10">
	                <select class="form-control" ng-model="calendar.type" ng-options="x.id as x.name for x in calendarTypeList"></select>
	              </div>
	            </div>
	            -->
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">起止时间</label>
	              <div class="col-sm-10">
	              	<input type="text" class="form-control" ng-model="calendar.eventStartEndTime" id="reservation">
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