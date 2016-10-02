·<#include "../css.ftl"/>

<link rel="stylesheet" href="css/attendance.css">
<link rel="stylesheet" href="css/common212.css">

<div ng-app="myApp" ng-controller="myCtrl">

<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>考勤打卡</b></h3>
            </div>
            
            <div class="row-fluid me-margin-top10">
        	<div class="span4 w210">
        		<div class="date-wrap" style="margin: 0 auto;">
        			<div class="date-top">{{today_yearMonth}}</div>
        			<div class="date-body">
        				<div class="date-day">{{today_day}}</div>
        				<div class="p14">{{today_week}}</div>
        			</div>
        		</div>
        	</div>
        	<div class="span16 takeAtdW" style="margin-left:0">
        		<div class="me-padding-right10">
        			<table class="table table-shift">
        				<tbody><tr>
        					<td>
        					{{workTime.name}}
        					</td>
        				</tr>
        			</tbody></table>
        			<table class="table table-bordered attend">
        				<tbody><tr>
        					<td class="span4 textcenter" rowspan="2">
        						<span class="p14">
        							 {{workTime.starttime}} ~ {{workTime.endtime}}
        						</span>
        					</td>
        					<td>
        						<div class="pull-left me-margin-top3">
        									<strong ng-class="{0: 'vertop', 1: 'vertop remark_r'}[attendance.chid]">
        									<span class="p16">{{attendance.starttime}}</span>
        									</strong>
        									
        									<span ng-class="{0: 'vertop', 1: 'vertop remark_r'}[attendance.iperror]">
        											&nbsp;&nbsp;&nbsp;{{attendance.ip}}
        									</span>
        						</div>
        						
        						<button class="btn btn-primary btn-big pull-right" ng-click="signin()" id="signinButton">签到</button>
        						<button style="display:none" class="btn btn-big pull-right" timespan="09:00 ~ 18:00" ng-click="signinShenS()">申述</button>
        								    
        					</td>
        				</tr>
        				<tr>
        					<td>
        						<div class="pull-left me-margin-top3">
        									<strong ng-class="{0: 'vertop', 1: 'vertop remark_r'}[attendance.zaot]">{{attendance.endtime}}</strong> 
        									
        									<span ng-class="{0: 'vertop', 1: 'vertop remark_r'}[attendance.iperror]" ng-if="attendance.endtime">
        									&nbsp;&nbsp;&nbsp;{{attendance.ip}}
									</span>
        						</div>
								<button class="btn btn-primary btn-big pull-right" ng-click="signout()" id="signoutButton">签退</button>
								<button style="display:none" class="btn btn-big pull-right" timespan="09:00 ~ 18:00" onclick="attend.getAppealModal('309443170','-1',1,'20160704',this)">申述</button>
        					</td>
        				</tr>
        			</tbody></table>
        	

        <table class="table table-bordered attend">
        	<tbody><tr>
        		<td class="span4 textcenter attend-remark">
        			<span class="muted">备注</span>
        		</td>
        		<td>
        			<span class="ws97" id="mgtEditSpan" style="display: inline;"></span>
        			<div id="mgtEditTextDiv">
        				<textarea rows="3" class="ws97" ng_model="attendance.remark" maxlength="140" placeholder="" style="resize:none;"></textarea>
        				<div class="me-margin-top10 text-right">
        					<button class="btn btn-primary btn-big pull-right" id="okBtn" ng-click="submitRemark()">提交</button>
        				</div>
        			</div>
        		</td>
        	</tr>
        </tbody></table>
        		</div>
        	</div>
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

<script src="signin/controller.js"></script>
