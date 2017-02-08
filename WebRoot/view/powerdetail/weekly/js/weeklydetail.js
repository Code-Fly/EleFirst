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
                dg('tt1', 'weeklypower/listWeeklyLoad.do');
            } else if ("示数" == title) {
                //dg('tt2', 'weeklypower/listCurrentDisplayDetail.do');
            } else if ("电压" == title) {
                dg('tt3', 'weeklypower/listWeeklyVoltage.do');
            } else if ("电流" == title) {
                dg('tt4', 'weeklypower/listWeeklyCurrent.do');
            } else if ("功率因数" == title) {
                dg('tt5', 'weeklypower/listWeeklyPowerFactor.do');
            } else if ("需量" == title) {
                dg('tt6', 'weeklypower/listWeeklyDemand.do');
            } else if ("电量" == title) {
                dg('tt7', 'weeklypower/listWeeklyElectricity.do');
            }
        }
    });

    //初始化databox为当前日期
    var d = new Date();
    var dateboxDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
    $("#main-input-detail-datebox").datebox("clear");
    $("#main-input-detail-datebox").datebox("setValue", dateboxDate);
    //初始化第一个页面
    dg('tt1', 'weeklypower/listWeeklyLoad.do');

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
                url: _ctx + 'weeklypower/listWeeklyDetailDemand.do',
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
        } else if ("电量" == title) {
            getElectricityDetailChart({
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
            $("#table1 tr:eq(3) td:eq(1)").html(DataGridUtils.floatFormatter(data.peakValleyDifferenceRate, 1) + "(%)");
            //最大负荷发生时间
            $("#table1 tr:eq(0) td:eq(3)").html(DataGridUtils.dateToDayFormatter(data.maxTotalActivePowerTime));
            //最小负荷发生时间
            $("#table1 tr:eq(1) td:eq(3)").html(DataGridUtils.dateToDayFormatter(data.minTotalActivePowerTime));
            //峰谷差
            $("#table1 tr:eq(2) td:eq(3)").html(DataGridUtils.floatFormatter(data.peakValleyDifference, 3) + "(kW)");
            //负荷率
            $("#table1 tr:eq(3) td:eq(3)").html(DataGridUtils.floatFormatter(data.loadFactorRate, 1) + "(%)");
        } else if ("electricity" == tabType) {
            //总电量
            $("#dtt4 tr:eq(0) td:eq(1)").html(DataGridUtils.floatFormatter(data.totalpositiveactivePower, 4) + "(kWh)");
            //峰电量
            $("#dtt4 tr:eq(1) td:eq(1)").html(DataGridUtils.floatFormatter(data.rateseq1, 4) + "(kWh)");

            //谷电量
            $("#dtt4 tr:eq(2) td:eq(1)").html(DataGridUtils.floatFormatter(data.rateseq2, 4) + "(kWh)");

            //平电量
            $("#dtt4 tr:eq(1) td:eq(3)").html(DataGridUtils.floatFormatter(data.rateseq3, 4) + "(kWh)");
            //尖峰电量
            $("#dtt4 tr:eq(2) td:eq(3)").html(DataGridUtils.floatFormatter(data.rateseq4, 4) + "(kWh)");
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
            url: _ctx + 'weeklypower/queryWeeklyPower.do',
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
                        date = singlerow.weekstart;
                        $("#input-detail-datebox").datebox("setValue", date);
                        $('#tab2').tabs('select', '负荷');
                        //初始化负荷详情
                        handerBySouthTabType('负荷');
                    } else if ('tt6' == dgId) {
                        singlerow = $('#tt6').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.weekstart;
                        $("#input-detail-datebox").datebox("setValue", date);
                        $('#tab2').tabs('select', '示数');
                        //初始化负荷详情
                        handerBySouthTabType('示数');
                    } else if ('tt3' == dgId) {
                        singlerow = $('#tt3').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.weekstart;
                        $("#input-detail-datebox").datebox("setValue", date);
                        $('#tab2').tabs('select', '电压');
                        //初始化负荷详情
                        handerBySouthTabType('电压');
                    } else if ('tt4' == dgId) {
                        singlerow = $('#tt4').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.weekstart;
                        $("#input-detail-datebox").datebox("setValue", date);
                        $('#tab2').tabs('select', '电流');
                        //初始化负荷详情
                        handerBySouthTabType('电流');
                    } else if ('tt5' == dgId) {
                        singlerow = $('#tt5').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.weekstart;
                        $("#input-detail-datebox").datebox("setValue", date);
                        $('#tab2').tabs('select', '功率因数');
                        //初始化负荷详情
                        handerBySouthTabType('功率因数');
                    } else if ('tt7' == dgId) {
                        singlerow = $('#tt7').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.weekstart;
                        $("#input-detail-datebox").datebox("setValue", date);
                        $('#tab2').tabs('select', '电量');
                        //初始化负荷详情
                        handerBySouthTabType('电量');
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
            } else if ("电量" == title) {
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
                dg('tt1', 'weeklypower/listWeeklyLoad.do');
            } else if ("示数" == title) {
                //dg('tt2', 'weeklypower/listCurrentDisplayDetail.do');
            } else if ("电压" == title) {
                dg('tt3', 'weeklypower/listWeeklyVoltage.do');
            } else if ("电流" == title) {
                dg('tt4', 'weeklypower/listWeeklyCurrent.do');
            } else if ("功率因数" == title) {
                dg('tt5', 'weeklypower/listWeeklyPowerFactor.do');
            } else if ("需量" == title) {
                dg('tt6', 'weeklypower/listWeeklyDemand.do');
            } else if ("电量" == title) {
                dg('tt7', 'monthlypower/listMonthlyElectricity.do');
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

                        var s = new Date(y, m, d);
                        var w = TimeUtils.weekFromODBC(s.getDay());

                        s.setDate(s.getDate() - w);

                        paramChart.time.push(
                            s.format("yyyyMMdd") + "000000"
                        );

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/load/weekly/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];

                                        var item = ChartUtils.getLoadWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "最高"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxTotalActivePower");
                                        series.push(item);

                                        var item = ChartUtils.getLoadWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "最低"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minTotalActivePower");
                                        series.push(item);

                                        var item = ChartUtils.getLoadWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "平均"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "avgTotalActivePower");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/loadDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);


                                        config.xAxis.categories = ChartUtils.getWeeklyCategories();
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

                        var s = new Date(y, m, d);
                        var w = TimeUtils.weekFromODBC(s.getDay());

                        s.setDate(s.getDate() - w);

                        paramChart.time.push(
                            s.format("yyyyMMdd") + "000000"
                        );

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/voltage/weekly/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getVoltageWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxA_Voltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxB_Voltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxC_Voltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minA_Voltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minB_Voltage");
                                        series.push(item);

                                        var item = ChartUtils.getVoltageWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minC_Voltage");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/voltageDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.xAxis.categories = ChartUtils.getWeeklyCategories();
                                        config.series = series;

                                        // $.messager.alert("操作提示", JSON.stringify(config));
                                        // $.messager.alert("操作提示", JSON.stringify(r.data));

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

                        var s = new Date(y, m, d);
                        var w = TimeUtils.weekFromODBC(s.getDay());

                        s.setDate(s.getDate() - w);

                        paramChart.time.push(
                            s.format("yyyyMMdd") + "000000"
                        );

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/current/weekly/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getCurrentWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxA_Current");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxB_Current");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxC_Current");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minA_Current");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minB_Current");
                                        series.push(item);

                                        var item = ChartUtils.getCurrentWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minC_Current");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/currentDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.xAxis.categories = ChartUtils.getWeeklyCategories();
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

                        var s = new Date(y, m, d);
                        var w = TimeUtils.weekFromODBC(s.getDay());

                        s.setDate(s.getDate() - w);

                        paramChart.time.push(
                            s.format("yyyyMMdd") + "000000"
                        );

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/powerFactor/weekly/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getPowerFactorWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxA_PowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxB_PowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxC_PowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "A相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minA_PowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "B相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minB_PowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "C相最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minC_PowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "总最大"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "maxTotalPowerFactor");
                                        series.push(item);

                                        var item = ChartUtils.getPowerFactorWeeklyDetailSeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct,
                                            name: "总最小"
                                        }, s.format('yyyyMMdd') + "000000", r.data, "minTotalPowerFactor");
                                        series.push(item);

                                        var config = $.parseJSON($.ajax({
                                            url: "data/powerFactorDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.xAxis.categories = ChartUtils.getWeeklyCategories();
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

    function getElectricityDetailChart(row) {
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

                        var s = new Date(y, m, d);
                        var w = TimeUtils.weekFromODBC(s.getDay());

                        s.setDate(s.getDate() - w);

                        paramChart.time.push(
                            s.format("yyyyMMdd") + "000000"
                        );

                        $.ajax({
                            url: _ctx + "poweranalysis/comparison/electricity/weekly/chart.do",
                            type: "POST",
                            cache: false,
                            contentType: "text/plain;charset=UTF-8",
                            data: JSON.stringify(paramChart),
                            success: function (r) {
                                if (r.hasOwnProperty("errcode")) {
                                    if ("0" == r.errcode) {
                                        var series = [];
                                        var item = ChartUtils.getElectricityWeeklySeries({
                                            areaId: row.areaId,
                                            concentratorId: row.concentratorId,
                                            pn: row.pn,
                                            pt: pnInfo.pt,
                                            ct: pnInfo.ct
                                        }, s.format('yyyyMMdd') + "000000", r.data);
                                        series.push(item);


                                        var config = $.parseJSON($.ajax({
                                            url: "data/electricityDetailChart.json",
                                            type: "GET",
                                            async: false
                                        }).responseText);

                                        config.xAxis.categories = ChartUtils.getWeeklyCategories();
                                        config.series = series;

                                        $("#chart-electricity-detail").highcharts(config);

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
            } else if ("电量" == title) {
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



