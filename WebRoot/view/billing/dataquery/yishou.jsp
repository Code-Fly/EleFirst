<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 2017/7/17
  Time: 上午12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>

    <script type="text/javascript">
        require(["js/yishou.js"]);
    </script>
    <style>
        .title p {
            text-align: left;
        }
    </style>
</head>
<body id="cc1" class="easyui-layout">
<div data-options="region:'center',border:false" style="overflow: hidden">
    <table id="dgList_1" class="easyui-datagrid" toolbar="#dgListToolbar_1"
           singleSelect="true" rownumbers="true"
           border="false" fit="true">
        <thead frozen="true">
        <tr>
            <th field="userName" width="100" align="center">用户名</th>
            <th field="userCode" width="100" align="center">用户编号</th>
        </tr>
        </thead>
        <thead>
        <tr>
            <th field="idNo" width="150" align="center">身份证号</th>
            <th field="period" width="150" align="center">计费期间</th>
            <th field="billType" width="150" align="center">收费项目</th>
            <th field="jijiaType" width="150" align="center">计价类型</th>
            <th field="kpType" width="150" align="center">开票方式</th>
            <th field="payType" width="150" align="center">付款方式</th>
            <th field="amount" width="150" align="center">用能量</th>
            <th field="price" width="150" align="center">单价</th>
            <th field="pay" width="150" align="center">收费金额</th>
            <th field="payId" width="150" align="center">打印流水号</th>
            <th field="operator" width="150" align="center">操作员</th>
            <th field="opTime" width="150" align="center">操作日期</th>
            <th field="comment" width="150" align="center">备注</th>
        </tr>
        </thead>
    </table>
    <div id="dgListToolbar_1" style="padding:5px;height:auto">
        <div>
            <a href="#" id="btn-pn-tool-search_1" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-search'">查询</a>
            <a href="#" id="btn-pn-tool-export_1" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
        </div>
    </div>
</div>
<div data-options="region:'south',title:'查询',border:false,collapsed:true,collapsible:true"
     style="height:100%;">
    <div class="container_12" style="padding-top: 10px">
        <div class="grid_2 cell title">
            <p>
                操作时间：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-datebox" style="width: 100%;">
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-datebox" style="width: 100%;">
            </p>
        </div>
        <div class="grid_2 cell title">
            <p>
                计费时间：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-datebox" style="width: 100%;">
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-datebox" style="width: 100%;">
            </p>
        </div>
        <div class="clear"></div>
        <div class="grid_2 cell title">
            <p>
                开票方式：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-combobox" style="width: 100%;">
            </p>
        </div>
        <div class="grid_2 cell title">
            <p>
                收款方式：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-combobox" style="width: 100%;">
            </p>
        </div>
        <div class="grid_2 cell title">
            <p>
                收费项目：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-combobox" style="width: 100%;">
            </p>
        </div>
        <div class="clear"></div>
        <div class="grid_2 cell title">
            <p>
                用户编号：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-textbox" style="width: 100%;">
            </p>
        </div>
        <div class="grid_2 cell title">
            <p>
                用户名：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-textbox" style="width: 100%;">
            </p>
        </div>
        <div class="grid_2 cell title">
            <p>
                身份证号：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-textbox" style="width: 100%;">
            </p>
        </div>
        <div class="clear"></div>
        <div class="grid_2 cell title">
            <p>
                打印流水号：
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <input class="easyui-textbox" style="width: 100%;">
            </p>
        </div>
        <div class="grid_2 cell title">
            <p>
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
            </p>
        </div>
        <div class="grid_2 cell title">
            <p>
            </p>
        </div>
        <div class="grid_2 cell">
            <p>
                <a style="width: 100%;max-width: 150px;"
                   href="javascript:void(0)"
                   class="easyui-linkbutton"
                   icon="icon-search" title="查询">查询</a>
            </p>
        </div>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>
