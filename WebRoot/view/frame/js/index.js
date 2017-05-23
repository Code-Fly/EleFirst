/**
 * Created by barrie on 17/1/15.
 */
//新添加tab
function addTab(cname, curl, ciconCls) {
    if ($("#centerTabs").tabs("exists", cname)) {
        $("#centerTabs").tabs("select", cname);
    } else {
        if (curl && curl.length > 0) {
            $("#centerTabs").tabs("add", {
                title: cname,
                closable: true,
                iconCls: ciconCls,
                bodyCls: "tab-body",
                fit: true,
                content: "<iframe  src='" + curl + "' style='width: 100%;height: 100%;' frameborder='no' border='0' marginwidth='0' marginheight='0' scrolling='no' allowtransparency='yes'></iframe>",
            });
        }
    }
}

$(document).ready(function () {
    $("#combo-areaId").combobox({
        url: _ctx + "system/area/info/list.do",
        valueField: "areaId",
        textField: "name",
        onSelect: function (record) {
            top.location.href = top.location.pathname + "?" + $.param({id: record.areaId});
        }
    });

    $("#dlg-select-area").dialog({
        closable: false,
        onBeforeOpen: function () {
            $("#combo-areaId").combobox("reload");
        },
        onOpen: function () {

        },
        onClose: function () {

        }
    });

    $("#indexLayout").layout("add", {
            region: "west",
            title: "导航菜单",
            width: 200,
            href: _ctx + "view/frame/west.jsp",
            onLoad: function () {
                $("#centerTabs").tabs({
                    fit: true,
                    border: false
                });
            }
        }
    );

    $("#indexLayout").layout("add", {
            region: "north",
        height: 65,
            cls: "head-north",
            href: _ctx + "view/frame/north.jsp",
            onLoad: function () {
                $("#btn-select-area").click(function () {
                    $("#dlg-select-area").dialog("open");
                });

                $("#logout").click(function () {
                    $.messager.confirm("注销", "您确定要退出?", function (r) {
                        if (r) {
                            $("#form-logout").submit();
                        } else {
                            return;
                        }
                    });
                });
            }
        }
    );

    if (0 == _areaId) {
        $("#dlg-select-area").dialog("open");
    }
});