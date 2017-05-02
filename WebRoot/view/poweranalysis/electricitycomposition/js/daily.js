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

        var sNode = parent.parent.getSelectedNodeInfo();

        var paramNode = [];

        var categories = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];

                // var sInfo = parent.parent.findNode(sChildren.id);
                //
                // var pnInfos = getPnList(sInfo);

                var pnInfos = sChildren.attributes.master;

                paramNode.push(pnInfos);
                categories.push(sChildren.text);
            }
        }

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
                            name: "上月同期",
                            categories: categories
                        }, d);
                        series.push(item);

                        var d = [];
                        for (var i = 0; i < categories.length; i++) {
                            d.push(r.data[i][2]);
                        }

                        var item = ChartUtils.getF5AllCategorySeries({
                            name: "去年同期",
                            categories: categories
                        }, d);
                        series.push(item);

                        // $.messager.alert("信息提示", JSON.stringify(series));

                        var config = new ChartConfig("view/poweranalysis/electricitycomposition/data/electricityComparisonChart.json");
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
    }

    function getElectricityComparisonPieChart(param) {
        var sNode = parent.parent.getSelectedNodeInfo();

        var paramNode = [];

        var categories = [];
        if (sNode.hasOwnProperty("children")) {
            for (var ch = 0; ch < sNode.children.length; ch++) {
                var sChildren = sNode.children[ch];
                // var sInfo = parent.parent.findNode(sChildren.id);
                //
                // var pnInfos = getPnList(sInfo);

                var pnInfos = sChildren.attributes.master;

                paramNode.push(pnInfos);
                categories.push(sChildren.text);
            }
        }

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

                // var sInfo = parent.parent.findNode(sChildren.id);
                //
                // var pnInfos = getPnList(sInfo);

                var pnInfos = sChildren.attributes.master;

                paramNode.push(pnInfos);
                categories.push(sChildren.text);
            }
        }

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

                            var lastMonth = 0;

                            for (var j = 0; j < r.data[i][1].length; j++) {
                                lastMonth += parseFloat(r.data[i][1][j].totalpositiveactivepower) * ChartUtils.NUM_FIX;
                            }

                            lastMonth = lastMonth / ChartUtils.NUM_FIX;

                            var lastYear = 0;

                            for (var j = 0; j < r.data[i][2].length; j++) {
                                lastYear += parseFloat(r.data[i][2][j].totalpositiveactivepower) * ChartUtils.NUM_FIX;
                            }

                            lastYear = lastYear / ChartUtils.NUM_FIX;

                            dgData.push({
                                name: categories[i],
                                electricity: thisMonth,
                                rate1: lastMonth == 0 ? null : ((thisMonth - lastMonth) / lastMonth).toFixed(1),
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