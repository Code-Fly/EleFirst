/**
 * Created by barrie on 2017/7/2.
 */
$(document).ready(function () {
    $("#dgList_1").datagrid({
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

    $("#dgList_2").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [
            {
                userName: "魏嘉志",
                userCode: "1470901301",
                idNo: "440901197709194316",
                jiaofeiType: "-",
                period: "2017-06-28",
                billType: "电费",
                jijiaType: "单价 x 数量",
                kpType: "发票",
                payType: "现金",
                amount: "93.5",
                price: "3.22",
                owe: "12",
                penalty: "33",
                comment: ""
            }
        ]
    });

    $("#dgList_3").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [
            {
                userName: "班旦",
                userCode: "1470901302",
                idNo: "230302197804086916",
                jiaofeiType: "-",
                period: "2017-06-28",
                billType: "水费",
                yingshou: "225.3",
                weishou: "0",
                shishou: "225.3",
                chongxiao: "0",
                owe: "0",
                youhui: "0",
                operator: "admin",
                opTime: "2017-07-01",
                comment: ""
            }
        ]
    });

    $("#btn-pn-tool-setting").linkbutton({
        onClick: function () {
            $("#dlg-remote-setting").dialog("open")
        }
    });

    $("#btn-dlg-remote-setting-submit").linkbutton({
        onClick: function () {
            $("#dlg-remote-setting").dialog("close")
        }
    });
});