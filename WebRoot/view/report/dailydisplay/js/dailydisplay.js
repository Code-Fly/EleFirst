$(document).ready(function () {
    DateBoxUtils.initMonthBox($("#startdate"));
    //初始化databox为当前日期
    var d = new Date();
    var dateboxDate = d.getFullYear() + "-" + (d.getMonth() + 1) + "-01";
    $("#startdate").datebox("clear");
    $("#startdate").datebox("setValue", "2017-06");
    var dgtt2 = $("#tt2").datagrid({
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        loadMsg: "正在加载...",
        border: false,
        // url: _ctx + 'report/display/daily/list.do',
        // queryParams: {
        //     date: $("#startdate").datebox("getValue")
        // },
        data: [{
            name: "1-1",
            p00: "1723.35",
            p01: "1726.55",
            p02: "1729.35",
            p03: "1731.55",
            p04: "1734.75",
            p05: "1739.35",
            p06: "1745.35",
            p07: "1755.55",
            p08: "1757.35",
            p09: "1759.35",
            p10: "1761.55",
            p11: "1766.35",
            p12: "1768.55",
            p13: "1771.35",
            p14: "1775.55",
            p15: "1779.35",
            p16: "1782.55",
            p17: "1785.35",
            p18: "1788.35",
            p19: "1791.55",
            p20: "1793.35",
            p21: "1799.35",
            p22: "1809.55",
            p23: "1812.55",
            p24: "1820.55",
            p25: "1829.25",
            p26: "1839.55",
            p27: "1859.25",
            p28: "1869.55",
            p29: "1872.25",
            p30: "1875.25",
            p31: "1879.55"
        }, {
            name: "1-2",
            p00: "627.68",
            p01: "637.68",
            p02: "639.68",
            p03: "651.68",
            p04: "653.28",
            p05: "656.68",
            p06: "659.68",
            p07: "662.68",
            p08: "666.58",
            p09: "669.68",
            p10: "671.68",
            p11: "676.68",
            p12: "679.28",
            p13: "682.68",
            p14: "685.68",
            p15: "687.28",
            p16: "689.68",
            p17: "693.68",
            p18: "696.28",
            p19: "699.68",
            p20: "671.68",
            p21: "676.28",
            p22: "679.68",
            p23: "682.68",
            p24: "686.28",
            p25: "689.68",
            p26: "692.68",
            p27: "695.68",
            p28: "699.28",
            p29: "702.68",
            p30: "707.28",
            p31: "710.28"
        }, {
            name: "1-3",
            p00: "268.61",
            p01: "272.61",
            p02: "275.51",
            p03: "279.61",
            p04: "280.61",
            p05: "283.61",
            p06: "285.21",
            p07: "287.61",
            p08: "290.21",
            p09: "295.61",
            p10: "297.61",
            p11: "299.61",
            p12: "301.61",
            p13: "305.21",
            p14: "308.61",
            p15: "310.21",
            p16: "315.61",
            p17: "319.21",
            p18: "322.61",
            p19: "325.21",
            p20: "330.61",
            p21: "333.61",
            p22: "343.21",
            p23: "353.61",
            p24: "357.61",
            p25: "365.61",
            p26: "371.21",
            p27: "375.61",
            p28: "381.51",
            p29: "389.61",
            p30: "395.61",
            p31: "398.81"
        }, {
            name: "1-4",
            p00: "191.1",
            p01: "195.1",
            p02: "197.1",
            p03: "198.2",
            p04: "199.1",
            p05: "201.1",
            p06: "203.1",
            p07: "205.1",
            p08: "207.2",
            p09: "209.1",
            p10: "211.1",
            p11: "215.1",
            p12: "219.2",
            p13: "229.2",
            p14: "232.2",
            p15: "235.2",
            p16: "238.2",
            p17: "241.2",
            p18: "245.1",
            p19: "248.1",
            p20: "251.2",
            p21: "253.2",
            p22: "255.1",
            p23: "259.2",
            p24: "269.2",
            p25: "271.2",
            p26: "279.2",
            p27: "281.1",
            p28: "286.2",
            p29: "288.2",
            p30: "292.2",
            p31: "296.2"
        }, {
            name: "1-5",
            p00: "1011.88",
            p01: "1016.28",
            p02: "1018.88",
            p03: "1021.28",
            p04: "1025.88",
            p05: "1029.88",
            p06: "1051.28",
            p07: "1055.28",
            p08: "1059.28",
            p09: "1061.68",
            p10: "1065.28",
            p11: "1068.28",
            p12: "1071.28",
            p13: "1076.28",
            p14: "1078.28",
            p15: "1081.68",
            p16: "1085.28",
            p17: "1088.28",
            p18: "1091.28",
            p19: "1095.28",
            p20: "1097.68",
            p21: "1099.28",
            p22: "1101.28",
            p23: "1111.28",
            p24: "1116.28",
            p25: "1119.28",
            p26: "1201.28",
            p27: "1205.28",
            p28: "1208.28",
            p29: "1209.28",
            p30: "1301.28",
            p31: "1305.28"
        }, {
            name: "1-6",
            p00: "601.6",
            p01: "605.6",
            p02: "609.6",
            p03: "611.6",
            p04: "616.6",
            p05: "619.6",
            p06: "621.6",
            p07: "626.9",
            p08: "629.6",
            p09: "635.6",
            p10: "639.5",
            p11: "645.6",
            p12: "651.9",
            p13: "655.6",
            p14: "659.6",
            p15: "661.6",
            p16: "666.6",
            p17: "669.2",
            p18: "672.9",
            p19: "676.6",
            p20: "679.2",
            p21: "681.6",
            p22: "685.6",
            p23: "688.6",
            p24: "690.2",
            p25: "692.6",
            p26: "695.6",
            p27: "699.2",
            p28: "671.6",
            p29: "676.2",
            p30: "679.6",
            p31: "681.2"
        }, {
            name: "1-7",
            p00: "801.2",
            p01: "802.2",
            p02: "805.5",
            p03: "807.2",
            p04: "808.2",
            p05: "810.6",
            p06: "812.9",
            p07: "816.2",
            p08: "819.2",
            p09: "821.2",
            p10: "825.2",
            p11: "828.6",
            p12: "831.5",
            p13: "835.2",
            p14: "842.5",
            p15: "851.2",
            p16: "855.6",
            p17: "860.2",
            p18: "862.2",
            p19: "865.2",
            p20: "868.2",
            p21: "871.2",
            p22: "876.2",
            p23: "878.2",
            p24: "881.2",
            p25: "886.2",
            p26: "888.2",
            p27: "891.2",
            p28: "896.2",
            p29: "899.6",
            p30: "901.2",
            p31: "906.2"
        }, {
            name: "1-8",
            p00: "201.2",
            p01: "205.2",
            p02: "207.6",
            p03: "209.2",
            p04: "211.2",
            p05: "212.2",
            p06: "216.2",
            p07: "218.6",
            p08: "221.2",
            p09: "226.2",
            p10: "228.2",
            p11: "231.2",
            p12: "235.2",
            p13: "238.2",
            p14: "241.2",
            p15: "245.2",
            p16: "251.2",
            p17: "253.6",
            p18: "256.2",
            p19: "258.2",
            p20: "261.2",
            p21: "262.2",
            p22: "266.6",
            p23: "268.2",
            p24: "271.2",
            p25: "276.2",
            p26: "278.2",
            p27: "279.6",
            p28: "281.2",
            p29: "286.2",
            p30: "291.2",
            p31: "297.2"
        }, {
            name: "1-9",
            p00: "701.2",
            p01: "705.2",
            p02: "706.6",
            p03: "708.2",
            p04: "709.2",
            p05: "711.2",
            p06: "715.2",
            p07: "717.2",
            p08: "719.2",
            p09: "721.6",
            p10: "726.2",
            p11: "729.8",
            p12: "731.2",
            p13: "735.2",
            p14: "738.2",
            p15: "741.2",
            p16: "746.7",
            p17: "750.2",
            p18: "751.2",
            p19: "756.8",
            p20: "761.2",
            p21: "766.2",
            p22: "769.2",
            p23: "771.2",
            p24: "776.9",
            p25: "781.2",
            p26: "786.2",
            p27: "788.2",
            p28: "791.2",
            p29: "795.2",
            p30: "798.2",
            p31: "799.2"
        }, {
            name: "1-10",
            p00: "1202.6",
            p01: "1205.6",
            p02: "1207.6",
            p03: "1209.6",
            p04: "1212.2",
            p05: "1216.6",
            p06: "1219.6",
            p07: "1222.6",
            p08: "1226.6",
            p09: "1228.6",
            p10: "1229.6",
            p11: "1232.6",
            p12: "1235.8",
            p13: "1238.6",
            p14: "1242.6",
            p15: "1247.6",
            p16: "1252.6",
            p17: "1255.2",
            p18: "1259.6",
            p19: "1262.6",
            p20: "1265.6",
            p21: "1269.1",
            p22: "1272.6",
            p23: "1276.9",
            p24: "1279.6",
            p25: "1282.6",
            p26: "1286.6",
            p27: "1292.6",
            p28: "1295.6",
            p29: "1297.6",
            p30: "1298.6",
            p31: "1299.6"
        }, {
            name: "1-11",
            p00: "1712.1",
            p01: "1716.6",
            p02: "1718.1",
            p03: "1719.1",
            p04: "1722.1",
            p05: "1726.8",
            p06: "1729.1",
            p07: "1732.1",
            p08: "1735.8",
            p09: "1737.1",
            p10: "1756.1",
            p11: "1759.1",
            p12: "1762.1",
            p13: "1766.1",
            p14: "1768.1",
            p15: "1769.1",
            p16: "1772.1",
            p17: "1775.1",
            p18: "1776.1",
            p19: "1779.2",
            p20: "1782.1",
            p21: "1786.1",
            p22: "1788.1",
            p23: "1789.1",
            p24: "1791.1",
            p25: "1792.1",
            p26: "1795.1",
            p27: "1797.1",
            p28: "1799.2",
            p29: "1802.1",
            p30: "1806.1",
            p31: "1809.1"
        }, {
            name: "1-12",
            p00: "1502.6",
            p01: "1505.6",
            p02: "1508.6",
            p03: "1512.2",
            p04: "1516.6",
            p05: "1519.6",
            p06: "1522.6",
            p07: "1526.6",
            p08: "1529.2",
            p09: "1532.6",
            p10: "1535.8",
            p11: "1555.6",
            p12: "1558.6",
            p13: "1559.6",
            p14: "1562.6",
            p15: "1566.2",
            p16: "1569.6",
            p17: "1572.6",
            p18: "1575.6",
            p19: "1577.6",
            p20: "1579.8",
            p21: "1582.6",
            p22: "1586.6",
            p23: "1588.6",
            p24: "1589.6",
            p25: "1592.8",
            p26: "1596.6",
            p27: "1599.6",
            p28: "1602.2",
            p29: "1606.6",
            p30: "1609.6",
            p31: "1612.2"
        }, {
            name: "1-13",
            p00: "901.1",
            p01: "905.1",
            p02: "906.6",
            p03: "907.1",
            p04: "909.1",
            p05: "911.1",
            p06: "912.1",
            p07: "916.1",
            p08: "918.1",
            p09: "921.1",
            p10: "922.7",
            p11: "926.1",
            p12: "928.1",
            p13: "931.1",
            p14: "935.1",
            p15: "937.1",
            p16: "951.1",
            p17: "952.1",
            p18: "955.6",
            p19: "956.1",
            p20: "959.1",
            p21: "961.2",
            p22: "966.1",
            p23: "967.1",
            p24: "969.1",
            p25: "971.2",
            p26: "976.1",
            p27: "977.1",
            p28: "979.1",
            p29: "981.1",
            p30: "986.1",
            p31: "990.1"
        }, {
            name: "1-14",
            p00: "801.2",
            p01: "802.2",
            p02: "805.5",
            p03: "807.2",
            p04: "808.2",
            p05: "810.6",
            p06: "812.9",
            p07: "816.2",
            p08: "819.2",
            p09: "821.2",
            p10: "825.2",
            p11: "828.6",
            p12: "831.5",
            p13: "835.2",
            p14: "842.5",
            p15: "851.2",
            p16: "855.6",
            p17: "860.2",
            p18: "862.2",
            p19: "865.2",
            p20: "868.2",
            p21: "871.2",
            p22: "876.2",
            p23: "878.2",
            p24: "881.2",
            p25: "886.2",
            p26: "888.2",
            p27: "891.2",
            p28: "896.2",
            p29: "899.6",
            p30: "901.2",
            p31: "906.2"
        }, {
            name: "1-15",
            p00: "1260.1",
            p01: "1261.2",
            p02: "1263.1",
            p03: "1265.1",
            p04: "1266.1",
            p05: "1267.1",
            p06: "1268.2",
            p07: "1269.1",
            p08: "1271.1",
            p09: "1272.1",
            p10: "1276.1",
            p11: "1277.1",
            p12: "1278.1",
            p13: "1279.2",
            p14: "1281.1",
            p15: "1282.1",
            p16: "1283.1",
            p17: "1286.1",
            p18: "1287.6",
            p19: "1289.1",
            p20: "1291.1",
            p21: "1292.2",
            p22: "1296.1",
            p23: "1297.1",
            p24: "1298.1",
            p25: "1299.1",
            p26: "1301.1",
            p27: "1302.1",
            p28: "1306.1",
            p29: "1307.1",
            p30: "1309.1",
            p31: "1319.1"
        }],


        onLoadError: function () {
            jError("查询日电量信息失败", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
        }
    });

    $("#btn-detail-search").linkbutton({
        onClick: function () {
            if (!$("#startdate").datebox("isValid")) {
                $.messager.alert("信息提示", "请选择时间！", "info");
                return;
            }
            dgtt2.datagrid('load', {
                date: $("#startdate").datebox("getValue")
            });
        }
    });

    $("#exportexcel").linkbutton({
        onClick: function () {
            window.location.href = _ctx + "view/report/dailydisplay/report20170703201737.xls";
        }
    });


    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

