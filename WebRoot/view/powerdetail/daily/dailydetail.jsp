<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/commonHighChart.jsp" %>
    <script type="text/javascript">
        require(["js/dailydetail.js"]);
    </script>
</head>
<body class="easyui-layout" id="cc">
<div data-options="region:'center',border:false" style="width:100%;overflow: hidden">
    <div id="tab-tt-tools" style="border-left: none;border-top: none;border-right: none;">
        <input id="main-input-detail-datebox" class="easyui-datebox"
               data-options="editable:false,required: true" style="width:150px"/>
        <a id="main-input-btn-detail-search" href="javascript:void(0)" class="easyui-linkbutton"
           data-options="iconCls:'icon-search'">查询</a>

    </div>
    <div id="tt" class="easyui-tabs" data-options="border:false,tools:'#tab-tt-tools'" fit="true">
        <div title="负荷" style="display:none;overflow: hidden">
            <table id="tt1" style="display:none">
                <thead>
                <tr>
                    <!-- <th field="id" hidden="true"></th>
                    <th field="ck" data-options="checkbox:true"></th> -->
                    <!--
                    <th rowspan="2" field="areaId" width="80" align="center" hidden="true"></th>
                    <th rowspan="2" field="concentratorId" width="80" align="center" hidden="true"></th>
                    <th rowspan="2" field="pn" width="80" align="center" hidden="true"></th>
                    -->
                    <th rowspan="2" field="name" width="200" align="center" formatter="DataGridUtils.strFormatter">监测点
                    </th>
                    <th rowspan="2" field="days" width="120" align="center"
                        formatter="DataGridUtils.dateToDayFormatter">日期
                    </th>
                    <th colspan="5">有功负荷(kW)</th>
                </tr>
                <tr>
                    <th field="maxactivepower" width="80" align="center" formatter="DataGridUtils.floatFormatter">最大负荷
                    </th>
                    <th field="minactivepower" width="80" align="center" formatter="DataGridUtils.floatFormatter">最小负荷
                    </th>
                    <th field="avgactivepower" width="80" align="center" formatter="DataGridUtils.floatFormatter">平均负荷
                    </th>
                    <th field="loadrate" width="80" align="center" formatter="DataGridUtils.floatFormatter">负荷率</th>
                    <th field="peakrate" width="80" align="center" formatter="DataGridUtils.floatFormatter">峰谷差率</th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="电量" style="display:none;overflow: hidden">
            <table id="tt7" style="display:none">
                <thead>
                <tr>
                    <th rowspan="2" field="name" width="200" align="center" formatter="DataGridUtils.strFormatter">监测点
                    </th>
                    <th rowspan="2" field="days" width="120" align="center"
                        formatter="DataGridUtils.dateToDayFormatter">日期
                    </th>
                    <th colspan="5">有功电量(kWh)</th>
                    <th rowspan="2" field="totalpositiveactivePower" width="120" align="center"
                        formatter="DataGridUtils.floatFormatter">无功电量
                    </th>
                </tr>
                <tr>
                    <th field="totalpositiveactivePower" width="80" align="center"
                        formatter="DataGridUtils.floatFormatter">总
                    </th>
                    <th field="rateseq1" width="80" align="center" formatter="DataGridUtils.floatFormatter">峰</th>
                    <th field="rateseq2" width="80" align="center" formatter="DataGridUtils.floatFormatter">平</th>
                    <th field="rateseq3" width="80" align="center" formatter="DataGridUtils.floatFormatter">谷</th>
                    <th field="rateseq4" width="80" align="center" formatter="DataGridUtils.floatFormatter">尖峰</th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="示数" style="display:none;overflow: hidden">
            <table id="tt2" style="display:none">
                <thead>
                <tr>
                    <th rowspan="2" field="name" width="200" align="center" formatter="DataGridUtils.strFormatter">监测点
                    </th>
                    <th rowspan="2" field="days" width="120" align="center"
                        formatter="DataGridUtils.dateToDayFormatter">日期
                    </th>
                    <th colspan="5">示数</th>
                    <th colspan="2">最大需量</th>
                </tr>
                <tr>
                    <th field="totalpositiveactivePower" width="80" align="center"
                        formatter="DataGridUtils.floatFormatter">总
                    </th>
                    <th field="rateseq1" width="80" align="center" formatter="DataGridUtils.floatFormatter">峰</th>
                    <th field="rateseq2" width="80" align="center" formatter="DataGridUtils.floatFormatter">平</th>
                    <th field="rateseq3" width="80" align="center" formatter="DataGridUtils.floatFormatter">谷</th>
                    <th field="rateseq4" width="80" align="center" formatter="DataGridUtils.floatFormatter">尖峰</th>
                    <th field="totalpositivemaxactivepower" width="120" align="center"
                        formatter="DataGridUtils.floatFormatter">最大需量
                    </th>
                    <th field="totalpositivemaxactivepowertime" width="150" align="center"
                        formatter="DataGridUtils.dateToDayFormatter">发生时间
                    </th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="电压" style="display:none;overflow: hidden">
            <table id="tt3" style="display:none">
                <thead>
                <tr>
                    <%--<th rowspan="2" field="areaId" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="200" align="center" formatter="DataGridUtils.strFormatter">监测点
                    </th>
                    <th rowspan="2" field="days" width="120" align="center"
                        formatter="DataGridUtils.dateToDayFormatter">日期
                    </th>
                    <th colspan="3">最高电压(V)</th>
                    <th colspan="3">最低电压(V)</th>
                </tr>
                <tr>
                    <th field="maxavoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">Uu/Uuv
                    </th>
                    <th field="maxbvoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">Uv</th>
                    <th field="maxcvoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">Uw/Uwv
                    </th>
                    <th field="minavoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">Uu/Uuv
                    </th>
                    <th field="minbvoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">Uv</th>
                    <th field="mincvoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">Uw/Uwv
                    </th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="电流" style="display:none;overflow: hidden">
            <table id="tt4" style="display:none">
                <thead>
                <tr>
                    <%--<th rowspan="2" field="areaId" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="200" align="center" formatter="DataGridUtils.strFormatter">监测点
                    </th>
                    <th rowspan="2" field="days" width="120" align="center"
                        formatter="DataGridUtils.dateToDayFormatter">日期
                    </th>
                    <th colspan="3">最高电流(A)</th>
                    <th colspan="3">最低电流(A)</th>
                </tr>
                <tr>
                    <th field="maxacurrent" width="80" align="center" formatter="DataGridUtils.floatFormatter">Iu</th>
                    <th field="maxbcurrent" width="80" align="center" formatter="DataGridUtils.floatFormatter">Iv</th>
                    <th field="maxccurrent" width="80" align="center" formatter="DataGridUtils.floatFormatter">Iw</th>
                    <th field="minacurrent" width="80" align="center" formatter="DataGridUtils.floatFormatter">Iu</th>
                    <th field="minbcurrent" width="80" align="center" formatter="DataGridUtils.floatFormatter">Iv</th>
                    <th field="minacurrent" width="80" align="center" formatter="DataGridUtils.floatFormatter">Iw</th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="功率因数" style="display:none;overflow: hidden">
            <table id="tt5" style="display:none">
                <thead>
                <tr>
                    <%--<th rowspan="2" field="areaId" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="200" align="center" formatter="DataGridUtils.strFormatter">监测点
                    </th>
                    <th rowspan="2" field="days" width="120" align="center"
                        formatter="DataGridUtils.dateToDayFormatter">日期
                    </th>
                    <th colspan="2">平均功率因数(%)</th>
                </tr>
                <tr>
                    <th field="powerFactorStandard" width="80" align="center" formatter="DataGridUtils.floatFormatter">
                        标准
                    </th>
                    <th field="avgtotalpowerfactor" width="80" align="center" formatter="DataGridUtils.floatFormatter">
                        功率因素
                    </th>
                </tr>
                </thead>
            </table>
        </div>
        <div title="谐波" style="display:none;overflow: hidden">
            <div id="tool-harmonicTimes" style="">
                <input id="cc1" class="easyui-combobox" label="谐波次数:" style="width: 150px;">
            </div>
            <table id="tt6" class="easyui-datagrid" fit="true"
                   data-options="border:false,toolbar:'#tool-harmonicTimes'">
                <thead>
                <tr>
                    <%--<th rowspan="2" field="areaId" width="80" align="center">区域</th>--%>
                    <%--<th rowspan="2" field="concentratorId" width="80" align="center">集中器</th>--%>
                    <%--<th rowspan="2" field="pn" width="80" align="center">监测点</th>--%>
                    <th rowspan="2" field="name" width="200" align="center" formatter="DataGridUtils.strFormatter">监测点
                    </th>
                    <th rowspan="2" field="days" width="120" align="center"
                        formatter="DataGridUtils.dateToDayFormatter">日期
                    </th>
                    <th rowspan="2" field="days" width="120" align="center">
                        谐波次数
                    </th>
                    <th colspan="3">电压含有率(%）</th>
                    <th colspan="3">电流最大值(A)</th>
                </tr>
                <tr>
                    <th field="maxavoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">U相</th>
                    <th field="maxbvoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">V相</th>
                    <th field="maxcvoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">W相</th>
                    <th field="minavoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">U相</th>
                    <th field="minbvoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">V相</th>
                    <th field="mincvoltage" width="80" align="center" formatter="DataGridUtils.floatFormatter">U相</th>
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
                                   data-options="editable:false,required: true" label="日期:"
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
                <div title="电量" style="display:none;overflow: hidden">
                    <div class="easyui-layout" data-options="fit:true">
                        <div id="chart-electricity-detail" data-options="region:'north',split:true,border:false"
                             style="height:70%;text-align: center;">
                            <span style="font-size: medium;line-height: 300px;">请载入数据</span>
                        </div>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <table id="dtt4" border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 100%;width: 100%">
                                <tbody>
                                <tr>
                                    <td width="25%" valign="center" align="center">总电量</td>
                                    <td width="25%" valign="center" align="center" rowspan="1" colspan="3">--
                                    </td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">峰电量</td>
                                    <td width="25%" valign="center" align="center">--(kWh)</td>
                                    <td width="25%" valign="center" align="center">平电量</td>
                                    <td width="25%" valign="center" align="center">--(kWh)</td>
                                </tr>
                                <tr>
                                    <td width="25%" valign="center" align="center">谷电量</td>
                                    <td width="25%" valign="center" align="center">--(kWh)</td>
                                    <td width="25%" valign="center" align="center">尖峰电量</td>
                                    <td width="25%" valign="center" align="center">--(kWh)</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div title="示数" style="display:none;overflow: hidden">
                    <table id="dtt2" class="easyui-datagrid" data-options="border:false" fit="true">
                        <thead>
                        <tr>
                            <th rowspan="2" field="clientoperationtime33" width="120" align="center">日期</th>
                            <th colspan="4">示数</th>
                        </tr>
                        <tr>
                            <th field="totalpositiveactivepower" width="80" align="center">正向有功</th>
                            <th field="totalreverseactivepower" width="80" align="center">反向有功</th>
                            <th field="totalpositivereactivepower" width="80" align="center">正向无功</th>
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

                <div title="谐波" style="display:none;overflow: hidden">
                    <div class="easyui-layout" data-options="fit:true">
                        <!--
                        <div data-options="region:'north',split:true,border:false"
                           style="height:5%;text-align: left;padding:5px;">
                          <span>谐波次数: </span>&nbsp;&nbsp;<input id="cc2">&nbsp;&nbsp;<span>类别: </span>&nbsp;&nbsp;<input id="cc3">
                      </div>
                      -->
                        <div id="chart-harmonic-detail" data-options="region:'north',border:false"
                             style="height:50%;text-align: center;">
                            <span style="font-size: medium;line-height: 200px;">请载入数据</span>
                        </div>
                        <div data-options="region:'south',border:false" style="height:50%;">
                            <div id="dtt3-tools" style="border-left: none;border-top: none;border-right: none;">
                                <input class="easyui-combobox" label="谐波次数:" style="width: 150px;">
                                <input class="easyui-combobox" label="数据类别:" style="width: 150px;">
                            </div>
                            <table id="dtt3" class="easyui-datagrid" data-options="border:false,toolbar:'#dtt3-tools'"
                                   fit="true">
                                <thead>
                                <tr>
                                    <th rowspan="2" field="clientoperationtime33" width="120" align="center">谐波次数</th>
                                    <th colspan="2">U相</th>
                                    <th colspan="2">V相</th>
                                    <th colspan="2">W相</th>
                                </tr>
                                <tr>
                                    <th field="totalpositiveactivepower" width="80" align="center">最大值</th>
                                    <th field="totalreverseactivepower" width="80" align="center">发生时间</th>
                                    <th field="totalpositiveactivepower" width="80" align="center">最大值</th>
                                    <th field="totalreverseactivepower" width="80" align="center">发生时间</th>
                                    <th field="totalpositiveactivepower" width="80" align="center">最大值</th>
                                    <th field="totalreverseactivepower" width="80" align="center">发生时间</th>
                                </tr>
                                </thead>
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
