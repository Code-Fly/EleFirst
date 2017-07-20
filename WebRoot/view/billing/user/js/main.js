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
            userName: "王勇",
            userCode: "1890901601",
            idNo: "320680199412081208",
            mobile: "15020000315",
            wechat: "wy0016",
            contractNo: "15109961386198237973",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 港闸区",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2017-06-01",
            payment: "预交费",
            comment: ""
        }, {
            userName: "钱名",
            userCode: "1890901602",
            idNo: "320680198512091102",
            mobile: "15026660013",
            wechat: "zb8980",
            contractNo: "15109961386198238081",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 港闸区",
            area: "90平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2017-05-01",
            payment: "预交费",
            comment: ""
        }, {
            userName: "刘明",
            userCode: "1890901607",
            idNo: "320680199412081209",
            mobile: "15515558809",
            wechat: "15515558809",
            contractNo: "15109961386198237980",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 港闸区",
            area: "100平",
            checkInStatus: "入住",
            unit: "干货区",
            chargeType: "电",
            openDate: "2017-02-01",
            payment: "后缴费",
            comment: ""
        }, {
            userName: "王方",
            userCode: "1890901608",
            idNo: "320680199412081210",
            mobile: "13915552209",
            wechat: "13915552209",
            contractNo: "15109961386198237981",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 港闸区",
            area: "90平",
            checkInStatus: "入住",
            unit: "干货区",
            chargeType: "电",
            openDate: "2017-02-01",
            payment: "后缴费",
            comment: ""
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            idNo: "320680199412081211",
            mobile: "13915559909",
            wechat: "13915559909",
            contractNo: "15109961386198237982",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海安县",
            area: "190平",
            checkInStatus: "入住",
            unit: "干货区",
            chargeType: "电",
            openDate: "2017-01-06",
            payment: "预交费",
            comment: ""
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            idNo: "320680199412081212",
            mobile: "13912662209",
            wechat: "13912662209",
            contractNo: "15109961386198237983",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海安县",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2017-02-06",
            payment: "预交费",
            comment: ""
        }, {
            userName: "高学峰",
            userCode: "1890901611",
            idNo: "320680199412081213",
            mobile: "15012222091",
            wechat: "15012222091",
            contractNo: "15109961386198237984",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 如东县",
            area: "70平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2017-02-06",
            payment: "预交费",
            comment: ""
        }, {
            userName: "葛明",
            userCode: "1890901612",
            idNo: "320680199412081219",
            mobile: "13912266092",
            wechat: "13912266092",
            contractNo: "15109961386198237985",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 通州市",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2017-02-06",
            payment: "预交费",
            comment: ""
        }, {
            userName: "方亮",
            userCode: "1890901613",
            idNo: "320680199412081215",
            mobile: "13913388091",
            wechat: "13913388091",
            contractNo: "15109961386198237986",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 通州市",
            area: "160平",
            checkInStatus: "入住",
            unit: "干货区",
            chargeType: "电",
            openDate: "2017-06-01",
            payment: "预交费",
            comment: ""
        }, {
            userName: "王强",
            userCode: "1890901616",
            idNo: "320680199412081216",
            mobile: "15016688092",
            wechat: "15016688092",
            contractNo: "15109961386198237987",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海门市",
            area: "100平",
            checkInStatus: "入住",
            unit: "干货区",
            chargeType: "电",
            openDate: "2017-01-01",
            payment: "预交费",
            comment: ""
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            idNo: "320680199412081212",
            mobile: "13912662269",
            wechat: "13912662269",
            contractNo: "15109961386198237983",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海安县",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2017-02-06",
            payment: "预交费",
            comment: ""
        }, {
            userName: "冯勤",
            userCode: "1890902211",
            idNo: "320680199712061216",
            mobile: "15012226691",
            wechat: "15012226691",
            contractNo: "15109961386198237991",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 如东县",
            area: "70平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2017-02-06",
            payment: "预交费",
            comment: ""
        }, {
            userName: "张远",
            userCode: "1390901612",
            idNo: "320680197612081217",
            mobile: "13915566096",
            wechat: "13915566096",
            contractNo: "15109961386198237992",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 通州市",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2017-02-06",
            payment: "预交费",
            comment: ""
        }, {
            userName: "王翔",
            userCode: "1890922611",
            idNo: "320680197912081218",
            mobile: "13913322091",
            wechat: "13913322091",
            contractNo: "15109961386198237993",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 通州市",
            area: "160平",
            checkInStatus: "入住",
            unit: "干货区",
            chargeType: "电",
            openDate: "2017-06-01",
            payment: "预交费",
            comment: ""
        }, {
            userName: "王为",
            userCode: "18979166619",
            idNo: "320680198612081219",
            mobile: "15012188092",
            wechat: "15012188092",
            contractNo: "15109961386198237999",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海门市",
            area: "100平",
            checkInStatus: "入住",
            unit: "干货区",
            chargeType: "电",
            openDate: "2017-01-01",
            payment: "预交费",
            comment: ""
        }]
    });

    $("#dgPnConfigList").datagrid({
        pagination: false,
        data: [{
            pnName: "干货区表1",
            pnAddr: "bj110101",
            pnType: "单费率",
            total: "980",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表2",
            pnAddr: "bj110102",
            pnType: "单费率",
            total: "680",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表3",
            pnAddr: "bj110103",
            pnType: "单费率",
            total: "202",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表4",
            pnAddr: "bj110104",
            pnType: "单费率",
            total: "610",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表5",
            pnAddr: "bj110105",
            pnType: "单费率",
            total: "280",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表6",
            pnAddr: "bj110106",
            pnType: "多费率",
            total: "661",
            rate1: "200",
            rate2: "101",
            rate3: "200",
            rate4: "160",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表7",
            pnAddr: "bj110107",
            pnType: "单费率",
            total: "620",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表8",
            pnAddr: "bj110108",
            pnType: "单费率",
            total: "208",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表9",
            pnAddr: "bj110109",
            pnType: "单费率",
            total: "690",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }, {
            pnName: "干货区表10",
            pnAddr: "bj110110",
            pnType: "单费率",
            total: "712",
            rate1: "",
            rate2: "",
            rate3: "",
            rate4: "",
            unit: "干货区",
            comment: ""

        }]
    });

    $("#combo-charge-type").combobox({
        data: [{
            "value": 1,
            "text": "电",
            "selected": true
        }, {
            "value": 2,
            "text": "水",
            "selected": true
        }, {
            "value": 3,
            "text": "气",
            "selected": true
        }, {
            "value": 4,
            "text": "热"
        }]
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