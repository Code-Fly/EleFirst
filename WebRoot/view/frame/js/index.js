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
                fit: true,
                content: '<iframe src="' + curl + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
            });
        }
    }
}

$(document).ready(function () {
    $("#indexLayout").layout("add", {
            region: "west",
            width: 150,
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
            height: 70,
            cls: "head-north",
            href: _ctx + "view/frame/north.jsp",
            onLoad: function () {
                $("#logout").click(function () {
                    $.messager.confirm("注销", "您确定要退出?", function (r) {
                        if (r) {
                            window.location = _ctx + "system/userinfo/logout.do";
                        } else {
                            return;
                        }
                    });
                });
            }
        }
    );

});