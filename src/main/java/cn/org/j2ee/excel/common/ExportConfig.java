package cn.org.j2ee.excel.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel导出注解定义,作用于javabean的字段,用于标识具体的注解配置
 * @Author wuwz
 * @TypeName ExportConfig
 */
@Documented
@Target(ElementType.FIELD)//注解的作用目标(字段,枚举)
@Retention(RetentionPolicy.RUNTIME)//注解的保留策略(运行时级别,存在于class字节码中,可以通过反射获取)
public @interface ExportConfig {

	/**
	 * 导出列的显示名,用于处理Excel表头
	 * @return
	 */
	String name();
	
	/**
	 * 导出列的宽度
	 * @return
	 */
	short width();
}