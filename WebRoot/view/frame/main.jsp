<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>
    <%@ include file="/view/common/commonAdminLTE.jsp" %>
    <style>
        .table-condensed, .table-condensed {
            color: #fff;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false," style="height:180px;overflow: hidden;">
    <!-- Small boxes (Stat box) -->
    <div class="container_12">
        <div class="grid_3">
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
                                <h4 id="d1" style="font-size: 18px; margin: 0px"></h4>
                            </td>
                        </tr>
                        <tr style="border-bottom: 1px solid">
                            <td style="vertical-align: middle;">主变容量</td>
                            <td>
                                <h4 id="d2" style="font-size: 18px; margin: 0px"></h4>
                            </td>
                        </tr>
                        <tr>
                            <td style="vertical-align: middle;">监测分路个数</td>
                            <td>
                                <h4 id="d3" style="font-size: 18px; margin: 0px"></h4>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="grid_3">
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
                                <h4 id="d5" style="font-size: 18px; margin: 0px"></h4>
                            </td>
                        </tr>
                        <tr style="border-bottom: 1px solid">
                            <td style="width: 45%">上月总电量</td>
                            <td style="width: 55%">
                                <h4 id="d4" style="font-size: 18px; margin: 0px"></h4>
                            </td>
                        </tr>
                        <tr style="border-bottom: 0px solid">
                            <td style="width: 45%">上上月总电量</td>
                            <td style="width: 55%">
                                <h4 id="d6" style="font-size: 18px; margin: 0px"></h4>
                            </td>
                        </tr>

                    </table>
                </div>
            </div>
        </div>
        <div class="grid_3">
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
                                    <h4 id="d8" style="font-size: 18px; margin: 0px"></h4>
                                </div>
                            </td>
                        </tr>
                        <tr style="border-bottom: 1px solid">
                            <td>本年最大负荷</td>
                            <td>
                                <div class="tooltipMaxYear" data-toggle="tooltip" data-placement="top" title="">
                                    <h4 id="d9" style="font-size: 18px; margin: 0px"></h4>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 50%" class="tooltipMaxHistoryName">历史最大负荷</td>
                            <td style="width: 60%">
                                <div class="tooltipMaxHistory" data-toggle="tooltip" data-placement="top" title="">
                                    <h4 id="d7" style="font-size: 18px; margin: 0px"></h4>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="grid_3">
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
</div>
<div data-options="region:'center',border:false" style="padding-left: 5px;padding-right: 2px;">

</div>


</body>
</html>