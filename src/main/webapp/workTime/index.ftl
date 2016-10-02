<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<style>
tr.selected{background-color:#FFFFCC}
</style>
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="plugins/datepicker/css/bootstrap-datepicker.css">

<div ng-app="myApp" ng-controller="myCtrl">

<#include "confrim.ftl" />
<#include "create.ftl" />
<#include "edit.ftl" />


<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>排班管理</b></h3>
            </div>
           
            <a class="btn btn-app" data-toggle="modal" data-target="#myModalCreate">
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
                  <th>名称</th>
                  <th>上班时间</th>
                  <th>下班时间</th>
                  <th>时间范围</th>
                  <th>生效时间</th>
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
      "ajax": _serverPath + "/Commander?beanId=workTimeService&bizType=query&companyId=" + companyId,
      "columns": [
                  {"data": "index"},
                  {"data": "id"},
                  {"data": "name" ,"defaultContent": ""},
                  {"data": "starttime" ,"defaultContent": "", 
                	  "render": function ( data, type, full, meta ) {
                		  return new Date(Date.parse(data)).Format("hh:mm:ss");
                  }},
                  {"data": "endtime" ,"defaultContent": "", 
                	  "render": function ( data, type, full, meta ) {
                		  return new Date(Date.parse(data)).Format("hh:mm:ss");
                  }},
                  {"data": "range" ,"defaultContent": ""},
                  {"data": "startdate" ,"defaultContent": "", 
                	  "render": function ( data, type, full, meta ) {
                		  return new Date(Date.parse(data)).Format("yyyy-MM-dd");
                  }},
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

<!-- bootstrap datepicker -->
<script src="plugins/datepicker/js/bootstrap-datepicker.js"></script>

<script src="workTime/controller.js"></script>