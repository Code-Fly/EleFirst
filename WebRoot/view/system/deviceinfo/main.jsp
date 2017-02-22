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
                                <%--<th field="concentratorName" width="120" align="center">集中器名称</th>--%>
                                <%--<th field="concentratorId" width="120" align="center">集中器编号</th>--%>
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
                        <div title="集中器">
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
                    <%--<div class="clear"></div>--%>
                    <%--<div class="grid_4 cell title cell-concentratorId">--%>
                    <%--<p>--%>
                    <%--集中器--%>
                    <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="grid_8 cell cell-concentratorId">--%>
                    <%--<p>--%>
                    <%--<input id="combo-tree-node-concentratorId" class="easyui-combobox" style="width: 100%;">--%>
                    <%--</p>--%>
                    <%--</div>--%>
                    <div class="clear"></div>
                    <div class="grid_4 cell title cell-concentratorId">
                        <p>
                            监测点
                        </p>
                    </div>
                    <div class="grid_8 cell cell-pn">
                        <p>
                            <input id="tagbox-tree-node-pn" class="easyui-tagbox" style="width: 100%;">
                            <input id="hid-tree-node-pn" type="hidden" value="[]">
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
                <form id="form-add-pn-node" class="easyui-form" method="post" data-options="novalidate:true,fit:true">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_4 cell title">
                            <p>
                                名称
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-name" name="name" class="easyui-textbox" style="width: 100%;"
                                       required="true">
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
                                <input id="text-dg-pn-id" name="pn" class="easyui-textbox" style="width: 100%;"
                                       required="true">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title cell-concentratorId">
                            <p>
                                集中器
                            </p>
                        </div>
                        <div class="grid_8 cell cell-concentratorId">
                            <p>
                                <input id="combo-dg-pn-concentratorId" name="concentratorId" class="easyui-combobox"
                                       style="width: 100%;"
                                       editable="false" required="true" textField="name" valueField="concentratorId">
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
                                <input id="text-dg-pn-ct" name="ct" class="easyui-textbox" style="width: 100%;"
                                       required="true">
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
                                <input id="text-dg-pn-pt" name="pt" class="easyui-textbox" style="width: 100%;"
                                       required="true">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电压标准
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-voltageStandard" name="voltageStandard"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电压上限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-voltageUpperLimit" name="voltageUpperLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电压下限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-voltageLowerLimit" name="voltageLowerLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电流标准
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-currentStandard" name="currentStandard"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电流上限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-currentUpperLimit" name="currentUpperLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电流下限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-currentLowerLimit" name="currentLowerLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                负荷标准
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-loadStandard" name="loadStandard"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                负荷上限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-loadUpperLimit" name="loadUpperLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                负荷下限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-loadLowerLimit" name="loadLowerLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电量标准
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-electricityStandard" name="electricityStandard"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电量上限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-electricityUpperLimit" name="electricityUpperLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                电量下限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-electricityLowerLimit" name="electricityLowerLimit"
                                       class="easyui-textbox" style="width: 100%;">
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
                                <input id="text-dg-pn-powerFactorStandard" name="powerFactorStandard"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                功率因数上限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-powerFactorUpperLimit" name="powerFactorUpperLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_4 cell title">
                            <p>
                                功率因数下限(%)
                            </p>
                        </div>
                        <div class="grid_8 cell">
                            <p>
                                <input id="text-dg-pn-powerFactorLowerLimit" name="powerFactorLowerLimit"
                                       class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </form>
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

    <div id="dlg-add-concentrator-node" class="easyui-dialog" title="集中器"
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
                            集中器编号
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
