$().ready(function() {
		$("#tt2").datagrid({
			url : _ctx + '/system/role/info/list.do',
			pagination : true,
			rownumbers : true,
			pageSize : 20,
			pageList : [ 5, 10, 20, 30 ],
			singleSelect : true,
			fit : true,
			loadMsg : "正在加载...",
			onLoadError : function() {
				jError("查询角色失败", {
					VerticalPosition : 'center',
					HorizontalPosition : 'center',
					ShowOverlay : false
				});
			}
		});
	});

	//操作标示
	var flag = "";

	//添加角色
	function addClick() {
		flag = "add";
		$('#dlg').dialog('open').dialog('setTitle', "新增角色");
		$('#ff').form('clear');
		$("#roleCode").textbox('readonly', false);
		$("#roleName").textbox('readonly', false);
		$("#description").textbox('readonly', false);
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
				VerticalPosition : 'center',
				HorizontalPosition : 'center',
				ShowOverlay : false
			});
			return;
		}
		$('#dlg').dialog('open').dialog('setTitle', "编辑角色");
		$('#ff').form('load', {
			roleName : singlerow.roleName,
			roleCode : singlerow.roleCode,
			description : singlerow.description
		});
		$("#roleCode").textbox('readonly', true);
		$("#roleName").textbox('readonly', true);

		if (singlerow.rolecode == "sysmanager") {
			$("#description").textbox('readonly', true);
		} else {
			$("#description").textbox('readonly', false);
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
			url : url,
			queryParams : {
				roleId : roleId
			},
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
					jError("保存失败", {
						VerticalPosition : 'center',
						HorizontalPosition : 'center',
						ShowOverlay : false
					});
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
				VerticalPosition : 'center',
				HorizontalPosition : 'center',
				ShowOverlay : false
			});
			return;
		}

		$.messager.confirm("确认", "删除角色", function(r) {
			if (r) {
				$.ajax({
					url : _ctx + 'system/role/info/delete.do',
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
						} else {
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

	//刷新页面
	function refreshClick() {
		window.location.reload();
	}
	
	//分配区域
	function areaClick(){
		var singlerow = $('#tt2').datagrid('getSelected');
		var index = null;
		if (singlerow) {
			index = $('#tt2').datagrid('getRowIndex', singlerow);
		} else {
			jNotify("请选择一条记录", {
				VerticalPosition : 'center',
				HorizontalPosition : 'center',
				ShowOverlay : false
			});
			return;
		}
		var roleId = singlerow.id;
		$('#dlg4').dialog('open');
		$("#tt4").datagrid({
			url : _ctx + '/system/area/info/queryAreaByRoleId.do',
			pagination : true,
			rownumbers : true,
			fit : true,
			fitColumns: true,
			singleSelect:true,
			selectOnCheck : true,
			checkOnSelect : true,
			queryParams : {
				roleId : roleId
			},
			onLoadSuccess : function(row, data){ 
				console.log(row);
				$.each(row.rows, function(index,item){
					if(item.checked){
						$('#tt4').datagrid('checkRow', index);
					}
				});
			},
			pageSize : 10,
			pageList : [ 5, 10, 20, 30 ],
			loadMsg : "正在加载...",
			onLoadError : function() {
				jError("查询失败", {
					VerticalPosition : 'center',
					HorizontalPosition : 'center',
					ShowOverlay : false
				});
			}
		});
	}
	
	//确定
	function confirm(){
		//获取所有check
		var rows =$('#tt4').datagrid("getChecked");
		var areaIds = [];
		for(var i=0;i<rows.length;i++){
			areaIds.push(rows[i].areaId);
		}
		var areaIdsStr = areaIds.join(",")
		var singlerow = $('#tt2').datagrid('getSelected');
		var roleId = singlerow.id;
		$.ajax({
			url : _ctx + '/system/area/info/addRoleAreaMap.do',
			type : "post",//使用post方法访问后台
			dataType : "json",
			cache : false,
			data : {
				roleId : roleId,
				areaIdsStr : areaIdsStr
			},
			success : function(msg) {
				if ("success" == msg.errmsg) {
					$('#dlg4').dialog('close');
					jSuccess("保存成功", {
						VerticalPosition : 'center',
						HorizontalPosition : 'center',
						ShowOverlay : false
					});
				} else if ("failed" == msg.errmsg) {
					jError("保存失败", {
						VerticalPosition : 'center',
						HorizontalPosition : 'center',
						ShowOverlay : false
					});
				}
			}
		});
	}
	
	//取消
	function cancel(thisObj){
		$('#dlg4').dialog('close');
	}