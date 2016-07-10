<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!--@Date: 2016-7-10 08:48:30, @Author: wuwz@live.com-->
<script type="text/javascript">
$(function() {
	$('#form').form({
		url : _ctx + '/tbAccountInfo/saveOrUpdate.do',
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
});
</script>
<form id="form" method="post">
	<table class="tb2" style="width:100%;">
						
		<tr>
			<th style="text-align: right;">
				<label for="uuid">唯一标识：</label>
			</th>
			<td style="text-align: left;">
				<input name="uuid" id="uuid" class="easyui-textbox" 
					data-options="required:true,missingMessage:'唯一标识不能为空',validType:'length[0,50]'" 
				style="width:175px" value="${model.uuid}" readonly="readonly" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="username">用户名：</label>
			</th>
			<td style="text-align: left;">
				<input name="username" id="username" class="easyui-textbox" 
					data-options="required:true,missingMessage:'用户名不能为空',validType:'length[0,50]'" 
				style="width:175px" value="${model.username}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="password">密码：</label>
			</th>
			<td style="text-align: left;">
				<input name="password" id="password" class="easyui-textbox" 
					data-options="required:true,missingMessage:'密码不能为空',validType:'length[0,50]'" 
				style="width:175px" value="${model.password}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="nickname">昵称：</label>
			</th>
			<td style="text-align: left;">
				<input name="nickname" id="nickname" class="easyui-textbox" 
					data-options="validType:'length[0,50]'" 
				style="width:175px" value="${model.nickname}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="email">邮箱：</label>
			</th>
			<td style="text-align: left;">
				<input name="email" id="email" class="easyui-textbox" 
					data-options="validType:'length[0,50]'" 
				style="width:175px" value="${model.email}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="phone">手机号：</label>
			</th>
			<td style="text-align: left;">
				<input name="phone" id="phone" class="easyui-textbox" 
					data-options="validType:'length[0,50]'" 
				style="width:175px" value="${model.phone}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="qQ">QQ号：</label>
			</th>
			<td style="text-align: left;">
				<input name="qQ" id="qQ" class="easyui-textbox" 
					data-options="validType:'length[0,50]'" 
				style="width:175px" value="${model.QQ}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="address">地址：</label>
			</th>
			<td style="text-align: left;">
				<input name="address" id="address" class="easyui-textbox" 
					data-options="validType:'length[0,50]'" 
				style="width:175px" value="${model.address}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="createDate">创建日期：</label>
			</th>
			<td style="text-align: left;">
				<input name="createDate" id="createDate" class="easyui-datetimebox" 
					data-options="editable:false" 
				style="width:175px" value="${model.createDate}" />
			</td>
		</tr>
							
		<tr>
			<th style="text-align: right;">
				<label for="remark">备注：</label>
			</th>
			<td style="text-align: left;">
				<input name="remark" id="remark" class="easyui-textbox" 
					data-options="validType:'length[0,300]',multiline:true" 
				style="width:205px;height: 50px;" value="${model.remark}" />
			</td>
		</tr>
				
	</table>
</form>