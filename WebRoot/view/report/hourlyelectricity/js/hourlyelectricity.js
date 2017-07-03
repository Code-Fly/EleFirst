$(document).ready(function () {
	var _spinner = new Spinner();

    var data = $.base64.atob(decodeURIComponent(GetQueryString("data")), true);

    $("#startdate").datebox("calendar").calendar({
        firstDay: 1
    });
    
    //初始化databox为当前日期
    var d = new Date();
    d.setDate(d.getDate());
    var dateboxDate = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();
    $("#startdate").datebox("clear");
    $("#startdate").datebox("setValue", dateboxDate);
    
    var dgtt2 = $("#tt2").datagrid({
        url: _ctx + 'report/energy/list.do',
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        fitColumns: true,
        loadMsg: "正在加载...",
        border: false,
        queryParams: {
            date: $("#startdate").datebox("getValue")
        },
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
        	window.location.href = _ctx + "view/report/hourlyelectricity/report20170702164315.xls";
        }
     });
    
    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

