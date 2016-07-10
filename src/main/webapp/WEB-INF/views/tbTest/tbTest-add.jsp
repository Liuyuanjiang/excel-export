<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!--@Date: 2016-07-10 17:42:21, @Author: wuwz@live.com-->
<script type="text/javascript">	
$('#form').form({
	url : _ctx+'/tbTest/saveOrUpdate.do',
	onSubmit : function() {
		var isValid = $(this).form('validate');	
		if(!isValid) {
			parent.$.messager.show({
				title: '提示',
				msg: '请填写完整后再提交!',
				showType: 'slide'
			});
		}				
		return isValid;
	},
	success : function(result) {
		result = $.parseJSON(result);
		if (result.status) {
			parent.$.messager.show({
				title: '提示',
				msg: result.message,
				showType: 'slide'
			});
			parent.$.modalDialog.openner_dataGrid.datagrid('reload');
			parent.$.modalDialog.handler.dialog('close');
		} else {
			parent.$.messager.alert('错误', result.message, 'error');
		}
	}
});
</script>
<form id="form" method="post">
	<table class="tb2" style="width:100%;">
					
		<tr>
			<th style="text-align: right;">
				<label for="name">姓名：</label>
			</th>
			<td style="text-align: left;">
				<input name="name" class="easyui-textbox" id="name"
					data-options="validType:'length[0,255]'" 
				 style="width:175px;" />
			</td>
		</tr>
				
	</table>
</form>