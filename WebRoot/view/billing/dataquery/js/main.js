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
            },
            {
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
            },
            {
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