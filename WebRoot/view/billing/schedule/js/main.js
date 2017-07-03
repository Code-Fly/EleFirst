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
            unit: "干货区",
            name: "1-1",
            address: "厂房开水炉/P1-11#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "干货区",
            name: "1-2",
            address: "检测楼一楼平面1AL/P2-12#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "干货区",
            name: "1-3",
            address: "厂房开水炉/P1-12#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "干货区",
            name: "1-4",
            address: "检测楼一楼平面1AL/P2-13#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "干货区",
            name: "1-5",
            address: "厂房开水炉/P1-14#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "干货区",
            name: "1-6",
            address: "检测楼一楼平面1AL/P2-15#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "干货区",
            name: "1-7",
            address: "厂房开水炉/P1-16#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "干货区",
            name: "1-8",
            address: "检测楼一楼平面1AL/P2-17#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "水产区",
            name: "2-1",
            address: "厂房开水炉/P2-11#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "水产区",
            name: "2-2",
            address: "检测楼一楼平面1AL/P2-13#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "水产区",
            name: "2-3",
            address: "厂房开水炉/P2-12#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "水产区",
            name: "2-4",
            address: "检测楼一楼平面1AL/P2-14#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "水产区",
            name: "2-5",
            address: "检测楼一楼平面1AL/P2-15#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "水产区",
            name: "2-6",
            address: "厂房开水炉/P2-13#",
            type: "园区试挂9998",
            comment: ""
        }, {
            unit: "水产区",
            name: "2-7",
            address: "检测楼一楼平面1AL/P2-16#",
            type: "园区试挂9998",
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