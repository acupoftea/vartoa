<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<style>
tr.selected{background-color:#FFFFCC}
</style>

<div ng-app="myApp" ng-controller="myCtrl">

<#include "detail.ftl" />

<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>已归档</b></h3>
            </div>
     <a class="btn btn-app" ng-click="showDetail()">
                <i class="fa fa-file-text-o"></i> 详情
              </a>
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover" data-page-length='25'>
                <thead>
                <tr>
	                <th width="3%"></th>
	                <th width="5%">id</th>
	                <th>流程类型</th>
	                <th>当前位置</th>
	                <th>审批人</th>
	                <th>审批摘要</th>
	                <th>审批结果</th>
	                <th>申请时间</th>
	                <th>修改时间</th>
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

<!-- page script -->
<script>
var table = null;
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": false,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=workflowInstanceService&bizType=query4archiving&userId=" + userId,
      "columns": [
			{"data": "index"},
			{"data": "id"},
			{"data": "workflowName" ,"defaultContent": ""},
			{"data": "nodeName" ,"defaultContent": ""},
			{"data": "receiverName" ,"defaultContent": ""},
			{"data": "summary" ,"defaultContent": ""},
			{"data": "workflowStatus" ,"defaultContent": ""},
			{"data": "createtime" ,"defaultContent": "", 
				  "render": function ( data, type, full, meta ) {
					  return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
			}},
			{"data": "updatetime" ,"defaultContent": "", 
				  "render": function ( data, type, full, meta ) {
					  return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
			}},
			],
			"columnDefs": [ {
			"visible": false,
			"targets": 1
			}  ],
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

<script src="historyApply/controller.js"></script>