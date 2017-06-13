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
			singleSelect="true" fitColumns="true" border="false" fit="true">
		<thead frozen="true">
			<tr>
				<th field="address" width="160" align="center">监测点</th>
				<th field="totalpower" width="160" align="center">总电量</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<th field="str1" align="center">6-1</th>
				<th field="str2" align="center">6-2</th>
				<th field="str3" align="center">6-3</th>
				<th field="str4" align="center">6-4</th>
				<th field="str5" align="center">6-5</th>
				<th field="str6" align="center">6-6</th>
				<th field="str7" align="center">6-7</th>
				<th field="str8" align="center">6-8</th>
				<th field="str9" align="center">6-9</th>
				<th field="str10" align="center">6-10</th>
				<th field="str11" align="center">6-11</th>
				<th field="str12" align="center">6-12</th>
				<th field="str13" align="center">6-13</th>
				<th field="str14" align="center">6-14</th>
				<th field="str15" align="center">6-15</th>
				<th field="str16" align="center">6-16</th>
				<th field="str17" align="center">6-17</th>
				<th field="str18" align="center">6-18</th>
				<th field="str19" align="center">6-19</th>
				<th field="str20" align="center">6-20</th>
				<th field="str21" align="center">6-21</th>
				<th field="str22" align="center">6-22</th>
				<th field="str23" align="center">6-23</th>
				<th field="str24" align="center">6-24</th>
				<th field="str25" align="center">6-25</th>
				<th field="str26" align="center">6-26</th>
				<th field="str27" align="center">6-27</th>
				<th field="str28" align="center">6-28</th>
				<th field="str29" align="center">6-29</th>
				<th field="str30" align="center">6-30</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div>
			&nbsp;&nbsp;开始时间: <input class="easyui-datebox" style="width:120px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间: <input class="easyui-datebox" style="width:120px">
			&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			&nbsp;&nbsp;<a href="#" class="easyui-linkbutton">导出报表</a>
		</div>
	</div>
</body>
<script type="text/javascript">

</script>
</html>
