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
    <link href="${ctx}Content/css/page/login.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT ==null}">
        require([_ctx + "view/frame/js/cloud.js", _ctx + "view/frame/js/login.js"]);
        </c:if>
        <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT !=null}">
        window.location.href = "index.do";
        </c:if>
    </script>
</head>
<body>
<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>
<div class="logintop">
    <span>欢迎登录光一电管家</span>
    <%--<ul>--%>
    <%--<li><a href="#">回首页</a></li>--%>
    <%--<li><a href="#">帮助</a></li>--%>
    <%--<li><a href="#">关于</a></li>--%>
    <%--</ul>--%>
</div>
<div class="loginbody">
    <span class="systemlogo"></span>
    <div class="loginbox" style="position: absolute;margin: auto; left: 0; right: 0;">
        <%--<form id="f1" action="${ctx}user/login.do" method="post">--%>
        <form id="form-login" action="<c:url value="/login"/>" method="post">
            <ul>
                <li>
                    <input id="userName" name="username" class="easyui-textbox"
                           style="width:343px;height:40px;padding:12px"
                           data-options="prompt:'用户名',iconCls:'icon-man',iconWidth:38">
                </li>
                <li>
                    <%--<input id="password" class="easyui-textbox" type="password" style="width:343px;height:40px;padding:12px"--%>
                    <%--data-options="prompt:'密码',iconCls:'icon-lock',iconWidth:38">--%>
                    <input id="password" name="password" class="easyui-passwordbox" prompt="密码" iconWidth="38"
                           style="width:343px;height:40px;padding:12px">
                </li>
                <li>
                    <input id="btnLogin" name="" type="button" class="loginbtn" value="登录"/>
                    <%--<label>--%>
                    <%--<input name="" type="checkbox" value="" checked="checked"/>记住密码</label>--%>
                    <%--<label>--%>
                    <%--<a href="#">忘记密码？</a>--%>
                    <%--</label>--%>
                </li>
            </ul>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form>
    </div>
</div>
<div class="loginbm">
    ${sessionScope.areaInfo.name} <a href="http://www.miitbeian.gov.cn/">${sessionScope.areaInfo.icp}</a>
</div>
</body>
</html>
