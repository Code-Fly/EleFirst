<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/14
  Time: 下午8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="view/common/config.jsp" %>
<html>
<head>
    <%@ include file="view/common/meta.jsp" %>
    <!--本页面样式-->
    <link href="${ctx}Content/css/page/index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        require([_ctx + "view/frame/js/index.js"]);
    </script>
</head>
<body id="indexLayout" class="easyui-layout">
<%--<div data-options="region:'north',href:'${ctx}view/frame/north.jsp'" style="height: 70px;overflow: hidden;" class="head-north"></div>--%>
<%--<div data-options="region:'west',href:'${ctx}view/frame/west.jsp'" style="width: 150px;overflow: hidden;"></div>--%>
<div data-options="region:'center',href:'${ctx}view/frame/center.jsp'" style="overflow: hidden;"></div>
<div data-options="region:'south'" class="head-south">
    版权所有 © 2016-2017 光一科技股份有限公司
</div>
</body>
</html>