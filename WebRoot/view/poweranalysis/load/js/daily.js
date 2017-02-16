/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var DEFAULT_INTERVAL = 6;

    var _nodes = $.parseJSON($.base64.atob(decodeURIComponent(GetQueryString("data")), true));

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

    $("#dg-table").datagrid({
        singleSelect: true,
        rownumbers: true,
        fitColumns: true,
        columns: [[
            {
                field: "clientoperationtime",
                title: "日期",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var y = value.substr(0, 4);
                    var m = value.substr(4, 2);
                    var d = value.substr(6, 2);

                    return y + "-" + m + "-" + d;
                }
            },
            {
                field: "maxTotalActivePower",
                title: "最大负荷(kW)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(value);
                    var pt = row.pt;
                    var ct = row.ct;
                    t = t * pt * ct;
                    t = DataGridUtils.floatFormatter(t, 3);
                    return t;
                }
            },
            {
                field: "minTotalActivePower",
                title: "最小负荷(kW)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(value);
                    var pt = row.pt;
                    var ct = row.ct;
                    t = t * pt * ct;
                    t = DataGridUtils.floatFormatter(t, 3);
                    return t;
                }
            },
            {
                field: "avgTotalActivePower",
                title: "平均负荷(kW)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(value);
                    var pt = row.pt;
                    var ct = row.ct;
                    t = t * pt * ct;
                    t = DataGridUtils.floatFormatter(t, 3);
                    return t;
                }
            },
            {
                field: "differ",
                title: "峰谷差(kW)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(row.maxTotalActivePower) - parseFloat(row.minTotalActivePower);
                    var pt = row.pt;
                    var ct = row.ct;
                    t = t * pt * ct;
                    t = DataGridUtils.floatFormatter(t, 3);
                    return t;
                }
            },
            {
                field: "rate",
                title: "负荷率(%)",
                align: "center",
                width: 100,
                formatter: function (value, row, index) {
                    var t = parseFloat(row.avgTotalActivePower) / parseFloat(row.maxTotalActivePower);
                    t = t * 100;
                    t = DataGridUtils.floatFormatter(t, 1);
                    if (parseFloat(row.maxTotalActivePower) == 0) {
                        t = "-";
                    }
                    return t;
                }
            }
        ]]
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

            getLoadDetailChart({
                nodes: _nodes,
                time: $("#datebox-time-start").datebox("getValue"),
                interval: interval
            });
        }
    });

    init();

    function getLoadDetailChart(row) {
        var pnList = getPnDetail(row.nodes);


        var time = new Date().format("yyyy-MM-dd");
        var interval = 6;
        if (row.time != null && row.time != "") {
            time = row.time;
            interval = row.interval;
        }

        var paramChart = {
            node: pnList,
            time: []
        }

        for (var i = 0; i < (interval + 1); i++) {
            var ss = time.split('-');
            var y = parseInt(ss[0], 10);
            var m = parseInt(ss[1], 10) - 1;
            var d = parseInt(ss[2], 10);

            var dt = new Date(y, m, d);
            dt.setDate(dt.getDate() + i);
            paramChart.time.push(
                dt.format("yyyyMMdd") + "000000"
            );
        }

        $.ajax({
            url: _ctx + "poweranalysis/comparison/load/daily/interval/day/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        $("#dg-table").datagrid("loadData", getDgData(r.data, pnList));

                        getTbData(getDgData(r.data, pnList));

                        var series = [];

                        var item = ChartUtils.getLoadDailyIntervalDaySeries("最大", pnList, time, interval, r.data, "maxTotalActivePower");
                        series.push(item);

                        var item = ChartUtils.getLoadDailyIntervalDaySeries("最小", pnList, time, interval, r.data, "minTotalActivePower");
                        series.push(item);

                        var item = ChartUtils.getLoadDailyIntervalDaySeries("平均", pnList, time, interval, r.data, "avgTotalActivePower");
                        series.push(item);

                        var config = $.parseJSON($.ajax({
                            url: "data/loadDetailByDateChart.json?bust=" + new Date().getTime(),
                            type: "GET",
                            async: false
                        }).responseText);

                        // config.xAxis.categories = ChartUtils.getDateTimeByDateCategories(time, interval);
                        config.series = series;

                        $("#chart-load").highcharts(config);

                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                MaskUtil.mask();
            },
            error: function (request) {
                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
            },
            complete: function (XMLHttpRequest, textStatus) {
                MaskUtil.unmask();
            }
        });
    }

    function getTbData(data) {
        var tmp = {
            maxTotalActivePower: ChartUtils.MIN_CHART_NUMBER,
            maxTotalActivePowerTime: "",
            minTotalActivePower: ChartUtils.MAX_CHART_NUMBER,
            minTotalActivePowerTime: "",
            avgTotalActivePower: 0,
        };
        for (var i = 0; i < data.length; i++) {
            var max = parseFloat(data[i].maxTotalActivePower) * data[i].pt * data[i].ct;
            max = DataGridUtils.floatFormatter(max, 3);
            if (max > tmp.maxTotalActivePower) {
                tmp.maxTotalActivePower = max;
                tmp.maxTotalActivePowerTime = data[i].clientoperationtime;
            }

            var min = parseFloat(data[i].minTotalActivePower) * data[i].pt * data[i].ct;
            min = DataGridUtils.floatFormatter(min, 3);
            if (min < tmp.minTotalActivePower) {
                tmp.minTotalActivePower = min;
                tmp.minTotalActivePowerTime = data[i].clientoperationtime;
            }
            if (data[i].avgTotalActivePower != null) {
                var avg = parseFloat(data[i].avgTotalActivePower) * data[i].pt * data[i].ct;

                tmp.avgTotalActivePower += avg;
            }
        }
        tmp.avgTotalActivePower = tmp.avgTotalActivePower / parseFloat(data.length);
        tmp.avgTotalActivePower = DataGridUtils.floatFormatter(tmp.avgTotalActivePower, 3);


        $("#maxTotalActivePower").text(tmp.maxTotalActivePower + "(kW)");
        $("#minTotalActivePower").text(tmp.minTotalActivePower + "(kW)");
        $("#avgTotalActivePower").text(tmp.avgTotalActivePower + "(kW)");

        var maxTime = tmp.maxTotalActivePowerTime.substr(0, 4) + "-" + tmp.maxTotalActivePowerTime.substr(4, 2) + "-" + tmp.maxTotalActivePowerTime.substr(6, 2);
        $("#maxTotalActivePowerTime").text(maxTime);

        var minTime = tmp.minTotalActivePowerTime.substr(0, 4) + "-" + tmp.minTotalActivePowerTime.substr(4, 2) + "-" + tmp.minTotalActivePowerTime.substr(6, 2);
        $("#minTotalActivePowerTime").text(minTime);

        var differ = tmp.maxTotalActivePower - tmp.minTotalActivePower;
        differ = DataGridUtils.floatFormatter(differ, 3) + "(kW)";
        $("#differ").text(differ);

        var differRate = ((tmp.maxTotalActivePower - tmp.minTotalActivePower) / tmp.maxTotalActivePower) * 100
        differRate = DataGridUtils.floatFormatter(differRate, 1) + "(%)";
        if (tmp.maxTotalActivePower == 0) {
            differRate = "-";
        }
        $("#differRate").text(differRate);

        var loadRate = (tmp.avgTotalActivePower / tmp.maxTotalActivePower) * 100
        loadRate = DataGridUtils.floatFormatter(loadRate, 1) + "(%)";
        if (tmp.maxTotalActivePower == 0) {
            loadRate = "-";
        }
        $("#loadRate").text(loadRate);


        return tmp;
    }

    function getDgData(data, pnList) {
        var tmp = {};
        for (var i = 0; i < data.length; i++) {
            var day = data[i].clientoperationtime.substr(0, 8);
            if (!tmp.hasOwnProperty(day)) {
                tmp[day] = data[i];
            } else {
                var item = clone(tmp[day]);
                item.maxTotalActivePower = parseFloat(item.maxTotalActivePower) + parseFloat(data[i].maxTotalActivePower);
                item.minTotalActivePower = parseFloat(item.minTotalActivePower) + parseFloat(data[i].minTotalActivePower);
                item.avgTotalActivePower = parseFloat(item.avgTotalActivePower) + parseFloat(data[i].avgTotalActivePower);
                tmp[day] = item;
            }
        }
        var nData = [];
        $.each(tmp, function (i, n) {
            for (var j = 0; j < pnList.length; j++) {
                var target = clone(n);
                if (pnList[j].areaId == n.areaId && pnList[j].concentratorId == n.concentratorId && pnList[j].pn == n.pn) {
                    nData.push($.extend(target, pnList[j]));
                }
            }
        });
        return nData;
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

    function init() {
        var endDate = new Date();

        var startDate = new Date();
        startDate.setDate(startDate.getDate() - 6);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

        getLoadDetailChart({
            nodes: _nodes,
            time: $("#datebox-time-start").datebox("getValue"),
            interval: DEFAULT_INTERVAL
        });
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

});