<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/26
  Time: 上午2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <!-- 公共属性 -->
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>
    <script type="text/javascript">
        require(["js/rolemanager.js"]);
    </script>
    <style>
        .title p {
            text-align: left;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false"
     style="overflow: hidden;">
    <table id="tt2" class="easyui-datagrid"
           data-options="border:false,fit:true,rownumbers:true,singleSelect:true,toolbar:'#tool-table'">
        <thead>
        <tr>
            <th field="id" hidden="true"></th>
            <th field="roleName" width="200" formatter="DataGridUtils.strFormatter">角色名称</th>
            <th field="roleCode" width="200" formatter="DataGridUtils.strFormatter">角色编码</th>
            <th field="createPerson" width="100" formatter="DataGridUtils.strFormatter">创建人</th>
            <th field="createDate" width="200" formatter="DataGridUtils.timestampToMinuteFormatter">创建日期</th>
            <th field="description" width="300" formatter="DataGridUtils.strFormatter">描述</th>
        </tr>
        </thead>
    </table>

    <div id="tool-table" style="">
        <a href="#" id="btn-pn-tool-add" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'">添加</a>
        <a href="#" id="btn-pn-tool-edit" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        <a href="#" id="btn-pn-tool-delete" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-cross'">删除</a>
        <a href="#" id="btn-pn-tool-load-current" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-building'">分配企业</a>
    </div>
</div>
<div id="dlg" class="easyui-dialog"
     style="width: 400px; height: 300px;" data-options="closed: true, cache: false, modal: true,">
    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center'">
            <div class="container_12">
                <form id="ff" method="get">
                    <div class="grid_4 cell title">
                        <p>
                            角色名称
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input class="easyui-textbox" id="roleName" name="roleName"
                                   style="width: 100%" data-options="required:true">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            角色编码
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input class="easyui-textbox" id="roleCode" name="roleCode"
                                   style="width: 100%" data-options="required:true">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            描述说明
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input class="easyui-textbox" id="description" name="description"
                                   validType="length[2,100]" style="width: 100%; height: 60px"
                                   data-options="multiline:true">
                        </p>
                    </div>
                    <div class="clear"></div>
                </form>
            </div>
        </div>
        <div data-options="region:'south'" style="height:50px;overflow: hidden;">
            <div class="container_12">
                <div class="grid_4 prefix_4 cell">
                    <p style="text-align: center;">
                        <a style="width: 100%;max-width: 150px;" id="btn-dlg-submit"
                           href="javascript:void(0)"
                           class="easyui-linkbutton"
                           icon="icon-save" title="提交">提交</a>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <div id="dlg2" class="easyui-dialog" title="添加角色"
         style="width: 800px; height: 400px;" closed="true"
         buttons="#dlg-buttons" style="display:none"
         data-options="modal:true,resizable:true">
        <div id="tt1" class="easyui-tabs" fit="true"/>
    </div>

    <div id="dlg3" class="easyui-dialog" title="编辑角色"
         style="width: 800px; height: 400px;" closed="true"
         buttons="#dlg-buttons" style="display:none"
         data-options="modal:true,resizable:true">
        <div id="tt3" class="easyui-tabs" fit="true"/>
    </div>
</div>
<div id="dlg4" class="easyui-dialog" title="企业分配"
     style="width: 850px; height: 350px;" closed="true"
     buttons="#dlg-buttons" style="display:none" data-options="modal:true,resizable:true">
    <div id="cc" class="easyui-layout" fit="true">
        <div data-options="region:'center',border:false" style="height:80%;">
            <table id="tt4">
                <thead>
                <tr>
                    <th field="id" hidden="true"></th>
                    <th field="name" width="200" formatter="DataGridUtils.strFormatter">企业名称</th>
                    <th field="areaId" width="200" formatter="DataGridUtils.strFormatter">企业编码</th>
                    <th field="createPerson" width="100" formatter="DataGridUtils.strFormatter">创建人</th>
                    <th field="createDate" width="200" formatter="DataGridUtils.timestampToMinuteFormatter">创建日期</th>
                </tr>
                </thead>
            </table>
        </div>
        <div data-options="region:'south'" style="height:50px;overflow: hidden;">
            <div class="container_12">
                <div class="grid_4 prefix_4 cell">
                    <p style="text-align: center;">
                        <a style="width: 100%;max-width: 150px;" id="btn-dlg4-submit"
                           href="javascript:void(0)"
                           class="easyui-linkbutton"
                           icon="icon-save" title="提交">提交</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>