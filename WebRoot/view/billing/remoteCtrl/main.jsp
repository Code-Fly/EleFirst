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
<div data-options="region:'center',border:false" style="overflow: hidden">
    <div class="easyui-tabs" data-options="fit:true" border="false">
        <div title="控制设置">
            <div id="cc1" class="easyui-layout" data-options="fit:true" style="height:100%;">
                <div data-options="region:'center',border:false" style="overflow: hidden">
                    <table id="dgList_1" class="easyui-datagrid" toolbar="#dgListToolbar_1"
                           singleSelect="false" rownumbers="true"
                           border="false" fit="true">
                        <thead frozen="true">
                        <tr>
                            <th data-options="field:'ck',checkbox:true"></th>
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
                            <th field="payment" width="150" align="center">缴费模式</th>
                            <th field="balance" width="150" align="center">剩余金额</th>
                            <th field="comment" width="150" align="center">备注</th>
                        </tr>
                        </thead>
                    </table>
                    <div id="dgListToolbar_1" style="padding:5px;height:auto">
                        <div>
                            <a href="#" id="btn-pn-tool-setting" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-cog'">设置</a>
                            <a href="#" id="btn-pn-tool-search_1" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-search'">查询</a>
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
        </div>
        <div title="控制查询">
            <div id="cc2" class="easyui-layout" data-options="fit:true" style="height:100%;">
                <div data-options="region:'center',border:false" style="overflow: hidden">
                    <table id="dgList_2" class="easyui-datagrid" toolbar="#dgListToolbar_2"
                           singleSelect="false" rownumbers="true"
                           border="false" fit="true">
                        <thead frozen="true">
                        <tr>
                            <th field="userName" width="100" align="center">用户名</th>
                            <th field="userCode" width="100" align="center">用户编号</th>
                        </tr>
                        </thead>
                        <thead>
                        <tr>
                            <th field="bjlx" width="150" align="center">表计类型</th>
                            <th field="bjdz" width="150" align="center">表计地址</th>
                            <th field="ssdy" width="150" align="center">所属单元</th>
                            <th field="zhye" width="150" align="center">账户余额</th>
                            <th field="qfye" width="150" align="center">欠费金额</th>
                            <th field="kzzt" width="150" align="center">控制状态</th>
                            <th field="comment" width="150" align="center">备注</th>
                        </tr>
                        </thead>
                    </table>
                    <div id="dgListToolbar_2" style="padding:5px;height:auto">
                        <div>
                            <a href="#" id="btn-pn-tool-search_2" class="easyui-linkbutton"
                               data-options="plain:true,iconCls:'icon-search'">查询</a>
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
        </div>
    </div>
</div>
<div id="dlg-remote-setting" class="easyui-dialog" title="远程控制设置"
     data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
     style="width:470px;height:300px;">

    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center'" align="center" style="padding: 5px;">
            <div class="easyui-panel" title="预警设置" style="width:95%;margin-bottom: 5px;">
                <table style="padding: 10px;width: 100%;font-size:12px;">
                    <tbody>
                    <tr>
                        <td rowspan="3">
                            <input type="radio" name="radio1">
                        </td>
                        <td>
                            <input type="checkbox" name="checkbox1">
                        </td>
                        <td>
                            <span>当账户余额小于</span>
                            <input class="easyui-textbox" style="width: 50px;">
                            <span>通过微信公众号向用户推送信息，提示用户预缴充值</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" name="checkbox1">
                        </td>
                        <td>
                            <span>当账户余额小于</span>
                            <input class="easyui-textbox" style="width: 50px;">
                            <span>跳闸/关阀</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" name="checkbox1">
                        </td>
                        <td>
                            <span>在缴费截止日逾期</span>
                            <input class="easyui-textbox" style="width: 50px;">
                            <span>天通过微信公众号向用户推送信息，提示用户缴费</span>
                        </td>
                    </tr>

                    <tr>
                        <td rowspan="3">
                            <input type="radio" name="radio1">
                        </td>
                        <td>
                            <input type="checkbox" name="checkbox2">
                        </td>
                        <td>
                            <span>欠费达到</span>
                            <input class="easyui-textbox" style="width: 50px;">
                            <span>通过微信公众号向用户推送信息，提示用户缴费</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" name="checkbox2">
                        </td>
                        <td>
                            <span>欠费达到</span>
                            <input class="easyui-textbox" style="width: 50px;">
                            <span>跳闸/关阀</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" name="checkbox2">
                        </td>
                        <td>
                            <span>透支电量达到</span>
                            <input class="easyui-textbox" style="width: 50px;">
                            <span>跳闸/关阀</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="easyui-panel" title="控制设置" style="width:95%;margin-bottom: 5px;">
                <table style="padding: 10px;width: 100%;font-size:12px;">
                    <tbody>
                    <tr>
                        <td>
                            <input type="checkbox" name="checkbox3">
                        </td>
                        <td>
                            <span>不允许跳闸/关阀</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" name="checkbox3">
                        </td>
                        <td>
                            <span>允许合闸/开阀</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div data-options="region:'south'" style="height:50px;overflow: hidden;">
            <div class="container_12">
                <div class="grid_4 prefix_4 cell">
                    <p style="text-align: center;">
                        <a id="btn-dlg-remote-setting-submit" style="width: 100%;max-width: 150px;"
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