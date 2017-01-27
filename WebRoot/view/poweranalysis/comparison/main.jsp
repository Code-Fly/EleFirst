<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/27
  Time: 下午2:12
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
        require(["js/main.js"]);
    </script>
    <style>
        p {
            padding-top: 0px;
            padding-bottom: 0px;
            margin-top: 5px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height: 115px;">
    <div class="container_12" style="padding-top: 5px">
        <div class="grid_1 cell title">
            <p>监测点</p>
        </div>
        <div class="grid_9 cell">
            <p>
                <input id="input-pn" class="easyui-tagbox" style="width:100%">
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input id="combo-pn" class="easyui-combobox" style="width:100%">
            </p>
        </div>
        <div class="clear"></div>
        <div class="grid_1 cell title">
            <p>时间点</p>
        </div>
        <div class="grid_9 cell">
            <p>
                <input id="input-time" class="easyui-tagbox" style="width:100%">
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input id="datebox-time" type="text" class="easyui-datebox" style="width:100%">
            </p>
        </div>
        <div class="clear"></div>
        <div class="grid_1 cell title">
            <p>合并计算</p>
        </div>
        <div class="grid_3 cell">
            <p>
                <input class="easyui-switchbutton" data-options="onText:'是',offText:'否'">
            </p>
        </div>
        <div class="grid_6 cell">
            <p>
            </p>
        </div>
        <div class="grid_2 cell">
            <p style="text-align: right">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:100px">查找</a>
            </p>
        </div>
    </div>

</div>
<div id="chart-comparasion" data-options="region:'center',border:false" style="padding: 10px;">

</div>
</body>
</html>
