/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var _spinner = new Spinner();

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

            // if (interval > DEFAULT_INTERVAL) {
            //     $.messager.alert("操作提示", "最大间隔为 " + (DEFAULT_INTERVAL + 1) + " 天！", "info");
            //     return;
            // }

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


                        var startDate = TimeUtils.dataBoxDateToDate(param.time);
                        var endDate = TimeUtils.dataBoxDateToDate(param.time)
                        endDate.setDate(endDate.getDate() + param.interval + 1);

                        var startTime = startDate.format('yyyyMMdd') + "000000";
                        var endTime = endDate.format('yyyyMMdd') + "000000";

                        $.ajax({
                            url: _ctx + "power/data/f33/frozen/day/node/sum.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(paramNode),
                                startTime: startTime,
                                endTime: endTime
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var item = ChartUtils.getElectricityAllSeries({
                                            name: "本期"
                                        }, r.data);
                                        item.dataGrouping = {
                                            approximation: "sum",
                                            forced: true
                                        };
                                        series.push(item);

                                        if (series.length == 3) {
                                            var config = new ChartConfig("view/chart/column-date-all-electricity.json");
                                            config
                                                .setShared(false)
                                                .setZoom(false)
                                                .setCrossHairSnap(false)
                                                .setSeries(series)
                                                .setDataGroupingByDay();


                                            $("#chart-electricity-detail").highcharts("StockChart", config.getConfig());
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


                        var startDate = TimeUtils.dataBoxDateToDate(param.time);
                        startDate.setMonth(startDate.getMonth() - 1);
                        var endDate = TimeUtils.dataBoxDateToDate(param.time);
                        endDate.setMonth(endDate.getMonth() - 1);
                        endDate.setDate(endDate.getDate() + param.interval + 1);

                        var startTime = startDate.format('yyyyMMdd') + "000000";
                        var endTime = endDate.format('yyyyMMdd') + "000000";

                        $.ajax({
                            url: _ctx + "power/data/f33/frozen/day/node/sum.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(paramNode),
                                startTime: startTime,
                                endTime: endTime
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var item = ChartUtils.getElectricityAllSeries({
                                            name: "上月同期"
                                        }, r.data);
                                        item.dataGrouping = {
                                            approximation: "sum",
                                            forced: true
                                        };
                                        series.push(item);

                                        if (series.length == 3) {
                                            var config = new ChartConfig("view/chart/column-date-all-electricity.json");
                                            config
                                                .setShared(false)
                                                .setZoom(false)
                                                .setCrossHairSnap(false)
                                                .setSeries(series)
                                                .setDataGroupingByDay();


                                            $("#chart-electricity-detail").highcharts("StockChart", config.getConfig());
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


                        var startDate = TimeUtils.dataBoxDateToDate(param.time);
                        startDate.setFullYear(startDate.getFullYear() - 1);
                        var endDate = TimeUtils.dataBoxDateToDate(param.time);
                        endDate.setFullYear(endDate.getFullYear() - 1);
                        endDate.setDate(endDate.getDate() + param.interval + 1);

                        var startTime = startDate.format('yyyyMMdd') + "000000";
                        var endTime = endDate.format('yyyyMMdd') + "000000";

                        $.ajax({
                            url: _ctx + "power/data/f33/frozen/day/node/sum.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(paramNode),
                                startTime: startTime,
                                endTime: endTime
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var item = ChartUtils.getElectricityAllSeries({
                                            name: "去年同期"
                                        }, r.data);
                                        item.dataGrouping = {
                                            approximation: "sum",
                                            forced: true
                                        };
                                        series.push(item);

                                        if (series.length == 3) {
                                            var config = new ChartConfig("view/chart/column-date-all-electricity.json");
                                            config
                                                .setShared(false)
                                                .setZoom(false)
                                                .setCrossHairSnap(false)
                                                .setSeries(series)
                                                .setDataGroupingByDay();


                                            $("#chart-electricity-detail").highcharts("StockChart", config.getConfig());
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
                                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                                    }
                                } else {
                                    jError("请求失败！" + ErrUtils.getMsg("2"));
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
                                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                                    }
                                } else {
                                    jError("请求失败！" + ErrUtils.getMsg("2"));
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
                                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                                    }
                                } else {
                                    jError("请求失败！" + ErrUtils.getMsg("2"));
                                }
                            }
                        });

                        var dgData = [];

                        var currentDataTotal = 0;
                        var lastMonthDataTotal = 0;
                        var lastYearDataTotal = 0;

                        for (var i = 0; i < (param.interval + 1); i++) {
                            var item = TimeUtils.dataBoxDateToDate(param.time);
                            item.setDate(item.getDate() + i);

                            currentDataTotal = currentDataTotal + currentData[i];
                            lastMonthDataTotal = lastMonthDataTotal + lastMonthData[i];
                            lastYearDataTotal = lastYearDataTotal + lastYearData[i];

                            dgData.push({
                                time: item.format("yyyy-MM-dd"),
                                currentData: currentData[i],
                                lastMonthData: lastMonthData[i],
                                lastYearData: lastYearData[i],
                                rate1: lastMonthData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastMonthData[i]) * 100) / lastMonthData[i]), 1),
                                rate2: lastYearData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastYearData[i]) * 100 ) / lastYearData[i]), 1),
                            });
                        }
                        dgData.push({
                            time: "总计",
                            currentData: currentDataTotal,
                            lastMonthData: lastMonthDataTotal,
                            lastYearData: lastYearDataTotal,
                            rate1: lastYearDataTotal == 0 ? "-" : DataGridUtils.floatFormatter((((currentDataTotal - lastMonthDataTotal) * 100) / lastYearDataTotal), 1),
                            rate2: lastYearDataTotal == 0 ? "-" : DataGridUtils.floatFormatter((((currentDataTotal - lastYearDataTotal) * 100 ) / lastYearDataTotal), 1),
                        });

                        $("#dg-table").datagrid("loadData", dgData);

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
        startDate.setDate(startDate.getDate() - 6);

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
});