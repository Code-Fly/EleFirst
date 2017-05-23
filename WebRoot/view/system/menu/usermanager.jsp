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
			url : _ctx + 'system/user/info/list.do',
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
	
	//刷新页面
	function refreshClick() {
		window.location.reload();
	}
	
	//添加用户实例
	function addClick() {
		flag = "add";
		$('#dlg').dialog('open').dialog('setTitle','新增用户');
		$('#ff').form('clear');
	}
	
	//编辑用户
	function editClick() {
		flag = "modify";
		var singlerow = $('#tt2').datagrid('getSelected');
		var index = null;
		if (singlerow) {
			index = $('#tt2').datagrid('getRowIndex', singlerow);
		} else {
			jNotify("请选择一条用户记录", {
				VerticalPosition : 'center',
				HorizontalPosition : 'center',
				ShowOverlay : false
			});
			return;
		}
		
		
		$('#dlgUpdate').dialog('open').dialog('setTitle','编辑用户');
		$('#ffs').form('clear');
		$('#ffs').form('load', {
			userName : singlerow.userName,
			userCode : singlerow.userCode,
			description : singlerow.description
		});
	}
	
	//新增用户
	function submitForm() {
		var url = "";
		var userId = "";
		if ("add" == flag) {
			url = _ctx +  'system/user/info/addpersoninfo.do';
			$('#ff').form('submit', {
				url : url,
				success : function(data) {
					var d = eval('(' + data + ')');
					if ("success" == d.errmsg) {
						$('#dlg').dialog('close');
						//重新查询数据
						$('#tt2').datagrid("reload");
						jSuccess("保存成功", {
							VerticalPosition : 'center',
							HorizontalPosition : 'center',
							ShowOverlay : false
						});
					} else {
						if('-1' == d.errcode){
							jError("保存失败", {
								VerticalPosition : 'center',
								HorizontalPosition : 'center',
								ShowOverlay : false
							});
						}else if('-2' == d.errcode){
							jError("用户名已存在", {
								VerticalPosition : 'center',
								HorizontalPosition : 'center',
								ShowOverlay : false
							});
						}
					}
				},
				onLoadError : function() {
					jError("保存失败", {
						VerticalPosition : 'center',
						HorizontalPosition : 'center',
						ShowOverlay : false
					});
				}
			});
		} else if ("modify" == flag) {
			var singlerow = $('#tt2').datagrid('getSelected');
			userId = singlerow.id
			url = _ctx +  'system/user/info/updatePersonInfo.do';
			
			$('#ffs').form('submit', {
				url : url,
				queryParams : {
					userId : userId
				},
				success : function(data) {
					var d = eval('(' + data + ')');
					if ("success" == d.errmsg) {
						$('#dlgUpdate').dialog('close');
						//重新查询数据
						$('#tt2').datagrid("reload");
						jSuccess("保存成功", {
							VerticalPosition : 'center',
							HorizontalPosition : 'center',
							ShowOverlay : false
						});
					} else {
						if('-1' == d.errcode){
							jError("保存失败", {
								VerticalPosition : 'center',
								HorizontalPosition : 'center',
								ShowOverlay : false
							});
						}else if('-2' == d.errcode){
							jError("用户已存在", {
								VerticalPosition : 'center',
								HorizontalPosition : 'center',
								ShowOverlay : false
							});
						}
					}
				},
				onLoadError : function() {
					jError("保存失败", {
						VerticalPosition : 'center',
						HorizontalPosition : 'center',
						ShowOverlay : false
					});
				}
			});
		} else {
			return;
		}
		
	}

	function clearForm() {
		$('#ff').form('clear');
		$('#ffs').form('clear');
	}
	
	//删除用户
	function deleteClick() {
		var singlerow = $('#tt2').datagrid('getSelected');
		var index = null;
		if (singlerow) {
			index = $('#tt2').datagrid('getRowIndex', singlerow);
		} else {
			jNotify("请选择一条用户记录", {
				VerticalPosition : 'center',
				HorizontalPosition : 'center',
				ShowOverlay : false
			});
			return;
		}
		
		$.messager.confirm("确认", "删除用户", function(r){
            if (r){
            	$.ajax({
        			url :  _ctx +  'system/user/info/delete.do',
        			type : "post",//使用post方法访问后台
        			dataType : "json",
        			cache : false,
        			data : {
        				id : singlerow.id
        			},
        			success : function(msg) {
        				if ("success" == msg.errmsg) {
        					jSuccess("删除成功", {
        						VerticalPosition : 'center',
        						HorizontalPosition : 'center',
        						ShowOverlay : false
        					});
        					$('#tt2').datagrid("reload");
        				}else{
        					jError("删除失败", {
        						VerticalPosition : 'center',
        						HorizontalPosition : 'center',
        						ShowOverlay : false
        					});
        				}
        			}
        		});
            }
        });
	}
</script>