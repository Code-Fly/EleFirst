/**
 * Created by barrie on 2017/3/12.
 */
var Spinner = function () {
    this.loader = 0;
    this.defMsg = "正在处理，请稍待。。。";
    this.mask = $('<div class="datagrid-mask" style="display: block; z-index: 10000"></div>');
    this.maskMsg = $('<div class="datagrid-mask-msg"  style="display: block; z-index: 10000">' + this.defMsg + '</div>');
    this.initialized = false;
};

Spinner.prototype.init = function () {
    if (!this.initialized) {
        $(this.mask).appendTo("body");
        $(this.maskMsg).appendTo("body");

        var scrollTop = $(document.body).scrollTop();

        $(this.mask).css({
            "width": "100%",
            "height": $(document).height()
        });

        $(this.maskMsg).css({
            "font-size": "12px",
            "left": ( $(document.body).outerWidth(true) - $(this.maskMsg).outerWidth(true) ) / 2,
            "top": ( ($(window).height() - $(this.maskMsg).outerHeight(true)) / 2 ) + scrollTop
        });

        this.initialized = true;
    }
};

Spinner.prototype.load = function (msg) {
    if (0 == this.loader) {
        this.init();
        $(this.mask).show();
        $(this.maskMsg).html(msg || this.defMsg).show();
    }
    this.loader++;
};

Spinner.prototype.unload = function () {
    this.loader--;
    if (0 == this.loader) {
        $(this.mask).hide();
        $(this.maskMsg).hide();
    }
};