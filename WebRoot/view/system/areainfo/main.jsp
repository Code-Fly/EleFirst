<%--
  Created by IntelliJ IDEA.
  User: VM
  Date: 2/9/2017
  Time: 14:05
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
<div data-options="region:'center',border:false" align="center" style="padding-top: 20px;">
    <form id="ff" class="easyui-form" method="post" data-options="novalidate:true,fit:true">
        <div class="easyui-panel" title="企业信息"
             style="width:95%;padding-left:30px;padding-right:30px;margin-bottom: 20px;">
            <div class="container_12" style="padding-top: 10px;padding-bottom: 10px;">
                <div class="grid_2 cell title">
                    <p>
                        企业名称
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="name" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        简称
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="abbreviation" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
                <div class="grid_2 cell title">
                    <p>
                        ICP备案号
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="icp" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        成立日期
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="establishmentDate" required="true" editable="false" class="easyui-datebox"
                               style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
                <div class="grid_2 cell title">
                    <p>
                        企业性质
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="nature" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        注册资金(万元)
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="registeredCapital" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
                <div class="grid_2 cell title">
                    <p>
                        主营行业
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="bussinessIndustry" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        人数
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="numberOfEmployees" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
                <div class="grid_2 cell title">
                    <p>
                        股票代码
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="stockCode" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        面积(平方米)
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="area" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
            </div>
        </div>

        <div class="easyui-panel" title="用电信息"
             style="width:95%;padding-left:30px;padding-right:30px;margin-bottom: 20px;">
            <div class="container_12" style="padding-top: 10px;padding-bottom: 10px;">
                <div class="grid_2 cell title">
                    <p>
                        合同容量(kVA)
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="contractCapacity" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        运行容量(kVA)
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="operationCapacity" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
                <div class="grid_2 cell title">
                    <p>
                        用电户号
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="accountNumber" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        电压等级
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="voltageLevel" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
            </div>
        </div>

        <div class="easyui-panel" title="联系人"
             style="width:95%;padding-left:30px;padding-right:30px;margin-bottom: 20px;">
            <div class="container_12" style="padding-top: 10px;padding-bottom: 10px;">
                <div class="grid_2 cell title">
                    <p>
                        用电主管
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="executiveName" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        联系电话
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="executiveTel" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
                <div class="grid_2 cell title">
                    <p>
                        用电负责人
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="personInChargeName" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="grid_2 cell title">
                    <p>
                        联系电话
                    </p>
                </div>
                <div class="grid_4 cell">
                    <p>
                        <input name="personInChargeTel" required="true" class="easyui-textbox" style="width: 100%;">
                    </p>
                </div>
                <div class="clear"></div>
            </div>
        </div>

        <div class="easyui-panel" title="变压器信息" style="width:95%;margin-bottom: 20px;height: 300px;">
            <div class="easyui-layout" data-options="fit:true">
                <div data-options="region:'north',border:false" style="padding-left:30px;padding-right:30px;">
                    <div class="container_12" style="padding-top: 10px;padding-bottom: 10px;">
                        <div class="grid_2 cell title">
                            <p>
                                变压器名称
                            </p>
                        </div>
                        <div class="grid_4 cell">
                            <p>
                                <input class="easyui-combobox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="grid_2 cell title">
                            <p>
                                额定容量
                            </p>
                        </div>
                        <div class="grid_4 cell">
                            <p>
                                <input class="easyui-textbox" style="width: 100%;">
                            </p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
                <div data-options="region:'center',border:false">
                    <div id="tool-dg-transformer" style="">
                        <a href="#" id="btn-pn-tool-add" class="easyui-linkbutton"
                           data-options="plain:true,iconCls:'icon-add'">添加</a>
                        <a href="#" id="btn-pn-tool-delete" class="easyui-linkbutton"
                           data-options="plain:true,iconCls:'icon-cross'">删除</a>
                    </div>
                    <table class="easyui-datagrid"
                           data-options="singleSelect:true,fit:true,border:false,fitColumns:true,toolbar:'#tool-dg-transformer'">
                        <thead>
                        <tr>
                            <th data-options="field:'itemid',width:100" align="center">变压器名称</th>
                            <th data-options="field:'productid',width:100" align="center">额定容量</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>

    </form>
</div>
<div data-options="region:'south'" style="height:50px;overflow: hidden;">
    <div class="container_12">
        <div class="grid_4 prefix_2 cell">
            <p style="text-align: center;">
                <a id="btn-reset" style="width: 150px;" href="javascript:void(0)" class="easyui-linkbutton"
                   icon="icon-arrow_refresh" title="清空">清空</a>
            </p>
        </div>
        <div class="grid_4 suffix_2 cell">
            <p style="text-align: center;">
                <a id="btn-submit" style="width: 100%;max-width: 150px;"
                   href="javascript:void(0)"
                   class="easyui-linkbutton"
                   icon="icon-save" title="提交">提交</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
