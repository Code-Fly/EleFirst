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
            pay: "301.2",
            payId: "1470901301",
            operator: "admin",
            opTime: "2017-07-01",
            comment: ""
        }, {
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
            pay: "301.2",
            payId: "1470901301",
            operator: "admin",
            opTime: "2017-07-01",
            comment: ""
        }, {
            userName: "班旦",
            userCode: "1470901302",
            idNo: "230302197804086916",
            jiaofeiType: "-",
            period: "2017-06-28",
            billType: "水费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "20",
            price: "1.5",
            pay: "30",
            payId: "1470912301",
            operator: "admin",
            opTime: "2017-06-30",
            comment: ""
        }, {
            userName: "班旦",
            userCode: "1470901302",
            idNo: "230302197804086916",
            jiaofeiType: "-",
            period: "2017-06-28",
            billType: "水费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "20",
            price: "1.5",
            pay: "30",
            payId: "1470912301",
            operator: "admin",
            opTime: "2017-06-30",
            comment: ""
        }, {
            userName: "鄂听枫",
            userCode: "1470901303",
            idNo: "500241199412081706",
            jiaofeiType: "-",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "34.5",
            price: "3.22",
            pay: "111.09",
            payId: "1470901301",
            operator: "admin",
            opTime: "2017-07-02",
            comment: ""
        }, {
            userName: "鄂听枫",
            userCode: "1470901303",
            idNo: "500241199412081706",
            jiaofeiType: "-",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "34.5",
            price: "3.22",
            pay: "111.09",
            payId: "1470901301",
            operator: "admin",
            opTime: "2017-07-02",
            comment: ""
        }, {
            userName: "张远",
            userCode: "1890901604",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "水费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "2.62",
            owe: "262",
            penalty: "0",
            comment: ""
        }, {
            userName: "张斌",
            userCode: "1890901605",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "300",
            price: "1.6",
            owe: "480",
            penalty: "0",
            comment: ""
        }, {
            userName: "刘明",
            userCode: "1890901607",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "600",
            price: "1.6",
            owe: "960",
            penalty: "0",
            comment: ""
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "1.6",
            owe: "160",
            penalty: "0",
            comment: ""
        }, {
            userName: "王方",
            userCode: "1890901608",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "410",
            price: "1.6",
            owe: "656",
            penalty: "0",
            comment: ""
        }, {
            userName: "王方",
            userCode: "1890901608",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "410",
            price: "1.6",
            owe: "656",
            penalty: "0",
            comment: ""
        }, {
            userName: "高权",
            userCode: "1890901610",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "2.62",
            owe: "262",
            penalty: "0",
            comment: ""
        }, {
            userName: "高权",
            userCode: "1890901610",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "2.62",
            owe: "262",
            penalty: "0",
            comment: ""
        }]
    });

    $("#dgList_2").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [{
            userName: "魏嘉志",
            userCode: "1890901601",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "200",
            price: "1.6",
            owe: "320",
            penalty: "0",
            comment: ""
        }, {
            userName: "魏嘉志",
            userCode: "1890901601",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "水费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "2.62",
            owe: "262",
            penalty: "0",
            comment: ""
        }, {
            userName: "班旦",
            userCode: "1890901602",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "106.1",
            price: "3.22",
            owe: "12",
            penalty: "0",
            comment: ""
        }, {
            userName: "班旦",
            userCode: "1890901602",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "水费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "300",
            price: "2.62",
            owe: "786",
            penalty: "0",
            comment: ""
        }, {
            userName: "鄂听枫",
            userCode: "1890901603",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "600",
            price: "1.6",
            owe: "960",
            penalty: "0",
            comment: ""
        }, {
            userName: "张远",
            userCode: "1890901604",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "水费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "2.62",
            owe: "262",
            penalty: "0",
            comment: ""
        }, {
            userName: "张斌",
            userCode: "1890901605",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "300",
            price: "1.6",
            owe: "480",
            penalty: "0",
            comment: ""
        }, {
            userName: "刘明",
            userCode: "1890901607",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "600",
            price: "1.6",
            owe: "960",
            penalty: "0",
            comment: ""
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "1.6",
            owe: "160",
            penalty: "0",
            comment: ""
        }, {
            userName: "王方",
            userCode: "1890901608",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "410",
            price: "1.6",
            owe: "656",
            penalty: "0",
            comment: ""
        }, {
            userName: "高权",
            userCode: "1890901610",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "2.62",
            owe: "262",
            penalty: "0",
            comment: ""
        }, {
            userName: "高学峰",
            userCode: "1890901611",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "600",
            price: "1.6",
            owe: "960",
            penalty: "0",
            comment: ""
        }, {
            userName: "葛明峰",
            userCode: "1890901612",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "电费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "1.6",
            owe: "160",
            penalty: "0",
            comment: ""
        }, {
            userName: "葛明峰",
            userCode: "1890901612",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "水费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "800.5",
            price: "2.62",
            owe: "1662",
            penalty: "0",
            comment: ""
        }, {
            userName: "葛明峰",
            userCode: "1890901612",
            idNo: "440901197709194316",
            jiaofeiType: "后付费",
            period: "2017-06-28",
            billType: "气费",
            jijiaType: "单价 x 数量",
            kpType: "发票",
            payType: "现金",
            amount: "100",
            price: "2.6",
            owe: "260",
            penalty: "0",
            comment: ""
        }]
    });

    $("#dgList_3").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [{
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
        }]
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

    $("#btn-pn-tool-search_3").linkbutton({
        onClick: function () {
            $("#cc3").layout("expand", "south")
        }
    });
});