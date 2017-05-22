/**
 * 操作Grid
 */

var mmg;

var cols;

function searchPage() {
	var searchForm = $(".search-condition");
	mmg = $('.mmg').mmGrid({
		height : 450,
		cols : cols,
		params : function() {
			var formData = searchForm.serializeJSON();
			return formData;
		},
		url : searchForm.attr("action"),
		method : searchForm.attr("method"),
		remoteSort : true,
		// sortName : 'SECUCODE',
		// sortStatus : 'asc',
		multiSelect : true,
		checkCol : true,
		fullWidthRows : true,
		autoLoad : false,
		plugins : [ $('#pg').mmPaginator({}) ]
	});

	mmg.on('cellSelected', function(e, item, rowIndex, colIndex) {
		// 查看
		if ($(e.target).is('.btn-info, .btnPrice')) {
			e.stopPropagation(); // 阻止事件冒泡
			alert(JSON.stringify(item));
		} else if ($(e.target).is('.btn-danger') && confirm('你确定要删除该行记录吗?')) {
			e.stopPropagation(); // 阻止事件冒泡
			mmg.removeRow(rowIndex);
		}
	}).on('loadSuccess', function(e, data) {
		// 这个事件需要在数据加载之前注册才能触发
	}).on('rowInserted', function(e, item, index) {
	}).on('rowUpdated', function(e, oldItem, newItem, index) {
	}).on('rowRemoved', function(e, item, index) {
	}).load();

	/*
	 * $('#btnAdd').on('click', function() { mmg.deselect('all');
	 * mmg.addRow(item1, 2); mmg.select(2); });
	 * 
	 * $('#btnAddArr').on('click', function() { mmg.deselect('all');
	 * mmg.addRow([ item1, item2 ], 1); mmg.select(function(item, index) { if
	 * (index === 1 || index === 2) { return true; } }); });
	 * 
	 * $('#btnRemove').on('click', function() { mmg.removeRow(1); });
	 * $('#btnRemoveAll').on('click', function() { mmg.removeRow(); });
	 * 
	 * $('#btnRemoveSelected').on('click', function() { var selectedIndexes =
	 * mmg.selectedRowsIndex(); mmg.removeRow(selectedIndexes); });
	 * 
	 * $('#btnUpdate').on('click', function() { var updateItem = mmg.row(1); if
	 * (updateItem) { mmg.updateRow(item1, 1); }
	 * 
	 * });
	 * 
	 * $('#btnSearch').on('click', function() { // 点击查询时页码置为1 mmg.load({ page :
	 * 1 }); });
	 */

}

/**
 * 操作成功之后,刷新页面
 * 
 * @param data
 */
function gridDftBack(data) {
	if (data.isOk) {
		searchPage();
		success(data.msg);
	} else {
		error(data.msg);
	}
}
