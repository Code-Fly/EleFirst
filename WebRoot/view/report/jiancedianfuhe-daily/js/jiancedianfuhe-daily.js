$(document).ready(function () {
    var _spinner = new Spinner();


    $("#datebox-time").datebox("calendar").calendar({
        firstDay: 1
    });


    $("#datebox-time").datebox({
        required: true,
        editable: false
    });


    $("#tt2").datagrid({
        rownumbers: true,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        border: false,
        // url: _ctx + 'report/display/daily/list.do',
        // queryParams: {
        //     date: $("#startdate").datebox("getValue"),
        //     areaId: _areaId
        // },
    });

    $("#btn-detail-search").linkbutton({
        onClick: function () {
            if (!$("#datebox-time").datebox("isValid")) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }

            loadData();
        }
    });

    $("#btn-detail-export").linkbutton({
        onClick: function () {
            var startDate = TimeUtils.dataBoxDateToDate($("#datebox-time").datebox("getValue"));
            var endDate = TimeUtils.dataBoxDateToDate($("#datebox-time").datebox("getValue"));
            endDate.setTime(endDate.getTime() + (24 * 60 * 60 * 1000 - 1));

            var param = $.param({
                areaId: _areaId,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss")
            });
            window.location.href = _ctx + "report/t003/daily/export.do?" + param;
        }
    });

    init();

    function init() {
        var startDate = new Date();

        $("#datebox-time").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        loadData();
    }

    function loadData() {
        var startDate = TimeUtils.dataBoxDateToDate($("#datebox-time").datebox("getValue"));
        var endDate = TimeUtils.dataBoxDateToDate($("#datebox-time").datebox("getValue"));
        endDate.setTime(endDate.getTime() + (24 * 60 * 60 * 1000 - 1));

        $("#tt2").datagrid({
            url: _ctx + "report/t003/daily/list.do",
            queryParams: {
                areaId: _areaId,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss"),
            },
        });
    }


    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

