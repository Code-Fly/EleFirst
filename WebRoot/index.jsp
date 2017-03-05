<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/14
  Time: 下午8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="view/common/config.jsp" %>
<html>
<head>
    <%@ include file="view/common/meta.jsp" %>
    <!--本页面样式-->
    <link href="${ctx}Content/css/page/index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        require(["view/frame/js/index.js"]);
    </script>
    <style>
        .tab-body {
            overflow: hidden;
        }

        .head-left {
            background: url(${sessionScope.areaLocal.indexLogoPath}) no-repeat left !important;
        }
    </style>
</head>
<body id="indexLayout" class="easyui-layout">
<%--<div data-options="region:'north',href:'${ctx}view/frame/north.jsp'" style="height: 70px;overflow: hidden;" class="head-north"></div>--%>
<%--<div data-options="region:'west',href:'${ctx}view/frame/west.jsp'" style="width: 150px;overflow: hidden;"></div>--%>
<div data-options="region:'center',href:'${ctx}view/frame/center.jsp'" style="overflow: hidden;"></div>
<div data-options="region:'south'" class="head-south">
    ${sessionScope.areaLocal.name} <a href="http://www.miitbeian.gov.cn/"
                                      style="color: black;text-decoration: none">${sessionScope.areaLocal.icp}</a>
</div>
</body>
</html>