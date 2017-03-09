/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
    var data = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    $("#input-detail-datebox").datebox("calendar").calendar({
        firstDay: 1
    });

    //初始化center中tabs
    $('#tt').tabs({
        border: false,
        onSelect: function (title) {
            if ("负荷" == title) {
                dg('tt1', 'powerdetail/listCurrentDetailPower.do');
            } else if ("示数" == title) {
                dg('tt2', 'powerdetail/listCurrentDisplayDetail.do');
            } else if ("电压" == title) {
                dg('tt3', 'powerdetail/listCurrentDetailPower.do');
            } else if ("电流" == title) {
                dg('tt4', 'powerdetail/listCurrentDetailPower.do');
            } else if ("功率因数" == title) {
                dg('tt5', 'powerdetail/listCurrentDetailPower.do');
            }
        }
    });
    //初始化第一个页面
    dg('tt1', 'powerdetail/listCurrentDetailPower.do');

    var areaId = null;
    var concentratorId = null;
    var pn = null;
    var date = null;

    //初始化south中tabs
    $('#tab2').tabs({
        border: false,
        onSelect: function (title) {
            handerBySouthTabType(title);
        }
    });

    //south区域不同类型tab页面板处理方式
    function handerBySouthTabType(title) {
        if ("负荷" == title) {
            //获取当前选中的行
            getLoadDetailChart({
                areaId: areaId,
                concentratorId: concentratorId,
                pn: pn,
                time: $("#input-detail-datebox").datebox("getValue")
            });
        } else if ("示数" == title) {
            //刷新当前监测点所有示数信息
            $("#dtt2").datagrid({
                url: _ctx + 'powerdetail/listAllDisplayDetailByPn.do',
                pagination: true,
                rownumbers: true,
                pageSize: 10,
                pageList: [2, 10, 20],
                singleSelect: true,
                fit: true,
                border: false,
                queryParams: {
                    areaId: areaId,
                    concentratorId: concentratorId,
                    pn: pn,
                    date: $("#input-detail-datebox").datebox("getValue")
                },
                onLoadError: function () {
                    jError("查询监测点示数信息错误！", {
                        VerticalPosition: 'center',
                        HorizontalPosition: 'center',
                        ShowOverlay: false
                    });
                }
            });
        } else if ("电压" == title) {
            getVoltageDetailChart({
                areaId: areaId,
                concentratorId: concentratorId,
                pn: pn,
                time: $("#input-detail-datebox").datebox("getValue")
            });
        } else if ("电流" == title) {
            getCurrentDetailChart({
                areaId: areaId,
                concentratorId: concentratorId,
                pn: pn,
                time: $("#input-detail-datebox").datebox("getValue")
            });
        } else if ("功率因数" == title) {
            getPowerFactorDetailChart({
                areaId: areaId,
                concentratorId: concentratorId,
                pn: pn,
                time: $("#input-detail-datebox").datebox("getValue")
            });
        }

        //刷新tab
        date = $("#input-detail-datebox").datebox("getValue");
        refreshTab(title, areaId, concentratorId, pn, date);
    }

    //根据不同tab类型展现不同的表格数据
    function handerByTabType(tabType, data) {
        //电压详情
        if ('voltage' == tabType) {
            //设置最大电压a,b,c
            $("#table2 tr:eq(0) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxAVoltage, 1) + "(V)");
            $("#table2 tr:eq(1) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxBVoltage, 1) + "(V)");
            $("#table2 tr:eq(2) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxCVoltage, 1) + "(V)");
            //设置最小电压a,b,c
            $("#table2 tr:eq(0) td:eq(3)").html(DataGridUtils.floatFormatter(data.minAVoltage, 1) + "(V)");
            $("#table2 tr:eq(1) td:eq(3)").html(DataGridUtils.floatFormatter(data.minBVoltage, 1) + "(V)");
            $("#table2 tr:eq(2) td:eq(3)").html(DataGridUtils.floatFormatter(data.minCVoltage, 1) + "(V)");
        } else if ('current' == tabType) {
            //设置最大电流a,b,c
            $("#table3 tr:eq(0) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxACurrent, 3) + "(A)");
            $("#table3 tr:eq(1) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxBCurrent, 3) + "(A)");
            $("#table3 tr:eq(2) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxCCurrent, 3) + "(A)");
            //设置最小电流a,b,c
            $("#table3 tr:eq(0) td:eq(3)").html(DataGridUtils.floatFormatter(data.minACurrent, 3) + "(A)");
            $("#table3 tr:eq(1) td:eq(3)").html(DataGridUtils.floatFormatter(data.minBCurrent, 3) + "(A)");
            $("#table3 tr:eq(2) td:eq(3)").html(DataGridUtils.floatFormatter(data.minCCurrent, 3) + "(A)");
        } else if ('powerfactor' == tabType) {
            //设置最大功率因素a,b,c
            $("#table4 tr:eq(0) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxAPowerFactor, 1) + "(%)");
            $("#table4 tr:eq(1) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxBPowerFactor, 1) + "(%)");
            $("#table4 tr:eq(2) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxCPowerFactor, 1) + "(%)");
            $("#table4 tr:eq(3) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxTotalPowerFactor, 1) + "(%)");
            //设置最小功率因素a,b,c
            $("#table4 tr:eq(0) td:eq(3)").html(DataGridUtils.floatFormatter(data.minAPowerFactor, 1) + "(%)");
            $("#table4 tr:eq(1) td:eq(3)").html(DataGridUtils.floatFormatter(data.minBPowerFactor, 1) + "(%)");
            $("#table4 tr:eq(2) td:eq(3)").html(DataGridUtils.floatFormatter(data.minCPowerFactor, 1) + "(%)");
            $("#table4 tr:eq(3) td:eq(3)").html(DataGridUtils.floatFormatter(data.minTotalPowerFactor, 1) + "(%)");
        } else if ('totalactivepower' == tabType) {
            //最大负荷
            $("#table1 tr:eq(0) td:eq(1)").html(DataGridUtils.floatFormatter(data.maxTotalActivePower, 3) + "(kW)");
            //最小负荷
            $("#table1 tr:eq(1) td:eq(1)").html(DataGridUtils.floatFormatter(data.minTotalActivePower, 3) + "(kW)");
            //平均负荷
            $("#table1 tr:eq(2) td:eq(1)").html(DataGridUtils.floatFormatter(data.avgTotalActivePower, 3) + "(kW)");
            //峰谷差率
            $("#table1 tr:eq(3) td:eq(1)").html(DataGridUtils.floatFormatter(data.peakValleyDifferenceRate, 3) + "(kW)");
            //最大负荷发生时间
            $("#table1 tr:eq(0) td:eq(3)").html(DataGridUtils.dateToMinuteFormatter(data.maxTotalActivePowerTime));
            //最小负荷发生时间
            $("#table1 tr:eq(1) td:eq(3)").html(DataGridUtils.dateToMinuteFormatter(data.minTotalActivePowerTime));
            //峰谷差
            $("#table1 tr:eq(2) td:eq(3)").html(DataGridUtils.floatFormatter(data.peakValleyDifference, 3) + "(kW)");
            //负荷率
            $("#table1 tr:eq(3) td:eq(3)").html(DataGridUtils.floatFormatter(data.loadFactorRate, 1) + "(%)");
        }
    }

    function refreshTab(vtitle, vareaId, vconcentratorId, vpn, vdate) {
        $.ajax({
            url: _ctx + 'powerdetail/queryPowerDetail.do',
            type: "post",//使用post方法访问后台
            dataType: "json",
            cache: false,
            data: {
                tabName: vtitle,
                areaId: vareaId,
                concentratorId: vconcentratorId,
                pn: vpn,
                date: vdate
            },
            success: function (msg) {
                if ("success" == msg.errmsg) {
                    /* jSuccess("查询实时用电信息成功！", {
                     VerticalPosition: 'center',
                     HorizontalPosition: 'center',
                     ShowOverlay: false
                     });*/
                    var data = msg.data;
                    handerByTabType(data.type, data);
                } else if ("failed" == msg.errmsg) {
                    /*jError("查询实时用电信息失败！", {
                     VerticalPosition: 'center',
                     HorizontalPosition: 'center',
                     ShowOverlay: false
                     });*/
                }
            }
        });
    }

    //公用datagrid
    function dg(dgId, url) {
        $("#" + dgId).datagrid({
            url: _ctx + url,
            pagination: true,
            rownumbers: true,
            pageSize: 10,
            pageList: [2, 10, 20],
            singleSelect: true,
            fit: true,
            border: false,
            queryParams: {
                jasonStr: data
            },
            onLoadError: function () {
                jError("查询监测点信息错误！", {
                    VerticalPosition: 'center',
                    HorizontalPosition: 'center',
                    ShowOverlay: false
                });
            },
            onSelect: function (index, row) {
                $('#cc').layout('expand', 'south');
                $('#cc').layout('panel', 'south').panel('setTitle', '当前监测点:' + row.name);

                $("#input-detail-datebox").datebox("clear");

                // 展开的时候有点卡顿，设置个延时
                setTimeout(function () {
                    //选中对应的tab页
                    if ('tt1' == dgId) {
                        singlerow = $('#tt1').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        var dateStr = singlerow.clientoperationtime.split(" ");
                        date = dateStr[0];
                        $("#input-detail-datebox").datebox("setValue", date);
                        $('#tab2').tabs('select', '负荷');
                        //初始化负荷详情
                        handerBySouthTabType('负荷');
                    } else if ('tt2' == dgId) {
                        singlerow = $('#tt2').datagrid('getSelected');
                        areaId = singlerow.areaId33;
                        concentratorId = singlerow.concentratorId33;
                        pn = singlerow.pn33;

                        var dateStr = singlerow.clientoperationtime33.split(" ");
                        date = dateStr[0];
                        $("#input-detail-datebox").datebox("setValue", date)
                        $('#tab2').tabs('select', '示数');
                        //初始化负荷详情
                        handerBySouthTabType('示数');

                    } else if ('tt3' == dgId) {
                        singlerow = $('#tt3').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        var dateStr = singlerow.clientoperationtime.split(" ");
                        date = dateStr[0];
                        $("#input-detail-datebox").datebox("setValue", date);
                        $('#tab2').tabs('select', '电压');
                        //初始化负荷详情
                        handerBySouthTabType('电压');
                    } else if ('tt4' == dgId) {
                        singlerow = $('#tt4').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        var dateStr = singlerow.clientoperationtime.split(" ");
                        date = dateStr[0];
                        $("#input-detail-datebox").datebox("setValue", date);

                        $('#tab2').tabs('select', '电流');
                        //初始化负荷详情
                        handerBySouthTabType('电流');
                    } else if ('tt5' == dgId) {
                        singlerow = $('#tt5').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;

                        var dateStr = singlerow.clientoperationtime.split(" ");
                        date = dateStr[0];
                        $("#input-detail-datebox").datebox("setValue", date)
                        $('#tab2').tabs('select', '功率因数');
                        //初始化负荷详情
                        handerBySouthTabType('功率因数');
                    }
                }, 1000);
            }
        });
    }

    function getLoadDetailChart(row) {
        $.ajax({
            url: _ctx + "system/pn/info/detail.do",
            type: "POST",
            cache: false,
            data: row,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var pnInfo = r.data[0];

                        var time = new Date().format("yyyy-MM-dd");
                        if (row.time != null && row.time != "") {
                            time = row.time;
                        }

                        var node = [];

                        node.push({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn
                        });

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
                            url: _ctx + "power/data/f25/frozen/day/node/list.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(node),
                                startTime: startTime,
                                endTime: endTime
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];

                                        var item = ChartUtils.getLoadAllSeries({}, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data);
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/loadDetailChart.json?bust=" + new Date().getTime(),
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.series = series;
                                        $("#chart-load-detail").highcharts(config);

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
                MaskUtil.unmask();
            },
            complete: function (XMLHttpRequest, textStatus) {
            }
        });
    }

    function getVoltageDetailChart(row) {
        $.ajax({
            url: _ctx + "system/pn/info/detail.do",
            type: "POST",
            cache: false,
            data: row,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var pnInfo = r.data[0];

                        var time = new Date().format("yyyy-MM-dd");
                        if (row.time != null && row.time != "") {
                            time = row.time;
                        }

                        var node = [];

                        node.push({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn
                        });

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
                            url: _ctx + "power/data/f25/frozen/day/node/list.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(node),
                                startTime: startTime,
                                endTime: endTime
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getVoltageAllSeries({
                                            name: "A相",
                                            color: "orange"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "aVoltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageAllSeries({
                                            name: "B相",
                                            color: "green"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "bVoltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageAllSeries({
                                            name: "C相",
                                            color: "red"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "cVoltage");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/voltageDetailChart.json?bust=" + new Date().getTime(),
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        // config.xAxis.categories = ChartUtils.getDailyCategories();
                                        config.series = series;

                                        $("#chart-voltage-detail").highcharts(config);

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
                MaskUtil.unmask();
            },
            complete: function (XMLHttpRequest, textStatus) {
            }
        });
    }

    function getCurrentDetailChart(row) {
        $.ajax({
            url: _ctx + "system/pn/info/detail.do",
            type: "POST",
            cache: false,
            data: row,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var pnInfo = r.data[0];

                        var time = new Date().format("yyyy-MM-dd");
                        if (row.time != null && row.time != "") {
                            time = row.time;
                        }

                        var node = [];

                        node.push({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn
                        });

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
                            url: _ctx + "power/data/f25/frozen/day/node/list.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(node),
                                startTime: startTime,
                                endTime: endTime
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getCurrentAllSeries({
                                            name: "A相",
                                            color: "orange"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "aCurrent");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentAllSeries({
                                            name: "B相",
                                            color: "green"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "bCurrent");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentAllSeries({
                                            name: "C相",
                                            color: "red"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "cCurrent");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/currentDetailChart.json?bust=" + new Date().getTime(),
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.series = series;

                                        $("#chart-current-detail").highcharts(config);

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
                MaskUtil.unmask();
            },
            complete: function (XMLHttpRequest, textStatus) {
            }
        });
    }

    function getPowerFactorDetailChart(row) {
        $.ajax({
            url: _ctx + "system/pn/info/detail.do",
            type: "POST",
            cache: false,
            data: row,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var pnInfo = r.data[0];

                        var time = new Date().format("yyyy-MM-dd");
                        if (row.time != null && row.time != "") {
                            time = row.time;
                        }

                        var node = [];

                        node.push({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn
                        });

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
                            url: _ctx + "power/data/f25/frozen/day/node/list.do",
                            type: "POST",
                            cache: false,
                            data: {
                                node: JSON.stringify(node),
                                startTime: startTime,
                                endTime: endTime
                            },
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getPowerFactorAllSeries({
                                            name: "A相",
                                            color: "orange"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "aPowerfactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorAllSeries({
                                            name: "B相",
                                            color: "green"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "bPowerfactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorAllSeries({
                                            name: "C相",
                                            color: "red"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "cPowerfactor");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/powerFactorDetailChart.json?bust=" + new Date().getTime(),
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.series = series;

                                        $("#chart-power-factor-detail").highcharts(config);

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
                MaskUtil.unmask();
            },
            complete: function (XMLHttpRequest, textStatus) {
            }
        });
    }

    $("#btn-detail-search").linkbutton({
        onClick: function () {
            if (!$("#input-detail-datebox").datebox("isValid")) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }

            var tab = $('#tab2').tabs('getSelected');
            var title = tab.panel("options").title;
            if ("负荷" == title) {
                handerBySouthTabType(title);
            } else if ("示数" == title) {
                handerBySouthTabType(title);
            } else if ("电压" == title) {
                handerBySouthTabType(title);
            } else if ("电流" == title) {
                handerBySouthTabType(title);
            } else if ("功率因数" == title) {
                handerBySouthTabType(title);
            }
        }
    });

    function getPnDetail(node) {
        var pnInfo = $.parseJSON($.ajax({
            url: _ctx + "system/pn/info/detail.do",
            type: "POST",
            data: node,
            async: false
        }).responseText);

        if ("0" == pnInfo.errcode) {
            return pnInfo.data[0];
        } else {
            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(pnInfo.errcode), "info");
            return {};
        }
    }
});

//返回列表
function backlist() {
    $("#input-detail-datebox").datebox("clear");
    $('#cc').layout('collapse', 'south');
}

