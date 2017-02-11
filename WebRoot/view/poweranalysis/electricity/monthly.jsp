<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/30
  Time: 下午4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>
    <%@ include file="/view/common/commonHighChart.jsp" %>
    <script type="text/javascript">
        require(["js/monthly.js"]);
    </script>
    <style>
        p {
            padding-top: 0px;
            padding-bottom: 0px;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        .title p {
            text-align: left;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:60px;overflow: hidden">
    <div class="container_12" style="padding-top: 10px">
        <div class="grid_2 cell">
            <p>
                <input id="datebox-time-start" type="text" class="easyui-datebox" style="width:100%">
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input id="datebox-time-end" type="text" class="easyui-datebox" style="width:100%">
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <a id="btn-search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                   style="width:100px">查询</a>
            </p>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div data-options="region:'center',border:false" align="center">
    <div id="chart-load" class="easyui-panel" data-options="border:true"
         style="margin-bottom: 10px;height: 400px;width: 95%;text-align: center;overflow: hidden;">
        <span style="font-size: medium;line-height: 400px;">请载入数据</span>
    </div>

    <div class="easyui-panel" data-options="border:false"
         style="margin-bottom: 10px;height: 200px;width: 95%;">
        <table id="dg-table" class="easyui-datagrid" data-options="border:true,fit:true">
            <thead>
            <tr>
                <th data-options="field:'price'" width="150" align="center">本期电量(kWh)</th>
                <th data-options="field:'price'" width="150" align="center">去年同期电量(kWh)</th>
                <th data-options="field:'price'" width="150" align="center">同比(%)</th>
                <th data-options="field:'name'" width="150" align="center">日期</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>