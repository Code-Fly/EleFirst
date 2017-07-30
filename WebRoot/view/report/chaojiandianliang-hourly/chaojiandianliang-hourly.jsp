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
        require(["js/chaojiandianliang-hourly.js"]);
    </script>
</head>
<body>
<table id="tt2" class="easyui-datagrid"
       toolbar="#tb"
       singleSelect="true" border="false" fit="true">
</table>

<div id="tb" style="padding:5px;height:auto">
    <div>
        <input id="datebox-time-start" label="开始时间" labelAlign="right" type="text" class="easyui-datebox"
               style="width:220px">
        <input id="datebox-time-end" label="结束时间" labelAlign="right" type="text" class="easyui-datebox"
               style="width:220px">
        <input id="numberspinner-hour" label="时" labelAlign="right" class="easyui-numberspinner"
               style="width:150px;">
        <input id="numberspinner-minute" label="分" labelAlign="right" class="easyui-numberspinner"
               style="width:150px;">

        <a href="#" id="btn-detail-search" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-search'">查询</a>
        <a href="#" id="btn-detail-export" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
    </div>
</div>
</body>
</html>
