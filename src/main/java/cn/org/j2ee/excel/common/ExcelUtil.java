package cn.org.j2ee.excel.common;


import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;

/**
 * 
 * Excel读写工具类
 * @Author wuwz
 * @TypeName ExcelUtil
 */
public abstract class ExcelUtil {
	/**
	 * 导出类型
     */
    public enum ExcelType {
    	/** Excel2003版 */	EXCEL2003,
    	/** Excel2007版+ */	EXCEL2007;
    	
    	@Override
    	public String toString() {
    		if (this == EXCEL2003) {
    			return "Excel2003版";
    		} else {
    			return "Excel2007版+";
    		}
    	}
    	/**
    	 * 导出类型json数据
    	 */
    	public static String buildJson(String value,String display) {
			StringBuffer buffer = new StringBuffer("[");
			for (ExcelType excelType : ExcelType.values()) {
				buffer
					.append("{" + value + ":\"")
					.append(excelType.name())
					.append("\"," + display + ":\"")
					.append(excelType)
					.append("\"},");
			}
			buffer.deleteCharAt(buffer.length() - 1);
			buffer.append("]");
			return buffer.toString();
    	}
    	
    	public static List<DynamicBean> buildList(String value,String display) {
    		List<DynamicBean> list = new ArrayList<DynamicBean>();
    		for (ExcelType excelType : ExcelType.values()) {
    			DynamicBean bean = new DynamicBean();
    			bean.addProperty(value, String.class, excelType.name())
    				.addProperty(display, String.class, excelType);
    			list.add(bean);
			}
    		return list;
    	}
    }
    
    /**
     * 获取excel的workbook对象
     * @param excelType excel文件类型
     * @return workbook对象
     */
	public static Workbook getWorkbook(ExcelType excelType) {
		if (excelType == ExcelType.EXCEL2003) {
			return new HSSFWorkbook();
		} else {
			return new XSSFWorkbook();
		}
	}
	
