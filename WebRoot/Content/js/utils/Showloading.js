/* 使用方法: 
 * 开启:MaskUtil.mask(); 
 * 关闭:MaskUtil.unmask(); 
 *  
 * MaskUtil.mask('其它提示文字...'); 
 */
var MaskUtil = (function () {

    var $mask, $maskMsg;

    var defMsg = '正在处理，请稍待。。。';

    function init() {
        if (!$mask) {
            $mask = $("<div class='datagrid-mask mymask' style='display: block; z-index: 10000'></div>").appendTo("body");
        }
        if (!$maskMsg) {
            $maskMsg = $("<div class='datagrid-mask-msg mymask'  style='display: block; z-index: 10000'>" + defMsg + "</div>")
                .appendTo("body").css({'font-size': '12px'});
        }

        $mask.css({width: "100%", height: $(document).height()});

        var scrollTop = $(document.body).scrollTop();

        $maskMsg.css({
            left: ( $(document.body).outerWidth(true) - $maskMsg.outerWidth(true) ) / 2
            , top: ( ($(window).height() - $maskMsg.outerHeight(true)) / 2 ) + scrollTop
        });
    }

    return {
        mask: function (msg) {
            init();
            $mask.show();
            $maskMsg.html(msg || defMsg).show();
        }
        , unmask: function () {
            $mask.hide();
            $maskMsg.hide();
        }
    }

}());  