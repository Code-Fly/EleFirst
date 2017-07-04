$(document).ready(function () {

    $("#tt").datagrid({
        // url: _ctx + 'report/display/monthly/list.do',
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        fitColumns: true,
        loadMsg: "正在加载...",
        data: [
            {
                date: "2017-06-01",
                display: "1",
                olddisplay: "1",
                displaydiffer: "1",
                override: "1",
                power: "1"
            }
        ],
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

