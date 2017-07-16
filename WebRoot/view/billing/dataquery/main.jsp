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
                    <iframe src="ysmx.jsp" style="width: 100%;height: 100%;" frameborder="no"
                            border="0"
                            marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
                </div>
            </div>
        </div>
        <div title="未收明细">
            <div class="easyui-layout" data-options="fit:true" style="height:100%;">
                <div data-options="region:'center'" border="false" style="overflow: hidden">
                    <iframe src="wsmx.jsp" style="width: 100%;height: 100%;" frameborder="no"
                            border="0"
                            marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
                </div>
            </div>
        </div>
        <div title="应收明细">
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
                            <th field="idNo" width="150" align="center">身份证号</th>
                            <th field="jiaofeiType" width="150" align="center">缴费模式</th>
                            <th field="period" width="150" align="center">计费期间</th>
                            <th field="billType" width="150" align="center">收费项目</th>
                            <th field="yingshou" width="150" align="center">应收金额</th>
                            <th field="weishou" width="150" align="center">未收金额</th>
                            <th field="shishou" width="150" align="center">实收金额</th>
                            <th field="chongxiao" width="150" align="center">冲销金额</th>
                            <th field="owe" width="150" align="center">违约金</th>
                            <th field="youhui" width="150" align="center">优惠</th>
                            <th field="operator" width="150" align="center">操作员</th>
                            <th field="opTime" width="150" align="center">操作日期</th>
                            <th field="comment" width="150" align="center">备注</th>
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
                                统计时间从：
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
                                收费截止时间：
                            </p>
                        </div>
                        <div class="grid_2 cell">
                            <p>
                                <input class="easyui-datebox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="grid_2 cell">
                            <p>
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_2 cell title">
                            <p>
                                状态：
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
                        <div class="grid_2 cell title">
                            <p>
                                所在单元：
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
            </div>
        </div>
    </div>


</div>
</body>
</html>