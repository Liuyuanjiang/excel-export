package cn.org.j2ee.excel.common;

import java.util.List;

/**
 * 允许导出接口,具体的导出业务实体请实现此接口
 * @Author wuwz
 * @TypeName AllowExport
 */
public interface AllowExport {

	List<Object> getExport(String value,String display);
}
