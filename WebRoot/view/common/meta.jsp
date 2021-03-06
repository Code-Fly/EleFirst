<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2016/4/11
  Time: 15:58 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<title>${sessionScope.areaInfo.name}</title>
<SCRIPT type="text/javascript">
    var _ctx = "${ctx}";
    var _areaId = "${sessionScope.areaInfo.areaId}";
    var _areaName = "${sessionScope.areaInfo.name}";
    var _treeId = "${sessionScope.treeId}";

    var _userName = "${sessionScope.userInfo.userName}";
    var _userCode = "${sessionScope.userInfo.userCode}";
    var _roleName = "${sessionScope.userInfo.roleName}";

    var _csrfHeaderName = "${_csrf.headerName}"
    var _csrfParameterName = "${_csrf.parameterName}";
    var _csrToken = "${_csrf.token}";
</SCRIPT>

<link href="${ctx}Content/css/base.css?v=${timestamp}" rel="stylesheet" type="text/css"/>


<!-- easyui库 -->
<script type="text/javascript" src="${ctx}Content/js/jquery-easyui/jquery.min.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}Content/js/jquery-easyui/jquery.easyui.min.js?v=${timestamp}"></script>
<script type="text/javascript"
        src="${ctx}Content/js/jquery-easyui/locale/easyui-lang-zh_CN.js?v=${timestamp}"></script>
<script type="text/javascript"
        src="${ctx}Content/js/jquery-easyui-plugin/datagrid-dnd/datagrid-dnd.js?v=${timestamp}"></script>
<script type="text/javascript"
        src="${ctx}Content/js/jquery-easyui-plugin/datagrid-columns/columns-ext.js?v=${timestamp}"></script>
<script type="text/javascript"
        src="${ctx}Content/js/jquery-easyui-plugin/portal/jquery.portal.js?v=${timestamp}"></script>
<link rel="stylesheet" type="text/css"
      href="${ctx}Content/js/jquery-easyui/themes/default/easyui.css?v=${timestamp}"/>
<!-- theme定制图标与配色 -->
<link rel="stylesheet" type="text/css" href="${ctx}Content/css/theme/easyui_os_1.5.2.css?v=${timestamp}"/>
<link rel="stylesheet" type="text/css" href="${ctx}Content/css/theme/icon.css?v=${timestamp}"/>
<link rel="stylesheet" type="text/css" href="${ctx}Content/css/theme/icon2.css?v=${timestamp}"/>

<!--加载组件CSS-->
<script type="text/javascript" src="${ctx}view/utils/Showloading.js?v=${timestamp}"></script>


<%----%>
<script type="text/javascript" src="${ctx}Content/js/jquery-plugin/base64/jquery.base64.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}Content/js/lodash/lodash.js"></script>
<script type="text/javascript" src="${ctx}Content/js/requirejs/require.js"></script>
<script type="text/javascript"
        src="${ctx}Content/js/jquery-i18n-properties/jquery.i18n.properties.js?v=${timestamp}"></script>

<script type="text/javascript" src="${ctx}view/utils/global.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/SQLUtils.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/TimeUtils.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/HTMLUtils.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/CacheUtils.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/DashboardUtils.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/ChartUtils.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/ChartConfig.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/DateBoxUtils.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/DataGridUtils.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/Spinner.js?v=${timestamp}"></script>

<script type="text/javascript" src="${ctx}view/utils/easyui.default.loader.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/error.map.js?v=${timestamp}"></script>
<script type="text/javascript" src="${ctx}view/utils/easyui.validate.define.js?v=${timestamp}"></script>

<%-- color picker--%>
<link rel="stylesheet" href="${ctx}Content/js/jquery-plugin/color-picker/css/colorpicker.css?v=${timestamp}"
      type="text/css"/>
<script type="text/javascript"
        src="${ctx}Content/js/jquery-plugin/color-picker/js/colorpicker.js?v=${timestamp}"></script>

<script type="text/javascript" src="${ctx}Content/js/jquery-plugin/verify/gVerify.js?v=${timestamp}"></script>


<!-- 提示对话框 -->
<script type="text/javascript" src="${ctx}Content/js/jquery-notify/jNotify.jquery.js?v=${timestamp}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}Content/js/jquery-notify/jNotify.jquery.css?v=${timestamp}">

<SCRIPT type="text/javascript">
    require.config({
        urlArgs: "v=" + (new Date()).getTime(),
    });

    // you can set utf8 encoding and decoding via global option
    // possible options:
    // `utf8encode` - utf8 encoding only (default: `false`)
    // `utf8decode` - utf8 decoding only (default: `false`)
    // `raw` - both (default: `true`)
    $.base64.utf8encode = true;

    var headers = {};
    //防伪标记放入headers
    //也可以将防伪标记放入data
    headers[_csrfHeaderName] = _csrToken;
    $.ajaxSetup({
        statusCode: {
            403: function () {
                top.location.href = _ctx;
            },

        },
        headers: headers,
//        beforeSend: function (xhr) {
//            xhr.setRequestHeader(_csrfHeaderName, _csrToken);
//        },
    });

    $(document).ajaxComplete(function (event, xhr, settings) {
        if (xhr.getResponseHeader("sessionstatus") == "timeOut") {
            top.location.href = _ctx
        }
    });


    window.history.forward(1);

    //    require([_ctx + "Content/js/lodash/lodash.js"]);
</SCRIPT>
