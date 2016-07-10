/****@Date: 2016-7-10 08:48:10, @Author: wuwz@live.com****/

function addTbAccountInfo() {
	parent.$.modalDialog.openner_dataGrid = $grid;
	parent.$.modalDialog({
		title: '&nbsp;添加【账号信息】',
		iconCls: 'icon-add',
		width: 400,
		height: 400,
		href: _ctx + '/tbAccountInfo/addPage.do',
		buttons: [{
			id: 'btn-save',
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


function editTbAccountInfo() {
	var selected = $grid.datagrid('getSelections');
	
	if(selected.length == 0) {
		parent.$.messager.alert('错误','请选择一行再编辑！','error');
		return;
	}
	
	if(selected.length > 1) {
		parent.$.messager.alert('错误','只能选中一个进行编辑！','error');
		return;
	}
	
	parent.$.modalDialog.openner_dataGrid = $grid;
	
	parent.$.modalDialog({
		title: '&nbsp;编辑【账号信息】',
		iconCls: 'icon-edit',
		width: 400,
		height: 400,
		href: _ctx + '/tbAccountInfo/editPage.do?id='+selected[0].uuid,
		buttons: [{
			id: 'btn-save',
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

function removeTbAccountInfo() {
	var selected = $grid.datagrid('getSelections');
	
	if(selected.length == 0) {
		parent.$.messager.alert('错误','至少选择一行再删除！','error');
		return;
	}
	parent.$.messager.progress({
		title: '请稍后',
		text: '正在处理。。。'
	});
	
	
	var ids = new Array();
	$.each(selected,function(i,e) {
		ids.push(e.uuid);
	});
	
	parent.$.messager.confirm('询问','你选中了【'+ids.length+'】行,要删除选中的数据?',function(r) {
		
		if(r) {
			$.ajax({
				url: _ctx+'/tbAccountInfo/delete.do',
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

function exportDialog() {
	
	parent.$.modalDialog.openner_dataGrid = $grid;
	
	parent.$.modalDialog({
		title: '&nbsp;导出【账号信息】',
		iconCls: 'icon-large-smartart',
		width: 600,
		height: 500,
		href: _ctx + '/tbAccountInfo/exportPage.do',
		buttons: [{
			id: 'btn-save',
			text: '开始导出',
			iconCls: 'icon-ok',
			handler: function() {
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		},{
			text: '关闭页面',
			iconCls: 'icon-cancel',
			handler: function() {
				parent.$.modalDialog.handler.dialog('close');
			}
		}]
	});
}