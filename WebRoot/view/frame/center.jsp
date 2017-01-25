<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
</head>
<body>
<div id="centerTabs">
    <div title="我的桌面" data-options="border:false,tools:'#p-tools'"
         style="overflow: hidden;">
        <iframe id="frame-portal" src="${ctx}view/frame/main.jsp"
                style="width: 100%; height: 100%;" frameborder="no" border="0"
                marginwidth="0" marginheight="0" scrolling="no"
                allowtransparency="yes"></iframe>
    </div>
</div>
</body>
</html>