/**
 * Created by barrie on 17/1/29.
 */
var ChartUtils = {
    getLoadDailySeries: function (node, time, data) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            data: []
        };

        for (var t = 0; t < 24; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 8) == data[i].clientoperationtime.substr(0, 8)) {
                        if (parseInt(data[i].hourClientOperationTime) == t) {
                            tmp = parseFloat(data[i].maxTotalActivePower) * node.pt * node.ct;
                            tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getLoadDailySumSeries: function (nodes, time, data) {
        var series = {
            name: time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2),
            data: []
        };

        for (var t = 0; t < 24; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < nodes.length; j++) {

                    if (data[i].areaId == nodes[j].areaId && data[i].concentratorId == nodes[j].concentratorId && data[i].pn == nodes[j].pn) {
                        if (time.substr(0, 8) == data[i].clientoperationtime.substr(0, 8)) {
                            if (parseInt(data[i].hourClientOperationTime) == t) {
                                if (tmp == null) {
                                    tmp = parseFloat(data[i].maxTotalActivePower) * nodes[j].pt * nodes[j].ct;
                                } else {
                                    tmp += parseFloat(data[i].maxTotalActivePower) * nodes[j].pt * nodes[j].ct;
                                }
                                tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                            }
                        }
                    }
                }

            }
            series.data.push(tmp);

        }

        return series;
    },
    getLoadDailyIntervalDaySeries: function (name, nodes, time, interval, data, type) {
        var series = {
            name: name,
            data: []
        };

        var category = this.getDailyIntervalDayCategories(time, interval);
        for (var t = 0; t < category.length; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < nodes.length; j++) {
                    if (data[i].areaId == nodes[j].areaId && data[i].concentratorId == nodes[j].concentratorId && data[i].pn == nodes[j].pn) {
                        if (parseInt(data[i].dayClientOperationTime) == parseInt(category[t])) {
                            if (tmp == null) {
                                tmp = parseFloat(data[i][type]) * nodes[j].pt * nodes[j].ct;
                            } else {
                                tmp += parseFloat(data[i][type]) * nodes[j].pt * nodes[j].ct;
                            }
                            tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                        }
                    }
                }
            }

            series.data.push(tmp);
        }

        return series;
    },
    getLoadMonthlyIntervalMonthSeries: function (name, nodes, time, interval, data, type) {
        var series = {
            name: name,
            data: []
        };

        var category = this.getMonthlyIntervalMonthCategories(time, interval);
        for (var t = 0; t < category.length; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < nodes.length; j++) {
                    if (data[i].areaId == nodes[j].areaId && data[i].concentratorId == nodes[j].concentratorId && data[i].pn == nodes[j].pn) {
                        if (parseInt(data[i].monthClientOperationTime) == parseInt(category[t])) {
                            if (tmp == null) {
                                tmp = parseFloat(data[i][type]) * nodes[j].pt * nodes[j].ct;
                            } else {
                                tmp += parseFloat(data[i][type]) * nodes[j].pt * nodes[j].ct;
                            }
                            tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                        }
                    }
                }
            }

            series.data.push(tmp);
        }

        return series;
    },
    getLoadDailyDetailSeries: function (node, time, data) {
        var series = {
            name: time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2),
            data: []
        };

        for (var t = 0; t < 24; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 8) == data[i].clientoperationtime.substr(0, 8)) {
                        if (parseInt(data[i].hourClientOperationTime) == t) {
                            tmp = parseFloat(data[i].maxTotalActivePower) * node.pt * node.ct;
                            tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getLoadAllSeries: function (node, time, data) {
        var series = {
            name: time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2),
            data: []
        };

        for (var i = 0; i < data.length; i++) {
            if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                var tmp = parseFloat(data[i].totalactivepower) * node.pt * node.ct;
                tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                series.data.push([TimeUtils.dbTimeToDate(data[i].clientoperationtime), tmp]);
            }
        }

        return series;
    },
    getLoadWeeklyDetailSeries: function (node, time, data, type) {
        var y = parseInt(time.substr(0, 4));
        var m = parseInt(time.substr(4, 2)) - 1;
        var d = parseInt(time.substr(6, 2));
        var s = new Date(y, m, d);

        var w = TimeUtils.weekFromODBC(s.getDay());

        var firstDay = new Date(y, m, d);
        firstDay.setDate(s.getDate() - w);
        var lastDay = new Date(y, m, d);
        lastDay.setDate(s.getDate() + (6 - w));


        var series = {
            name: node.name + "(" + firstDay.format("yyyy-MM-dd") + "~" + lastDay.format("yyyy-MM-dd") + ")",
            data: []
        };

        for (var t = 0; t < 7; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (parseInt(data[i].dayClientOperationTime) == t) {
                        tmp = parseFloat(data[i][type]) * node.pt * node.ct;
                        tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getLoadMonthlyDetailSeries: function (node, time, data, type) {
        var y = time.substr(0, 4);
        var m = time.substr(4, 2);
        var series = {
            name: node.name + "(" + y + "-" + m + ")",
            data: []
        };

        // alert(TimeUtils.getMonthDays(new Date(parseInt(y), (parseInt(m) - 1))))

        for (var t = 0; t < TimeUtils.getMonthDays(new Date(parseInt(y), (parseInt(m) - 1))); t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 6) == data[i].clientoperationtime.substr(0, 6)) {
                        if (parseInt(data[i].dayClientOperationTime) == (t + 1)) {
                            tmp = parseFloat(data[i][type]) * node.pt * node.ct;
                            tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getVoltageAllSeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            data: []
        };

        for (var i = 0; i < data.length; i++) {
            if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                var tmp = parseFloat(data[i][phase]) * node.pt;
                tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                series.data.push([TimeUtils.dbTimeToDate(data[i].clientoperationtime), tmp]);
            }
        }
        return series;
    },
    getVoltageDailySeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            data: []
        };

        for (var t = 0; t < 24; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 8) == data[i].clientoperationtime.substr(0, 8)) {
                        if (parseInt(data[i].hourClientOperationTime) == t) {
                            tmp = parseFloat(data[i][phase]) * node.pt;
                            tmp = DataGridUtils.floatFormatter(tmp, 1, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getVoltageWeeklyDetailSeries: function (node, time, data, type) {
        var y = parseInt(time.substr(0, 4));
        var m = parseInt(time.substr(4, 2)) - 1;
        var d = parseInt(time.substr(6, 2));
        var s = new Date(y, m, d);

        var w = TimeUtils.weekFromODBC(s.getDay());

        var firstDay = new Date(y, m, d);
        firstDay.setDate(s.getDate() - w);
        var lastDay = new Date(y, m, d);
        lastDay.setDate(s.getDate() + (6 - w));


        var series = {
            name: node.name + "(" + firstDay.format("yyyy-MM-dd") + "~" + lastDay.format("yyyy-MM-dd") + ")",
            data: []
        };

        for (var t = 0; t < 7; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (parseInt(data[i].dayClientOperationTime) == t) {
                        tmp = parseFloat(data[i][type]) * node.pt;
                        tmp = DataGridUtils.floatFormatter(tmp, 1, true);
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getVoltageMonthlySeries: function (node, time, data, phase) {
        var y = time.substr(0, 4);
        var m = time.substr(4, 2);
        var series = {
            name: node.name + "(" + y + "-" + m + ")",
            data: []
        };

        for (var t = 0; t < TimeUtils.getMonthDays(new Date(parseInt(y), (parseInt(m) - 1))); t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 6) == data[i].clientoperationtime.substr(0, 6)) {
                        if (parseInt(data[i].dayClientOperationTime) == (t + 1)) {
                            tmp = parseFloat(data[i][phase]) * node.pt;
                            tmp = DataGridUtils.floatFormatter(tmp, 1, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getCurrentAllSeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            data: []
        };

        for (var i = 0; i < data.length; i++) {
            if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                var tmp = parseFloat(data[i][phase]) * node.ct;
                tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                series.data.push([TimeUtils.dbTimeToDate(data[i].clientoperationtime), tmp]);
            }
        }
        return series;
    },
    getCurrentDailySeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            data: []
        };

        for (var t = 0; t < 24; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 8) == data[i].clientoperationtime.substr(0, 8)) {
                        if (parseInt(data[i].hourClientOperationTime) == t) {
                            tmp = parseFloat(data[i][phase]) * node.ct;
                            tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getCurrentWeeklyDetailSeries: function (node, time, data, type) {
        var y = parseInt(time.substr(0, 4));
        var m = parseInt(time.substr(4, 2)) - 1;
        var d = parseInt(time.substr(6, 2));
        var s = new Date(y, m, d);

        var w = TimeUtils.weekFromODBC(s.getDay());

        var firstDay = new Date(y, m, d);
        firstDay.setDate(s.getDate() - w);
        var lastDay = new Date(y, m, d);
        lastDay.setDate(s.getDate() + (6 - w));


        var series = {
            name: node.name + "(" + firstDay.format("yyyy-MM-dd") + "~" + lastDay.format("yyyy-MM-dd") + ")",
            data: []
        };

        for (var t = 0; t < 7; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (parseInt(data[i].dayClientOperationTime) == t) {
                        tmp = parseFloat(data[i][type]) * node.ct;
                        tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getCurrentMonthlySeries: function (node, time, data, phase) {
        var y = time.substr(0, 4);
        var m = time.substr(4, 2);
        var series = {
            name: node.name + "(" + y + "-" + m + ")",
            data: []
        };

        for (var t = 0; t < TimeUtils.getMonthDays(new Date(parseInt(y), (parseInt(m) - 1))); t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 6) == data[i].clientoperationtime.substr(0, 6)) {
                        if (parseInt(data[i].dayClientOperationTime) == (t + 1)) {
                            tmp = parseFloat(data[i][phase]) * node.pt;
                            tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getPowerFactorAllSeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            data: []
        };

        for (var i = 0; i < data.length; i++) {
            if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                var tmp = parseFloat(data[i][phase]);
                tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                series.data.push([TimeUtils.dbTimeToDate(data[i].clientoperationtime), tmp]);
            }
        }
        return series;
    },
    getPowerFactorDailySeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            data: []
        };

        for (var t = 0; t < 24; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 8) == data[i].clientoperationtime.substr(0, 8)) {
                        if (parseInt(data[i].hourClientOperationTime) == t) {
                            tmp = parseFloat(data[i][phase]);
                            tmp = DataGridUtils.floatFormatter(tmp, 1, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getPowerFactorWeeklyDetailSeries: function (node, time, data, type) {
        var y = parseInt(time.substr(0, 4));
        var m = parseInt(time.substr(4, 2)) - 1;
        var d = parseInt(time.substr(6, 2));
        var s = new Date(y, m, d);

        var w = TimeUtils.weekFromODBC(s.getDay());

        var firstDay = new Date(y, m, d);
        firstDay.setDate(s.getDate() - w);
        var lastDay = new Date(y, m, d);
        lastDay.setDate(s.getDate() + (6 - w));


        var series = {
            name: node.name + "(" + firstDay.format("yyyy-MM-dd") + "~" + lastDay.format("yyyy-MM-dd") + ")",
            data: []
        };

        for (var t = 0; t < 7; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (parseInt(data[i].dayClientOperationTime) == t) {
                        tmp = parseFloat(data[i][type]);
                        tmp = DataGridUtils.floatFormatter(tmp, 1, true);
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getPowerFactorMonthlySeries: function (node, time, data, phase) {
        var y = time.substr(0, 4);
        var m = time.substr(4, 2);
        var series = {
            name: node.name + "(" + y + "-" + m + ")",
            data: []
        };

        for (var t = 0; t < TimeUtils.getMonthDays(new Date(parseInt(y), (parseInt(m) - 1))); t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 6) == data[i].clientoperationtime.substr(0, 6)) {
                        if (parseInt(data[i].dayClientOperationTime) == (t + 1)) {
                            tmp = parseFloat(data[i][phase]) * node.pt;
                            tmp = DataGridUtils.floatFormatter(tmp, 1, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getElectricityDailySeries: function (node, time, data) {
        var series = {
            name: time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2),
            data: []
        };

        for (var t = 0; t < 24; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 8) == data[i].clientoperationtime.substr(0, 8)) {
                        if (parseInt(data[i].hourClientOperationTime) == t) {
                            if (i == 0) {
                                tmp = (parseFloat(data[i]["lastTotalPositiveActivePower"]) - parseFloat(data[i]["firstTotalPositiveActivePower"])) * node.ct * node.pt;
                            } else {
                                tmp = (parseFloat(data[i]["lastTotalPositiveActivePower"]) - parseFloat(data[(i - 1)]["lastTotalPositiveActivePower"])) * node.ct * node.pt;
                            }
                            tmp = DataGridUtils.floatFormatter(tmp, 4, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getElectricityDailyIntervalDaySeries: function (name, nodes, time, interval, data) {
        var startTime = TimeUtils.dataBoxTimeToDate(time);
        var endTime = TimeUtils.dataBoxTimeToDate(time);
        endTime.setDate(endTime.getDate() + interval);

        var series = {
            name: startTime.format("yyyy-MM-dd") + "~" + endTime.format("yyyy-MM-dd"),
            data: []
        };

        var category = this.getDailyIntervalDayCategories(time, interval);

        var nData = [];

        for (var i = 0; i < data.length; i++) {
            for (var j = 0; j < nodes.length; j++) {
                if (data[i].areaId == nodes[j].areaId && data[i].concentratorId == nodes[j].concentratorId && data[i].pn == nodes[j].pn) {
                    var item = $.extend(data[i], nodes[j]);
                    nData.push(item);
                }
            }
        }

        var cData = {};

        for (var i = 0; i < nData.length; i++) {
            var key = nData[i].areaId + " " + nData[i].concentratorId + " " + nData[i].pn;
            if (!cData.hasOwnProperty(key)) {
                cData[key] = [];
            }
            cData[key].push(
                nData[i]
            );
        }

        var sData = {};
        $.each(cData, function (k, n) {
            var key = k;

            if (!sData.hasOwnProperty(key)) {
                sData[key] = [];
            }
            for (i = 0; i < n.length; i++) {
                if (i == 0) {
                    sData[key].push(
                        (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i]["firstTotalPositiveActivePower"])) * n[i].ct * n[i].pt
                    );
                } else {
                    sData[key].push(
                        (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i - 1]["lastTotalPositiveActivePower"])) * n[i].ct * n[i].pt
                    );
                }
            }
        });


        $.each(sData, function (k, n) {
            if (series.data == 0) {
                for (i = 0; i < n.length; i++) {
                    series.data.push(0);
                }
            }

            for (i = 0; i < n.length; i++) {
                series.data[i] = DataGridUtils.floatFormatter((series.data[i] + n[i]), 4), true;
            }
        });


        return series;
    },
    getElectricityWeeklySeries: function (node, time, data) {
        var y = parseInt(time.substr(0, 4));
        var m = parseInt(time.substr(4, 2)) - 1;
        var d = parseInt(time.substr(6, 2));
        var s = new Date(y, m, d);

        var w = TimeUtils.weekFromODBC(s.getDay());

        var firstDay = new Date(y, m, d);
        firstDay.setDate(s.getDate() - w);
        var lastDay = new Date(y, m, d);
        lastDay.setDate(s.getDate() + (6 - w));

        var series = {
            name: firstDay.format("yyyy-MM-dd") + "~" + lastDay.format("yyyy-MM-dd"),
            data: []
        };

        for (var t = 0; t < 7; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (parseInt(data[i].dayClientOperationTime) == t) {
                        if (i == 0) {
                            tmp = (parseFloat(data[i]["lastTotalPositiveActivePower"]) - parseFloat(data[i]["firstTotalPositiveActivePower"])) * node.ct * node.pt;
                        } else {
                            tmp = (parseFloat(data[i]["lastTotalPositiveActivePower"]) - parseFloat(data[(i - 1)]["lastTotalPositiveActivePower"])) * node.ct * node.pt;
                        }
                        tmp = DataGridUtils.floatFormatter(tmp, 4, true);
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    }
    ,
    getElectricityMonthlySeries: function (node, time, data) {
        var y = time.substr(0, 4);
        var m = time.substr(4, 2);
        var series = {
            name: y + "-" + m,
            data: []
        };

        for (var t = 0; t < TimeUtils.getMonthDays(new Date(parseInt(y), (parseInt(m) - 1))); t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                    if (time.substr(0, 6) == data[i].clientoperationtime.substr(0, 6)) {
                        if (parseInt(data[i].dayClientOperationTime) == (t + 1)) {
                            if (i == 0) {
                                tmp = (parseFloat(data[i]["lastTotalPositiveActivePower"]) - parseFloat(data[i]["firstTotalPositiveActivePower"])) * node.ct * node.pt;
                            } else {
                                tmp = (parseFloat(data[i]["lastTotalPositiveActivePower"]) - parseFloat(data[(i - 1)]["lastTotalPositiveActivePower"])) * node.ct * node.pt;
                            }
                            tmp = DataGridUtils.floatFormatter(tmp, 4, true);
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    }
    ,
    getDailyCategories: function () {
        var categories = [];
        for (var i = 0; i < 24; i++) {
            categories.push(fixNum(i, 2) + ":00");
        }
        return categories;
    }
    ,
    getDailyIntervalDayCategories: function (time, interval) {
        var categories = [];
        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;
        var d = parseInt(ss[2], 10);

        for (var i = 0; i < (interval + 1); i++) {
            var dt = new Date(y, m, d);
            dt.setDate(dt.getDate() + i);
            categories.push(dt.getDate() + "日");
        }

        return categories;
    }
    ,
    getMonthlyIntervalMonthCategories: function (time, interval) {
        var categories = [];
        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;

        for (var i = 0; i < (interval + 1); i++) {
            var dt = new Date(y, m);
            dt.setMonth(dt.getMonth() + i);
            categories.push(dt.getMonth() + 1 + "月");
        }

        return categories;
    }
    ,
    getWeeklyCategories: function () {
        var categories = [];
        var week = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
        for (var i = 0; i < 7; i++) {
            categories.push(week[i]);
        }
        return categories;
    }
    ,
    getMonthCategories: function (date) {
        var y = date.getFullYear();
        var m = date.getMonth();

        var categories = [];
        for (var i = 0; i < TimeUtils.getMonthDays(new Date(y, m)); i++) {
            categories.push(((i + 1) + "日"));
        }

        return categories;
    }
}