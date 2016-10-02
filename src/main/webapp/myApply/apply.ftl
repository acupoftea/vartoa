<div class="modal" id="myModalDynamicApply" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog" style="width:800px">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建申请 - {{workflowName}}
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
         		<div class="box-body">
         			<div class="form-group">
	              	<label for="inputPassword3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;&nbsp;&nbsp;审批摘要</label>
		              <div class="col-sm-10">
		                	<input type="text" class="form-control" placeholder="" ng-model="applySummary">
		              </div>
	            	</div>
              	</div>
              <div class="box-body" id="vessel">
              </div>
            </form>
            
            
         </div>
         
         <div class="modal-footer">
            <p style="text-align:left;">审核环节: {{auditStep}}</p>
            <button type="button" class="btn btn-primary" ng-click="createApply()">
               提交
            </button>
         </div>
      </div>
   </div>
</div>