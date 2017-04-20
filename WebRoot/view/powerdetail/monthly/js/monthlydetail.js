/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
    var _spinner = new Spinner();

    var data = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    DateBoxUtils.initMonthBox($("#main-input-detail-datebox"));

    DateBoxUtils.initMonthBox($("#input-detail-datebox"));

    //初始化center中tabs
    $('#tt').tabs({
        border: false,
        onSelect: function (title) {
            if ("负荷" == title) {
                dg('tt1', 'monthlypower/listMonthlyLoad.do');
            } else if ("示数" == title) {
                dg('tt2', 'monthlypower/listCurrentDisplayDetail.do');
            } else if ("电压" == title) {
                dg('tt3', 'monthlypower/listMonthlyVoltage.do');
            } else if ("电流" == title) {
                dg('tt4', 'monthlypower/listMonthlyCurrent.do');
            } else if ("功率因数" == title) {
                dg('tt5', 'monthlypower/listMonthlyPowerFactor.do');
            } else if ("需量" == title) {
                dg('tt6', 'monthlypower/listMonthlyDemand.do');
            } else if ("电量" == title) {
                dg('tt7', 'monthlypower/listMonthlyElectricity.do');
            }
        }
    });

    //初始化databox为当前日期
    var d = new Date();
    var dateboxDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-01";
    $("#main-input-detail-datebox").datebox("clear");
    $("#main-input-detail-datebox").datebox("setValue", dateboxDate);
    //初始化第一个页面
    dg('tt1', 'monthlypower/listMonthlyLoad.do');

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
                url: _ctx + 'monthlypower/listDailylyDetailDemand.do',
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
            $("#table1 tr:eq(3) td:eq(1)").html(DataGridUtils.floatFormatter(data.peakValleyDifferenceRate, 1) + "%");
            //最大负荷发生时间
            $("#table1 tr:eq(0) td:eq(3)").html(DataGridUtils.dateToMonthFormatter(data.maxTotalActivePowerTime));
            //最小负荷发生时间
            $("#table1 tr:eq(1) td:eq(3)").html(DataGridUtils.dateToMonthFormatter(data.minTotalActivePowerTime));
            //峰谷差
            $("#table1 tr:eq(2) td:eq(3)").html(DataGridUtils.floatFormatter(data.peakValleyDifference, 3) + "(kw)");
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
            url: _ctx + 'monthlypower/queryMonthlyPower.do',
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
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6) + "-01";
                        $("#input-detail-datebox").datebox("setValue", dateStr);
                        $('#tab2').tabs('select', '负荷');
                        //初始化负荷详情
                        handerBySouthTabType('负荷');
                    } else if ('tt2' == dgId) {
                        singlerow = $('#tt2').datagrid('getSelected');
                        areaId = singlerow.areaId33;
                        concentratorId = singlerow.concentratorId33;
                        pn = singlerow.pn33;
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6) + "-01";
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
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6) + "-01";
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
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6) + "-01";
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
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6) + "-01";
                        $("#input-detail-datebox").datebox("setValue", dateStr);
                        $('#tab2').tabs('select', '功率因数');
                        //初始化负荷详情
                        handerBySouthTabType('功率因数');
                    } else if ('tt6' == dgId) {
                        singlerow = $('#tt6').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6) + "-01";
                        $("#input-detail-datebox").datebox("setValue", dateStr);
                        $('#tab2').tabs('select', '示数');
                        //初始化负荷详情
                        handerBySouthTabType('示数');
                    } else if ('tt7' == dgId) {
                        singlerow = $('#tt7').datagrid('getSelected');
                        areaId = singlerow.areaId;
                        concentratorId = singlerow.concentratorId;
                        pn = singlerow.pn;
                        date = singlerow.days;
                        var dateStr = date.substring(0, 4) + '-' + date.substring(4, 6) + '-' + date.substring(6, 8);
                        $("#input-detail-datebox").datebox("setValue", dateStr);
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
                dg('tt1', 'monthlypower/listMonthlyLoad.do');
            } else if ("示数" == title) {
                //dg('tt2', 'monthlypower/listCurrentDisplayDetail.do');
            } else if ("电压" == title) {
                dg('tt3', 'monthlypower/listMonthlyVoltage.do');
            } else if ("电流" == title) {
                dg('tt4', 'monthlypower/listMonthlyCurrent.do');
            } else if ("功率因数" == title) {
                dg('tt5', 'monthlypower/listMonthlyPowerFactor.do');
            } else if ("需量" == title) {
                dg('tt6', 'monthlypower/listMonthlyDemand.do');
            } else if ("电量" == title) {
                dg('tt7', 'monthlypower/listMonthlyElectricity.do');
            }
        }
    });

    function getLoadDetailChart(row) {

        var time = new Date().format("yyyy-MM");
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

        var startDate = new Date(y, m);
        var endDate = new Date(y, m);
        endDate.setMonth(endDate.getMonth() + 1);

        var startTime = startDate.format('yyyyMM') + "01000000";
        var endTime = endDate.format('yyyyMM') + "01000000";

        $.ajax({
            url: _ctx + "power/data/f25/node/list.do",
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

                        var item = ChartUtils.getLoadAllSeries({
                            name: "最大"
                        }, r.data);
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getLoadAllSeries({
                            name: "最小"
                        }, r.data);
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getLoadAllSeries({
                            name: "平均"
                        }, r.data);
                        item.dataGrouping = {
                            approximation: "average",
                            forced: true
                        };
                        series.push(item);

                        var config = new ChartConfig("view/chart/spline-date-all-load.json");

                        config
                            .setShared(true)
                            .setZoom(false)
                            .setSeries(series)
                            .setDataGroupingByDay();

                        $("#chart-load-detail").highcharts("StockChart", config.getConfig());

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

    function getVoltageDetailChart(row) {

        var time = new Date().format("yyyy-MM");
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

        var startDate = new Date(y, m);
        var endDate = new Date(y, m);
        endDate.setMonth(endDate.getMonth() + 1);

        var startTime = startDate.format('yyyyMM') + "01000000";
        var endTime = endDate.format('yyyyMM') + "01000000";

        $.ajax({
            url: _ctx + "power/data/f25/node/list.do",
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
                            name: "A相最大"
                        }, r.data, "aVoltage");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getVoltageAllSeries({
                            name: "B相最大"
                        }, r.data, "bVoltage");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getVoltageAllSeries({
                            name: "C相最大"
                        }, r.data, "cVoltage");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        //

                        var item = ChartUtils.getVoltageAllSeries({
                            name: "A相最小"
                        }, r.data, "aVoltage");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getVoltageAllSeries({
                            name: "B相最小"
                        }, r.data, "bVoltage");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getVoltageAllSeries({
                            name: "C相最小"
                        }, r.data, "cVoltage");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var config = new ChartConfig("view/chart/spline-date-all-voltage.json");

                        config
                            .setShared(true)
                            .setZoom(false)
                            .setSeries(series)
                            .setDataGroupingByDay();

                        $("#chart-voltage-detail").highcharts("StockChart", config.getConfig());


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

    function getCurrentDetailChart(row) {
        var time = new Date().format("yyyy-MM");
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

        var startDate = new Date(y, m);
        var endDate = new Date(y, m);
        endDate.setMonth(endDate.getMonth() + 1);

        var startTime = startDate.format('yyyyMM') + "01000000";
        var endTime = endDate.format('yyyyMM') + "01000000";

        $.ajax({
            url: _ctx + "power/data/f25/node/list.do",
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
                            name: "A相最大"
                        }, r.data, "aCurrent");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getCurrentAllSeries({
                            name: "B相最大"
                        }, r.data, "bCurrent");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getCurrentAllSeries({
                            name: "C相最大"
                        }, r.data, "cCurrent");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        //

                        var item = ChartUtils.getCurrentAllSeries({
                            name: "A相最小"
                        }, r.data, "aCurrent");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getCurrentAllSeries({
                            name: "B相最小"
                        }, r.data, "bCurrent");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getCurrentAllSeries({
                            name: "C相最小"
                        }, r.data, "cCurrent");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var config = new ChartConfig("view/chart/spline-date-all-current.json");

                        config
                            .setShared(true)
                            .setZoom(false)
                            .setSeries(series)
                            .setDataGroupingByDay();

                        $("#chart-current-detail").highcharts("StockChart", config.getConfig());


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

    function getPowerFactorDetailChart(row) {

        var time = new Date().format("yyyy-MM");
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

        var startDate = new Date(y, m);
        var endDate = new Date(y, m);
        endDate.setMonth(endDate.getMonth() + 1);

        var startTime = startDate.format('yyyyMM') + "01000000";
        var endTime = endDate.format('yyyyMM') + "01000000";

        $.ajax({
            url: _ctx + "power/data/f25/node/list.do",
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
                            name: "A相最大"
                        }, r.data, "aPowerfactor");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getPowerFactorAllSeries({
                            name: "B相最大"
                        }, r.data, "bPowerfactor");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getPowerFactorAllSeries({
                            name: "C相最大"
                        }, r.data, "cPowerfactor");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        //

                        var item = ChartUtils.getPowerFactorAllSeries({
                            name: "A相最小"
                        }, r.data, "aPowerfactor");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getPowerFactorAllSeries({
                            name: "B相最小"
                        }, r.data, "bPowerfactor");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getPowerFactorAllSeries({
                            name: "C相最小"
                        }, r.data, "cPowerfactor");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        //

                        var item = ChartUtils.getPowerFactorAllSeries({
                            name: "总最大"
                        }, r.data, "totalpowerfactor");
                        item.dataGrouping = {
                            approximation: "high",
                            forced: true
                        };
                        series.push(item);

                        var item = ChartUtils.getPowerFactorAllSeries({
                            name: "总最小"
                        }, r.data, "totalpowerfactor");
                        item.dataGrouping = {
                            approximation: "low",
                            forced: true
                        };
                        series.push(item);

                        var config = new ChartConfig("view/chart/spline-date-all-power-factor.json");

                        config
                            .setShared(true)
                            .setZoom(false)
                            .setSeries(series)
                            .setDataGroupingByDay();

                        $("#chart-power-factor-detail").highcharts("StockChart", config.getConfig());


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

    function getElectricityDetailChart(row) {

        var time = new Date().format("yyyy-MM");
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

        var startDate = new Date(y, m);
        var endDate = new Date(y, m);
        endDate.setMonth(endDate.getMonth() + 1);

        var timeList = [];
        timeList.push({
            startTime: startDate.format('yyyyMM') + "01000000",
            endTime: endDate.format('yyyyMM') + "01000000"
        });

        $.ajax({
            url: _ctx + "power/data/f5/node/time/sum.do",
            type: "POST",
            cache: false,
            data: {
                node: JSON.stringify(node),
                time: JSON.stringify(timeList)
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var series = [];

                        var item = ChartUtils.getF5AllSeries({
                            name: startDate.format("yyyy-MM-dd") + "~" + endDate.format("yyyy-MM-dd")
                        }, r.data[0]);

                        item.dataGrouping = {
                            approximation: ChartUtils.approximations.sum,
                            forced: true
                        };
                        series.push(item);

                        var config = new ChartConfig("view/chart/column-date-all-electricity.json");

                        config
                            .setShared(false)
                            .setZoom(false)
                            .setCrossHairSnap(false)
                            .setSeries(series)
                            .setDataGroupingByDay();


                        $("#chart-electricity-detail").highcharts("StockChart", config.getConfig());

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
            jError("请求失败！" + ErrUtils.getMsg(pnInfo.errcode));
            return {};
        }
    }
});

//返回列表
function backlist() {
    $("#input-detail-datebox").datebox("clear");
    $('#cc').layout('collapse', 'south');
}



