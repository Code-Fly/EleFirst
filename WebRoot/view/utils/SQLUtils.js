/**
 * Created by Administrator on 2016/8/31.
 */
var SQLUtils = (function () {
    return {
        build: function (dbName, tbName, columns, limit) {
            var allList = columns;
            var dimensionList = [];
            for (var i = 0; i < allList.length; i++) {
                if (allList[i].category == "dimension" && allList[i].axis != "filter") {
                    dimensionList.push(allList[i]);
                }
            }
            var measureList = [];
            for (var i = 0; i < allList.length; i++) {
                if (allList[i].category == "measure" && allList[i].axis != "filter") {
                    if (allList[i]["param"]["tree-calc"] != "default") {
                        measureList.push(allList[i]);
                    } else {
                        dimensionList.push(allList[i]);
                    }
                }
            }
            var orderByList = [];
            for (var i = 0; i < allList.length; i++) {
                if (allList[i]["param"]["tree-order"] != "default" && allList[i].axis != "filter") {
                    orderByList.push(allList[i]);
                }
            }
            var selectList = [];
            for (var i = 0; i < allList.length; i++) {
                if (allList[i].axis != "filter") {
                    selectList.push(allList[i]);
                }
            }
            var filterList = [];
            for (var i = 0; i < allList.length; i++) {
                if (allList[i].axis == "filter") {
                    filterList.push(allList[i]);
                }
            }

            var SQL = "";
            SQL += " SELECT ";
            for (var i = 0; i < selectList.length; i++) {
                if (i > 0) {
                    SQL += ",";
                }
                if (selectList[i].category == "dimension") {
                    SQL += " `" + selectList[i].name + "` ";
                }
                if (selectList[i].category == "measure") {
                    if (selectList[i]["param"]["tree-calc"] == "default") {
                        SQL += " `" + selectList[i].name + "` ";
                    } else {
                        SQL += " " + selectList[i]["param"]["tree-calc"] + "(`" + selectList[i].name + "`) as `" + selectList[i]["param"]["tree-calc"] + "（" + selectList[i].name + "）` ";
                    }
                }
            }
            SQL += " FROM ";
            SQL += " `" + dbName + "`.`" + tbName + "` ";

            if (filterList.length > 0) {
                SQL += " WHERE ";
                for (var i = 0; i < filterList.length; i++) {
                    if (i > 0) {
                        SQL += " AND ";
                    }

                    var value = this.getFilterParam(filterList[i]["param"]["tree-filter"]["value"], "value");
                    var sep = this.isDimensionType(filterList[i]["type"]) ? "'" : "";

                    SQL += "`" + filterList[i].name + "` " + filterList[i]["param"]["tree-filter"]["name"] + " ";
                    if (null != value) {
                        if ("BETWEEN" == filterList[i]["param"]["tree-filter"]["name"]) {
                            SQL += sep + (value + "").split(":")[0] + sep + " AND " + sep + (value + "").split(":")[1] + sep + " ";
                        } else {
                            SQL += sep + (value + "").split(":")[0] + sep + " ";
                        }
                    }

                }
            }

            if (dimensionList.length > 0) {
                SQL += " GROUP BY ";
                for (var i = 0; i < dimensionList.length; i++) {
                    if (i > 0) {
                        SQL += ",";
                    }
                    SQL += " `" + dimensionList[i].name + "` ";
                }
            }

            if (orderByList.length > 0) {
                SQL += " ORDER BY ";
                for (var i = 0; i < orderByList.length; i++) {
                    if (i > 0) {
                        SQL += ",";
                    }
                    if (orderByList[i].category == "dimension") {
                        SQL += " `" + orderByList[i].name + "` " + orderByList[i]["param"]["tree-order"] + " ";
                    }
                    if (orderByList[i].category == "measure") {
                        if (orderByList[i]["param"]["tree-calc"] == "default") {
                            SQL += " `" + orderByList[i].name + "` " + orderByList[i]["param"]["tree-order"] + " ";
                        } else {
                            SQL += " " + orderByList[i]["param"]["tree-calc"] + "(`" + orderByList[i].name + "`) " + orderByList[i]["param"]["tree-order"] + " ";
                        }
                    }
                }
            }

            SQL += "limit " + limit;

            return SQL;
        },
        isDimensionType: function (type) {
            var dimensionType = ["string", "char", "varchar", "boolean"];
            for (var j = 0; j < dimensionType.length; j++) {
                if ((dimensionType[j] + "").toLowerCase().startWith(type)) {
                    return true;
                }
            }
            return false;
        },
        getFilterParam: function (params, name) {
            for (var i = 0; i < params.length; i++) {
                if (params[i].name == name) {
                    return params[i].value;
                }
            }
            return null;
        }
    }
}());