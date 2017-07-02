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
    <table id="dgList" class="easyui-datagrid" toolbar="#dgListToolbar"
           singleSelect="true"
           border="false" fit="true">
        <thead frozen="true">
        <tr>
            <th field="userName" width="100" align="center">用户名</th>
            <th field="userCode" width="100" align="center">用户编号</th>
        </tr>
        </thead>
        <thead>
        <tr>
            <th field="electricity" width="150" align="center">电费</th>
            <th field="water" width="150" align="center">水费</th>
            <th field="gas" width="150" align="center">气费</th>
            <th field="heat" width="150" align="center">热费</th>
            <th field="property" width="150" align="center">物业费</th>
            <th field="total" width="150" align="center">应收合计</th>
            <th field="pay" width="150" align="center">实收金额</th>
            <th field="change" width="150" align="center">找零</th>
        </tr>
        </thead>
    </table>
    <div id="dgListToolbar" style="padding:5px;height:auto">
        <div>
            <a href="#" id="btn-pn-tool-add" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-money_yen'">现金收费</a>
            <a href="#" id="btn-pn-tool-edit" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-mail'">打印票据</a>
            <a href="#" id="btn-pn-tool-export" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
        </div>
    </div>
    <div id="dlg-add-user" class="easyui-dialog" title="用户管理"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:470px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <form id="form-add-tree-node" class="easyui-form" method="post" data-options="novalidate:true">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_3 cell title">
                            <p>
                                电费
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" value="201.3" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                水费
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" value="35.3" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                气费
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" value="21.1" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                热费
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" value="62.3" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                物业费
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" value="15" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                应收合计
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" value="335" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                实收金额
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                找零
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </form>
            </div>
            <div data-options="region:'south'" style="height:50px;overflow: hidden;">
                <div class="container_12">
                    <div class="grid_4 prefix_4 cell">
                        <p style="text-align: center;">
                            <a id="btn-dlg-add-user-submit" style="width: 100%;max-width: 150px;"
                               href="javascript:void(0)"
                               class="easyui-linkbutton"
                               icon="icon-save" title="提交">提交</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>