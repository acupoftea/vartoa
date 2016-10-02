<div class="modal" id="myModal4edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            编辑考勤
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
	         <div class="form-group">
	           <label for="inputEmail3" class="col-sm-2 control-label">id</label>
	           <div class="col-sm-10">
	             <input type="text" class="form-control" placeholder="" ng-model="id">
	           </div>
	         </div>
             <div class="form-group">
               <label for="inputEmail3" class="col-sm-2 control-label">考勤名称</label>
               <div class="col-sm-10">
               <select ng-model="selectedName" ng-options="x for x in modelList">
               </select>
               </div>
             </div>
             <div class="form-group">
               <label for="inputPassword3" class="col-sm-2 control-label">考勤地址</label>

               <div class="col-sm-10">
                 <input type="text" class="form-control" placeholder="" ng-model="url">
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