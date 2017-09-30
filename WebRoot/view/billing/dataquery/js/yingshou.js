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
            ysje: "860",
            ssje: "860",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "1100",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "钱名",
            userCode: "1890901602",
            ysje: "1100",
            ssje: "1100",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "600",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "刘明",
            userCode: "1890901607",
            ysje: "340",
            ssje: "340",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "0",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "王方",
            userCode: "1890901608",
            ysje: "680",
            ssje: "680",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "0",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            ysje: "180",
            ssje: "180",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "600",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            ysje: "881",
            ssje: "881",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "800",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "高学峰",
            userCode: "1890901611",
            ysje: "670",
            ssje: "670",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "900",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "葛明",
            userCode: "1890901612",
            ysje: "682",
            ssje: "682",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "900",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "方亮",
            userCode: "1890901613",
            ysje: "221",
            ssje: "221",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "1000",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "王强",
            userCode: "1890901616",
            ysje: "883",
            ssje: "883",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "800",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            ysje: "671",
            ssje: "671",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "900",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "冯勤",
            userCode: "1890902211",
            ysje: "1000",
            ssje: "1000",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "600",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "张远",
            userCode: "1390901612",
            ysje: "230",
            ssje: "230",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "800",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "王翔",
            userCode: "1890922611",
            ysje: "670",
            ssje: "670",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "900",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "王为",
            userCode: "18979166619",
            ysje: "961",
            ssje: "961",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "1000",
            czr: "xy",
            czrq: "2017.2.2"
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
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "860",
            ssje: "860",
            wsje: "0",
            wyj: "0",
            yh: "0",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "钱名",
            userCode: "1890901602",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "1100",
            ssje: "1100",
            wsje: "0",
            wyj: "0",
            yh: "0",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "刘明",
            userCode: "1890901607",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "340",
            ssje: "340",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "0",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "王方",
            userCode: "1890901608",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "680",
            ssje: "680",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "0",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "孙凡",
            userCode: "1890901609",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "180",
            ssje: "180",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "600",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "881",
            ssje: "881",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "800",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "高学峰",
            userCode: "1890901611",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "670",
            ssje: "670",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "900",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "葛明",
            userCode: "1890901612",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "682",
            ssje: "682",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "900",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "方亮",
            userCode: "1890901613",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "221",
            ssje: "221",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "1000",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "王强",
            userCode: "1890901616",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "883",
            ssje: "883",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "800",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "孔亮",
            userCode: "1890901610",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "671",
            ssje: "671",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "900",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "冯勤",
            userCode: "1890902211",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "1000",
            ssje: "1000",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "600",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "张远",
            userCode: "1390901612",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "230",
            ssje: "230",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "800",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "王翔",
            userCode: "1890922611",
            sfxm: "电",
            kssj: "2017.1.1",
            jssj: "2017.1.31",
            ysje: "670",
            ssje: "670",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "900",
            czr: "xy",
            czrq: "2017.2.2"
        }, {
            userName: "王为",
            userCode: "18979166619",
            ysje: "961",
            ssje: "961",
            wsje: "0",
            wyj: "0",
            yh: "0",
            zhye: "1000",
            czr: "xy",
            czrq: "2017.2.2"
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

});