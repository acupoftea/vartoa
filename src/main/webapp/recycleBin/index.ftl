<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<style>
tr.selected{background-color:#FFFFCC}
</style>

<div ng-app="myApp" ng-controller="myCtrl">

<#include "confrim.ftl" />

<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>回收站</b></h3>
            </div>
     <a class="btn btn-app" ng-click="edit()">
                <i class="fa fa-edit"></i> 恢复
              </a>
      <a class="btn btn-app" data-toggle="modal" data-target="#myModalConfrim">
                <i class="fa fa-remove"></i> 彻底删除
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


<!-- date-range-picker -->
<script src="ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>

<!-- page script -->
<script>

//Date range picker with time picker
$('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'YYYY/MM/DD hh:mm'});

var table = null;
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": true,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=workflowInstanceService&bizType=query4recycleBin&userId=" + userId,
		      "columns": [
		{"data": "index"},
		{"data": "id"},
		{"data": "workflowType" ,"defaultContent": ""},
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

<script src="recycleBin/controller.js"></script>