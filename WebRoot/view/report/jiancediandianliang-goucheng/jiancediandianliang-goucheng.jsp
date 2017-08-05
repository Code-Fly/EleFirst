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
        require(["js/jiancediandianliang-goucheng.js"]);
    </script>
</head>
<body>
<table id="tt2" class="easyui-datagrid"
       toolbar="#tb"
       singleSelect="true" border="false" fit="true" fitColumns="true" rownumbers="true">
    <thead>
    <tr>
        <th colspan="2">总电量</th>
        <th colspan="2">峰电量</th>
        <th colspan="2">平电量</th>
        <th colspan="2">谷电量</th>
        <th colspan="2">尖电量</th>
    </tr>
    <tr>
        <th colspan="2" id="total">-</th>
        <th colspan="2" id="rate1">-</th>
        <th colspan="2" id="rate2">-</th>
        <th colspan="2" id="rate3">-</th>
        <th colspan="2" id="rate4">-</th>
    </tr>
    <tr>
        <th colspan="2">占比</th>
        <th colspan="2" id="rate1_rate">-</th>
        <th colspan="2" id="rate2_rate">-</th>
        <th colspan="2" id="rate3_rate">-</th>
        <th colspan="2" id="rate4_rate">-</th>
    </tr>
    <tr>
        <th rowspan="2" width="100" field="日期" align="center" formatter="DataGridUtils.strFormatter">日期</th>
        <th rowspan="2" width="100" field="总" align="center" formatter="DataGridUtils.strFormatter">总</th>
        <th colspan="2">峰</th>
        <th colspan="2">平</th>
        <th colspan="2">谷</th>
        <th colspan="2">尖</th>
    </tr>
    <tr>
        <th width="100" field="峰|电量" align="center" formatter="DataGridUtils.strFormatter">电量</th>
        <th width="100" field="峰|占比" align="center" formatter="DataGridUtils.strFormatter">占比</th>
        <th width="100" field="平|电量" align="center" formatter="DataGridUtils.strFormatter">电量</th>
        <th width="100" field="平|占比" align="center" formatter="DataGridUtils.strFormatter">占比</th>
        <th width="100" field="谷|电量" align="center" formatter="DataGridUtils.strFormatter">电量</th>
        <th width="100" field="谷|占比" align="center" formatter="DataGridUtils.strFormatter">占比</th>
        <th width="100" field="尖|电量" align="center" formatter="DataGridUtils.strFormatter">电量</th>
        <th width="100" field="尖|占比" align="center" formatter="DataGridUtils.strFormatter">占比</th>
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