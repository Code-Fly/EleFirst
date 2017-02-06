<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/31
  Time: 下午2:02
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
<div data-options="region:'center',border:false" style="padding: 5px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',title:'树形',tools:'#tool-tree'" style="width:200px;overflow: hidden">
            <ul id="dTree" class="easyui-tree">

            </ul>
        </div>
        <div id="tool-tree">
            <a href="javascript:void(0)" id="btn-tree-tool-add" class="icon-add"></a>
            <a href="javascript:void(0)" id="btn-tree-tool-edit" class="icon-edit"></a>
            <a href="javascript:void(0)" id="btn-tree-tool-delete" class="icon-cross"></a>
            <a href="javascript:void(0)" id="btn-tree-tool-refresh" class="icon-reload"></a>
        </div>
        <div data-options="region:'center',border:false" style="padding-left: 5px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'center',border:false" style="overflow: hidden">
                    <div id="tt" class="easyui-tabs" data-options="fit:true">
                        <div title="监测点">
                            <table id="dg-pn-detail" class="easyui-datagrid"
                                   data-options="border:false,fit:true,rownumbers:true,singleSelect:true,toolbar:'#tool-pn'">
                                <%--<thead>--%>
                                <%--<tr>--%>
                                <%--<th field="name" width="120" align="center">监测点名称</th>--%>
                                <%--<th field="pn" width="120" align="center">监测点编号</th>--%>
                                <%--<th field="concentratorName" width="120" align="center">馈线柜名称</th>--%>
                                <%--<th field="concentratorId" width="120" align="center">馈线柜编号</th>--%>
                                <%--<th field="ct" width="120" align="center">CT</th>--%>
                                <%--<th field="pt" width="120" align="center">PT</th>--%>
                                <%--<th field="powerFactorStandard" width="120" align="center">功率因数标准</th>--%>
                                <%--</tr>--%>
                                <%--</thead>--%>
                            </table>
                            <div id="tool-pn" style="">
                                <a href="#" id="btn-pn-tool-add" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-add'">添加</a>
                                <a href="#" id="btn-pn-tool-edit" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-edit'">编辑</a>
                                <a href="#" id="btn-pn-tool-delete" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-cross'">删除</a>
                                <a href="#" id="btn-pn-tool-load-current" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-arrow_refresh'">加载当前</a>
                                <a href="#" id="btn-pn-tool-load-all" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-arrow_refresh'">加载全部</a>
                            </div>
                        </div>
                        <div title="馈线柜">
                            <table id="dg-concentrator-detail" class="easyui-datagrid"
                                   data-options="border:false,fit:true,rownumbers:true,singleSelect:true,toolbar:'#tool-concentrator'">

                            </table>
                            <div id="tool-concentrator" style="">
                                <a href="#" id="btn-concentrator-tool-add" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-add'">添加</a>
                                <a href="#" id="btn-concentrator-tool-edit" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-edit'">编辑</a>
                                <a href="#" id="btn-concentrator-tool-delete" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-cross'">删除</a>
                                <a href="#" id="btn-concentrator-tool-load-current" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-arrow_refresh'">加载当前</a>
                                <a href="#" id="btn-concentrator-tool-load-all" class="easyui-linkbutton"
                                   data-options="plain:true,iconCls:'icon-arrow_refresh'">加载全部</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div id="dlg-add-tree-node" class="easyui-dialog" title="树形节点"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:400px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <div class="container_12" style="padding-top: 10px">
                    <div class="grid_4 cell title">
                        <p>
                            名称
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="text-tree-node-name" class="easyui-textbox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            类型
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="combo-tree-node-type" class="easyui-combobox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            图标
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="combo-tree-node-iconCls" class="easyui-combobox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title cell-concentratorId">
                        <p>
                            馈线柜
                        </p>
                    </div>
                    <div class="grid_8 cell cell-concentratorId">
                        <p>
                            <input id="combo-tree-node-concentratorId" class="easyui-combobox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div data-options="region:'south'" style="height:50px;overflow: hidden;">
                <div class="container_12">
                    <%--<div class="grid_4 prefix_2 cell">--%>
                    <%--<p style="text-align: center;">--%>
                    <%--<a id="btn-reset" style="width: 150px;" href="javascript:void(0)" class="easyui-linkbutton"--%>
                    <%--icon="icon-arrow_refresh" title="重置">重置</a>--%>
                    <%--</p>--%>
                    <%--</div>--%>
                    <div class="grid_4 prefix_4 cell">
                        <p style="text-align: center;">
                            <a id="btn-tree-dlg-add-tree-node-submit" style="width: 100%;max-width: 150px;"
                               href="javascript:void(0)"
                               class="easyui-linkbutton"
                               icon="icon-save" title="提交">提交</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="dlg-add-pn-node" class="easyui-dialog" title="监测点"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:400px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <div class="container_12" style="padding-top: 10px">
                    <div class="grid_4 cell title">
                        <p>
                            名称
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="text-dg-pn-name" class="easyui-textbox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            监测点编号
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="text-dg-pn-id" class="easyui-textbox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title cell-concentratorId">
                        <p>
                            馈线柜
                        </p>
                    </div>
                    <div class="grid_8 cell cell-concentratorId">
                        <p>
                            <input id="combo-dg-pn-concentratorId" class="easyui-combobox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            CT
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="text-dg-pn-ct" class="easyui-textbox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            PT
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="text-dg-pn-pt" class="easyui-textbox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            功率因数标准
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="text-dg-pn-powerFactorStandard" class="easyui-textbox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div data-options="region:'south'" style="height:50px;overflow: hidden;">
                <div class="container_12">
                    <%--<div class="grid_4 prefix_2 cell">--%>
                    <%--<p style="text-align: center;">--%>
                    <%--<a id="btn-reset" style="width: 150px;" href="javascript:void(0)" class="easyui-linkbutton"--%>
                    <%--icon="icon-arrow_refresh" title="重置">重置</a>--%>
                    <%--</p>--%>
                    <%--</div>--%>
                    <div class="grid_4 prefix_4 cell">
                        <p style="text-align: center;">
                            <a id="btn-dg-dlg-add-pn-submit" style="width: 100%;max-width: 150px;"
                               href="javascript:void(0)"
                               class="easyui-linkbutton"
                               icon="icon-save" title="提交">提交</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="dlg-add-concentrator-node" class="easyui-dialog" title="馈线柜"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:400px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <div class="container_12" style="padding-top: 10px">
                    <div class="grid_4 cell title">
                        <p>
                            名称
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="text-dg-concentrator-name" class="easyui-textbox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            馈线柜编号
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input id="text-dg-concentrator-id" class="easyui-textbox" style="width: 100%;">
                        </p>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div data-options="region:'south'" style="height:50px;overflow: hidden;">
                <div class="container_12">
                    <%--<div class="grid_4 prefix_2 cell">--%>
                    <%--<p style="text-align: center;">--%>
                    <%--<a id="btn-reset" style="width: 150px;" href="javascript:void(0)" class="easyui-linkbutton"--%>
                    <%--icon="icon-arrow_refresh" title="重置">重置</a>--%>
                    <%--</p>--%>
                    <%--</div>--%>
                    <div class="grid_4 prefix_4 cell">
                        <p style="text-align: center;">
                            <a id="btn-dg-dlg-add-concentrator-submit" style="width: 100%;max-width: 150px;"
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
