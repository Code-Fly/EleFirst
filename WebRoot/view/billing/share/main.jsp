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
           fitColumns="true"
           border="false" fit="true">
        <thead>
        <tr>
            <th field="name" width="150" align="center">公摊名称</th>
            <th field="type" width="150" align="center">公摊方式</th>
            <th field="unit" width="150" align="center">用能公摊单元</th>
        </tr>
        </thead>
    </table>
    <div id="dgListToolbar" style="padding:5px;height:auto">
        <div>
            <a href="#" id="btn-pn-tool-add" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-add'">添加</a>
            <a href="#" id="btn-pn-tool-edit" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-edit'">编辑</a>
            <a href="#" id="btn-pn-tool-delete" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-cross'">删除</a>
        </div>
    </div>
    <div id="dlg-add-user" class="easyui-dialog" title="公摊管理"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:470px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <form id="form-add-tree-node" class="easyui-form" method="post" data-options="novalidate:true">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_3 cell title">
                            <p>
                                公摊名称
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
                                公摊方式
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
                                用能公摊单元
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
    <div id="dlg-pn-config" class="easyui-dialog" title="表计对应"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:470px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <table id="dgPnConfigList" class="easyui-datagrid"
                       singleSelect="false"
                       border="false" fit="true">
                    <thead>
                    <tr>
                        <th field="pnName" width="150" align="center">表计名称</th>
                        <th field="pnAddr" width="150" align="center">表计地址</th>
                        <th field="pnType" width="150" align="center">电表类型</th>
                        <th field="unit" width="150" align="center">所属单元</th>
                        <th field="comment" width="150" align="center">备注</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div data-options="region:'south'" style="height:50px;overflow: hidden;">
                <div class="container_12">
                    <div class="grid_4 prefix_4 cell">
                        <p style="text-align: center;">
                            <a id="btn-dlg-pn-config-submit" style="width: 100%;max-width: 150px;"
                               href="javascript:void(0)"
                               class="easyui-linkbutton"
                               icon="icon-save" title="提交">提交</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="dlg-recharge" class="easyui-dialog" title="预交收费"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:470px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <form id="form-recharge" class="easyui-form" method="post" data-options="novalidate:true">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_3 cell title">
                            <p>
                                账户余额
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" value="0" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                预交金额
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
                            <a id="btn-dlg-recharge-submit" style="width: 100%;max-width: 150px;"
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