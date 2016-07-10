package cn.org.j2ee.excel.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

/**
 * 基于Spring的动态bean构建工具
 * @Author wuwz
 * @TypeName DynamicBean
 */
public class DynamicBean {
	private Map<String, Class<?>> propMap = new HashMap<String, Class<?>>();
	
	private Map<String, Object> valueMap = new HashMap<String, Object>();
	
	/**
	 * 为当前bean动态添加一个属性,支持链式写法
	 * @param name
	 * @param type
	 * @param value
	 * @return
	 */
	public DynamicBean addProperty(String name, Class<?> type, Object value) {
		propMap.put(name, type);
		valueMap.put(name, value);
		return this;
	}
	
	/**
	 * 获取构建的bean
	 * @return
	 */
	public Object getObject() {
		BeanGenerator beanGenerator = new BeanGenerator();
		for (Entry<String, Class<?>> entry : propMap.entrySet()) {
			beanGenerator.addProperty(entry.getKey(), entry.getValue());
		}
		Object obj = beanGenerator.create();
		BeanMap beanMap = BeanMap.create(obj);
		for (Entry<String, Object> entry : valueMap.entrySet()) {
			beanMap.put(entry.getKey(), entry.getValue());
		}
		return obj;
	}
	
	/**
	 * 序列化Json字符串
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (valueMap.size() > 0) {
			buffer.append("{");
		}
		for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
			buffer.append(entry.getKey()).append(":");
			if (propMap.get(entry.getKey()) == String.class) {
				buffer.append("'");
			}
			buffer.append(entry.getValue());
			if (propMap.get(entry.getKey()) == String.class) {
				buffer.append("'");
			}
			buffer.append(",");
		}
		if (buffer.length() > 0) {
			buffer
				.deleteCharAt(buffer.length() - 1)
				.append("}");
		}
		return buffer.toString();
	}
}
