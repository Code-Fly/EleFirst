<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>
    <%@ include file="/view/common/commonAdminLTE.jsp" %>
    <%@ include file="/view/common/commonHighChart.jsp" %>
    <script type="text/javascript">
        require(["js/main.js"]);
    </script>
    <style>
        .table-condensed {
            color: #fff;
        }

        .table-condensed tr td {
            font-size: small;
        }

        .grid_box_3 {
            width: 25%;
            float: left;
            padding: 10px;
        }

        .small-box {
            margin: 0px !important;
        }

        .unitStyle {
            font-size: 14px;
            margin: 0;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false" align="center" style="overflow-x: hidden;">
    <!-- Small boxes (Stat box) -->
    <div class="easyui-panel" data-options="border:false,"
         style="margin-bottom: 10px; width:95%;height: 180px;overflow: hidden">
        <div class="grid_box_3">
            <!-- small box -->
            <div class="small-box bg-aqua" style="background-color: rgb(53, 156, 182) !important">
                <div class="small-box-footer">
                    <h4><b>基本概况</b></h4>
                </div>
                <div style="padding-left: 5px; padding-right: 5px; height: 95px">
                    <table class="table-condensed" style="width: 100%">
                        <tr style="border-bottom: 1px solid">
                            <td style="width: 50%">主变台数</td>
                            <td style="width: 50%">
                                <h4 style="font-size: 18px; margin: 0px">
                                    <span id="transformers">&nbsp;</span>
                                    <span class="unitStyle">台</span>
                                </h4>
                            </td>
                        </tr>
                        <tr style="border-bottom: 1px solid">
                            <td style="vertical-align: middle;">主变容量</td>
                            <td>
                                <h4 style="font-size: 18px; margin: 0px">
                                    <span id="ratedCapacity">&nbsp;</span>
                                    <span class="unitStyle">kVA</span>
                                </h4>
                            </td>
                        </tr>
                        <tr>
                            <td style="vertical-align: middle;">监测分路个数</td>
                            <td>
                                <h4 style="font-size: 18px; margin: 0px">
                                    <span id="pns">&nbsp;</span>
                                    <span class="unitStyle">个</span>
                                </h4>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="grid_box_3">
            <!-- small box -->
            <div class="small-box bg-green">
                <div class="small-box-footer">
                    <h4><b>电量概况</b></h4>
                </div>
                <div style="padding-left: 5px; padding-right: 5px; height: 95px">
                    <table class="table-condensed" style="width: 100%">
                        <tr style="border-bottom: 1px solid">
                            <td>本月累计电量</td>
                            <td>
                                <h4 style="font-size: 18px; margin: 0px">
                                    <span id="electricityThisMonth">&nbsp;</span>
                                    <span class="unitStyle">kWh</span>
                                </h4>
                            </td>
                        </tr>
                        <tr style="border-bottom: 1px solid">
                            <td style="width: 45%">上月总电量</td>
                            <td style="width: 55%">
                                <h4 style="font-size: 18px; margin: 0px">
                                    <span id="electricityLastMonth">&nbsp;</span>
                                    <span class="unitStyle">kWh</span>
                                </h4>
                            </td>
                        </tr>
                        <tr style="border-bottom: 0px solid">
                            <td style="width: 45%">上上月总电量</td>
                            <td style="width: 55%">
                                <h4 style="font-size: 18px; margin: 0px">
                                    <span id="electricityLastLastMonth">&nbsp;</span>
                                    <span class="unitStyle">kWh</span>
                                </h4>
                            </td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
        <div class="grid_box_3">
            <!-- small box -->
            <div class="small-box bg-yellow">
                <div class="small-box-footer">
                    <h4><b>负荷概况</b></h4>
                </div>
                <div style="padding-left: 5px; padding-right: 5px; height: 95px">
                    <table class="table-condensed" style="width: 100%">
                        <tr style="border-bottom: 1px solid">
                            <td>本月最大负荷</td>
                            <td>
                                <div class="tooltipMaxMonth" data-toggle="tooltip" data-placement="top" title="">
                                    <h4 style="font-size: 18px; margin: 0px">
                                        <span id="maxLoadThisMonth">&nbsp;</span>
                                        <span class="unitStyle">kW</span>
                                    </h4>
                                </div>
                            </td>
                        </tr>
                        <tr style="border-bottom: 1px solid">
                            <td>本年最大负荷</td>
                            <td>
                                <div class="tooltipMaxYear" data-toggle="tooltip" data-placement="top" title="">
                                    <h4 style="font-size: 18px; margin: 0px">
                                        <span id="maxLoadThisYear">&nbsp;</span>
                                        <span class="unitStyle">kW</span>
                                    </h4>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 50%" class="tooltipMaxHistoryName">历史最大负荷</td>
                            <td style="width: 60%">
                                <div class="tooltipMaxHistory" data-toggle="tooltip" data-placement="top" title="">
                                    <h4 style="font-size: 18px; margin: 0px">
                                        <span id="maxLoadTotal">&nbsp;</span>
                                        <span class="unitStyle">kW</span>
                                    </h4>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="grid_box_3">
            <!-- small box -->
            <div class="small-box bg-red">
                <div class="small-box-footer">
                    <h4><b>电量预警</b></h4>
                </div>
                <div style="padding-left: 5px; padding-right: 5px; height: 95px">
                    <table class="table-condensed" style="width: 100%">
                        <tr style="border-bottom: 1px solid">
                            <td>预警次数</td>
                            <td>
                                <h4 id="d10" style="font-size: 18px; margin: 0px"></h4>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="easyui-panel" data-options="iconCls:'icon-chart_line'" title="日负荷曲线(kW)"
         style="margin-bottom: 10px;height: 400px;width: 95%;">
        <div class="easyui-layout" data-options="fit:true">
            <div id="chart-day-load" data-options="region:'center',border:false" style="padding:10px;overflow: hidden">
            </div>
            <div data-options="region:'east',border:false" style="width:200px;padding:10px">
                <p style="color: #0073b7;font-size: medium;">
                    昨日最大负荷
                </p>
                <p>
                    <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-chart_line'"></a><span
                        style="color: green;font-size: medium;" id="yesterday-max-load"></span>
                </p>
                <p>
                    <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-clock'"></a><span
                        style="color: black;font-size: medium;" id="yesterday-max-load-time"></span>
                </p>
                <%----%>
                <p style="color: #0073b7;font-size: medium;">
                    今日最大负荷
                </p>
                <p>
                    <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-chart_line'"></a><span
                        style="color: green;font-size: medium;" id="today-max-load"></span>
                </p>
                <p>
                    <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-clock'"></a><span
                        style="color: black;font-size: medium;" id="today-max-load-time"></span>
                </p>
            </div>
        </div>
    </div>
    <div class="easyui-panel" data-options="iconCls:'icon-chart_bar'" title="月电量柱图(kWh)"
         style="margin-bottom: 10px;height: 400px;width: 95%;">
        <div class="easyui-layout" data-options="fit:true">
            <div id="chart-month-electricity" data-options="region:'center',border:false"
                 style="padding:10px;overflow: hidden">
            </div>
            <div data-options="region:'east',border:false" style="width:200px;padding:10px">
                <p style="color: #0073b7;font-size: medium;">
                    本月累计电量
                </p>
                <p>
                    <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-chart_bar'"></a><span
                        style="color: green;font-size: medium;" id="this-month-total-electricity"></span>
                </p>
                <p style="color: #0073b7;font-size: medium;">
                    同时段累计对比
                </p>
                <p>
                    <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-arrow_up'"></a><span
                        style="color: red;font-size: medium;" id="electricity-rate"></span>
                </p>
                <p>
                    <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-clock'"></a><span
                        style="color: black;font-size: medium;"
                        id="this-month-total-electricity-time"></span>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>