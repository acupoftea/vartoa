<div class="modal" id="myModal4edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog" style="width:800px">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            编辑申请
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
             <div class="box-body" id="editFromAndData">
             </div>
         </form>
      </div>
      
      <div class="modal-footer">
      	<p style="text-align:left;">审核环节: {{auditStep}}</p>
      	
      	<table id="historyAuditTable" class="table table-bordered table-hover" data-page-length='25'>
            <thead>
            <tr>
                <th width="3%"></th>
                <th>审批结点</th>
                <th>审批人</th>
                <th>审批结果</th>
                <th>审批意见</th>
                <th>审批时间</th>
            </tr>
            </thead>
          </table>
          
         <button type="button" class="btn btn-primary" ng-click="reSubmit()">
           重新提交
         </button>
          <button type="button" class="btn btn-primary" ng-click="archiving()">
            归档
         </button>
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->