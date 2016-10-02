<div class="modal" id="myModalCreate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog" style="width:1024px">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建表单
            </h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
              <div class="box-body">
              	
              	<div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">表单名称</label>
	              <div class="col-sm-10">
	                	<input type="text" class="form-control" ng-model="formName">
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">相关下载</label>
	              <div class="col-sm-10">
	              <span class="btn btn-success fileinput-button">
	              <i class="glyphicon glyphicon-plus"></i>
	              <span>选择文件...</span>
	              <!-- The file input field used as target for the file upload widget -->
	              <input id="fileupload4create" type="file" name="files[]" multiple>
	          </span>
	          
	          <br>
	          <br>
	          <!-- The global progress bar -->
	          <div id="progress" class="progress">
	              <div class="progress-bar progress-bar-success"></div>
	          </div>
	          <!-- The container for the uploaded files -->
	          <div id="files" class="files"></div>
	          <div ng-show="showHideDownloadLink">
	          <a href="#" id="udfCreate_downloadUrl" target="_blank" >查看上传文件</a>
	          </div>
	              </div>
	            </div>
	            
	            <div class="form-group">
	              <label for="inputPassword3" class="col-sm-2 control-label">自定义表单</label>
	            <div class="col-sm-10">
            
	            <button class="btn btn-primary" data-toggle="modal" data-target="#myModalSelectContrl" data-src="create" id="selectControlSrc" ng-click="initAddControl()">添加</button>
	            <div style="height:3px"></div>
	            
	            <table id="udfTable" class="table table-bordered table-hover" >
	                <thead>
	                <tr>
	                  <th>显示顺序</th>
	                  <th>是否必填</th>
	                  <th>标题</th>
	                  <th>控件类型</th>
	                  <th>控件数值</th>
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
            
            <button type="button" class="btn btn-primary" ng-click="create()">
               保存
            </button>
         </div>
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->