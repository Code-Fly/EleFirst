/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var DEFAULT_INTERVAL = 30;

    var _spinner = new Spinner();

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

                        var startTime = TimeUtils.dataBoxDateToDate(param.time);
                        var endTime = TimeUtils.dataBoxDateToDate(param.time);
                        endTime.setDate(endTime.getDate() + (param.interval + 1));

                        var paramNode = r.data;
                        var paramChart = {
                            node: JSON.stringify(paramNode),
                            startTime: startTime.format("yyyyMMdd") + "000000",
                            endTime: endTime.format("yyyyMMdd") + "000000"
                        };

                        $.ajax({
                            url: _ctx + "power/data/f5/rate/node/list.do",
                            type: "POST",
                            cache: false,
                            data: paramChart,
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {

                                        var series = [];

                                        var data = _.filter(r.data, function (o) {
                                            return o.rateSeq == "1";
                                        });

                                        var item = ChartUtils.getF5RateAllSeries({
                                            name: "尖电量"
                                        }, data);
                                        item.dataGrouping = {
                                            approximation: ChartUtils.approximations.sum,
                                            forced: true
                                        };
                                        series.push(item);

                                        var data = _.filter(r.data, function (o) {
                                            return o.rateSeq == "2";
                                        });

                                        var item = ChartUtils.getF5RateAllSeries({
                                            name: "峰电量"
                                        }, data);
                                        item.dataGrouping = {
                                            approximation: ChartUtils.approximations.sum,
                                            forced: true
                                        };
                                        series.push(item);

                                        var data = _.filter(r.data, function (o) {
                                            return o.rateSeq == "3";
                                        });

                                        var item = ChartUtils.getF5RateAllSeries({
                                            name: "平电量"
                                        }, data);
                                        item.dataGrouping = {
                                            approximation: ChartUtils.approximations.sum,
                                            forced: true
                                        };
                                        series.push(item);

                                        var data = _.filter(r.data, function (o) {
                                            return o.rateSeq == "4";
                                        });

                                        var item = ChartUtils.getF5RateAllSeries({
                                            name: "谷电量"
                                        }, data);
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

                        timeList.push({
                            startTime: TimeUtils.getDataBoxDateToDateInterval(param.time, 0, -1, 0).format('yyyyMMdd') + "000000",
                            endTime: TimeUtils.getDataBoxDateToDateInterval(param.time, 0, -1, param.interval + 1).format('yyyyMMdd') + "000000"
                        });

                        timeList.push({
                            startTime: TimeUtils.getDataBoxDateToDateInterval(param.time, -1, 0, 0).format('yyyyMMdd') + "000000",
                            endTime: TimeUtils.getDataBoxDateToDateInterval(param.time, -1, 0, param.interval + 1).format('yyyyMMdd') + "000000"
                        });

                        $.ajax({
                            url: _ctx + "power/data/f5/rate/node/time/sum.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(paramNode),
                                time: JSON.stringify(timeList)
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var series = [];

                                        var categories = ChartUtils.getElectricityRateSeqCategories();

                                        var d = r.data[0][0];

                                        var item = ChartUtils.getF5RateAllCategorySeries({
                                            name: "本期",
                                            categories: categories
                                        }, d);
                                        series.push(item);

                                        var d = r.data[0][1];

                                        var item = ChartUtils.getF5RateAllCategorySeries({
                                            name: "上月同期",
                                            categories: categories
                                        }, d);
                                        series.push(item);

                                        var d = r.data[0][2];

                                        var item = ChartUtils.getF5RateAllCategorySeries({
                                            name: "去年同期",
                                            categories: categories
                                        }, d);
                                        series.push(item);


                                        var config = new ChartConfig("view/poweranalysis/electricityByTime/data/electricityComparisonChart.json");
                                        config
                                            .setShared(false)
                                            // .setZoom(false)
                                            .setCrossHairSnap(false)
                                            .setSeries(series)
                                        // .setDataGroupingByDay();

                                        $("#chart-electricity-comparison").highcharts(config.getConfig());
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

    function getElectricityComparisonPieChart(param) {
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
                            url: _ctx + "power/data/f5/rate/node/time/sum.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(paramNode),
                                time: JSON.stringify(timeList)
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        var series = [];

                                        var categories = ChartUtils.getElectricityRateSeqCategories();

                                        var d = r.data[0][0];

                                        var item = ChartUtils.getF5RateAllCategoryPieSeries({
                                            name: "本期",
                                            categories: categories
                                        }, d);
                                        series.push(item);


                                        var config = new ChartConfig("view/poweranalysis/electricityByTime/data/electricityCompositionChart.json");
                                        config
                                        // .setShared(false)
                                        // .setZoom(false)
                                        // .setCrossHairSnap(false)
                                            .setSeries(series)
                                        // .setDataGroupingByDay();

                                        $("#chart-electricity-composition").highcharts(config.getConfig());
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
    }

});