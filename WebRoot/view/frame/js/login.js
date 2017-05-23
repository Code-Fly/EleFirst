/**
 * Created by barrie on 17/1/22.
 */
$(document).ready(function () {
    if (!isTopWindow()) {
        var topWindow = getTopWinow();
        topWindow.location.href = window.location.href;
    }

    var error = GetQueryString("error");
    if (error == "1") {
        $.messager.alert("信息提示", "无效用户或用户名、密码错误！", "warning");
    }
    else if (error == "2") {
        $.messager.alert("信息提示", "Session 已过期，请重新登陆！", "warning");
    }

    // $(".loginbox").css({"position": "absolute", "left": ($(window).width() - 692) / 2});
    // $(window).resize(function () {
    //     $(".loginbox").css({"position": "absolute", "left": ($(window).width() - 692) / 2});
    // })

    $("#userName").textbox({
        required: true,
        validateOnCreate: false,
        inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents, {
            keyup: function (event) {
                if (event.keyCode == 13) {
                    var vUsr = $("#userName").textbox("isValid");
                    var vPwd = $("#password").passwordbox("isValid");
                    if (vUsr && vPwd) {
                        $("#form-login").submit();
                    }
                }
            }
        })
    });

    $("#password").passwordbox({
        required: true,
        validateOnCreate: false,
        inputEvents: $.extend({}, $.fn.passwordbox.defaults.inputEvents, {
            keyup: function (event) {
                if (event.keyCode == 13) {
                    var vUsr = $("#userName").textbox("isValid");
                    var vPwd = $("#password").passwordbox("isValid");
                    if (vUsr && vPwd) {
                        $("#form-login").submit();
                    }
                }
            }
        })
    });

    $("#btnLogin").click(function () {
        // if ($("#userName").val() == "admin" && $("#password").val() == "admin") {
        // 	  $("#f1").submit();
        // 	  return true;
        //     //window.location.href = "login/login.do";
        // } else {
        //     $.messager.alert("信息提示", "用户名或密码错误！", "warning");
        // }
        var vUsr = $("#userName").textbox("isValid");
        var vPwd = $("#password").passwordbox("isValid");
        if (vUsr && vPwd) {
            $("#form-login").submit();
        }

    });

    function isTopWindow() {
        var p = window;
        if (p != p.parent) {
            return false;
        }
        return true;
    }

    /**
     * 在页面中任何嵌套层次的窗口中获取顶层窗口
     * @return 当前页面的顶层窗口对象
     */
    function getTopWinow() {
        var p = window;
        while (p != p.parent) {
            p = p.parent;
        }
        return p;
    }

});