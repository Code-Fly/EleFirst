<%--
  Created by IntelliJ IDEA.
  User: barrie
  Date: 17/1/26
  Time: 上午2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/view/common/config.jsp"%>
<html>
<head>
<!-- 公共属性 -->
<%@ include file="/view/common/meta.jsp"%>
<script type="text/javascript">
	require(["js/usermanager.js"]);
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="overflow: hidden;">
		<div class="z-toolbar">
			<a id="a_refresh" href="javascript:void(0)" plain="true"
				class="easyui-linkbutton" icon="icon-arrow_refresh" title="刷新"
				onclick="refreshClick()">刷新</a> <a id="a_add"
				href="javascript:void(0)" plain="true" class="easyui-linkbutton"
				icon="icon-add" title="新增" onclick="addClick()">新增</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" title="编辑" onclick="editClick()">编辑</a>
			<a id="a_del" href="javascript:void(0)" plain="true"
				class="easyui-linkbutton" icon="icon-cross" title="删除"
				onclick="deleteClick()">删除</a> <a id="a_authorization"
				href="javascript:void(0)" plain="true" class="easyui-linkbutton"
				icon="icon-sysroleassign" title="分配角色" onclick="roleClick()">分配角色</a>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="tt2">
			<thead>
				<tr>
					<th field="id" hidden="true"></th>
					<th field="userName" align="left" width="200">用户名</th>
					<th field="userCode" align="left" width="200">用户编码</th>
					<th field="createPerson" align="left" width="100">创建者</th>
					<th field="createDate" align="left" width="200">创建日期</th>
					<th field="description" align="left" width="300" editor="text">描述</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 350px; height: 300px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" style="display:none" data-options="modal:true">
		<div class="container_12">
			<form id="ff" method="get">
				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">用户名</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" name="userName"
							data-options="required:true" style="width: 100%">
					</div>
				</div>

				<div class="clear"></div>

				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">用户编码</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" name="userCode"
							data-options="required:true" style="width: 100%">
					</div>
				</div>

				<div class="clear"></div>

				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">用户密码</div>
					<div class="grid_8 val">
						<input name="passwd" validType="length[4,32]" style="width: 98%"
							class="easyui-validatebox" required="true" type="password"
							value="" />
					</div>
				</div>

				<div class="clear"></div>

				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">描述说明</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" name="description"
							style="width: 100%; height: 60px" data-options="multiline:true">
					</div>
				</div>
			</form>

			<div class="clear"></div>

			<div style="text-align: center; padding: 20px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					icon="icon-save" onclick="submitForm()" style="width: 80px">提交</a>
			</div>
		</div>
	</div>


	<div id="dlgUpdate" class="easyui-dialog"
		style="width: 300px; height: 260px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" style="display:none" data-options="modal:true">
		<div class="container_12">
			<form id="ffs" method="get">
				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">
						用户名称
					</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" id="userName" name="userName"
							style="width: 100%" data-options="required:true">
					</div>
				</div>

				<div class="clear"></div>

				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">
						用户编码
					</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" id="userCode" name="userCode"
							style="width: 100%" data-options="required:true">
					</div>
				</div>
				<div class="clear"></div>
				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">用户密码</div>
					<div class="grid_8 val">
						<input name="passwd" validType="length[4,32]" style="width: 98%"
							class="easyui-validatebox" required="true" type="password"
							value="" />
					</div>
				</div>

				<div class="clear"></div>
				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">
						描述说明
					</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" name="description"
							validType="length[2,100]" style="width: 100%; height: 60px"
							data-options="multiline:true">
					</div>
				</div>
			</form>
			<div class="clear"></div>
			<div style="text-align: center; padding: 20px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					icon="icon-save" onclick="submitForm()" style="width: 80px">提交</a>
			</div>
		</div>
	</div>
	<div id="dlg2" class="easyui-dialog" title="角色分配"
		style="width: 850px; height: 350px;" closed="true" 
		buttons="#dlg-buttons"  style="display:none" data-options="modal:true,resizable:true">
			<div id="cc" class="easyui-layout" fit="true">
				<div data-options="region:'center',border:false" style="height:80%;">
					<table id="tt3">
						<thead>
							<tr>
								<th field="id" hidden="true"></th>
					            <th field="roleName" align="left" width="200">角色名称</th>
					            <th field="roleCode" align="left" width="200">角色编码</th>
					            <th field="createPerson" align="left" width="100">创建人</th>
					            <th field="createDate" align="left" width="200">创建日期</th>
					            <th field="description" align="left" width="300" editor="text">描述</th>
							</tr>
						</thead>
			        </table>
				</div>
			    <div data-options="region:'south',border:false" style="height:9%;text-align: center;margin:1% 0;">
			    	<a class="easyui-linkbutton" style="width: 100px;" onclick="confirm()" href="javascript:void(0)">确认</a>
	                <a class="easyui-linkbutton" style="width: 100px;" onclick="cancel()" href="javascript:void(0)">取消</a>
			    </div>
		    </div>
		</div>
</body>
</html>