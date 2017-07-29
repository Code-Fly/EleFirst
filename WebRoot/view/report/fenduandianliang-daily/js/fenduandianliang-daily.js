$(document).ready(function () {
    var _spinner = new Spinner();


    $("#datebox-time-start").datebox("calendar").calendar({
        firstDay: 1
    });

    $("#datebox-time-end").datebox("calendar").calendar({
        firstDay: 1
    });

    $("#datebox-time-start").datebox({
        required: true,
        editable: false
    });

    $("#datebox-time-end").datebox({
        required: true,
        editable: false
    });

    $("#tt2").datagrid({
        // url: _ctx + 'report/energy/list.do',
        // queryParams: {
        //     date: $("#startdate").datebox("getValue")
        // },
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
            var startDate = TimeUtils.dataBoxDateToDate($("#datebox-time-start").datebox("getValue"));
            var endDate = TimeUtils.dataBoxDateToDate($("#datebox-time-end").datebox("getValue"));
            endDate.setTime(endDate.getTime() + (24 * 60 * 60 * 1000 - 1));

            var param = $.param({
                areaId: _areaId,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss")
            });
            window.location.href = _ctx + "report/t030/daily/export.do?" + param;
        }
    });

    function loadData() {
        var startDate = TimeUtils.dataBoxDateToDate($("#datebox-time-start").datebox("getValue"));
        var endDate = TimeUtils.dataBoxDateToDate($("#datebox-time-end").datebox("getValue"));
        endDate.setTime(endDate.getTime() + (24 * 60 * 60 * 1000 - 1));

        $.ajax({
            url: _ctx + "report/t030/daily/list.do",
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
                            {field: '时段', title: '时段', width: 100, align: 'center'},
                            // {field: 'total-0', title: '总', width: 100, align: 'center'},
                            // {field: 'total-1', title: '峰', width: 100, align: 'center'},
                            // {field: 'total-2', title: '平', width: 100, align: 'center'},
                            // {field: 'total-3', title: '谷', width: 100, align: 'center'},
                            // {field: 'total-4', title: '尖', width: 100, align: 'center'},
                        ];

                        var dataColumns = DataGridUtils.getColumn(r.data, frozenColumns);

                        var headerColumns = [];
                        for (var i = 0; i < dataColumns.length; i++) {

                            if (i % 5 == 0) {
                                headerColumns.push(
                                    {title: (dataColumns[i].title + "").split("|")[0], align: 'center', colspan: 5}
                                );
                            }

                            dataColumns[i].title = (dataColumns[i].title + "").split("|")[1]
                        }

                        console.log(JSON.stringify([
                            headerColumns,
                            dataColumns
                        ]))

                        $("#tt2").datagrid({
                            url: _ctx + "report/t030/daily/list.do",
                            queryParams: {
                                areaId: _areaId,
                                startTime: startDate.format("yyyyMMddhhmmss"),
                                endTime: endDate.format("yyyyMMddhhmmss"),
                            },
                            frozenColumns: [
                                frozenColumns
                            ],
                            columns: [
                                headerColumns,
                                dataColumns
                            ]
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

    init();

    function init() {
        var endDate = new Date();

        var startDate = new Date();
        startDate.setDate(startDate.getDate() - 30);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

        setTimeout(function () {
            loadData();
        }, 500);
    }

    function getDateInterval(s1, s2) {
        var ss1 = (s1 + "").split("-");
        var y1 = parseInt(ss1[0], 10);
        var m1 = parseInt(ss1[1], 10) - 1;
        var d1 = parseInt(ss1[2], 10);

        var start = new Date(y1, m1, d1);

        var ss2 = (s2 + "").split("-");
        var y2 = parseInt(ss2[0], 10);
        var m2 = parseInt(ss2[1], 10) - 1;
        var d2 = parseInt(ss2[2], 10);

        var end = new Date(y2, m2, d2);

        var days = end.getTime() - start.getTime();
        var time = parseInt(days / (1000 * 60 * 60 * 24));
        return time
    }
});

