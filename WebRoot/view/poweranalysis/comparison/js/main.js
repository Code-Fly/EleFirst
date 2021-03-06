/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
    var _spinner = new Spinner();

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

    // $("#tab-table").tabs({
    //     onAdd: function (title, index) {
    //         var panel = $(this).tabs("getTab", index);
    //         var table = $(panel).find(".dg-table");
    //         $(table).datagrid({
    //             fitColumns: true,
    //             border: false,
    //             singleSelect: true,
    //             columns: [[
    //                 {
    //                     field: "clientOperationTime",
    //                     title: "日期",
    //                     align: "center",
    //                     width: 100
    //                 },
    //                 {field: "totalactivepower", title: "最大值", align: "center", width: 100},
    //                 {
    //                     field: "currentClientOperationTime",
    //                     title: "发生时间",
    //                     align: "center",
    //                     width: 100
    //                 }
    //             ]]
    //         });
    //     }
    // });

    $("#btn-search").linkbutton({
        onClick: function () {
            var nodeStr = $("#input-pn").tagbox("getData");
            if (nodeStr.length == 0) {
                $.messager.alert("信息提示", "请选择监测点！", "info");
                return;
            }
            var timeStr = $("#input-time").tagbox("getData");
            if (timeStr.length == 0) {
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
                    pn: pn,
                    ct: ct,
                    pt: pt,
                    name: name
                })
            }

            var times = [];

            for (var i = 0; i < timeStr.length; i++) {
                var ss = timeStr[i].value.split('-');
                var y = parseInt(ss[0], 10);
                var m = parseInt(ss[1], 10) - 1;
                var d = parseInt(ss[2], 10);

                var cur = new Date(y, m, d);

                var next = new Date(y, m, d);
                next.setDate(next.getDate() + 1);

                times.push({
                    startTime: cur.format('yyyyMMdd') + "000000",
                    endTime: next.format('yyyyMMdd') + "000000"
                });
            }

            if ($("#switch-total").switchbutton("options").checked) {
                $.ajax({
                    url: _ctx + "power/data/f25/frozen/minute/node/time/sum.do",
                    type: "POST",
                    cache: false,
                    data: {
                        node: JSON.stringify(nodes),
                        time: JSON.stringify(times)
                    },
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                var series = [];

                                // $.messager.alert("操作提示", JSON.stringify(r.data));
                                for (var i = 0; i < r.data.length; i++) {
                                    var ss = r.data[i][0].clientoperationtime;
                                    var date = TimeUtils.dbTimeToDate(ss);

                                    if (r.data[i].length > 0) {
                                        var item = ChartUtils.getLoadAllByHourSeries({
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

                                $("#chart-load").highcharts("StockChart", config.getConfig());
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
            } else {
                $.ajax({
                    url: _ctx + "power/data/f25/frozen/minute/node/time/list.do",
                    type: "POST",
                    cache: false,
                    data: {
                        node: JSON.stringify(nodes),
                        time: JSON.stringify(times)
                    },
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                var series = [];

                                // $.messager.alert("操作提示", JSON.stringify(r.data));
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


                                for (var i = 0; i < r.data.length; i++) {
                                    if (r.data[i].length > 0) {
                                        var ss = r.data[i][0].clientoperationtime;
                                        var date = TimeUtils.dbTimeToDate(ss);

                                        var areaId = r.data[i][0].areaId;
                                        var concentratorId = r.data[i][0].concentratorId;
                                        var pn = r.data[i][0].pn;

                                        var node = getNode(areaId, concentratorId, pn, nodes);
                                        var item = ChartUtils.getLoadAllByHourSeries({
                                            name: node.name + "(" + date.format("yyyy-MM-dd") + ")"
                                        }, r.data[i]);
                                        series.push(item);
                                    }
                                }

                                var config = new ChartConfig("view/chart/spline-date-all-load.json");

                                config
                                    .setShared(true)
                                    .setZoom(true)
                                    .setSeries(series);

                                $("#chart-load").highcharts("StockChart", config.getConfig());
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

            $.ajax({
                url: _ctx + "power/data/f25f5/frozen/minute/load/activepower/total/statistic/node/list.do",
                type: "POST",
                cache: false,
                data: {
                    node: JSON.stringify(nodes),
                    time: JSON.stringify(times)
                },
                success: function (r) {
                    if (r.hasOwnProperty("errcode")) {
                        if ("0" == r.errcode) {
                            var dgData = [];
                            for (var i = 0; i < nodes.length; i++) {
                                var d = [];

                                for (var j = 0; j < r.data.length; j++) {
                                    if (nodes[i].areaId == r.data[j].areaId && nodes[i].concentratorId == r.data[j].concentratorId && nodes[i].pn == r.data[j].pn) {
                                        d.push({
                                            name: nodes[i].name,
                                            clientOperationTime: (formatDbTimestamp(r.data[j].maxTotalActivePowerTime) + "").substr(0, 10),
                                            totalactivepower: r.data[j].maxTotalActivePower,
                                            currentClientOperationTime: (formatDbTimestamp(r.data[j].maxTotalActivePowerTime) + "").substr(11, 5)
                                        })
                                    }
                                }

                                if (d.length > 0) {
                                    dgData = dgData.concat(d);
                                }
                            }
                            $("#dg-table").datagrid("loadData", dgData);
                        } else {
                            jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                        }
                    } else {
                        jError("请求失败！" + ErrUtils.getMsg("2"));
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    // for (var i = 0; i < nodes.length; i++) {
                    //     if (!$("#tab-table").tabs("getTab", nodes[i].name)) {
                    //         $("#tab-table").tabs("add", {
                    //             title: nodes[i].name,
                    //             content: "<div class='dg-table table-" + nodes[i].areaId + "-" + nodes[i].concentratorId + "-" + nodes[i].pn + "'></div>"
                    //         })
                    //     }
                    // }


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
    });

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

    function getNode(areaId, concentratorId, pn, data) {
        for (var n = 0; n < data.length; n++) {
            if (data[n].areaId == areaId && data[n].concentratorId == concentratorId && data[n].pn == pn) {
                return data[n];
            }
        }
    }
});