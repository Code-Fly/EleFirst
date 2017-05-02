/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var _spinner = new Spinner();

    var DEFAULT_INTERVAL = 11;

    var _nodes = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    var _pnInfo = getPnListAll(_nodes);

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
                        var series = [];

                        var paramNode = r.data;
                        var paramChart = {
                            node: paramNode,
                            time: []
                        };

                        var chartCnt = 1;

                        for (var i = 0; i <= param.interval; i++) {
                            var item = TimeUtils.dataBoxMonthToDate(param.time);
                            item.setMonth(item.getMonth() + i);
                            paramChart.time.push(item.format('yyyyMM') + "01000000");
                        }

                        var paramNode = [
                            r.data
                        ];

                        var timeList = [];
                        timeList.push({
                            startTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, 0, 0).format('yyyyMM') + "01000000",
                            endTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, 0, param.interval + 1).format('yyyyMM') + "01000000"
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
                                            .setDataGroupingByMonth();

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
        var sNode = parent.parent.getSelectedNodeInfo();

        var paramNode = [];

        var categories = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var pnInfos = sChildren.attributes.master;

                paramNode.push(pnInfos);
                categories.push(sChildren.text);
            }
        }

        var timeList = [];
        timeList.push({
            startTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, 0, 0).format('yyyyMM') + "01000000",
            endTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, 0, param.interval + 1).format('yyyyMMdd') + "01000000"
        });

        timeList.push({
            startTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, -1, 0).format('yyyyMM') + "01000000",
            endTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, -1, param.interval + 1).format('yyyyMMdd') + "01000000"
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

                        var d = [];
                        for (var i = 0; i < categories.length; i++) {
                            d.push(r.data[i][0]);
                        }

                        var item = ChartUtils.getF5AllCategorySeries({
                            name: "本期",
                            categories: categories
                        }, d);
                        series.push(item);


                        var d = [];
                        for (var i = 0; i < categories.length; i++) {
                            d.push(r.data[i][1]);
                        }

                        var item = ChartUtils.getF5AllCategorySeries({
                            name: "去年同期",
                            categories: categories
                        }, d);
                        series.push(item);

                        var config = new ChartConfig("view/poweranalysis/electricitycomposition/data/electricityComparisonChart.json");
                        config
                            .setShared(false)
                            .setCrossHairSnap(false)
                            .setSeries(series);

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
    }

    function getElectricityComparisonPieChart(param) {
        var sNode = parent.parent.getSelectedNodeInfo();

        var paramNode = [];

        var categories = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var pnInfos = sChildren.attributes.master;

                paramNode.push(pnInfos);
                categories.push(sChildren.text);
            }
        }

        var timeList = [];
        timeList.push({
            startTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, 0, 0).format('yyyyMM') + "01000000",
            endTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, 0, param.interval + 1).format('yyyyMMdd') + "01000000"
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

                        var d = [];
                        for (var i = 0; i < categories.length; i++) {
                            d.push(r.data[i][0]);
                        }

                        var item = ChartUtils.getF5AllCategoryPieSeries({
                            name: "本期",
                            categories: categories
                        }, d);
                        series.push(item);

                        var config = new ChartConfig("view/poweranalysis/electricitycomposition/data/electricityCompositionChart.json");
                        config
                            .setSeries(series);

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
    }

    function getElectricityComparisonTable(param) {
        var sNode = parent.parent.getSelectedNodeInfo();

        var paramNode = [];

        var categories = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                var pnInfos = sChildren.attributes.master;

                paramNode.push(pnInfos);
                categories.push(sChildren.text);
            }
        }

        var timeList = [];
        timeList.push({
            startTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, 0, 0).format('yyyyMM') + "01000000",
            endTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, 0, param.interval + 1).format('yyyyMMdd') + "01000000"
        });

        timeList.push({
            startTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, -1, 0).format('yyyyMM') + "01000000",
            endTime: TimeUtils.getDataBoxMonthToDateToDateInterval(param.time, -1, param.interval + 1).format('yyyyMMdd') + "01000000"
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
                        var dgData = [];

                        for (var i = 0; i < categories.length; i++) {
                            // d.push(r.data[i][0]);
                            console.log(JSON.stringify(r.data[i]))

                            var thisMonth = 0;

                            for (var j = 0; j < r.data[i][0].length; j++) {
                                thisMonth += parseFloat(r.data[i][0][j].totalpositiveactivepower) * ChartUtils.NUM_FIX;
                            }

                            thisMonth = thisMonth / ChartUtils.NUM_FIX;

                            var lastYear = 0;

                            for (var j = 0; j < r.data[i][1].length; j++) {
                                lastYear += parseFloat(r.data[i][1][j].totalpositiveactivepower) * ChartUtils.NUM_FIX;
                            }

                            lastYear = lastYear / ChartUtils.NUM_FIX;

                            dgData.push({
                                name: categories[i],
                                electricity: thisMonth,
                                rate2: lastYear == 0 ? null : ((thisMonth - lastYear) / lastYear).toFixed(1)
                            });


                        }

                        var total = 0;
                        for (var i = 0; i < categories.length; i++) {
                            total += dgData[i].electricity;
                        }

                        for (var i = 0; i < categories.length; i++) {
                            dgData[i].composition = total == 0 ? null : (dgData[i].electricity * 100 / total).toFixed(1);
                        }

                        $("#dg-table").datagrid("loadData", dgData);

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

        getElectricityComparisonTable({
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