<div class="modal" id="myModalUserList2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog" style="width:480px">
   <div class="modal-content">
      <div class="modal-header">
         <button type="button" class="close" data-dismiss="modal" 
            aria-hidden="true">×
         </button>
         <h4 class="modal-title" id="myModalLabel">
            选择抄送人
         </h4>
      </div>
      
      <div class="modal-body">
      	<form class="form-horizontal">
           <div class="box-body">
             <div class="form-group">
               <div class="col-sm-10">
               
                 <#-- <select class="form-control" ng-model="selectedUser" ng-options="x.id as x.username for x in employeeList"></select> -->
                 
                 <div class="selectbox">
<div class="select-bar">
    <select multiple="multiple" id="select1" ng-model="selectedUsers" ng-options="x.id as x.username for x in employeeList">
    </select>
</div>

<div class="btn-bar">
    <p><span id="add"><input type="button" class="btn" value=">" title="移动选择项到右侧"/></span></p>
    <p><span id="add_all"><input type="button" class="btn" value=">>" title="全部移到右侧"/></span></p>
    <p><span id="remove"><input type="button" class="btn" value="<" title="移动选择项到左侧"/></span></p>
    <p><span id="remove_all"><input type="button" class="btn" value="<<" title="全部移到左侧"/></span></p>
</div>
<div class="select-bar">
    <select multiple="multiple" id="select2" ng-model="seleted" ng-options="x.id as x.username for x in employeeList2"></select>
</div>	
</div>

               </div>
             </div>
           </div>
         </form>
      </div>
      
      <div class="modal-footer">
         <button type="button" class="btn btn-primary" ng-click="selectCCer()" data-dismiss="modal">
            选择
         </button>
      </div>
   </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->