/**
 * 消息弹出js
 */

/**
 * 制作消息框样式<br>
 * 位置代码:<br>
 * toast-top-left 顶端左边<br>
 * toast-top-right 顶端右边<br>
 * toast-top-center 顶端中间<br>
 * toast-top-full-width 顶端，宽度铺满整个屏幕<br>
 * toast-botton-right <br>
 * toast-bottom-left <br>
 * toast-bottom-center <br>
 * toast-bottom-full-width <br>
 */
function declareToast() {
	toastr.options = {
		"closeButton" : false, // 是否显示关闭按钮
		"debug" : false, // 是否使用debug模式
		"positionClass" : "toast-top-center",
		"showDuration" : "300",// 显示的动画时间
		"hideDuration" : "1000",// 消失的动画时间
		"timeOut" : "1000", // 展现时间
		"extendedTimeOut" : "1000",// 加长展示时间
		"showEasing" : "swing",// 显示时的动画缓冲方式
		"hideEasing" : "linear",// 消失时的动画缓冲方式
		"showMethod" : "fadeIn",// 显示时的动画方式
		"hideMethod" : "fadeOut" // 消失时的动画方式
	};
}

/**
 * 成功提示
 * 
 * @param msg
 */
function success(msg) {
	toastr.success(msg);
}

/**
 * 信息
 * 
 * @param msg
 */
function info(msg) {
	toastr.info(msg);
}

/**
 * 警告
 * 
 * @param msg
 */
function warn(msg) {
	toastr.warning(msg);
}

/**
 * 错误提示
 * 
 * @param msg
 */
function error(msg) {
	toastr.error(msg);
}
