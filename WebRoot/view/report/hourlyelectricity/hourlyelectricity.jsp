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
        require(["js/hourlyelectricity.js"]);
    </script>
</head>
<body>
	<table id="tt2" class="easyui-datagrid"
			 toolbar="#tb"
			singleSelect="true" fitColumns="true" border="false" fit="true">
		<thead frozen="true">
			<tr>
				<th field="name" width="160" align="center">监测点</th>
				<th field="total" width="160" align="center">总电量</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th field="p00" align="center">1</th>
				<th field="p01" align="center">2</th>
				<th field="p03" align="center">3</th>
				<th field="p04" align="center">4</th>
				<th field="p05" align="center">5</th>
				<th field="p06" align="center">6</th>
				<th field="p07" align="center">7</th>
				<th field="p08" align="center">8</th>
				<th field="p09" align="center">9</th>
				<th field="p10" align="center">10</th>
				<th field="p11" align="center">11</th>
				<th field="p12" align="center">12</th>
				<th field="p13" align="center">13</th>
				<th field="p14" align="center">14</th>
				<th field="p15" align="center">15</th>
				<th field="p16" align="center">16</th>
				<th field="p17" align="center">17</th>
				<th field="p18" align="center">18</th>
				<th field="p19" align="center">19</th>
				<th field="p20" align="center">20</th>
				<th field="p21" align="center">21</th>
				<th field="p22" align="center">22</th>
				<th field="p23" align="center">23</th>
				<th field="p24" align="center">24</th>
				<th field="p25" align="center">25</th>
				<th field="p26" align="center">26</th>
				<th field="p27" align="center">27</th>
				<th field="p28" align="center">28</th>
				<th field="p29" align="center">29</th>
				<th field="p30" align="center">30</th>
				<th field="p31" align="center">31</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div>
			&nbsp;&nbsp;<input class="easyui-datebox" style="width:120px" id="startdate">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-search" id="btn-detail-search">查询</a>
			&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" id="exportexcel">导出报表</a>
		</div>
	</div>
</body>
<script type="text/javascript">

</script>
</html>
