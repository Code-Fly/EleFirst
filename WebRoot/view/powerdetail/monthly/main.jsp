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
<%@ include file="/view/common/meta.jsp" %>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false" style="padding: 5px;">
                <table id="tt" class="easyui-datagrid" style="width:600px;height:250px"
			url="datagrid_data.json"
			title="Load Data" iconCls="icon-save"
			rownumbers="true" pagination="true" method="get">
		<thead>
			<tr>
				<th field="itemid" width="80">Item ID</th>
				<th field="productid" width="80">Product ID</th>
				<th field="listprice" width="80" align="right">List Price</th>
				<th field="unitcost" width="80" align="right">Unit Cost</th>
				<th field="attr1" width="150">Attribute</th>
				<th field="status" width="60" align="center">Stauts</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>

