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
				icon="icon-sysroleassign" title="分配角色" onclick="groupClick()">分配角色</a>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="tt2">
			<thead>
				<tr>
					<th field="userid" hidden="true"></th>
					<th field="username" align="left" width="200">用户名</th>
					<th field="usercode" align="left" width="200">用户编码</th>
					<th field="createperson" align="left" width="100">创建者</th>
					<th field="createdate" align="left" width="200">创建日期</th>
					<th field="description" align="left" width="300" editor="text">描述</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 350px; height: 300px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" style="display:none" data-options="modal:true">
		<div class="container_12">
			<form id="ff" method="post">
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
			<form id="ffs" method="post">
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
				<div class="clear"></div>
				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">
						描述
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


	<div id="editPassword" class="easyui-dialog"
		style="width: 400px; height: 200px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" style="display:none" data-options="modal:true">
		<div class="container_12">
			<form id="ffpassword" method="post">
				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">新密码</div>
					<div class="grid_8 val">
						<input id="newPassword" name="newPassword"
							validType="length[4,32]" style="width: 98%"
							class="easyui-validatebox" required="true" type="password">
					</div>
				</div>

				<div class="clear"></div>
				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">确认密码</div>
					<div class="grid_8 val">
						<input id="comformPassword" name="comformPassword"
							validType="length[4,32]" style="width: 98%"
							class="easyui-validatebox" required="true" type="password">
					</div>
				</div>

				<div class="clear"></div>
			</form>

			<div class="clear"></div>

			<div style="text-align: center; padding: 20px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitFormPassword()" style="width: 80px">提交</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearForm()" style="width: 80px">重置</a>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$().ready(function() {
		$("#tt2").datagrid({
			url : '',
			pagination : true,
			rownumbers : true,
			pageSize : 20,
			pageList : [ 5, 10, 20, 30 ],
			singleSelect : true,
			fit : true,
			loadMsg : "正在加载...",
			onLoadError : function() {
				jError("查询用户信息失败", {
					VerticalPosition : 'center',
					HorizontalPosition : 'center',
					ShowOverlay : false
				});
			}
		});
	});
</script>