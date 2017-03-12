/**
 * Created by barrie on 2017/3/12.
 */

var ChartConfig = function (path) {
    var config = $.parseJSON($.ajax({
        url: _ctx + path + "?bust=" + new Date().getTime(),
        type: "GET",
        async: false
    }).responseText);

    this.config = config;
}

ChartConfig.prototype.setConfig = function (config) {
    this.config = config;
    return this;
};

ChartConfig.prototype.getConfig = function () {
    return this.config;
};

ChartConfig.prototype.setShared = function (shared) {
    this.config.tooltip.shared = shared;
    for (var i = 0; i < this.config.xAxis.length; i++) {
        if (this.config.xAxis[i].hasOwnProperty("crosshair")) {
            this.config.xAxis[i].crosshair.snap = !shared;
        }
    }
    for (var i = 0; i < this.config.yAxis.length; i++) {
        if (this.config.yAxis[i].hasOwnProperty("crosshair")) {
            this.config.yAxis[i].crosshair.snap = !shared;
        }
    }
    return this;
};

ChartConfig.prototype.setCrossHairSnap = function (snap) {
    for (var i = 0; i < this.config.xAxis.length; i++) {
        if (this.config.xAxis[i].hasOwnProperty("crosshair")) {
            this.config.xAxis[i].crosshair.snap = snap;
        }
    }
    for (var i = 0; i < this.config.yAxis.length; i++) {
        if (this.config.yAxis[i].hasOwnProperty("crosshair")) {
            this.config.yAxis[i].crosshair.snap = snap;
        }
    }
    return this;
};

ChartConfig.prototype.setZoom = function (zoom) {
    this.config.chart.zoomType = zoom ? "x" : undefined;
    this.config.subtitle.text = zoom ? "鼠标拖动可以进行缩放" : undefined;
    this.config.rangeSelector.enabled = zoom;
    this.config.navigator.enabled = zoom;
    this.config.scrollbar.enabled = zoom;
    return this;
};

ChartConfig.prototype.setSeries = function (series) {
    this.config.series = series;
    return this;
};

ChartConfig.prototype.getSeries = function (series) {
    return this.config.series;
};

ChartConfig.prototype.setDataGroupingByHour = function () {
    this.config.plotOptions.series.dataGrouping = {
        forced: true,
        units: [
            [
                "hour", [1]
            ]
        ],
        dateTimeLabelFormats: {
            hour: ['%H:%M']
        }
    };
    return this;
};

ChartConfig.prototype.setDataGroupingByDay = function () {
    this.config.plotOptions.series.dataGrouping = {
        forced: true,
        units: [
            [
                "day", [1]
            ]
        ],
        dateTimeLabelFormats: {
            day: ['%e日']
        }
    };
    return this;
};

ChartConfig.prototype.setDataGroupingByWeek = function () {
    this.config.plotOptions.series.dataGrouping = {
        forced: true,
        units: [
            [
                "day", [1]
            ]
        ],
        dateTimeLabelFormats: {
            day: ["%A"]
        }
    };
    this.config.navigator.xAxis.dateTimeLabelFormats = {
        day: "%A"
    };
    for (var i = 0; i < this.config.xAxis.length; i++) {
        this.config.xAxis[i].dateTimeLabelFormats = {
            day: "%A"
        };
    }

    return this;
};