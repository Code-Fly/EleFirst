/**
 * Created by VM on 1/25/2017.
 */
$(document).ready(function () {

    loadSummaryInfo();

    function loadSummaryInfo() {
        $.ajax({
            url: _ctx + "index/summary/info.do",
            type: "POST",
            data: {
                areaId: _areaId
            },
            cache: false,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        $("#transformers").text(r.data.transformers);
                        $("#ratedCapacity").text(r.data.ratedCapacity);
                        $("#pns").text(r.data.pns);
                        $("#electricityThisMonth").text(DataGridUtils.floatWithUnitFormatter(r.data.electricityThisMonth, 4));
                        $("#electricityLastMonth").text(DataGridUtils.floatWithUnitFormatter(r.data.electricityLastMonth, 4));
                        $("#electricityLastLastMonth").text(DataGridUtils.floatWithUnitFormatter(r.data.electricityLastLastMonth, 4));
                        $("#maxLoadThisMonth").text(DataGridUtils.floatWithUnitFormatter(r.data.maxLoadThisMonth, 3));
                        $("#maxLoadThisYear").text(DataGridUtils.floatWithUnitFormatter(r.data.maxLoadThisYear, 3));
                        $("#maxLoadTotal").text(DataGridUtils.floatWithUnitFormatter(r.data.maxLoadTotal, 3));

                        var transformers = r.data.transformersInfo;
                        var nodes = [];

                        for (var i = 0; i < transformers.length; i++) {
                            var pnInfo = $.parseJSON($.ajax({
                                url: _ctx + "system/pn/info/detailById.do",
                                type: "POST",
                                data: {
                                    id: r.data.masterPnId
                                },
                                async: false
                            }).responseText).data[0];
                            nodes.push(pnInfo);
                        }
                        if (nodes.length > 0) {
                            getLoadDetailChart({
                                nodes: nodes
                            });


                            getElectricityDetailChart({
                                node: nodes
                            });
                        }
                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
            },
            complete: function (XMLHttpRequest, textStatus) {

            }
        });
    }


    function getLoadDetailChart(param) {
        var series = [];

        var pnList = param.nodes;

        var startDate = new Date();
        var endDate = new Date();
        endDate.setDate(endDate.getDate() + 1);

        var startTime = startDate.format('yyyyMMdd') + "000000";
        var endTime = endDate.format('yyyyMMdd') + "000000";

        $.ajax({
            url: _ctx + "power/data/f25/node/list.do",
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

                        var item = ChartUtils.getLoadAllByHourSeries({
                            name: "今日"
                        }, r.data);
                        series.push(item);

                        if (series.length == 2) {
                            var config = $.parseJSON($.ajax({
                                url: _ctx + "view/chart/spline-date-single-load.json?bust=" + new Date().getTime(),
                                type: "GET",
                                async: false
                            }).responseText);

                            config.series = series;
                            $("#chart-day-load").highcharts(config);
                        }

                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
            },
            complete: function (XMLHttpRequest, textStatus) {

            }
        });

        var startDate = new Date();
        var endDate = new Date();
        startDate.setDate(startDate.getDate() - 1);

        var startTime = startDate.format('yyyyMMdd') + "000000";
        var endTime = endDate.format('yyyyMMdd') + "000000";

        $.ajax({
            url: _ctx + "power/data/f25/node/list.do",
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
                        var item = ChartUtils.getLoadAllByHourSeries({
                            name: "昨日"
                        }, r.data);
                        series.push(item);

                        if (series.length == 2) {
                            var config = $.parseJSON($.ajax({
                                url: _ctx + "view/chart/spline-date-single-load.json?bust=" + new Date().getTime(),
                                type: "GET",
                                async: false
                            }).responseText);

                            config.series = series;
                            $("#chart-day-load").highcharts(config);
                        }

                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            beforeSend: function (XMLHttpRequest) {

            },
            error: function (request) {
                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
            },
            complete: function (XMLHttpRequest, textStatus) {

            }
        });
    }

    function getElectricityDetailChart(param) {
        var series = [];

        var paramNode = param.node;
        var paramChart = {
            node: paramNode,
            time: []
        };


        var interval = getDateInterval(getCurrentMonthFirst(), getCurrentMonthLast());
        for (var i = 0; i <= interval; i++) {
            var item = getCurrentMonthFirst();
            item.setDate(item.getDate() + i);
            paramChart.time.push(item.format('yyyyMMdd') + "000000");
        }

        var thisMonthTotal = 0;
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

                        thisMonthTotal = ChartUtils.getElectricityDailyIntervalDayIndexSum(paramNode, getCurrentMonthFirst().format("yyyy-MM-dd"), interval, r.data)
                        $("#this-month-total-electricity").text(thisMonthTotal + "(kWh)");

                        var item = ChartUtils.getElectricityDailyIntervalDayIndexSeries("本期", paramNode, getCurrentMonthFirst().format("yyyy-MM-dd"), interval, r.data);
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
        var interval = getDateInterval(getLastMonthFirst(), getLastMonthLast());
        for (var i = 0; i <= interval; i++) {
            var item = getLastMonthFirst();
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
                        var item = ChartUtils.getElectricityDailyIntervalDayIndexSeries("上月同期", paramNode, getLastMonthFirst().format("yyyy-MM-dd"), interval, r.data);
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
        var lastMonthSameTime = new Date();
        lastMonthSameTime.setMonth(lastMonthSameTime.getMonth() - 1);
        var interval = getDateInterval(getLastMonthFirst(), lastMonthSameTime);
        for (var i = 0; i <= interval; i++) {
            var item = getLastMonthFirst();
            item.setDate(item.getDate() + i);
            paramChart.time.push(item.format('yyyyMMdd') + "000000");
        }

        var lastMonthTotal = 0;
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
                        lastMonthTotal = ChartUtils.getElectricityDailyIntervalDayIndexSum(paramNode, getLastMonthFirst().format("yyyy-MM-dd"), interval, r.data)

                        $("#electricity-rate").text(lastMonthTotal == 0 ? "-" : ( DataGridUtils.floatFormatter((thisMonthTotal * 100) / lastMonthTotal, 1) + "(%)"));

                        $("#this-month-total-electricity-time").text("截止" + new Date().format("yyyy-MM-dd"));

                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            }
        });

        var config = $.parseJSON($.ajax({
            url: "data/electricityDetailByDateChart.json?bust=" + new Date().getTime(),
            type: "GET",
            async: false
        }).responseText);

        // config.xAxis.categories = ChartUtils.getDailyIntervalDayCategories(param.time, param.interval);
        config.series = series;

        $("#chart-month-electricity").highcharts(config);
    }

    function getDateInterval(start, end) {
        var days = end.getTime() - start.getTime();
        var time = parseInt(days / (1000 * 60 * 60 * 24));
        return time
    }


    function getCurrentMonthFirst() {
        var date = new Date();
        date.setDate(1);
        return date;
    }

    function getLastMonthFirst() {
        var date = new Date();
        date.setMonth(date.getMonth() - 1);
        date.setDate(1);
        return date;
    }

    function getCurrentMonthLast() {
        var date = new Date();
        date.setDate(1);
        date.setMonth(date.getMonth() + 1);
        date.setDate(date.getDate() - 1);
        return date;
    }

    function getLastMonthLast() {
        var date = new Date();
        date.setDate(1);
        date.setDate(date.getDate() - 1);
        return date;
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
            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(pnInfo.errcode), "info");
            return null;
        }
    }


});