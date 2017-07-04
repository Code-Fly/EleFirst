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
            unit: "水产区",
            name: "1-1",
            address: "C1-001",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-2",
            address: "C1-002",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-3",
            address: "C1-003",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-4",
            address: "C1-004",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-5",
            address: "C1-005",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-6",
            address: "C1-006",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-7",
            address: "C1-007",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-8",
            address: "C1-008",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-9",
            address: "C1-009",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-10",
            address: "C1-010",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-11",
            address: "C1-011",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-12",
            address: "C1-012",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-13",
            address: "C1-013",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-14",
            address: "C1-014",
            type: "单相表",
            comment: ""
        }, {
            unit: "水产区",
            name: "1-15",
            address: "C1-015",
            type: "单相表",
            comment: ""
        }]
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