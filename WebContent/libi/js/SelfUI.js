/**
 * UI设置
 */

/**
 * 通用UI设置
 */
function commonUI() {
	$(".knob").knob();
	// jQuery UI Sliders
	$(".slider-sample1").slider({
		value : 100,
		min : 1,
		max : 500
	});
	$(".slider-sample2").slider({
		range : "min",
		value : 130,
		min : 1,
		max : 500
	});
	$(".slider-sample3").slider({
		range : true,
		min : 0,
		max : 500,
		values : [ 40, 170 ],
	});
	var previousPoint = null;
	$("#statsChart")
			.bind(
					"plothover",
					function(event, pos, item) {
						if (item) {
							if (previousPoint != item.dataIndex) {
								previousPoint = item.dataIndex;

								$("#tooltip").remove();
								var x = item.datapoint[0].toFixed(0), y = item.datapoint[1]
										.toFixed(0);

								var month = item.series.xaxis.ticks[item.dataIndex].label;

								showTooltip(item.pageX, item.pageY,
										item.series.label + " of " + month
												+ ": " + y);
							}
						} else {
							$("#tooltip").remove();
							previousPoint = null;
						}
					});
	declareToast();
	// 二级菜单绑定点击事件
	$(".submenu").children().each(function() {
		$(this).bind("click", pointMe());
	});
}

window.Layers = function() {
	var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
	var alr = $("<div id='layers' class='modal'><div class='modal-dialog modal-sm'><div class='modal-content'><div class='modal-header'><button type='button' class='close' data-dismiss='modal'><span aria-hidden='true'>×</span><span class='sr-only'>Close</span></button> <h5 class='modal-title'><i class='fa fa-exclamation-circle'></i> [Title]</h5></div><div class='modal-body small'><p>[Message]</p></div><div class='modal-footer' ><button type='button' class='btn btn-primary ok' data-dismiss='modal'>[BtnOk]</button><button type='button' class='btn btn-default cancel' data-dismiss='modal'>[BtnCancel]</button></div></div></div></div>");
	var ahtml = alr.html();

	// 关闭时恢复 modal html 原样，供下次调用时 replace 用
	// var _init = function () {
	// alr.on("hidden.bs.modal", function (e) {
	// $(this).html(ahtml);
	// });
	// }();

	/* html 复原不在 _init() 里面做了，重复调用时会有问题，直接在 _alert/_confirm 里面做 */

	var _alert = function(options) {
		alr.html(ahtml); // 复原
		alr.find('.ok').removeClass('btn-success').addClass('btn-primary');
		alr.find('.cancel').hide();
		_dialog(options);

		return {
			on : function(callback) {
				if (callback && callback instanceof Function) {
					alr.find('.ok').click(function() {
						callback(true)
					});
				}
			}
		};
	};

	var _confirm = function(options) {
		alr.html(ahtml); // 复原
		alr.find('.ok').removeClass('btn-primary').addClass('btn-success');
		alr.find('.cancel').show();
		_dialog(options);

		return {
			on : function(callback) {
				if (callback && callback instanceof Function) {
					alr.find('.ok').click(function() {
						callback(true);
					});
					alr.find('.cancel').click(function() {
						return;
					});
				}
			}
		};
	};

	var _dialog = function(options) {
		var ops = {
			msg : "提示内容",
			title : "操作提示",
			btnok : "确定",
			btncl : "取消"
		};

		$.extend(ops, options);

		console.log(alr);

		var html = alr.html().replace(reg, function(node, key) {
			return {
				Title : ops.title,
				Message : ops.msg,
				BtnOk : ops.btnok,
				BtnCancel : ops.btncl
			}[key];
		});

		alr.html(html);
		alr.modal({
			width : 500,
			backdrop : 'static'
		});
	}

	return {
		alert : _alert,
		confirm : _confirm
	}

}();

function pointMe() {
	$("#dashboard-menu #pointer").remove();
	var pointerHtml = "<div id='pointer' class='pointer'><div class='arrow'></div><div class='arrow_border'></div></div>";
	$(this).parent().parent().prepend(pointerHtml);
}

function showTooltip(x, y, contents) {
	$('<div id="tooltip">' + contents + '</div>').css({
		position : 'absolute',
		display : 'none',
		top : y - 30,
		left : x - 50,
		color : "#fff",
		padding : '2px 5px',
		'border-radius' : '6px',
		'background-color' : '#000',
		opacity : 0.80
	}).appendTo("body").fadeIn(200);
}
