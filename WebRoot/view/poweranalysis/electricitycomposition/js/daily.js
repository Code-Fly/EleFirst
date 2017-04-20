/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var _spinner = new Spinner();

    var DEFAULT_INTERVAL = 30;

    var _nodes = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    var _pnInfo = getPnListAll(_nodes);

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

            getElectricityComparisonBarChart({
                node: _nodes,
                time: $("#datebox-time-start").datebox("getValue"),
                interval: interval
            });

            getElectricityComparisonPieChart({
                node: _nodes,
                time: $("#datebox-time-start").datebox("getValue"),
                interval: interval
            });

            getElectricityComparisonTable({
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
                        var paramNode = [
                            r.data
                        ];

                        var timeList = [];
                        timeList.push({
                            startTime: TimeUtils.getDataBoxDateToDateInterval(param.time, 0, 0, 0).format('yyyyMMdd') + "000000",
                            endTime: TimeUtils.getDataBoxDateToDateInterval(param.time, 0, 0, param.interval + 1).format('yyyyMMdd') + "000000"
                        });

                        $.ajax({
                            url: _ctx + "power/data/f5/node/time/sum.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(paramNode),
                                time: JSON.stringify(timeList)
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];

                                        var item = ChartUtils.getF5AllSeries({
                                            name: "本期"
                                        }, r.data[0][0]);
                                        item.dataGrouping = {
                                            approximation: ChartUtils.approximations.sum,
                                            forced: true
                                        };
                                        series.push(item);

                                        var config = new ChartConfig("view/chart/column-date-all-electricity.json");
                                        config
                                            .setShared(false)
                                            .setZoom(false)
                                            .setCrossHairSnap(false)
                                            .setSeries(series)
                                            .setDataGroupingByDay();


                                        $("#chart-electricity-detail").highcharts("StockChart", config.getConfig());

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

    function getElectricityComparisonBarChart(param) {
        var series = [];

        var sNode = parent.parent.getSelectedNodeInfo();

        var paramChart = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var sInfo = parent.parent.findNode(sChildren.id);

                var pnInfos = getPnList(sInfo);

                var startTime = TimeUtils.dataBoxDateToDate(param.time);
                var endTime = TimeUtils.dataBoxDateToDate(param.time);
                endTime.setDate(endTime.getDate() + (param.interval + 1));
                paramChart.push({
                    node: pnInfos,
                    id: sChildren.id,
                    name: sChildren.text,
                    start: startTime.format("yyyyMMdd") + "000000",
                    end: endTime.format("yyyyMMdd") + "000000"
                });
            }
        }

        var chartCnt = 3;

        $.ajax({
            url: _ctx + "poweranalysis/comparison/electricity/all/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        chartCnt = chartCnt - 1;
                        var item = ChartUtils.getElectricityComparisonSeries("本期", paramChart, r.data);
                        series.push(item);

                        if (chartCnt <= 0) {
                            var config = $.parseJSON($.ajax({
                                url: "data/electricityComparisonChart.json?bust=" + new Date().getTime(),
                                type: "GET",
                                async: false
                            }).responseText);

                            config.xAxis.categories = ChartUtils.getElectricityComparisonCategories(paramChart);
                            config.series = series;

                            $("#chart-electricity-comparison").highcharts(config);
                        }

                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {

            }
        });

        var paramChart = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var sInfo = parent.parent.findNode(sChildren.id);

                var pnInfos = getPnList(sInfo);

                var startTime = TimeUtils.dataBoxDateToDate(param.time);
                startTime.setMonth(startTime.getMonth() - 1);
                var endTime = TimeUtils.dataBoxDateToDate(param.time);
                endTime.setMonth(endTime.getMonth() - 1);
                endTime.setDate(endTime.getDate() + (param.interval + 1));
                paramChart.push({
                    node: pnInfos,
                    id: sChildren.id,
                    name: sChildren.text,
                    start: startTime.format("yyyyMMdd") + "000000",
                    end: endTime.format("yyyyMMdd") + "000000"
                });
            }
        }

        $.ajax({
            url: _ctx + "poweranalysis/comparison/electricity/all/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        chartCnt = chartCnt - 1;

                        var item = ChartUtils.getElectricityComparisonSeries("上月同期", paramChart, r.data);
                        series.push(item);

                        if (chartCnt <= 0) {
                            var config = $.parseJSON($.ajax({
                                url: "data/electricityComparisonChart.json?bust=" + new Date().getTime(),
                                type: "GET",
                                async: false
                            }).responseText);

                            config.xAxis.categories = ChartUtils.getElectricityComparisonCategories(paramChart);
                            config.series = series;

                            $("#chart-electricity-comparison").highcharts(config);
                        }
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {

            }
        });

        var paramChart = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var sInfo = parent.parent.findNode(sChildren.id);

                var pnInfos = getPnList(sInfo);

                var startTime = TimeUtils.dataBoxDateToDate(param.time);
                startTime.setFullYear(startTime.getFullYear() - 1);
                var endTime = TimeUtils.dataBoxDateToDate(param.time);
                endTime.setFullYear(endTime.getFullYear() - 1);
                endTime.setDate(endTime.getDate() + (param.interval + 1));
                paramChart.push({
                    node: pnInfos,
                    id: sChildren.id,
                    name: sChildren.text,
                    start: startTime.format("yyyyMMdd") + "000000",
                    end: endTime.format("yyyyMMdd") + "000000"
                });
            }
        }

        $.ajax({
            url: _ctx + "poweranalysis/comparison/electricity/all/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        chartCnt = chartCnt - 1;

                        var item = ChartUtils.getElectricityComparisonSeries("去年同期", paramChart, r.data);
                        series.push(item);

                        if (chartCnt <= 0) {
                            var config = $.parseJSON($.ajax({
                                url: "data/electricityComparisonChart.json?bust=" + new Date().getTime(),
                                type: "GET",
                                async: false
                            }).responseText);

                            config.xAxis.categories = ChartUtils.getElectricityComparisonCategories(paramChart);
                            config.series = series;

                            $("#chart-electricity-comparison").highcharts(config);
                        }
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {

            }
        });
    }

    function getElectricityComparisonPieChart(param) {
        var series = [];

        var sNode = parent.parent.getSelectedNodeInfo();

        var chartCnt = 1;

        var paramChart = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var sInfo = parent.parent.findNode(sChildren.id);

                var pnInfos = getPnList(sInfo);

                var startTime = TimeUtils.dataBoxDateToDate(param.time);
                var endTime = TimeUtils.dataBoxDateToDate(param.time);
                endTime.setDate(endTime.getDate() + (param.interval + 1));
                paramChart.push({
                    node: pnInfos,
                    id: sChildren.id,
                    name: sChildren.text,
                    start: startTime.format("yyyyMMdd") + "000000",
                    end: endTime.format("yyyyMMdd") + "000000"
                });
            }
        }

        $.ajax({
            url: _ctx + "poweranalysis/comparison/electricity/all/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        chartCnt = chartCnt - 1;

                        var item = ChartUtils.getElectricityComparisonPieSeries("本期", paramChart, r.data);
                        series.push(item);

                        if (chartCnt <= 0) {
                            var config = $.parseJSON($.ajax({
                                url: "data/electricityCompositionChart.json?bust=" + new Date().getTime(),
                                type: "GET",
                                async: false
                            }).responseText);

                            config.series = series
                            $("#chart-electricity-composition").highcharts(config);
                        }
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            }
        });
    }

    function getElectricityComparisonTable(param) {
        var series = {};

        var sNode = parent.parent.getSelectedNodeInfo();

        var paramChart = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var sInfo = parent.parent.findNode(sChildren.id);

                var pnInfos = getPnList(sInfo);

                var startTime = TimeUtils.dataBoxDateToDate(param.time);
                var endTime = TimeUtils.dataBoxDateToDate(param.time);
                endTime.setDate(endTime.getDate() + (param.interval + 1));
                paramChart.push({
                    node: pnInfos,
                    id: sChildren.id,
                    name: sChildren.text,
                    start: startTime.format("yyyyMMdd") + "000000",
                    end: endTime.format("yyyyMMdd") + "000000"
                });
            }
        }

        var currentData = [];

        $.ajax({
            url: _ctx + "poweranalysis/comparison/electricity/all/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        currentData = ChartUtils.getElectricityComparisonTable(paramChart, r.data);

                        if (currentData.length > 0 && lastMonthData.length > 0 && lastYearData.length > 0) {
                            var category = ChartUtils.getElectricityComparisonCategories(paramChart);
                            var dgData = [];
                            for (var i = 0; i < category.length; i++) {
                                var item = {
                                    name: category[i],
                                    electricity: currentData[i],
                                    composition: DataGridUtils.floatFormatter((currentData[i] * 100 / currentData[category.length]), 1),
                                    rate1: lastMonthData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastMonthData[i]) * 100) / lastMonthData[i]), 1),
                                    rate2: lastYearData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastYearData[i]) * 100 ) / lastYearData[i]), 1)
                                };
                                dgData.push(item);
                            }

                            $("#dg-table").datagrid("loadData", dgData);
                        }
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {

            }
        });

        var paramChart = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var sInfo = parent.parent.findNode(sChildren.id);

                var pnInfos = getPnList(sInfo);

                var startTime = TimeUtils.dataBoxDateToDate(param.time);
                startTime.setMonth(startTime.getMonth() - 1);
                var endTime = TimeUtils.dataBoxDateToDate(param.time);
                endTime.setMonth(endTime.getMonth() - 1);
                endTime.setDate(endTime.getDate() + (param.interval + 1));
                paramChart.push({
                    node: pnInfos,
                    id: sChildren.id,
                    name: sChildren.text,
                    start: startTime.format("yyyyMMdd") + "000000",
                    end: endTime.format("yyyyMMdd") + "000000"
                });
            }
        }

        var lastMonthData = [];

        $.ajax({
            url: _ctx + "poweranalysis/comparison/electricity/all/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        lastMonthData = ChartUtils.getElectricityComparisonTable(paramChart, r.data);

                        if (currentData.length > 0 && lastMonthData.length > 0 && lastYearData.length > 0) {
                            var category = ChartUtils.getElectricityComparisonCategories(paramChart);
                            var dgData = [];
                            for (var i = 0; i < category.length; i++) {
                                var item = {
                                    name: category[i],
                                    electricity: currentData[i],
                                    composition: DataGridUtils.floatFormatter((currentData[i] * 100 / currentData[category.length]), 1),
                                    rate1: lastMonthData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastMonthData[i]) * 100) / lastMonthData[i]), 1),
                                    rate2: lastYearData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastYearData[i]) * 100 ) / lastYearData[i]), 1)
                                };
                                dgData.push(item);
                            }

                            $("#dg-table").datagrid("loadData", dgData);
                        }
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {

            }
        });

        var paramChart = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var sInfo = parent.parent.findNode(sChildren.id);

                var pnInfos = getPnList(sInfo);

                var startTime = TimeUtils.dataBoxDateToDate(param.time);
                startTime.setFullYear(startTime.getFullYear() - 1);
                var endTime = TimeUtils.dataBoxDateToDate(param.time);
                endTime.setFullYear(endTime.getFullYear() - 1);
                endTime.setDate(endTime.getDate() + (param.interval + 1));
                paramChart.push({
                    node: pnInfos,
                    id: sChildren.id,
                    name: sChildren.text,
                    start: startTime.format("yyyyMMdd") + "000000",
                    end: endTime.format("yyyyMMdd") + "000000"
                });
            }
        }

        var lastYearData = [];

        $.ajax({
            url: _ctx + "poweranalysis/comparison/electricity/all/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {

                        lastYearData = ChartUtils.getElectricityComparisonTable(paramChart, r.data);

                        if (currentData.length > 0 && lastMonthData.length > 0 && lastYearData.length > 0) {
                            var category = ChartUtils.getElectricityComparisonCategories(paramChart);
                            var dgData = [];
                            for (var i = 0; i < category.length; i++) {
                                var item = {
                                    name: category[i],
                                    electricity: currentData[i],
                                    composition: DataGridUtils.floatFormatter((currentData[i] * 100 / currentData[category.length]), 1),
                                    rate1: lastMonthData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastMonthData[i]) * 100) / lastMonthData[i]), 1),
                                    rate2: lastYearData[i] == 0 ? "-" : DataGridUtils.floatFormatter((((currentData[i] - lastYearData[i]) * 100 ) / lastYearData[i]), 1)
                                };
                                dgData.push(item);
                            }

                            $("#dg-table").datagrid("loadData", dgData);
                        }
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {

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

    function getPnList(nodes) {
        var infoList = [];
        for (var i = 0; i < _pnInfo.length; i++) {
            if (_pnInfo[i].areaId == nodes.areaId) {
                for (var j = 0; j < nodes.concentrators.length; j++) {
                    if (_pnInfo[i].concentratorId == nodes.concentrators[j].concentratorId) {
                        infoList.push(_pnInfo[i]);
                    }
                }
            }
        }
        return infoList;
    }

    function getPnListAll(nodes) {
        var pnInfo = $.parseJSON($.ajax({
            url: _ctx + "system/pn/info/list.do",
            type: "POST",
            data: {
                node: _nodes
            },
            async: false
        }).responseText);

        if ("0" == pnInfo.errcode) {
            return pnInfo.data;
        } else {
            jError("请求失败！" + ErrUtils.getMsg(pnInfo.errcode));
            return [];
        }
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

        getElectricityComparisonBarChart({
            node: _nodes,
            time: $("#datebox-time-start").datebox("getValue"),
            interval: interval
        });

        getElectricityComparisonPieChart({
            node: _nodes,
            time: $("#datebox-time-start").datebox("getValue"),
            interval: interval
        });

        getElectricityComparisonTable({
            node: _nodes,
            time: $("#datebox-time-start").datebox("getValue"),
            interval: interval
        });
    }

});