/**
 * 全局设置
 */

/**
 * 通用UI设置
 */
function commonUI() {
	$("[rel=tooltip]").tooltip();
	$('.demo-cancel-click').click(function() {
		return false;
	});
}