/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var DEFAULT_INTERVAL = 11;

    var _nodes = $.parseJSON($.base64.atob(decodeURIComponent(GetQueryString("data")), true));

    DateBoxUtils.initMonthBox($("#datebox-time-start"));

    DateBoxUtils.initMonthBox($("#datebox-time-end"));

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

                    return y + "-" + m;
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
                    t = Math.floor(t * 100) / 100;
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
                    t = Math.floor(t * 100) / 100;
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
                    t = Math.floor(t * 100) / 100;
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
                    t = Math.floor(t * 100) / 100;
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
                    t = Math.floor(t * 100) / 100;
                    if (parseFloat(row.maxTotalActivePower) == 0) {
                        t = "";
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

            if (interval > DEFAULT_INTERVAL) {
                $.messager.alert("操作提示", "最大间隔为 " + (DEFAULT_INTERVAL + 1) + " 个月！", "info");
                return;
            }

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


        var time = new Date().format("yyyy-MM");
        var interval = 11;
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

            var dt = new Date(y, m);
            dt.setMonth(dt.getMonth() + i);
            paramChart.time.push(
                dt.format("yyyyMM") + "01000000"
            );
        }

        $.ajax({
            url: _ctx + "poweranalysis/comparison/load/monthly/interval/month/chart.do",
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

                        var item = ChartUtils.getLoadMonthlyIntervalMonthSeries("最大", pnList, time, interval, r.data, "maxTotalActivePower");
                        series.push(item);

                        var item = ChartUtils.getLoadMonthlyIntervalMonthSeries("最小", pnList, time, interval, r.data, "minTotalActivePower");
                        series.push(item);

                        var item = ChartUtils.getLoadMonthlyIntervalMonthSeries("平均", pnList, time, interval, r.data, "avgTotalActivePower");
                        series.push(item);

                        var config = $.parseJSON($.ajax({
                            url: "data/loadDetailChart.json",
                            type: "GET",
                            async: false
                        }).responseText);


                        config.xAxis.categories = ChartUtils.getMonthlyIntervalMonthCategories(time, interval);
                        config.series = series;

                        // $.messager.alert("操作提示", JSON.stringify(config));

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
            maxTotalActivePower: 0,
            maxTotalActivePowerTime: "",
            minTotalActivePower: 10000000,
            minTotalActivePowerTime: "",
            avgTotalActivePower: 0,
        };
        for (var i = 0; i < data.length; i++) {
            var max = parseFloat(data[i].maxTotalActivePower) * data[i].pt * data[i].ct;
            max = Math.floor(max * 100) / 100;
            if (max > tmp.maxTotalActivePower) {
                tmp.maxTotalActivePower = max;
                tmp.maxTotalActivePowerTime = data[i].clientoperationtime;
            }

            var min = parseFloat(data[i].minTotalActivePower) * data[i].pt * data[i].ct;
            min = Math.floor(min * 100) / 100;
            if (min < tmp.minTotalActivePower) {
                tmp.minTotalActivePower = min;
                tmp.minTotalActivePowerTime = data[i].clientoperationtime;
            }
            if (data[i].avgTotalActivePower != null) {
                var avg = parseFloat(data[i].avgTotalActivePower) * data[i].pt * data[i].ct;
                tmp.avgTotalActivePower += avg;
            }
        }
        tmp.avgTotalActivePower = tmp.avgTotalActivePower / data.length;
        tmp.avgTotalActivePower = Math.floor(tmp.avgTotalActivePower * 100) / 100;


        $("#maxTotalActivePower").text(tmp.maxTotalActivePower + "(kW)");
        $("#minTotalActivePower").text(tmp.minTotalActivePower + "(kW)");
        $("#avgTotalActivePower").text(tmp.avgTotalActivePower + "(kW)");

        var maxTime = tmp.maxTotalActivePowerTime.substr(0, 4) + "-" + tmp.maxTotalActivePowerTime.substr(4, 2) + "-" + tmp.maxTotalActivePowerTime.substr(6, 2);
        $("#maxTotalActivePowerTime").text(maxTime);

        var minTime = tmp.minTotalActivePowerTime.substr(0, 4) + "-" + tmp.minTotalActivePowerTime.substr(4, 2) + "-" + tmp.minTotalActivePowerTime.substr(6, 2);
        $("#minTotalActivePowerTime").text(minTime);

        var differ = tmp.maxTotalActivePower - tmp.minTotalActivePower;
        differ = Math.floor(differ * 100) / 100 + "(kW)";
        $("#differ").text(differ);

        var differRate = ((tmp.maxTotalActivePower - tmp.minTotalActivePower) / tmp.maxTotalActivePower) * 100
        differRate = Math.floor(differRate * 100) / 100 + "(%)";
        if (tmp.maxTotalActivePower == 0) {
            differRate = "--";
        }
        $("#differRate").text(differRate);

        var loadRate = (tmp.avgTotalActivePower / tmp.maxTotalActivePower) * 100
        loadRate = Math.floor(loadRate * 100) / 100 + "(%)";
        if (tmp.maxTotalActivePower == 0) {
            loadRate = "--";
        }
        $("#loadRate").text(loadRate);


        return tmp;
    }

    function getDgData(data, pnList) {
        var tmp = {};
        for (var i = 0; i < data.length; i++) {
            var day = data[i].clientoperationtime.substr(0, 6);
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
        startDate.setMonth(startDate.getMonth() - 1);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

        var interval = getDateInterval($("#datebox-time-start").datebox("getValue"), $("#datebox-time-end").datebox("getValue"));

        getLoadDetailChart({
            nodes: _nodes,
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