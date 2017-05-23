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
				icon="icon-add" title="分配资源" onclick="authorClick()">分配资源</a>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<table id="tt2" style="display: none">
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
	<div id="dlg" class="easyui-dialog" style="width: 300px; height: 270px; padding: 10px 20px"
		closed="true" buttons="#dlg-buttons" style="display:none"
		data-options="modal:true">
		<div class="container_12">
			<form id="ff" method="get">
				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">
						角色名称
					</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" id="roleName" name="roleName" 
							style="width: 100%" data-options="required:true">
					</div>
				</div>

				<div class="clear"></div>

				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">
						角色编码
					</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" id="roleCode" name="roleCode" 
							style="width: 100%" data-options="required:true">
					</div>
				</div>

				<div class="clear"></div>

				<div style="margin-bottom: 20px">
					<div class="grid_4 lbl">
						描述说明
					</div>
					<div class="grid_8 val">
						<input class="easyui-textbox" id="description" name="description"
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

		<div id="dlg2" class="easyui-dialog"
			title="添加角色"
			style="width: 800px; height: 400px;" closed="true"
			buttons="#dlg-buttons" style="display:none"
			data-options="modal:true,resizable:true">
			<div id="tt1" class="easyui-tabs" fit="true" />
		</div>

		<div id="dlg3" class="easyui-dialog"
			title="编辑角色"
			style="width: 800px; height: 400px;" closed="true"
			buttons="#dlg-buttons" style="display:none"
			data-options="modal:true,resizable:true">
			<div id="tt3" class="easyui-tabs" fit="true" />
		</div>
	</div>
	</div>
	</div>
</body>
</html>
<script type="text/javascript">
    $().ready(function () {
        $("#tt2").datagrid({
            url: _ctx + '/system/role/info/list.do',
            pagination: true,
            rownumbers: true,
            pageSize: 20,
            pageList: [5, 10, 20, 30],
            singleSelect: true,
            fit: true,
            loadMsg: "正在加载...",
            onLoadError: function () {
                jError("查询角色失败", {
                    VerticalPosition: 'center',
                    HorizontalPosition: 'center',
                    ShowOverlay: false
                });
            }
        });
    });
    
    //操作标示
    var flag = "";

    //添加角色
    function addClick() {
        flag = "add";
        $('#dlg').dialog('open').dialog('setTitle',"新增角色");
        $('#ff').form('clear');
        $("#roleCode").textbox('readonly',false);
        $("#roleName").textbox('readonly',false);
        $("#description").textbox('readonly',false);
    }

    //编辑角色
    function editClick() {
        flag = "modify";
        var singlerow = $('#tt2').datagrid('getSelected');
        var index = null;
        if (singlerow) {
            index = $('#tt2').datagrid('getRowIndex', singlerow);
        } else {
            jNotify("请选择一条记录", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
            return;
        }
        $('#dlg').dialog('open').dialog('setTitle',"编辑角色");
        $('#ff').form('load', {
            roleName: singlerow.roleName,
            roleCode: singlerow.roleCode,
            description: singlerow.description
        });
        $("#roleCode").textbox('readonly',true);
        $("#roleName").textbox('readonly',true);
        
        if(singlerow.rolecode=="sysmanager"){
      	  $("#description").textbox('readonly',true);
        }else{
          $("#description").textbox('readonly',false);
        }
    }

    //新增角色
    function submitForm() {
        var url = "";
        var roleId = "";
        if ("add" == flag) {
            url = _ctx + 'system/role/info/addroleinfo.do';
        } else if ("modify" == flag) {
            var singlerow = $('#tt2').datagrid('getSelected');
            roleId = singlerow.id
            url = _ctx + 'system/role/info/updateroleinfo.do';
        } else {
            return;
        }
        $('#ff').form('submit', {
            url: url,
            queryParams: {
                roleId: roleId
            },
            success: function (data) {
                var d = eval('(' + data + ')');
                if ("success" == d.errmsg) {
                    $('#dlg').dialog('close');
                    //重新查询数据
                    $('#tt2').datagrid("reload");
                    jSuccess("保存成功", {
                        VerticalPosition: 'center',
                        HorizontalPosition: 'center',
                        ShowOverlay: false
                    });
                } else {
                        jError("保存失败", {
                            VerticalPosition: 'center',
                            HorizontalPosition: 'center',
                            ShowOverlay: false
                        });
                }
            },
            onLoadError: function () {
                jError("保存失败", {
                    VerticalPosition: 'center',
                    HorizontalPosition: 'center',
                    ShowOverlay: false
                });
            }
        });
    }

    function clearForm() {
        $('#ff').form('clear');
    }

    //删除角色实例
    function deleteClick() {
        var singlerow = $('#tt2').datagrid('getSelected');

        var index = null;
        if (singlerow) {
            index = $('#tt2').datagrid('getRowIndex', singlerow);
        } else {
            jNotify("请选择一条记录", {
                VerticalPosition: 'center',
                HorizontalPosition: 'center',
                ShowOverlay: false
            });
            return;
        }

        $.messager.confirm("确认", "删除角色", function (r) {
            if (r) {
                $.ajax({
                    url: _ctx + 'system/role/info/delete.do',
                    type: "post",//使用post方法访问后台
                    dataType: "json",
                    cache: false,
                    data: {
                        id: singlerow.id
                    },
                    success: function (msg) {
                        if ("success" == msg.errmsg) {
                            jSuccess("删除成功", {
                                VerticalPosition: 'center',
                                HorizontalPosition: 'center',
                                ShowOverlay: false
                            });
                            $('#tt2').datagrid("reload");
                        } else {
                            jError("删除失败", {
                                VerticalPosition: 'center',
                                HorizontalPosition: 'center',
                                ShowOverlay: false
                            });
                        }
                    }
                });
            }
        });
    }

    //刷新页面
    function refreshClick() {
        window.location.reload();
    }
</script>