<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<style>
tr.selected{background-color:#FFFFCC}
</style>

<link rel="stylesheet" href="plugins/jQuery-File-Upload-master/css/jquery.fileupload.css">

<div ng-app="myApp" ng-controller="myCtrl">

<#include "confrim.ftl" />
<#include "create.ftl" />
<#include "edit.ftl" />
<#include "selectControl.ftl" />
<#include "changeControlType.ftl" />

<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>自定义表单管理</b></h3>
            </div>
           
            <a class="btn btn-app" ng-click="showCreate()">
                <i class="fa fa-save"></i> 新建
              </a>
     <a class="btn btn-app" ng-click="showEdit()">
                <i class="fa fa-edit"></i> 编辑
              </a>
      <a class="btn btn-app" data-toggle="modal" data-target="#myModalConfrim">
                <i class="fa fa-remove"></i> 删除
              </a>
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover" data-page-length='25'>
                <thead>
                <tr>
                  <th width="3%"></th>
                  <th width="5%">id</th>
                  <th>类型名称</th>
                </tr>
                </thead>
              </table>
            </div>
          </div>
      </div>
</div>

<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="plugins/fastclick/fastclick.js"></script>
<script src="dist/js/app.min.js"></script>

<!-- 遮罩loading -->
<script src="plugins/layer/layer.js"></script>

<script src="plugins/jQuery-File-Upload-master/js/vendor/jquery.ui.widget.js"></script>
<script src="plugins/jQuery-File-Upload-master/js/jquery.iframe-transport.js"></script>
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload.js"></script>


<!-- page script -->
<script>


//遮罩loading
var layerIndex, layerIndex2;
function showLoading(showOrHide) {
	if(showOrHide) {
		layerIndex = layer.load(2, {
		    shade: [0.3, '#000'],
		    offset: '250px'
		});
		  
		layerIndex2 = layer.msg('处理中...', {
			icon: 16,
			time: 9 * 10000,
			offset: '250px'
		});
	}
	else {
		layer.close(layerIndex);
    	layer.close(layerIndex2);
	}
}

var table = null;
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": false,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=userDefinedFormService&bizType=query&companyId=" + companyId,
      "columns": [
                  {"data": "index"},
                  {"data": "id"},
                  {"data": "name" ,"defaultContent": ""}
              ],
              "columnDefs": [{
                  "visible": false,
                  "targets": 1
              } ],
    });
	 
	 $('#example2 tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	    } );
    
  });
</script>

<script src="udfForm/controller.js"></script>