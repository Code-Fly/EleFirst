$().ready(function () {
    $("#tt2").datagrid({
        url: _ctx + 'system/user/info/list.do',
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        fitColumns: true,
        loadMsg: "正在加载...",
        onLoadError: function () {
            jError("查询用户信息失败", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
        }
    });
});

//刷新页面
function refreshClick() {
    window.location.reload();
}

//添加用户实例
function addClick() {
    flag = "add";
    $('#dlg').dialog('open').dialog('setTitle', '新增用户');
    $('#ff').form('clear');
}

//编辑用户
function editClick() {
    flag = "modify";
    var singlerow = $('#tt2').datagrid('getSelected');
    var index = null;
    if (singlerow) {
        index = $('#tt2').datagrid('getRowIndex', singlerow);
    } else {
        jNotify("请选择一条用户记录", {
            VerticalPosition: 'center',
            HorizontalPosition: 'center',
            ShowOverlay: false
        });
        return;
    }


    $('#dlgUpdate').dialog('open').dialog('setTitle', '编辑用户');
    $('#ffs').form('clear');
    $('#ffs').form('load', {
        userName: singlerow.userName,
        userCode: singlerow.userCode,
        description: singlerow.description
    });
}

//新增用户
function submitForm() {
    var url = "";
    var userId = "";
    if ("add" == flag) {
        url = _ctx + 'system/user/info/addpersoninfo.do';
        $('#ff').form('submit', {
            url: url,
            success: function (data) {
                var d = eval('(' + data + ')');
                if ("success" == d.errmsg) {
                    $('#dlg').dialog('close');
                    //重新查询数据
                    $('#tt2').datagrid("reload");
                    jSuccess("保存成功", {
                        VerticalPosition: 'center',
                        HorizontalPosition: 'center',
                        ShowOverlay: false
                    });
                } else {
                    if ('-1' == d.errcode) {
                        jError("保存失败", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                    } else if ('-2' == d.errcode) {
                        jError("用户名已存在", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                    }
                }
            },
            onLoadError: function () {
                jError("保存失败", {
                    VerticalPosition: 'center',
                    HorizontalPosition: 'center',
                    ShowOverlay: false
                });
            }
        });
    } else if ("modify" == flag) {
        var singlerow = $('#tt2').datagrid('getSelected');
        userId = singlerow.id
        url = _ctx + 'system/user/info/updatePersonInfo.do';

        $('#ffs').form('submit', {
            url: url,
            queryParams: {
                userId: userId
            },
            success: function (data) {
                var d = eval('(' + data + ')');
                if ("success" == d.errmsg) {
                    $('#dlgUpdate').dialog('close');
                    //重新查询数据
                    $('#tt2').datagrid("reload");
                    jSuccess("保存成功", {
                        VerticalPosition: 'center',
                        HorizontalPosition: 'center',
                        ShowOverlay: false
                    });
                } else {
                    if ('-1' == d.errcode) {
                        jError("保存失败", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                    } else if ('-2' == d.errcode) {
                        jError("用户已存在", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                    }
                }
            },
            onLoadError: function () {
                jError("保存失败", {
                    VerticalPosition: 'center',
                    HorizontalPosition: 'center',
                    ShowOverlay: false
                });
            }
        });
    } else {
        return;
    }

}

function clearForm() {
    $('#ff').form('clear');
    $('#ffs').form('clear');
}

//删除用户
function deleteClick() {
    var singlerow = $('#tt2').datagrid('getSelected');
    var index = null;
    if (singlerow) {
        index = $('#tt2').datagrid('getRowIndex', singlerow);
    } else {
        jNotify("请选择一条用户记录", {
            VerticalPosition: 'center',
            HorizontalPosition: 'center',
            ShowOverlay: false
        });
        return;
    }

    $.messager.confirm("确认", "删除用户", function (r) {
        if (r) {
            $.ajax({
                url: _ctx + 'system/user/info/delete.do',
                type: "post",//使用post方法访问后台
                dataType: "json",
                cache: false,
                data: {
                    id: singlerow.id
                },
                success: function (msg) {
                    if ("success" == msg.errmsg) {
                        jSuccess("删除成功", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                        $('#tt2').datagrid("reload");
                    } else {
                        jError("删除失败", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                    }
                }
            });
        }
    });
}

//分配角色
function roleClick() {
    var singlerow = $('#tt2').datagrid('getSelected');
    var index = null;
    if (singlerow) {
        index = $('#tt2').datagrid('getRowIndex', singlerow);
    } else {
        jNotify("请选择一条记录", {
            VerticalPosition: 'center',
            HorizontalPosition: 'center',
            ShowOverlay: false
        });
        return;
    }
    var userId = singlerow.id;
    $('#dlg2').dialog('open');
    $("#tt3").datagrid({
        url: _ctx + '/system/role/info/queryRoleByUserId.do',
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        fitColumns: true,
        checkOnSelect: true,
        queryParams: {
            userId: userId
        },
        onLoadSuccess: function (row, data) {
            $.each(row.rows, function (index, item) {
                if (item.checked) {
                    $('#tt3').datagrid('checkRow', index);
                }
            });
        },
        loadMsg: "正在加载...",
        onLoadError: function () {
            jError("查询失败", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
        }
    });
}

//确定
function confirm() {
    //获取所有check
    var rows = $('#tt3').datagrid("getChecked");
    var roleIds = [];
    for (var i = 0; i < rows.length; i++) {
        roleIds.push(rows[i].id);
    }
    var roleIdsStr = roleIds.join(",")
    var singlerow = $('#tt2').datagrid('getSelected');
    var userId = singlerow.id;
    $.ajax({
        url: _ctx + '/system/role/info/addUserRoleMap.do',
        type: "post",//使用post方法访问后台
        dataType: "json",
        cache: false,
        data: {
            userId: userId,
            roleIdsStr: roleIdsStr
        },
        success: function (msg) {
            if ("success" == msg.errmsg) {
                $('#dlg2').dialog('close');
                jSuccess("保存成功", {
                    VerticalPosition: 'center',
                    HorizontalPosition: 'center',
                    ShowOverlay: false
                });
            } else if ("failed" == msg.errmsg) {
                jError("保存失败", {
                    VerticalPosition: 'center',
                    HorizontalPosition: 'center',
                    ShowOverlay: false
                });
            }
        }
    });
}

//取消
function cancel(thisObj) {
    $('#dlg2').dialog('close');
}