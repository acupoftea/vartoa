<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<style>
tr.selected{background-color:#FFFFCC}
</style>

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
              <h3 class="box-title"><b>公告管理</b></h3>
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
                  <th>id</th>
                  <th width="10%">类型</th>
                  <th width="15%">标题</th>
                  <th>内容</th>
                  <th>创建时间</th>
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

<script src="plugins/xheditor-1.2.2/xheditor-1.2.2.min.js"></script>
<script src="plugins/xheditor-1.2.2/xheditor_lang/zh-cn.js"></script>

<!-- page script -->
<script>
$('#elm1').xheditor({skin:'nostyle', upLinkUrl:"UploadifyServlet3",upLinkExt:"*"});
$('#elm2').xheditor({skin:'nostyle', upLinkUrl:"UploadifyServlet3",upLinkExt:"*"});

var table = null;
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": false,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=noticeService&bizType=query&companyId=" + companyId,
      "columns": [
                  {"data": "index"},
                  {"data": "id"},
                  {"data": "noticeTypeName" ,"defaultContent": ""},
                  {"data": "title" ,"defaultContent": ""},
                  {"data": "content" ,"defaultContent": ""},
                  {"data": "createtime" ,"defaultContent": "", 
                	  "render": function ( data, type, full, meta ) {
                		  return new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
                  }}
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
  
  $('#myModalCreate').off('shown.bs.modal').on('shown.bs.modal', function (e) {
      $(document).off('focusin.modal');//解决编辑器弹出层文本框不能输入的问题http://stackoverflow.com/questions/14795035/twitter-bootstrap-modal-blocks-text-input-field
  });
  
  $('#myModal4edit').off('shown.bs.modal').on('shown.bs.modal', function (e) {
      $(document).off('focusin.modal');//解决编辑器弹出层文本框不能输入的问题http://stackoverflow.com/questions/14795035/twitter-bootstrap-modal-blocks-text-input-field
  });
 
</script>

<script src="notice/controller.js"></script>