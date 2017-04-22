<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/commonHighChart.jsp" %>
    <script type="text/javascript">
        require(["js/yearlydetail.js"]);
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
                    <th rowspan="2" field="areaId" width="80" align="center" hidden="true"></th>
                    <th rowspan="2" field="concentratorId" width="80" align="center" hidden="true"></th>
                    <th rowspan="2" field="pn" width="80" align="center" hidden="true"></th>
                    <th rowspan="2" field="name" width="200" align="center" formatter="DataGridUtils.strFormatter">监测点
                    </th>
                    <th rowspan="2" field="days" width="120" align="center"
                        formatter="DataGridUtils.dateToYearFormatter">日期
                    </th>
                    <th colspan="5">有功负荷(kW)</th>
                </tr>
                <tr>
                    <th field="maxactivepower" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 3)}">最大负荷
                    </th>
                    <th field="minactivepower" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 3)}">最小负荷
                    </th>
                    <th field="avgactivepower" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 3)}">平均负荷
                    </th>
                    <th field="loadrate" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 1)}">负荷率
                    </th>
                    <th field="peakrate" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 1)}">峰谷差率
                    </th>
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
                        formatter="DataGridUtils.dateToYearFormatter">日期
                    </th>
                    <th colspan="5">有功电量(kWh)</th>
                    <th rowspan="2" field="totalpositiveactivePower" width="120" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 4)}">无功电量
                    </th>
                </tr>
                <tr>
                    <th field="totalpositiveactivePower" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 4)}">总
                    </th>
                    <th field="rateseq1" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 4)}">峰
                    </th>
                    <th field="rateseq2" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 4)}">平
                    </th>
                    <th field="rateseq3" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 4)}">谷
                    </th>
                    <th field="rateseq4" width="80" align="center"
                        data-options="formatter:function(value){return DataGridUtils.floatFormatter(value, 4)}">尖峰
                    </th>
                </tr>
                </thead>
            </table>
        </div>
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
                        <%--<div id="chart-load-detail" data-options="region:'north',split:true,border:false"--%>
                        <%--style="height:70%;text-align: center;">--%>
                        <%--<span style="font-size: medium;line-height: 300px;">请载入数据</span>--%>
                        <%--</div>--%>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <div id="chart-load-detail" style="height:400px;text-align: center;">
                                <span style="font-size: medium;line-height: 300px;">请载入数据</span>
                            </div>
                            <table id="dtt1" border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 150px;width: 100%">
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
                        <%--<div id="chart-electricity-detail" data-options="region:'north',split:true,border:false"--%>
                        <%--style="height:70%;text-align: center;">--%>
                        <%--<span style="font-size: medium;line-height: 300px;">请载入数据</span>--%>
                        <%--</div>--%>
                        <div data-options="region:'center',border:false" style="height:30%;padding:10px;">
                            <div id="chart-electricity-detail" style="height:400px;text-align: center;">
                                <span style="font-size: medium;line-height: 300px;">请载入数据</span>
                            </div>
                            <table id="dtt4" border="1" cellpadding="0" cellspacing="0" bordercolor="#ccc"
                                   style="height: 150px;width: 100%">
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
            </div>
        </div>
    </div>
</div>
</body>
</html>
