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
                                    id: transformers[i].pnId
                                },
                                async: false
                            }).responseText).data[0];
                            nodes.push(pnInfo);
                        }

                        getLoadDetailChart({
                            nodes: nodes,
                            time: new Date().format("yyyy-MM-dd")
                        });
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


    function getLoadDetailChart(row) {
        var series = [];

        var pnList = row.nodes;

        var time = row.time;

        var paramChart = {
            node: pnList,
            time: []
        };

        var today = TimeUtils.dataBoxDateToDate(time);

        paramChart.time.push(
            today.format("yyyyMMdd") + "000000"
        );

        $.ajax({
            url: _ctx + "poweranalysis/comparison/load/daily/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var maxLoad = ChartUtils.getLoadDailySumIndexMax(pnList, today.format('yyyyMMdd') + "000000", r.data);
                        $("#today-max-load").text(DataGridUtils.floatFormatter(maxLoad[0], 3) + "(kW)");
                        $("#today-max-load-time").text(today.format('yyyy-MM-dd') + " " + fixNum(maxLoad[1], 2) + ":00");

                        var item = ChartUtils.getLoadDailySumIndexSeries("今日", pnList, today.format('yyyyMMdd') + "000000", r.data);
                        series.push(item);

                        if (series.length == 2) {
                            var config = $.parseJSON($.ajax({
                                url: "data/loadDetailChart.json?bust=" + new Date().getTime(),
                                type: "GET",
                                async: false
                            }).responseText);

                            config.xAxis.categories = ChartUtils.getDailyCategories();
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

        paramChart.time = [];
        var yesterday = TimeUtils.dataBoxDateToDate(time);
        yesterday.setDate(yesterday.getDate() - 1);
        paramChart.time.push(
            yesterday.format("yyyyMMdd") + "000000"
        );

        $.ajax({
            url: _ctx + "poweranalysis/comparison/load/daily/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var maxLoad = ChartUtils.getLoadDailySumIndexMax(pnList, yesterday.format('yyyyMMdd') + "000000", r.data);
                        $("#yesterday-max-load").text(DataGridUtils.floatFormatter(maxLoad[0], 3) + "(kW)");
                        $("#yesterday-max-load-time").text(yesterday.format('yyyy-MM-dd') + " " + fixNum(maxLoad[1], 2) + ":00");

                        var item = ChartUtils.getLoadDailySumIndexSeries("昨日", pnList, yesterday.format('yyyyMMdd') + "000000", r.data);
                        series.push(item);

                        if (series.length == 2) {
                            var config = $.parseJSON($.ajax({
                                url: "data/loadDetailChart.json?bust=" + new Date().getTime(),
                                type: "GET",
                                async: false
                            }).responseText);

                            config.xAxis.categories = ChartUtils.getDailyCategories();
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