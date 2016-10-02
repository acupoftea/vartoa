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
              <h3 class="box-title"><b>公告列表</b></h3>
            </div>
           
     <a class="btn btn-app" ng-click="showDetail()">
                <i class="fa fa-edit"></i> 详情
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

<!-- page script -->
<script>
	function getParameter(param) {
		var query = window.location.search;
		var iLen = param.length;
		var iStart = query.indexOf(param);
		if (iStart == -1)
		　return "";
	
		iStart += iLen + 1;
		var iEnd = query.indexOf("&", iStart);
		if (iEnd == -1)
		　return query.substring(iStart);
	
		return query.substring(iStart, iEnd);
	}

	var noticeType = getParameter("noticeType");
	
	var table = null;
	
  $(function () {
	 table = $('#example2').DataTable({
      "lengthChange": false,
      "searching": false,
      "processing": true,
      "serverSide": true,
      "ajax": _serverPath + "/Commander?beanId=noticeService&bizType=query4employ&companyId=" + companyId + "&noticeType=" + noticeType,
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
 
</script>

<script src="notice/controller.js"></script>