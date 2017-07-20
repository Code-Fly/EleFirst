/**
 * Created by barrie on 2017/7/17.
 */
$(document).ready(function () {
    $("#dgList_1").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [{
            userName: "王勇",
            userCode: "1890901601",
            idNo: "320680199412081208",
            period: "2017.5.1-2017.5.31",
            billType: "电",
            jijiaType: "单一制",
            kpType: "发票",
            payType: "预交费",
            amount: "960",
            price: "1",
            pay: "960",
            payId: "00001",
            operator: "xy",
            opTime: "2017.6.2",
            comment: ""
        }, {
            userName: "钱名",
            userCode: "1890901602",
            idNo: "320680198512091102",
            period: "2017.5.1-2017.5.31",
            billType: "电",
            jijiaType: "单一制",
            kpType: "发票",
            payType: "预交费",
            amount: "1100",
            price: "1",
            pay: "1100",
            payId: "00002",
            operator: "xy",
            opTime: "2017.6.2",
            comment: ""
        }, {
            userName: "刘明",
            userCode: "1890901607",
            idNo: "320680199412081209",
            period: "2017.5.1-2017.5.31",
            billType: "电",
            jijiaType: "单一制",
            kpType: "发票",
            payType: "现金后缴费",
            amount: "600",
            price: "1",
            pay: "600",
            payId: "00006",
            operator: "yz",
            opTime: "2017.6.2",
            comment: ""
        }, {
            userName: "王方",
            userCode: "1890901608",
            idNo: "320680199412081210",
            period: "2017.5.1-2017.5.31",
            billType: "电",
            jijiaType: "分时电价",
            kpType: "发票",
            payType: "现金后缴费",
            amount: "680",
            price: "",
            pay: "680",
            payId: "00008",
            operator: "yz",
            opTime: "2017.6.2",
            comment: ""
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            idNo: "320680199412081211",
            period: "2017.5.1-2017.5.31",
            billType: "电",
            jijiaType: "分时电价",
            kpType: "发票",
            payType: "预交费",
            amount: "180",
            price: "1",
            pay: "180",
            payId: "00009",
            operator: "xy",
            opTime: "2017.6.2",
            comment: ""
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            idNo: "320680199412081212",
            period: "2017.5.1-2017.5.31",
            billType: "电",
            jijiaType: "单一制",
            kpType: "发票",
            payType: "预交费",
            amount: "801",
            price: "1",
            pay: "801",
            payId: "00010",
            operator: "xy",
            opTime: "2017.6.2",
            comment: ""
        }, {
            userName: "高学峰",
            userCode: "1890901611",
            idNo: "320680199412081213",
            period: "2017.5.1-2017.5.31",
            billType: "电",
            jijiaType: "单一制",
            kpType: "发票",
            payType: "预交费",
            amount: "610",
            price: "1",
            pay: "610",
            payId: "00011",
            operator: "xy",
            opTime: "2017.6.2",
            comment: ""
        }]
    });

    $("#btn-pn-tool-search_1").linkbutton({
        onClick: function () {
            $("#cc1").layout("expand", "south")
        }
    });

});