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
            name: "干货区公用公摊",
            type: "按面积公摊公用",
            unit: "干货区"
        }, {
            name: "干货区损耗公摊",
            type: "按面积公摊损耗",
            unit: "干货区"
        }, {
            name: "水产区公用公摊",
            type: "按面积公摊公用",
            unit: "水产区"
        }, {
            name: "水产区损耗公摊",
            type: "按面积公摊损耗",
            unit: "干货区"
        }
        ]
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