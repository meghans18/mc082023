package models;

public class Tool {
	private ToolCode code;
	private String type;
	private String brand;
	
	public Tool(ToolCode code, String type, String brand) {
		this.code = code;
		this.type = type;
		this.brand = brand;
	}
	
	public ToolCode getCode() {
		return code;
	}
	
	public String getType() {
		return type;
	}
	
	public String getBrand() {
		return brand;
	}
	
}
