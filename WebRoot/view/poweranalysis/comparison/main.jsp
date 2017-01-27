<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/27
  Time: 下午2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>
    <script type="text/javascript">
        require(["js/main.js"]);
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false">
    <div class="container_12">
        <div class="grid_1 cell title">
            <p>监测点</p>
        </div>
        <div class="grid_3 cell">
            <p>
                <input id="input-pn" class="easyui-tagbox" value="Apple,Orange" style="width:100%">
            </p>
        </div>
        <div class="grid_1 cell title">
            <p>时间点</p>
        </div>
        <div class="grid_3 cell">
            <p>
                <input id="input-time" class="easyui-tagbox" value="Apple,Orange" style="width:100%">
            </p>
        </div>
        <div class="grid_1 cell title">
            <p>合并计算</p>
        </div>
        <div class="grid_3 cell">
            <p>
                <input class="easyui-switchbutton" data-options="onText:'是',offText:'否'">
            </p>
        </div>
        <div class="clear"></div>
    </div>

</div>
<div data-options="region:'center',border:false" style="padding: 5px;">

</div>
</body>
</html>
