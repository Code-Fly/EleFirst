<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
</head>
<body>
<div class="easyui-layout head-north" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" class="head-north">
        <span class="head-left"></span>
    </div>
    <div data-options="region:'east',border:false" class="head-north" style="width: 200px;padding-top: 15px;">
        <a id="user" href="javascript:void(0)" class="easyui-menubutton" style="color:whitesmoke;"
           data-options="menu:'#mm_user'">${userInfo.userName}</a>
        <div id="mm_user" style="width: 150px;">
            <div data-options="iconCls:'icon-information'" id="btn-select-area">切换区域</div>
            <div class="menu-sep"></div>
            <form id="form-logout" action="<c:url value="/logout"/>" method="post" style="display: none">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            </form>
            <div data-options="iconCls:'icon-user_go'" class="loginOut" id="logout">安全退出</div>
        </div>
        <%--<div class="logoBg"></div>--%>

    </div>
</div>

</body>
</html>