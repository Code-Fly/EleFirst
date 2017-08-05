$(document).ready(function () {
    var _spinner = new Spinner();

    DateBoxUtils.initMonthBox($("#datebox-time-start"));

    DateBoxUtils.initMonthBox($("#datebox-time-end"));

    $("#datebox-time-start").datebox({
        required: true,
        editable: false
    });

    $("#datebox-time-end").datebox({
        required: true,
        editable: false
    });

    $("#numberspinner-hour").numberspinner({
        required: true,
        editable: false,
        value: 0,
        min: 0,
        max: 23,
        increment: 1
    });

    $("#numberspinner-minute").numberspinner({
        required: true,
        editable: false,
        value: 0,
        min: 0,
        max: 45,
        increment: 15
    });

    $("#tt2").datagrid({
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
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
            if (!$("#datebox-time-start").datebox("isValid")) {
                $.messager.alert("信息提示", "请选择开始时间！", "info");
                return;
            }

            if (!$("#datebox-time-end").datebox("isValid")) {
                $.messager.alert("信息提示", "请选择结束时间！", "info");
                return;
            }

            loadData();
        }
    });

    $("#btn-detail-export").linkbutton({
        onClick: function () {
            var startDate = TimeUtils.dataBoxMonthToDate($("#datebox-time-start").datebox("getValue"));
            var endDate = TimeUtils.dataBoxMonthToDate($("#datebox-time-end").datebox("getValue"));
            endDate.setMonth(endDate.getMonth() + 1);
            endDate.setSeconds(endDate.getSeconds() - 1);


            var param = $.param({
                areaId: _areaId,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss")
            });
            window.location.href = _ctx + "report/t035/daily/export.do?" + param;
        }
    });

    init();

    function init() {
        var endDate = new Date();

        var startDate = new Date();
        startDate.setMonth(startDate.getMonth() - 12);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

        setTimeout(function () {
            loadData();
        }, 500);
    }

    function loadData() {
        var startDate = TimeUtils.dataBoxMonthToDate($("#datebox-time-start").datebox("getValue"));
        var endDate = TimeUtils.dataBoxMonthToDate($("#datebox-time-end").datebox("getValue"));
        endDate.setMonth(endDate.getMonth() + 1);
        endDate.setSeconds(endDate.getSeconds() - 1);


        $.ajax({
            url: _ctx + "report/t035/daily/list.do",
            type: "POST",
            cache: false,
            data: {
                areaId: _areaId,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss"),
                page: 1,
                rows: 1
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var frozenColumns = [
                            {field: '监测点', title: '监测点', width: 100, align: 'center'},
                        ];

                        $("#tt2").datagrid({
                            url: _ctx + "report/t035/daily/list.do",
                            queryParams: {
                                areaId: _areaId,
                                startTime: startDate.format("yyyyMMddhhmmss"),
                                endTime: endDate.format("yyyyMMddhhmmss"),
                            },
                            frozenColumns: [frozenColumns],
                            columns: [DataGridUtils.getColumn(r.data, frozenColumns)]
                        });

                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            beforeSend: function (XMLHttpRequest) {
                $("#tt2").datagrid("loadData", []);
                _spinner.load();
            },
            complete: function (XMLHttpRequest, textStatus) {
                _spinner.unload();
            }
        });
    }


    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

