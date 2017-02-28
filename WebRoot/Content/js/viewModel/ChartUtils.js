/**
 * Created by barrie on 17/1/29.
 */
var ChartUtils = {
    MAX_CHART_NUMBER: 1000000000.0,
    MIN_CHART_NUMBER: -1000000000.0,
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
    getLoadDailySumIndexSeries: function (name, nodes, time, data) {
        var series = {
            name: name,
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
    getLoadDailySumIndexMax: function (nodes, time, data) {
        var series = [];

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
            series.push([tmp, t]);

        }

        var maxLoad = [ChartUtils.MIN_CHART_NUMBER, ""];
        for (var i = 0; i < series.length; i++) {
            if (series[i][0] > maxLoad[0]) {
                maxLoad[0] = series[i][0];
                maxLoad[1] = series[i][1];
            }
        }

        return maxLoad;
    },
    getLoadDailyIntervalDaySeries: function (name, nodes, time, interval, data, type) {
        var series = {
            name: name,
            data: []
        };

        var category = this.getDateTimeByDateCategories(time, interval);
        for (var t = 0; t < category.length; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < nodes.length; j++) {
                    if (data[i].areaId == nodes[j].areaId && data[i].concentratorId == nodes[j].concentratorId && data[i].pn == nodes[j].pn) {
                        if (data[i].dayClientOperationTime == (TimeUtils.dateToDbTime(category[t]) + "").substring(0, 8)) {
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

            series.data.push([category[t].format("MM-dd"), tmp]);
        }

        return series;
    },
    getLoadMonthlyIntervalMonthSeries: function (name, nodes, time, interval, data, type) {
        var series = {
            name: name,
            data: []
        };


        var category = this.getDateTimeByMonthCategories(time, interval);

        for (var t = 0; t < category.length; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {
                for (var j = 0; j < nodes.length; j++) {
                    if (data[i].areaId == nodes[j].areaId && data[i].concentratorId == nodes[j].concentratorId && data[i].pn == nodes[j].pn) {
                        if (data[i].monthClientOperationTime == (TimeUtils.dateToDbTime(category[t]) + "").substring(0, 6)) {
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

            series.data.push([category[t].format("yyyy-MM"), tmp]);
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
                series.data.push([TimeUtils.dbTimeToUTC(data[i].clientoperationtime), tmp]);
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
            color: node.color,
            data: []
        };

        for (var i = 0; i < data.length; i++) {
            if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                var tmp = parseFloat(data[i][phase]) * node.pt;
                tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                series.data.push([TimeUtils.dbTimeToUTC(data[i].clientoperationtime), tmp]);
            }
        }
        return series;
    },
    getVoltageDailySeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            color: node.color,
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
            color: node.color,
            data: []
        };

        for (var i = 0; i < data.length; i++) {
            if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                var tmp = parseFloat(data[i][phase]) * node.ct;
                tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                series.data.push([TimeUtils.dbTimeToUTC(data[i].clientoperationtime), tmp]);
            }
        }
        return series;
    },
    getCurrentDailySeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            color: node.color,
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
            color: node.color,
            data: []
        };

        for (var i = 0; i < data.length; i++) {
            if (data[i].areaId == node.areaId && data[i].concentratorId == node.concentratorId && data[i].pn == node.pn) {
                var tmp = parseFloat(data[i][phase]);
                tmp = DataGridUtils.floatFormatter(tmp, 3, true);
                series.data.push([TimeUtils.dbTimeToUTC(data[i].clientoperationtime), tmp]);
            }
        }
        return series;
    },
    getPowerFactorDailySeries: function (node, time, data, phase) {
        var series = {
            name: node.name + "(" + time.substr(0, 4) + "-" + time.substr(4, 2) + "-" + time.substr(6, 2) + ")",
            color: node.color,
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
        var series = {
            name: name,
            data: []
        };

        var category = this.getDateTimeByDateCategories(time, interval);

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
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i]["firstTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                } else {
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i - 1]["lastTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                }
            }
        });


        for (var t = 0; t < category.length; t++) {
            series.data.push([category[t].format("MM-dd"), 0]);
        }

        $.each(sData, function (k, n) {
            for (var t = 0; t < category.length; t++) {
                for (i = 0; i < n.length; i++) {
                    if (category[t].format("yyyyMMdd") == n[i].key) {
                        series.data[t][1] = DataGridUtils.floatFormatter((series.data[t][1] + n[i].value), 4, true);
                    }
                }
            }
        });

        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", JSON.stringify(category));
        // $.messager.alert("操作提示", JSON.stringify(sData));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return series;
    },
    getElectricityDailyIntervalDayIndexSeries: function (name, nodes, time, interval, data) {
        var series = {
            name: name,
            data: []
        };

        var category = this.getDateTimeByDateCategories(time, interval);

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
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i]["firstTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                } else {
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i - 1]["lastTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                }
            }
        });


        for (var t = 0; t < category.length; t++) {
            series.data.push([category[t].format("d") + "日", 0]);
        }

        $.each(sData, function (k, n) {
            for (var t = 0; t < category.length; t++) {
                for (i = 0; i < n.length; i++) {
                    if (category[t].format("yyyyMMdd") == n[i].key) {
                        series.data[t][1] = DataGridUtils.floatFormatter((series.data[t][1] + n[i].value), 4, true);
                    }
                }
            }
        });

        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", JSON.stringify(category));
        // $.messager.alert("操作提示", JSON.stringify(sData));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return series;
    },
    getElectricityDailyIntervalDayIndexSum: function (nodes, time, interval, data) {
        var category = this.getDateTimeByDateCategories(time, interval);

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
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i]["firstTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                } else {
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i - 1]["lastTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                }
            }
        });


        var total = [];

        for (var t = 0; t < category.length; t++) {
            total.push(0);
        }

        $.each(sData, function (k, n) {
            for (var t = 0; t < category.length; t++) {
                for (i = 0; i < n.length; i++) {
                    if (category[t].format("yyyyMMdd") == n[i].key) {
                        total[t] = +DataGridUtils.floatFormatter((total[t] + n[i].value), 4, true);
                    }
                }
            }
        });

        var sumTotal = 0
        for (var t = 0; t < total.length; t++) {
            sumTotal = sumTotal + total[t];
        }

        return sumTotal;
    },
    getElectricityDailyIntervalDayTable: function (nodes, time, interval, data) {
        var tbData = [];

        var category = this.getDateTimeByDateCategories(time, interval);

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
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i]["firstTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                } else {
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i - 1]["lastTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                }
            }
        });


        for (var t = 0; t < category.length; t++) {
            tbData.push(0);
        }

        $.each(sData, function (k, n) {
            for (var t = 0; t < category.length; t++) {
                for (i = 0; i < n.length; i++) {
                    if (category[t].format("yyyyMMdd") == n[i].key) {
                        tbData[t] = DataGridUtils.floatFormatter((tbData[t] + n[i].value), 4, true);
                    }
                }
            }
        });

        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", JSON.stringify(category));
        // $.messager.alert("操作提示", JSON.stringify(sData));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return tbData;
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
    },
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
    },
    getElectricityMonthlyIntervalMonthSeries: function (name, nodes, time, interval, data) {
        var series = {
            name: name,
            data: []
        };

        var category = this.getDateTimeByMonthCategories(time, interval);

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
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i]["firstTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 6)
                    });
                } else {
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i - 1]["lastTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 6)
                    });
                }
            }
        });


        for (var t = 0; t < category.length; t++) {
            series.data.push([category[t].format("yyyy-MM"), 0]);
        }

        $.each(sData, function (k, n) {
            for (var t = 0; t < category.length; t++) {
                for (i = 0; i < n.length; i++) {
                    if (category[t].format("yyyyMM") == parseInt(n[i].key)) {
                        series.data[t][1] = DataGridUtils.floatFormatter((series.data[t][1] + n[i].value), 4, true);
                    }
                }
            }
        });

        // $.messager.alert("操作提示", JSON.stringify(nData));
        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", JSON.stringify(category));
        // $.messager.alert("操作提示", JSON.stringify(sData));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return series;
    },
    getElectricityMonthlyIntervalMonthTable: function (nodes, time, interval, data) {
        var tbData = [];

        var category = this.getDateTimeByMonthCategories(time, interval);

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
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i]["firstTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 6)
                    });
                } else {
                    sData[key].push({
                        value: (parseFloat(n[i]["lastTotalPositiveActivePower"]) - parseFloat(n[i - 1]["lastTotalPositiveActivePower"])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 6)
                    });
                }
            }
        });


        for (var t = 0; t < category.length; t++) {
            tbData.push(0);
        }

        $.each(sData, function (k, n) {
            for (var t = 0; t < category.length; t++) {
                for (i = 0; i < n.length; i++) {
                    if (category[t].format("yyyyMM") == parseInt(n[i].key)) {
                        tbData[t] = DataGridUtils.floatFormatter((tbData[t] + n[i].value), 4, true);
                    }
                }
            }
        });

        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", JSON.stringify(category));
        // $.messager.alert("操作提示", JSON.stringify(sData));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return tbData;
    },
    getElectricityMonthlyRateSeqSeries: function (name, nodes, time, interval, data, type) {
        var category = this.getDateTimeByDateCategories(time, interval);

        var series = {
            name: name,
            data: []
        };

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
                    sData[key].push({
                        value: (parseFloat(n[i]["maxrateseq" + type]) - parseFloat(n[i]["minrateseq" + type]) ) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                } else {
                    sData[key].push({
                        value: (parseFloat(n[i]["maxrateseq" + type]) - parseFloat(n[i - 1]["maxrateseq" + type])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 8)
                    });
                }
            }
        });


        for (var t = 0; t < category.length; t++) {
            series.data.push([category[t].format("MM-dd"), 0]);
        }

        $.each(sData, function (k, n) {
            for (var t = 0; t < category.length; t++) {
                for (i = 0; i < n.length; i++) {
                    if (category[t].format("yyyyMMdd") == n[i].key) {
                        series.data[t][1] = DataGridUtils.floatFormatter((series.data[t][1] + n[i].value), 4, true);
                    }
                }
            }
        });

        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", JSON.stringify(category));
        // $.messager.alert("操作提示", JSON.stringify(sData));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return series;
    },
    getElectricityRateSeqBarSeries: function (name, nodes, time, interval, data) {
        var series = {
            name: name,
            data: []
        };

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
                sData[key] = {
                    maxSeq1: ChartUtils.MIN_CHART_NUMBER,
                    maxSeq2: ChartUtils.MIN_CHART_NUMBER,
                    maxSeq3: ChartUtils.MIN_CHART_NUMBER,
                    maxSeq4: ChartUtils.MIN_CHART_NUMBER,
                    minSeq1: ChartUtils.MAX_CHART_NUMBER,
                    minSeq2: ChartUtils.MAX_CHART_NUMBER,
                    minSeq3: ChartUtils.MAX_CHART_NUMBER,
                    minSeq4: ChartUtils.MAX_CHART_NUMBER
                };
            }
            for (i = 0; i < n.length; i++) {
                for (var j = 1; j <= 4; j++) {
                    if ((parseFloat(n[i]["maxrateseq" + j]) * n[i].ct * n[i].pt) > sData[key]["maxSeq" + j]) {
                        sData[key]["maxSeq" + j] = parseFloat(n[i]["maxrateseq" + j] * n[i].ct * n[i].pt);
                    }

                    if ((parseFloat(nData[i]["minrateseq" + j]) * n[i].ct * n[i].pt) < sData[key]["minSeq" + j]) {
                        sData[key]["minSeq" + j] = parseFloat(n[i]["minrateseq" + j] * n[i].ct * n[i].pt);
                    }
                }
            }
        });

        var seq = {
            seq1: 0,
            seq2: 0,
            seq3: 0,
            seq4: 0
        };

        $.each(sData, function (k, n) {
            for (var j = 1; j <= 4; j++) {
                seq["seq" + j] = seq["seq" + j] + (n["maxSeq" + j] - n["minSeq" + j]);
            }
        });

        // for (var j = 1; j <= 4; j++) {
        //     series.data.push(seq["seq" + j]);
        // }

        var seqTotal = 0.0;
        for (var j = 1; j <= 4; j++) {
            seqTotal = seqTotal + seq["seq" + j];
        }

        for (var j = 1; j <= 4; j++) {
            series.data.push(seqTotal == 0 ? 0 : DataGridUtils.floatFormatter((seq["seq" + j] * 100) / seqTotal, 1, true));
        }

        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", seqTotal);
        // $.messager.alert("操作提示", JSON.stringify(seq));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return series;
    },
    getElectricityRateSeqPieSeries: function (name, nodes, time, interval, data) {
        var series = {
            type: "pie",
            name: name,
            data: []
        };

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
                sData[key] = {
                    maxSeq1: ChartUtils.MIN_CHART_NUMBER,
                    maxSeq2: ChartUtils.MIN_CHART_NUMBER,
                    maxSeq3: ChartUtils.MIN_CHART_NUMBER,
                    maxSeq4: ChartUtils.MIN_CHART_NUMBER,
                    minSeq1: ChartUtils.MAX_CHART_NUMBER,
                    minSeq2: ChartUtils.MAX_CHART_NUMBER,
                    minSeq3: ChartUtils.MAX_CHART_NUMBER,
                    minSeq4: ChartUtils.MAX_CHART_NUMBER
                };
            }
            for (i = 0; i < n.length; i++) {
                for (var j = 1; j <= 4; j++) {
                    if ((parseFloat(n[i]["maxrateseq" + j]) * n[i].ct * n[i].pt) > sData[key]["maxSeq" + j]) {
                        sData[key]["maxSeq" + j] = parseFloat(n[i]["maxrateseq" + j] * n[i].ct * n[i].pt);
                    }

                    if ((parseFloat(nData[i]["minrateseq" + j]) * n[i].ct * n[i].pt) < sData[key]["minSeq" + j]) {
                        sData[key]["minSeq" + j] = parseFloat(n[i]["minrateseq" + j] * n[i].ct * n[i].pt);
                    }
                }
            }
        });

        var seq = {
            seq1: 0,
            seq2: 0,
            seq3: 0,
            seq4: 0
        };

        $.each(sData, function (k, n) {
            for (var j = 1; j <= 4; j++) {
                seq["seq" + j] = seq["seq" + j] + (n["maxSeq" + j] - n["minSeq" + j]);
            }
        });

        var category = this.getElectricityRateSeqCategories();

        for (var i = 0; i < category.length; i++) {
            series.data.push([
                category[i],
                seq["seq" + (i + 1)]
            ]);
        }
        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", seqTotal);
        // $.messager.alert("操作提示", JSON.stringify(seq));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return series;
    },
    getElectricityYearlyRateSeqSeries: function (name, nodes, time, interval, data, type) {
        var category = this.getDateTimeByMonthCategories(time, interval);

        var series = {
            name: name,
            data: []
        };

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
                    sData[key].push({
                        value: (parseFloat(n[i]["maxrateseq" + type]) - parseFloat(n[i]["minrateseq" + type]) ) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 6)
                    });
                } else {
                    sData[key].push({
                        value: (parseFloat(n[i]["maxrateseq" + type]) - parseFloat(n[i - 1]["maxrateseq" + type])) * n[i].ct * n[i].pt,
                        key: (n[i].clientoperationtime + "").substring(0, 6)
                    });
                }
            }
        });


        for (var t = 0; t < category.length; t++) {
            series.data.push([category[t].format("yyyy-MM"), 0]);
        }

        $.each(sData, function (k, n) {
            for (var t = 0; t < category.length; t++) {
                for (i = 0; i < n.length; i++) {
                    if (category[t].format("yyyyMM") == parseInt(n[i].key)) {
                        series.data[t][1] = DataGridUtils.floatFormatter((series.data[t][1] + n[i].value), 4, true);
                    }
                }
            }
        });

        // $.messager.alert("操作提示", JSON.stringify(cData));
        // $.messager.alert("操作提示", JSON.stringify(category));
        // $.messager.alert("操作提示", JSON.stringify(sData));
        // $.messager.alert("操作提示", JSON.stringify(series));

        return series;
    },

    getElectricityComparisonSeries: function (name, nodes, data) {
        var category = this.getElectricityComparisonCategories(nodes);

        var series = {
            name: name,
            data: []
        };

        for (var t = 0; t < category.length; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {

                if (category[t] == data[i].name) {
                    tmp = data[i].electricity;
                    tmp = DataGridUtils.floatFormatter(tmp, 4, true);
                }
            }
            series.data.push(tmp);

        }
        return series;
    },
    getElectricityComparisonPieSeries: function (name, nodes, data) {
        var series = {
            type: "pie",
            name: name,
            data: []
        };

        for (var i = 0; i < data.length; i++) {
            var sName = data[i].name;
            var sValue = data[i].electricity;
            sValue = DataGridUtils.floatFormatter(sValue, 4, true);
            series.data.push([
                sName,
                sValue
            ]);
        }

        return series;
    },
    getElectricityComparisonTable: function (nodes, data) {
        var category = this.getElectricityComparisonCategories(nodes);

        var series = [];

        for (var t = 0; t < category.length; t++) {
            var tmp = null;
            for (var i = 0; i < data.length; i++) {

                if (category[t] == data[i].name) {
                    tmp = data[i].electricity;
                    tmp = DataGridUtils.floatFormatter(tmp, 4, true);
                }
            }
            series.push(tmp);

        }
        var total = 0;
        for (var i = 0; i < series.length; i++) {
            total = total + series[i];
        }
        series.push(total);
        return series;
    },
    getDailyCategories: function () {
        var categories = [];
        for (var i = 0; i < 24; i++) {
            categories.push(fixNum(i, 2) + ":00");
        }
        return categories;
    },
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
    },
    getDailyIntervalDayStrCategories: function (time, interval) {
        var categories = [];
        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;
        var d = parseInt(ss[2], 10);

        for (var i = 0; i < (interval + 1); i++) {
            var dt = new Date(y, m, d);
            dt.setDate(dt.getDate() + i);
            categories.push(dt.format("yyyyMMdd"));
        }

        return categories;
    },
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
    },
    getElectricityRateSeqCategories: function () {
        var categories = ["尖电量", "峰电量", "平电量", "谷电量"];

        return categories;
    },
    getElectricityRateSeqBarCategories: function () {
        var categories = ["尖占比", "峰占比", "平占比", "谷占比"];

        return categories;
    },
    getWeeklyCategories: function () {
        var categories = [];
        var week = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
        for (var i = 0; i < 7; i++) {
            categories.push(week[i]);
        }
        return categories;
    },
    getMonthCategories: function (date) {
        var y = date.getFullYear();
        var m = date.getMonth();

        var categories = [];
        for (var i = 0; i < TimeUtils.getMonthDays(new Date(y, m)); i++) {
            categories.push(((i + 1) + "日"));
        }

        return categories;
    },
    getDateTimeByDateCategories: function (time, interval) {
        var categories = [];
        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;
        var d = parseInt(ss[2], 10);

        for (var i = 0; i < (interval + 1); i++) {
            var dt = new Date(y, m, d);
            dt.setDate(dt.getDate() + i);

            categories.push(dt);
        }

        return categories;
    },
    getDateTimeByMonthCategories: function (time, interval) {
        var categories = [];
        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;

        for (var i = 0; i < (interval + 1); i++) {
            var dt = new Date(y, m);
            dt.setMonth(dt.getMonth() + i);
            categories.push(dt);
        }

        return categories;
    },
    getElectricityComparisonCategories: function (data) {
        var categories = [];
        for (var i = 0; i < data.length; i++) {
            categories.push(data[i].name);
        }
        return categories;
    },

};