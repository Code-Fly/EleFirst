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
                userName: "魏嘉志",
                userCode: "1470901301",
                idNo: "440901197709194316",
                mobile: "13987654321",
                wechat: "13987654321",
                contractNo: "15109961386041285971",
                billTimeStart: "2017-06-01",
                billTimeEnd: "2017-06-31",
                address: "四川省 甘孜藏族自治州 九龙县",
                area: "88平",
                checkInStatus: "入住",
                unit: "1号楼",
                chargeType: "电",
                openDate: "2016-10-10",
                pnType: "单项",
                pnBase: "1876234",
                billType: "单一制",
                payment: "预交费",
                paymentDeadline: "2017-07-12",
                balance: "0",
                comment: ""
            },
            {
                userName: "班旦",
                userCode: "1470901302",
                idNo: "230302197804086916",
                mobile: "15020000314",
                wechat: "15020000314",
                contractNo: "15109961386198237973",
                billTimeStart: "2017-06-01",
                billTimeEnd: "2017-06-31",
                address: "黑龙江省 鸡西市 鸡冠区",
                area: "120平",
                checkInStatus: "入住",
                unit: "2号楼",
                chargeType: "电",
                openDate: "2016-10-10",
                pnType: "单项",
                pnBase: "2134455",
                billType: "单一制",
                payment: "预交费",
                paymentDeadline: "2017-07-12",
                balance: "0",
                comment: ""
            },
            {
                userName: "鄂听枫",
                userCode: "1470901303",
                idNo: "500241199412081706",
                mobile: "15515555539",
                wechat: "15515555539",
                contractNo: "15109961386198237973",
                billTimeStart: "2017-06-01",
                billTimeEnd: "2017-06-31",
                address: "河南省 平顶山市 湛河区",
                area: "120平",
                checkInStatus: "入住",
                unit: "1号楼",
                chargeType: "电",
                openDate: "2016-10-10",
                pnType: "单项",
                pnBase: "1424344",
                billType: "单一制",
                payment: "预交费",
                paymentDeadline: "2017-07-12",
                balance: "100",
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

    $("#btn-pn-tool-pn-config").linkbutton({
        onClick: function () {
            $("#dlg-pn-config").dialog("open");
        }
    });

    $("#btn-pn-tool-recharge").linkbutton({
        onClick: function () {
            $("#dlg-recharge").dialog("open");
        }
    });

    $("#btn-dlg-add-user-submit").linkbutton({
        onClick: function () {
            $("#dlg-add-user").dialog("close");
        }
    });

    $("#btn-dlg-pn-config-submit").linkbutton({
        onClick: function () {
            $("#dlg-pn-config").dialog("close");
        }
    });

    $("#btn-dlg-recharge-submit").linkbutton({
        onClick: function () {
            $("#dlg-recharge").dialog("close");
        }
    });
});