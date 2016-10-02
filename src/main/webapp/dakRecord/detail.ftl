<div class="modal" id="myModal4detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            考勤详情
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">备注</label>
	              <div class="col-sm-10">
	                <textarea rows=3 cols=20 class="form-control" ng-model="calendar.remark"></textarea>
	              </div>
	            </div>
	            
           </div>
         </form>
      </div>
      
      <#--
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="edit()">
            更新
         </button>
         <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalConfrim">
            删除
         </button>
      </div>
      -->
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->