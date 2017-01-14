<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/config.jsp" %>
<html>
<head>
    <%@ include file="../common/meta.jsp" %>
</head>
<body>
<div class="easyui-accordion" data-options="fit:true,border:false">
    <div title="流程管理" data-options="ionCls:'icon-save'">
        <ul id="nav-dme">

            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-workflowdef'"
                   onclick="addTab('流程定义','view/dme/workflowManager/workflowBoardModuleManagerM.jsp','icon-workflowdef')"
                   plain="true" href="javascript:void(0)">流程定义</a></li>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-processmonitor'"
                   onclick="addTab('流程状态监控','view/dme/workflowManager/workflowMonitor.jsp','icon-processmonitor')"
                   plain="true" href="javascript:void(0)">流程状态监控</a></li>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-timermanage'"
                   onclick="addTab('策略管理','view/dme/workflowManager/workflowstrategymanager.jsp','icon-timermanage')"
                   plain="true" href="javascript:void(0)">策略管理</a></li>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-timerlog'"
                   onclick="addTab('策略执行日志','view/dme/workflowManager/workflowtimerlog.jsp','icon-timerlog')"
                   plain="true" href="javascript:void(0)">策略执行日志</a></li>

        </ul>
    </div>
    <div title="数据视图" data-options="ionCls:'icon-save'">
        <ul id="nav-ace">
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-datadisplay'"
                   onclick="addTab('数据展示','view/ace_new/frame/main.jsp','icon-datadisplay')"
                   plain="true" href="javascript:void(0)">数据展示</a></li>
        </ul>
    </div>
    <div title="系统管理" data-options="ionCls:'icon-save'">
        <ul id="nav-sys">
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-sysuser'"
                   onclick="addTab('用户管理','view/system/userinfo/usermanager.jsp','icon-sysuser')"
                   plain="true" href="javascript:void(0)">用户管理</a></li>
            <li><a class="easyui-linkbutton"
                   data-options="iconCls:'icon-sysrole'"
                   onclick="addTab('角色管理','view/system/userinfo/rolemanager.jsp','icon-sysrole')"
                   plain="true" href="javascript:void(0)">角色管理</a></li>
        </ul>
    </div>
</div>
</body>
</html>