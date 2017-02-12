/**
 * Created by barrie on 17/1/31.
 */
$(document).ready(function () {
    var info = {
        areaId: _areaId,
        concentrators: [],
        type: "physical"
    }

    var _concentratorInfo = [];

    var flag_tree_node_edit = false;

    var flag_dg_concentrator_edit = false;

    var flag_dg_pn_edit = false;


    getConcentratorInfo();

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
            $("#dg-pn-detail").datagrid("load", {
                node: JSON.stringify(info)
            });
            $("#dg-concentrator-detail").datagrid("load", {
                node: JSON.stringify(info)
            });
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

    // 监测点处理开始

    $("#dg-pn-detail").datagrid({
        url: _ctx + "system/pn/info/list.do",
        queryParams: {
            node: JSON.stringify({
                areaId: _areaId,
                concentrators: [],
                type: "physical"
            })
        },
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        columns: [[
            {
                field: "name",
                title: "监测点名称",
                align: "center",
                width: 120
            },
            {
                field: "pn",
                title: "监测点编号",
                align: "center",
                width: 120
            }, {
                field: "concentratorName",
                title: "馈线柜名称",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    for (var i = 0; i < _concentratorInfo.length; i++) {
                        if (row.concentratorId == _concentratorInfo[i].concentratorId) {
                            return _concentratorInfo[i].name;
                        }
                    }
                    return "-";
                }
            }, {
                field: "concentratorId",
                title: "馈线柜编号",
                align: "center",
                width: 120
            }, {
                field: "ct",
                title: "CT",
                align: "center",
                width: 120
            }, {
                field: "pt",
                title: "PT",
                align: "center",
                width: 120
            }, {
                field: "voltageStandard",
                title: "电压标准",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "voltageUpperLimit",
                title: "电压上限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "voltageLowerLimit",
                title: "电压下限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "currentStandard",
                title: "电流标准",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "currentUpperLimit",
                title: "电流上限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "currentLowerLimit",
                title: "电流下限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "loadStandard",
                title: "负荷标准",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "loadUpperLimit",
                title: "负荷上限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "loadLowerLimit",
                title: "负荷下限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "electricityStandard",
                title: "电量标准",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "electricityUpperLimit",
                title: "电量上限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "electricityLowerLimit",
                title: "电量下限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "powerFactorStandard",
                title: "功率因数标准",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "powerFactorUpperLimit",
                title: "功率因数上限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }, {
                field: "powerFactorLowerLimit",
                title: "功率因数下限(%)",
                align: "center",
                width: 120,
                formatter: function (value, row, index) {
                    return value == null ? "-" : value;
                }
            }
        ]],
    });

    $("#dlg-add-pn-node").dialog({
        onBeforeOpen: function () {
            $("#combo-dg-pn-concentratorId").combobox("reload", _ctx + "system/concentrator/info/list.do?" + $.param({
                    areaId: _areaId
                })
            );

            $("#form-add-pn-node").form("clear");

            if (flag_dg_pn_edit) {
                var rows = $("#dg-pn-detail").datagrid("getSelections");

                var item = rows[0];

                $("#form-add-pn-node").form("load", item);

                // $("#text-dg-pn-name").textbox("setValue", item.name);
                // $("#text-dg-pn-id").textbox("setValue", item.pn);
                // $("#text-dg-pn-ct").textbox("setValue", item.ct);
                // $("#text-dg-pn-pt").textbox("setValue", item.pt);
                // $("#text-dg-pn-powerFactorStandard").textbox("setValue", item.powerFactorStandard);
                // $("#combo-dg-pn-concentratorId").combobox("select", item.concentratorId);

            }
        },
        onOpen: function () {

        },
        onClose: function () {
            flag_dg_pn_edit = false;
        }
    });

    $("#btn-pn-tool-add").linkbutton({
        onClick: function () {
            $("#dlg-add-pn-node").dialog("open");
        }
    });

    $("#btn-pn-tool-load-current").linkbutton({
        onClick: function () {
            $("#dg-pn-detail").datagrid("load", {
                node: JSON.stringify(info)
            });
        }
    });

    $("#btn-pn-tool-load-all").linkbutton({
        onClick: function () {
            $("#dg-pn-detail").datagrid("load", {});
        }
    });

    $("#btn-pn-tool-edit").linkbutton({
        onClick: function () {
            var rows = $("#dg-pn-detail").datagrid("getSelections");

            if (rows.length == 0) {
                $.messager.alert("操作提示", "请选择监测点！", "info");
            } else {
                flag_dg_pn_edit = true;
                $("#dlg-add-pn-node").dialog("open");
            }
        }
    });

    $("#btn-pn-tool-delete").linkbutton({
        onClick: function () {
            var rows = $("#dg-pn-detail").datagrid("getSelections");

            if (rows.length == 0) {
                $.messager.alert("操作提示", "请选择监测点！", "info");
                return;
            }

            $.messager.confirm("操作提示", "确定要删除吗？", function (r) {
                if (r) {
                    $.ajax({
                        url: _ctx + "system/pn/info/delete.do",
                        type: "POST",
                        data: {
                            id: rows[0].id
                        },
                        cache: false,
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $("#dg-pn-detail").datagrid("reload");
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

        }
    });

    $("#btn-dg-dlg-add-pn-submit").linkbutton({
        onClick: function () {
            if ($("#form-add-pn-node").form("enableValidation").form("validate")) {
                if (flag_dg_pn_edit) {
                    var rows = $("#dg-pn-detail").datagrid("getSelections");

                    var param = formToJson($("#form-add-pn-node"));
                    param.id = rows[0].id;
                    param.areaId = _areaId;

                    $.ajax({
                        url: _ctx + "system/pn/info/update.do",
                        type: "POST",
                        data: JSON.stringify(param),
                        contentType: "text/plain;charset=UTF-8",
                        cache: false,
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $("#dg-pn-detail").datagrid("reload");
                                    $("#dlg-add-pn-node").dialog("close");
                                    $.messager.alert("操作提示", "修改成功。", "info");
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
                    var param = formToJson($("#form-add-pn-node"));
                    param.areaId = _areaId;

                    var iList = $.parseJSON($.ajax({
                        url: _ctx + "system/pn/info/list.do",
                        type: "POST",
                        data: {
                            areaId: _areaId,
                            concentratorId: param.concentratorId
                        },
                        async: false
                    }).responseText);

                    if ("0" != iList.errcode) {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(iList.errcode), "info");
                        return;
                    }

                    for (var i = 0; i < iList.data.length; i++) {
                        if (iList.data[i].concentratorId == param.concentratorId && iList.data[i].pn == param.pn) {
                            $.messager.alert("操作提示", "编号已存在！", "info");
                            return;
                        }
                    }

                    $.ajax({
                        url: _ctx + "system/pn/info/add.do",
                        type: "POST",
                        data: JSON.stringify(param),
                        contentType: "text/plain;charset=UTF-8",
                        cache: false,
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $("#dg-pn-detail").datagrid("reload");
                                    $("#dlg-add-pn-node").dialog("close");
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
        }
    });

    // !监测点处理结束

    // 馈线柜处理开始
    $("#dg-concentrator-detail").datagrid({
        url: _ctx + "system/concentrator/info/list.do",
        queryParams: {
            node: JSON.stringify({
                areaId: _areaId,
                concentrators: [],
                type: "physical"
            })
        },
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        columns: [[
            {
                field: "name",
                title: "馈线柜名称",
                align: "center",
                width: 120
            }, {
                field: "concentratorId",
                title: "馈线柜编号",
                align: "center",
                width: 120
            }
        ]],
    });

    $("#dlg-add-concentrator-node").dialog({
        onBeforeOpen: function () {
            if (flag_dg_concentrator_edit) {
                var rows = $("#dg-concentrator-detail").datagrid("getSelections");

                var item = rows[0];

                $("#text-dg-concentrator-name").textbox("setValue", item.name);
                $("#text-dg-concentrator-id").textbox("setValue", item.concentratorId);
            } else {
                $("#text-dg-concentrator-name").textbox("clear");
                $("#text-dg-concentrator-id").textbox("clear");
            }
        },
        onOpen: function () {
        },
        onClose: function () {
            flag_dg_concentrator_edit = false;
        }
    });

    $("#text-dg-concentrator-name").textbox({
        required: true
    });

    $("#text-dg-concentrator-id").textbox({
        required: true
    });

    $("#btn-concentrator-tool-load-current").linkbutton({
        onClick: function () {
            $("#dg-concentrator-detail").datagrid("load", {
                node: JSON.stringify(info)
            });
        }
    });

    $("#btn-concentrator-tool-load-all").linkbutton({
        onClick: function () {
            $("#dg-concentrator-detail").datagrid("load", {});
        }
    });

    $("#btn-concentrator-tool-add").linkbutton({
        onClick: function () {
            $("#dlg-add-concentrator-node").dialog("open");
        }
    });

    $("#btn-concentrator-tool-edit").linkbutton({
        onClick: function () {
            var rows = $("#dg-concentrator-detail").datagrid("getSelections");

            if (rows.length == 0) {
                $.messager.alert("操作提示", "请选择监测点！", "info");
            } else {
                flag_dg_concentrator_edit = true;
                $("#dlg-add-concentrator-node").dialog("open");
            }
        }
    });

    $("#btn-concentrator-tool-delete").linkbutton({
        onClick: function () {
            var rows = $("#dg-concentrator-detail").datagrid("getSelections");

            if (rows.length == 0) {
                $.messager.alert("操作提示", "请选择监测点！", "info");
                return;
            }

            $.messager.confirm("操作提示", "确定要删除吗？", function (r) {
                if (r) {
                    $.ajax({
                        url: _ctx + "system/concentrator/info/delete.do",
                        type: "POST",
                        data: {
                            id: rows[0].id
                        },
                        cache: false,
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    getConcentratorInfo();
                                    $("#dg-concentrator-detail").datagrid("reload");
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

        }
    });

    $("#btn-dg-dlg-add-concentrator-submit").linkbutton({
        onClick: function () {
            if (!$("#text-dg-concentrator-name").textbox("isValid")) {
                $.messager.alert("操作提示", "请输入正确名称！", "info");
                return;
            }

            if (!$("#text-dg-concentrator-id").textbox("isValid")) {
                $.messager.alert("操作提示", "请输入正确馈线柜编号！", "info");
                return;
            }

            var name = $("#text-dg-concentrator-name").textbox("getValue");
            var concentratorId = $("#text-dg-concentrator-id").textbox("getValue");

            if (flag_dg_concentrator_edit) {
                var rows = $("#dg-concentrator-detail").datagrid("getSelections");
                var id = rows[0].id;

                $.ajax({
                    url: _ctx + "system/concentrator/info/update.do",
                    type: "POST",
                    data: {
                        id: id,
                        areaId: _areaId,
                        concentratorId: concentratorId,
                        name: name
                    },
                    cache: false,
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                getConcentratorInfo();
                                $("#dg-concentrator-detail").datagrid("reload");
                                $("#dlg-add-concentrator-node").dialog("close");
                                $.messager.alert("操作提示", "修改成功。", "info");
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
                var iList = $.parseJSON($.ajax({
                    url: _ctx + "system/concentrator/info/list.do",
                    type: "POST",
                    data: {
                        areaId: _areaId
                    },
                    async: false
                }).responseText);

                if ("0" != iList.errcode) {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(iList.errcode), "info");
                    return;
                }

                for (var i = 0; i < iList.data.length; i++) {
                    if (iList.data[i].concentratorId == concentratorId) {
                        $.messager.alert("操作提示", "编号已存在！", "info");
                        return;
                    }
                }

                $.ajax({
                    url: _ctx + "system/concentrator/info/add.do",
                    type: "POST",
                    data: {
                        areaId: _areaId,
                        concentratorId: concentratorId,
                        name: name
                    },
                    cache: false,
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                getConcentratorInfo();
                                $("#dg-concentrator-detail").datagrid("reload");
                                $("#dlg-add-concentrator-node").dialog("close");
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
    // 馈线柜处理结束

    // 树形处理开始
    $("#dlg-add-tree-node").dialog({
        onBeforeOpen: function () {
            if (flag_tree_node_edit) {
                var node = $("#dTree").tree("getSelected");
                var name = node.text;
                var iconCls = node.iconCls;
                var type = node.attributes.type;
                var concentratorId = node.attributes.concentratorId;

                $("#text-tree-node-name").textbox("setValue", name);
                $("#combo-tree-node-type").combobox("select", type);
                $("#combo-tree-node-iconCls").combobox("select", iconCls);
                $("#combo-tree-node-concentratorId").combobox("reload");
                $("#combo-tree-node-concentratorId").combobox("select", concentratorId);

            } else {
                $("#text-tree-node-name").textbox("clear");
                $("#combo-tree-node-type").combobox("clear");
                $("#combo-tree-node-iconCls").combobox("clear");
                $("#combo-tree-node-concentratorId").combobox("clear");
                $("#combo-tree-node-concentratorId").combobox("reload");
                $("#combo-tree-node-concentratorId").combobox("disable");
            }
        },
        onOpen: function () {
        },
        onClose: function () {
            flag_tree_node_edit = false;
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
            $("#combo-tree-node-iconCls").combobox("clear");
            $("#combo-tree-node-iconCls").combobox("loadData", []);
            if (record.value == "category") {
                $("#combo-tree-node-concentratorId").combobox("clear");
                $("#combo-tree-node-concentratorId").combobox("disable");
                $("#combo-tree-node-iconCls").combobox("reload", "data/comboTreeNodeCategoryIcons.json");
            } else if (record.value == "concentrator") {
                $("#combo-tree-node-concentratorId").combobox("clear");
                $("#combo-tree-node-concentratorId").combobox("enable");
                $("#combo-tree-node-iconCls").combobox("reload", "data/comboTreeNodeConcentratorIcons.json");
            }
        }

    });

    $("#combo-tree-node-iconCls").combobox({
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

            if (!$("#combo-tree-node-iconCls").combobox("isValid")) {
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
            var iconCls = $("#combo-tree-node-iconCls").combobox("getValue");
            var type = $("#combo-tree-node-type").combobox("getValue");
            var concentratorId = $("#combo-tree-node-concentratorId").combobox("getValue");
            var attributes = {
                type: type
            };

            if (attributes.type == "concentrator") {
                attributes.concentratorId = concentratorId;
            }

            if (flag_tree_node_edit) {
                $.ajax({
                    url: _ctx + "system/tree/info/update.do",
                    type: "POST",
                    data: {
                        id: node.id,
                        iconCls: iconCls,
                        attributes: JSON.stringify(attributes),
                        name: name
                    },
                    cache: false,
                    success: function (r) {
                        if (r.hasOwnProperty("errcode")) {
                            if ("0" == r.errcode) {
                                $("#dTree").tree("reload");
                                $("#dlg-add-tree-node").dialog("close");
                                $.messager.alert("操作提示", "修改成功。", "info");
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
                        areaId: _areaId,
                        pid: node.id,
                        treeId: _treeId,
                        iconCls: iconCls,
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
                            areaId: _areaId,
                            pid: 0,
                            treeId: info.areaId,
                            iconCls: "icon-house",
                            attributes: JSON.stringify({"type": "category"}),
                            name: _areaName
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

    $("#btn-tree-tool-edit").click(function () {
        var node = $("#dTree").tree("getSelected");

        if (node == undefined || node == null) {
            $.messager.alert("操作提示", "请选择节点！", "info");
        } else {
            flag_tree_node_edit = true;
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

    // !树形处理结束

    function getConcentratorInfo() {
        $.ajax({
            url: _ctx + "system/concentrator/info/list.do",
            type: "POST",
            data: {
                areaId: _areaId
            },
            cache: false,
            async: false,
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        _concentratorInfo = r.data;
                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            error: function (request) {
                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
            }
        });
    }
});