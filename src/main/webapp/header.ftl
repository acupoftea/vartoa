<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv=Cache-Control content=no-cache />
  <meta name="renderer" content="webkit">
  <title>VART | OA</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
  <link rel="stylesheet" href="dist/css/skins/skin-red.css">
  <!-- fullCalendar 2.2.5-->
  <link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.min.css">
  <link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.print.css" media="print">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-red sidebar-mini ">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>OA</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>VART</b>OA</span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button -->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- Menu toggle button -->
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- Inner Menu: contains the notifications -->
                <ul class="menu">
                  <li><!-- start notification -->
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <!-- end notification -->
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">${userMap.userName}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                	${userMap.companyName} - ${userMap.departmentName}
                  <small>${userMap.roleName} - ${userMap.userName}</small>
                </p>
              </li>
              
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat" data-toggle="modal" data-target="#modalChangePsw">修改密码</a>
                </div>
                <div class="pull-right">
                  <a href="index.jsp" class="btn btn-default btn-flat">退出</a>
                </div>
              </li>
            </ul>
          
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">导航面板</li>
        <!-- Optionally, you can add icons to the links -->
        
        <li>
          <a target="ie" href="Jump?url=homePage/index.ftl">
            <i class="fa fa-dashboard"></i> <span>首页</span>
          </a>
        </li>
        
        <li class="treeview">
	  		<a href="#"><i class="fa fa-check-square-o"></i> <span>审批</span> <i class="fa fa-angle-left pull-right"></i></a>
	  		<ul class="treeview-menu">
	   			<li><a target="ie" href="Jump?url=myApply/index.ftl"><i class="fa fa-circle-o"></i>我的申请</a></li>
	   			<li><a target="ie" href="Jump?url=myAudit/index.ftl"><i class="fa fa-circle-o"></i>我的审批</a></li>
	   			<li><a target="ie" href="Jump?url=cc2me/index.ftl"><i class="fa fa-circle-o"></i>抄送给我的</a></li>
	   			<li><a target="ie" href="Jump?url=historyApply/index.ftl"><i class="fa fa-circle-o"></i>已归档</a></li>
	   			<li><a target="ie" href="Jump?url=recycleBin/index.ftl"><i class="fa fa-circle-o"></i>回收站</a></li>
	  		</ul>
	    </li>
	    
	    <li>
	  		<a target="ie"  href="Jump?url=calendar/index.ftl">
	  			<i class="fa fa-calendar"></i> <span>日历</span>
  			</a>
	    </li>
        
	    <li class="treeview">
	  		<a href="#"><i class="fa fa-bus"></i> <span>考勤</span> <i class="fa fa-angle-left pull-right"></i></a>
	  		<ul class="treeview-menu">
	   			<li><a target="ie" href="Jump?url=signin/index.ftl"><i class="fa fa-circle-o"></i>考勤打卡</a></li>
	   			<li><a target="ie" href="Jump?url=dakRecord/index.ftl"><i class="fa fa-circle-o"></i>考勤记录</a></li>
	   			<!--<li><a target="ie" href="Jump?url=createing.ftl"><i class="fa fa-circle-o"></i>我的班次</a></li>-->
	  		</ul>
	    </li>
        
	    <li class="treeview">
	  		<a href="#"><i class="fa fa-bullhorn"></i> <span>公告</span> <i class="fa fa-angle-left pull-right"></i></a>
	  		<ul class="treeview-menu">
	   			<li class="active"><a target="ie" href="Jump?url=notice/index4employee.ftl?noticeType=1"><i class="fa fa-circle-o"></i>热点新闻</a></li>
	   			<li><a target="ie" href="Jump?url=notice/index4employee.ftl?noticeType=2"><i class="fa fa-circle-o"></i>企业动态</a></li>
	   			<li><a target="ie" href="Jump?url=notice/index4employee.ftl?noticeType=3"><i class="fa fa-circle-o"></i>文档中心</a></li>
	  		</ul>
	    </li>
        
	    <#if userPowerMap.faterPower??>
	        <li class="treeview">
	      		<a href="#"><i class="fa fa-gear"></i> <span>${userPowerMap.faterPower.modelName?if_exists}</span> <i class="fa fa-angle-left pull-right"></i></a>
	      		<ul class="treeview-menu">
	      			<#list userPowerMap.sonPowerList?if_exists as list>
	       				<li><a target="ie" href="${list.modelUrl?if_exists}"><i class="fa fa-circle-o"></i>${list.modelName?if_exists}</a></li>
	       			</#list>
	      		</ul>
	        </li>
        </#if>
        
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>
  
  <script src="plugins/angularJs/angular.min.js"></script>
  <#include "changePsw.ftl" />