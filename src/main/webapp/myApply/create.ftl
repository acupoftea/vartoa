<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建申请
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
              <div class="box-body">
                
                <div class="form-group">
	                <label for="inputPassword3" class="col-sm-2 control-label">流程类型</label>
	                <div class="col-sm-10">
	                	<select class="select2" style="width: 100%;" ng-model="workflowId" ng-options="x.id as x.name for x in workflowList"></select>
	                </div>
                </div>
	            
              </div>
            </form>
         </div>
         
         <div class="modal-footer">
            
            <button type="button" class="btn btn-primary" ng-click="selectWorkflow()" data-dismiss="modal">
               填写
            </button>
         </div>
      </div>
   </div>
</div>
