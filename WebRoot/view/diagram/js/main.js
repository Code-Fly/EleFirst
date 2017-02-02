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
            var config = $.parseJSON($("#hid-config").val());
            var nodeConfig = getConfig(node, config);

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

            var config = $.parseJSON($("#hid-config").val());

            var newConfig = addConfig(node, config);
            $("#hid-config").val(JSON.stringify(newConfig));

            $("#dlg-bound-pn").dialog("close");
        }
    });

    function initConfig(config) {
        var newConfig = clone(config);
        if (!newConfig.hasOwnProperty(graphConstants.USER_OBJECT_CURRENT)) {
            newConfig[graphConstants.USER_OBJECT_CURRENT] = [];
        }

        if (!newConfig.hasOwnProperty(graphConstants.USER_OBJECT_SWITCH_STATE)) {
            newConfig[graphConstants.USER_OBJECT_SWITCH_STATE] = [];
        }

        return newConfig;
    }

    function addConfig(node, config) {
        var newConfig = initConfig(config);

        var newList = [];
        var oldList = newConfig[node.cellType];
        for (var i = 0; i < oldList.length; i++) {
            if (oldList[i].cellId != node.cellId) {
                newList.push(oldList[i]);
            }
        }
        newList.push(node);
        newConfig[node.cellType] = newList;


        // if (node.cellType == graphConstants.USER_OBJECT_CURRENT) {
        //     var newList = [];
        //     var oldList = newConfig[graphConstants.USER_OBJECT_CURRENT];
        //     for (var i = 0; i < oldList.length; i++) {
        //         if (oldList[i].cellId != node.cellId) {
        //             newList.push(oldList[i]);
        //         }
        //     }
        //     newList.push(node);
        //     newConfig[graphConstants.USER_OBJECT_CURRENT] = newList;
        // }
        //
        // if (node.cellType == graphConstants.USER_OBJECT_SWITCH_STATE) {
        //     var newList = [];
        //     var oldList = newConfig[graphConstants.USER_OBJECT_SWITCH_STATE];
        //     for (var i = 0; i < oldList.length; i++) {
        //         if (oldList[i].cellId != node.cellId) {
        //             newList.push(oldList[i]);
        //         }
        //     }
        //     newList.push(node);
        //     newConfig[graphConstants.USER_OBJECT_SWITCH_STATE] = newList;
        // }

        return newConfig;
    }

    function getConfig(node, config) {
        var config = initConfig(config);

        var list = config[node.cellType];
        for (var i = 0; i < list.length; i++) {
            if (list[i].cellId == node.cellId) {
                return list[i];
            }
        }

        // if (node.cellType == graphConstants.USER_OBJECT_CURRENT) {
        //     var list = config[graphConstants.USER_OBJECT_CURRENT];
        //     for (var i = 0; i < list.length; i++) {
        //         if (list[i].cellId == node.cellId) {
        //             return list[i];
        //         }
        //     }
        // }
        //
        // if (node.cellType == graphConstants.USER_OBJECT_SWITCH_STATE) {
        //     var list = config[graphConstants.USER_OBJECT_SWITCH_STATE];
        //     for (var i = 0; i < list.length; i++) {
        //         if (list[i].cellId == node.cellId) {
        //             return list[i];
        //         }
        //     }
        // }

        return null;
    }
});