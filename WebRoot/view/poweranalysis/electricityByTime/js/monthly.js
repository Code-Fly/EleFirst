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
                        var series = [];

                        var startTime = TimeUtils.dataBoxMonthToDate(param.time);
                        var endTime = TimeUtils.dataBoxMonthToDate(param.time);
                        endTime.setMonth(endTime.getMonth() + (param.interval + 1));

                        var paramNode = r.data;
                        var paramChart = {
                            node: paramNode,
                            start: startTime.format("yyyyMM") + "01000000",
                            end: endTime.format("yyyyMM") + "01000000"
                        };

                        var chartCnt = 1;

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/yearly/rateseq/all/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {

                                        chartCnt = chartCnt - 1;

                                        var category = ChartUtils.getElectricityRateSeqCategories();
                                        for (var i = 0; i < category.length; i++) {
                                            var item = ChartUtils.getElectricityYearlyRateSeqSeries(category[i], paramNode, param.time, param.interval, r.data, (i + 1));
                                            series.push(item);
                                        }

                                        if (chartCnt <= 0) {
                                            var config = $.parseJSON($.ajax({
                                                url: "data/electricityDetailByDateChart.json?bust=" + new Date().getTime(),
                                                type: "GET",
                                                async: false
                                            }).responseText);

                                            // config.xAxis.categories = ChartUtils.getMonthlyIntervalMonthCategories(param.time, param.interval);
                                            config.series = series;

                                            $("#chart-electricity-detail").highcharts(config);
                                        }
                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });
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
                        var series = [];

                        var chartCnt = 2;

                        var startTime = TimeUtils.dataBoxMonthToDate(param.time);
                        var endTime = TimeUtils.dataBoxMonthToDate(param.time);
                        endTime.setMonth(endTime.getMonth() + (param.interval + 1));

                        var paramNode = r.data;
                        var paramChart = {
                            node: paramNode,
                            start: startTime.format("yyyyMM") + "01000000",
                            end: endTime.format("yyyyMM") + "01000000"
                        };


                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/yearly/rateseq/all/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        chartCnt = chartCnt - 1;

                                        var item = ChartUtils.getElectricityRateSeqBarSeries("本期", paramNode, param.time, param.interval, r.data);
                                        series.push(item);

                                        if (chartCnt <= 0) {
                                            var config = $.parseJSON($.ajax({
                                                url: "data/electricityComparisonChart.json?bust=" + new Date().getTime(),
                                                type: "GET",
                                                async: false
                                            }).responseText);

                                            config.xAxis.categories = ChartUtils.getElectricityRateSeqBarCategories(param.time, param.interval);
                                            config.series = series;

                                            $("#chart-electricity-comparison").highcharts(config);
                                        }
                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });

                        var startTime = TimeUtils.dataBoxMonthToDate(param.time);
                        startTime.setFullYear(startTime.getFullYear() - 1);
                        var endTime = TimeUtils.dataBoxMonthToDate(param.time);
                        endTime.setMonth(endTime.getMonth() + (param.interval + 1));
                        endTime.setFullYear(endTime.getFullYear() - 1);

                        var paramNode = r.data;
                        var paramChart = {
                            node: paramNode,
                            start: startTime.format("yyyyMM") + "01000000",
                            end: endTime.format("yyyyMM") + "01000000"
                        };

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/yearly/rateseq/all/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        chartCnt = chartCnt - 1;

                                        var item = ChartUtils.getElectricityRateSeqBarSeries("去年同期", paramNode, param.time, param.interval, r.data);
                                        series.push(item);

                                        if (chartCnt <= 0) {
                                            var config = $.parseJSON($.ajax({
                                                url: "data/electricityComparisonChart.json?bust=" + new Date().getTime(),
                                                type: "GET",
                                                async: false
                                            }).responseText);

                                            config.xAxis.categories = ChartUtils.getElectricityRateSeqBarCategories(param.time, param.interval);
                                            config.series = series;

                                            $("#chart-electricity-comparison").highcharts(config);
                                        }
                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });

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
                        var series = [];

                        var chartCnt = 1;

                        var startTime = TimeUtils.dataBoxMonthToDate(param.time);
                        var endTime = TimeUtils.dataBoxMonthToDate(param.time);
                        endTime.setMonth(endTime.getMonth() + (param.interval + 1));

                        var paramNode = r.data;
                        var paramChart = {
                            node: paramNode,
                            start: startTime.format("yyyyMM") + "01000000",
                            end: endTime.format("yyyyMM") + "01000000"
                        };


                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/yearly/rateseq/all/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

                                        chartCnt = chartCnt - 1;

                                        var item = ChartUtils.getElectricityRateSeqPieSeries("本期", paramNode, param.time, param.interval, r.data);
                                        series.push(item);

                                        if (chartCnt <= 0) {
                                            var config = $.parseJSON($.ajax({
                                                url: "data/electricityCompositionChart.json?bust=" + new Date().getTime(),
                                                type: "GET",
                                                async: false
                                            }).responseText);

                                            config.series = series;

                                            $("#chart-electricity-composition").highcharts(config);
                                        }
                                    } else {
                                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                    }
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                                }
                            }
                        });

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
            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(pnInfo.errcode), "info");
            return [];
        }
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