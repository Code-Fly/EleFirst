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
<div data-options="region:'center',border:false" style="padding: 5px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',title:'收费项目',tools:'#tool-tree'" style="width:200px;overflow: hidden">
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
                    <table id="dgList" class="easyui-datagrid" toolbar="#dgListToolbar"
                           singleSelect="true" rownumbers="true"
                           fit="true">

                        <thead>
                        <tr>
                            <th field="corpName" width="150" align="center">单位名称</th>
                            <th field="corpCode" width="150" align="center">单位编号</th>
                            <th field="name" width="150" align="center">收费项目名称</th>
                            <th field="evalTimeStart" width="150" align="center">有效期（起）</th>
                            <th field="evalTimeEnd" width="150" align="center">有效期（止）</th>
                            <th field="type" width="150" align="center">收费类别</th>
                            <th field="calcType" width="150" align="center">金额计算方式</th>
                            <th field="unitPrice" width="150" align="center">单价</th>
                            <th field="jfr" width="150" align="center">计费日</th>
                            <th field="jfjzr" width="150" align="center">缴费截止日</th>
                            <th field="zdkfr" width="150" align="center">自动扣费日</th>
                            <th field="wyj" width="150" align="center">违约金</th>
                            <th field="ycxyhje" width="150" align="center">一次性优惠金额</th>
                            <th field="ycxyhwjbh" width="150" align="center">一次性优惠文件编号</th>
                            <th field="enable" width="150" align="center">启用状态</th>
                            <th field="comment" width="150" align="center">备注</th>
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
                            <a href="#" id="btn-pn-tool-enable" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-play_green'">启用</a>
                            <a href="#" id="btn-pn-tool-disable" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-stop_red'">禁用</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div id="dlg-add-user" class="easyui-dialog" title="收费标准"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:470px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <form id="form-add-user" class="easyui-form" method="post" data-options="novalidate:true">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_3 cell title">
                            <p>
                                金额计算方式
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-combobox" value="单一制（单价x数量）" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                单价
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
                                计量方式
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-combobox" value="用电量（千瓦时）" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                计费周期
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                每隔<input class="easyui-numberspinner" value="1" style="width: 50px;">月计费一次
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                计费日
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-numberspinner" value="1" style="width: 50px;">日
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                缴费截止日
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-numberspinner" value="1" style="width: 50px;">日
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                自动扣费日
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-numberspinner" value="1" style="width: 50px;">日
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                违约金
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                逾期未缴按欠费金额<input class="easyui-numberspinner" value="1" style="width: 50px;">%/天收取
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                优惠金额
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-numberspinner" value="0" style="width: 50px;">元
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                文件编号
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
                                有效期（起）
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-datebox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                有效期（止）
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-datebox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                备注
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
    <div id="dlg-add-tree-node" class="easyui-dialog" title="收费项目"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:470px;height:300px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <form id="form-add-tree-node" class="easyui-form" method="post" data-options="novalidate:true">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_3 cell title">
                            <p>
                                收费项目名称
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
                                收费类别
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-combobox" value="周期性收费" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                备注
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
</div>
</body>
</html>