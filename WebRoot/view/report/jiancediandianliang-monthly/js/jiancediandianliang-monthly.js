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

    $("#combo-pn").combobox({
        required: true,
        textField: "name",
        valueField: "id",
        url: _ctx + "system/pn/info/list.do",
        queryParams: {
            areaId: _areaId
        },
        editable: false,
        onLoadSuccess: function () {
            var pns = $("#combo-pn").combobox("getData");
            if (pns.length > 0) {
                $("#combo-pn").combobox("select", pns[0].id);
                loadData();
            }
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
            startDate.setTime(startDate.getTime() - (24 * 60 * 60 * 1000));

            var endDate = TimeUtils.dataBoxDateToDate($("#datebox-time-end").datebox("getValue"));
            endDate.setTime(endDate.getTime() + (24 * 60 * 60 * 1000 - 1));

            var pnId = $("#combo-pn").combobox("getValue");
            var pns = $("#combo-pn").combobox("getData");
            var pn = _.find(pns, function (o) {
                return o.id == pnId;
            });


            var param = $.param({
                areaId: _areaId,
                concentratorId: pn.concentratorId,
                pn: pn.pn,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss")
            });
            window.location.href = _ctx + "report/t033/daily/export.do?" + param;
        }
    });

    init();

    function init() {
        var endDate = new Date();

        var startDate = new Date();
        startDate.setDate(startDate.getDate() - 30);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

    }

    function loadData() {
        var startDate = TimeUtils.dataBoxDateToDate($("#datebox-time-start").datebox("getValue"));
        startDate.setTime(startDate.getTime() - (24 * 60 * 60 * 1000));

        var endDate = TimeUtils.dataBoxDateToDate($("#datebox-time-end").datebox("getValue"));
        endDate.setTime(endDate.getTime() + (24 * 60 * 60 * 1000 - 1));

        var pnId = $("#combo-pn").combobox("getValue");
        var pns = $("#combo-pn").combobox("getData");
        var pn = _.find(pns, function (o) {
            return o.id == pnId;
        });


        $("#tt2").datagrid({
            url: _ctx + "report/t033/daily/list.do",
            queryParams: {
                areaId: _areaId,
                concentratorId: pn.concentratorId,
                pn: pn.pn,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss"),
            },
        });

        $.ajax({
            url: _ctx + "report/t033/daily/statistic.do",
            type: "POST",
            cache: false,
            data: {
                areaId: _areaId,
                concentratorId: pn.concentratorId,
                pn: pn.pn,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss"),
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        $("#dg-max > div").text(r.data.max == null ? "-" : r.data.max);
                        $("#dg-maxTime > div").text(r.data.maxTime == null ? "-" : r.data.maxTime);
                        $("#dg-min > div").text(r.data.min == null ? "-" : r.data.min);
                        $("#dg-minTime > div").text(r.data.minTime == null ? "-" : r.data.minTime);
                        $("#dg-total > div").text(r.data.total == null ? "-" : r.data.total);

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
            },
            complete: function (XMLHttpRequest, textStatus) {
            }
        });
    }


    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

