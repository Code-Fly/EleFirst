/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var _spinner = new Spinner();

    var DEFAULT_INTERVAL = 11;

    var _nodes = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    DateBoxUtils.initMonthBox($("#datebox-time-start"));

    DateBoxUtils.initMonthBox($("#datebox-time-end"));

    $("#dg-table").datagrid({
        url: _ctx + "/power/data/f33/frozen/day/electricity/positiveactivepower/total/interval/month/statistic.do",
        method: "POST",
        border: true,
        fit: true,
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {
                field: "clientOperationTime",
                title: "日期",
                align: "center",
                width: 120,
                formatter: DataGridUtils.dateToMonthFormatter
            },
            {
                field: "thisMonthTotalPositiveActivePower",
                title: "本期电量(kWh)",
                align: "center",
                width: 120,
                formatter: DataGridUtils.strFormatter
            }, {
                field: "lastYearTotalPositiveActivePower",
                title: "去年同期电量(kWh)",
                align: "center",
                width: 120,
                formatter: DataGridUtils.strFormatter
            }, {
                field: "rate2",
                title: "同比(%)",
                align: "center",
                width: 120,
                formatter: DataGridUtils.strFormatter
            }
        ]],
        // onLoadError:function () {
        //     jError("请求失败！");
        // }
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

                        var startDate = TimeUtils.dataBoxMonthToDate(param.time);
                        var endDate = TimeUtils.dataBoxMonthToDate(param.time)
                        endDate.setMonth(endDate.getMonth() + param.interval + 1);

                        var startTime = startDate.format('yyyyMM') + "01000000";
                        var endTime = endDate.format('yyyyMM') + "01000000";

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
                                            name: "本期"
                                        }, r.data);
                                        item.dataGrouping = {
                                            approximation: "sum",
                                            forced: true
                                        };
                                        series.push(item);

                                        if (series.length == 2) {
                                            var config = new ChartConfig("view/chart/column-date-all-electricity.json");
                                            config
                                                .setShared(false)
                                                .setZoom(false)
                                                .setCrossHairSnap(false)
                                                .setSeries(series)
                                                .setDataGroupingByMonth();


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

                        var startDate = TimeUtils.dataBoxMonthToDate(param.time);
                        startDate.setFullYear(startDate.getFullYear() - 1);
                        var endDate = TimeUtils.dataBoxMonthToDate(param.time);
                        endDate.setFullYear(endDate.getFullYear() - 1);
                        endDate.setMonth(endDate.getMonth() + param.interval + 1);

                        var startTime = startDate.format('yyyyMM') + "01000000";
                        var endTime = endDate.format('yyyyMM') + "01000000";

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

                                        if (series.length == 2) {
                                            var config = new ChartConfig("view/chart/column-date-all-electricity.json");
                                            config
                                                .setShared(false)
                                                .setZoom(false)
                                                .setCrossHairSnap(false)
                                                .setSeries(series)
                                                .setDataGroupingByMonth();


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

                        var item = TimeUtils.dataBoxMonthToDate(param.time);
                        var startTime = item.format('yyyyMM') + "01000000";

                        $("#dg-table").datagrid("reload", {
                            node: JSON.stringify(paramNode),
                            time: startTime,
                            interval: param.interval
                        });

                        return;

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
                                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                                    }
                                } else {
                                    jError("请求失败！" + ErrUtils.getMsg("2"));
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
                                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                                    }
                                } else {
                                    jError("请求失败！" + ErrUtils.getMsg("2"));
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