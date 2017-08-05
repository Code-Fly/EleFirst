<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/26
  Time: 上午2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <!-- 公共属性 -->
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>
    <script type="text/javascript">
        require(["js/jiancediandianliang-monthly.js"]);
    </script>
</head>
<body>
<table id="tt2" class="easyui-datagrid"
       toolbar="#tb"
       singleSelect="true" border="false" fit="true" fitColumns="true" rownumbers="true">
    <thead>
    <tr>
        <th colspan="2">月电量最大值(kWh)</th>
        <th colspan="2" id="dg-max">-</th>
        <th align="center">出现时间</th>
        <th align="center" id="dg-maxTime"></th>
    </tr>
    <tr>
        <th colspan="2">月电量最小值(kWh)</th>
        <th colspan="2" id="dg-min">-</th>
        <th width="100" align="center">出现时间</th>
        <th width="100" align="center" id="dg-minTime">-</th>
    </tr>
    <tr>
        <th colspan="2">月电量累计(kWh)</th>
        <th colspan="4" id="dg-total">-</th>
    </tr>
    <tr>
        <th width="100" field="时点" align="center" formatter="DataGridUtils.strFormatter">时点</th>
        <th width="100" field="本次示数" align="center" formatter="DataGridUtils.strFormatter">本次示数</th>
        <th width="100" field="上次示数" align="center" formatter="DataGridUtils.strFormatter">上次示数</th>
        <th width="100" field="示值差" align="center" formatter="DataGridUtils.strFormatter">示值差</th>
        <th width="100" field="倍率" align="center" formatter="DataGridUtils.strFormatter">倍率</th>
        <th width="100" field="电量" align="center" formatter="DataGridUtils.strFormatter">电量</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:5px;height:auto">
    <div>
        <input id="combo-pn" label="监测点" labelAlign="right" class="easyui-combobox" style="width: 220px;">
        <input id="datebox-time-start" label="开始时间" labelAlign="right" type="text" class="easyui-datebox"
               style="width:220px">
        <input id="datebox-time-end" label="结束时间" labelAlign="right" type="text" class="easyui-datebox"
               style="width:220px">
        <a href="#" id="btn-detail-search" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-search'">查询</a>
        <a href="#" id="btn-detail-export" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
    </div>
</div>
</body>
</html>