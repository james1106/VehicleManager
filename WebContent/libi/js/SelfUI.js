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
	$(".submenu")
			.children()
			.each(
					function() {
						$(this)
								.bind(
										"click",
										function() {
											$("#dashboard-menu #pointer")
													.remove();
											var pointerHtml = "<div id='pointer' class='pointer'><div class='arrow'></div><div class='arrow_border'></div></div>";
											$(this).parent().parent().prepend(
													pointerHtml);
										});
					});
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
