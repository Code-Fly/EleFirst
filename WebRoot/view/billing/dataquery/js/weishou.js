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
            userName: "张远",
            userCode: "1390901612",
            ynlx: "电",
            lxfs: "13915566096",
            jzmj: "100平",
            qfze: "100",
            wyj: "10",
            zhye: "0",
            czr: "x",
            czrq: "2017-02-02"
        }]
    });

    $("#dgList_2").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [{
            userName: "张远",
            userCode: "1390901612",
            lxfs: "13915566096",
            jzmj: "100平",
            qfze: "100",
            wyj: "10",
            bl: "60",
            sl: "100",
            kssj: "2017-01-01",
            jssj: "2017-03-01",
            sh: "100",
            zhye: "0"
        }]
    });

    $("#dgList_3").datagrid({
        pagination: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        data: [{
            userName: "张远",
            userCode: "1390901612",
            lxfs: "13915566096",
            kssj: "2017-01-01",
            jssj: "2017-03-01",
            zhye: "0"
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