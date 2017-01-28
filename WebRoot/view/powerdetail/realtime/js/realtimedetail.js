/**
 * Created by barrie on 17/1/27.
 */
$(document).ready(function () {
    var data = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);


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

    //初始化south中tabs
    $('#tab2').tabs({
        border: false,
        onSelect: function (title) {
            var singlerow = null;
            if ("负荷" == title) {
                //获取当前选中的行
                singlerow = $('#tt1').datagrid('getSelected');
                getLoadDetailChart({
                    areaId: singlerow.areaId,
                    concentratorId: singlerow.concentratorId,
                    pn: singlerow.pn,
                    time: null,
                    name: title
                });
            } else if ("示数" == title) {
                singlerow = $('#tt2').datagrid('getSelected');
            } else if ("电压" == title) {
                singlerow = $('#tt3').datagrid('getSelected');
            } else if ("电流" == title) {
                singlerow = $('#tt4').datagrid('getSelected');
            } else if ("功率因数" == title) {
                singlerow = $('#tt5').datagrid('getSelected');
            }


            $.ajax({
                url: _ctx + 'powerdetail/queryPowerDetail.do',
                type: "post",//使用post方法访问后台
                dataType: "json",
                cache: false,
                data: {
                    tabName: title,
                    areaId: singlerow.areaId,
                    concentratorId: singlerow.concentratorId,
                    pn: singlerow.pn
                },
                success: function (msg) {
                    if ("success" == msg.errmsg) {
                        jSuccess("查询实时用电信息成功！", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                        var data = msg.data;
                        handerByTabType(data.type, data);
                    } else if ("failed" == msg.errmsg) {
                        jError("查询实时用电信息失败！", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                    }
                }
            });
        }
    });

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
            //设置最大电压a,b,c
            $("#table3 tr:eq(0) td:eq(1)").html("" + data.maxACurrent);
            $("#table3 tr:eq(1) td:eq(1)").html("" + data.maxBCurrent);
            $("#table3 tr:eq(2) td:eq(1)").html("" + data.maxCCurrent);
            //设置最小电压a,b,c
            $("#table3 tr:eq(0) td:eq(3)").html("" + data.minACurrent);
            $("#table3 tr:eq(1) td:eq(3)").html("" + data.minBCurrent);
            $("#table3 tr:eq(2) td:eq(3)").html("" + data.minCCurrent);
        }
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
                $('#cc').layout('panel', 'south').panel('setTitle', '当前监测点:' + row.pn);

                //选中对应的tab页
                if ('tt1' == dgId) {
                    $('#tab2').tabs('select', '负荷');
                    getLoadDetailChart({
                        areaId: row.areaId,
                        concentratorId: row.concentratorId,
                        pn: row.pn,
                        time: $("#input-detail-datebox").datebox("getValue"),
                        name: "监测点" + row.pn
                    });
                } else if ('tt2' == dgId) {
                    $('#tab2').tabs('select', '示数');
                } else if ('tt3' == dgId) {
                    $('#tab2').tabs('select', '电压');
                } else if ('tt4' == dgId) {
                    $('#tab2').tabs('select', '电流');
                } else if ('tt5' == dgId) {
                    $('#tab2').tabs('select', '功率因数');
                }
            }
        });
    }

    function getLoadDetailChart(row) {
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
        paramChart.time.push(
            new Date(time).format('yyyyMMdd') + "000000"
        )

        $.ajax({
            url: _ctx + "poweranalysis/comparison/daily/chart.do",
            type: "POST",
            cache: false,
            contentType: "text/plain;charset=UTF-8",
            data: JSON.stringify(paramChart),
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        var series = [];
                        var item = ChartUtils.getLoadDailySeries({
                            areaId: row.areaId,
                            concentratorId: row.concentratorId,
                            pn: row.pn,
                            pt: 100,
                            ct: 100,
                            name: row.name
                        }, new Date(time).format('yyyyMMdd') + "000000", r.data);
                        series.push(item);

                        $("#chart-load-detail").highcharts();

                        var config = $.parseJSON($.ajax({
                            url: "data/loadDetailChart.json",
                            type: "GET",
                            async: false
                        }).responseText);

                        config.series = series;

                        $("#chart-load-detail").highcharts(config);

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

    $("#btn-detail-search").linkbutton({
        onClick: function () {
            if (!$("#input-detail-datebox").datebox("isValid")) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }
            var tab = $('#tab2').tabs('getSelected');
            var title = tab.panel("options").title;
            if ("负荷" == title) {
                //获取当前选中的行
                var row = $('#tt1').datagrid('getSelected');
                getLoadDetailChart({
                    areaId: row.areaId,
                    concentratorId: row.concentratorId,
                    pn: row.pn,
                    time: $("#input-detail-datebox").datebox("getValue"),
                    name: title
                });
            } else if ("示数" == title) {
                var row = $('#tt2').datagrid('getSelected');
            } else if ("电压" == title) {
                var row = $('#tt3').datagrid('getSelected');
            } else if ("电流" == title) {
                var row = $('#tt4').datagrid('getSelected');
            } else if ("功率因数" == title) {
                var row = $('#tt5').datagrid('getSelected');
            }
        }
    });
});

//返回列表
function backlist() {
    $('#cc').layout('collapse', 'south');
}