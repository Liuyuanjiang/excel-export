<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账号信息列表</title>
<%@ include file="../common/easyui.jsp" %>
</head>
<body>
<!--@Date: 2016-7-10 08:46:51, @Author: wuwz@live.com-->
<table id="tbAccountInfoController-grid" style="width:100%;height:100%;"></table>

<script type="text/javascript" src="${ctx}/resources/js/page-tbAccountInfo.js"></script>
<script type="text/javascript">
var $grid;
$(function(){
	$grid = $('#tbAccountInfoController-grid').datagrid({
		fitColumns: true,
		nowrap: true,
		striped: true,
		autoRowHeight: false,
		fit: true,
		url:'${ctx}/tbAccountInfo/getGridData.do',
		columns: [ [
			{field: 'ck',checkbox:'true',width:30},
				
			{field: "uuid",title: "唯一标识",width: 80},
				
			{field: "username",title: "用户名",width: 80},
				
			{field: "password",title: "密码",width: 80},
				
			{field: "nickname",title: "昵称",width: 80},
				
			{field: "email",title: "邮箱",width: 80},
				
			{field: "phone",title: "手机号",width: 80},
				
// 			{field: "QQ",title: "QQ号",width: 80},
				
			{field: "address",title: "地址",width: 80},
				
			{field: "createDate",title: "创建日期",width: 80},
				
			{field: "remark",title: "备注",width: 80},
				
		] ],
		toolbar : [{
			text:'刷新',
			iconCls:'icon-reload',
			handler:function(){
				$grid.datagrid('reload');
			}
		},{
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				addTbAccountInfo();
			}
		},'-',{
			text:'编辑选中行',
			iconCls:'icon-edit',
			handler:function(){
				editTbAccountInfo();
			}
		},'-',{
			text:'删除选中行',
			iconCls:'icon-no',
			handler:function(){
				removeTbAccountInfo();
			}
		 },'-',{
			text:'清除选中行',
			iconCls:'icon-undo',
			handler:function(){
				$grid.datagrid('clearSelections');
			}
	 	},'-',{
			text:'导出到Excel',
			iconCls:'icon-large-smartart',
			handler:function(){
				exportDialog();
			}
		 }
		],
		//pagination : true,
		rownumbers:true
	});
});
</script>
</body>
</html>