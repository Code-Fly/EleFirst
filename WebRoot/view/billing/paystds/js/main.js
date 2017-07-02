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
                name: "电费",
                type: "周期性收费",
                calcType: "单价 x 数量",
                unitPrice: "2.13",
                measure: "用电量",
                period: "每隔 1 月计费一次",
                comment: ""
            },
            {
                name: "水费",
                type: "周期性收费",
                calcType: "单价 x 数量",
                unitPrice: "1.22",
                measure: "用水量",
                period: "每隔 2 月计费一次",
                comment: ""
            }
        ]
    });

    $("#dgPnConfigList").datagrid({
        pagination: false,
        data: [
            {
                pnName: "1-1",
                pnAddr: "厂房开水炉/P1-11#",
                pnType: "运行",
                unit: "1号楼",
                comment: ""
            },
            {
                pnName: "1-2",
                pnAddr: "检测楼一楼平面1AL/P2-12#",
                pnType: "运行",
                unit: "2号楼",
                comment: ""
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