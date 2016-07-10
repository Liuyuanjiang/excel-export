<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!--@Date: 2016-7-10 08:48:38, @Author: wuwz@live.com-->
<!-- easyui 中没有listbox控件，只有自己实现 -->
<div class="easyui-layout" border="false" style="width:96%;height:95%;overflow: hidden;margin:10px;">
	<div data-options="region:'west'" style="width: 40%;">
		<table id="gdProperties" class="easyui-datagrid" 
			data-options="border:false,url: '${ctx}/tbAccountInfo/getExportProperties.do'"
		>
			<thead>
				<tr>
					<th field="ck" checkbox="true"/>
					<th field="id" align="center" hidden="true">编号</th>
					<th field="text" width="190" align="center">所有列名</th>
				</tr>
			</thead>
		</table>
	</div>
	<div data-options="region:'east'" style="width: 40%;">
		<table id="gdExport" class="easyui-datagrid" data-options="border:false">
			<thead>
				<tr>
					<th field="ck" checkbox="true"/>
					<th field="id" align="center" hidden="true">编号</th>
					<th field="text" width="190" align="center">导出列名</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div data-options="region:'center'">
		<div align="center" style="height: 200px;margin-top: 80px;">
			<a href="javascript:addItem()" class="easyui-linkbutton">添加 &gt;&gt;</a>
		</div>
		<div align="center">
			<a href="javascript:removeItem()" class="easyui-linkbutton">&lt;&lt; 移除</a>
		</div>
	</div>
</div>
<form id="form" method="post"></form>
<script type="text/javascript">
var $leftGrid = $('#gdProperties');
var $rightGrid = $('#gdExport');

function addItem() {
	var data = $leftGrid.datagrid('getSelections');
	
	$.each(data,function(i,e) {
		//添加到右侧
		$rightGrid.datagrid('appendRow',e);
		
		var rowIndex = $leftGrid.datagrid('getRowIndex',e);
		//移除左侧
		$leftGrid.datagrid('deleteRow',rowIndex);
	});
	
}
function removeItem() {
	var data = $rightGrid.datagrid('getSelections');
	
	$.each(data,function(i,e) {
		//添加到左侧
		$leftGrid.datagrid('appendRow',e);
		
		var rowIndex = $rightGrid.datagrid('getRowIndex',e);
		//移除右侧
		$rightGrid.datagrid('deleteRow',rowIndex);
		
		
		if($rightGrid.datagrid('getData').total == 0) {
			$leftGrid.datagrid('reload');
		}
	});
}

$('#form').form({
	url: "",
	onSubmit : function() {
		var rightData = $rightGrid.datagrid('getData');
		
		if(rightData.total == 0) {
			parent.$.messager.alert('错误','至少选择一个导出列!','error');
			return false;
		}
		
		//把用户的选择的导出项处理成约定的格式
		var exportItems = '';
		$.each(rightData.rows,function(i,e) {
			exportItems += e.id + "," + e.text + "," + e.width + "|";
		});
		parent.$.messager.progress({
			title: '请稍后',
			text: '正在处理。。。'
		});
		
		
		//发送导出请求(以隐藏的iframe域实现,类似easyui的form提交)
		var iframe = $('<iframe />', {name: 'iframe'});
       	iframe.attr('src', window.ActiveXObject ? 'javascript:false' : 'about:blank');
       	iframe.css({position : 'absolute', top : -1000, left : -1000});

       	var myForm = $('<form />', {
       		action: '${ctx}/tbAccountInfo/doExport.do' ,
       		method: 'post' ,
       		target: 'iframe'
       	});

       	var hidden = $('<input />', {
   			type: 'hidden' ,
   			name: 'exportConfig' ,
   			value: exportItems
   		});
   		hidden.appendTo(myForm);
   		
       	myForm.appendTo(iframe);
       	iframe.appendTo($('body'));

       	myForm.submit();
		
		parent.$.messager.progress('close');
		
		parent.$.modalDialog.handler.dialog('close');
		
// 		window.open('${ctx}/tbAccountInfo/doExport.do?exportConfig='+exportItems);

		return false;
	}
});

</script>
