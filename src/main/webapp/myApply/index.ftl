<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="plugins/datepicker/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet" href="plugins/jQuery-File-Upload-master/css/jquery.fileupload.css">
<!-- Select2 -->
<link rel="stylesheet" href="plugins/select2/css/select2.css">

<style>
tr.selected{background-color:#FFFFCC}
</style>

<div ng-app="myApp" ng-controller="myCtrl">

<#include "confrim.ftl" />
<#include "create.ftl" />
<#include "apply.ftl" />
<#include "edit.ftl" />

<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>我的申请</b></h3>
            </div>
            <a class="btn btn-app" data-toggle="modal" data-target="#myModal" >
            <i class="fa fa-save"></i> 新建
          </a>
     <a class="btn btn-app" ng-click="showEdit()">
                <i class="fa fa-edit"></i> 编辑
              </a>
      <a class="btn btn-app" data-toggle="modal" ng-click="move2recycleBin()">
                <i class="fa fa-remove"></i> 删除
              </a>
                
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover" data-page-length='25'>
                <thead>
                <tr>
                  <th width="3%"></th>
                  <th width="5%">id</th>
                  <th>流程名称</th>
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

<!-- bootstrap datepicker -->
<script src="plugins/datepicker/js/bootstrap-datepicker.js"></script>
<script src="plugins/datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>


<!-- 遮罩loading -->
<script src="plugins/layer/layer.js"></script>

<script src="plugins/jQuery-File-Upload-master/js/vendor/jquery.ui.widget.js"></script>
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload.js"></script>

<!-- Select2 -->
<script src="plugins/select2/js/select2.js"></script>

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

$(".select2").select2();
$.fn.modal.Constructor.prototype.enforceFocus = function () { };

// Date range picker with time picker
$('#reservationtime').daterangepicker({
	  "timePicker": true,
	    "timePicker24Hour": true,
	    "locale": {
	        "format": "YYYY/MM/DD HH:mm",
	        "separator": " - ",
	        "applyLabel": "确认",
	        "cancelLabel": "取消",
	        "fromLabel": "从",
	        "toLabel": "到",
	        "customRangeLabel": "Custom",
	        "weekLabel": "W",
	        "daysOfWeek": [
	            "日",
	            "一",
	            "二",
	            "三",
	            "四",
	            "五",
	            "六"
	        ],
	        "monthNames": [
	            "一月",
	            "二月",
	            "三月",
	            "四月",
	            "五月",
	            "六月",
	            "七月",
	            "八月",
	            "九月",
	            "十月",
	            "十一月",
	            "十二月"
	        ],
	        "firstDay": 1
	    }
});

var table = null;
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": true,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=workflowInstanceService&bizType=query&userId=" + userId,
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

<script src="myApply/controller.js"></script>