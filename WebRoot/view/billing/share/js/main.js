/**
 * Created by barrie on 2017/7/2.
 */
$(document).ready(function () {
    $("#dgList").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [
            {
                name: "1号楼公摊",
                type: "按用量公摊",
                unit: "1号楼"
            },
            {
                name: "2号楼公摊",
                type: "按用量公摊",
                unit: "2号楼"
            },
            {
                name: "3号楼公摊",
                type: "按用量公摊",
                unit: "3号楼"
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