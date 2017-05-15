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
    }
};