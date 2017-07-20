/**
 * Created by barrie on 2017/7/2.
 */
$(document).ready(function () {
    $("#dgList_1").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [{
            userName: "魏嘉志",
            userCode: "1890901601",
            idNo: "440901197709194316",
            mobile: "13987654321",
            wechat: "13987654321",
            contractNo: "15109961386041285971",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "四川省 甘孜藏族自治州 九龙县",
            area: "88平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1876234",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "0",
            comment: ""
        }, {
            userName: "班旦",
            userCode: "1890901602",
            idNo: "230302197804086916",
            mobile: "15020000314",
            wechat: "15020000314",
            contractNo: "15109961386198237973",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "黑龙江省 鸡西市 鸡冠区",
            area: "120平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "2134455",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "0",
            comment: ""
        }, {
            userName: "鄂听枫",
            userCode: "1890901603",
            idNo: "500241199412081706",
            mobile: "15515555539",
            wechat: "15515555539",
            contractNo: "15109961386198237979",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "河南省 平顶山市 湛河区",
            area: "120平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424344",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "张远",
            userCode: "1890901604",
            idNo: "320121199412081206",
            mobile: "15515556609",
            wechat: "15515556609",
            contractNo: "15109961386198237976",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南京市 鼓楼区",
            area: "120平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424344",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "张斌",
            userCode: "1890901605",
            idNo: "320680199412081208",
            mobile: "15515557709",
            wechat: "15515557709",
            contractNo: "15109961386198237977",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 港闸区",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
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
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "王方",
            userCode: "1890901608",
            idNo: "320680199412081210",
            mobile: "15515552209",
            wechat: "15515552209",
            contractNo: "15109961386198237981",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 港闸区",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            idNo: "320680199412081211",
            mobile: "13915552209",
            wechat: "13915552209",
            contractNo: "15109961386198237982",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海安县",
            area: "120平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "高权",
            userCode: "1890901610",
            idNo: "320680199412081212",
            mobile: "13912662209",
            wechat: "13912662209",
            contractNo: "15109961386198237983",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海安县",
            area: "120平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
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
            area: "180平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "葛明峰",
            userCode: "1890901612",
            idNo: "320680199412081214",
            mobile: "13912266091",
            wechat: "13912266091",
            contractNo: "15109961386198237985",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 通州市",
            area: "180平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "方晓海",
            userCode: "1890901613",
            idNo: "320680199412081215",
            mobile: "13913388091",
            wechat: "13913388091",
            contractNo: "15109961386198237986",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 通州市",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "王强",
            userCode: "1890901614",
            idNo: "320680199412081216",
            mobile: "15013388091",
            wechat: "15013388091",
            contractNo: "15109961386198237987",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海门市",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "冯小强",
            userCode: "1890901615",
            idNo: "320680199412081217",
            mobile: "13914488091",
            wechat: "13914488091",
            contractNo: "15109961386198237988",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 海门市",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }, {
            userName: "付飞",
            userCode: "1890901616",
            idNo: "320680199412081218",
            mobile: "13912288091",
            wechat: "13912288091",
            contractNo: "15109961386198237990",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南通市 崇川区",
            area: "100平",
            checkInStatus: "入住",
            unit: "水产区",
            chargeType: "电",
            openDate: "2016-10-10",
            pnType: "单项",
            pnBase: "1424355",
            billType: "单一制",
            payment: "预交费",
            paymentDeadline: "2017-07-12",
            balance: "100",
            comment: ""
        }]
    });

    $("#dgList_2").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [{
            userName: "王勇",
            userCode: "1890901601",
            bjlx: "单费率",
            bjdz: "bj021201",
            ssdy: "水产区",
            zhye: "2000",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "钱名",
            userCode: "1890901602",
            bjlx: "单费率",
            bjdz: "bj021206",
            ssdy: "水产区",
            zhye: "1000",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "刘明",
            userCode: "1890901607",
            bjlx: "单费率",
            bjdz: "bj061201",
            ssdy: "干货区",
            zhye: "0",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "王方",
            userCode: "1890901608",
            bjlx: "多费率",
            bjdz: "bj021209",
            ssdy: "干货区",
            zhye: "0",
            qfye: "100",
            kzzt: "跳闸",
            comment: ""

        }, {
            userName: "孙凡",
            userCode: "1890901609",
            bjlx: "多费率",
            bjdz: "bj091201",
            ssdy: "干货区",
            zhye: "1000",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "孔亮",
            userCode: "1890901610",
            bjlx: "单费率",
            bjdz: "bj061609",
            ssdy: "水产区",
            zhye: "600",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "高学峰",
            userCode: "1890901611",
            bjlx: "单费率",
            bjdz: "bj161629",
            ssdy: "水产区",
            zhye: "200",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "葛明",
            userCode: "1890901612",
            bjlx: "多费率",
            bjdz: "bj861699",
            ssdy: "水产区",
            zhye: "1000",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "方亮",
            userCode: "1890901613",
            bjlx: "单费率",
            bjdz: "bj161609",
            ssdy: "干货区",
            zhye: "800",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "王强",
            userCode: "1890901616",
            bjlx: "单费率",
            bjdz: "bj661601",
            ssdy: "干货区",
            zhye: "200",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "孔亮",
            userCode: "1890901610",
            bjlx: "单费率",
            bjdz: "bj661602",
            ssdy: "干货区",
            zhye: "600",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "冯勤",
            userCode: "1890902211",
            bjlx: "单费率",
            bjdz: "bj661606",
            ssdy: "干货区",
            zhye: "800",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "张远",
            userCode: "1390901612",
            bjlx: "单费率",
            bjdz: "bj661607",
            ssdy: "干货区",
            zhye: "200",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "王翔",
            userCode: "1890922611",
            bjlx: "单费率",
            bjdz: "bj661609",
            ssdy: "干货区",
            zhye: "800",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }, {
            userName: "王为",
            userCode: "18979166619",
            bjlx: "单费率",
            bjdz: "bj661610",
            ssdy: "干货区",
            zhye: "900",
            qfye: "0",
            kzzt: "合闸",
            comment: ""

        }]
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

    $("#btn-pn-tool-search_1").linkbutton({
        onClick: function () {
            $("#cc1").layout("expand", "south")
        }
    });

    $("#btn-pn-tool-search_2").linkbutton({
        onClick: function () {
            $("#cc2").layout("expand", "south")
        }
    });
});