<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 2017/7/2
  Time: 下午1:54
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
    <style>
        .title p {
            text-align: left;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false" align="center">
    <div class="easyui-tabs" data-options="fit:true" border="false">
        <div title="已收明细">
            <div class="easyui-layout" data-options="fit:true" style="height:100%;">
                <div data-options="region:'center'" border="false" style="overflow: hidden">
                    <iframe src="yishou.jsp" style="width: 100%;height: 100%;" frameborder="no"
                            border="0"
                            marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
                </div>
            </div>
        </div>
        <div title="未收明细">
            <div class="easyui-layout" data-options="fit:true" style="height:100%;">
                <div data-options="region:'center'" border="false" style="overflow: hidden">
                    <iframe src="weishou.jsp" style="width: 100%;height: 100%;" frameborder="no"
                            border="0"
                            marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
                </div>
            </div>
        </div>
        <div title="应收明细">
            <div class="easyui-layout" data-options="fit:true" style="height:100%;">
                <div data-options="region:'center'" border="false" style="overflow: hidden">
                    <iframe src="yingshou.jsp" style="width: 100%;height: 100%;" frameborder="no"
                            border="0"
                            marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>