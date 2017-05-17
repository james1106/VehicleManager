<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/head_base.jsp"%>
<title>登录</title>
</head>

<body class="" onkeydown='if(event.keyCode==13){gosubmit();}'>
	<div class="row"></div>
	<div class="row-fluid">
		<div class="dialog">
			<div class="block">
				<p class="block-heading">登录</p>
				<div class="block-body">
					<form id="signInForm" name="signIn" action="<%=ctxPath%>/auth/signIn.action" method="post">
						<label>用户名</label> <input name="username" type="text" class="span12">
						<label>密码</label> <input name="password" type="password" class="span12">
						<!-- <a href="javascript:gosubmit();" class="btn btn-primary pull-right">登 录</a> -->
						<!-- login button begin -->
						<a href="javascript:gosubmit();" class="bt_green">
							<span class="bt_green_lft"></span>
							<strong>登 录</strong>
							<span class="bt_green_r"></span>
						</a>
						<!-- login button end -->
						<label class="remember-me"><input type="checkbox">记住我</label>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
			<p id="errMsg" style="color: red"></p>
			<!-- 
			<p class="pull-right" style="">
				<a href="reset-password.html">忘记密码?</a>
			</p>
			<p>
				<a href="#" target="blank"></a>
			</p>
			 -->
		</div>
	</div>

</body>

<script type="text/javascript">
	$(function() {
		commonUI();
	});
	
	function gosubmit(){
		formAjax("signInForm", "<%=ctxPath%>/auth/signIn.action", loginBackFunc);
	}
	
	function loginBackFunc(data){
		
	}
</script>
</html>

<!-- 
<a href="#" class="bt_green"><span class="bt_green_lft"></span><strong>Add new item</strong><span class="bt_green_r"></span></a>

 -->

