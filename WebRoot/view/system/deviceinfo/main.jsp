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
        <div data-options="region:'west',title:'监测点',tools:'#tool-tree'" style="width:200px;overflow: hidden">
            <ul id="dTree" class="easyui-tree">

            </ul>
        </div>
        <div id="tool-tree">
            <a href="javascript:void(0)" id="btn-tree-tool-add" class="icon-add"></a>
            <a href="javascript:void(0)" id="btn-tree-tool-delete" class="icon-cross"></a>
            <a href="javascript:void(0)" id="btn-tree-tool-refresh" class="icon-reload"></a>
        </div>
        <div data-options="region:'center',border:false" style="padding-left: 5px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'center'" style="overflow: hidden">

                </div>
            </div>
        </div>
    </div>
    <div id="dlg-add-tree-node" class="easyui-dialog" title="添加档案"
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
                            <input id="combo-tree-node-iconcls" class="easyui-combobox" style="width: 100%;">
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
</div>
</body>
</html>
