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
           fitColumns="true" rownumbers="true"
           border="false" fit="true">
        <thead>
        <tr>
            <th field="unit" width="150" align="center">控制单元</th>
            <th field="name" width="150" align="center">表计名称</th>
            <th field="address" width="150" align="center">表计地址</th>
            <th field="type" width="150" align="center">表计类型</th>
            <th field="comment" width="150" align="center">备注</th>
        </tr>
        </thead>
    </table>
    <div id="dgListToolbar" style="padding:5px;height:auto">
        <div>
            <a href="#" id="btn-pn-tool-add" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-cog'">设置</a>
        </div>
    </div>
    <div id="dlg-policy" class="easyui-dialog" title="控制策略"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:470px;height:230px;">

        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <form id="form-add-tree-node" class="easyui-form" method="post" data-options="novalidate:true">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_2 cell title">
                            <p>
                                跳闸断电
                            </p>
                        </div>
                        <div class="grid_5 cell">
                            <p>
                                <input class="easyui-datetimebox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="grid_5 cell">
                            <p>
                                <input class="easyui-datetimebox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_2 cell title">
                            <p>
                                合闸供电
                            </p>
                        </div>
                        <div class="grid_5 cell">
                            <p>
                                <input class="easyui-datetimebox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="grid_5 cell">
                            <p>
                                <input class="easyui-datetimebox" style="width: 100%;">
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
                            <a id="btn-dlg-policy-submit" style="width: 100%;max-width: 150px;"
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