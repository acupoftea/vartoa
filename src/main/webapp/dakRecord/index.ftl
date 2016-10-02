<#include "../css.ftl"/>

<#--
<link rel="stylesheet" href="css/attendance.css">
<link rel="stylesheet" href="css/common212.css">
-->

<!-- fullCalendar 2.2.5-->
<link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.min.css">
<link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">

<style>
.fc-time{
	   display : none;
	}
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
              <h3 class="box-title"><b>考勤记录</b></h3>
            </div>
            
            <div class="box-body">
            <!-- Date -->
            <div class="form-group">

              <div class="input-group date">
                <div class="input-group-addon">
                  <i class="fa fa-calendar"></i>
                </div>
                <input type="text" class="form-control" id="reservationtime" style="width:20%;height:30px;">&nbsp;
                <button type="button" style="width:5%;height:30px;" ng-click="queryStatisticsData()">查询</button>
              </div>
              <!-- /.input group -->
            </div>
            <!-- /.form group -->
            </div>
            
            <div class="row-fluid">
        	<div class="span20">
        		
        		<table class="table table-hover table-head table-statistics bottom-line" border="1">
        			<thead>
        				<tr>
        					<th class="text-center">实际出勤(天)</th>
        					<th class="text-center">迟到(次)</th>
        					<th class="text-center">早退(次)</th>
        					<th class="text-center">IP异常(次)</th>
        				</tr>
        			</thead>
        			<tbody>
        				<tr>
        					</tr><tr>
        					<td class="text-center remark_g">
        						{{attendance.chuqNu}}
        					</td><td class="text-center remark_r">
        						{{attendance.chidNu}}
        					</td><td class="text-center remark_r">
        						{{attendance.zaotNu}}
        					</td><td class="text-center remark_r">
        						{{attendance.iperrorNu}}
        					</td>
        				</tr>
        			</tbody>
        		</table>
        	</div>
        </div>
        
        <div class="box box-primary">
          <div class="box-body">
          	<h3>工作时间: {{workTime.starttime}} ~ {{workTime.endtime}}</h3>
            <!-- THE CALENDAR -->
            <div id="calendar"></div>
          </div>
          <!-- /.box-body -->
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

<!-- fullCalendar 2.2.5 -->
<script src="ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="plugins/fullcalendar/fullcalendar.min.js"></script>

<!-- date-range-picker -->
<script src="plugins/daterangepicker/daterangepicker.js"></script>

<!-- Page specific script -->
<script>
  $(function () {
  $('#calendar').fullCalendar({
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      monthNames: ["一月", "二月", "三月", "四月",//设置月份名称，中英文均可
                    "五月", "六月", "七月", "八月", "九月",
                    "十月", "十一月", "十二月"
        ],
        monthNamesShort: ["一月", "二月", "三月",//设置月份的简称
            "四月", "五月", "六月", "七月", "八月",
            "九月", "十月", "十一月", "十二月"
        ],
        dayNames: ["周日", "周一", "周二", "周三",//设置星期名称
            "周四", "周五", "周六"
        ],
        dayNamesShort: ["周日", "周一", "周二",//设置星期简称
            "周三", "周四", "周五", "周六"
        ],
        today: ["今天"],//today 按钮的显示名称
        firstDay: 0,//设置每星期的第一天是星期几，0 是星期日
        buttonText: {//设置按钮文本
            today: '今天',
            month: '月',
            week: '周',
            day: '日',
            prev: '上一月',
            next: '下一月'
        },
        
        currentTimezone: 'Asia/Beijing',//设置时区
        selectable: true,//元素是否可以被选中
        eventClick: function(event) {//点击事项的方法
        	showDetail(event);
        }
    });
    
	  $('#reservationtime').daterangepicker({
		  //"timePicker": true,
		    //"timePicker24Hour": true,
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
	  
	  $('#reservationtime').val("");
    
  });
</script>

<script src="dakRecord/controller.js"></script>
