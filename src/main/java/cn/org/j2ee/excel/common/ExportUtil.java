package cn.org.j2ee.excel.common;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.org.j2ee.excel.common.ExcelUtil.ExcelType;
import cn.org.j2ee.excel.model.TbAccountInfoModel;

/**
 * Excel导出工具
 * @Author wuwz
 * @TypeName ExportUtil
 * @param <T>
 */
public class ExportUtil<T> {
	

	public void exportExcel(ServletOutputStream outputStream,ExcelType excelType,String sheetName,List<ExportItem> exportItems, List<T> data) {
		
		//创建工作薄对象
		Workbook workbook = ExcelUtil.getWorkbook(excelType);
		//创建工作表对象
		Sheet sheet = workbook.createSheet(sheetName);
		int index = 0;
		try {
			//创建表格标题行
			ExcelUtil.createHeader(workbook, sheet, exportItems, excelType, index++);
			//遍历集合，产生数据行
			ExcelUtil.createBody(TbAccountInfoModel.class, data, workbook, sheet, exportItems, excelType, index++);
			
			//生成内存字节输出流
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public  void setDownloadResponse(HttpServletRequest request, HttpServletResponse response,
			String fileName,ExcelType excelType) throws Exception {
		response.setContentType(ExcelUtil.getContentType(excelType));
		response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
	}
}
