/**
 * Created by barrie on 17/1/16.
 */
$(document).ready(function () {
    var _areaId = "1";
    var _areaName = "配用电监测";

    var flag_bound_pn_edit = false;

    $("#dlg-bound-pn").dialog({
        onBeforeOpen: function () {
            var node = $.parseJSON($("#hid-selected-node-info").val());
            var nodeConfig = graphConfig.getConfig(node, $("#hid-config"));

            $("#combo-bound-pn-id").combobox("reload");
            if (null != nodeConfig && nodeConfig.hasOwnProperty("pnId")) {
                $("#combo-bound-pn-id").combobox("select", nodeConfig.pnId);
            } else {
                $("#combo-bound-pn-id").combobox("clear");
            }

        },
        onOpen: function () {

        },
        onClose: function () {
            flag_bound_pn_edit = false;
        }
    });

    $("#combo-bound-pn-id").combobox({
        required: true,
        textField: "name",
        valueField: "id",
        url: _ctx + "system/pn/info/list.do",
        queryParams: {
            areaId: _areaId
        },
        editable: false,
    });

    $("#btn-refresh").linkbutton({
        onClick: function () {
            window.location.href = window.location.href;
        }
    });

    $("#btn-dlg-bound-pn-submit").linkbutton({
        onClick: function () {
            if (!$("#combo-bound-pn-id").combobox("isValid")) {
                $.messager.alert("操作提示", "请选择正确监测点！", "info");
                return;
            }

            var node = $.parseJSON($("#hid-selected-node-info").val());

            var pnInfo = $.parseJSON($.ajax({
                url: _ctx + "system/pn/info/detailById.do",
                type: "POST",
                data: {
                    id: $("#combo-bound-pn-id").combobox("getValue")
                },
                async: false
            }).responseText);

            if ("0" != pnInfo.errcode) {
                $.messager.alert("操作提示", "请求失败！", "info");
                return;
            }

            if (pnInfo.length == 0) {
                $.messager.alert("操作提示", "请求失败！", "info");
                return;
            }

            pnInfo = pnInfo.data[0];

            node.areaId = _areaId;
            node.concentratorId = pnInfo.concentratorId;
            node.pn = pnInfo.pn;
            node.pnId = pnInfo.id;


            graphConfig.addConfig(node, $("#hid-config"));

            $("#dlg-bound-pn").dialog("close");
        }
    });


});