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
    <link href="${ctx}Content/css/page/login.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT ==null}">
        require([_ctx + "view/frame/js/cloud.js", _ctx + "view/frame/js/login.js"]);
        </c:if>
        <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT !=null}">
        window.location.href = "index.do";
        </c:if>
    </script>
    <style>
        .systemlogo {
            background: url(${sessionScope.areaInfo.loginLogoPath}) no-repeat center !important;
        }
    </style>
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
                    <%--<input type="checkbox" name="remember-me" value="true">记住密码--%>
                    <%--</label>--%>
                    <%--<label>--%>
                    <%--<a href="#">忘记密码？</a>--%>
                    <%--</label>--%>
                </li>
            </ul>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form>
    </div>

    <div id="dlg-verity" class="easyui-dialog" title="验证码"
         data-options="iconCls:'icon-key',closed: true, cache: false, modal: true,"
         style="width:300px;height:150px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <div class="container_12" style="padding-top: 10px">
                    <div class="grid_6 cell title">
                        <p>
                        <div id="v_container" style="width: 100%;"></div>
                        </p>
                    </div>
                    <div class="grid_6 cell">
                        <p>
                            <input id="verityCode" class="easyui-textbox" style="width: 100%;height: 30px;">
                        </p>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div data-options="region:'south'" style="height:50px;overflow: hidden;padding: 10px;">
                <div class="container_12">
                    <div class="grid_4 prefix_4 cell">
                        <p style="text-align: center;">
                            <a id="btn-verity-submit" style="width: 100%;max-width: 150px;"
                               href="javascript:void(0)"
                               class="easyui-linkbutton"
                               title="提交">提交</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="loginbm">
    ${sessionScope.areaLocal.name} <a href="http://www.miitbeian.gov.cn/">${sessionScope.areaLocal.icp}</a>
</div>
</body>
</html>
