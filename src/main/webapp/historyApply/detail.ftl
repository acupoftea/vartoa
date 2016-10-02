<div class="modal" id="myModal4Detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog" style="width:800px">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            流程详情
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
	      	<div class="box-body" id="auditForm"></div>
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
      </div>
      
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->