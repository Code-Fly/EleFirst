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
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "810",
            nhfy: "810",
            gtfy: "50",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "860",
            qfje: "0",
            zhye: "1100",
            comment: ""
        }, {
            userName: "钱名",
            userCode: "1890901602",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "1000",
            nhfy: "1000",
            gtfy: "100",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "1100",
            qfje: "0",
            zhye: "600",
            comment: ""
        }, {
            userName: "刘明",
            userCode: "1890901607",
            ynlx: "电",
            jfType: "后缴费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "310",
            nhfy: "310",
            gtfy: "30",
            yhje: "0",
            wyj: "0",
            ysfy: "340",
            zdkfje: "0",
            qfje: "0",
            zhye: "0",
            comment: ""
        }, {
            userName: "王方",
            userCode: "1890901608",
            ynlx: "电",
            jfType: "后缴费",
            jjlx: "分时电价",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "720",
            nhfy: "620",
            gtfy: "60",
            yhje: "0",
            wyj: "0",
            ysfy: "680",
            zdkfje: "0",
            qfje: "0",
            zhye: "0",
            comment: ""
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "分时电价",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "210",
            nhfy: "160",
            gtfy: "20",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "180",
            qfje: "0",
            zhye: "600",
            comment: ""
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "801",
            nhfy: "801",
            gtfy: "80",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "881",
            qfje: "0",
            zhye: "800",
            comment: ""
        }, {
            userName: "高学峰",
            userCode: "1890901611",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "610",
            nhfy: "610",
            gtfy: "60",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "670",
            qfje: "0",
            zhye: "900",
            comment: ""
        }, {
            userName: "葛明",
            userCode: "1890901612",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "分时电价",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "802",
            nhfy: "602",
            gtfy: "80",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "682",
            qfje: "0",
            zhye: "900",
            comment: ""
        }, {
            userName: "方亮",
            userCode: "1890901613",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "201",
            nhfy: "201",
            gtfy: "21",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "221",
            qfje: "0",
            zhye: "1000",
            comment: ""
        }, {
            userName: "王强",
            userCode: "1890901616",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "802",
            nhfy: "802",
            gtfy: "81",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "883",
            qfje: "0",
            zhye: "800",
            comment: ""
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "610",
            nhfy: "610",
            gtfy: "61",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "671",
            qfje: "0",
            zhye: "900",
            comment: ""
        }, {
            userName: "冯勤",
            userCode: "1890902211",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "910",
            nhfy: "910",
            gtfy: "90",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "1000",
            qfje: "0",
            zhye: "600",
            comment: ""
        }, {
            userName: "张远",
            userCode: "1390901612",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "210",
            nhfy: "210",
            gtfy: "20",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "230",
            qfje: "0",
            zhye: "800",
            comment: ""
        }, {
            userName: "王翔",
            userCode: "1890922611",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "610",
            nhfy: "610",
            gtfy: "60",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "670",
            qfje: "0",
            zhye: "900",
            comment: ""
        }, {
            userName: "王为",
            userCode: "18979166619",
            ynlx: "电",
            jfType: "预交费",
            jjlx: "单一制",
            gtfs: "按用量公摊",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynl: "880",
            nhfy: "880",
            gtfy: "81",
            yhje: "0",
            wyj: "0",
            ysfy: "0",
            zdkfje: "961",
            qfje: "0",
            zhye: "1000",
            comment: ""
        }]
    });


    $("#btn-pn-tool-add").linkbutton({
        onClick: function () {
            $("#dlg-add-user").dialog("open");
        }
    });

    $("#btn-pn-tool-chongzhi").linkbutton({
        onClick: function () {
            $("#dlg-chongzhi").dialog("open");
        }
    });

    $("#btn-dlg-add-user-submit").linkbutton({
        onClick: function () {
            $("#dlg-add-user").dialog("close");
        }
    });

    $("#btn-dlg-chongzhi-submit").linkbutton({
        onClick: function () {
            $("#dlg-chongzhi").dialog("close");
        }
    });

    $("#btn-pn-tool-search").linkbutton({
        onClick: function () {
            $("#cc1").layout("expand", "south")
        }
    });
});