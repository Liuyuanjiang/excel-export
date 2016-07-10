<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试信息列表</title>
<%@ include file="../common/easyui.jsp" %>
</head>
<body>
<!--@Date: 2016-07-10 17:42:21, @Author: wuwz@live.com-->
<table id="tbTestController-grid" style="width:100%;height:100%;"></table>

<script type="text/javascript" src="${ctx}/resources/js/page-tbTest.js"></script>
<script type="text/javascript">
var $grid;
$(function(){
	$grid = $('#tbTestController-grid').datagrid({
		fitColumns: true,
		nowrap: false,
		striped: true,
		fit: true,
		url:'${ctx}/tbTest/getGridData.do',
		columns: [ [
			{field: 'ck',checkbox:'true',width:30},
				
			{field: "id",title: "",width: 80},
				
			{field: "name",title: "姓名",width: 80},
				
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
				addTbTest();
			}
		},'-',{
			text:'编辑选中行',
			iconCls:'icon-edit',
			handler:function(){
				editTbTest();
			}
		},'-',{
			text:'删除选中行',
			iconCls:'icon-no',
			handler:function(){
				removeTbTest();
			}
		 },'-',{
			text:'清除选中行',
			iconCls:'icon-undo',
			handler:function(){
				$grid.datagrid('clearSelections');
			}
		}],
		pagination : true,
		rownumbers:true
	});
});
</script>
</body>
</html>