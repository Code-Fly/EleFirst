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
                                nodes: nodes
                            });
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
                            $("#chart-day-load").highcharts("StockChart", config);
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
                            $("#chart-day-load").highcharts("StockChart", config);
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

    function getElectricityDetailChart(param) {
        var series = [];

        var pnList = param.nodes;

        var startDate = TimeUtils.getCurrentMonthFirst();
        var endDate = TimeUtils.getNextMonthFirst();

        var startTime = startDate.format('yyyyMMdd') + "000000";
        var endTime = endDate.format('yyyyMMdd') + "000000";


        var thisMonthTotal = 0;
        $.ajax({
            url: _ctx + "power/data/f33/node/list.do",
            type: "POST",
            cache: false,
            data: {
                node: JSON.stringify(pnList),
                startTime: startTime,
                endTime: endTime
            },
            async: false,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {

                        var item = ChartUtils.getElectricityAllByDaySeries({
                            name: "本期"
                        }, r.data);
                        item.dataGrouping = {
                            approximation: "sum",
                            forced: true
                        };
                        series.push(item);


                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            }
        });


        var startDate = TimeUtils.getLastMonthFirst();
        var endDate = TimeUtils.getCurrentMonthFirst();

        var startTime = startDate.format('yyyyMMdd') + "000000";
        var endTime = endDate.format('yyyyMMdd') + "000000";

        $.ajax({
            url: _ctx + "power/data/f33/node/list.do",
            type: "POST",
            cache: false,
            data: {
                node: JSON.stringify(pnList),
                startTime: startTime,
                endTime: endTime
            },
            async: false,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {

                        var item = ChartUtils.getElectricityAllByDaySeries({
                            name: "上月同期"
                        }, r.data);
                        item.dataGrouping = {
                            approximation: "sum",
                            forced: true
                        };
                        series.push(item);
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            }
        });


        // var startDate = getLastMonthFirst();
        // var endDate = getCurrentMonthFirst();
        //
        // var startTime = startDate.format('yyyyMMdd') + "000000";
        // var endTime = endDate.format('yyyyMMdd') + "000000";
        //
        //
        // var lastMonthTotal = 0;
        // $.ajax({
        //     url: _ctx + "poweranalysis/comparison/electricity/daily/interval/day/chart.do",
        //     type: "POST",
        //     cache: false,
        //     async: false,
        //     contentType: "text/plain;charset=UTF-8",
        //     data: JSON.stringify(paramChart),
        //     success: function (r) {
        //         if (r.hasOwnProperty("errcode")) {
        //             if ("0" == r.errcode) {
        //                 // $.messager.alert("操作提示", JSON.stringify(r.data));
        //                 lastMonthTotal = ChartUtils.getElectricityDailyIntervalDayIndexSum(paramNode, getLastMonthFirst().format("yyyy-MM-dd"), interval, r.data)
        //
        //                 $("#electricity-rate").text(lastMonthTotal == 0 ? "-" : ( DataGridUtils.floatFormatter((thisMonthTotal * 100) / lastMonthTotal, 1) + "(%)"));
        //
        //                 $("#this-month-total-electricity-time").text("截止" + new Date().format("yyyy-MM-dd"));
        //
        //             } else {
        //                 jError("请求失败！" + ErrUtils.getMsg(r.errcode));
        //             }
        //         } else {
        //             jError("请求失败！" + ErrUtils.getMsg("2"));
        //         }
        //     }
        // });


        var config = $.parseJSON($.ajax({
            url: _ctx + "view/chart/column-date-single-electricity.json?bust=" + new Date().getTime(),
            type: "GET",
            async: false
        }).responseText);

        config.plotOptions.series.dataGrouping = {
            forced: true,
            units: [
                [
                    "day", [1]
                ]
            ]
        };
        config.tooltip.formatter = function () {
            var s = '<span style="font-size: 10px">' + new Date(this.point.x).format("d") + '日' + '</span><br/>'
                + '<span style="color:"' + this.point.color + '>\u25CF</span> ' + this.series.name + ': <b>' + this.point.y + 'kWh</b><br/>';
            return s;
        };

        // config.tooltip.formatter = function () {
        //     var s = '<span style="font-size: 10px">' + new Date(this.x).format("d") + '日' + '</span>'
        //     $.each(this.points, function (i, point) {
        //         s += '<br/><span style="color:"' + point.color + '>\u25CF</span> ' + point.series.name + ': <b>' + point.y + 'kWh</b><br/>';
        //     });
        //     return s;
        // };


        config.series = series;

        $("#chart-month-electricity").highcharts("StockChart", config);
    }

    function getDateInterval(start, end) {
        var days = end.getTime() - start.getTime();
        var time = parseInt(days / (1000 * 60 * 60 * 24));
        return time
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


});