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
            <th field="idNo" width="150" align="center">身份证号</th>
            <th field="mobile" width="150" align="center">移动电话</th>
            <th field="wechat" width="150" align="center">微信号</th>
            <th field="contractNo" width="150" align="center">合同号</th>
            <th field="billTimeStart" width="150" align="center">计费时间(起)</th>
            <th field="billTimeEnd" width="150" align="center">计费时间(止)</th>
            <th field="address" width="150" align="center">地址</th>
            <th field="area" width="150" align="center">建筑面积</th>
            <th field="checkInStatus" width="150" align="center">入住状态</th>
            <th field="unit" width="150" align="center">所属单元</th>
            <th field="chargeType" width="150" align="center">能源收费类别</th>
            <th field="openDate" width="150" align="center">开户日期</th>
            <th field="pnType" width="150" align="center">电表类型</th>
            <th field="pnBase" width="150" align="center">表计底数</th>
            <th field="billType" width="150" align="center">计价类型</th>
            <th field="payment" width="150" align="center">缴费模式</th>
            <th field="paymentDeadline" width="150" align="center">缴费截止日</th>
            <th field="balance" width="150" align="center">剩余金额</th>
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
            <a href="#" id="btn-pn-tool-pn-config" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-calculator'">表计对应</a>
            <a href="#" id="btn-pn-tool-recharge" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-money_yen'">预交收费</a>
            <a href="#" id="btn-pn-tool-switch" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-lightning'">合闸</a>
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
                                用户名
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
                                用户编号
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
                                身份证号
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
                                移动电话
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
                                微信号
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
                                合同号
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
                                计费时间(起)
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
                                计费时间(止)
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
                                地址
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
                                建筑面积
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
                                入住状态
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
                                所属单元
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
                                能源收费类别
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
                                开户日期
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
                                电表类型
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
                                表计底数
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
                                计价类型
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
                                缴费模式
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
                                缴费截止日
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
                                剩余金额
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