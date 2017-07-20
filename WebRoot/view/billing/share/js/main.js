/**
 * Created by barrie on 2017/7/2.
 */
$(document).ready(function () {
    $("#dgList").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [{
            name: "公摊（按面积）",
            type: "按面积公摊",
            unit: "干货区",
            enable: "启用"
        }, {
            name: "公摊（按用量）",
            type: "按用量公摊",
            unit: "水产区",
            enable: "启用"
        }]
    });


    $("#btn-pn-tool-add").linkbutton({
        onClick: function () {
            $("#dlg-add-user").dialog("open");
        }
    });

    $("#btn-pn-tool-edit").linkbutton({
        onClick: function () {
            $("#dlg-add-user").dialog("open");
        }
    });


    $("#btn-dlg-add-user-submit").linkbutton({
        onClick: function () {
            $("#dlg-add-user").dialog("close");
        }
    });

});