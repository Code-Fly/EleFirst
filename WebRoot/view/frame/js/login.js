/**
 * Created by barrie on 17/1/22.
 */
$(document).ready(function () {
    $(".loginbox").css({"position": "absolute", "left": ($(window).width() - 692) / 2});
    $(window).resize(function () {
        $(".loginbox").css({"position": "absolute", "left": ($(window).width() - 692) / 2});
    })
    $("#btnLogin").click(function () {
        if ($("#userName").val() == "admin" && $("#password").val() == "admin") {
            window.location.href = "index.jsp";
        } else {
            $.messager.alert("信息提示", "用户名或密码错误！", "warning");
        }
    });
});