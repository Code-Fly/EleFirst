/**
 * Created by barrie on 17/1/31.
 */
$(document).ready(function () {
    var _areaId = "1";
    var _treeId = "1";
    var info = {
        areaId: _areaId,
        concentrators: [],
        type: "physical"
    }
    $("#dTree").tree({
        // url: "data/test.json",
        // method: "get",
        url: _ctx + "system/tree/info/node.do",
        queryParams: {
            treeId: _treeId
        },
        animate: true,
        lines: true,
        onDblClick: function (node) {
            $(this).tree("beginEdit", node.target);
        },
        // onClick: function (node) {
        //     $(this).tree("beginEdit", node.target);
        // },
        onAfterEdit: function (node) {
            $.ajax({
                url: _ctx + "system/tree/info/update.do",
                type: "POST",
                data: {
                    id: node.id,
                    name: node.text
                },
                cache: false,
                success: function (r) {
                    if (r.hasOwnProperty("errcode")) {
                        if ("0" == r.errcode) {
                            // $.messager.alert("操作提示", JSON.stringify(r.data));
                        } else {
                            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                        }
                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    MaskUtil.mask();
                },
                error: function (request) {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
                },
                complete: function (XMLHttpRequest, textStatus) {
                    MaskUtil.unmask();
                }
            });
        },
        onSelect: function (node) {
            traverse(node);
            // parent.saveTreeInfo(info);
        },
        onBeforeSelect: function (node) {
            info.concentrators = [];
        },
        onLoadSuccess: function (node, data) {
            var root = $(this).tree("getRoot");
            if (root != undefined && root != null) {
                $(this).tree("select", root.target);
            }
        }

    });

    // var root = $("#dTree").tree("getRoot");
    // $("#dTree").tree("select", root.target);


    function traverse(tree) {
        var nodes = tree.children;
        if ("concentrator" == tree.attributes.type) {
            info.concentrators.push({
                concentratorId: tree.attributes.concentratorId,
                pns: []
            });
        }
        if (nodes != null) {
            for (var i = 0; i < nodes.length; i++) {
                if ("concentrator" == nodes[i].attributes.type) {
                    info.concentrators.push({
                        concentratorId: nodes[i].attributes.concentratorId,
                        pns: []
                    });
                }
                if (nodes[i].children) { //递归调用自己，以实现遍历
                    traverse(nodes[i]);
                }
            }
        }
    }

    $("#dlg-add-tree-node").dialog({
        onBeforeOpen: function () {
            if (flag_edit) {
                var node = $("#dTree").tree("getSelected");
                var name = node.text;
                var iconcls = node.iconCls;
                var type = node.attributes.type;
                var concentratorId = node.attributes.concentratorId;

                $("#text-tree-node-name").textbox("setValue", name);
                $("#combo-tree-node-type").combobox("select", type);
                $("#combo-tree-node-iconcls").combobox("select", iconcls);
                $("#combo-tree-node-concentratorId").combobox("select", concentratorId);

            } else {
                $("#text-tree-node-name").textbox("clear");
                $("#combo-tree-node-type").combobox("clear");
                $("#combo-tree-node-iconcls").combobox("clear");
                $("#combo-tree-node-concentratorId").combobox("clear");
                $("#combo-tree-node-concentratorId").combobox("disable");
            }
        },
        onOpen: function () {
        },
        onClose: function () {
            flag_edit = false;
        }
    });

    $("#combo-tree-node-concentratorId").combobox({
        required: true,
        textField: "name",
        valueField: "concentratorId",
        url: _ctx + "system/concentrator/info/list.do",
        queryParams: {
            areaId: _areaId
        },
        editable: false,
    });

    $("#text-tree-node-name").textbox({
        required: true
    });

    $("#combo-tree-node-type").combobox({
        required: true,
        textField: "name",
        valueField: "value",
        url: "data/comboTreeNodeType.json",
        method: "GET",
        editable: false,
        onSelect: function (record) {
            $("#combo-tree-node-iconcls").combobox("clear");
            $("#combo-tree-node-iconcls").combobox("loadData", []);
            if (record.value == "category") {
                $("#combo-tree-node-concentratorId").combobox("clear");
                $("#combo-tree-node-concentratorId").combobox("disable");
                $("#combo-tree-node-iconcls").combobox("reload", "data/comboTreeNodeCategoryIcons.json");
            } else if (record.value == "concentrator") {
                $("#combo-tree-node-concentratorId").combobox("clear");
                $("#combo-tree-node-concentratorId").combobox("enable");
                $("#combo-tree-node-iconcls").combobox("reload", "data/comboTreeNodeConcentratorIcons.json");
            }
        }

    });

    $("#combo-tree-node-iconcls").combobox({
        required: true,
        method: "GET",
        textField: "name",
        valueField: "value",
        editable: false,
    });

    $("#btn-tree-dlg-add-tree-node-submit").linkbutton({
        onClick: function () {
            if (!$("#text-tree-node-name").textbox("isValid")) {
                $.messager.alert("操作提示", "请输入正确名称！", "info");
                return;
            }

            if (!$("#combo-tree-node-iconcls").combobox("isValid")) {
                $.messager.alert("操作提示", "请选择正确图标！", "info");
                return;
            }

            if (!$("#combo-tree-node-type").combobox("isValid")) {
                $.messager.alert("操作提示", "请选择正确类型！", "info");
                return;
            }

            if (!$("#combo-tree-node-concentratorId").combobox("isValid")) {
                $.messager.alert("操作提示", "请选择正确馈线柜！", "info");
                return;
            }

            var node = $("#dTree").tree("getSelected");
            var children = node.children == undefined ? [] : node.children;
            var name = $("#text-tree-node-name").textbox("getValue");
            var iconcls = $("#combo-tree-node-iconcls").combobox("getValue");
            var type = $("#combo-tree-node-type").combobox("getValue");
            var concentratorId = $("#combo-tree-node-concentratorId").combobox("getValue");
            var attributes = {
                type: type
            };

            if (flag_edit) {
                $.ajax({
                    url: _ctx + "system/tree/info/update.do",
                    type: "POST",
                    data: {
                        id: node.id,
                        iconcls: iconcls,
                        attributes: JSON.stringify(attributes),
                        name: name
                    },
                    cache: false,
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                $("#dTree").tree("reload");
                                $("#dlg-add-tree-node").dialog("close");
                                $.messager.alert("操作提示", "添加成功。", "info");
                            } else {
                                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                            }
                        } else {
                            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                        }
                    },
                    beforeSend: function (XMLHttpRequest) {
                        MaskUtil.mask();
                    },
                    error: function (request) {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        MaskUtil.unmask();
                    }
                });
            } else {
                if (node.attributes.type == "concentrator") {
                    $.messager.alert("操作提示", "无法在此下添加节点！", "info");
                    return;
                }

                if (attributes.type == "concentrator") {
                    for (var i = 0; i < children.length; i++) {
                        if (children[i].attributes.type != "concentrator") {
                            $.messager.alert("操作提示", "无法在此下添加馈线柜！", "info");
                            return;
                        }
                    }
                }
                else if (attributes.type == "category") {
                    for (var i = 0; i < children.length; i++) {
                        if (children[i].attributes.type == "concentrator") {
                            $.messager.alert("操作提示", "无法在此下添加子分类！", "info");
                            return;
                        }
                    }
                }

                $.ajax({
                    url: _ctx + "system/tree/info/add.do",
                    type: "POST",
                    data: {
                        pid: node.id,
                        treeId: _treeId,
                        iconcls: iconcls,
                        attributes: JSON.stringify(attributes),
                        name: name
                    },
                    cache: false,
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                $("#dTree").tree("reload");
                                $("#dlg-add-tree-node").dialog("close");
                                $.messager.alert("操作提示", "添加成功。", "info");
                            } else {
                                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                            }
                        } else {
                            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                        }
                    },
                    beforeSend: function (XMLHttpRequest) {
                        MaskUtil.mask();
                    },
                    error: function (request) {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        MaskUtil.unmask();
                    }
                });
            }
        }
    });

    $("#btn-tree-tool-add").click(function () {
        var roots = $("#dTree").tree("getRoots");

        if (roots.length == 0) {
            $.messager.confirm("操作提示", "是否添加根节点？", function (r) {
                if (r) {
                    $.ajax({
                        url: _ctx + "system/tree/info/add.do",
                        type: "POST",
                        data: {
                            pid: 0,
                            treeId: info.areaId,
                            iconcls: "icon-house",
                            attributes: JSON.stringify({"type": "category"}),
                            name: "配用电监测"
                        },
                        cache: false,
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $("#dTree").tree("reload");
                                    $("#dlg-add-tree-node").dialog("close");
                                    $.messager.alert("操作提示", "添加成功。", "info");
                                } else {
                                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                }
                            } else {
                                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            MaskUtil.mask();
                        },
                        error: function (request) {
                            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            MaskUtil.unmask();
                        }
                    });
                }
            });
        } else {
            $("#dlg-add-tree-node").dialog("open");
        }
    });


    var flag_edit = false;
    $("#btn-tree-tool-edit").click(function () {
        var node = $("#dTree").tree("getSelected");

        if (node == undefined || node == null) {
            $.messager.alert("操作提示", "请选择节点！", "info");
        } else {
            flag_edit = true;
            $("#dlg-add-tree-node").dialog("open");
        }
    });

    $("#btn-tree-tool-delete").click(function () {
        var node = $("#dTree").tree("getSelected");
        var children = node.children == undefined ? [] : node.children;

        if (children.length > 0) {
            $.messager.alert("操作提示", "请先删除子节点！", "info");
            return;
        }

        $.messager.confirm("操作提示", "确定要删除吗？", function (r) {
            if (r) {
                $.ajax({
                    url: _ctx + "system/tree/info/delete.do",
                    type: "POST",
                    data: {
                        id: node.id
                    },
                    cache: false,
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                $("#dTree").tree("loadData", []);
                                $("#dTree").tree("reload");
                                $.messager.alert("操作提示", "删除成功！", "info");
                            } else {
                                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                            }
                        } else {
                            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                        }
                    },
                    beforeSend: function (XMLHttpRequest) {
                        MaskUtil.mask();
                    },
                    error: function (request) {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        MaskUtil.unmask();
                    }
                });
            }
        });

    });

    $("#btn-tree-tool-refresh").click(function () {
        $("#dTree").tree("reload");
    });
});