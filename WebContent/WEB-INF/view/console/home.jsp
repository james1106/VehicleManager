<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/head_base.jsp"%>
	<title>军区通用车辆维修管理系统</title>
<%@ page import='com.xpizza.vclemgr.domain.*'%>
<%
	User user = (User)request.getSession().getAttribute("SESSION_USER");
	Long roleId = user.getRole().getId();
%>
<body>
    <!-- navbar -->
    <div class="navbar navbar-default">
            <!-- logo -->
            <!-- <a class="brand" href="index.html"><img src="img/logo.png" /></a> -->

            <ul class="nav navbar-nav navbar-right">  
            	<!-- search menu -->     
            	<!--         
                <li class="hidden-phone">
                    <input class="search" type="text" />
                </li>
                 -->
                <!-- notice -->
                <!-- 
                <li class="notification-dropdown hidden-phone">
                    <a href="#" class="trigger">
                        <i class="icon-warning-sign"></i>
                        <span class="count">22</span>
                    </a>
                    <div class="pop-dialog">
                        <div class="pointer right">
                            <div class="arrow"></div>
                            <div class="arrow_border"></div>
                        </div>
                        <div class="body">
                            <a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
                            <div class="notifications">
                                <h3>You have 6 new notifications</h3>
                                <a href="#" class="item">
                                    <i class="icon-signin"></i> New user registration
                                    <span class="time"><i class="icon-time"></i> 13 min.</span>
                                </a>
                                <a href="#" class="item">
                                    <i class="icon-signin"></i> New user registration
                                    <span class="time"><i class="icon-time"></i> 18 min.</span>
                                </a>
                                <a href="#" class="item">
                                    <i class="icon-envelope-alt"></i> New message from Alejandra
                                    <span class="time"><i class="icon-time"></i> 28 min.</span>
                                </a>
                                <a href="#" class="item">
                                    <i class="icon-signin"></i> New user registration
                                    <span class="time"><i class="icon-time"></i> 49 min.</span>
                                </a>
                                <a href="#" class="item">
                                    <i class="icon-download-alt"></i> New order placed
                                    <span class="time"><i class="icon-time"></i> 1 day.</span>
                                </a>
                                <div class="footer">
                                    <a href="#" class="logout">View all notifications</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                 -->
                <!-- mail -->
                <!-- 
                <li class="notification-dropdown hidden-phone">
                    <a href="#" class="trigger">
                        <i class="icon-envelope-alt"></i>
                    </a>
                    <div class="pop-dialog">
                        <div class="pointer right">
                            <div class="arrow"></div>
                            <div class="arrow_border"></div>
                        </div>
                        <div class="body">
                            <a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
                            <div class="messages">
                                <a href="#" class="item">
                                    <img src="img/contact-img.png" class="display" />
                                    <div class="name">Alejandra GalvÃ¡n</div>
                                    <div class="msg">
                                        There are many variations of available, but the majority have suffered alterations.
                                    </div>
                                    <span class="time"><i class="icon-time"></i> 13 min.</span>
                                </a>
                                <a href="#" class="item">
                                    <img src="img/contact-img2.png" class="display" />
                                    <div class="name">Alejandra GalvÃ¡n</div>
                                    <div class="msg">
                                        There are many variations of available, have suffered alterations.
                                    </div>
                                    <span class="time"><i class="icon-time"></i> 26 min.</span>
                                </a>
                                <a href="#" class="item last">
                                    <img src="img/contact-img.png" class="display" />
                                    <div class="name">Alejandra GalvÃ¡n</div>
                                    <div class="msg">
                                        There are many variations of available, but the majority have suffered alterations.
                                    </div>
                                    <span class="time"><i class="icon-time"></i> 48 min.</span>
                                </a>
                                <div class="footer">
                                    <a href="#" class="logout">View all messages</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle hidden-phone" data-toggle="dropdown"> 
                    	${sessionScope.SESSION_USERNAME}(${sessionScope.SESSION_USER.role.name})<b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                    	<!-- 
                        <li><a href="javascript:loadRight('template/personal-info.html');">角色管理</a></li>
                        <li class="divider"></li>
                         -->
                        <li><a href="javascript:loadRight('template/resetPwd.html');">修改密码</a></li>
                    </ul>
                </li>
                <!-- 
                <li class="settings hidden-phone">
                    <a href="personal-info.html" role="button">
                        <i class="icon-cog icon-2x"></i>
                    </a>
                </li>
                 -->
                <li class="settings hidden-phone">
                    <a href="auth/signOut.do" role="button">
                        <i class="icon-signout icon-2x"></i>
                    </a>
                </li>
                <li>&nbsp;</li>
            </ul>            
    </div>
    <!-- end navbar -->

    <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            <li class="active">
                <div id="pointer" class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a href="<%=ctxPath%>/console/home.page">
                    <i class="icon-home"></i>
                    <span>首页</span>
                </a>
            </li>            
            <li>
                <a href="javascript:loadRight('template/eqLossPie.html');">
                    <i class="icon-signal"></i>
                    <span>图表</span>
                </a>
            </li>
            <% if(roleId == 2){ // 只有管理助手才有权限操作用户和角色%>
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-group"></i>
                    <span>用户</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                	<li><a href="javascript:loadRight('template/role-list.html');">角色管理</a></li>
                	<li><a href="javascript:loadRight('template/user-list.html');">用户管理</a></li>
                </ul>
            </li>
            <%} %>
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-cog"></i>
                    <span>设备</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                	<li><a href="javascript:loadRight('template/equipment-list.html');">器材管理</a></li>
                    <li><a href="javascript:loadRight('template/vehicle-list.html');">车辆管理</a></li>
                </ul>
            </li>
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-envelope"></i>
                    <span>单据</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                	<% if(roleId == 1 || roleId == 2 || roleId == 3){ %>
                    <li><a href="javascript:loadRight('template/repairApply.html');">维修申请</a></li>
                    <%} %>
                    <% if(roleId == 2 || roleId == 3 || roleId == 4){ %>
                    <li><a href="javascript:loadRight('template/repairNotice.html');">维修通知</a></li>
                    <%} %>
                    <% if(roleId == 4){ %>
                    <li><a href="javascript:loadRight('template/repairBill.html');">维修记录</a></li>
                    <%} %>
                </ul>
            </li>
        </ul>
    </div>
    <!-- end sidebar -->


	<!-- main container -->
    <div class="content">

        <!-- settings changer -->
        <!-- 
        <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default skin</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div>
         -->

		<!-- Ajax Model -->
        <div class="container-fluid">
        </div>
    </div>

	<input type="hidden" id="temp" name="temp">

	<!-- scripts -->
    
    <script type="text/javascript">
        $(function () {
        	commonUI();
            // jQuery Flot Chart
            var visits = [[1, 50], [2, 40], [3, 45], [4, 23],[5, 55],[6, 65],[7, 61],[8, 70],[9, 65],[10, 75],[11, 57],[12, 59]];
            var visitors = [[1, 25], [2, 50], [3, 23], [4, 48],[5, 38],[6, 40],[7, 47],[8, 55],[9, 43],[10,50],[11,47],[12, 39]];
            var plot = $.plot($("#statsChart"),
                [ { data: visits, label: "Signups"},
                 { data: visitors, label: "Visits" }], {
                    series: {
                        lines: { show: true,
                                lineWidth: 1,
                                fill: true, 
                                fillColor: { colors: [ { opacity: 0.1 }, { opacity: 0.13 } ] }
                             },
                        points: { show: true, 
                                 lineWidth: 2,
                                 radius: 3
                             },
                        shadowSize: 0,
                        stack: true
                    },
                    grid: { hoverable: true, 
                           clickable: true, 
                           tickColor: "#f9f9f9",
                           borderWidth: 0
                        },
                    legend: {
                            // show: false
                            labelBoxBorderColor: "#fff"
                        },  
                    colors: ["#a7b5c5", "#30a0eb"],
                    xaxis: {
                        ticks: [[1, "JAN"], [2, "FEB"], [3, "MAR"], [4,"APR"], [5,"MAY"], [6,"JUN"], 
                               [7,"JUL"], [8,"AUG"], [9,"SEP"], [10,"OCT"], [11,"NOV"], [12,"DEC"]],
                        font: {
                            size: 12,
                            family: "Open Sans, Arial",
                            variant: "small-caps",
                            color: "#697695"
                        }
                    },
                    yaxis: {
                        ticks:3, 
                        tickDecimals: 0,
                        font: {size:12, color: "#9da3a9"}
                    }
                 });
        });
    </script>
</body>
</html>