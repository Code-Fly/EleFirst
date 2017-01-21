/**
 * Created by barrie on 17/1/22.
 */
$(document).ready(function () {
    $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
    $(window).resize(function () {
        $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
    })
});