<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2016/4/11
  Time: 15:58 
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setContentType("text/html;charset=UTF-8");
    String path = request.getContextPath();
    int port = request.getServerPort();
    String basePath = null;
    if (80 == port) {
        basePath = request.getScheme() + "://" + request.getServerName() + path + "/";
    } else {
        basePath = request.getScheme() + "://" + request.getServerName() + ":" + port + path + "/";
    }
    application.setAttribute("ctx", basePath);
%>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">--%>