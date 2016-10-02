<div class="modal" id="myModal4detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            事件详情
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
             <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">主题</label>
	              <div class="col-sm-10">
	                <input type="text" class="form-control" ng-model="calendar.eventname">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">事件详情</label>
	              <div class="col-sm-10">
	              	<textarea rows=3 cols=20 class="form-control" ng-model="calendar.envetdetail"></textarea>
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
	              	<input type="text" class="form-control" ng-model="calendar.startendtime" id="reservation2">
	              </div>
	            </div>
           </div>
         </form>
      </div>
      
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="edit()">
            更新
         </button>
         <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalConfrim">
            删除
         </button>
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->