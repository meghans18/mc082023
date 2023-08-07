package models;

import java.util.Objects;

/**
 * Class that holds information for a specific tool.
 */
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

	@Override
	public int hashCode() {
		return Objects.hash(brand, code, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tool other = (Tool) obj;
		return Objects.equals(brand, other.brand) && code == other.code && Objects.equals(type, other.type);
	}
	
}
