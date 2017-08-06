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
        require(["js/jiancedianfuhe-monthly.js"]);
    </script>
</head>
<body>
<table id="tt2" class="easyui-datagrid"
       toolbar="#tb"
       singleSelect="true" border="false" fit="true" fitColumns="true" rownumbers="true">
    <thead>
    <tr>
        <th width="100" rowspan="2" field="监测点" align="center" formatter="DataGridUtils.strFormatter">监测点</th>
        <th width="100" rowspan="2" field="日期" align="center" formatter="DataGridUtils.strFormatter">日期</th>
        <th colspan="5">负荷(kW)</th>
    </tr>
    <tr>
        <th width="100" field="最大负荷" align="center" formatter="DataGridUtils.strFormatter">最大负荷</th>
        <th width="100" field="最小负荷" align="center" formatter="DataGridUtils.strFormatter">最小负荷</th>
        <th width="100" field="平均负荷" align="center" formatter="DataGridUtils.strFormatter">平均负荷</th>
        <th width="100" field="峰谷差率" align="center" formatter="DataGridUtils.strFormatter">峰谷差率</th>
        <th width="100" field="负荷率" align="center" formatter="DataGridUtils.strFormatter">负荷率</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:5px;height:auto">
    <div>
        <input id="datebox-time" label="时间" labelAlign="right" type="text" class="easyui-datebox"
               style="width:220px">
        <a href="#" id="btn-detail-search" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-search'">查询</a>
        <a href="#" id="btn-detail-export" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
    </div>
</div>
</body>
</html>