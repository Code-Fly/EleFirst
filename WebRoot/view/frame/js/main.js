/**
 * Created by VM on 1/25/2017.
 */
$(document).ready(function () {
    var _spinner = new Spinner();

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
                // _spinner.load();
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {
                //  _spinner.unload();
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
            url: _ctx + "power/data/f25/frozen/minute/node/list.do",
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
                            var config = new ChartConfig("view/chart/spline-date-all-load.json");

                            config
                                .setShared(true)
                                .setZoom(true)
                                .setSeries(series);

                            $("#chart-day-load").highcharts("StockChart", config.getConfig());
                        }

                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {
                // _spinner.load();
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {
                //  _spinner.unload();
            }
        });

        var startDate = new Date();
        var endDate = new Date();
        startDate.setDate(startDate.getDate() - 1);

        var startTime = startDate.format('yyyyMMdd') + "000000";
        var endTime = endDate.format('yyyyMMdd') + "000000";

        $.ajax({
            url: _ctx + "power/data/f25/frozen/minute/node/list.do",
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
                            var config = new ChartConfig("view/chart/spline-date-all-load.json");

                            config
                                .setShared(true)
                                .setZoom(true)
                                .setSeries(series);

                            $("#chart-day-load").highcharts("StockChart", config.getConfig());
                        }

                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {
                // _spinner.load();
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {
                //  _spinner.unload();
            }
        });

        $.ajax({
            url: _ctx + "index/load/activepower/total/statistic.do",
            type: "POST",
            cache: false,
            data: {
                areaId: _areaId
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var item = r.data;

                        if (null != item[0].maxTotalActivePower) {
                            $("#today-max-load").text(item[0].maxTotalActivePower + "(kW)");
                        } else {
                            $("#today-max-load").text("--");
                        }
                        if (null != item[0].maxTotalActivePowerTime) {
                            $("#today-max-load-time").text(TimeUtils.dbTimeToDate(item[0].maxTotalActivePowerTime).format("hh:mm"));
                        } else {
                            $("#today-max-load-time").text("--");
                        }

                        if (null != item[1].maxTotalActivePower) {
                            $("#yesterday-max-load").text(item[1].maxTotalActivePower + "(kW)");
                        } else {
                            $("#yesterday-max-load").text("--");
                        }
                        if (null != item[1].maxTotalActivePowerTime) {
                            $("#yesterday-max-load-time").text(TimeUtils.dbTimeToDate(item[1].maxTotalActivePowerTime).format("hh:mm"));
                        } else {
                            $("#yesterday-max-load-time").text("--");
                        }


                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {
                // _spinner.load();
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {
                //  _spinner.unload();
            }
        });
    }

    function getElectricityDetailChart(param) {
        var series = [];

        var pnList = [
            param.nodes
        ];

        var timeList = [];
        timeList.push({
            startTime: TimeUtils.getCurrentMonthFirst().format('yyyyMMdd') + "000000",
            endTime: TimeUtils.getNextMonthFirst().format('yyyyMMdd') + "000000"
        });

        timeList.push({
            startTime: TimeUtils.getLastMonthFirst().format('yyyyMMdd') + "000000",
            endTime: TimeUtils.getCurrentMonthFirst().format('yyyyMMdd') + "000000"
        });


        $.ajax({
            url: _ctx + "power/data/f5/node/time/sum.do",
            type: "POST",
            cache: false,
            data: {
                node: JSON.stringify(pnList),
                time: JSON.stringify(timeList)
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        // $.messager.alert("信息提示", JSON.stringify(r.data));

                        getElectricityStatistic(r.data[0]);

                        var item = ChartUtils.getF5AllByDaySeries({
                            name: "本期"
                        }, r.data[0][0]);
                        item.dataGrouping = {
                            approximation: ChartUtils.approximations.sum,
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getF5AllByDaySeries({
                            name: "上月同期"
                        }, r.data[0][1]);
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
                            .setDataGroupingByDayOfMonth();

                        $("#chart-month-electricity").highcharts("StockChart", config.getConfig());


                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {
                //  // _spinner.load();
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
            },
            complete: function (XMLHttpRequest, textStatus) {
                //  //  _spinner.unload();
            }
        });
    }

    function getElectricityStatistic(data) {
        var thisMonthTotal = null;
        for (var i = 0; i < data[0].length; i++) {
            if (null != data[0][i].totalpositiveactivepower) {
                if (null == thisMonthTotal) {
                    thisMonthTotal = 0;
                }
                thisMonthTotal += parseFloat(data[0][i].totalpositiveactivepower) * ChartUtils.NUM_FIX;
            }
        }

        var lastMonthTotal = null;
        for (var i = 0; i < data[1].length; i++) {
            if (null != data[1][i].totalpositiveactivepower) {
                if (null == lastMonthTotal) {
                    lastMonthTotal = 0;
                }
                lastMonthTotal += parseFloat(data[1][i].totalpositiveactivepower) * ChartUtils.NUM_FIX;
            }
        }

        if (null != thisMonthTotal) {
            thisMonthTotal = thisMonthTotal / ChartUtils.NUM_FIX;
        }

        if (null != lastMonthTotal) {
            lastMonthTotal = lastMonthTotal / ChartUtils.NUM_FIX;
        }

        var rate = null;
        if (null != thisMonthTotal && null != lastMonthTotal) {
            rate = ((thisMonthTotal - lastMonthTotal) * 100) / lastMonthTotal;
            rate = rate.toFixed(1);
        }


        $("#this-month-total-electricity").text(null == thisMonthTotal ? "--" : (thisMonthTotal + "(kWh)"));
        $("#electricity-rate").text(null == rate ? "--" : (rate + "(%)"));
        var d = new Date();
        d.setDate(d.getDate() - 1);
        $("#this-month-total-electricity-time").text("截止：" + d.format('yyyy-MM-dd'));
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