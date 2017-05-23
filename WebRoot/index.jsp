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
    <%@ include file="/view/common/common960.jsp" %>
    <!--本页面样式-->
    <link href="${ctx}Content/css/page/index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        require(["view/frame/js/index.js"]);
    </script>
    <style>
        .title p {
            text-align: left;
        }
        .tab-body {
            overflow: hidden;
        }

        .head-left {
            background: url(${sessionScope.areaInfo.indexLogoPath}) no-repeat left !important;
        }
    </style>
</head>
<body id="indexLayout" class="easyui-layout">
<%--<div data-options="region:'north',href:'${ctx}view/frame/north.jsp'" style="height: 70px;overflow: hidden;" class="head-north"></div>--%>
<%--<div data-options="region:'west',href:'${ctx}view/frame/west.jsp'" style="width: 150px;overflow: hidden;"></div>--%>
<div data-options="region:'center',href:'${ctx}view/frame/center.jsp'" style="overflow: hidden;"></div>
<div id="dlg-select-area" class="easyui-dialog" title="选择企业"
     data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
     style="width:400px;height:120px;">

    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center'">
            <div class="container_12" style="padding-top: 10px">
                <div class="grid_4 cell title">
                    <p>
                        企业
                    </p>
                </div>
                <div class="grid_8 cell">
                    <p>
                        <input id="combo-areaId" class="easyui-combobox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<div data-options="region:'south'" class="head-south">
    ${sessionScope.areaInfo.name}
    <a href="http://www.miitbeian.gov.cn/" style="color: black;text-decoration: none">${sessionScope.areaInfo.icp}</a>
</div>
</body>
</html>