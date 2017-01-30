<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/30
  Time: 下午4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <script type="text/javascript">
        require(["js/detail.js"]);
    </script>
</head>
<body>
<div id="tab-detail" class="easyui-tabs" data-options="fit:true,border:false">
    <div title="实时数据" style="overflow:hidden;display:none;">
        <iframe ref="realtime.jsp" style="width: 100%;height: 100%;" frameborder="no" border="0"
                marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
    </div>
    <div title="日数据" style="overflow:hidden;display:none;">
        <iframe ref="daily.jsp" style="width: 100%;height: 100%;" frameborder="no" border="0"
                marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
    </div>
    <div title="月数据" style="overflow:hidden;display:none;">
        <iframe ref="monthly.jsp" style="width: 100%;height: 100%;" frameborder="no" border="0"
                marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
    </div>
</div>
</body>
</html>
