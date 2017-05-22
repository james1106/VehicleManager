<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/head_base.jsp"%>
	<title>登录</title>
    <link type="text/css" rel="stylesheet" href="<%=ctxPath %>/plugin/css/signin.css" />
<body background="img/bg.jpg">


    <!-- background switcher -->
    <!-- 
    <div class="bg-switch visible-desktop">
        <div class="bgs">
            <a href="#" data-img="landscape.jpg" class="bg active">
                <img src="img/bgs/landscape.jpg" />
            </a>
            <a href="#" data-img="blueish.jpg" class="bg">
                <img src="img/bgs/blueish.jpg" />
            </a>            
            <a href="#" data-img="7.jpg" class="bg">
                <img src="img/bgs/7.jpg" />
            </a>
            <a href="#" data-img="8.jpg" class="bg">
                <img src="img/bgs/8.jpg" />
            </a>
            <a href="#" data-img="9.jpg" class="bg">
                <img src="img/bgs/9.jpg" />
            </a>
            <a href="#" data-img="10.jpg" class="bg">
                <img src="img/bgs/10.jpg" />
            </a>
            <a href="#" data-img="11.jpg" class="bg">
                <img src="img/bgs/11.jpg" />
            </a>
        </div>
    </div>
     -->


    <div class="row-fluid login-wrapper">
        <a href="index.html">
        </a>

        <div class="span4 box">
            <div class="content-wrap">
                <h6>登录</h6>
                <!-- 
                <input name="username" class="span12" type="text" placeholder="输入用户名" />
                <input name="password" class="span12" type="password" placeholder="输入密码" />
                 -->
                	<form id="signInForm" class="form-inline" role="form" action="auth/signIn.action" method="post">
                		<div class="row" align="center">
		                	<input type="text" class="form-control" name="username" required="required" placeholder="输入用户名" style="width:300px">
		                </div>
		                <div class="row" align="center">
		                	<input type="password" class="form-control" name="password" required="required" placeholder="输入密码" style="width:300px">
		                </div>
		                <br/>
                		<a class="btn-glow primary login" href="javascript:signIn();">登录</a>
                	</form>
            </div>
        </div>

        <div class="span4 no-account">
        </div>
    </div>

    <!-- pre load bg imgs -->
    <script type="text/javascript">
        $(function () {
        	commonUI();
        });
        
        function signIn(){
        	if(!emptyValidate("signInForm")){
        		warn("用户名或密码输入为空!");
        		return;
        	}
        	formAjax("signInForm", signInBack);
        }
        
        function signInBack(data){
			if(data.isOk){
				window.location.href="<%=ctxPath %>/console/home.page";
			}else {
				error(data.msg);
			}
        }
        
    </script>
</body>
</html>