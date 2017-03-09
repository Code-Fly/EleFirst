/**
 * Created by barrie on 17/1/30.
 */
$(document).ready(function () {
    var _nodes = $.parseJSON($.base64.atob(decodeURIComponent(GetQueryString("data")), true));

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

        var times = [];
        times.push(
            new Date(y, m, d).format('yyyyMMdd') + "000000"
        );

        $.ajax({
            url: _ctx + "power/data/f25/frozen/day/node/time/sum.do",
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

                        var config = $.parseJSON($.ajax({
                            url: _ctx + "view/chart/spline-date-all-load.json?bust=" + new Date().getTime(),
                            type: "GET",
                            async: false
                        }).responseText);

                        config.series = series;
                        $("#chart-load-sum").highcharts("StockChart", config);

                        getTbData(getDgData(r.data, pnList));

                    } else {
                        jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                    }
                } else {
                    jError("请求失败！" + ErrUtils.getMsg("2"));
                }
            },
            beforeSend: function (XMLHttpRequest) {
                MaskUtil.mask();
            },
            error: function (request) {
                jError("请求失败！" + ErrUtils.getMsg("3"));
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
            maxTotalActivePowerHour: "",
            minTotalActivePower: ChartUtils.MAX_CHART_NUMBER,
            minTotalActivePowerTime: "",
            minTotalActivePowerHour: "",
            avgTotalActivePower: 0.0,
        };
        for (var i = 0; i < data.length; i++) {
            var max = parseFloat(data[i].maxTotalActivePower) * data[i].pt * data[i].ct;
            max = DataGridUtils.floatFormatter(max, 3);
            if (max > tmp.maxTotalActivePower) {
                tmp.maxTotalActivePower = max;
                tmp.maxTotalActivePowerTime = data[i].clientoperationtime;
                tmp.maxTotalActivePowerHour = data[i].hourClientOperationTime;
            }

            var min = parseFloat(data[i].maxTotalActivePower) * data[i].pt * data[i].ct;
            min = DataGridUtils.floatFormatter(min, 3);
            if (min < tmp.minTotalActivePower) {
                tmp.minTotalActivePower = min;
                tmp.minTotalActivePowerTime = data[i].clientoperationtime;
                tmp.minTotalActivePowerHour = data[i].hourClientOperationTime;
            }
            if (data[i].maxTotalActivePower != null) {
                var avg = parseFloat(data[i].maxTotalActivePower) * data[i].pt * data[i].ct;

                tmp.avgTotalActivePower += avg;
            }
        }

        tmp.avgTotalActivePower = tmp.avgTotalActivePower / parseFloat(data.length);
        tmp.avgTotalActivePower = DataGridUtils.floatFormatter(tmp.avgTotalActivePower, 3);


        $("#maxTotalActivePower").text(tmp.maxTotalActivePower + "(kW)");
        $("#minTotalActivePower").text(tmp.minTotalActivePower + "(kW)");
        $("#avgTotalActivePower").text(tmp.avgTotalActivePower + "(kW)");

        var maxTime = tmp.maxTotalActivePowerTime.substr(0, 4) + "-" + tmp.maxTotalActivePowerTime.substr(4, 2) + "-" + tmp.maxTotalActivePowerTime.substr(6, 2) + " " + fixNum(tmp.maxTotalActivePowerHour, 2) + ":00";
        $("#maxTotalActivePowerTime").text(maxTime);

        var minTime = tmp.minTotalActivePowerTime.substr(0, 4) + "-" + tmp.minTotalActivePowerTime.substr(4, 2) + "-" + tmp.minTotalActivePowerTime.substr(6, 2) + " " + fixNum(tmp.minTotalActivePowerHour, 2) + ":00";
        $("#minTotalActivePowerTime").text(minTime);

        var differ = tmp.maxTotalActivePower - tmp.minTotalActivePower;
        differ = DataGridUtils.floatFormatter(differ, 3) + "(kW)";
        $("#differ").text(differ);

        var differRate = ((tmp.maxTotalActivePower - tmp.minTotalActivePower) / tmp.maxTotalActivePower) * 100.0;
        differRate = DataGridUtils.floatFormatter(differRate, 1) + "(%)";
        if (tmp.maxTotalActivePower == 0) {
            differRate = "-";
        }
        $("#differRate").text(differRate);

        var loadRate = (tmp.avgTotalActivePower / tmp.maxTotalActivePower) * 100.0;
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
            var day = data[i].clientoperationtime.substr(0, 10);
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
            jError("请求失败！" + ErrUtils.getMsg(pnInfo.errcode));
            return null;
        }
    }

    function init() {
        getLoadDetailChart({
            nodes: _nodes,
            time: $("#datebox-time").datebox("getValue")
        });
    }
});