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
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "班旦",
            userCode: "1890901602",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "450",
            bccbss: "550",
            ynl: "100",
            dj: "2.0",
            nhfy: "200",
            gtfy: "15",
            zfy: "215",
            zhye: "0",
            comment: ""
        }, {
            userName: "鄂听枫",
            userCode: "1890901603",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "650",
            bccbss: "950",
            ynl: "300",
            dj: "2.0",
            nhfy: "600",
            gtfy: "15",
            zfy: "615",
            zhye: "0",
            comment: ""
        }, {
            userName: "张远",
            userCode: "1890901604",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "张斌",
            userCode: "1890901605",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "850",
            ynl: "500",
            dj: "2.0",
            nhfy: "1000",
            gtfy: "15",
            zfy: "1015",
            zhye: "0",
            comment: ""
        }, {
            userName: "刘明",
            userCode: "1890901607",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "750",
            ynl: "400",
            dj: "2.0",
            nhfy: "800",
            gtfy: "15",
            zfy: "815",
            zhye: "0",
            comment: ""
        }, {
            userName: "王方",
            userCode: "1890901608",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "高权",
            userCode: "1890901610",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "高学峰",
            userCode: "1890901611",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "葛明峰",
            userCode: "1890901612",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "方晓海",
            userCode: "1890901612",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "王强",
            userCode: "1890901613",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "冯小强",
            userCode: "1890901614",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
            comment: ""
        }, {
            userName: "付飞",
            userCode: "1890901615",
            jfType: "后付费",
            jfzq: "1个月",
            jnqj: "2017.1.1-2017.1.31",
            ynlx: "水费",
            jjlx: "单一制",
            gtfs: "按面积公摊公用",
            sccbss: "350",
            bccbss: "550",
            ynl: "200",
            dj: "2.0",
            nhfy: "400",
            gtfy: "15",
            zfy: "415",
            zhye: "0",
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

});