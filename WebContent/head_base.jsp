<%
	String ctxPath = request.getContextPath();
%>
<base id="pageBase" href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=ctxPath%>/" />
<script>
	document.getElementById("pageBase").href = document.getElementById("pageBase").href;
</script>
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	
	<!-- 
    <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/lib/bootstrap/css/bootstrap.css">
     -->
    <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/libraries/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/libraries/bootstrap/css/bootstrap-responsive.min.css">
    <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/libraries/toastr/toastr.min.css">
    <link rel="stylesheet" type="text/css" href="<%=ctxPath %>/stylesheets/theme.css">
    <link rel="stylesheet" href="<%=ctxPath %>/lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="<%=ctxPath %>/mycss/common.css">
    <link rel="stylesheet" href="<%=ctxPath %>/mycss/button.css">
    <script src="<%=ctxPath %>/libraries/jquery-1.11.1.min.js"></script>
    <!-- 
    <script src="<%=ctxPath %>/lib/bootstrap/js/bootstrap.js"></script>
     -->
    <script src="<%=ctxPath %>/libraries/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=ctxPath %>/libraries/toastr/toastr.min.js"></script>
    <script src="<%=ctxPath %>/myjs/common.js"></script>
    <script src="<%=ctxPath %>/myjs/AjaxUtil.js"></script>
    
    <!-- Le fav and touch icons -->
    <!-- 
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    
    
	<style type="text/css">
	#line-chart {
		height: 300px;
		width: 800px;
		margin: 0px auto;
		margin-top: 1em;
	}
	
	.brand {
		font-family: georgia, serif;
	}
	
	.brand .first {
		color: #ccc;
		font-style: italic;
	}
	
	.brand .second {
		color: #fff;
		font-weight: bold;
	}
	</style>
     -->
