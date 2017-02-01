<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/26
  Time: 上午2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/view/common/config.jsp" %>
<html>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <script type="text/javascript">
    	  function parentcodeformatter(value, row, index) {
            if (value == '0') {
                return '一级菜单'
            }else if (value == '01' || value == '02' || value == '03' || value == '04') {
                return '二级菜单'
            }
        }
        
        function isenableformatter(value, row, index) {
            if (value == 'Y') {
                return '启用'
            }else{
                return '禁用'
            }
        }
        require(["js/menu.js"]);
    </script>
</head>
<body>
<table id="tt" class="easyui-datagrid" fit="true" data-options="border:false">
  <thead>
  <tr>
      <th field="menuid" data-options="checkbox:true">是否选择</th>
      <th field="menuname" width="120" align="center">菜单名称</th>
      <th field="parentcode" width="80" align="center" formatter="parentcodeformatter">菜单类型</th>
      <th field="isenable" width="80" align="center" formatter="isenableformatter">是否启用</th>
  </tr>
  </thead>
</table>
</body>
</html>
