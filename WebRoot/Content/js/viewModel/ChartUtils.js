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

}