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
            window.location.href = _ctx + "report/t030/daily//export2.do?" + param;
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
        var endDate = TimeUtils.dataBoxDateToDate($("#datebox-time-end").datebox("getValue"));
        endDate.setTime(endDate.getTime() + (24 * 60 * 60 * 1000 - 1));

        var pnId = $("#combo-pn").combobox("getValue");
        var pns = $("#combo-pn").combobox("getData");
        var pn = _.find(pns, function (o) {
            return o.id == pnId;
        });


        $("#tt2").datagrid({
            url: _ctx + "report/t030/daily/list2.do",
            queryParams: {
                areaId: _areaId,
                concentratorId: pn.concentratorId,
                pn: pn.pn,
                startTime: startDate.format("yyyyMMddhhmmss"),
                endTime: endDate.format("yyyyMMddhhmmss"),
            },
        });

        $.ajax({
            url: _ctx + "report/t030/daily/statistic2.do",
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
                        $("#total > div").text(r.data.total == null ? "-" : r.data.total);
                        $("#rate1 > div").text(r.data.rate1 == null ? "-" : r.data.rate1);
                        $("#rate1_rate > div").text(r.data.rate1_rate == null ? "-" : r.data.rate1_rate);
                        $("#rate2 > div").text(r.data.rate2 == null ? "-" : r.data.rate2);
                        $("#rate2_rate > div").text(r.data.rate2_rate == null ? "-" : r.data.rate2_rate);
                        $("#rate3 > div").text(r.data.rate3 == null ? "-" : r.data.rate3);
                        $("#rate3_rate > div").text(r.data.rate3_rate == null ? "-" : r.data.rate3_rate);
                        $("#rate4 > div").text(r.data.rate4 == null ? "-" : r.data.rate4);
                        $("#rate4_rate > div").text(r.data.rate4_rate == null ? "-" : r.data.rate4_rate);


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

