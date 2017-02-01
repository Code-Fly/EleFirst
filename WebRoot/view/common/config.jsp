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
    String path = request.getContextPath() + "/";
    application.setAttribute("ctx", path);
%>