$(document).ready(function () {
    DateBoxUtils.initMonthBox($("#startdate"));
    //初始化databox为当前日期
    var d = new Date();
    var dateboxDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-01";
    $("#startdate").datebox("clear");
    $("#startdate").datebox("setValue", "2017-06");
    var dgtt2 = $("#tt2").datagrid({
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        loadMsg: "正在加载...",
        border: false,
        url: _ctx + 'report/display/daily/list.do',
        queryParams: {
           date: $("#startdate").datebox("getValue"),
           areaId: _areaId
        },
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
                date: $("#startdate").datebox("getValue")
            });
        }
    });

    $("#exportexcel").linkbutton({
        onClick: function () {
            window.location.href = _ctx + "view/report/dailydisplay/report20170703201737.xls";
        }
    });


    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

