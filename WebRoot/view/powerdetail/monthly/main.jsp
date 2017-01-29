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
	<script type="text/javascript">
        require(["js/main.js"]);
	</script>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false" style="padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',title:'监测点'" style="width:200px;overflow: hidden">
			<iframe src="../../devicetree/main.jsp" style="width: 100%;height: 100%;" frameborder="no" border="0"
					marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
		</div>
		<div data-options="region:'center',border:false" style="padding-left: 5px;">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'" style="overflow: hidden">
					<iframe id="centerFrame" ref="monthlydetail.jsp" style="width: 100%;height: 100%;" frameborder="no"
							border="0"
							marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

