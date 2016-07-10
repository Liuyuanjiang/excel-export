/****@Date: 2016-07-10 17:42:21, @Author: wuwz@live.com****/

function addTbTest() {
	parent.$.modalDialog.openner_dataGrid = $grid;

	parent.$.modalDialog({
		title: '&nbsp;添加【测试信息】',
		iconCls: 'icon-add',
		width: 500,
		height: 400,
		href: _ctx + '/tbTest/addPage.do',
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function() {
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function() {
				parent.$.modalDialog.handler.dialog('close');
			}
		}]
	});
}


function editTbTest() {
	var selected = $grid.datagrid('getSelections');
	
	if(!selected) {
		parent.$.messager.alert('错误','请选择一行再编辑！','error');
		return;
	}
	
	if(selected.length > 1) {
		parent.$.messager.alert('错误','只能选中一个进行编辑！','error');
		return;
	}
	
	parent.$.modalDialog.openner_dataGrid = $grid;
	
	parent.$.modalDialog({
		title: '&nbsp;编辑【测试信息】',
		iconCls: 'icon-edit',
		width: 500,
		height: 400,
		href: _ctx + '/tbTest/editPage.do?id='+selected[0].id,
		buttons: [{
			text: '保存',
			iconCls: 'icon-ok',
			handler: function() {
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function() {
				parent.$.modalDialog.handler.dialog('close');
			}
		}]
	});
}

function removeTbTest() {
	var selected = $grid.datagrid('getSelections');
	
	if(!selected) {
		parent.$.messager.alert('错误','至少选择一行再删除！','error');
		return;
	}
	
	parent.$.messager.progress({
		title: '请稍后',
		text: '正在处理。。。'
	});
	
	var ids = new Array();
	$.each(selected,function(i,e) {
		ids.push(e.id);
	});
	
	parent.$.messager.confirm('询问','你选中了【'+ids.length+'】行,要删除选中的数据?',function(r) {
		
		if(r) {
			$.ajax({
				url: _ctx+'/tbTest/delete.do',
				data: {ids: ids},
				dataType: 'json',
				type: 'post',
				success: function(data) {
					parent.$.messager.progress('close');
				
					if(data) {
						parent.$.messager.show({
							title: '提示',
							msg: data.message,
							showType: 'slide'
						});
						
						$grid.datagrid('reload');
					}
				}
			});
		}
	});
}