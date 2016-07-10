<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="${ctx}/resources/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${ctx}/resources/jquert-easyui-ext.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/easyui/themes/default/easyui.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/easyui/themes/icon.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/default.css"/>
<script type="text/javascript">
var _ctx = '${ctx}';

//回车提交表单
document.onkeydown = function(e){ 
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
        $('#form').submit();//处理事件
    }
}
</script>