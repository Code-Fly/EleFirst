<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/26
  Time: 上午2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<%
	String path1 = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path1 + "/";
%>

<html>
<head>
    <!-- 公共属性 -->
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>
</head>
<body>
	<table class="easyui-treegrid" border="false" fit="true" fitColumns = "true"
		   url="<%=basePath%>report/display/lineloss/list.do"
		   rownumbers="true"
		   idField="id" treeField="region" toolbar="#tb">
		<thead frozen="true">
			<tr>
				<th field="region" width="150">线路</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th field="f1" width="50" align="right">输入(kWh)</th>
				<th field="f2" width="50" align="right">输出(kWh)</th>
				<th field="f3" width="50" align="right">损耗(kWh)</th>
				<th field="f4" width="50" align="right">损耗率(%)</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div>
            &nbsp;&nbsp;开始时间: <input class="easyui-datebox" value="2017-06-01" style="width:120px">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间: <input class="easyui-datebox" value="2017-06-30"
                                                             style="width:120px">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" id="btn-detail-search" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-search'">查询</a>
            <a href="#" id="exportexcel" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
			<div style=""></div>
		</div>
	</div>
</body>
<script type="text/javascript">

</script>
</html>
