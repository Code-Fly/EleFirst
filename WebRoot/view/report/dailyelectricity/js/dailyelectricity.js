$(document).ready(function () {
	DateBoxUtils.initMonthBox($("#startdate"));
	//初始化databox为当前日期
    var d = new Date();
    var dateboxDate = d.getFullYear() + "-" + (d.getMonth()+1) + "-01";
    $("#startdate").datebox("clear");
    $("#startdate").datebox("setValue", dateboxDate);
    var dgtt2 = $("#tt2").datagrid({
        // url: _ctx + 'report/electricity/daily/list.do',
        // queryParams: {
        //     date: $("#startdate").datebox("getValue")
        // },
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        loadMsg: "正在加载...",
        border: false,
        data: [{
            name: "1-1",
            total: "100",
            p00: "1",
            p01: "1",
            p02: "1",
            p03: "1",
            p04: "1",
            p05: "1",
            p06: "1",
            p07: "1",
            p08: "1",
            p09: "1",
            p10: "1",
            p11: "1",
            p12: "1",
            p13: "1",
            p14: "1",
            p15: "1",
            p16: "1",
            p17: "1",
            p18: "1",
            p19: "1",
            p20: "1",
            p21: "1",
            p22: "1",
            p23: "1",
            p24: "1",
            p25: "1",
            p26: "1",
            p27: "1",
            p28: "1",
            p29: "1",
            p30: "1",
            p31: "1"
        }
        ],
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
            dgtt2.datagrid('load',{
				date: $("#startdate").datebox("getValue")
			});
         }
     });
     
     $("#exportexcel").linkbutton({
        onClick: function () {
        	window.location.href = _ctx + "view/report/dailyelectricity/report20170703204120.xls";
        }
     });
    
    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

