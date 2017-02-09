/**
 * Created by VM on 2/9/2017.
 */
$(document).ready(function () {


    var areaDbId = null;

    $(".easyui-datebox").datebox("calendar").calendar({
        firstDay: 1
    });

    $("#btn-reset").linkbutton({
        onClick: function () {
            $("#ff").form("clear");
            $("#dg-column").datagrid("loadData", []);
        }
    });

    $("#combo-pn-list").combobox("reload", _ctx + "system/pn/info/list.do?areaId=" + _areaId);

    $("#btn-dg-add").linkbutton({
        onClick: function () {
            if ($("#combo-pn-list").combobox("isValid") && $("#text-rated-capacity").textbox("isValid")) {
                var rows = $("#dg-column").datagrid("getRows");
                var pnName = $("#combo-pn-list").combobox("getText");
                var pnId = $("#combo-pn-list").combobox("getValue");
                var ratedCapacity = $("#text-rated-capacity").textbox("getValue");

                if (!isInDg(rows, pnId)) {
                    $("#dg-column").datagrid("appendRow", {
                        pnName: pnName,
                        pnId: pnId,
                        ratedCapacity: ratedCapacity
                    });
                } else {
                    $.messager.alert("操作提示", "不能重复添加！", "info");
                }
            }
        }
    });

    $("#btn-dg-delete").linkbutton({
        onClick: function () {
            var rows = $("#dg-column").datagrid("getSelections");
            if (rows.length > 0) {
                $.messager.confirm("操作提示", "确定要删除吗？", function (r) {
                    if (r) {
                        for (var i = 0; i < rows.length; i++) {
                            var index = $("#dg-column").datagrid("getRowIndex");
                            $("#dg-column").datagrid("deleteRow", index);
                        }
                    }
                });
            }
        }
    });

    loadForm();

    $("#btn-submit").linkbutton({
        onClick: function () {
            if (areaDbId != null) {
                var param = formToJson($("#ff"));
                param.id = areaDbId;
                param.areaId = _areaId;
                param.transformers = JSON.stringify($("#dg-column").datagrid("getData"));
                if ($("#ff").form("enableValidation").form("validate")) {
                    $.ajax({
                        url: _ctx + "/system/area/info/update.do",
                        type: "POST",
                        cache: false,
                        contentType: "text/plain;charset=UTF-8",
                        data: JSON.stringify(param),
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $.messager.alert("操作提示", "修改成功", "info");
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
            } else {
                var param = formToJson($("#ff"));
                param.areaId = _areaId;
                param.transformers = JSON.stringify($("#dg-column").datagrid("getData"));
                if ($("#ff").form("enableValidation").form("validate")) {
                    $.ajax({
                        url: _ctx + "/system/area/info/add.do",
                        type: "POST",
                        cache: false,
                        contentType: "text/plain;charset=UTF-8",
                        data: JSON.stringify(param),
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $.messager.alert("操作提示", "修改成功", "info");
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

    function loadForm() {
        $.ajax({
            url: _ctx + "/system/area/info/detailByAreaId.do",
            type: "POST",
            cache: false,
            data: {
                areaId: _areaId
            },
            success: function (r) {
                if (r.hasOwnProperty("errcode")) {
                    if ("0" == r.errcode) {
                        if (r.data.areaId != "-1") {
                            areaDbId = r.data.id;
                        }
                        var info = clone(r.data);
                        if (info.establishmentDate != null && info.establishmentDate != "") {
                            var date = new Date();
                            date.setTime(info.establishmentDate);
                            info.establishmentDate = date.format("yyyy-MM-dd");
                        }
                        if (info.transformers != null && info.transformers != "") {
                            $("#dg-column").datagrid("loadData", $.parseJSON(info.transformers));
                        }
                        $("#ff").form("load", info);
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

    function isInteger(obj) {
        return typeof obj === 'number' && obj % 1 === 0
    }

    function isInDg(data, pnId) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].pnId == pnId) {
                return true;
            }
        }
        return false;
    }

});