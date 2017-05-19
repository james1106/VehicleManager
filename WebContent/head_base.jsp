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

	<!-- css -->	
	<!-- my lib -->
	<link type="text/css" rel="stylesheet" href="<%=ctxPath %>/font/font-awesome.css" />
	<link type="text/css" rel="stylesheet" href="<%=ctxPath %>/font/googleapis-fonts/fonts.css" />
	<!-- open source lib -->
	<!-- bootstrap -->
	<link type="text/css" rel="stylesheet" href="<%=ctxPath %>/libo/bootstrap/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="<%=ctxPath %>/libo/bootstrap/css/bootstrap-responsive.min.css" />
	<!-- toastr -->
	<link type="text/css" rel="stylesheet" href="<%=ctxPath %>/libo/toastr/toastr.min.css" />
	
	<!-- plugin -->
	<link type="text/css" rel="stylesheet" href="<%=ctxPath %>/plugin/css/bootstrap-overrides.css" />
	<link type="text/css" rel="stylesheet" href="<%=ctxPath %>/plugin/css/jquery-ui-1.10.2.custom.css" />
	<link type="text/css" rel="stylesheet" href="<%=ctxPath %>/plugin/css/layout.css" />
    <link type="text/css" rel="stylesheet" href="<%=ctxPath %>/plugin/css/elements.css" />
    <link type="text/css" rel="stylesheet" href="<%=ctxPath %>/plugin/css/icons.css" />
    <link type="text/css" rel="stylesheet" href="<%=ctxPath %>/plugin/css/index.css" />
	
	
	
	<!-- js -->
    <!-- open source lib -->
    <script type="text/javascript" src="<%=ctxPath %>/libo/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=ctxPath %>/libo/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=ctxPath %>/libo/toastr/toastr.min.js"></script>
    
	<!-- my lib -->
    <script type="text/javascript" src="<%=ctxPath %>/libi/js/FormUtil.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/libi/js/Message.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/libi/js/AjaxUtil.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/libi/js/SelfUI.js"></script>
    
    <!-- plugin -->
    <script type="text/javascript" src="<%=ctxPath %>/plugin/js/jquery-ui-1.10.2.custom.min.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/plugin/js/jquery.knob.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/plugin/js/jquery.flot.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/plugin/js/jquery.flot.stack.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/plugin/js/jquery.flot.resize.js"></script>
    <script type="text/javascript" src="<%=ctxPath %>/plugin/js/theme.js"></script>
    
    
    
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
