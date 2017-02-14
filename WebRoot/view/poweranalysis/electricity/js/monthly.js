/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var DEFAULT_INTERVAL = 11;

    var _nodes = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

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
                            var item = TimeUtils.dataBoxMonthToDate(param.time);
                            item.setMonth(item.getMonth() + i);
                            paramChart.time.push(item.format('yyyyMM') + "01000000");
                        }

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/monthly/interval/month/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var item = ChartUtils.getElectricityMonthlyIntervalMonthSeries("本期", paramNode, param.time, param.interval, r.data);
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
                            var item = TimeUtils.dataBoxMonthToDate(param.time);
                            item.setMonth(item.getMonth() + i);
                            item.setFullYear(item.getFullYear() - 1);
                            paramChart.time.push(item.format('yyyyMM') + "01000000");
                        }

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/monthly/interval/month/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var item = ChartUtils.getElectricityMonthlyIntervalMonthSeries("去年同期", paramNode, param.time, param.interval, r.data);
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

                        config.xAxis.categories = ChartUtils.getMonthlyIntervalMonthCategories(param.time, param.interval);
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
                            var item = TimeUtils.dataBoxMonthToDate(param.time);
                            item.setMonth(item.getMonth() + i);
                            paramChart.time.push(item.format('yyyyMM') + "01000000");
                        }

                        var currentData = [];

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/monthly/interval/month/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        currentData = ChartUtils.getElectricityMonthlyIntervalMonthTable(paramNode, param.time, param.interval, r.data);

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
                            var item = TimeUtils.dataBoxMonthToDate(param.time);
                            item.setMonth(item.getMonth() + i);
                            item.setFullYear(item.getFullYear() - 1);
                            paramChart.time.push(item.format('yyyyMM') + "01000000");
                        }

                        var lastYearData = [];

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/monthly/interval/month/chart.do",
                            type: "POST",
                            cache: false,
                            async: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        lastYearData = ChartUtils.getElectricityMonthlyIntervalMonthTable(paramNode, param.time, param.interval, r.data);

                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });

                        var dgData = [];

                        var currentDataTotal = 0;
                        var lastYearDataTotal = 0;

                        for (var i = 0; i < (param.interval + 1); i++) {
                            currentDataTotal = currentDataTotal + currentData[i];
                            lastYearDataTotal = lastYearDataTotal + lastYearData[i];

                            var item = TimeUtils.dataBoxMonthToDate(param.time);
                            item.setMonth(item.getMonth() + i);

                            dgData.push({
                                time: item.format("yyyy-MM"),
                                currentData: currentData[i],
                                lastYearData: lastYearData[i],
                                rate2: lastYearData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastYearData[i]) * 100 ) / lastYearData[i]), 1),
                            });
                        }

                        dgData.push({
                            time: "总计",
                            currentData: currentDataTotal,
                            lastYearData: lastYearDataTotal,
                            rate2: lastYearDataTotal == 0 ? "-" : DataGridUtils.floatFormatter((((currentDataTotal - lastYearDataTotal) * 100 ) / lastYearDataTotal), 1),
                        });

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

    function init() {
        var endDate = new Date();

        var startDate = new Date();
        startDate.setMonth(startDate.getMonth() - 1);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

        var interval = getDateInterval($("#datebox-time-start").datebox("getValue"), $("#datebox-time-end").datebox("getValue"));

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


    function getDateInterval(s1, s2) {
        var ss1 = (s1 + "").split("-");
        var y1 = parseInt(ss1[0], 10);
        var m1 = parseInt(ss1[1], 10) - 1;

        var ss2 = (s2 + "").split("-");
        var y2 = parseInt(ss2[0], 10);
        var m2 = parseInt(ss2[1], 10) - 1;

        var time = parseInt((y2 * 12 + m2) - (y1 * 12 + m1));
        return time;
    }

});