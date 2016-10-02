<div class="modal" id="myModal4audit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog" style="width:800px">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            审核 {{audit.title}}
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
      	   <div class="box-body" id="auditForm">
	           
           </div>
           
           <div class="box-body">
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;&nbsp;&nbsp;是否通过</label>
		          <div class="col-sm-10">
		          		<select class="form-control" ng-model="audit.isPass" ng-options="x.value as x.key for x in auditList"></select>
		          </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">审批意见</label>
	              <div class="col-sm-10">
	                	<textarea class="form-control" rows="3" ng-model="audit.opinion"></textarea>
	              </div>
	            </div>
           </div>
         </form>
      </div>
      
      <div class="modal-footer">
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
         <button type="button" class="btn btn-primary" ng-click="startAudit()">提交</button>
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->