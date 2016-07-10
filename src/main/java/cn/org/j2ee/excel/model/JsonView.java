package cn.org.j2ee.excel.model;

public class JsonView {
	
	private boolean status;
	private String message;
	private Object model;
	
	public JsonView(boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getModel() {
		return model;
	}
	public void setModel(Object model) {
		this.model = model;
	}
}
