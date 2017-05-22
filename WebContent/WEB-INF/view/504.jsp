<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/head_base.jsp"%>
<title>权限异常</title>
</head>
<body topmargin="0" leftmargin="0" style="background-color: #FFFFFF">
	<!--页帧-->
	<div class="titleb">
		<h1 class="titlec_2">&nbsp;&nbsp;&nbsp;&nbsp;权限错误</h1>
	</div>
	<br>
	<div class="tables">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="12" class="titlee">&nbsp;</td>
				<td width="100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td bgcolor="#FFFFFF">
								<h2>权限不足非法访问,请联系系统管理员！</h2>
							</td>
						</tr>
						<tr>
							<td bgcolor="#FFFFFF">
								<a href="<%=ctxPath %>/auth/sign-in.page"><h2 style="color: red">请重新登录</h2></a>
							</td>
						</tr>
						
					</table>
				</td>
				<td width="10" class="titlef">&nbsp;</td>
			</tr>
		</table>
	</div>
</body>
</html>

