/**
 * Created by VM on 1/25/2017.
 */
$(document).ready(function () {

    loadSummaryInfo();

    function loadSummaryInfo() {
        $.ajax({
            url: _ctx + "index/summary/info.do",
            type: "POST",
            data: {
                areaId: _areaId
            },
            cache: false,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        $("#transformers").text(r.data.transformers);
                        $("#ratedCapacity").text(r.data.ratedCapacity);
                        $("#pns").text(r.data.pns);
                        $("#electricityThisMonth").text(DataGridUtils.floatWithUnitFormatter(r.data.electricityThisMonth, 4));
                        $("#electricityLastMonth").text(DataGridUtils.floatWithUnitFormatter(r.data.electricityLastMonth, 4));
                        $("#electricityLastLastMonth").text(DataGridUtils.floatWithUnitFormatter(r.data.electricityLastLastMonth, 4));
                        $("#maxLoadThisMonth").text(DataGridUtils.floatWithUnitFormatter(r.data.maxLoadThisMonth, 3));
                        $("#maxLoadThisYear").text(DataGridUtils.floatWithUnitFormatter(r.data.maxLoadThisYear, 3));
                        $("#maxLoadTotal").text(DataGridUtils.floatWithUnitFormatter(r.data.maxLoadTotal, 3));
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

            }
        });
    }

    $('#chart-day-load').highcharts({
            "credits": {
                "enabled": false
            },
            "exporting": {
                "enabled": false
            },
            "chart": {
                "type": "spline"
            },
            "title": {
                "text": null
            },
            "legend": {
                "borderWidth": 1,
                "shadow": false
            },
            "xAxis": {
                "tickmarkPlacement": "on",
                "categories": [
                    "00",
                    "01",
                    "02",
                    "03",
                    "04",
                    "05",
                    "06",
                    "07",
                    "08",
                    "09",
                    "10",
                    "11",
                    "12",
                    "13",
                    "14",
                    "15",
                    "16",
                    "17",
                    "18",
                    "19",
                    "20",
                    "21",
                    "22",
                    "23"
                ],
                "title": {
                    "text": null
                }
            },
            "yAxis": {
                "title": {
                    "text": "单位（kW）"
                }
            },
            "tooltip": {
                "valueSuffix": "kW",
                "shared": true
            },
            "plotOptions": {
                "line": {
                    "dataLabels": {
                        "enabled": false
                    },
                    "enableMouseTracking": true
                }
            },
            "series": [
                {
                    "name": "昨日",
                    "data": [
                        1,
                        2,
                        5,
                        4,
                        6,
                        5,
                        8,
                        7,
                        9,
                        3,
                        3,
                        2,
                        5,
                        6,
                        2,
                        3,
                        4,
                        3,
                        6,
                        3,
                        3,
                        7,
                        3,
                        3
                    ]
                },
                {
                    "name": "今日",
                    "data": [
                        3,
                        2,
                        2,
                        5,
                        3,
                        7,
                        2,
                        3,
                        6,
                        5,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    ]
                }
            ]
        }
    );

    $('#chart-month-load').highcharts({
        "credits": {
            "enabled": false
        },
        "exporting": {
            "enabled": false
        },
        "chart": {
            "type": "column"
        },
        "title": {
            "text": null
        },
        "legend": {
            "borderWidth": 1,
            "shadow": false
        },
        "xAxis": {
            "tickmarkPlacement": "on",
            "categories": [
                "01",
                "02",
                "03",
                "04",
                "05",
                "06",
                "07",
                "08",
                "09",
                "10",
                "11",
                "12"
            ],
            "title": {
                "text": null
            }
        },
        "yAxis": {
            "title": {
                "text": "单位（kW）"
            }
        },
        "tooltip": {
            "valueSuffix": "kW",
            "shared": true
        },
        "plotOptions": {
            "line": {
                "dataLabels": {
                    "enabled": false
                },
                "enableMouseTracking": true
            }
        },
        "series": [
            {
                "name": "上月",
                "data": [
                    1,
                    3,
                    2,
                    3,
                    5,
                    3,
                    4,
                    6,
                    7,
                    8,
                    5,
                    6
                ]
            },
            {
                "name": "本月",
                "data": [
                    3,
                    1,
                    4,
                    5,
                    2,
                    3,
                    4,
                    null,
                    null,
                    null,
                    null,
                    null
                ]
            }
        ]
    });

});