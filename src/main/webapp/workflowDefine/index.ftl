<#include "../css.ftl"/>

<!-- DataTables -->
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<style>
tr.selected{background-color:#FFFFCC}

.selectbox{width:500px;height:220px;margin:0px auto;}
.selectbox div{float:left;}
.selectbox .select-bar{padding:0 20px;}
.selectbox .select-bar select{width:150px;height:200px;border:1px #A0A0A4 solid;padding:4px;font-size:14px;font-family:"microsoft yahei";}
.btn-bar{}
.btn-bar p{margin-top:16px;}
.btn-bar p .btn{width:50px;height:30px;cursor:pointer;font-family:simsun;font-size:14px;}

</style>

<div ng-app="myApp" ng-controller="myCtrl">

<#include "confrim.ftl" />
<#include "create.ftl" />
<#include "edit.ftl" />
<#include "userListWin.ftl" />
<#include "userListWin2.ftl" />


<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>审批流程管理</b></h3>
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
                  <th>流程名称</th>
                </tr>
                </thead>
              </table>
            </div>
          </div>
      </div>
</div>

<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="plugins/jQueryUI/jquery-ui.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="plugins/fastclick/fastclick.js"></script>
<script src="dist/js/app.min.js"></script>

<!-- 遮罩loading -->
<script src="plugins/layer/layer.js"></script>

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
      "ajax": _serverPath + "/Commander?beanId=workflowService&bizType=query&companyId=" + companyId,
      "columns": [
                  {"data": "index"},
                  {"data": "id"},
                  //{"data": "workflowTypeName" ,"defaultContent": ""},
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
	 
	 /*
	 table2 = $('#workflowNodeTable').DataTable({
	 	"paging":   false,
        "ordering": false,
        "info":     false,
	    "lengthChange": false,
	    "searching": false,
	    "processing": true,
	    "serverSide": true,
	    "ajax": _serverPath + "/Commander?beanId=userService&bizType=query",
	    "columns": [
	                {"data": "index"},
	                {"data": "id"},
	                {"data": "username" ,"defaultContent": ""},
	                {"data": "loginname" ,"defaultContent": ""},
	                {"data": "roleName" ,"defaultContent": ""},
	            ],
	            "columnDefs": [{
	                "visible": false,
	                "targets": 1
	            } ],
	  });
	  */
    
  });
</script>

<script type="text/javascript">
$(function(){	
	//移到右边
	$('#add').click(function(){
		//先判断是否有选中
		if(!$("#select1 option").is(":selected")){			
			alert("请选择需要移动的选项")
		}
		//获取选中的选项，删除并追加给对方
		else{
			$('#select1 option:selected').appendTo('#select2');
		}	
	});
	
	//移到左边
	$('#remove').click(function(){
		//先判断是否有选中
		if(!$("#select2 option").is(":selected")){			
			alert("请选择需要移动的选项")
		}
		else{
			$('#select2 option:selected').appendTo('#select1');
		}
	});
	
	//全部移到右边
	$('#add_all').click(function(){
		//获取全部的选项,删除并追加给对方
		$('#select1 option').appendTo('#select2');
	});
	
	//全部移到左边
	$('#remove_all').click(function(){
		$('#select2 option').appendTo('#select1');
	});
	
	//双击选项
	$('#select1').dblclick(function(){ //绑定双击事件
		//获取全部的选项,删除并追加给对方
		$("option:selected",this).appendTo('#select2'); //追加给对方
	});
	
	//双击选项
	$('#select2').dblclick(function(){
		$("option:selected",this).appendTo('#select1');
	});
	
});
</script>

<script src="workflowDefine/controller.js"></script>