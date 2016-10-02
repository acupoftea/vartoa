<div class="modal" id="myModal4edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog" style="width:900px">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            编辑工作流
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
             <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;&nbsp;&nbsp;流程名称</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" value="1" ng-model="workflowName">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label"><font color="red">*</font>&nbsp;&nbsp;&nbsp;关联表单</label>
                <div class="col-sm-10">
                		<select class="form-control" ng-model="workflowForm" ng-options="x.id as x.name for x in udfList"></select>
                </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">审核结点</label>
              <div class="col-sm-10">
              
	            <button ng-click="addOneRow4edit()" class="btn btn-primary">添加</button>
	            <div style="height:3px"></div>
	            
	            <table id="workflowNodeTable4edit" class="table table-bordered table-hover" >
	                <thead>
	                <tr>
	                  <th width="3%"></th>
	                  <th>级别名称</th>
	                  <th>审批人</th>
	                  <th>抄送人</th>
	                  <th>操作</th>
	                </tr>
	                </thead>
	                <tbody>
	                </tbody>
	            </table>
	            
	            </div>
	            </div>
	            
           </div>
         </form>
      </div>
      
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="edit()">更新</button>
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->