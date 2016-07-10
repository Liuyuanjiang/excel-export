# excel-export #
####基于java注解的Excel导出工具，可以灵活定制导出的列，包括列名，列宽等等;####


----------


### 使用例子 ###
1. 针对要支持的业务实体，继承`DefaultAllowExport`类：
 
> （一旦继承该类，则表明该实体允许导出，且可定制）

    public class UserModel extends DefaultAllowExport<UserModel> { //.. }

2. 为该实体可以导出的字段配置`@ExcelConfig`注解：
 
> （用于定制列名，列宽等属性）

	@ExportConfig(name="编号",width=40)
    private Integer userId;
	
	@ExportConfig(name="用户名",width=80)
	private String username;

	//getter and setter...



**待续。2016-7-10 18:25:55 wuwz@live.com.**