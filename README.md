# excel-export
基于java注解的Excel导出工具，可以灵活定制导出的列，包括列名，列宽等等;


### 使用例子

-- 1. javabean继承DefaultAllowExport类：

`
public class TbAccountInfoModel extends DefaultAllowExport<TbAccountInfoModel> 
		implements java.io.Serializable {
	//...		
}
`

-- 2. 为支持导出的字段配置@ExportConfig注解：

`
public class TbAccountInfoModel extends DefaultAllowExport<TbAccountInfoModel> 
		implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
	
	/***唯一标识***/
	@ExportConfig(name="唯一标识",width=400)
	private String uuid;
	/***用户名***/
	@ExportConfig(name="用户名",width=120)
	private String username;
	/***密码***/
	@ExportConfig(name="密码",width=80)
	private String password;
	/***昵称***/
	@ExportConfig(name="昵称",width=80)
	private String nickname;
	
	//getter and setter ...		
}
`


--待续。 2016-7-10 19:56:07 wuwz@live.com