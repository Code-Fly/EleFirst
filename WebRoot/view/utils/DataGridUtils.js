/**
 * Created by VM on 2/8/2017.
 */
var DataGridUtils = {
    FLOAT_PRECISION: 1,
    strFormatter: function (value) {
        if (value != null && value != "null") {
            return "<div title='" + HTMLUtils.encode(value) + "'>" + HTMLUtils.encode(value) + "</div>";
        } else {
            return "-";
        }
    },
    floatFormatter: function (value, precision, isNull) {
        if (value != null && value != "null" && !isNaN(value)) {
            var t = parseFloat(value);
            // t = Math.floor(t * Math.pow(10, DataGridUtils.FLOAT_PRECISION)) / Math.pow(10, DataGridUtils.FLOAT_PRECISION);
            return parseFloat(t.toFixed(precision));
            // return t;
        }
        else {
            if (isNull) {
                return null;
            } else {
                return "-";
            }
        }
    },
    floatWithUnitFormatter: function (value, precision, isNull) {
        if (value != null && value != "null" && !isNaN(value)) {
            var t = parseFloat(value);
            if (t > 10000) {
                t = t / 10000.0
                return parseFloat(t.toFixed(2)) + "万";
            }
            else if (t > 1000) {
                t = t / 1000.0
                return parseFloat(t.toFixed(2)) + "千";
            }
            else if (t > 100) {
                t = t / 100.0
                return parseFloat(t.toFixed(2)) + "百";
            }
            else {
                return parseFloat(t.toFixed(precision));
            }
        }
        else {
            if (isNull) {
                return null;
            } else {
                return "-";
            }
        }
    },
    totalHarmonicFormatter: function (value) {
        if (value != null && value != "null" && value == "19t") {
            return '总';
        } else {
            return value;
        }
    },
    dateToMinuteFormatter: function (value) {
        if (value != null && value != "null") {
            if ((value + "").indexOf("-") < 0 && (value + "").indexOf(":") < 0) {
                return value.substring(0, 4) + '-' + value.substring(4, 6) + '-' + value.substring(6, 8) + " " + value.substring(8, 10) + ":" + value.substring(10, 12) + ":" + value.substring(12, 14);
            } else {
                return value;
            }
        } else {
            return "-";
        }
    },
    dateToDayFormatter: function (value) {
        if (value != null && value != "null") {
            if ((value + "").indexOf("-") < 0) {
                return value.substring(0, 4) + '-' + value.substring(4, 6) + '-' + value.substring(6, 8);
            } else {
                return value;
            }
        } else {
            return "-";
        }
    },
    dateToMonthFormatter: function (value) {
        if (value != null && value != "null") {
            if ((value + "").indexOf("-") < 0) {
                return value.substring(0, 4) + '-' + value.substring(4, 6);
            } else {
                return value;
            }
        } else {
            return "-";
        }
    },
    dateToYearFormatter: function (value) {
        if (value != null && value != "null") {
            if ((value + "").indexOf("-") < 0) {
                return value.substring(0, 4);
            } else {
                return value;
            }
        } else {
            return "-";
        }
    },
    timestampToMinuteFormatter: function (value) {
        if (value != null && value != "null") {
            var date = new Date();
            date.setTime(value);
            return date.format("yyyy-MM-dd hh:mm:ss");
        } else {
            return "-";
        }
    },
    getColumn: function (result, frozenColumns) {
        var columns = new Array();
        if (null != result && result.length > 0) {
            $.each(result[0], function (i, field) {
                // var index = -1;
                // for (var j = 0; j < frozenColumns.length; j++) {
                //     if (frozenColumns[j].field == i) {
                //         index = j
                //     }
                // }

                var index = _.findIndex(frozenColumns, function (o) {
                    return o.field == i;
                });

                if (-1 == index) {
                    var column = {};
                    column["title"] = i;
                    column["field"] = i;
                    column["width"] = 100;
                    column["align"] = "center";
                    column["formatter"] = DataGridUtils.strFormatter
                    columns.push(column);
                }
            });
        }
        return columns;
    },
    floatLoadFormatter: function (value, precision, isNull) {
        if (value != null && value != "null" && !isNaN(value) && 0 != value && '0' != value) {
            var t = parseFloat(value);
            // t = Math.floor(t * Math.pow(10, DataGridUtils.FLOAT_PRECISION)) / Math.pow(10, DataGridUtils.FLOAT_PRECISION);
            return parseFloat(t.toFixed(precision));
            // return t;
        }
        else {
            if (isNull) {
                return null;
            } else {
                return "-";
            }
        }
    },
};