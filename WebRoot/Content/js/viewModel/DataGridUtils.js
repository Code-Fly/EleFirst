/**
 * Created by VM on 2/8/2017.
 */
var DataGridUtils = {
    FLOAT_PRECISION: 1,
    strFormatter: function (value, row, index) {
        if (value != null) {
            return "<div title='" + HTMLUtils.encode(value) + "'>" + HTMLUtils.encode(value) + "</div>";
        } else {
            return "-";
        }
    },
    floatFormatter: function (value, row, index) {
        if (value != null) {
            var t = parseFloat(value);
            t = Math.floor(t * Math.pow(10, DataGridUtils.FLOAT_PRECISION)) / Math.pow(10, DataGridUtils.FLOAT_PRECISION);
            // return t.toFixed(DataGridUtils.FLOAT_PRECISION);
            return t;
        } else {
            return "-";
        }
    },
    dateToDayFormatter: function (value, row, index) {
        if (value != null) {
            if ((value + "").split("-").length == 1) {
                return value.substring(0, 4) + '-' + value.substring(4, 6) + '-' + value.substring(6, 8);
            } else {
                return value;
            }
        } else {
            return "-";
        }
    },
    dateToMonthFormatter: function (value, row, index) {
        if (value != null) {
            if ((value + "").split("-").length == 1) {
                return value.substring(0, 4) + '-' + value.substring(4, 6);
            } else {
                return value;
            }
        } else {
            return "-";
        }
    }
};