<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/16
  Time: 下午6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/common960.jsp" %>
    <%@ include file="/view/common/commonMxGraph.jsp" %>
    <script type="text/javascript">
        require(["js/init.js"]);
    </script>
    <style>
        /*html, body {*/
        /*margin: 0;*/
        /*height: 100%;*/
        /*}*/

        #graphContainer {
            overflow: hidden;
            /*position: relative;*/
            width: 100%;
            height: 100%;
            /*left: 50px;*/
            /*right: 50px;*/
            /*top: 50px;*/
            /*bottom: 50px;*/
            /*border: 1px solid gray;*/
            background: url('${mxBasePath}images/examples/wires-grid.gif');
            background-position: -1px 0px;
            cursor: crosshair;
        }

        /*.current p {*/
        /*padding: 0px;*/
        /*margin: 5px;*/
        /*text-align: left;*/
        /*}*/

        #outlineContainer {
            z-index: 1;
            position: relative;
            overflow: hidden;
            top: 0px;
            right: 0px;
            width: 160px;
            height: 120px;
            background: transparent;
            border: solid;
            border-color: lightgray;
        }
    </style>
    <style>
        .title p {
            text-align: left;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:40px;overflow: hidden;padding-top: 5px;">
    <div class="container_12">
        <div class="grid_7">
            <a id="btn-refresh" href="javascript:void(0)" plain="true" class="easyui-linkbutton"
               icon="icon-arrow_refresh"
               title="重置">重置</a>
        </div>
        <div class="grid_3" style="padding-top: 2px;">
            <input id="combo-template" class="easyui-combobox" style="width: 100%;"
                   label="接线图" labelPosition="before">
        </div>
        <div class="grid_2">
            <div style="float: right">
                <a id="btn-save-template" title="保存" href="javascript:void(0)" plain="true"
                   class="easyui-linkbutton"
                   icon="icon-disk"></a>
                <a id="btn-saveAs-template" title="另存为" href="javascript:void(0)" plain="true"
                   class="easyui-linkbutton"
                   icon="icon-disk_multiple"></a>
                <a id="btn-del-template" title="删除" href="javascript:void(0)" plain="true"
                   class="easyui-linkbutton"
                   icon="icon-cross"></a>
            </div>
        </div>
    </div>
</div>
<div data-options="region:'center',border:false">
    <!-- Creates a container for the sidebar -->
    <div id="toolbarContainer">
    </div>
    <!-- Creates a container for the graph -->
    <div id="graphContainer">
    </div>
    <input id="hid-selected-node-info" type="hidden" value="{}">
    <input id="hid-config" type="hidden" value="[]">
</div>
<div id="dlg-bound-pn" class="easyui-dialog" title="数据绑定"
     data-options="iconCls:'icon-save',closed: true, cache: false, modal: true,"
     style="width:400px;height:200px;">

    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center'">
            <div class="container_12" style="padding-top: 10px">
                <div class="grid_4 cell title">
                    <p>
                        监测点
                    </p>
                </div>
                <div class="grid_8 cell">
                    <p>
                        <input id="combo-bound-pn-id" class="easyui-combobox" style="width: 100%;">
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
                        <a id="btn-dlg-bound-pn-submit" style="width: 100%;max-width: 150px;"
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
