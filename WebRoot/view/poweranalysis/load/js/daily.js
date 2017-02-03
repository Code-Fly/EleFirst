/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
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


    $("#btn-search").linkbutton({
        onClick: function () {
            var interval = getDateInterval($("#datebox-time-start").datebox("getValue"), $("#datebox-time-end").datebox("getValue"));

            if (interval < 0) {
                $.messager.alert("操作提示", "请选择正确起止时间！", "info");
                return;
            }

            if (interval > 6) {
                $.messager.alert("操作提示", "最大间隔为 7 天！", "info");
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
                        var series = [];

                        var item = ChartUtils.getLoadDailyIntervalDaySeries("最大", pnList, time, interval, r.data, "maxTotalActivePower");
                        series.push(item);

                        var item = ChartUtils.getLoadDailyIntervalDaySeries("最小", pnList, time, interval, r.data, "minTotalActivePower");
                        series.push(item);

                        var item = ChartUtils.getLoadDailyIntervalDaySeries("平均", pnList, time, interval, r.data, "avgTotalActivePower");
                        series.push(item);

                        var config = $.parseJSON($.ajax({
                            url: "data/loadDetailChart.json",
                            type: "GET",
                            async: false
                        }).responseText);

                        config.xAxis.categories = ChartUtils.getDailyIntervalDayCategories(time, interval);
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
        startDate.setDate(startDate.getDate() - 6);

        $("#datebox-time-start").datebox("setValue", startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate());

        $("#datebox-time-end").datebox("setValue", endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate());

        getLoadDetailChart({
            nodes: _nodes,
            time: $("#datebox-time").datebox("getValue")
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