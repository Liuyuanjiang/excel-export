package cn.org.j2ee.excel.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

/**
 * 导出接口默认实现
 * @Author wuwz
 * @TypeName ExportBean
 * @param <T>
 */
public class DefaultAllowExport<T> implements AllowExport {
	//利用spring反射获取泛型的真实类型
	private Class<?> entityClass = ResolvableType.forClass(this.getClass()).getSuperType().getGeneric(0).resolve(); 
	
	@Override
	public List<Object> getExport(String value, String display) {
		List<Object> beans = new ArrayList<>();
		//利用spring反射组装导出集合
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(entityClass);
		for (PropertyDescriptor pd : pds) {
			//获取当前属性字段
			Field field = ReflectionUtils.findField(entityClass, pd.getName());
			if(field == null)
				continue;
			
			//获取配置注解
			ExportConfig exportConfig = field.getAnnotation(ExportConfig.class);
			if(exportConfig == null) 
				continue;
			
			//构建动态bean
			DynamicBean dynamicBean = new DynamicBean();
			dynamicBean
				.addProperty(value, String.class, field.getName())
				.addProperty(display, String.class, exportConfig.name())
				.addProperty("width", Short.class, exportConfig.width());
			beans.add(dynamicBean.getObject());
		}
		return beans;
	}

}
