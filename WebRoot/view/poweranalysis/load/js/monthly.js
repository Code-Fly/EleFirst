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