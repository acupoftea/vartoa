<div class="modal" id="myModal4detail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog" style="width:800px">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            公告详情
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
             <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">标题</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" ng-model="notice.title">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">内容</label>
	              <div class="col-sm-10">
	              	<p ng-bind-html="trustAsHtml(notice.content)"></p>
	              </div>
	            </div>
           </div>
         </form>
      </div>
      
      <div class="modal-footer">
        
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->