<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 2017/7/17
  Time: 上午12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>

    <script type="text/javascript">
        require(["js/wsmx.js"]);
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
        <div title="按客户查询">
            <div id="cc1" class="easyui-layout" data-options="fit:true" style="height:100%;">
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
                            <th field="lxfs" width="150" align="center">联系方式</th>
                            <th field="jzmj" width="150" align="center">建筑面积</th>
                            <th field="qfze" width="150" align="center">欠费总额</th>
                            <th field="wyj" width="150" align="center">违约金</th>
                            <th field="zhye" width="150" align="center">账户余额</th>
                            <th field="czr" width="150" align="center">操作人</th>
                            <th field="czrq" width="150" align="center">操作日期</th>
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
                                欠费时间：
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
                                收费项目：
                            </p>
                        </div>
                        <div class="grid_2 cell">
                            <p>
                                <input class="easyui-combobox" value="全部" style="width: 100%;">
                            </p>
                        </div>
                        <div class="grid_2 cell title">
                            <p>
                                收费区域：
                            </p>
                        </div>
                        <div class="grid_2 cell">
                            <p>
                                <input class="easyui-combobox" value="全部" style="width: 100%;">
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
            </div>
        </div>
        <div title="按费用明细查询">
            <div id="cc2" class="easyui-layout" data-options="fit:true" style="height:100%;">
                <div data-options="region:'center',border:false" style="overflow: hidden">
                    <table id="dgList_2" class="easyui-datagrid" toolbar="#dgListToolbar_2"
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
                            <th field="lxfs" width="150" align="center">联系方式</th>
                            <th field="jzmj" width="150" align="center">建筑面积</th>
                            <th field="qfze" width="150" align="center">欠费总额</th>
                            <th field="wyj" width="150" align="center">违约金</th>
                            <th field="bl" width="150" align="center">倍率</th>
                            <th field="sl" width="150" align="center">数量</th>
                            <th field="kssj" width="150" align="center">开始时间</th>
                            <th field="jssj" width="150" align="center">结束时间</th>
                            <th field="sh" width="150" align="center">损耗</th>
                            <th field="zhye" width="150" align="center">账户余额</th>
                        </tr>
                        </thead>
                    </table>
                    <div id="dgListToolbar_2" style="padding:5px;height:auto">
                        <div>
                            <a href="#" id="btn-pn-tool-search_2" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-search'">查询</a>
                            <a href="#" id="btn-pn-tool-export_2" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
                        </div>
                    </div>
                </div>
                <div data-options="region:'south',title:'查询',border:false,collapsed:true,collapsible:true"
                     style="height:100%;">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_2 cell title">
                            <p>
                                欠费时间：
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
                                收费项目：
                            </p>
                        </div>
                        <div class="grid_2 cell">
                            <p>
                                <input class="easyui-combobox" value="全部" style="width: 100%;">
                            </p>
                        </div>
                        <div class="grid_2 cell title">
                            <p>
                                收费区域：
                            </p>
                        </div>
                        <div class="grid_2 cell">
                            <p>
                                <input class="easyui-combobox" value="全部" style="width: 100%;">
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
            </div>
        </div>
        <div title="按到期时间查询">
            <div id="cc3" class="easyui-layout" data-options="fit:true" style="height:100%;">
                <div data-options="region:'center',border:false" style="overflow: hidden">
                    <table id="dgList_3" class="easyui-datagrid" toolbar="#dgListToolbar_3"
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
                            <th field="lxfs" width="150" align="center">收费项目</th>
                            <th field="kssj" width="150" align="center">开始时间</th>
                            <th field="jssj" width="150" align="center">结束时间</th>
                            <th field="zhye" width="150" align="center">账户余额</th>
                        </tr>
                        </thead>
                    </table>
                    <div id="dgListToolbar_3" style="padding:5px;height:auto">
                        <div>
                            <a href="#" id="btn-pn-tool-search_3" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-search'">查询</a>
                            <a href="#" id="btn-pn-tool-export_3" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
                        </div>
                    </div>
                </div>
                <div data-options="region:'south',title:'查询',border:false,collapsed:true,collapsible:true"
                     style="height:100%;">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_2 cell title">
                            <p>
                                费用到期时间：
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
                                收费项目：
                            </p>
                        </div>
                        <div class="grid_2 cell">
                            <p>
                                <input class="easyui-combobox" value="全部" style="width: 100%;">
                            </p>
                        </div>
                        <div class="grid_2 cell title">
                            <p>
                                收费区域：
                            </p>
                        </div>
                        <div class="grid_2 cell">
                            <p>
                                <input class="easyui-combobox" value="全部" style="width: 100%;">
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
            </div>
        </div>
    </div>


</div>
</body>
</html>
