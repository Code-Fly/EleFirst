<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/29
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- highcharts 5. 库 -->
<script src="${ctx}Content/js/Highcharts-5.0.7/code/highcharts.js"></script>
<script src="${ctx}Content/js/Highcharts-5.0.7/code/highcharts-3d.js"></script>
<script src="${ctx}Content/js/Highcharts-5.0.7/code/highcharts-more.js"></script>
<script src="${ctx}Content/js/Highcharts-5.0.7/code/modules/exporting.js"></script>
<script src="${ctx}Content/js/Highcharts-5.0.7/code/modules/drilldown.js"></script>
<!-- highcharts 4. 库 -->
<%--<script src="${ctx}Content/js/Highcharts-4.2.7/js/highcharts.js"></script>--%>
<%--<script src="${ctx}Content/js/Highcharts-4.2.7/js/highcharts-3d.js"></script>--%>
<%--<script src="${ctx}Content/js/Highcharts-4.2.7/js/highcharts-more.js"></script>--%>
<%--<script src="${ctx}Content/js/Highcharts-4.2.7/js/modules/exporting.js"></script>--%>
<%--<script src="${ctx}Content/js/Highcharts-4.2.7/js/modules/drilldown.js"></script>--%>
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
            numericSymbols: ["千", "兆", "G", "T", "P", "E"],
            printChart: "打印图表",
            resetZoom: "恢复缩放",
            resetZoomTitle: "恢复图表",
            shortMonths: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            thousandsSep: ",",
            weekdays: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"]
        }
    });
</script>
