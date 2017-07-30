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
            window.location.href = _ctx + "report/t037/hourly/export.do?" + param;
        }
    });

    init();

    function init() {
        var startDate = new Date();
        startDate.setDate(startDate.getDate() - 30);

        $("#datebox-time").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());


        setTimeout(function () {
            loadData();
        }, 500);
    }

    function loadData() {
        var startDate = TimeUtils.dataBoxDateToDate($("#datebox-time").datebox("getValue"));
        var endDate = TimeUtils.dataBoxDateToDate($("#datebox-time").datebox("getValue"));
        endDate.setTime(endDate.getTime() + (24 * 60 * 60 * 1000 - 1));


        $.ajax({
            url: _ctx + "report/t037/hourly/list.do",
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

                        console.log(JSON.stringify(DataGridUtils.getColumn(r.data, frozenColumns)))

                        $("#tt2").datagrid({
                            url: _ctx + "report/t037/hourly/list.do",
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

