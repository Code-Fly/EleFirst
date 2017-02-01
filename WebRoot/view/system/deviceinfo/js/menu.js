$(document).ready(function () {
     $("#tt").datagrid({
            url: _ctx + "system/menuinfo/listAllMenu.do",
            pagination: true,
            rownumbers: true,
            pageSize: 20,
            pageList: [20, 40, 60],
            singleSelect: false,
            fit: true,
            onLoadError: function () {
                jError("查询监测点信息错误！", {
                    VerticalPosition: 'center',
                    HorizontalPosition: 'center',
                    ShowOverlay: false
                });
            },
            toolbar: [{
	            text:'启用',
	            iconCls:'icon-add',
	            handler:enableMenu}
			        ,{
			            text:'禁用',
			            iconCls:'icon-save',
			            handler:disableMenu}
			        ]
        });
        
        function enableMenu(){
        	  
        	  var rows = $('#tt').datagrid("getChecked");
						if(rows.length == 0){
							jNotify("请选择记录！", {
								VerticalPosition : 'center',
								HorizontalPosition : 'center',
								ShowOverlay : false
							});
							return;
						}
						var processDefIds = [];
						for (var i = 0; i < rows.length; i++) {
							processDefIds.push(rows[i].menuid);
						}
						var ids = processDefIds.join(",");
        	  
	        	$.ajax({
	            url: _ctx + 'system/menuinfo/updatemenuinfo.do',
	            type: "post",//使用post方法访问后台
	            dataType: "json",
	            cache: false,
	            data: {
	                isEnable: 'Y',
	                idsJason: ids
	            },
	            success: function (msg) {
	                if ("success" == msg.errmsg) {
	                    jSuccess("启用菜单成功！", {
	                     VerticalPosition: 'center',
	                     HorizontalPosition: 'center',
	                     ShowOverlay: false
	                     });
	                    $('#tt').datagrid('reload');
	                } else if ("failed" == msg.errmsg) {
	                    jError("启用菜单失败！", {
	                     VerticalPosition: 'center',
	                     HorizontalPosition: 'center',
	                     ShowOverlay: false
	                     });
	                }
	            }
	         });
        }
        
        function disableMenu(){
        	var rows = $('#tt').datagrid("getChecked");
						if(rows.length == 0){
							jNotify("请选择记录！", {
								VerticalPosition : 'center',
								HorizontalPosition : 'center',
								ShowOverlay : false
							});
							return;
						}
						var processDefIds = [];
						for (var i = 0; i < rows.length; i++) {
							processDefIds.push(rows[i].menuid);
						}
						var ids = processDefIds.join(",");
        	$.ajax({
	            url: _ctx + 'system/menuinfo/updatemenuinfo.do',
	            type: "post",//使用post方法访问后台
	            dataType: "json",
	            cache: false,
	            data: {
	                isEnable: 'N',
	                idsJason: ids
	            },
	            success: function (msg) {
	                if ("success" == msg.errmsg) {
	                    jSuccess("禁用菜单成功！", {
	                     VerticalPosition: 'center',
	                     HorizontalPosition: 'center',
	                     ShowOverlay: false
	                     });
	                     $('#tt').datagrid('reload');
	                } else if ("failed" == msg.errmsg) {
	                    jError("禁用菜单失败！", {
	                     VerticalPosition: 'center',
	                     HorizontalPosition: 'center',
	                     ShowOverlay: false
	                     });
	                }
	            }
	         });
        }
});