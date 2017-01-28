/**
 * Created by VM on 1/25/2017.
 */
$(document).ready(function () {
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