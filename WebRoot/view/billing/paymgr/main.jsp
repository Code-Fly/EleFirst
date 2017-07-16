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
    <div id="cc1" class="easyui-layout" data-options="fit:true" style="height:100%;">
        <div data-options="region:'center',border:false" style="overflow: hidden">
            <table id="dgList" class="easyui-datagrid" toolbar="#dgListToolbar"
                   singleSelect="true" rownumbers="true"
                   border="false" fit="true">
                <thead frozen="true">
                <tr>
                    <th field="userName" width="100" align="center">用户名</th>
                    <th field="userCode" width="100" align="center">用户编号</th>
                </tr>
                </thead>
                <thead>
                <tr>
                    <th field="ynlx" align="center" width="150">用能类型</th>
                    <th field="jfType" align="center" width="150">缴费模式</th>
                    <th field="jjlx" align="center" width="150">计价类型</th>
                    <th field="gtfs" align="center" width="150">公摊方式</th>
                    <th field="jfzq" align="center" width="150">计费周期</th>
                    <th field="jnqj" align="center" width="150">计能期间</th>
                    <th field="ynl" align="center" width="150">用能量</th>
                    <th field="nhfy" align="center" width="150">能耗费用</th>
                    <th field="gtfy" align="center" width="150">公摊费用</th>
                    <th field="yhje" align="center" width="150">优惠金额</th>
                    <th field="wyj" align="center" width="150">违约金</th>
                    <th field="ysfy" align="center" width="150">应收费用</th>
                    <th field="zdkfje" align="center" width="150">自动扣费金额</th>
                    <th field="qfje" align="center" width="150">欠费金额</th>
                    <th field="zhye" align="center" width="150">账户余额</th>
                    <th field="comment" align="center" width="150">备注</th>

                </tr>
                </thead>
            </table>
            <div id="dgListToolbar" style="padding:5px;height:auto">
                <div>
                    <a href="#" id="btn-pn-tool-chongzhi" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'icon-money_add'">充值收费</a>
                    <a href="#" id="btn-pn-tool-add" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'icon-money_yen'">现金收费</a>
                    <a href="#" id="btn-pn-tool-edit" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'icon-mail'">打印票据</a>
                    <a href="#" id="btn-pn-tool-xhjs" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'icon-user_delete'">销户结算</a>
                    <a href="#" id="btn-pn-tool-search" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'icon-search'">查询</a>
                    <a href="#" id="btn-pn-tool-export" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'icon-page_excel'">导出</a>
                </div>
            </div>
        </div>
        <div data-options="region:'south',title:'查询',border:false,collapsed:true,collapsible:true"
             style="height:100%;">
            <div class="container_12" style="padding-top: 10px">
                <div class="grid_2 cell title">
                    <p>
                        用户编号：
                    </p>
                </div>
                <div class="grid_2 cell">
                    <p>
                        <input class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        用户名：
                    </p>
                </div>
                <div class="grid_2 cell">
                    <p>
                        <input class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        身份证号：
                    </p>
                </div>
                <div class="grid_2 cell">
                    <p>
                        <input class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
                <div class="grid_2 cell title">
                    <p>
                    </p>
                </div>
                <div class="grid_2 cell">
                    <p>
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                    </p>
                </div>
                <div class="grid_2 cell">
                    <p>
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                    </p>
                </div>
                <div class="grid_2 cell">
                    <p>
                        <a style="width: 100%;max-width: 150px;"
                           href="javascript:void(0)"
                           class="easyui-linkbutton"
                           icon="icon-search" title="查询">查询</a>
                    </p>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>

    <div id="dlg-add-user" class="easyui-dialog" title="现金收费"
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
    <div id="dlg-chongzhi" class="easyui-dialog" title="充值收费"
         data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
         style="width:470px;height:300px;">
        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <form id="form-chongzhi" class="easyui-form" method="post" data-options="novalidate:true">
                    <div class="container_12" style="padding-top: 10px">
                        <div class="grid_3 cell title">
                            <p>
                                账户余额
                            </p>
                        </div>
                        <div class="grid_9 cell">
                            <p>
                                <input class="easyui-textbox" value="110" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                        <div class="grid_3 cell title">
                            <p>
                                充值金额
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
                            <a id="btn-chongzhi-submit" style="width: 100%;max-width: 150px;"
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