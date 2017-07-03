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
            userName: "魏嘉志",
            userCode: "1890901601",
            idNo: "440901197709194316",
            mobile: "13987654321",
            wechat: "13987654321",
            contractNo: "15109961386041285971",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "四川省 甘孜藏族自治州 九龙县",
            area: "88平",
            checkInStatus: "入住",
            unit: "干货区",
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
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "黑龙江省 鸡西市 鸡冠区",
            area: "120平",
            checkInStatus: "入住",
            unit: "干货区",
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
            contractNo: "15109961386198237973",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "河南省 平顶山市 湛河区",
            area: "120平",
            checkInStatus: "入住",
            unit: "干货区",
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
            idNo: "500241199412081206",
            mobile: "15515556609",
            wechat: "15515556609",
            contractNo: "15109961386198237976",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "江苏省 南京市 鼓楼区",
            area: "120平",
            checkInStatus: "入住",
            unit: "干货区",
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
            idNo: "500241199412081208",
            mobile: "15515557709",
            wechat: "15515557709",
            contractNo: "15109961386198237977",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "江苏省 南京市 鼓楼区",
            area: "100平",
            checkInStatus: "入住",
            unit: "干货区",
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
            idNo: "500241199412081209",
            mobile: "15515558809",
            wechat: "15515558809",
            contractNo: "15109961386198237979",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "江苏省 南京市 鼓楼区",
            area: "100平",
            checkInStatus: "入住",
            unit: "干货区",
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
            idNo: "500241199412081210",
            mobile: "15515552209",
            wechat: "15515552209",
            contractNo: "15109961386198237980",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "江苏省 南京市 建邺区",
            area: "100平",
            checkInStatus: "入住",
            unit: "干货区",
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
            idNo: "500241199412081211",
            mobile: "13915552209",
            wechat: "13915552209",
            contractNo: "15109961386198237981",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "江苏省 南京市 秦淮区",
            area: "120平",
            checkInStatus: "入住",
            unit: "干货区",
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
            idNo: "500241199412081212",
            mobile: "13912662209",
            wechat: "13912662209",
            contractNo: "15109961386198237982",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "江苏省 南京市 鼓楼区",
            area: "120平",
            checkInStatus: "入住",
            unit: "干货区",
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
            idNo: "500241199412081213",
            mobile: "15012222091",
            wechat: "15012222091",
            contractNo: "15109961386198237983",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "江苏省 南京市 鼓楼区",
            area: "180平",
            checkInStatus: "入住",
            unit: "干货区",
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
            idNo: "500241199412081214",
            mobile: "13912266091",
            wechat: "13912266091",
            contractNo: "15109961386198237983",
            billTimeStart: "2017-06-01",
            billTimeEnd: "2017-06-31",
            address: "江苏省 南京市 鼓楼区",
            area: "180平",
            checkInStatus: "入住",
            unit: "干货区",
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
            userCode: "1890901612",
            idNo: "500241199412081215",
            mobile: "13913388091",
            wechat: "13913388091",
            contractNo: "15109961386198237984",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南京市 溧水区",
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
            userCode: "1890901613",
            idNo: "500241199412081216",
            mobile: "15013388091",
            wechat: "15013388091",
            contractNo: "15109961386198237985",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南京市 高淳区",
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
            userCode: "1890901614",
            idNo: "500241199412081217",
            mobile: "13914488091",
            wechat: "13914488091",
            contractNo: "15109961386198237986",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南京市 高淳区",
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
            userCode: "1890901615",
            idNo: "500241199412081218",
            mobile: "13912288091",
            wechat: "13912288091",
            contractNo: "15109961386198237986",
            billTimeStart: "2017-01-01",
            billTimeEnd: "2017-12-31",
            address: "江苏省 南京市 浦口区",
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

    $("#dgPnConfigList").datagrid({
        pagination: false,
        data: [{
            pnName: "1-1",
            pnAddr: "厂房开水炉/P1-11#",
            pnType: "单项",
            unit: "1号楼",
            comment: ""
        }, {
            pnName: "1-2",
            pnAddr: "检测楼一楼平面1AL/P2-12#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
        }, {
            pnName: "1-3",
            pnAddr: "检测楼一楼平面1AL/P2-13#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
        }, {
            pnName: "1-4",
            pnAddr: "检测楼一楼平面1AL/P2-14#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
        }, {
            pnName: "1-5",
            pnAddr: "检测楼一楼平面1AL/P2-15#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
        }, {
            pnName: "1-6",
            pnAddr: "检测楼一楼平面1AL/P2-16#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
        }, {
            pnName: "1-7",
            pnAddr: "检测楼一楼平面1AL/P2-17#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
        }, {
            pnName: "1-8",
            pnAddr: "检测楼一楼平面1AL/P2-18#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
        }, {
            pnName: "1-9",
            pnAddr: "检测楼一楼平面1AL/P2-19#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
        }, {
            pnName: "1-10",
            pnAddr: "检测楼一楼平面1AL/P2-20#",
            pnType: "单项",
            unit: "2号楼",
            comment: ""
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