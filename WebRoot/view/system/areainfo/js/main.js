/**
 * Created by VM on 2/9/2017.
 */
$(document).ready(function () {
    var localInfo = $.parseJSON($.ajax({
        url: _ctx + "system/area/info/local/detail.do",
        type: "POST",
        async: false
    }).responseText).data;


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
            if ($("#text-transformer-name").textbox("isValid") && $("#text-transformer-rated-capacity").textbox("isValid")) {
                var rows = $("#dg-column").datagrid("getRows");
                var name = $("#text-transformer-name").textbox("getValue");
                var ratedCapacity = $("#text-transformer-rated-capacity").textbox("getValue");

                if (!isInDg(rows, name)) {
                    $("#dg-column").datagrid("appendRow", {
                        transformerName: name,
                        transformerRatedCapacity: ratedCapacity
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
            if (localInfo.id != null) {
                var param = formToJson($("#ff"));
                param.id = localInfo.id;
                param.areaId = localInfo.areaId;
                param.transformers = JSON.stringify($("#dg-column").datagrid("getRows"));
                if ($("#ff").form("enableValidation").form("validate")) {
                    $.ajax({
                        url: _ctx + "system/area/info/update.do",
                        type: "POST",
                        cache: false,
                        contentType: "text/plain;charset=UTF-8",
                        data: JSON.stringify(param),
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $.messager.alert("操作提示", "修改成功", "info");
                                } else {
                                    jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                                }
                            } else {
                                jError("请求失败！" + ErrUtils.getMsg("2"));
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
            } else {
                var param = formToJson($("#ff"));
                param.areaId = localInfo.areaId;
                param.transformers = JSON.stringify($("#dg-column").datagrid("getRows"));
                if ($("#ff").form("enableValidation").form("validate")) {
                    $.ajax({
                        url: _ctx + "system/area/info/add.do",
                        type: "POST",
                        cache: false,
                        contentType: "text/plain;charset=UTF-8",
                        data: JSON.stringify(param),
                        success: function (r) {
                            if (r.hasOwnProperty("errcode")) {
                                if ("0" == r.errcode) {
                                    $.messager.alert("操作提示", "修改成功", "info");
                                } else {
                                    jError("请求失败！" + ErrUtils.getMsg(r.errcode));
                                }
                            } else {
                                jError("请求失败！" + ErrUtils.getMsg("2"));
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
            }
        }
    });

    function loadForm() {
        // if (localInfo.id != null) {
        //     areaDbId = localInfo.id;
        // }
        var info = clone(localInfo);
        if (info.establishmentDate != null && info.establishmentDate != "") {
            var date = new Date();
            date.setTime(info.establishmentDate);
            info.establishmentDate = date.format("yyyy-MM-dd");
        }
        if (info.transformers != null && info.transformers != "") {
            $("#dg-column").datagrid("loadData", $.parseJSON(info.transformers));
        }
        $("#ff").form("load", info);
    }

    function isInteger(obj) {
        return typeof obj === 'number' && obj % 1 === 0
    }

    function isInDg(data, name) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].transformerName == name) {
                return true;
            }
        }
        return false;
    }

});