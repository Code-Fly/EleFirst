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
        require(["js/dailyelectricity.js"]);
    </script>
</head>
<body>
<table id="tt2" class="easyui-datagrid"
       toolbar="#tb"
       singleSelect="true" border="false" fit="true">
    <thead frozen="true">
    <tr>
        <th field="name" width="120" align="center">监测点</th>
        <th field="total" width="120" align="center">总电量</th>
    </tr>
    </thead>
    <thead>
    <tr>
        <th field="p01" width="100" align="center">1日</th>
        <th field="p02" width="100" align="center">2日</th>
        <th field="p03" width="100" align="center">3日</th>
        <th field="p04" width="100" align="center">4日</th>
        <th field="p05" width="100" align="center">5日</th>
        <th field="p06" width="100" align="center">6日</th>
        <th field="p07" width="100" align="center">7日</th>
        <th field="p08" width="100" align="center">8日</th>
        <th field="p09" width="100" align="center">9日</th>
        <th field="p10" width="100" align="center">10日</th>
        <th field="p11" width="100" align="center">11日</th>
        <th field="p12" width="100" align="center">12日</th>
        <th field="p13" width="100" align="center">13日</th>
        <th field="p14" width="100" align="center">14日</th>
        <th field="p15" width="100" align="center">15日</th>
        <th field="p16" width="100" align="center">16日</th>
        <th field="p17" width="100" align="center">17日</th>
        <th field="p18" width="100" align="center">18日</th>
        <th field="p19" width="100" align="center">19日</th>
        <th field="p20" width="100" align="center">20日</th>
        <th field="p21" width="100" align="center">21日</th>
        <th field="p22" width="100" align="center">22日</th>
        <th field="p23" width="100" align="center">23日</th>
        <th field="p24" width="100" align="center">24日</th>
        <th field="p25" width="100" align="center">25日</th>
        <th field="p26" width="100" align="center">26日</th>
        <th field="p27" width="100" align="center">27日</th>
        <th field="p28" width="100" align="center">28日</th>
        <th field="p29" width="100" align="center">29日</th>
        <th field="p30" width="100" align="center">30日</th>
        <th field="p31" width="100" align="center">31日</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:5px;height:auto">
    <div>
        &nbsp;&nbsp;<input class="easyui-datebox" style="width:120px" id="startdate">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="#" id="btn-detail-search" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-search'">查询</a>
        <a href="#" id="exportexcel" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
    </div>
</div>
</body>
<script type="text/javascript">

</script>
</html>
