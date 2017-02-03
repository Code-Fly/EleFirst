/**
 * Created by barrie on 17/1/16.
 */
$(document).ready(function () {
    $.extend($.fn.datagrid.defaults.editors, {
        colorpicker: {//colorpicker就是你要自定义editor的名称
            init: function (container, options) {
                // var input = $('<input class="easyuidatetimebox">').appendTo(container);
                var input = $('<input>').appendTo(container);

                input.ColorPicker({
                    color: '#0000ff',
                    onShow: function (colpkr) {
                        $(colpkr).fadeIn(500);
                        return false;
                    },
                    onHide: function (colpkr) {
                        $(colpkr).fadeOut(500);
                        return false;
                    },
                    onChange: function (hsb, hex, rgb) {
                        //                    $('#colorSelector div').css('backgroundColor', '#' + hex);
                        input.css('backgroundColor', '#' + hex);
                        input.val('#' + hex);
                    }
                });
                return input;
            },
            getValue: function (target) {
                return $(target).val();
            },
            setValue: function (target, value) {
                $(target).val(value);
                $(target).css('backgroundColor', value);
                $(target).ColorPickerSetColor(value);
            },
            resize: function (target, width) {
                var input = $(target);
                if ($.boxModel == true) {
                    input.width(width - (input.outerWidth() - input.width()));
                } else {
                    input.width(width);
                }
            }
        }
    });

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


    $("#input-color-picker").ColorPicker({
        flat: true,
        onSubmit: function (hsb, hex, rgb, el) {
            $(el).val(hex);
            graph.setCellStyles(_colorPickerCell.style, "#" + hex, [_colorPickerCell.cell]);
            _colorPickerCell = null;
            $("#dlg-color-picker").dialog("close");
        },
        onBeforeShow: function () {
            $(this).ColorPickerSetColor(this.value);
        }
    });

    $("#dlg-color-picker").dialog({
        onBeforeOpen: function () {
            // var color = $("#hid-color").val();
            //
            // if (null != color && "" != color) {
            //     $("#input-color-picker").val(color);
            // }
        },
        onOpen: function () {

        },
        onClose: function () {

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
                            if (r.data[graphConstants.USER_OBJECT_CURRENT].length > 0) {
                                var time = r.data[graphConstants.USER_OBJECT_CURRENT][0].clientOperationTime;
                                $("#updateTimeContainer").text("采集状态：成功获取到 " + getDbDate(time).toLocaleString() + " 实时数据");
                            }
                            update(graph, r.data);
                        } else {
                            $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg(r.errcode, param), "info");
                        }
                    } else {
                        $.messager.alert("操作提示", "提交失败！" + DsmErrUtils.getMsg("2"), "info");
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

    $("#btn-dlg-color-picker-submit").linkbutton({
        onClick: function () {


            $("#dlg-color-picker").dialog("close");
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
                                    $.messager.alert("操作提示", "模板保存失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                }
                            } else {
                                $.messager.alert("操作提示", "模板保存失败！" + DsmErrUtils.getMsg("2"), "info");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            MaskUtil.mask();
                        },
                        error: function (request) {
                            $.messager.alert("操作提示", "模板保存失败！" + DsmErrUtils.getMsg("3"), "info");
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
                                    $.messager.alert("操作提示", "模板保存失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                }
                            } else {
                                $.messager.alert("操作提示", "模板保存失败！" + DsmErrUtils.getMsg("2"), "info");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            MaskUtil.mask();
                        },
                        error: function (request) {
                            $.messager.alert("操作提示", "模板保存失败！" + DsmErrUtils.getMsg("3"), "info");
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
                                    $.messager.alert("操作提示", "删除失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                                }
                            } else {
                                $.messager.alert("操作提示", "删除失败！" + DsmErrUtils.getMsg("2"), "info");
                            }
                        },
                        beforeSend: function (XMLHttpRequest) {
                            $("#btn-del-template").linkbutton("disable");
                        },
                        error: function (request) {
                            $.messager.alert("操作提示", "删除失败！" + DsmErrUtils.getMsg("3"), "info");
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
        formatter: function (row) {
            return "<div title='" + HTMLUtils.encode(row.name) + "'>" + HTMLUtils.encode(row.name) + "</div>";
        },
        onSelect: function (record) {
            getTemplate(record.id);
        },
        onBeforeLoad: function (param) {
            $(this).combobox("clear");
        },
        onLoadError: function (r) {
            $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
        },
    });

    function getTemplate(id) {
        $.ajax({
            url: _ctx + "system/graph/template/detail.do",
            type: "POST",
            cache: false,
            data: {
                id: id
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        if (0 == r.data.length) {
                            $("#combo-template").combobox("clear");
                            $("#combo-template").combobox("reload");
                            $.messager.alert("操作提示", "模板已失效，请重新选择！", "info");
                            return;
                        }

                        $("#updateTimeContainer").text("");
                        $("#hid-config").val(r.data[0].config);

                        var doc = mxUtils.parseXml(r.data[0].content);
                        var codec = new mxCodec(doc);
                        codec.decode(doc.documentElement, graph.getModel());


                        $("#btn-save-template").linkbutton("enable");
                    } else {
                        $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg(r.errcode), "info");
                    }
                } else {
                    $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("2"), "info");
                }
            },
            beforeSend: function (XMLHttpRequest) {
                $("#combo-template").combobox("disable");
            },
            error: function (request) {
                $.messager.alert("操作提示", "请求失败！" + DsmErrUtils.getMsg("3"), "info");
            },
            complete: function (XMLHttpRequest, textStatus) {
                $("#combo-template").combobox("enable");
            }

        });
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
                    if (switchStateNodes[i].attributes.state == graphConstants.SWITCH_STATE_ON) {
                        graph.setCellStyles(mxConstants.STYLE_FILLCOLOR, "lime", [cell]);
                    }
                    else if (switchStateNodes[i].attributes.state == graphConstants.SWITCH_STATE_OFF) {
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

    function getTwoPhaseCurrentLabel(a, b) {
        return '<p style="color: orange">' + '<span class="a">' + a + '</span>' + ' (A)</p>' + '<p style="color: green">' + '<span class="b">' + b + '</span>' + ' (A)</p>';
    }

    function getThreePhaseCurrentLabel(a, b, c) {
        return '<p style="color: orange">' + '<span class="a">' + a + '</span>' + ' (A)</p>' + '<p style="color: green">' + '<span class="b">' + b + '</span>' + ' (A)</p>' + '<p style="color: red">' + '<span class="c">' + c + '</span>' + ' (A)</p>';
    }
});