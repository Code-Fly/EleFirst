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
                unit: "干货区",
                name: "1-1",
                address: "厂房开水炉/P1-11#",
                type: "园区试挂9998",
                comment: ""
            },
            {
                unit: "水产区",
                name: "1-2",
                address: "检测楼一楼平面1AL/P2-12#",
                type: "园区试挂9998",
                comment: ""
            }
        ]
    });


    $("#btn-pn-tool-add").linkbutton({
        onClick: function () {
            $("#dlg-policy").dialog("open");
        }
    });


    $("#btn-dlg-policy-submit").linkbutton({
        onClick: function () {
            $("#dlg-policy").dialog("close");
        }
    });

});