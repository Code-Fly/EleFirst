/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
    var data = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    $("#main-input-detail-datebox").datebox("calendar").calendar({
        firstDay: 1
    });

    $("#input-detail-datebox").datebox("calendar").calendar({
        firstDay: 1
    });

    //初始化center中tabs
    $('#tt').tabs({
        border: false,
        onSelect: function (title) {
            if ("负荷" == title) {
                dg('tt1', 'dailypower/listDailyLoad.do');
            } else if ("示数" == title) {
                //dg('tt2', 'powerdetail/listCurrentDisplayDetail.do');
            } else if ("电压" == title) {
                dg('tt3', 'dailypower/listDailyVoltage.do');
            } else if ("电流" == title) {
                dg('tt4', 'dailypower/listDailyCurrent.do');
            } else if ("功率因数" == title) {
                dg('tt5', 'dailypower/listDailyPowerFactor.do');
            }
        }
    });
    
    $('#cc1').combobox({
		    url:'data/combobox_data.json',
		    valueField:'id',
		    textField:'text',
		    method:'get',
		    onSelect: function(rec){
            
        }
		});
		
		
		

    //初始化databox为当前日期
    var d = new Date();
    var dateboxDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
    $("#main-input-detail-datebox").datebox("clear");
    $("#main-input-detail-datebox").datebox("setValue", dateboxDate);
    //初始化第一个页面
    dg('tt1', 'dailypower/listDailyLoad.do');

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
            $("#table2 tr:eq(0) td:eq(1)").html("" + data.maxAVoltage);
            $("#table2 tr:eq(1) td:eq(1)").html("" + data.maxBVoltage);
            $("#table2 tr:eq(2) td:eq(1)").html("" + data.maxCVoltage);
            //设置最小电压a,b,c
            $("#table2 tr:eq(0) td:eq(3)").html("" + data.minAVoltage);
            $("#table2 tr:eq(1) td:eq(3)").html("" + data.minBVoltage);
            $("#table2 tr:eq(2) td:eq(3)").html("" + data.minCVoltage);
        } else if ('current' == tabType) {
            //设置最大电流a,b,c
            $("#table3 tr:eq(0) td:eq(1)").html("" + data.maxACurrent);
            $("#table3 tr:eq(1) td:eq(1)").html("" + data.maxBCurrent);
            $("#table3 tr:eq(2) td:eq(1)").html("" + data.maxCCurrent);
            //设置最小电流a,b,c
            $("#table3 tr:eq(0) td:eq(3)").html("" + data.minACurrent);
            $("#table3 tr:eq(1) td:eq(3)").html("" + data.minBCurrent);
            $("#table3 tr:eq(2) td:eq(3)").html("" + data.minCCurrent);
        } else if ('powerfactor' == tabType) {
            //设置最大功率因素a,b,c
            $("#table4 tr:eq(0) td:eq(1)").html("" + data.maxAPowerFactor);
            $("#table4 tr:eq(1) td:eq(1)").html("" + data.maxBPowerFactor);
            $("#table4 tr:eq(2) td:eq(1)").html("" + data.maxCPowerFactor);
            $("#table4 tr:eq(3) td:eq(1)").html("" + data.maxTotalPowerFactor);
            //设置最小功率因素a,b,c
            $("#table4 tr:eq(0) td:eq(3)").html("" + data.minAPowerFactor);
            $("#table4 tr:eq(1) td:eq(3)").html("" + data.minBPowerFactor);
            $("#table4 tr:eq(2) td:eq(3)").html("" + data.minCPowerFactor);
            $("#table4 tr:eq(3) td:eq(3)").html("" + data.minTotalPowerFactor);
        } else if ('totalactivepower' == tabType) {
            //最大负荷
            $("#table1 tr:eq(0) td:eq(1)").html("" + data.maxTotalActivePower);
            //最小负荷
            $("#table1 tr:eq(1) td:eq(1)").html("" + data.minTotalActivePower);
            //平均负荷
            $("#table1 tr:eq(2) td:eq(1)").html("" + data.avgTotalActivePower);
            //峰谷差率
            $("#table1 tr:eq(3) td:eq(1)").html("" + data.peakValleyDifferenceRate);
            //最大负荷发生时间
            $("#table1 tr:eq(0) td:eq(3)").html("" + data.maxTotalActivePowerTime);
            //最小负荷发生时间
            $("#table1 tr:eq(1) td:eq(3)").html("" + data.minTotalActivePowerTime);
            //峰谷差
            $("#table1 tr:eq(2) td:eq(3)").html("" + data.peakValleyDifference);
            //负荷率
            $("#table1 tr:eq(3) td:eq(3)").html("" + data.loadFactorRate);
        }
    }

    function Subtr(arg1, arg2) {
        var r1, r2, m, n;
        try {
            r1 = arg1.toString().split(".")[1].length
        } catch (e) {
            r1 = 0
        }
        try {
            r2 = arg2.toString().split(".")[1].length
        } catch (e) {
            r2 = 0
        }
        m = Math.pow(10, Math.max(r1, r2));
        n = (r1 >= r2) ? r1 : r2;
        return ((arg1 * m - arg2 * m) / m).toFixed(n);
    }

    function refreshTab(vtitle, vareaId, vconcentratorId, vpn, vdate) {
        $.ajax({
            url: _ctx + 'dailypower/queryDailyPower.do',
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
            queryParams: {
                jasonStr: data,
                date: $("#main-input-detail-datebox").datebox("getValue")
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
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6)+ '-' + date.substring(6, 8);
                        $("#input-detail-datebox").datebox("setValue", dateStr);
                        $('#tab2').tabs('select', '负荷');
                        //初始化负荷详情
                        // refreshTab('负荷', areaId, concentratorId, pn, $("#input-detail-datebox").datebox("getValue"));
                        handerBySouthTabType('负荷');
                    } else if ('tt2' == dgId) {
                        singlerow = $('#tt2').datagrid('getSelected');
                        areaId = singlerow.areaId33;
                        concentratorId = singlerow.concentratorId33;
                        pn = singlerow.pn33;
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6)+ '-' + date.substring(6, 8);
                        $("#input-detail-datebox").datebox("setValue", dateStr);
                        $('#tab2').tabs('select', '示数');
                        //初始化负荷详情
                        handerBySouthTabType('示数');
                    } else if ('tt3' == dgId) {
                        singlerow = $('#tt3').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6)+ '-' + date.substring(6, 8);
                        $("#input-detail-datebox").datebox("setValue", dateStr);
                        $('#tab2').tabs('select', '电压');
                        //初始化负荷详情
                        handerBySouthTabType('电压');
                    } else if ('tt4' == dgId) {
                        singlerow = $('#tt4').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6)+ '-' + date.substring(6, 8);
                        $("#input-detail-datebox").datebox("setValue", dateStr);
                        $('#tab2').tabs('select', '电流');
                        //初始化负荷详情
                        handerBySouthTabType('电流');
                    } else if ('tt5' == dgId) {
                        singlerow = $('#tt5').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6)+ '-' + date.substring(6, 8);
                        $("#input-detail-datebox").datebox("setValue", dateStr);
                        $('#tab2').tabs('select', '功率因数');
                        //初始化负荷详情
                        handerBySouthTabType('功率因数');
                    }
                }, 1000);
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

    $("#main-input-btn-detail-search").linkbutton({
        onClick: function () {
            if (!$("#main-input-detail-datebox").datebox("isValid")) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }

            var tab = $('#tt').tabs('getSelected');
            var title = tab.panel("options").title;
            if ("负荷" == title) {
                dg('tt1', 'dailypower/listDailyLoad.do');
            } else if ("示数" == title) {
                dg('tt2', 'powerdetail/listCurrentDisplayDetail.do');
            } else if ("电压" == title) {
                //dg('tt3', 'dailypower/listDailyVoltage.do');
            } else if ("电流" == title) {
                dg('tt4', 'dailypower/listDailyCurrent.do');
            } else if ("功率因数" == title) {
                dg('tt5', 'dailypower/listDailyPowerFactor.do');
            }
        }
    });

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

                        var paramChart = {
                            node: [],
                            time: []
                        }

                        paramChart.node.push({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn
                        })

                        var ss = time.split('-');
                        var y = parseInt(ss[0], 10);
                        var m = parseInt(ss[1], 10) - 1;
                        var d = parseInt(ss[2], 10);

                        paramChart.time.push(
                            new Date(y, m, d).format('yyyyMMdd') + "000000"
                        )

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/load/daily/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];

                                        var item = ChartUtils.getLoadDailyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data);
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/loadDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.xAxis.categories = ChartUtils.getDailyCategories();
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

                        var paramChart = {
                            node: [],
                            time: []
                        }

                        paramChart.node.push({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn
                        })

                        var ss = time.split('-');
                        var y = parseInt(ss[0], 10);
                        var m = parseInt(ss[1], 10) - 1;
                        var d = parseInt(ss[2], 10);

                        paramChart.time.push(
                            new Date(y, m, d).format('yyyyMMdd') + "000000"
                        )

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/voltage/daily/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getVoltageDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxA_Voltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxB_Voltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxC_Voltage");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/voltageDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.xAxis.categories = ChartUtils.getDailyCategories();
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

                        var paramChart = {
                            node: [],
                            time: []
                        }

                        paramChart.node.push({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn
                        })

                        var ss = time.split('-');
                        var y = parseInt(ss[0], 10);
                        var m = parseInt(ss[1], 10) - 1;
                        var d = parseInt(ss[2], 10);

                        paramChart.time.push(
                            new Date(y, m, d).format('yyyyMMdd') + "000000"
                        )

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/current/daily/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getCurrentDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxA_Current");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxB_Current");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxC_Current");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/currentDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.xAxis.categories = ChartUtils.getDailyCategories();
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

                        var paramChart = {
                            node: [],
                            time: []
                        }

                        paramChart.node.push({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn
                        })

                        var ss = time.split('-');
                        var y = parseInt(ss[0], 10);
                        var m = parseInt(ss[1], 10) - 1;
                        var d = parseInt(ss[2], 10);

                        paramChart.time.push(
                            new Date(y, m, d).format('yyyyMMdd') + "000000"
                        )

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/powerFactor/daily/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getPowerFactorDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxA_PowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxB_PowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorDailySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相"
                                        }, new Date(y, m, d).format('yyyyMMdd') + "000000", r.data, "maxC_PowerFactor");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/powerFactorDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.xAxis.categories = ChartUtils.getDailyCategories();
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



