/**
 * Created by 123 on 2016/4/20.
 */
$.extend($.fn.validatebox.defaults.rules, {
    /**
     * $('#cc').combobox({ validType: { comboInList: ["#cc"] },
	 * });
     */
    comboInList: {
        validator: function (value, param) {
            var c = $(param[0]);
            var opts = c.combobox("options");
            var data = c.combobox("getData");
            var exists = false;
            for (var i = 0; i < data.length; i++) {
                if (value == data[i][opts.textField]) {
                    exists = true;
                    break;
                }
            }
            return exists;
        },
        message: "输入值不合法！"
    },
    dsmIp: {
        validator: function (value, param) {
            var z = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
            return z.test(value);
        },
        message: "请填写正确IP地址!"
    },
    sessionNumber: {
        validator: function (value, param) {
            var regexp = /^\d+\d*$/g;
            var result = false;
            if (regexp.test(value)) {
                // 输入符合整数要求时;
                if (value > 0 && value <= 10) {
                    result = true;
                }
            }
            return result;
        },
        message: "会话值范围为1-10"
    },
    tb_dbName_restrict: {
        validator: function (value, param) {
            var key_array = ['insert', 'select', 'delete',
                'create', 'alter', 'drop', 'from', 'asc',
                'desc', 'case', 'when', 'where', 'between',
                'inner', 'left', 'right', 'join', 'on',
                'group', 'by', 'order', 'or', 'outer',
                'as', 'all', 'and', 'with', 'union',
                'database', 'table', 'limit', 'like',
                'distinct', 'having', 'null', 'is', 'asc',
                'desc'];
            var keySize = key_array.length;
            var regexp1_result = /^[_a-zA-Z][a-zA-Z0-9_]*$/g
                .test(value);
            var regexp2_result = /^\${[_a-zA-Z][a-zA-Z0-9_]*}$/g
                .test(value);
            if (regexp1_result || regexp2_result) {
                var isKeyword = true;
                // 不能超过20个字符;
                if (value.length > 20) {
                    return false;
                } else {
                    //符合长度要求时,也不能立即返回true;
                }
                for (var i = 0; i < keySize; i++) {
                    if (value == key_array[i]) {
                        isKeyword = false;
                        break;
                    }
                }
                return isKeyword;
            } else {
                return false;
            }

        },
        message: '名称不符合变量命名规则或为数据库关键字或长度超过20个字符'
    },
    dayValue: {
        validator: function (value, param) {
            var regexp = /^\d+\d*$/g;
            var result = false;
            if (regexp.test(value)) {
                // 输入符合整数要求时;
                if (value > 0 && value <= 31) {
                    result = true;
                }
            }
            return result;
        },
        message: "天数范围为1-31"
    },
    weekdayValue: {
        validator: function (value, param) {
            var regexp = /^\d+\d*$/g;
            var result = false;
            if (regexp.test(value)) {
                // 输入符合整数要求时;
                if (value > 0 && value <= 7) {
                    result = true;
                }
            }
            return result;
        },
        message: "周几值范围为1-7"
    },
    hourValue: {
        validator: function (value, param) {
            var regexp = /^\d+\d*$/g;
            var result = false;
            if (regexp.test(value)) {
                // 输入符合整数要求时;
                if (value >= 0 && value <= 23) {
                    result = true;
                }
            }
            return result;
        },
        message: "小时值范围为0-23"
    },
    minuteValue: {
        validator: function (value, param) {
            var regexp = /^\d+\d*$/g;
            var result = false;
            if (regexp.test(value)) {
                // 输入符合整数要求时;
                if (value >= 0 && value <= 59) {
                    result = true;
                }
            }
            return result;
        },
        message: "分钟值范围为0-59"
    },
    positiveInteger: {
        validator: function (value, param) {
            var z = /^\d+$/g;
            if (z.test(value)) {
                if (value > 0) {
                    return true;
                }
            }
            return false;
        },
        message: "请填正整数!"
    },
    scanNumber: {
        validator: function (value, param) {
            var z = /^\d+$/g;
            if (z.test(value)) {
                if (value > 0 && value < 20) {
                    return true;
                }
            }
            return false;
        },
        message: "请填0到20间的正整数!"
    },
    dsmPort: {
        validator: function (value, param) {
            var z = /^[0-9]*$/;
            return z.test(value) && parseInt(value) >= param[0]
                && parseInt(value) <= param[1];
        },
        message: "请填写正确端口号! ({0}-{1})"
    },
    dsmDatetime: {
        validator: function (value, param) {
            var z = /^[a-zA-Z-/\s:\.\d]+$/;
            return z.test(value);
        },
        message: "请填写正确时间格式!"
    },
    dsmNumber: {
        validator: function (value, param) {
            var z = /^\d+(\.\d+)?$/;
            return z.test(value);
        },
        message: "请填写正确数字!"
    },
    dsmPositiveNumber: {
        validator: function (value, param) {
            var z = /^(-)?\d+(\.\d+)?$/;
            return z.test(value) && value > 0;
        },
        message: "请填写正确数字!"
    },
    dsmPositiveNumberLtOne: {
        validator: function (value, param) {
            var z = /^(-)?\d+(\.\d+)?$/;
            return z.test(value) && value > 0 && value < 1;
        },
        message: "请填写正确数字!"
    },
    dsmPositiveNumberLeOne: {
        validator: function (value, param) {
            var z = /^(-)?\d+(\.\d+)?$/;
            return z.test(value) && value > 0 && value <= 1;
        },
        message: "请填写正确数字!"
    },
    dsmNegativeNumber: {
        validator: function (value, param) {
            var z = /^(-)?\d+(\.\d+)?$/;
            return z.test(value) && value < 0;
        },
        message: "请填写正确数字!"
    },
    dsmNatureNumber: {
        validator: function (value, param) {
            var z = /^[1-9]\d*$/;
            var x = /^0$/;
            return z.test(value) || x.test(value);
        },
        message: "请填写正确数字!"
    },
    dsmInteger: {
        validator: function (value, param) {
            var z = /^-?[1-9]\d*$/;
            var x = /^0$/;
            return z.test(value) || x.test(value);
        },
        message: "请填写正确数字!"
    },
    dsmIntegerWithoutZero: {
        validator: function (value, param) {
            var z = /^-?[1-9]\d*$/;
            return z.test(value);
        },
        message: "请填写正确数字!"
    },
    dsmPositiveInteger: {
        validator: function (value, param) {
            var z = /^-?[1-9]\d*$/;
            return z.test(value) && value > 0;
        },
        message: "请填写正确数字!"
    },
    dsmNegativeInteger: {
        validator: function (value, param) {
            var z = /^-?[1-9]\d*$/;
            return z.test(value) && value < 0;
        },
        message: "请填写正确数字!"
    },
    dsmName: {
        validator: function (value, param) {
            // var z= /.*?[<|>]+.*?/;
            var t = value.replace(/[^\x00-\xff]/g, ''); // 替换中文
            return (value.length - t.length) * 2 + t.length <= param[0]; // 判断长度
        },
        message: "内容过长!"
    },
    dsmNameChar: {
        validator: function (value, param) {
            var z = /.*?[<>]+.*?/;
            return !z.test(value);
        },
        message: "不能包含特殊字符!"
    },
    dsmParquetPath: {
        validator: function (value, param) {
            return (value + "").indexOf("/"
                    + (param[0] + "").toLowerCase() + ".db/") == -1;
        },
        message: "存储路径不合法!"
    },
    dsmParquetPathWithIp: {
        validator: function (value, param) {
            var z = /^((HDFS|hdfs)?:\/\/)(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)[^\s]+/;
            return z.test(value);
        },
        message: "存储路径不合法!"
    },
    dsmBaseDirectory: {
        validator: function (value, param) {
            return (value + "").startWith("/")
                && (value + "").endWith("/");
        },
        message: "路径不合法!"
    },
    dsmBaseDirectoryend: {
        validator: function (value, param) {
            return (value + "").startWith("/")
                && !(value + "").endWith("/");
        },
        message: "路径不合法!"
    },
    dsmTableName: {
        validator: function (value, param) {
            var z = /^[a-zA-Z0-9_]+$/;
            return z.test(value);
        },
        message: "名称不合法!"
    },
    dsmEsName: {
        validator: function (value, param) {
            var z = /^[a-z_]+[a-z0-9_]*$/;
            return z.test(value);
        },
        message: "名称不合法!"
    },
    notChinses: {
        validator: function (value, param) {
            var z = /^[a-zA-Z_]+$/;
            return escape(value).indexOf("%u") < 0;
        },
        message: "只能输入英文字符！"
    },
    notChinse: {
        validator: function (value, param) {
            var z = new RegExp(/^[a-zA-Z_]+[a-zA-Z0-9_]*$/gi);
            return z.test(value);
        },
        message: "不符合命名规则！"
    },
    // 自己写正则,避免出现(和)符号;
    regexp_restrict: {
        validator: function (value, param) {
            return /^[^()][^()]*$/g.test(value);
        },
        message: '正则表达式中不要使用分组'
    },
    col_restrict: {
        validator: function (value, param) {

            var regexp1_result = /^[_a-zA-Z][a-zA-Z0-9_]*$/g
                .test(value);
            var regexp2_result = /^\${[_a-zA-Z][a-zA-Z0-9_]*}$/g
                .test(value);
            if (regexp1_result || regexp2_result) {
                return true;
            } else {
                return false;
            }
        },
        message: '名称不符合变量命名规则'
    },
    col_restr_content_length: {
        validator: function (value, param) {

            var regexp1_result = /^[_a-zA-Z][a-zA-Z0-9_]*$/g
                .test(value);
            var regexp2_result = /^\${[_a-zA-Z][a-zA-Z0-9_]*}$/g
                .test(value);
            if (regexp1_result || regexp2_result) {
                var valueLength = value.length;
                if (valueLength > 32) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        },
        message: '列名需要符合变量命名规则且长度不能超过32个字符'
    },
    number_restrict: {
        validator: function (value, param) {
            return /^[0-9][0-9]*$/g.test(value);
        },
        message: '请填自然数'
    },
    lof_neighbor_scope: {
        validator: function (value, param) {
            var isNumber = /^[0-9][0-9]*$/g.test(value);
            if (isNumber == false) {
                return false;
            }
            // value = parseInt(value);
            // 这边比较会按照字符串来进行;
            if (value >= 10 && value <= 50) {
                return true;
            } else {
                return false;
            }
        },
        message: '请填10至50间的整数'
    },
    decisionTree_num: {
        validator: function (value, param) {
            var isNumber = isNaN(value);
            // 当value不是数值时;
            if (isNumber == true) {
                return false;
            }
            return true;
        },
        message: '请输入一个数值'
    },
    cof_num: {
        validator: function (value, param) {
            var isNumber = /^[0-9][0-9]*$/g.test(value);
            if (isNumber == false) {
                return false;
            }
            // value = parseInt(value);
            // 这边比较会按照字符串来进行;
            if (value > 0) {
                return true;
            } else {
                return false;
            }
        },
        message: '请输入大于0的整数'
    },
    no_chinese: {
        validator: function (value, param) {
            return /^[u4E00-u9FA5]+$/g.test(value);
        },
        message: '不能输入中文字符！'
    },
    col_restrict_no_keyWords: {
        validator: function (value, param) {

            var regexp1_result = /^[_a-zA-Z][a-zA-Z0-9_]*$/g
                .test(value);
            var regexp2_result = /^\${[_a-zA-Z][a-zA-Z0-9_]*}$/g
                .test(value);

            var regexp3_result = true;
            var ads = ["insert", "select", "delete", "create",
                "alter", "drop", "from", "asc", "desc",
                "case", "when", "where", "between",
                "inner", "left", "right", "join", "on",
                "group", "by", "order", "or", "outer",
                "as", "all", "and", "with", "union",
                "database", "table", "limit", "like",
                "distinct", "having", "null", "is"];
            for (var i = 0; i < ads.length; i++) {

                if (value.toLowerCase() == ads[i]) {
                    regexp3_result = false;
                    break;
                }
            }

            if ((regexp1_result || regexp2_result)
                && regexp3_result) {
                return true;
            } else {
                return false;
            }
        },
        message: '名称不符合变量命名规则'
    },
    dsmOutHdfsParColType: {
        validator: function (value, param) {
            if ("day" == param[0]) {
                var reg = /^(\d{4})(\d{2})(\d{2})$/;
                if (!reg.test(value) || (RegExp.$2 < 1 || RegExp.$2 > 12) || (RegExp.$3 < 1 || RegExp.$3 > 31)) {
                    return false;
                }
            } else if ("month" == param[0]) {
                var reg = /^(\d{4})(\d{2})$/;
                if (!reg.test(value) || (RegExp.$2 < 1 || RegExp.$2 > 12)) {
                    return false;
                }
            } else if ("year" == param[0]) {
                var reg = /^(\d{4})$/;
                if (!reg.test(value)) {
                    return false;
                }
            }else {
                return false;
            }
            return true;
        },
        message: '格式不正确！'
    },
});