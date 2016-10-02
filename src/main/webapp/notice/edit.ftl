<div class="modal" id="myModal4edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog" style="width:1024px">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            编辑公告
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
             <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">公告类型</label>
	              <div class="col-sm-10">
	                <select class="form-control" ng-model="notice.noticetype" ng-options="x.id as x.name for x in noticeTypeList"></select>
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">标题</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" ng-model="notice.title">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">内容</label>
	              <div class="col-sm-10">
	              	<textarea id="elm2" name="elm2" class="xheditor" rows="12" cols="80" ng-model="notice.content"></textarea>
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