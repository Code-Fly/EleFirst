/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
    $("#datebox-time").datebox("calendar").calendar({
        firstDay: 1
    });

    var MAX_PN_COUNT = 3;

    $("#input-pn").tagbox({
        buttonAlign: "right",
        buttonIcon: "icon-add",
        valueField: "value",
        textField: "name",
        editable: false,
        onClickButton: function () {
            if ($("#combo-pn").combobox("isValid")) {
                var name = $("#combo-pn").combobox("getText");
                var value = $("#combo-pn").combobox("getValue");
                var d = $(this).tagbox("getData");
                if (d.length >= MAX_PN_COUNT) {
                    $.messager.alert("信息提示", "最多选择 " + MAX_PN_COUNT + " 个监测点！", "info");
                    return;
                }
                for (var i = 0; i < d.length; i++) {
                    if (d[i].value == value) {
                        $.messager.alert("信息提示", "所选监测点已存在！", "info");
                        return;
                    }
                }

                d.push({
                    name: name,
                    value: value
                });

                $(this).tagbox("loadData", d);
                $(this).tagbox("select", value);

            } else {
                $.messager.alert("信息提示", "请选择监测点！", "info");
            }
        },
        // tagStyler: function (value) {
        //     var d = $(this).tagbox("getData");
        //     if (d.length >= 1 && d[0].value == value) {
        //         return "background:#b8eecf;color:#45872c";
        //     }
        // },
        onRemoveTag: function (value) {
            var d = $(this).tagbox("getData");
            var nd = [];
            for (var i = 0; i < d.length; i++) {
                if (d[i].value != value) {
                    nd.push(d[i])
                }
            }
            $(this).tagbox("loadData", nd);
        }
    });

    var MAX_DATE_COUNT = 5;

    $("#input-time").tagbox({
        buttonAlign: "right",
        buttonIcon: "icon-add",
        valueField: "value",
        textField: "name",
        editable: false,
        onClickButton: function () {
            if ($("#datebox-time").datebox("isValid")) {
                var date = $("#datebox-time").datebox("getValue");
                var d = $(this).tagbox("getData");
                if (d.length >= MAX_DATE_COUNT) {
                    $.messager.alert("信息提示", "最多选择 " + MAX_DATE_COUNT + " 个时间！", "info");
                    return;
                }
                for (var i = 0; i < d.length; i++) {
                    if (d[i].value == date) {
                        $.messager.alert("信息提示", "所选时间已存在！", "info");
                        return;
                    }
                }

                d.push({
                    name: date,
                    value: date
                });

                $(this).tagbox("loadData", d);
                $(this).tagbox("select", date);

            } else {
                $.messager.alert("信息提示", "请选择时间！", "info");
            }
        },
        onRemoveTag: function (value) {
            var d = $(this).tagbox("getData");
            var nd = [];
            for (var i = 0; i < d.length; i++) {
                if (d[i].value != value) {
                    nd.push(d[i])
                }
            }
            $(this).tagbox("loadData", nd);
        }
    });

    $("#combo-pn").combobox({
        required: true,
        valueField: "value",
        textField: "name",
        editable: false,
    });

    $("#datebox-time").datebox({
        required: true,
        editable: false,
    });

    $("#tab-table").tabs({
        onAdd: function (title, index) {
            var panel = $(this).tabs("getTab", index);
            var table = $(panel).find(".dg-table");
            $(table).datagrid({
                // data: d,
                fitColumns: true,
                border: false,
                singleSelect: true,
                columns: [[
                    {
                        field: "clientOperationTime",
                        title: "日期",
                        align: "center",
                        width: 100
                    },
                    {field: "totalactivepower", title: "最大值", align: "center", width: 100},
                    {
                        field: "currentClientOperationTime",
                        title: "发生时间",
                        align: "center",
                        width: 100
                    }
                ]]
            });
        }
    });

    $("#btn-search").linkbutton({
        onClick: function () {
            var nodeStr = $("#input-pn").tagbox("getData");
            if (nodeStr.length == 0) {
                $.messager.alert("信息提示", "请选择监测点！", "info");
                return;
            }
            var time = $("#input-time").tagbox("getData");
            if (time.length == 0) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }
            var nodes = [];

            for (var i = 0; i < nodeStr.length; i++) {
                var areaId = (nodeStr[i].value + "").split(":")[0];
                var concentratorId = (nodeStr[i].value + "").split(":")[1];
                var pn = (nodeStr[i].value + "").split(":")[2];
                var pt = parseFloat((nodeStr[0].value + "").split(":")[3]);
                var ct = parseFloat((nodeStr[0].value + "").split(":")[4]);
                var name = nodeStr[i].name;
                nodes.push({
                    areaId: areaId,
                    concentratorId: concentratorId,
                    pn: pn
                })
            }

            var times = [];

            for (var i = 0; i < time.length; i++) {
                var ss = time[i].value.split('-');
                var y = parseInt(ss[0], 10);
                var m = parseInt(ss[1], 10) - 1;
                var d = parseInt(ss[2], 10);

                times.push(
                    new Date(y, m, d).format('yyyyMMdd') + "000000"
                )
            }

            $.ajax({
                url: _ctx + "power/data/f25/frozen/day/node/time/list.do",
                type: "POST",
                cache: false,
                data: {
                    node: JSON.stringify(nodes),
                    time: JSON.stringify(times)
                },
                success: function (r) {
                    if (r.hasOwnProperty("errcode")) {
                        if ("0" == r.errcode) {
                            // $.messager.alert("操作提示", JSON.stringify(r.data));
                            getLoadChart(r.data);
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

            for (var i = 0; i < nodeStr.length; i++) {
                var paramTable = {
                    node: [],
                    time: []
                }

                var areaId = (nodeStr[i].value + "").split(":")[0];
                var concentratorId = (nodeStr[i].value + "").split(":")[1];
                var pn = (nodeStr[i].value + "").split(":")[2];
                var pt = parseFloat((nodeStr[i].value + "").split(":")[3]);
                var ct = parseFloat((nodeStr[i].value + "").split(":")[4]);
                var name = nodeStr[i].name;
                paramTable.node.push({
                    areaId: areaId,
                    concentratorId: concentratorId,
                    pn: pn
                });

                for (var j = 0; j < time.length; j++) {
                    var ss = time[j].value.split('-');
                    var y = parseInt(ss[0], 10);
                    var m = parseInt(ss[1], 10) - 1;
                    var d = parseInt(ss[2], 10);

                    paramTable.time.push(
                        new Date(y, m, d).format('yyyyMMdd') + "000000"
                    )
                }

                $.ajax({
                    url: _ctx + "poweranalysis/comparison/load/daily/table.do",
                    type: "POST",
                    cache: false,
                    contentType: "text/plain;charset=UTF-8",
                    data: JSON.stringify(paramTable),
                    success: function (r, textStatus, jqXHR) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                // $.messager.alert("操作提示", JSON.stringify(r.data));
                                var d = [];
                                for (var j = 0; j < r.data.length; j++) {
                                    var time = r.data[j].clientoperationtime + "";
                                    var totalactivepower = parseFloat(r.data[j].maxTotalActivePower) * pt * ct;
                                    totalactivepower = DataGridUtils.floatFormatter(totalactivepower);
                                    d.push({
                                        clientOperationTime: (formatDbTimestamp(time) + "").substr(0, 10),
                                        totalactivepower: totalactivepower,
                                        currentClientOperationTime: (formatDbTimestamp(time) + "").substr(11, 5)
                                    })
                                }

                                if (r.data.length > 0) {
                                    $(".table-" + r.data[0].areaId + "-" + r.data[0].concentratorId + "-" + r.data[0].pn).datagrid("loadData", d);
                                }
                            } else {
                                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                            }
                        } else {
                            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                        }
                    },
                    beforeSend: function (XMLHttpRequest) {
                        if (!$("#tab-table").tabs("getTab", name)) {
                            $("#tab-table").tabs("add", {
                                title: name,
                                content: "<div class='dg-table table-" + areaId + "-" + concentratorId + "-" + pn + "'></div>"
                            })
                        }
                        // MaskUtil.mask();
                    },
                    error: function (request) {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        // MaskUtil.unmask();
                    }
                });
            }
        }
    });


    function getLoadChart(data) {
        var nodeStr = $("#input-pn").tagbox("getData");
        var timeStr = $("#input-time").tagbox("getData");

        var series = []

        if ($("#switch-total").switchbutton("options").checked) {
            var nodes = [];
            for (var i = 0; i < node.length; i++) {
                var areaId = (node[i].value + "").split(":")[0];
                var concentratorId = (node[i].value + "").split(":")[1];
                var pn = (node[i].value + "").split(":")[2];
                var pt = parseFloat((node[0].value + "").split(":")[3]);
                var ct = parseFloat((node[0].value + "").split(":")[4]);
                var name = node[i].name;
                nodes.push({
                    areaId: areaId,
                    concentratorId: concentratorId,
                    pn: pn,
                    pt: pt,
                    ct: ct,
                    name: name
                });
            }

            for (var j = 0; j < time.length; j++) {
                var ss = time[j].value.split('-');
                var y = parseInt(ss[0], 10);
                var m = parseInt(ss[1], 10) - 1;
                var d = parseInt(ss[2], 10);

                var item = ChartUtils.getLoadDailySumSeries(nodes, new Date(y, m, d).format('yyyyMMdd') + "000000", data);
                series.push(item);
            }


        } else {
            var nodes = [];
            for (var i = 0; i < nodeStr.length; i++) {
                var areaId = (nodeStr[i].value + "").split(":")[0];
                var concentratorId = (nodeStr[i].value + "").split(":")[1];
                var pn = (nodeStr[i].value + "").split(":")[2];
                var pt = parseFloat((nodeStr[0].value + "").split(":")[3]);
                var ct = parseFloat((nodeStr[0].value + "").split(":")[4]);
                var name = nodeStr[i].name;

                nodes.push({
                    areaId: areaId,
                    concentratorId: concentratorId,
                    pn: pn,
                    pt: pt,
                    ct: ct,
                    name: name
                });
            }

            for (var i = 0; i < data.length; i++) {
                if (data[i].length > 0) {
                    var ss = data[i][0].clientoperationtime;
                    var date = TimeUtils.dbTimeToDate(ss);

                    var areaId = data[i][0].areaId;
                    var concentratorId = data[i][0].concentratorId;
                    var pn = data[i][0].pn;

                    var item = ChartUtils.getLoadAllByHourSeries(getNode(areaId, concentratorId, pn, nodes), date.format('yyyyMMdd') + "000000", data[i]);
                    series.push(item);
                }
            }
            // for (var i = 0; i < node.length; i++) {
            //     var areaId = (node[i].value + "").split(":")[0];
            //     var concentratorId = (node[i].value + "").split(":")[1];
            //     var pn = (node[i].value + "").split(":")[2];
            //     var pt = parseFloat((node[0].value + "").split(":")[3]);
            //     var ct = parseFloat((node[0].value + "").split(":")[4]);
            //     var name = node[i].name;
            //
            //     for (var j = 0; j < time.length; j++) {
            //         var ss = time[j].value.split('-');
            //         var y = parseInt(ss[0], 10);
            //         var m = parseInt(ss[1], 10) - 1;
            //         var d = parseInt(ss[2], 10);
            //
            //         // var item = ChartUtils.getLoadDailySeries({
            //         //     areaId: areaId,
            //         //     concentratorId: concentratorId,
            //         //     pn: pn,
            //         //     pt: pt,
            //         //     ct: ct,
            //         //     name: name
            //         // }, new Date(y, m, d).format('yyyyMMdd') + "000000", data);
            //
            //         var item = ChartUtils.getLoadAllSeries({
            //             areaId: areaId,
            //             concentratorId: concentratorId,
            //             pn: pn,
            //             pt: pt,
            //             ct: ct,
            //         }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data);
            //         series.push(item);
            //     }
            // }
        }

        var config = $.parseJSON($.ajax({
            url: "data/loadDetailChart.json?bust=" + new Date().getTime(),
            type: "GET",
            async: false
        }).responseText);

        config.series = series;

        $("#chart-load").highcharts(config);
    }


    loadConcentratorList();

    function loadConcentratorList() {
        $.ajax({
            url: _ctx + "system/pn/info/list.do",
            data: {
                areaId: _areaId
            },
            type: "POST",
            cache: false,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var d = [];
                        for (var i = 0; i < r.data.length; i++) {
                            var name = r.data[i].name;
                            var value = r.data[i].areaId + ":" + r.data[i].concentratorId + ":" + r.data[i].pn + ":" + r.data[i].pt + ":" + r.data[i].ct;
                            d.push({
                                name: name,
                                value: value
                            });
                        }
                        $("#combo-pn").combobox("loadData", d);
                        // $.messager.alert("操作提示", JSON.stringify(r.data));
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

    function getNode(areaId, concentratorId, pn, data) {
        for (var n = 0; n < data.length; n++) {
            if (data[n].areaId == areaId && data[n].concentratorId == concentratorId && data[n].pn == pn) {
                return data[n];
            }
        }
    }
});