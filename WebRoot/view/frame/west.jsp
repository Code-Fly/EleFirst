<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
</head>
<body>
<div class="easyui-accordion" data-options="fit:true,border:false">
    <div title="用电能耗" data-options="ionCls:'icon-save'">
        <ul>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-table'"
                   onclick="addTab('实时用电数据','https://www.baidu.com','icon-table')"
                   plain="true" href="javascript:void(0)">实时用电数据</a></li>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-table'"
                   onclick="addTab('日用电数据','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"
                   plain="true" href="javascript:void(0)">日用电数据</a></li>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-table'"
                   onclick="addTab('周用电数据','view/dme/workflowManager/workflowstrategymanager.jsp','icon-table')"
                   plain="true" href="javascript:void(0)">周用电数据</a></li>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-table'"
                   onclick="addTab('月用电数据','view/dme/workflowManager/workflowtable.jsp','icon-table')"
                   plain="true" href="javascript:void(0)">月用电数据</a></li>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('年用电数据','view/dme/workflowManager/workflowtable.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">年用电数据</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('越限查询','view/dme/workflowManager/workflowtable.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">越限查询</a></li>--%>

        </ul>
    </div>
    <div title="用电分析" data-options="ionCls:'icon-save'">
        <ul>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('电量分析','https://www.baidu.com','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">电量分析</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('负荷分析','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">负荷分析</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('电量构成分析','view/dme/workflowManager/workflowstrategymanager.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">电量构成分析</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('分时电量分析','view/dme/workflowManager/workflowtable.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">分时电量分析</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('报装方式分析','view/dme/workflowManager/workflowtable.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">报装方式分析</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('用电费用分析','view/dme/workflowManager/workflowtable.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">用电费用分析</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('费用分析','view/dme/workflowManager/workflowtable.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">费用分析</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('账单录入','view/dme/workflowManager/workflowtable.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">账单录入</a></li>--%>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-table'"
                   onclick="addTab('用电对比分析','view/dme/workflowManager/workflowtable.jsp','icon-table')"
                   plain="true" href="javascript:void(0)">用电对比分析</a></li>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-table'"--%>
            <%--onclick="addTab('对标排名分析','view/dme/workflowManager/workflowtable.jsp','icon-table')"--%>
            <%--plain="true" href="javascript:void(0)">对标排名分析</a></li>--%>
        </ul>
    </div>
    <%--<div title="损耗分析" data-options="ionCls:'icon-save'">--%>
    <%--<ul>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('变压器损耗','https://www.baidu.com','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">变压器损耗</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('用电线路损耗','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">用电线路损耗</a></li>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <%--<div title="统计报表" data-options="ionCls:'icon-save'">--%>
    <%--<ul>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('抄见示数日报表','https://www.baidu.com','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">抄见示数日报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('抄见示数月报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">抄见示数月报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电量日报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电量日报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电量周报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电量周报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电量月报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电量月报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电量年报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电量年报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('月最大需量报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">月最大需量报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('分时电量月报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">分时电量月报表</a></li>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <%--<div title="监测点统计报表" data-options="ionCls:'icon-save'">--%>
    <%--<ul>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电量日报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电量日报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电量周报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电量周报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电量月报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电量月报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电量年报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电量年报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('分时电量月报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">分时电量月报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('日负荷报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">日负荷报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('月负荷报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">月负荷报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('月负荷报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">日最大需量报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('功率因数及无功日报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">功率因数及无功日报表</a></li>--%>
    <%--<li><a class="easyui-linkbutton"--%>
    <%--data-options="iconCls:'icon-table'"--%>
    <%--onclick="addTab('电压日监测报表','view/dme/workflowManager/workflowMonitor.jsp','icon-table')"--%>
    <%--plain="true" href="javascript:void(0)">电压日监测报表</a></li>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <div title="系统管理" data-options="ionCls:'icon-save'">
        <ul id="nav-sys">
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-user'"--%>
            <%--onclick="addTab('用户管理','view/system/userinfo/usermanager.jsp','icon-user')"--%>
            <%--plain="true" href="javascript:void(0)">用户管理</a></li>--%>
            <%--<li><a class="easyui-linkbutton"--%>
            <%--data-options="iconCls:'icon-users'"--%>
            <%--onclick="addTab('角色管理','view/system/userinfo/rolemanager.jsp','icon-users')"--%>
            <%--plain="true" href="javascript:void(0)">角色管理</a></li>--%>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-cog'"
                   onclick="addTab('配置管理','view/system/userinfo/rolemanager.jsp','icon-cog')"
                   plain="true" href="javascript:void(0)">配置管理</a></li>
        </ul>
    </div>
</div>
</body>
</html>