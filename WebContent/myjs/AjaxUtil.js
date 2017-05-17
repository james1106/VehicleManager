/**
 * AjaxUtil
 */
// 显示loading
function showLoading() {
	var divHtml = "<div id='LOADING' style='position:absolute;left:50%;top:50%;margin:-100px 0 0 -150px;display:block;width:300px;height:200px;z-index:2;' align=center><IMG id='loadingPic' src='myimg/loading.gif' width=50 height=50><div class='loadingText'>正在载入...</div></div>"
	$(document.body).append(divHtml);
}
// 移除loading
function removeLoading() {
	$("div#LOADING").remove();
}

/**
 * Ajax请求
 * 
 * @param params
 * @param callUrl
 * @param backFunc
 */
function simpleAjax(params, callUrl, backFunc) {
	showLoading();
	$.ajax({
		type : "POST",
		url : callUrl,
		data : params,
		datatype : "json",// "xml", "html", "script", "json", "jsonp", "text".
		async : false,
		success : function(data) {
			// var obj = eval(data);
			// var obj = eval("(" + data + ")");
			// var obj = JSON.parse(data);
			var dataMap = data.dataMap;
			if (dataMap.isOk) {
				backFunc(dataMap);
			} else {
				toastr.error(dataMap.msg);
				setTimeout(toastr.remove(), 3000);
			}
			// alert(dataObj.root.length);//输出root的子对象数量
		},
		complete : function(XMLHttpRequest, textStatus) {
			removeLoading();
		},
		error : function() {
			alert("发送ajax请求失败!");
		}
	});
}

/**
 * 表单Ajax请求
 * 
 * @param formId
 * @param callUrl
 * @param backFunc
 */
function formAjax(formId, callUrl, backFunc) {
	var params = $("#" + formId).serialize();
	simpleAjax(params, callUrl, backFunc);
}
