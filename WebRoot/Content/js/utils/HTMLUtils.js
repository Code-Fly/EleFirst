/**
 * Created by Administrator on 2016/8/31.
 */
/**
 * HTML工具类
 * @type {{encode, decode, removeHtmlTab, html2Escape, escape2Html, nbsp2Space, return2Br, trimBr, mergeSpace}}
 */
var HTMLUtils = (function () {
    return {
        encode: function (value) {
            return this.html2Escape(value);
        },
        decode: function (value) {
            return this.escape2Html(value);
        },
        //去掉html标签
        removeHtmlTab: function (tab) {
            return (tab + "").replace(/<[^<>]+?>/g, '');//删除所有HTML标签
        },
        //普通字符转换成转意符
        html2Escape: function (sHtml) {
            return (sHtml + "").replace(/[<>&"']/g, function (c) {
                return {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;', '\'': "&apos;"}[c];
            });
        },
        //转意符换成普通字符
        escape2Html: function (str) {
            var arrEntities = {'lt': '<', 'gt': '>', 'nbsp': ' ', 'amp': '&', 'quot': '"', 'apos': '\''};
            return (str + "").replace(/&(lt|gt|nbsp|amp|quot|apos);/ig, function (all, t) {
                return arrEntities[t];
            });
        },
        // &nbsp;转成空格
        nbsp2Space: function (str) {
            var arrEntities = {'nbsp': ' '};
            return (str + "").replace(/&(nbsp);/ig, function (all, t) {
                return arrEntities[t]
            })
        },
        //回车转为br标签
        return2Br: function (str) {
            return (str + "").replace(/\r?\n/g, "<br />");
        },
        //去除开头结尾换行,并将连续3次以上换行转换成2次换行

        trimBr: function (str) {
            str = (str + "").replace(/((\s|&nbsp;)*\r?\n){3,}/g, "\r\n\r\n");//限制最多2次换行
            str = (str + "").replace(/^((\s|&nbsp;)*\r?\n)+/g, '');//清除开头换行
            str = (str + "").replace(/((\s|&nbsp;)*\r?\n)+$/g, '');//清除结尾换行
            return str;
        },
        // 将多个连续空格合并成一个空格
        mergeSpace: function (str) {
            str = (str + "").replace(/(\s|&nbsp;)+/g, ' ');
            return str;
        }
    }

}());

/**
 * HTML元素生成工具类
 * @type {{Div: HtmlElm.Div, Li: HtmlElm.Li, Img: HtmlElm.Img, A: HtmlElm.A, H2: HtmlElm.H2, H3: HtmlElm.H3, P: HtmlElm.P, Table: HtmlElm.Table, Caption: HtmlElm.Caption, Tr: HtmlElm.Tr, Td: HtmlElm.Td, Button: HtmlElm.Button}}
 */
var HtmlElm = {
    Div: function () {
        return $(document.createElement("div")).clone();
    },
    Li: function () {
        return $(document.createElement("li")).clone();
    },
    Img: function () {
        return $(document.createElement("img")).clone();
    },
    A: function () {
        return $(document.createElement("a")).clone();
    },
    H2: function () {
        return $(document.createElement("h2")).clone();
    },
    H3: function () {
        return $(document.createElement("h3")).clone();
    },
    P: function () {
        return $(document.createElement("p")).clone();
    },
    Table: function () {
        return $(document.createElement("table")).clone();
    },
    Caption: function () {
        return $(document.createElement("caption")).clone();
    },
    Tr: function () {
        return $(document.createElement("tr")).clone();
    },
    Td: function () {
        return $(document.createElement("td")).clone();
    },
    Button: function () {
        return $(document.createElement("button")).clone();
    },
    Span: function () {
        return $(document.createElement("span")).clone();
    },
    Ul: function () {
        return $(document.createElement("ul")).clone();
    }
}