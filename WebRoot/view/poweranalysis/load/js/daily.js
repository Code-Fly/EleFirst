/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var _spinner = new Spinner();

    var DEFAULT_INTERVAL = 6;

    var _nodes = $.parseJSON($.base64.atob(decodeURIComponent(GetQueryString("data")), true));

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

    $("#dg-table").datagrid({
        singleSelect: true,
        rownumbers: true,
        fitColumns: true,
        columns: [[
            {
                field: "clientoperationtime",
                title: "日期",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var y = value.substr(0, 4);
                    var m = value.substr(4, 2);
                    var d = value.substr(6, 2);

                    return y + "-" + m + "-" + d;
                }
            },
            {
                field: "maxTotalActivePower",
                title: "最大负荷(kW)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(value);
                    var pt = row.pt;
                    var ct = row.ct;
                    t = t * pt * ct;
                    t = DataGridUtils.floatFormatter(t, 3);
                    return t;
                }
            },
            {
                field: "minTotalActivePower",
                title: "最小负荷(kW)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(value);
                    var pt = row.pt;
                    var ct = row.ct;
                    t = t * pt * ct;
                    t = DataGridUtils.floatFormatter(t, 3);
                    return t;
                }
            },
            {
                field: "avgTotalActivePower",
                title: "平均负荷(kW)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(value);
                    var pt = row.pt;
                    var ct = row.ct;
                    t = t * pt * ct;
                    t = DataGridUtils.floatFormatter(t, 3);
                    return t;
                }
            },
            {
                field: "differ",
                title: "峰谷差(kW)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(row.maxTotalActivePower) - parseFloat(row.minTotalActivePower);
                    var pt = row.pt;
                    var ct = row.ct;
                    t = t * pt * ct;
                    t = DataGridUtils.floatFormatter(t, 3);
                    return t;
                }
            },
            {
                field: "rate",
                title: "负荷率(%)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(row.avgTotalActivePower) / parseFloat(row.maxTotalActivePower);
                    t = t * 100;
                    t = DataGridUtils.floatFormatter(t, 1);
                    if (parseFloat(row.maxTotalActivePower) == 0) {
                        t = "-";
                    }
                    return t;
                }
            }
        ]]
    });


    $("#btn-search").linkbutton({
        onClick: function () {
            var interval = getDateInterval($("#datebox-time-start").datebox("getValue"), $("#datebox-time-end").datebox("getValue"));

            if (interval < 0) {
                $.messager.alert("操作提示", "请选择正确起止时间！", "info");
                return;
            }

            // if (interval > DEFAULT_INTERVAL) {
            //     $.messager.alert("操作提示", "最大间隔为 " + (DEFAULT_INTERVAL + 1) + " 天！", "info");
            //     return;
            // }

            getLoadDetailChart({
                nodes: _nodes,
                time: $("#datebox-time-start").datebox("getValue"),
                interval: interval
            });

            getLoadDetailTable({
                nodes: _nodes,
                time: $("#datebox-time-start").datebox("getValue"),
                interval: interval
            });
        }
    });

    init();

    function getLoadDetailChart(row) {
        var pnList = getPnDetail(row.nodes);


        var time = new Date().format("yyyy-MM-dd");
        var interval = 6;
        if (row.time != null && row.time != "") {
            time = row.time;
            interval = row.interval;
        }

        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;
        var d = parseInt(ss[2], 10);

        var startDate = new Date(y, m, d);
        var endDate = new Date(y, m, d);
        endDate.setDate(endDate.getDate() + interval + 1);

        var startTime = startDate.format('yyyyMMdd') + "000000";
        var endTime = endDate.format('yyyyMMdd') + "000000";

        $.ajax({
            url: _ctx + "power/data/f25/frozen/minute/node/sum.do",
            type: "POST",
            cache: false,
            data: {
                node: JSON.stringify(pnList),
                startTime: startTime,
                endTime: endTime
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        // $("#dg-table").datagrid("loadData", getDgData(r.data, pnList));

                        // getTbData(getDgData(r.data, pnList));

                        var series = [];

                        var item = ChartUtils.getLoadAllSeries({
                            name: "最大"
                        }, r.data);
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getLoadAllSeries({
                            name: "最小"
                        }, r.data);
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getLoadAllSeries({
                            name: "平均"
                        }, r.data);
                        item.dataGrouping = {
                            approximation: "average",
                            forced: true
                        };
                        series.push(item);

                        var config = new ChartConfig("view/chart/spline-date-all-load.json");

                        config
                            .setShared(true)
                            .setZoom(false)
                            .setSeries(series)
                            .setDataGroupingByDay();

                        $("#chart-load").highcharts("StockChart", config.getConfig());
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {
                _spinner.load();
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {
                _spinner.unload();
            }
        });
    }

    function getLoadDetailTable(row) {
        var pnList = getPnDetail(row.nodes);

        var time = new Date().format("yyyy-MM-dd");
        var interval = 6;
        if (row.time != null && row.time != "") {
            time = row.time;
            interval = row.interval;
        }

        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;
        var d = parseInt(ss[2], 10);

        var startDate = new Date(y, m, d);
        var endDate = new Date(y, m, d);
        endDate.setDate(endDate.getDate() + interval + 1);

        var startTime = startDate.format('yyyyMMdd') + "000000";
        var endTime = endDate.format('yyyyMMdd') + "000000";

        $.ajax({
            url: _ctx + "power/data/f25/frozen/minute/load/activepower/total/aggregation.do",
            type: "POST",
            cache: false,
            data: {
                node: JSON.stringify(pnList),
                startTime: startTime,
                endTime: endTime
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var item = r.data;

                        if (null != item.maxTotalActivePower) {
                            $("#maxTotalActivePower").text(item.maxTotalActivePower + "(kW)");
                        } else {
                            $("#maxTotalActivePower").text("--");
                        }
                        if (null != item.minTotalActivePower) {
                            $("#minTotalActivePower").text(item.minTotalActivePower + "(kW)");
                        } else {
                            $("#minTotalActivePower").text("--");
                        }
                        if (null != item.avgTotalActivePower) {
                            $("#avgTotalActivePower").text(item.avgTotalActivePower + "(kW)");
                        } else {
                            $("#avgTotalActivePower").text("--");
                        }
                        if (null != item.maxTotalActivePowerTime) {
                            $("#maxTotalActivePowerTime").text(TimeUtils.dbTimeToDate(item.maxTotalActivePowerTime).format("MM-dd"));
                        } else {
                            $("#maxTotalActivePowerTime").text("--");
                        }
                        if (null != item.minTotalActivePowerTime) {
                            $("#minTotalActivePowerTime").text(TimeUtils.dbTimeToDate(item.minTotalActivePowerTime).format("MM-dd"));
                        } else {
                            $("#minTotalActivePowerTime").text("--");
                        }
                        if (null != item.differ) {
                            $("#differ").text(item.differ + "(kW)");
                        } else {
                            $("#differ").text("--");
                        }
                        if (null != item.differRate) {
                            $("#differRate").text(item.differRate + "(%)");
                        } else {
                            $("#differRate").text("--");
                        }
                        if (null != item.loadRate) {
                            $("#loadRate").text(item.loadRate + "(%)");
                        } else {
                            $("#loadRate").text("--");
                        }


                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {
                _spinner.load();
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {
                _spinner.unload();
            }
        });
    }

    function getPnDetail(nodes) {
        var pnInfo = $.parseJSON($.ajax({
            url: _ctx + "system/pn/info/list.do",
            type: "POST",
            data: {
                node: JSON.stringify(nodes)
            },
            async: false
        }).responseText);

        if ("0" == pnInfo.errcode) {
            return pnInfo.data;
        } else {
            jError("请求失败！" + ErrUtils.getMsg(pnInfo.errcode));
            return null;
        }
    }

    function init() {
        var endDate = new Date();

        var startDate = new Date();
        startDate.setDate(startDate.getDate() - 6);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

        getLoadDetailChart({
            nodes: _nodes,
            time: $("#datebox-time-start").datebox("getValue"),
            interval: DEFAULT_INTERVAL
        });

        getLoadDetailTable({
            nodes: _nodes,
            time: $("#datebox-time-start").datebox("getValue"),
            interval: DEFAULT_INTERVAL
        });
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