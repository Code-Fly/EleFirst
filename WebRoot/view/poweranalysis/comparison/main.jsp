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

        .title p {
            text-align: left;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height: 115px;">
    <div class="container_12" style="padding-top: 5px">
        <div class="grid_2 cell title">
            <p>监测点</p>
        </div>
        <div class="grid_8 cell">
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
        <div class="grid_2 cell title">
            <p>时间点</p>
        </div>
        <div class="grid_8 cell">
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
        <div class="grid_2 cell title">
            <p>合计监测点</p>
        </div>
        <div class="grid_3 cell">
            <p>
                <input id="switch-total" class="easyui-switchbutton" data-options="onText:'是',offText:'否'">
            </p>
        </div>
        <div class="grid_5 cell">
            <p>
            </p>
        </div>
        <div class="grid_2 cell">
            <p style="text-align: right">
                <a id="btn-search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                   style="width:100px">查询</a>
            </p>
        </div>
    </div>

</div>
<div data-options="region:'center',border:false" align="center" style="padding: 10px;">
    <div class="easyui-tabs" data-options="plain:true" style="width: 90%">
        <div title="负荷" align="center">
            <div id="chart-load" class="easyui-panel" data-options="border:false"
                 style="margin-bottom: 10px;height: 400px;width: 95%;text-align: center;">
                <span style="font-size: medium;line-height: 400px;">请载入数据</span>
            </div>
            <div class="easyui-panel" data-options="border:false"
                 style="margin-bottom: 10px;height: 200px;width: 95%;">
                <table id="dg-table" class="easyui-datagrid" data-options="border:false,fit:true,fitColumns:true">
                    <thead>
                    <tr>
                        <th field="name" width="30%" align="center">监测点</th>
                        <th field="clientOperationTime" width="30%" align="center">日期</th>
                        <th field="totalactivepower" width="35%" align="center">最大值</th>
                        <th field="currentClientOperationTime" width="35%" align="center">发生时间</th>
                    </tr>
                    </thead>
                </table>
                <%--<div id="tab-table" data-options="plain:true,fit:true">--%>
                <%--&lt;%&ndash;<div title="负荷" align="center">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<table id="dg-table" class="easyui-datagrid" data-options="border:false,fit:true,fitColumns:true">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<thead>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th field="clientOperationTime" width="30%" align="center">日期</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th field="totalactivepower" width="35%" align="center">最大值</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th field="currentClientOperationTime" width="35%" align="center">发生时间</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</thead>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</table>&ndash;%&gt;--%>

                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--</div>--%>
            </div>
        </div>
    </div>


</div>
</body>
</html>
