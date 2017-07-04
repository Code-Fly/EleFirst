$(document).ready(function () {

    $("#tt").datagrid({
        // url: _ctx + 'report/display/monthly/list.do',
        pagination: true,
        rownumbers: true,
        pageSize: DEFAULT_PAGE_SIZE,
        pageList: DEFAULT_PAGE_LIST,
        pageNumber: 1,
        singleSelect: true,
        fit: true,
        fitColumns: true,
        loadMsg: "正在加载...",
        data: [{
            date: "2017-06-01",
            display: "1553.75",
            olddisplay: "1550.00",
            displaydiffer: "3.75",
            override: "60",
            power: "3.75"
        }, {
            date: "2017-06-02",
            display: "1561.75",
            olddisplay: "1553.75",
            displaydiffer: "8.0",
            override: "50",
            power: "8.0"
        }, {
            date: "2017-06-03",
            display: "1566.85",
            olddisplay: "1561.75",
            displaydiffer: "5.10",
            override: "60",
            power: "5.10"
        }, {
            date: "2017-06-04",
            display: "1569.95",
            olddisplay: "1566.85",
            displaydiffer: "3.10",
            override: "50",
            power: "3.10"
        }, {
            date: "2017-06-05",
            display: "1575.95",
            olddisplay: "1569.95",
            displaydiffer: "6.0",
            override: "60",
            power: "6.0"
        }, {
            date: "2017-06-06",
            display: "1582.95",
            olddisplay: "1575.95",
            displaydiffer: "7",
            override: "50",
            power: "7"
        }, {
            date: "2017-06-07",
            display: "1587.95",
            olddisplay: "1582.95",
            displaydiffer: "5",
            override: "60",
            power: "1"
        }, {
            date: "2017-06-08",
            display: "1595.05",
            olddisplay: "1587.95",
            displaydiffer: "7.05",
            override: "50",
            power: "7.05"
        }, {
            date: "2017-06-09",
            display: "1601.05",
            olddisplay: "1595.05",
            displaydiffer: "6",
            override: "50",
            power: "6"
        }, {
            date: "2017-06-10",
            display: "1609.95",
            olddisplay: "1601.05",
            displaydiffer: "8.90",
            override: "60",
            power: "8.90"
        }, {
            date: "2017-06-11",
            display: "1616.05",
            olddisplay: "1609.95",
            displaydiffer: "6.05",
            override: "60",
            power: "6.05"
        }, {
            date: "2017-06-12",
            display: "1622.05",
            olddisplay: "1616.05",
            displaydiffer: "6",
            override: "50",
            power: "6"
        }, {
            date: "2017-06-13",
            display: "1626.05",
            olddisplay: "1622.05",
            displaydiffer: "4",
            override: "50",
            power: "4"
        }, {
            date: "2017-06-14",
            display: "1629.85",
            olddisplay: "1626.05",
            displaydiffer: "3.8",
            override: "50",
            power: "3.8"
        }, {
            date: "2017-06-15",
            display: "1655.85",
            olddisplay: "1629.85",
            displaydiffer: "26",
            override: "60",
            power: "26"
        }],
        onLoadError: function () {
            jError("查询日电量信息失败", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
        }
    });

    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
});

