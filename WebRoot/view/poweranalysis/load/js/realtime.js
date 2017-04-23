/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var _nodes = $.parseJSON($.base64.atob(decodeURIComponent(GetQueryString("data")), true));

    var _spinner = new Spinner();

    $("#datebox-time").datebox("calendar").calendar({
        firstDay: 1
    });

    $("#datebox-time").datebox({
        required: true,
        editable: false
    });

    var initDate = new Date();
    initDate.setDate(initDate.getDate() - 1);

    $("#datebox-time").datebox("setValue", initDate.getFullYear() + "-" + (initDate.getMonth() + 1) + "-" + initDate.getDate());

    $("#btn-search").linkbutton({
        onClick: function () {
            getLoadDetailChart({
                nodes: _nodes,
                time: $("#datebox-time").datebox("getValue")
            });

            getLoadDetailTable({
                nodes: _nodes,
                time: $("#datebox-time").datebox("getValue")
            });
        }
    });

    init();

    function getLoadDetailChart(row) {
        var pnList = getPnDetail(row.nodes);


        var time = new Date().format("yyyy-MM-dd");
        if (row.time != null && row.time != "") {
            time = row.time;
        }

        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;
        var d = parseInt(ss[2], 10);

        var cur = new Date(y, m, d);
        cur.setDate(cur.getDate());

        var next = new Date(y, m, d);
        next.setDate(next.getDate() + 1);

        var times = [];
        times.push({
            startTime: cur.format('yyyyMMdd') + "000000",
            endTime: next.format('yyyyMMdd') + "000000"
        });

        $.ajax({
            url: _ctx + "power/data/f25/frozen/minute/node/time/sum.do",
            type: "POST",
            cache: false,
            data: {
                node: JSON.stringify(pnList),
                time: JSON.stringify(times)

            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {

                        var series = [];

                        for (var i = 0; i < r.data.length; i++) {
                            if (r.data[i].length > 0) {
                                var ss = r.data[i][0].clientoperationtime;
                                var date = TimeUtils.dbTimeToDate(ss);

                                var item = ChartUtils.getLoadAllSeries({
                                    name: date.format("yyyy-MM-dd")
                                }, r.data[i]);
                                series.push(item);
                            }
                        }

                        var config = new ChartConfig("view/chart/spline-date-all-load.json");

                        config
                            .setShared(true)
                            .setZoom(true)
                            .setSeries(series);

                        $("#chart-load-sum").highcharts("StockChart", config.getConfig());

                        // getTbData(getDgData(r.data, pnList));

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


    function getLoadDetailTable(row) {
        var pnList = getPnDetail(row.nodes);


        var time = new Date().format("yyyy-MM-dd");
        if (row.time != null && row.time != "") {
            time = row.time;
        }

        var ss = time.split('-');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10) - 1;
        var d = parseInt(ss[2], 10);

        var startDate = new Date(y, m, d);
        var endDate = new Date(y, m, d);
        endDate.setDate(endDate.getDate() + 1);

        var startTime = startDate.format('yyyyMMdd') + "000000";
        var endTime = endDate.format('yyyyMMdd') + "000000";

        $.ajax({
            url: _ctx + "power/data/f25f5/frozen/minute/load/activepower/total/statistic.do",
            type: "POST",
            cache: false,
            data: {
                node: JSON.stringify(pnList),
                startTime: startTime,
                endTime: endTime
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var item = r.data;

                        if (null != item.maxTotalActivePower) {
                            $("#maxTotalActivePower").text(item.maxTotalActivePower + "(kW)");
                        } else {
                            $("#maxTotalActivePower").text("--");
                        }
                        if (null != item.minTotalActivePower) {
                            $("#minTotalActivePower").text(item.minTotalActivePower + "(kW)");
                        } else {
                            $("#minTotalActivePower").text("--");
                        }
                        if (null != item.avgTotalActivePower) {
                            $("#avgTotalActivePower").text(item.avgTotalActivePower + "(kW)");
                        } else {
                            $("#avgTotalActivePower").text("--");
                        }
                        if (null != item.maxTotalActivePowerTime) {
                            $("#maxTotalActivePowerTime").text(TimeUtils.dbTimeToDate(item.maxTotalActivePowerTime).format("hh:mm"));
                        } else {
                            $("#maxTotalActivePowerTime").text("--");
                        }
                        if (null != item.minTotalActivePowerTime) {
                            $("#minTotalActivePowerTime").text(TimeUtils.dbTimeToDate(item.minTotalActivePowerTime).format("hh:mm"));
                        } else {
                            $("#minTotalActivePowerTime").text("--");
                        }
                        if (null != item.differ) {
                            $("#differ").text(item.differ + "(kW)");
                        } else {
                            $("#differ").text("--");
                        }
                        if (null != item.differRate) {
                            $("#differRate").text(item.differRate + "(%)");
                        } else {
                            $("#differRate").text("--");
                        }
                        if (null != item.loadRate) {
                            $("#loadRate").text(item.loadRate + "(%)");
                        } else {
                            $("#loadRate").text("--");
                        }


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
            jError("请求失败！" + ErrUtils.getMsg(pnInfo.errcode));
            return null;
        }
    }

    function init() {
        getLoadDetailChart({
            nodes: _nodes,
            time: $("#datebox-time").datebox("getValue")
        });

        getLoadDetailTable({
            nodes: _nodes,
            time: $("#datebox-time").datebox("getValue")
        });
    }
});