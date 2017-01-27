<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2016/4/11
  Time: 15:58 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no"/>
<SCRIPT type="text/javascript">
    var _ctx = "${ctx}";
</SCRIPT>

<link href="${ctx}Content/css/base.css" rel="stylesheet" type="text/css"/>

<!-- theme定制图标与配色 -->
<link rel="stylesheet" type="text/css" href="${ctx}Content/css/theme/easyui_os_1.5.1.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}Content/css/theme/icon.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}Content/css/theme/icon2.css"/>
<!-- easyui库 -->
<script type="text/javascript" src="${ctx}Content/js/jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}Content/js/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}Content/js/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}Content/js/jquery-easyui-plugin/datagrid-dnd/datagrid-dnd.js"></script>
<script type="text/javascript" src="${ctx}Content/js/jquery-easyui-plugin/datagrid-columns/columns-ext.js"></script>
<script type="text/javascript" src="${ctx}Content/js/jquery-easyui-plugin/portal/jquery.portal.js"></script>

<!--加载组件CSS-->
<link href="${ctx}Content/js/jquery-plugin/showloading/showLoading.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}Content/js/viewModel/Showloading.js"></script>

<%----%>
<script type="text/javascript" src="${ctx}Content/js/jquery-plugin/base64/jquery.base64.js"></script>
<script type="text/javascript" src="${ctx}Content/js/requirejs-2.3.2/require.js"></script>
<script type="text/javascript" src="${ctx}Content/js/jquery-i18n-properties-1.2.2/jquery.i18n.properties.js"></script>

<script type="text/javascript" src="${ctx}Content/js/viewModel/global.js"></script>
<script type="text/javascript" src="${ctx}Content/js/viewModel/SQLUtils.js"></script>
<script type="text/javascript" src="${ctx}Content/js/viewModel/TimeUtils.js"></script>
<script type="text/javascript" src="${ctx}Content/js/viewModel/HTMLUtils.js"></script>
<script type="text/javascript" src="${ctx}Content/js/viewModel/CacheUtils.js"></script>
<script type="text/javascript" src="${ctx}Content/js/viewModel/DashboardUtils.js"></script>

<!-- 注释原因:经过测试发现引入下面 两个js datagrid 不显示也不报错-->
<script type="text/javascript" src="${ctx}Content/js/viewModel/easyui.default.loader.js"></script>
<script type="text/javascript" src="${ctx}Content/js/viewModel/error.map.js"></script> 
<script type="text/javascript" src="${ctx}Content/js/viewModel/easyui.validate.define.js"></script> 


<!-- 提示对话框 -->
<script type="text/javascript" src="${ctx}Content/js/jquery-notify/jNotify.jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}Content/js/jquery-notify/jNotify.jquery.css">

<SCRIPT type="text/javascript">
    require.config({
        urlArgs: "bust=" + (new Date()).getTime(),
    });

    // you can set utf8 encoding and decoding via global option
    // possible options:
    // `utf8encode` - utf8 encoding only (default: `false`)
    // `utf8decode` - utf8 decoding only (default: `false`)
    // `raw` - both (default: `true`)
    $.base64.utf8encode = true;

</SCRIPT>