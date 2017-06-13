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
        require(["js/monthlyelectricity.js"]);
    </script>
</head>
<body>
	<table id="tt" class="easyui-datagrid" singleSelect="true" iconCls="icon-save" toolbar="#tb" border="false" fit="true">
		<thead>
		    <tr>
				<th colspan="2">月电量最大值(kWh)</th>
				<th colspan="2">580</th>
				<th align="center">出现时间</th>
				<th align="center">2017-06-03</th>
			</tr>
			<tr>
				<th colspan="2">月电量最小值(kWh)</th>
				<th colspan="2">243</th>
				<th width="100" align="center">出现时间</th>
				<th width="100" align="center">2017-06-04</th>
			</tr>
			<tr>
				<th colspan="2">月电量累计(kWh)</th>
				<th colspan="4">2891</th>
			</tr>
			<tr>
				<th width="100" field="date" align="center">日期</th>
				<th width="100" field="display" align="center">本次示数</th>
				<th width="100" field="olddisplay" align="center">上次示数</th>
				<th width="100" field="displaydiffer" align="center">示值差</th>
				<th width="100" field="override" align="center">倍率</th>
				<th width="100" field="power" align="center">电量</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			&nbsp;&nbsp;开始时间: <input class="easyui-datebox" style="width:120px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间: <input class="easyui-datebox" style="width:120px">
			&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			&nbsp;&nbsp;<a href="#" class="easyui-linkbutton">导出报表</a>
			<div style=""></div>
		</div>
	</div>
</body>
<script type="text/javascript">

</script>
</html>
