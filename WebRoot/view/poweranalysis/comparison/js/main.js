/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
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

    $("#btn-search").linkbutton({
        onClick: function () {
            var node = $("#input-pn").tagbox("getData");
            if (node.length == 0) {
                $.messager.alert("信息提示", "请选择监测点！", "info");
                return;
            }
            var time = $("#input-time").tagbox("getData");
            if (time.length == 0) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }
            var paramChart = {
                node: [],
                time: []
            }

            for (var i = 0; i < node.length; i++) {
                var areaId = (node[i].value + "").split(":")[0];
                var concentratorId = (node[i].value + "").split(":")[1];
                var pn = (node[i].value + "").split(":")[2];
                var pt = parseFloat((node[0].value + "").split(":")[3]);
                var ct = parseFloat((node[0].value + "").split(":")[4]);
                var name = node[i].name;
                paramChart.node.push({
                    areaId: areaId,
                    concentratorId: concentratorId,
                    pn: pn
                })
            }
            for (var i = 0; i < time.length; i++) {
                paramChart.time.push(
                    new Date(time[i].value).format('yyyyMMdd') + "000000"
                )
            }

            $.ajax({
                url: _ctx + "poweranalysis/comparison/load/daily/chart.do",
                type: "POST",
                cache: false,
                contentType: "text/plain;charset=UTF-8",
                data: JSON.stringify(paramChart),
                success: function (r) {
                    if (r.hasOwnProperty("errcode")) {
                        if ("0" == r.errcode) {
                            // $.messager.alert("操作提示", JSON.stringify(r.data));
                            getLoadChart(r.data);
                        } else {
                            $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                        }
                    } else {
                        $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg("2"), "info");
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    MaskUtil.mask();
                },
                error: function (request) {
                    $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg("3"), "info");
                },
                complete: function (XMLHttpRequest, textStatus) {
                    MaskUtil.unmask();
                }
            });

            var paramTable = {
                node: [],
                time: []
            }

            var areaId = (node[0].value + "").split(":")[0];
            var concentratorId = (node[0].value + "").split(":")[1];
            var pn = (node[0].value + "").split(":")[2];
            var pt = parseFloat((node[0].value + "").split(":")[3]);
            var ct = parseFloat((node[0].value + "").split(":")[4]);
            var name = node[0].name;
            paramTable.node.push({
                areaId: areaId,
                concentratorId: concentratorId,
                pn: pn
            })
            for (var i = 0; i < time.length; i++) {
                paramTable.time.push(
                    new Date(time[i].value).format('yyyyMMdd') + "000000"
                )
            }

            $("#label-pn-table").text(name);

            $.ajax({
                url: _ctx + "poweranalysis/comparison/load/daily/table.do",
                type: "POST",
                cache: false,
                contentType: "text/plain;charset=UTF-8",
                data: JSON.stringify(paramTable),
                success: function (r) {
                    if (r.hasOwnProperty("errcode")) {
                        if ("0" == r.errcode) {
                            // $.messager.alert("操作提示", JSON.stringify(r.data));
                            var d = [];
                            for (var i = 0; i < r.data.length; i++) {
                                var time = r.data[i].clientoperationtime + "";
                                var totalactivepower = parseFloat(r.data[i].maxTotalActivePower) * pt * ct;
                                totalactivepower = Math.floor(totalactivepower * 100) / 100;
                                d.push({
                                    clientOperationTime: (formatDbTimestamp(time) + "").substr(0, 10),
                                    totalactivepower: totalactivepower,
                                    currentClientOperationTime: formatDbTimestamp(time)
                                })
                            }
                            $("#dg-table").datagrid("loadData", d);
                            // getLoadChart(r.data);
                        } else {
                            $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                        }
                    } else {
                        $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg("2"), "info");
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    MaskUtil.mask();
                },
                error: function (request) {
                    $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg("3"), "info");
                },
                complete: function (XMLHttpRequest, textStatus) {
                    MaskUtil.unmask();
                }
            });

        }
    });


    function getLoadChart(data) {
        var node = $("#input-pn").tagbox("getData");
        var time = $("#input-time").tagbox("getData");

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
                var item = ChartUtils.getLoadDailySumSeries(nodes, new Date(time[j].value).format('yyyyMMdd') + "000000", data);
                series.push(item);
            }


        } else {
            for (var i = 0; i < node.length; i++) {
                var areaId = (node[i].value + "").split(":")[0];
                var concentratorId = (node[i].value + "").split(":")[1];
                var pn = (node[i].value + "").split(":")[2];
                var pt = parseFloat((node[0].value + "").split(":")[3]);
                var ct = parseFloat((node[0].value + "").split(":")[4]);
                var name = node[i].name;

                for (var j = 0; j < time.length; j++) {
                    var item = ChartUtils.getLoadDailySeries({
                        areaId: areaId,
                        concentratorId: concentratorId,
                        pn: pn,
                        pt: pt,
                        ct: ct,
                        name: name
                    }, new Date(time[j].value).format('yyyyMMdd') + "000000", data);
                    series.push(item);
                }
            }
        }

        var config = $.parseJSON($.ajax({
            url: "data/loadComparison.json",
            type: "GET",
            async: false
        }).responseText);


        config.series = series;

        console.log(JSON.stringify(config))

        $("#chart-load").highcharts(config);
    }


    loadConcentratorList();

    function loadConcentratorList() {
        $.ajax({
            url: _ctx + "system/pn/info/list.do",
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
                        $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                MaskUtil.mask();
            },
            error: function (request) {
                $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg("3"), "info");
            },
            complete: function (XMLHttpRequest, textStatus) {
                MaskUtil.unmask();
            }
        });
    }
});