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
                    $.messager.alert("信息提示", "最多选择 " + MAX_PN_COUNT + " 个测量点！", "info");
                    return;
                }
                for (var i = 0; i < d.length; i++) {
                    if (d[i].value == value) {
                        $.messager.alert("信息提示", "所选测量点已存在！", "info");
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

    $('#chart-comparasion').highcharts({
        "credits": {
            "enabled": false
        },
        "exporting": {
            "enabled": false
        },
        chart: {
            type: 'spline',
        },
        title: {
            text: 'Wind speed during two days'
        },
        subtitle: {
            text: 'October 6th and 7th 2009 at two locations in Vik i Sogn, Norway'
        },
        xAxis: {
            type: 'datetime',
            labels: {
                overflow: 'justify'
            }
        },
        yAxis: {
            title: {
                text: 'Wind speed (m/s)'
            },
            min: 0,
            minorGridLineWidth: 0,
            gridLineWidth: 0,
            alternateGridColor: null,
            plotBands: [{ // Light air
                from: 0.3,
                to: 1.5,
                color: 'rgba(68, 170, 213, 0.1)',
                label: {
                    text: 'Light air',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Light breeze
                from: 1.5,
                to: 3.3,
                color: 'rgba(0, 0, 0, 0)',
                label: {
                    text: 'Light breeze',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Gentle breeze
                from: 3.3,
                to: 5.5,
                color: 'rgba(68, 170, 213, 0.1)',
                label: {
                    text: 'Gentle breeze',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Moderate breeze
                from: 5.5,
                to: 8,
                color: 'rgba(0, 0, 0, 0)',
                label: {
                    text: 'Moderate breeze',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Fresh breeze
                from: 8,
                to: 11,
                color: 'rgba(68, 170, 213, 0.1)',
                label: {
                    text: 'Fresh breeze',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // Strong breeze
                from: 11,
                to: 14,
                color: 'rgba(0, 0, 0, 0)',
                label: {
                    text: 'Strong breeze',
                    style: {
                        color: '#606060'
                    }
                }
            }, { // High wind
                from: 14,
                to: 15,
                color: 'rgba(68, 170, 213, 0.1)',
                label: {
                    text: 'High wind',
                    style: {
                        color: '#606060'
                    }
                }
            }]
        },
        tooltip: {
            valueSuffix: ' m/s'
        },
        plotOptions: {
            spline: {
                lineWidth: 4,
                states: {
                    hover: {
                        lineWidth: 5
                    }
                },
                marker: {
                    enabled: false
                },
                pointInterval: 3600000, // one hour
                pointStart: Date.UTC(2009, 9, 6, 0, 0, 0)
            }
        },
        series: [{
            name: 'Hestavollane',
            data: [4.3, 5.1, 4.3, 5.2, 5.4, 4.7, 3.5, 4.1, 5.6, 7.4, 6.9, 7.1,
                7.9, 7.9, 7.5, 6.7, 7.7, 7.7, 7.4, 7.0, 7.1, 5.8, 5.9, 7.4,
                8.2, 8.5, 9.4, 8.1, 10.9, 10.4, 10.9, 12.4, 12.1, 9.5, 7.5,
                7.1, 7.5, 8.1, 6.8, 3.4, 2.1, 1.9, 2.8, 2.9, 1.3, 4.4, 4.2,
                3.0, 3.0]
        }, {
            name: 'Voll',
            data: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0,
                0.0, 0.4, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.6, 1.2, 1.7, 0.7, 2.9, 4.1, 2.6, 3.7, 3.9, 1.7, 2.3,
                null, null, null, null, null, null, null, null, null, null, null, null, null]
        }],
    });
});