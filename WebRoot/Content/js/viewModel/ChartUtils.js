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
                            tmp = Math.floor(tmp * 100) / 100;
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
                                tmp = Math.floor(tmp * 100) / 100;
                            }
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
                            tmp = Math.floor(tmp * 100) / 100;
                        }
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
                            tmp = Math.floor(tmp * 100) / 100;
                        }
                    }
                }
            }
            series.data.push(tmp);

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
                            tmp = Math.floor(tmp * 100) / 100;
                        }
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
                            tmp = Math.floor(tmp * 100) / 100;
                        }
                    }
                }
            }
            series.data.push(tmp);

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
                            tmp = Math.floor(tmp * 100) / 100;
                        }
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
                            tmp = Math.floor(tmp * 100) / 100;
                        }
                    }
                }
            }
            series.data.push(tmp);

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
                            tmp = Math.floor(tmp * 100) / 100;
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    },
    getDailyCategories: function () {
        var categories = [];
        for (var i = 0; i < 24; i++) {
            categories.push(i);
        }
        return categories;
    },
    getMonthCategories: function (date) {
        var y = date.getFullYear();
        var m = date.getMonth();

        var categories = [];
        for (var i = 0; i < TimeUtils.getMonthDays(new Date(y, m)); i++) {
            categories.push((i + 1));
        }

        return categories;
    }
}