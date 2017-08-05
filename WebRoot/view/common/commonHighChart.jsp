<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/29
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- highcharts 5. 库 -->
<%--<script src="${ctx}Content/js/Highcharts/code/highcharts.js"></script>--%>
<script src="${ctx}Content/js/Highstock/code/highstock.js?v=${timestamp}"></script>
<script src="${ctx}Content/js/Highcharts/code/highcharts-3d.js?v=${timestamp}"></script>
<script src="${ctx}Content/js/Highcharts/code/highcharts-more.js?v=${timestamp}"></script>
<script src="${ctx}Content/js/Highcharts/code/modules/exporting.js?v=${timestamp}"></script>
<script src="${ctx}Content/js/Highcharts/code/modules/drilldown.js?v=${timestamp}"></script>

<!-- highcharts 4. 库 -->
<%--<script src="${ctx}Content/js/Highcharts/js/highcharts.js"></script>--%>
<%--<script src="${ctx}Content/js/Highcharts/js/highcharts-3d.js"></script>--%>
<%--<script src="${ctx}Content/js/Highcharts/js/highcharts-more.js"></script>--%>
<%--<script src="${ctx}Content/js/Highcharts/js/modules/exporting.js"></script>--%>
<%--<script src="${ctx}Content/js/Highcharts/js/modules/drilldown.js"></script>--%>
<script>
    Highcharts.setOptions({
        lang: {
            contextButtonTitle: "图表导出菜单",
            decimalPoint: ".",
            downloadJPEG: "下载JPEG图片",
            downloadPDF: "下载PDF文件",
            downloadPNG: "下载PNG文件",
            downloadSVG: "下载SVG文件",
            drillUpText: "返回 {series.name}",
            loading: "加载中",
            months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            noData: "没有数据",
            numericSymbols: ["K", "M", "G", "T", "P", "E"],
            printChart: "打印图表",
            resetZoom: "恢复缩放",
            resetZoomTitle: "恢复图表",
            shortMonths: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            thousandsSep: ",",
            weekdays: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
            rangeSelectorZoom: '缩放',
            rangeSelectorFrom: '起始',
            rangeSelectorTo: '结束'
        }
    });
</script>
