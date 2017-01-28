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
                $.messager.alert("信息提示", "请选择测量点！", "info");
            }
        },
        tagStyler: function (value) {
            var d = $(this).tagbox("getData");
            if (d.length >= 1 && d[0].value == value) {
                return "background:#b8eecf;color:#45872c";
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
        url: "data/pns.json",
        method: "get",
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
            if (node.length == 0) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }
            var param = {
                node: [],
                time: []
            }

            for (var i = 0; i < node.length; i++) {
                var areaId = (node[i].value + "").split(":")[0];
                var concentratorId = (node[i].value + "").split(":")[1];
                var pn = (node[i].value + "").split(":")[2];
                var rate = (node[i].value + "").split(":")[3];
                var name = node[i].name;
                param.node.push({
                    areaId: areaId,
                    concentratorId: concentratorId,
                    pn: pn
                })
            }
            for (var i = 0; i < time.length; i++) {
                param.time.push(
                    new Date(time[i].value).format('yyyyMMdd') + "000000"
                )
            }

            $.ajax({
                url: _ctx + "poweranalysis/comparison/chart.do",
                type: "POST",
                cache: false,
                contentType: "text/plain;charset=UTF-8",
                data: JSON.stringify(param),
                success: function (r) {
                    if (r.hasOwnProperty("errcode")) {
                        if ("0" == r.errcode) {
                            // $.messager.alert("操作提示", JSON.stringify(r.data));
                            loadComparisonChart(r.data);
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


    function loadComparisonChart(data) {
        var node = $("#input-pn").tagbox("getData");
        var time = $("#input-time").tagbox("getData");

        var series = []

        if ($("#switch-total").switchbutton("options").checked) {
            var nodes = [];
            for (var i = 0; i < node.length; i++) {
                var areaId = (node[i].value + "").split(":")[0];
                var concentratorId = (node[i].value + "").split(":")[1];
                var pn = (node[i].value + "").split(":")[2];
                var rate = (node[i].value + "").split(":")[3];
                var name = node[i].name;
                nodes.push({
                    areaId: areaId,
                    concentratorId: concentratorId,
                    pn: pn,
                    rate: rate,
                    name: name
                });
            }

            for (var j = 0; j < time.length; j++) {
                var item = getSeriesTotal(nodes, new Date(time[j].value).format('yyyyMMdd') + "000000", data);
                series.push(item);
            }


        } else {
            for (var i = 0; i < node.length; i++) {
                var areaId = (node[i].value + "").split(":")[0];
                var concentratorId = (node[i].value + "").split(":")[1];
                var pn = (node[i].value + "").split(":")[2];
                var rate = (node[i].value + "").split(":")[3];
                var name = node[i].name;

                for (var j = 0; j < time.length; j++) {
                    var item = getSeries({
                        areaId: areaId,
                        concentratorId: concentratorId,
                        pn: pn,
                        rate: rate,
                        name: name
                    }, new Date(time[j].value).format('yyyyMMdd') + "000000", data);
                    series.push(item);
                }
            }
        }

        var config = $.parseJSON($.ajax({
            url: "data/loadComparison.json",
            async: false
        }).responseText);


        config.series = series;

        console.log(JSON.stringify(config))


        $("#chart-comparasion").highcharts(config);
    }

    function getSeriesTotal(nodes, time, data) {
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
                                    tmp = parseFloat(data[i].maxTotalActivePower) * parseFloat(nodes[j].rate);
                                } else {
                                    tmp += parseFloat(data[i].maxTotalActivePower) * parseFloat(nodes[j].rate);
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

    }

    function getSeries(node, time, data) {
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
                            tmp = parseFloat(data[i].maxTotalActivePower) * parseFloat(node.rate);
                            tmp = Math.floor(tmp * 100) / 100;
                        }
                    }
                }
            }
            series.data.push(tmp);

        }

        return series;
    }

    // $('#chart-comparasion').highcharts({
    //     "credits": {
    //         "enabled": false
    //     },
    //     "exporting": {
    //         "enabled": false
    //     },
    //     chart: {
    //         type: 'spline',
    //     },
    //     title: {
    //         text: 'Wind speed during two days'
    //     },
    //     subtitle: {
    //         text: 'October 6th and 7th 2009 at two locations in Vik i Sogn, Norway'
    //     },
    //     xAxis: {
    //         type: 'datetime',
    //         labels: {
    //             overflow: 'justify'
    //         }
    //     },
    //     yAxis: {
    //         title: {
    //             text: 'Wind speed (m/s)'
    //         },
    //         min: 0,
    //         minorGridLineWidth: 0,
    //         gridLineWidth: 0,
    //         alternateGridColor: null,
    //         plotBands: [{ // Light air
    //             from: 0.3,
    //             to: 1.5,
    //             color: 'rgba(68, 170, 213, 0.1)',
    //             label: {
    //                 text: 'Light air',
    //                 style: {
    //                     color: '#606060'
    //                 }
    //             }
    //         }, { // Light breeze
    //             from: 1.5,
    //             to: 3.3,
    //             color: 'rgba(0, 0, 0, 0)',
    //             label: {
    //                 text: 'Light breeze',
    //                 style: {
    //                     color: '#606060'
    //                 }
    //             }
    //         }, { // Gentle breeze
    //             from: 3.3,
    //             to: 5.5,
    //             color: 'rgba(68, 170, 213, 0.1)',
    //             label: {
    //                 text: 'Gentle breeze',
    //                 style: {
    //                     color: '#606060'
    //                 }
    //             }
    //         }, { // Moderate breeze
    //             from: 5.5,
    //             to: 8,
    //             color: 'rgba(0, 0, 0, 0)',
    //             label: {
    //                 text: 'Moderate breeze',
    //                 style: {
    //                     color: '#606060'
    //                 }
    //             }
    //         }, { // Fresh breeze
    //             from: 8,
    //             to: 11,
    //             color: 'rgba(68, 170, 213, 0.1)',
    //             label: {
    //                 text: 'Fresh breeze',
    //                 style: {
    //                     color: '#606060'
    //                 }
    //             }
    //         }, { // Strong breeze
    //             from: 11,
    //             to: 14,
    //             color: 'rgba(0, 0, 0, 0)',
    //             label: {
    //                 text: 'Strong breeze',
    //                 style: {
    //                     color: '#606060'
    //                 }
    //             }
    //         }, { // High wind
    //             from: 14,
    //             to: 15,
    //             color: 'rgba(68, 170, 213, 0.1)',
    //             label: {
    //                 text: 'High wind',
    //                 style: {
    //                     color: '#606060'
    //                 }
    //             }
    //         }]
    //     },
    //     tooltip: {
    //         valueSuffix: ' m/s'
    //     },
    //     plotOptions: {
    //         spline: {
    //             lineWidth: 4,
    //             states: {
    //                 hover: {
    //                     lineWidth: 5
    //                 }
    //             },
    //             marker: {
    //                 enabled: false
    //             },
    //             pointInterval: 3600000, // one hour
    //             pointStart: Date.UTC(2009, 9, 6, 0, 0, 0)
    //         }
    //     },
    //     series: [{
    //         name: 'Hestavollane',
    //         data: [4.3, 5.1, 4.3, 5.2, 5.4, 4.7, 3.5, 4.1, 5.6, 7.4, 6.9, 7.1,
    //             7.9, 7.9, 7.5, 6.7, 7.7, 7.7, 7.4, 7.0, 7.1, 5.8, 5.9, 7.4,
    //             8.2, 8.5, 9.4, 8.1, 10.9, 10.4, 10.9, 12.4, 12.1, 9.5, 7.5,
    //             7.1, 7.5, 8.1, 6.8, 3.4, 2.1, 1.9, 2.8, 2.9, 1.3, 4.4, 4.2,
    //             3.0, 3.0]
    //     }, {
    //         name: 'Voll',
    //         data: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0,
    //             0.0, 0.4, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
    //             0.0, 0.6, 1.2, 1.7, 0.7, 2.9, 4.1, 2.6, 3.7, 3.9, 1.7, 2.3,
    //             null, null, null, null, null, null, null, null, null, null, null, null, null]
    //     }],
    // });
});