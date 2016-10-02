<#include "../css.ftl"/>

<div ng-app="myApp" ng-controller="myCtrl">

<div class="content-wrapper">

<!-- Main content -->
<section class="content">
  <!-- Main row -->
  <div class="row">
    <!-- Left col -->
    <section class="col-lg-7 connectedSortable">

      <!-- TO DO List -->
      <div class="box box-primary">
        <div class="box-header">
          <i class="ion ion-clipboard"></i>
          <h3 class="box-title">待办事宜</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <ul class="todo-list">
          
            <li ng-repeat="x in auditList">
                  <span class="handle">
                    <i class="fa fa-ellipsis-v"></i>
                    <i class="fa fa-ellipsis-v"></i>
                  </span>
              <span class="text">{{x.createtime | date : 'yyyy-MM-dd HH:mm:ss'}}&nbsp;-&nbsp;{{x.senderName}}&nbsp;-&nbsp;{{x.summary}}</span>
              <div class="tools">
                <a href="Jump?url=myAudit/index.ftl" class="fa fa-edit"></a>
              </div>
            </li>
            
          </ul>
        </div>
        <!-- /.box-body -->
      </div>
    </section>
    <!-- /.Left col -->
    
    <!-- right col (We are only adding the ID to make the widgets sortable)-->
    <section class="col-lg-5 connectedSortable">
          <div class="box box-default">
            <div class="box-header with-border">
              <i class="fa fa-bullhorn"></i>

              <h3 class="box-title">通知公告</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
            	<div ng-class="{1: 'callout callout-info', 2: 'callout callout-warning', 3: 'callout callout-success'}[x.noticetype]" ng-repeat="x in noticeList">
            		<h3>{{x.title}}</h3>
            		<h4>{{x.createtime}}</h4>
            		<p ng-bind-html="trustAsHtml(x.content)"></p>
            	</div>
            </div>
           </div>

    </section>
    <!-- right col -->
  </div>
  <!-- /.row (main row) -->

</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<!-- jQuery 2.2.0 -->
<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="plugins/jQueryUI/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>

<script src="homePage/controller.js"></script>