	/**
	 * 从指定输入流创建工作薄对象
	 * @param inputStream 输入流对象
	 * @return 工作薄对象
	 */
	public static Workbook getWorkbook(File file) {
		Workbook workbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));//2003
		} catch (Exception e) {
			try {
				workbook = new XSSFWorkbook(new FileInputStream(file));//2003以上
			} catch (Exception e1) {
				throw new RuntimeException("不能读取有效的Excel数据！");
			}
		}
		return workbook;
	}	

	/**
	 * 获取excel后缀名
	 * @param excelType excel文件类型
	 * @return 后缀名
	 */
	public static String getExcelSuffix(ExcelType excelType) {
		if (excelType == ExcelType.EXCEL2003) {
			return ".xls";
		} else {
			return ".xlsx";
		}
	}
	
	/**
	 * 获取标题单元格样式
	 * @param workbook 工作薄对象
	 * @param excelType excel文件类型
	 * @return 单元格样式
	 */
	public static CellStyle getHeadCellStyle(Workbook workbook, ExcelType excelType) {
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		if (excelType == ExcelType.EXCEL2003) {
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充模式
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框为细边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框为细边框
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框为细边框
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框为细边框
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//中间对齐
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		} else {
			short palIndex = 12;
			cellStyle.setFillForegroundColor(palIndex);
			cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);//填充模式
			cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框为细边框
			cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框为细边框
			cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//下边框为细边框
			cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框为细边框
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//中间对齐
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//加粗
		}
		font.setFontHeightInPoints((short)12);//字体大小
		font.setColor(HSSFColor.WHITE.index);
		//应用标题字体到标题样式
		cellStyle.setFont(font);
		
		return cellStyle;
	}
	
	/**
	 * 获取正文单元格样式
	 * @param workbook 工作薄对象
	 * @param excelType excel文件类型
	 * @return 单元格样式
	 */
	public static CellStyle getBodyCellStyle(Workbook workbook, ExcelType excelType) {
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		if (excelType == ExcelType.EXCEL2003) {
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充模式
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框为细边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框为细边框
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框为细边框
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框为细边框
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//水平对齐方式为左对齐
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直对齐方式为居中对齐
		} else {
			short palIndex = 15;
			cellStyle.setFillForegroundColor(palIndex);
			cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);//填充模式
			cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框为细边框
			cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框为细边框
			cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//下边框为细边框
			cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框为细边框
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);//水平对齐方式为左对齐
			cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直对齐方式为居中对齐
		}
		font.setFontHeightInPoints((short)12);//字体大小
		//应用标题字体到标题样式
		cellStyle.setFont(font);
		return cellStyle;
	}
	
	/**
	 * 创建Logo
	 * @param workbook 工作薄
	 * @param sheet 工作表
	 * @param excelType 导出类型
	 * @param logoFilePath Logo文件路径
	 * @throws IOException 
	 */
	public static void createExportExcelLogo(Workbook workbook, Sheet sheet, ExcelType excelType, String logoFilePath, int... index) throws IOException {
		Row row = sheet.createRow(index.length > 0 ? index[0] : 0);
		File file = new File(logoFilePath);
		row.setHeightInPoints(ImageIO.read(file).getHeight());
		ClientAnchor anchor;
		if (excelType == ExcelType.EXCEL2003) {
			anchor = new HSSFClientAnchor(0,0,0,0,(short)0,0,(short)4,1);
		} else {
			anchor = new XSSFClientAnchor(0,0,0,0,(short)0,0,(short)4,1);
		}
	    anchor.setAnchorType(2);
		//在工作薄中添加添加图片
		byte[] value = FileUtils.readFileToByteArray(file);
		int pictureId = workbook.addPicture(value, XSSFWorkbook.PICTURE_TYPE_JPEG);
		//绘制图片
		sheet.createDrawingPatriarch().createPicture(anchor, pictureId);
	}
	
	/**
	 * 创建标题行
	 * @param workbook 工作薄
	 * @param sheet 工作表
	 * @param exportItems 导出项
	 * @param excelType 导出类型
	 * @param index 标题行所在行索引
	 */
	public static void createHeader(Workbook workbook, Sheet sheet, List<ExportItem> exportItems, ExcelType excelType, int... index) {
		Row row = sheet.createRow(index.length > 0 ? index[0] : 1);
		for (int i = 0; i < exportItems.size(); i++) {
			ExportItem exportItem = exportItems.get(i);
			Cell cell = row.createCell(i);
			sheet.setColumnWidth(i, (short)(exportItem.getWidth() * 35.7));
			cell.setCellStyle(getHeadCellStyle(workbook, excelType));
			cell.setCellValue(exportItem.getDisplay());
		}
	}
	
	/**
	 * 创建数据行
	 * @param datas 数据
	 * @param workbook 工作薄
	 * @param sheet 工作表
	 * @param exportItems 导出项
	 * @param excelType 导出类型
	 * @param index 数据行所在行索引
	 * @throws Exception
	 */
	public static <T> void createBody(Class<?> entityClass, List<T> datas, Workbook workbook, Sheet sheet, List<ExportItem> exportItems, ExcelType excelType, int... index) throws Exception {
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(entityClass);
		Pattern p = Pattern.compile("^//d+(//.//d+)?$");
		int rowIndex = index.length > 0 ? index[0] : 2;
		for (T t : datas) {
			Row row = sheet.createRow(rowIndex++);
			for (int i = 0; i < exportItems.size(); i++) {
				ExportItem exportItem = exportItems.get(i);
				Cell cell = row.createCell(i);
				cell.setCellStyle(getBodyCellStyle(workbook, excelType));
				for (PropertyDescriptor pd : pds) {
					if (pd.getName().equals(exportItem.getName())) {
						String value = pd.getReadMethod().invoke(t).toString();
						if (p.matcher(value).matches()) {
							cell.setCellValue(Double.parseDouble(value));
						} else {
							cell.setCellValue(value);
						}
						break;
					}
				}
			}
		}
	}
	
	/**
	 * 获取导出文件的内容类型
	 * @param excelType excel文件类型
	 * @return 内容类型
	 */
	public static String getContentType(ExcelType excelType) {
		if (excelType == ExcelType.EXCEL2003) {
			return "application/vnd.ms-excel";
		} else {
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		}
	}
	
	/**
	 * 读取Excel文件数据
	 * @param file Excel文件
	 * @param sheetNum Excel中第几个工作表
	 * @param startRow Excel中工作表里开始的行
	 * @param endRow Excel中工作表里结束的行
	 * @param startCell Excel中工作表里开始的列
	 * @param endCell Excel中工作表里结束的列
	 * @return 指定行、列区域的数据
	 * @throws FileNotFoundException 
	 */
	public static List<List<String>> readExcel(File file, int sheetNum, int startRow, int endRow, int startCell, int endCell) throws FileNotFoundException {
		List<List<String>> lists = new ArrayList<List<String>>();
		Workbook workbook = getWorkbook(file);
		if (workbook == null) {
			return lists;
		}
		Sheet sheet = workbook.getSheetAt(sheetNum);
		if (sheet != null) {
			if (endRow == -1) {
				endRow = sheet.getPhysicalNumberOfRows();
			}
			if (endCell == -1) {
				endCell = sheet.getRow(startRow - 1).getPhysicalNumberOfCells();
			}
		 	for (int i = startRow - 1; i < endRow; i++) {
		 		List<String> list = new ArrayList<String>();
		 		Row row  = sheet.getRow(i);
		 		if (row != null) {
			 		for (int j = startCell - 1; j < endCell; j++) {
			 			Cell cell = row.getCell(j);
			 			if (cell != null) {
			 				list.add(cell.getStringCellValue());
			 			}
			 		}
		 		}
		 		lists.add(list);
		 	}
		}
		return lists;
	}
	
	/**
	 * 读取Excel文件数据
	 * @param file Excel文件
	 * @param sheetNum Excel中第几个工作表
	 * @param startRow Excel中工作表里开始的行
	 * @param startCell Excel中工作表里开始的列
	 * @return 指定行、列区域的数据
	 * @throws FileNotFoundException 
	 */
	public static List<List<String>> readExcel(File file,int sheetNum, int startRow, int startCell) throws FileNotFoundException {
		return readExcel(file,sheetNum,startRow,-1,startCell,-1);
	}
}
