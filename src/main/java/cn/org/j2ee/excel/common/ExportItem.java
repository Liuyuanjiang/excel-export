package cn.org.j2ee.excel.common;

import java.io.Serializable;

public class ExportItem implements Serializable {
	private static final long serialVersionUID = -3013516347890665762L;
	private Integer exportId;						/* 导出Excel项ID */	
	private String name;							/* 导出项名称，即实体属性名称 */
	private String display;							/* 导出项显示的名称，即在Excel文档中显示的列名称 */
	private Integer sort;							/* 导出项在Excel中的显示顺序 */
	private Short width;							/* 导出项在Excel中的列宽 */
	
	public Integer getExportId() {
		return exportId;
	}
	public void setExportId(Integer exportId) {
		this.exportId = exportId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Short getWidth() {
		return width;
	}
	public void setWidth(Short width) {
		this.width = width;
	}
	
	public static class Builder {
		private Integer exportId;		
		private String name;			
		private String display;		
		private Integer sort;			
		private Short width;	
		
		public Builder setExportId(Integer exportId) {
			this.exportId = exportId;
			return this;
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setDisplay(String display) {
			this.display = display;
			return this;
		}
		
		public Builder setSort(Integer sort) {
			this.sort = sort;
			return this;
		}
		
		public Builder setWidth(Short width) {
			this.width = width;
			return this;
		}
		
		public ExportItem create() {
			return new ExportItem(this);
		}
	}
	public ExportItem() {}
	private ExportItem(Builder builder) {
		this.exportId = builder.exportId;
		this.name = builder.name;
		this.display = builder.display;
		this.sort = builder.sort;
		this.width = builder.width;
	}
}
