$(document).ready(function () {
    DateBoxUtils.initMonthBox($("#startdate"));
    //初始化databox为当前日期
    var d = new Date();
    $("#startdate").datebox("setValue", new Date().format('yyyy-MM'));
    var dgtt2 = $("#tt2").datagrid({
        url: _ctx + 'report/electricity/daily/list.do',
        queryParams: {
             date: $("#startdate").datebox("getValue"),
             areaId: _areaId
         },
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        loadMsg: "正在加载...",
        border: false,
        onLoadError: function () {
            jError("查询日电量信息失败", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
        }
    });

    $("#btn-detail-search").linkbutton({
        onClick: function () {
            if (!$("#startdate").datebox("isValid")) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }
            dgtt2.datagrid('load', {
                date: $("#startdate").datebox("getValue"),
                areaId: _areaId
            });
        }
    });

    $("#exportexcel").linkbutton({
        onClick: function () {
            window.location.href = _ctx + "view/report/dailyelectricity/report20170703204120.xls";
        }
    });

    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

