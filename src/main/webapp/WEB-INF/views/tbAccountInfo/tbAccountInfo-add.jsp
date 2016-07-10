<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!--@Date: 2016-7-10 08:48:22, @Author: wuwz@live.com-->
<script type="text/javascript">	
$('#form').form({
	url : _ctx+'/tbAccountInfo/saveOrUpdate.do',
	onSubmit : function() {
		var isValid = $(this).form('validate');	
		if(!isValid) {
			parent.$.messager.show({
				title: '提示',
				msg: '请填写完整后再提交！',
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
				<label for="username">用户名：</label>
			</th>
			<td style="text-align: left;">
				<input name="username" class="easyui-textbox" id="username"
					data-options="required:true,missingMessage:'用户名不能为空',validType:'length[0,50]'" 
				 style="width:175px;" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="password">密码：</label>
			</th>
			<td style="text-align: left;">
				<input name="password" class="easyui-textbox" id="password"
					data-options="required:true,missingMessage:'密码不能为空',validType:'length[0,50]'" 
				 style="width:175px;" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="nickname">昵称：</label>
			</th>
			<td style="text-align: left;">
				<input name="nickname" class="easyui-textbox" id="nickname"
					data-options="validType:'length[0,50]'" 
				 style="width:175px;" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="email">邮箱：</label>
			</th>
			<td style="text-align: left;">
				<input name="email" class="easyui-textbox" id="email"
					data-options="validType:'length[0,50]'" 
				 style="width:175px;" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="phone">手机号：</label>
			</th>
			<td style="text-align: left;">
				<input name="phone" class="easyui-textbox" id="phone"
					data-options="validType:'length[0,50]'" 
				 style="width:175px;" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="qQ">QQ号：</label>
			</th>
			<td style="text-align: left;">
				<input name="qQ" class="easyui-textbox" id="qQ"
					data-options="validType:'length[0,50]'" 
				 style="width:175px;" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="address">地址：</label>
			</th>
			<td style="text-align: left;">
				<input name="address" class="easyui-textbox" id="address"
					data-options="validType:'length[0,50]'" 
				 style="width:175px;" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="createDate">创建日期：</label>
			</th>
			<td style="text-align: left;">
				<input name="createDate" class="easyui-datetimebox" id="createDate"
					data-options="editable:false" 
				 style="width:175px;" value="${nowDate}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="remark">备注：</label>
			</th>
			<td style="text-align: left;">
				<input name="remark" class="easyui-textbox" id="remark"
					data-options="validType:'length[0,300]',multiline:true" 
				 style="width:205px;height: 50px;" />
			</td>
		</tr>
				
	</table>
</form>