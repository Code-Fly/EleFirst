/**
 * Created by VM on 2/8/2017.
 */
var DataGridUtils = {
    FLOAT_PRECISION: 2,
    strFormatter: function (value, row, index) {
        if (value != null && value != "null") {
            return "<div title='" + HTMLUtils.encode(value) + "'>" + HTMLUtils.encode(value) + "</div>";
        } else {
            return "-";
        }
    },
    floatFormatter: function (value, row, index) {
        if (value != null && value != "null") {
            var t = parseFloat(value);
            t = Math.floor(t * Math.pow(10, DataGridUtils.FLOAT_PRECISION)) / Math.pow(10, DataGridUtils.FLOAT_PRECISION);
            // return t.toFixed(DataGridUtils.FLOAT_PRECISION);
            return t;
        } else {
            return "-";
        }
    },
    dateToMinuteFormatter: function (value, row, index) {
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
    dateToDayFormatter: function (value, row, index) {
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
    dateToMonthFormatter: function (value, row, index) {
        if (value != null && value != "null") {
            if ((value + "").indexOf("-") < 0) {
                return value.substring(0, 4) + '-' + value.substring(4, 6);
            } else {
                return value;
            }
        } else {
            return "-";
        }
    }
};