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
 * @param element
 */
function promptStyle(element) {
	$(element).css("border-color", "red");
	$(element).unbind("focusin");
	$(element).bind("focusin", function() {
		$(element).css("border-color", "#ccc");
	});
}
