/**
 * Created by barrie on 17/1/16.
 */
$(document).ready(function () {
    var flag_bound_pn_edit = false;


    $("#dlg-bound-pn").dialog({
        onBeforeOpen: function () {
            var node = $.parseJSON($("#hid-selected-node-info").val());
            var nodeConfig = graphUtils.getConfig(node, $("#hid-config"));

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


    $("#input-color-picker").ColorPicker({
        flat: true,
        onSubmit: function (hsb, hex, rgb, el) {
            $(el).val(hex);
            graph.setCellStyles(_colorPickerCell.style, "#" + hex, [_colorPickerCell.cell]);
            $("#dlg-color-picker").dialog("close");
        },
        onBeforeShow: function () {
            $(this).ColorPickerSetColor(this.value);
        }
    });

    $("#dlg-color-picker").dialog({
        onBeforeOpen: function () {
            $("#input-color-picker").ColorPicker().ColorPickerSetColor(_colorPickerCell.value);
        },
        onOpen: function () {

        },
        onClose: function () {
            _colorPickerCell = null;
        }
    });

    $("#dlg-size-picker").dialog({
        onBeforeOpen: function () {
            if (_sizeCell.style == mxConstants.STYLE_FONTSIZE) {
                $("#combo-size-picker").combobox("loadData", initSizeCombo(12, 40));
            }
            else if (_sizeCell.style == mxConstants.STYLE_STROKEWIDTH) {
                $("#combo-size-picker").combobox("loadData", initSizeCombo(1, 10));
            }

            $("#combo-size-picker").combobox("select", _sizeCell.value);
        },
        onOpen: function () {

        },
        onClose: function () {
            _sizeCell = null;
        }
    });


    $("#combo-size-picker").combobox({
        required: true,
        textField: "name",
        valueField: "value",
        editable: false
    });


    $("#combo-bound-pn-id").combobox({
        required: true,
        textField: "name",
        valueField: "id",
        url: _ctx + "system/pn/info/list.do",
        queryParams: {
            areaId: _areaId
        },
        editable: false
    });

    $("#btn-reset").linkbutton({
        onClick: function () {
            window.location.href = window.location.href;
        }
    });

    $("#btn-refresh").linkbutton({
        onClick: function () {
            if ($("#hid-config").val() == "{}" || $("#hid-config").val() == "") {
                $.messager.alert("操作提示", "请绑定数据！", "info");
                return;
            }

            $.ajax({
                url: _ctx + "system/graph/latest/current/list.do",
                type: "POST",
                cache: false,
                contentType: "text/plain;charset=UTF-8",
                data: $("#hid-config").val(),
                success: function (r) {
                    if (r.hasOwnProperty("errcode")) {
                        if ("0" == r.errcode) {
                            var maxTime = null;
                            var timeList = r.data[graphConstants.USER_OBJECT_CURRENT];
                            for (var i = 0; i < timeList.length; i++) {
                                if (timeList[i].clientOperationTime != null) {
                                    var timeItem = TimeUtils.dbTimeToDate(timeList[i].clientOperationTime);
                                    if (i == 0) {
                                        maxTime = timeItem;
                                    } else {
                                        if (timeItem.getTime() > maxTime.getTime()) {
                                            maxTime = timeItem;
                                        }
                                    }
                                }
                            }
                            if (null != maxTime) {
                                $("#updateTimeContainer").text("采集状态：成功获取到 " + maxTime.toLocaleString() + " 实时数据");
                            }
                            update(graph, r.data);
                        } else {
                            $.messager.alert("操作提示", "提交失败！" + ErrUtils.getMsg(r.errcode, param), "info");
                        }
                    } else {
                        $.messager.alert("操作提示", "提交失败！" + ErrUtils.getMsg("2"), "info");
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    MaskUtil.mask();
                },
                error: function (request) {
                    jError("请求失败！" + ErrUtils.getMsg("3"));
                },
                complete: function (XMLHttpRequest, textStatus) {
                    MaskUtil.unmask();
                }

            });
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


            graphUtils.addConfig(node, $("#hid-config"));

            $("#dlg-bound-pn").dialog("close");
        }
    });

    $("#btn-dlg-color-picker-submit").linkbutton({
        onClick: function () {
            $("#dlg-color-picker").dialog("close");
        }
    });

    $("#btn-dlg-size-picker-submit").linkbutton({
        onClick: function () {
            graph.setCellStyles(_sizeCell.style, $("#combo-size-picker").combobox("getValue"), [_sizeCell.cell]);
            $("#dlg-size-picker").dialog("close");
        }
    });

    $("#btn-save-template").linkbutton({
        onClick: function () {
            if ("" == $("#combo-template").combobox("getValue")) {
                $.messager.alert("操作提示", "请选择要保存的模板！", "info");
                return;
            }

            $.messager.confirm("信息提示", "需要保存当前模板吗？", function (r) {
                if (r) {
                    var config = $("#hid-config").val();

                    var encoder = new mxCodec();
                    var node = encoder.encode(graph.getModel());
                    var content = mxUtils.getPrettyXml(node);

                    $.ajax({
                        url: _ctx + "system/graph/template/update.do",
                        type: "POST",
                        cache: false,
                        data: {
                            id: $("#combo-template").combobox("getValue"),
                            config: config,
                            content: content
                        },
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $.messager.alert("操作提示", "模板已保存！", "info");
                                } else {
                                    $.messager.alert("操作提示", "模板保存失败！" + ErrUtils.getMsg(r.errcode), "info");
                                }
                            } else {
                                $.messager.alert("操作提示", "模板保存失败！" + ErrUtils.getMsg("2"), "info");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            MaskUtil.mask();
                        },
                        error: function (request) {
                            $.messager.alert("操作提示", "模板保存失败！" + ErrUtils.getMsg("3"), "info");
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            MaskUtil.unmask();
                        }

                    });
                }
            });
        }
    });

    var MAX_TEMPLATE_NAME_LENGTH = 50;

    $("#btn-saveAs-template").linkbutton({
        onClick: function () {


            $.messager.prompt("信息提示", "请输入新模板名：", function (name) {
                if (name) {
                    if (getStringLength(name) > MAX_TEMPLATE_NAME_LENGTH) {
                        $.messager.alert("操作提示", "名字不能超过 " + MAX_TEMPLATE_NAME_LENGTH + " 个字节！", "info");
                        return;
                    }

                    var config = $("#hid-config").val();

                    var encoder = new mxCodec();
                    var node = encoder.encode(graph.getModel());
                    var content = mxUtils.getPrettyXml(node);

                    $.ajax({
                        url: _ctx + "system/graph/template/add.do",
                        type: "POST",
                        cache: false,
                        data: {
                            areaId: _areaId,
                            name: name,
                            config: config,
                            content: content
                        },
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $.messager.alert("操作提示", "模板已保存！", "info", function (r) {
                                        $("#combo-template").combobox("reload");
                                    });
                                } else {
                                    $.messager.alert("操作提示", "模板保存失败！" + ErrUtils.getMsg(r.errcode), "info");
                                }
                            } else {
                                $.messager.alert("操作提示", "模板保存失败！" + ErrUtils.getMsg("2"), "info");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            MaskUtil.mask();
                        },
                        error: function (request) {
                            $.messager.alert("操作提示", "模板保存失败！" + ErrUtils.getMsg("3"), "info");
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            MaskUtil.unmask();
                        }

                    });
                }
            });
        }
    });

    $("#btn-del-template").linkbutton({
        onClick: function () {
            var selected = $("#combo-template").combobox("getValue");
            if ("" == selected || null == selected) {
                $.messager.alert("操作提示", "请选择模板！", "info");
                return;
            }
            $.messager.confirm("信息提示", "确定删除当前模板吗？", function (r) {
                if (r) {
                    $.ajax({
                        url: _ctx + "system/graph/template/delete.do",
                        type: "POST",
                        cache: false,
                        data: {
                            id: $("#combo-template").combobox("getValue")
                        },
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $.messager.alert("操作提示", "删除成功。", "info", function (r) {
                                        $("#combo-template").combobox("reload");
                                    });
                                } else {
                                    $.messager.alert("操作提示", "删除失败！" + ErrUtils.getMsg(r.errcode), "info");
                                }
                            } else {
                                $.messager.alert("操作提示", "删除失败！" + ErrUtils.getMsg("2"), "info");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            $("#btn-del-template").linkbutton("disable");
                        },
                        error: function (request) {
                            $.messager.alert("操作提示", "删除失败！" + ErrUtils.getMsg("3"), "info");
                        },
                        complete: function (XMLHttpRequest, textStatus) {
                            $("#btn-del-template").linkbutton("enable");
                        }

                    });
                }
            });
        }
    });

    $("#combo-template").combobox({
        editable: false,
        valueField: "id",
        textField: "name",
        url: _ctx + "system/graph/template/list.do",
        queryParams: {
            areaId: _areaId
        },
        formatter: function (row) {
            return "<div title='" + HTMLUtils.encode(row.name) + "'>" + HTMLUtils.encode(row.name) + "</div>";
        },
        onSelect: function (record) {
            getTemplate(record);
        },
        onBeforeLoad: function (param) {
            $(this).combobox("clear");
        },
        onLoadError: function (r) {
            jError("请求失败！" + ErrUtils.getMsg(r.errcode));
        }
    });

    function getTemplate(record) {
        $("#updateTimeContainer").text("");
        $("#hid-config").val(record.config);

        var doc = mxUtils.parseXml(record.content);
        var codec = new mxCodec(doc);
        codec.decode(doc.documentElement, graph.getModel());
    }

    /**
     * Updates the display of the given graph using the XML data
     */
    function update(graph, nodes) {
        var model = graph.getModel();

        var currentNodes = nodes[graphConstants.USER_OBJECT_CURRENT];

        for (var i = 0; i < currentNodes.length; i++) {
            // Processes the activity nodes inside the process node
            var id = currentNodes[i].cellId;
            // Gets the cell for the given activity name from the model
            var cell = model.getCell(id);
            // Updates the cell color and adds some tooltip information
            if (cell != null) {
                model.beginUpdate();
                try {
                    if (isValidStyle(cell.style, "phase=2")) {
                        var aCurrent = currentNodes[i].aCurrent;
                        var bCurrent = currentNodes[i].bCurrent;

                        var content = getTwoPhaseCurrentLabel(aCurrent, bCurrent);
                        var edit = new mxValueChange(model, cell, content);
                        model.execute(edit);
                    }
                    else if (isValidStyle(cell.style, "phase=3")) {
                        var aCurrent = currentNodes[i].aCurrent;
                        var bCurrent = currentNodes[i].bCurrent;
                        var cCurrent = currentNodes[i].cCurrent;

                        var content = getThreePhaseCurrentLabel(aCurrent, bCurrent, cCurrent);
                        var edit = new mxValueChange(model, cell, content);
                        model.execute(edit);
                    }
                }
                finally {
                    model.endUpdate();
                }
            }
        } // for

        var switchStateNodes = nodes[graphConstants.USER_OBJECT_SWITCH_STATE];

        for (var i = 0; i < switchStateNodes.length; i++) {
            // Processes the activity nodes inside the process node
            var id = switchStateNodes[i].cellId;
            // Gets the cell for the given activity name from the model
            var cell = model.getCell(id);
            // Updates the cell color and adds some tooltip information
            if (cell != null) {
                model.beginUpdate();
                try {
                    if (switchStateNodes[i].state == graphConstants.SWITCH_STATE_ON) {
                        graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "lime", [cell]);
                    }
                    else if (switchStateNodes[i].state == graphConstants.SWITCH_STATE_OFF) {
                        graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "red", [cell]);
                    }
                }
                finally {
                    model.endUpdate();
                }
            }
        } // for
    }

    function isValidStyle(style, name) {
        var styles = (style + "").split(";");
        for (var i = 0; i < styles.length; i++) {
            if (styles[i] == name) {
                return true;
            }
        }
        return false;
    }

    function initSizeCombo(min, max) {
        var sizeData = [];
        for (var i = min; i <= max; i++) {
            sizeData.push({
                name: i,
                value: i
            })
        }
        return sizeData;
    }

    function getTwoPhaseCurrentLabel(a, b) {
        return '<p style="color: orange">' + '<span class="a">' + a + '</span>' + ' (A)</p>' + '<p style="color: green">' + '<span class="b">' + b + '</span>' + ' (A)</p>';
    }

    function getThreePhaseCurrentLabel(a, b, c) {
        return '<p style="color: orange">' + '<span class="a">' + a + '</span>' + ' (A)</p>' + '<p style="color: green">' + '<span class="b">' + b + '</span>' + ' (A)</p>' + '<p style="color: red">' + '<span class="c">' + c + '</span>' + ' (A)</p>';
    }
});