/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var DEFAULT_INTERVAL = 30;

    var _nodes = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

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

    $("#btn-search").linkbutton({
        onClick: function () {
            var interval = getDateInterval($("#datebox-time-start").datebox("getValue"), $("#datebox-time-end").datebox("getValue"));

            if (interval < 0) {
                $.messager.alert("操作提示", "请选择正确起止时间！", "info");
                return;
            }

            if (interval > DEFAULT_INTERVAL) {
                $.messager.alert("操作提示", "最大间隔为 " + (DEFAULT_INTERVAL + 1) + " 天！", "info");
                return;
            }

            getElectricityDetailChart({
                node: _nodes,
                time: $("#datebox-time-start").datebox("getValue"),
                interval: interval
            });

            getElectricityDetailTable({
                node: _nodes,
                time: $("#datebox-time-start").datebox("getValue"),
                interval: interval
            });

        }
    });

    init();

    function getElectricityDetailChart(param) {
        $.ajax({
            url: _ctx + "system/pn/info/list.do",
            type: "POST",
            cache: false,
            data: {
                node: param.node
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var series = [];

                        var paramNode = r.data;
                        var paramChart = {
                            node: paramNode,
                            time: []
                        };

                        for (var i = 0; i <= param.interval; i++) {
                            var item = TimeUtils.dataBoxDateToDate(param.time);
                            item.setDate(item.getDate() + i);
                            paramChart.time.push(item.format('yyyyMMdd') + "000000");
                        }

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/daily/interval/day/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var item = ChartUtils.getElectricityDailyIntervalDaySeries("本期", paramNode, param.time, param.interval, r.data);
                                        series.push(item);

                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });


                        paramChart.time = [];
                        for (var i = 0; i <= param.interval; i++) {
                            var item = TimeUtils.dataBoxDateToDate(param.time);
                            item.setDate(item.getDate() + i);
                            item.setMonth(item.getMonth() - 1);
                            paramChart.time.push(item.format('yyyyMMdd') + "000000");
                        }

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/daily/interval/day/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var item = ChartUtils.getElectricityDailyIntervalDaySeries("上月同期", paramNode, param.time, param.interval, r.data);
                                        series.push(item);

                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });

                        paramChart.time = [];
                        for (var i = 0; i <= param.interval; i++) {
                            var item = TimeUtils.dataBoxDateToDate(param.time);
                            item.setDate(item.getDate() + i);
                            item.setFullYear(item.getFullYear() - 1);
                            paramChart.time.push(item.format('yyyyMMdd') + "000000");
                        }

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/daily/interval/day/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var item = ChartUtils.getElectricityDailyIntervalDaySeries("去年同期", paramNode, param.time, param.interval, r.data);
                                        series.push(item);

                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });

                        var config = $.parseJSON($.ajax({
                            url: "data/electricityDetailChart.json?bust=" + new Date().getTime(),
                            type: "GET",
                            async: false
                        }).responseText);

                        config.xAxis.categories = ChartUtils.getDailyIntervalDayCategories(param.time, param.interval);
                        config.series = series;

                        $("#chart-electricity-detail").highcharts(config);

                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                MaskUtil.mask();
            },
            error: function (request) {
                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
            },
            complete: function (XMLHttpRequest, textStatus) {
                MaskUtil.unmask();
            }
        });
    }

    function getElectricityDetailTable(param) {
        $.ajax({
            url: _ctx + "system/pn/info/list.do",
            type: "POST",
            cache: false,
            data: {
                node: param.node
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {

                        var paramNode = r.data;
                        var paramChart = {
                            node: paramNode,
                            time: []
                        };

                        for (var i = 0; i <= param.interval; i++) {
                            var item = TimeUtils.dataBoxDateToDate(param.time);
                            item.setDate(item.getDate() + i);
                            paramChart.time.push(item.format('yyyyMMdd') + "000000");
                        }

                        var currentData = [];

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/daily/interval/day/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        currentData = ChartUtils.getElectricityDailyIntervalDayTable(paramNode, param.time, param.interval, r.data);

                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });


                        paramChart.time = [];
                        for (var i = 0; i <= param.interval; i++) {
                            var item = TimeUtils.dataBoxDateToDate(param.time);
                            item.setDate(item.getDate() + i);
                            item.setMonth(item.getMonth() - 1);
                            paramChart.time.push(item.format('yyyyMMdd') + "000000");
                        }

                        var lastMonthData = [];

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/daily/interval/day/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        lastMonthData = ChartUtils.getElectricityDailyIntervalDayTable(paramNode, param.time, param.interval, r.data);

                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });

                        paramChart.time = [];
                        for (var i = 0; i <= param.interval; i++) {
                            var item = TimeUtils.dataBoxDateToDate(param.time);
                            item.setDate(item.getDate() + i);
                            item.setFullYear(item.getFullYear() - 1);
                            paramChart.time.push(item.format('yyyyMMdd') + "000000");
                        }

                        var lastYearData = [];

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/daily/interval/day/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        lastYearData = ChartUtils.getElectricityDailyIntervalDayTable(paramNode, param.time, param.interval, r.data);

                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });

                        var dgData = [];

                        for (var i = 0; i < (param.interval + 1); i++) {
                            var item = TimeUtils.dataBoxDateToDate(param.time);
                            item.setDate(item.getDate() + i);

                            dgData.push({
                                time: item.format("yyyy-MM-dd"),
                                currentData: currentData[i],
                                lastMonthData: lastMonthData[i],
                                lastYearData: lastYearData[i],
                                rate1: lastMonthData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastMonthData[i]) * 100) / lastYearData[i]), 1),
                                rate2: lastYearData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastYearData[i]) * 100 ) / lastYearData[i]), 1),
                            });
                        }

                        $("#dg-table").datagrid("loadData", dgData);

                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                MaskUtil.mask();
            },
            error: function (request) {
                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
            },
            complete: function (XMLHttpRequest, textStatus) {
                MaskUtil.unmask();
            }
        });
    }

    function getPnInfo(areaId, concentratorId, pn, data) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].areaId == areaId && data[i].concentratorId == concentratorId && data[i].pn == pn) {
                return data[i];
            }
        }
        return null;
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

    function init() {
        var endDate = new Date();

        var startDate = new Date();
        startDate.setDate(startDate.getDate() - DEFAULT_INTERVAL);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

        getElectricityDetailChart({
            node: _nodes,
            time: $("#datebox-time-start").datebox("getValue"),
            interval: DEFAULT_INTERVAL
        });

        getElectricityDetailTable({
            node: _nodes,
            time: $("#datebox-time-start").datebox("getValue"),
            interval: DEFAULT_INTERVAL
        });
    }
});