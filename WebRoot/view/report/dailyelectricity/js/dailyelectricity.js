$(document).ready(function () {
    
    $("#tt2").datagrid({
        url: _ctx + 'report/electricity/daily/list.do',
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        fitColumns: true,
        loadMsg: "正在加载...",
        onLoadError: function () {
            jError("查询日电量信息失败", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
        }
    });
    
    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

