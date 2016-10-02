<#include "../css.ftl"/>

<!-- fullCalendar 2.2.5-->
<link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.min.css">

<!-- daterange picker -->
<link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">

<div ng-app="myApp" ng-controller="myCtrl">

<#include "create.ftl" />
<#include "detail.ftl" />
<#include "confrim.ftl" />


<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title"><b>日历管理</b></h3>
            </div>
           
            <a class="btn btn-app" ng-click="showCreate()">
                <i class="fa fa-save"></i> 新建
            </a>
     
            <div class="box-body">
            	<div id="calendar"></div>
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
<script src="ajax/libs/moment.js/2.11.2/moment.min.js"></script>
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
  });
  
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
  
  	var calendarType = getParameter("type");
  
  
</script>

<script src="calendar/controller.js"></script>