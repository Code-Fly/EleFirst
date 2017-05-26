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
        require(["js/usermanager.js"]);
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
            <th field="userName" width="200" formatter="DataGridUtils.strFormatter">用户名</th>
            <th field="userCode" width="200" formatter="DataGridUtils.strFormatter">用户编码</th>
            <th field="createPerson" width="100" formatter="DataGridUtils.strFormatter">创建者</th>
            <th field="createDate" width="200" formatter="DataGridUtils.timestampToMinuteFormatter">创建日期</th>
            <th field="description" width="300">描述</th>
        </tr>
        </thead>
    </table>
    <div id="tool-table" style="">
        <a href="#" id="btn-pn-tool-add" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-add'" onclick="addClick()">添加</a>
        <a href="#" id="btn-pn-tool-edit" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-edit'" onclick="editClick()">编辑</a>
        <a href="#" id="btn-pn-tool-delete" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-cross'" onclick="deleteClick()">删除</a>
        <a href="#" id="btn-pn-tool-load-current" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-user_key'" onclick="roleClick()">分配角色</a>
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
                            用户名
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input class="easyui-textbox" name="userName"
                                   data-options="required:true" style="width: 100%">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            用户编码
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input class="easyui-textbox" name="userCode"
                                   data-options="required:true" style="width: 100%">
                        </p>
                    </div>
                    <div class="clear"></div>
                    <div class="grid_4 cell title">
                        <p>
                            密码
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input name="passwd" validType="length[4,32]" style="width: 100%"
                                   class="easyui-textbox" required="true" type="password"
                                   value=""/>
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
                            <input class="easyui-textbox" name="description"
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
                        <a style="width: 100%;max-width: 150px;"
                           href="javascript:void(0)"
                           class="easyui-linkbutton"
                           title="提交" onclick="submitForm()">提交</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="dlgUpdate" class="easyui-dialog"
     style="width: 400px; height: 300px;" data-options="closed: true, cache: false, modal: true,">
    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center'">
            <div class="container_12">
                <form id="ffs" method="get">
                    <div class="grid_4 cell title">
                        <p>
                            用户名
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input class="easyui-textbox" id="userName" name="userName"
                                   style="width: 100%" data-options="required:true">
                        </p>
                    </div>
                    <div class="clear"></div>

                    <div class="grid_4 cell title">
                        <p>
                            用户编码
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input class="easyui-textbox" id="userCode" name="userCode"
                                   style="width: 100%" data-options="required:true">
                        </p>
                    </div>
                    <div class="clear"></div>

                    <div class="grid_4 cell title">
                        <p>
                            密码
                        </p>
                    </div>
                    <div class="grid_8 cell">
                        <p>
                            <input name="passwd" validType="length[4,32]" style="width: 100%"
                                   class="easyui-textbox" required="true" type="password"
                                   value=""/>
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
                            <input class="easyui-textbox" name="description"
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
                        <a style="width: 100%;max-width: 150px;"
                           href="javascript:void(0)"
                           class="easyui-linkbutton"
                           title="提交" onclick="submitForm()">提交</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dlg2" class="easyui-dialog" title="角色分配"
     style="width: 850px; height: 350px;" closed="true"
     buttons="#dlg-buttons" style="display:none" data-options="modal:true">
    <div id="cc" class="easyui-layout" fit="true">
        <div data-options="region:'center',border:false" style="height:80%;">
            <table id="tt3">
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
        </div>
        <div data-options="region:'south'" style="height:50px;overflow: hidden;">
            <div class="container_12">
                <div class="grid_3 prefix_3 cell">
                    <p style="text-align: center;">
                        <a style="width: 100%;max-width: 150px;"
                           href="javascript:void(0)"
                           class="easyui-linkbutton"
                           title="重置" onclick="confirm()">确认</a>
                    </p>
                </div>
                <div class="grid_3 suffix_3 cell">
                    <p style="text-align: center;">
                        <a style="width: 100%;max-width: 150px;"
                           href="javascript:void(0)"
                           class="easyui-linkbutton"
                           title="提交" onclick="cancel()">取消</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>