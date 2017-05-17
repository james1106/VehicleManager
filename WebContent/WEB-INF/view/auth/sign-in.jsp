<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/head_base.jsp"%>
<title>登录</title>

</head>
<body class="">
	<div class="row"></div>
	<div class="row-fluid">
		<div class="dialog">
			<div class="block">
				<p class="block-heading">登录</p>
				<div class="block-body">
					<form>
						<label>用户名</label> <input type="text" class="span12">
						<label>密码</label> <input type="password" class="span12">
						<a href="index.html" class="btn btn-primary pull-right">登 录</a>
						<label class="remember-me"><input type="checkbox">记住我</label>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
			<p class="pull-right" style="">
				<a href="#" target="blank"></a>
			</p>
			<p>
				<a href="reset-password.html">忘记密码?</a>
			</p>
		</div>
	</div>

	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>

</body>
</html>
