/**
 * Created by VM on 2/9/2017.
 */
$(document).ready(function () {
    $(".easyui-datebox").datebox("calendar").calendar({
        firstDay: 1
    });

    $("#btn-reset").linkbutton({
        onClick: function () {
            $("#ff").form("clear");
        }
    });

    $("#btn-submit").linkbutton({
        onClick: function () {
            var param = formToJson($("#ff"));
            param.id = "5f119e1a-13ee-496f-b01e-a868d297d25d";
            param.areaId = "1";
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
        }
    });

});