<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/16
  Time: 下午6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <%@ include file="/view/common/commonMxGraph.jsp" %>

    <script type="text/javascript" src="js/init.js"></script>
    <style>
        html, body {
            margin: 0;
            height: 100%;
        }

        #graphContainer {
            /*overflow: auto;*/
            /*position: relative;*/
            width: 100%;
            height: 100%;
            /*left: 50px;*/
            /*right: 50px;*/
            /*top: 50px;*/
            /*bottom: 50px;*/
            /*border: 1px solid gray;*/
            background: url('${mxBasePath}images/examples/wires-grid.gif');
            background-position: -1px 0px;
            cursor: crosshair;
        }

        .current p {
            padding: 0px;
            margin: 5px;
            text-align: left;
        }

        #outlineContainer {
            z-index: 1;
            position: absolute;
            overflow: hidden;
            top: 0px;
            right: 0px;
            width: 160px;
            height: 120px;
            background: transparent;
            border: solid;
            border-color: lightgray;
        }
    </style>
</head>
<body style="overflow: hidden">
<!-- Creates a container for the sidebar -->
<div id="toolbarContainer">
</div>
<!-- Creates a container for the graph -->
<div id="graphContainer">
</div>
</body>
</html>
