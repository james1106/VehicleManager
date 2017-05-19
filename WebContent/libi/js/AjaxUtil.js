/**
 * AjaxUtil
 */
// 显示loading
function showLoading() {
	var divHtml = "<div id='LOADING' style='position:absolute;left:50%;top:50%;margin:-100px 0 0 -150px;display:block;width:300px;height:200px;z-index:2;' align=center><IMG id='loadingPic' src='libi/img/loading.gif' width=50 height=50><div class='loadingText'>正在载入...</div></div>"
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
			backFunc(dataMap);
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

function AJAXRequest() {
	var xmlObj = false;
	var CBfunc, ObjSelf;
	ObjSelf = this;
	try {
		xmlObj = new XMLHttpRequest;
	} catch (e) {
		try {
			xmlObj = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (e2) {
			try {
				xmlObj = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e3) {
				xmlObj = false;
			}
		}
	}
	if (!xmlObj)
		return false;
	this.method = "POST";
	this.url;
	this.async = true;
	this.content = "";
	this.callback = function(cbobj) {
		return;
	}
	this.send = function() {
		if (!this.method || !this.url || !this.async)
			return false;
		xmlObj.open(this.method, this.url, this.async);
		if (this.method == "POST")
			xmlObj.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
		xmlObj.onreadystatechange = function() {
			if (xmlObj.readyState == 4) {
				if (xmlObj.status == 200) {
					ObjSelf.callback(xmlObj);
				}
			}
		}
		if (this.method == "POST")
			xmlObj.send(this.content);
		else
			xmlObj.send(null);
	}
}