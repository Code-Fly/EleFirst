<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/commonHighChart.jsp" %>
    <script type="text/javascript">
        require(["js/realtimedetail.js"]);
    </script>
</head>
<body class="easyui-layout" id="cc">
<div data-options="region:'center',border:false" style="width:100%;overflow: hidden"">
    <div id="tt" class="easyui-tabs" data-options="border:false" fit="true">
        <div title="负荷" style="display:none;overflow: hidden">
            <table id="tt1" style="display:none" class="easyui-datagrid" fit="true" data-options="border:false">
                <thead>
                <tr>
                    <!-- <th field="id" hidden="true"></th>
                    <th field="ck" data-options="checkbox:true"></th> -->
                    <%--<th rowspan="2" field="areaId" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="80" align="center">监测点</th>
                    <th colspan="4">有功负荷(kW)</th>
                    <th colspan="4">无功负荷(kW)</th>
                    <th rowspan="2" field="clientoperationtime" width="120" align="center">抄表时刻</th>
                </tr>
                <tr>
                    <th field="totalactivepower" width="80" align="center">总</th>
                    <th field="aActivepower" width="80" align="center">Pu/PI</th>
                    <th field="bActivepower" width="80" align="center">Pv</th>
                    <th field="cActivepower" width="80" align="center">Pw/PII</th>
                    <th field="totalreactivepower" width="80" align="center">总</th>
                    <th field="aReactivepower" width="80" align="center">U相</th>
                    <th field="bReactivepower" width="80" align="center">V相</th>
                    <th field="cReactivepower" width="80" align="center">W相</th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="示数" style="display:none;overflow: hidden">
            <table id="tt2" class="easyui-datagrid" fit="true" data-options="border:false">
                <thead>
                <tr>
                    <%--<th rowspan="2" field="areaId33" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId33" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn33" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="80" align="center">监测点</th>
                    <th colspan="4">示数</th>
                    <th rowspan="2" field="clientoperationtime33" width="120" align="center">抄表时刻</th>
                </tr>
                <tr>
                    <th field="totalpositiveactivepower" width="80" align="center">正向有功</th>
                    <th field="totalreverseactivepower" width="80" align="center">反向有功</th>
                    <th field="totalpositivereactivepower" width="80">正向无功</th>
                    <th field="totalreversereactivepower" width="80" align="center">正向无功</th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="电压" style="display:none;overflow: hidden">
            <table id="tt3" class="easyui-datagrid" fit="true" data-options="border:false">
                <thead>
                <tr>
                    <%--<th rowspan="2" field="areaId" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="80" align="center">监测点</th>
                    <th colspan="3">电压(V)</th>
                    <th rowspan="2" field="clientoperationtime" width="120" align="center">抄表时刻</th>
                </tr>
                <tr>
                    <th field="aVoltage" width="80" align="center">Uu/Uuv</th>
                    <th field="bVoltage" width="80" align="center">Uv</th>
                    <th field="cVoltage" width="80" align="center">Uw/Uwv</th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="电流" style="display:none;overflow: hidden">
            <table id="tt4" class="easyui-datagrid" fit="true" data-options="border:false">
                <thead>
                <tr>
                    <%--<th rowspan="2" field="areaId" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="80" align="center">监测点</th>
                    <th colspan="3">电流(V)</th>
                    <th rowspan="2" field="clientoperationtime" width="120" align="center">抄表时刻</th>
                </tr>
                <tr>
                    <th field="aCurrent" width="80" align="center">Iu</th>
                    <th field="bCurrent" width="80" align="center">Iv</th>
                    <th field="cCurrent" width="80" align="center">Iw</th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="功率因数" style="display:none;overflow: hidden">
            <table id="tt5" class="easyui-datagrid" fit="true" data-options="border:false">
                <thead>
                <tr>
                    <%--<th rowspan="2" field="areaId" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="80" align="center">监测点</th>
                    <th colspan="4">功率因数(%)</th>
                    <th rowspan="2" field="clientoperationtime" width="120" align="center">抄表时刻</th>
                </tr>
                <tr>
                    <th field="totalpowerfactor" width="80" align="center">总</th>
                    <th field="aPowerfactor" width="80" align="center">U相</th>
                    <th field="bPowerfactor" width="80" align="center">V相</th>
                    <th field="cPowerfactor" width="80" align="center">W相</th>
                </tr>
                </thead>
            </table>
        </div>
        <!--  <div title="温度" style="display:none;overflow: hidden">
             <table id="tt6" class="easyui-datagrid" fit="true" data-options="border:false"
                    singleSelect="true" iconCls="icon-save" rownumbers="true">
                 <thead>
                 <tr>
                     <th rowspan="2" field="itemid" width="80" align="center">序号</th>
                     <th rowspan="2" field="productid" width="80" align="center">监测点</th>
                     <th rowspan="2" field="productid" width="80" align="center">状态</th>
                     <th colspan="3">温度(℃)</th>
                     <th rowspan="2" field="productid" width="80" align="center">抄表时刻</th>
                 </tr>
                 <tr>
                     <th field="unitcost" width="80" align="center">U相</th>
                     <th field="attr1" width="80" align="center">V相</th>
                     <th field="status" width="80" align="center">W相</th>
                 </tr>
                 </thead>
             </table>
         </div> -->
    </div>
