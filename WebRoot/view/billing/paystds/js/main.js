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
            name: "电费",
            type: "周期性收费",
            calcType: "单价 x 数量",
            unitPrice: "2.6",
            measure: "用电量",
            period: "每隔 1 月计费一次",
            comment: ""
        }, {
            name: "水费",
            type: "周期性收费",
            calcType: "单价 x 数量",
            unitPrice: "2.22",
            measure: "用水量",
            period: "每隔 2 月计费一次",
            comment: ""
        }, {
            name: "气费",
            type: "周期性收费",
            calcType: "单价 x 数量",
            unitPrice: "2.9",
            measure: "用电量",
            period: "每隔 1 月计费一次",
            comment: ""
        }, {
            name: "热费",
            type: "周期性收费",
            calcType: "单价 x 数量",
            unitPrice: "2.62",
            measure: "用水量",
            period: "每隔 2 月计费一次",
            comment: ""
        }]
    });

    $("#dTree").tree({
        animate: true,
        lines: true,
        data: [
            {
                "text": "收费项目",
                "attributes": {
                    "type": "category"
                },
                "children": [
                    {
                        "text": "周期性收费项目",
                        "state": "open",
                        "attributes": {
                            "type": "category"
                        },
                        "children": [
                            {
                                "text": "水费",
                                "attributes": {
                                    "type": "category"
                                },
                                "children": []
                            },
                            {
                                "text": "电费",
                                "attributes": {
                                    "type": "category"
                                },
                                "children": []
                            },
                            {
                                "text": "气费",
                                "attributes": {
                                    "type": "category"
                                },
                                "children": []
                            },
                            {
                                "text": "热费",
                                "attributes": {
                                    "type": "category"
                                },
                                "children": []
                            }
                        ]
                    },
                    {
                        "text": "临时性收费项目",
                        "state": "open",
                        "attributes": {
                            "type": "category"
                        },
                        "children": [
                            {
                                "text": "工本费",
                                "attributes": {
                                    "type": "category"
                                },
                                "children": []
                            }
                        ]
                    },
                    {
                        "text": "押金类收费项目",
                        "state": "open",
                        "attributes": {
                            "type": "category"
                        },
                        "children": [
                            {
                                "text": "装修保证金",
                                "attributes": {
                                    "type": "category"
                                },
                                "children": []
                            }
                        ]
                    }
                ]
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

    $("#btn-tree-tool-add").click(function () {
        $("#dlg-add-tree-node").dialog("open");
    });

});