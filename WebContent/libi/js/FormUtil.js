/**
 * 表单工具
 */

/**
 * 非空验证
 */
function emptyValidate(containId) {
	var emptyCount = 0;
	$("#" + containId).children("[required='required']").each(function() {
		var v = $(this).val();
		console.log("v:" + v);
		if (v == null || v == "") {
			emptyCount++;
			var element = $(this);
			promptStyle(element);
		}
	});
	console.log(emptyCount);
	if (emptyCount == 0) {
		return true;
	} else {
		return false;
	}
}

/**
 * 让控件边框变红,并且失去焦点
 * 
 * @param element
 */
function promptStyle(element) {
	$(element).css("border-color", "red");
	$(element).unbind("focusin");
	$(element).bind("focusin", function() {
		$(element).css("border-color", "#ccc");
	});
}

/**
 * 表单Ajax请求
 * 
 * @param formId
 * @param callUrl
 * @param backFunc
 */
function formAjax(formId, backFunc) {
	if (!emptyValidate(formId)) {
		warn("请完善表格");
		return;
	}
	var form = $("#" + formId);
	var params = form.serialize();
	var action = form.attr("action");
	simpleAjax(params, action, backFunc);
}

function clearForm(formId) {
	$(':input', '#' + formId).not(':button, :submit, :reset, :hidden').val('')
			.removeAttr('checked').removeAttr('selected');
}

/**
 * 弹出层Ajax
 * @param data
 */
function modalAjax(formId) {
	formAjax(formId, modalBack);
}

function modalBack(data) {
	gridDftBack(data);
	clearForm('save');
	if(data.isOk){
		$(".modal").modal('hide');
	}
}
