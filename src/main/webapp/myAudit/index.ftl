<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<style>
tr.selected{background-color:#FFFFCC}
</style>

<div ng-app="myApp" ng-controller="myCtrl">

<#include "audit.ftl" />

<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>我的审核</b></h3>
            </div>
                <a class="btn btn-app" ng-click="showAudit()">
                <i class="fa fa-check-square-o"></i> 审核
              </a>
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover" data-page-length='25'>
                <thead>
                <tr>
	                <th width="3%"></th>
	                <th width="5%">id</th>
	                <th>流程类型</th>
	                <th>申请人</th>
	                <th>审批摘要</th>
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

<!-- 遮罩loading -->
<script src="plugins/layer/layer.js"></script>

<!-- page script -->
<script>

// 遮罩loading
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

//Date range picker with time picker
$('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'YYYY/MM/DD hh:mm'});

var table = null;
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": true,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=workflowInstanceService&bizType=queryAuditList&userId=" + userId,
      "columns": [
                  {"data": "index"},
                  {"data": "id"},
                  {"data": "workflowName" ,"defaultContent": ""},
                  {"data": "senderName" ,"defaultContent": ""},
                  {"data": "summary" ,"defaultContent": ""},
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

<script src="myAudit/controller.js"></script>