</div>
<div data-options="region:'south',title:'当前监测点:',split:true,collapsed:true,border:false,collapsible:false,onExpand: function(region){

}" style="height:100%;">
    <div id="cc2" class="easyui-layout" fit="true">
        <div data-options="region:'north',border:false,split:true" style="overflow: hidden;">
            <div style="margin-left:20px;padding:5px">
                <a id="btn-detail-back" href="javascript:void(0)" class="easyui-linkbutton"
                   data-options="iconCls:'icon-search'" onclick="backlist()">返回列表</a>
                &nbsp;&nbsp;<input id="input-detail-datebox" class="easyui-datebox"
                                   data-options="editable:false,required: true" id="beginDate" label="日期:"
                                   style="width:250px"/>
                <a id="btn-detail-search" href="javascript:void(0)" class="easyui-linkbutton"
                   data-options="iconCls:'icon-search'">查询</a>
            </div>
        </div>
        <div data-options="region:'center',border:false" style="overflow: hidden;">
            <div id="tab2" class="easyui-tabs" data-options="border:false" fit="true">
                <div title="负荷" id="table1" style="display:none;overflow: hidden">
                    <div class="easyui-layout" data-options="fit:true">
                        <div id="chart-load-detail" data-options="region:'north',split:true,border:false"
                             style="height:70%;text-align: center;">
                            <span style="font-size: medium;line-height: 300px;">请载入数据</span>
                        </div>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <table id="dtt1" border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 100%;width: 100%">
                                <tbody>
                                <tr>
                                    <td width="25%" valign="center" align="center">最大负荷</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">发生时间</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">最小负荷</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">发生时间</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">平均负荷</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">峰谷差</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">峰谷差率</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">负荷率</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- <div title="电量">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',split:true,border:false" style="height:70%;padding:10px;">
                            图表
                        </div>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <table border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 100%;width: 100%">
                                <tbody>
                                <tr>
                                    <td width="25%" valign="center" align="center">总电量</td>
                                    <td width="25%" valign="center" align="center" rowspan="1" colspan="3">33.58(kW)
                                    </td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">峰电量</td>
                                    <td width="25%" valign="center" align="center">-(kWh)</td>
                                    <td width="25%" valign="center" align="center">平电量</td>
                                    <td width="25%" valign="center" align="center">-(kWh)</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">谷电量</td>
                                    <td width="25%" valign="center" align="center">-(kWh)</td>
                                    <td width="25%" valign="center" align="center">尖峰电量</td>
                                    <td width="25%" valign="center" align="center">-(kWh)</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div> -->
                <div title="示数" style="display:none;overflow: hidden">
                    <table id="dtt2" class="easyui-datagrid" data-options="border:false" fit="true">
                        <thead>
                        <tr>
                            <th rowspan="2" field="clientoperationtime33" width="80" align="center">日期</th>
                            <th colspan="4">示数</th>
                        </tr>
                        <tr>
                            <th field="totalpositiveactivepower" width="80" align="center">正向有功</th>
		                    <th field="totalreverseactivepower" width="80" align="center">反向有功</th>
		                    <th field="totalpositivereactivepower" width="80">正向无功</th>
		                    <th field="totalreversereactivepower" width="80" align="center">正向无功</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div title="电压" style="display:none;overflow: hidden">
                    <div class="easyui-layout" data-options="fit:true">
                        <div id="chart-voltage-detail" data-options="region:'north',split:true,border:false"
                             style="height:70%;text-align: center;">
                            <span style="font-size: medium;line-height: 300px;">请载入数据</span>
                        </div>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <table id="table2" border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 100%;width: 100%">
                                <tbody>
                                <tr>
                                    <td width="25%" valign="center" align="center">U相最高电压</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">U相最低电压</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">V相最高电压</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">V相最低电压</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">W相最高电压</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">W相最低电压</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div title="电流" style="display:none;overflow: hidden">
                    <div class="easyui-layout" data-options="fit:true">
                        <div id="chart-current-detail" data-options="region:'north',split:true,border:false"
                             style="height:70%;text-align: center;">
                            <span style="font-size: medium;line-height: 300px;">请载入数据</span>
                        </div>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <table id="table3" border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 100%;width: 100%">
                                <tbody>
                                <tr>
                                    <td width="25%" valign="center" align="center">U相最高电流</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">U相最低电流</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">V相最高电流</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">V相最低电流</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">W相最高电流</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">W相最低电流</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div title="功率因数" style="display:none;overflow: hidden">
                    <div class="easyui-layout" data-options="fit:true">
                        <div id="chart-power-factor-detail" data-options="region:'north',split:true,border:false"
                             style="height:70%;text-align: center;">
                            <span style="font-size: medium;line-height: 300px;">请载入数据</span>
                        </div>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <table id="table4" border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 100%;width: 100%">
                                <tbody>
                                <tr>
                                    <td width="25%" valign="center" align="center">U相最高功率因数</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">U相最低功率因数</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">V相最高功率因数</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">V相最低功率因数</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">W相最高功率因数</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">W相最低功率因数</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">总最高功率因数</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">总最低功率因数</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- <div title="谐波">

                </div>
                <div title="温度">
                    <div class="easyui-layout" data-options="fit:true">
                        <div data-options="region:'north',split:true,border:false" style="height:70%;padding:10px;">
                            图表
                        </div>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <table border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 100%;width: 100%">
                                <tbody>
                                <tr>
                                    <td width="25%" valign="center" align="center">U相最高温度</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">U相最低温度</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">V相最高温度</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">V相最低温度</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">W相最高温度</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                    <td width="25%" valign="center" align="center">W相最低温度</td>
                                    <td width="25%" valign="center" align="center">--</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div> -->
            </div>
        </div>
    </div>
</div>
</body>
</html>
