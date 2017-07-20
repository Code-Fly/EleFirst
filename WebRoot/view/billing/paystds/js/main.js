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
            corpName: "南通农副产品物流中心",
            corpCode: "ntnfwlzx",
            name: "电费",
            evalTimeStart: "2017.1.1",
            evalTimeEnd: "2018.12.31",
            type: "周期性收费",
            calcType: "单一制",
            unitPrice: "",
            jfr: "1日",
            jfjzr: "15日",
            zdkfr: "2日",
            wyj: "逾期未缴按欠费金额0.1%/天收取",
            ycxyhje: "100",
            ycxyhwjbh: "ycx001",
            enable: "启用",
            comment: ""
        }, {
            corpName: "南通农副产品物流中心",
            corpCode: "ntnfwlzx",
            name: "电费",
            evalTimeStart: "2017.1.1",
            evalTimeEnd: "2018.12.31",
            type: "周期性收费",
            calcType: "分时电价",
            unitPrice: "",
            jfr: "1日",
            jfjzr: "15日",
            zdkfr: "2日",
            wyj: "逾期未缴按欠费金额0.1%/天收取",
            ycxyhje: "0",
            ycxyhwjbh: "",
            enable: "启用",
            comment: ""
        }

        ]
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