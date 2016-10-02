<div class="modal" id="myModalSelectContrl" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog" style="width:550px">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            选择控件
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
             
             <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">是否必填</label>
              <div class="col-sm-10">
                	<select class="form-control" ng-model="isRequireField" ng-options="x.id as x.name for x in selectYesNo"></select>
              </div>
            </div>
            
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">标题</label>
              <div class="col-sm-10">
                	<input type="text" class="form-control" ng-model="controlLabel">
              </div>
            </div>
            
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">类型</label>
              <div class="col-sm-10">
                	<select class="form-control" ng-model="selectedControl" ng-options="x.id as x.name for x in controlList"></select>
              </div>
            </div>
            
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">数值</label>
              <div class="col-sm-10">
                	<input type="text" class="form-control" ng-model="controlValue">
              </div>
            </div>
             
           </div>
         </form>
      </div>
      
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="addControl()" data-dismiss="modal">
            选择
         </button>
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->