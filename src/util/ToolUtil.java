package util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import models.PricingInfo;
import models.Tool;
import models.ToolCode;

public class ToolUtil {
	
	/**
	 * This is essentially generating our data. If this were an actual application,
	 * this would be stored in a database and a repository class would be used
	 * to fetch this information for the application.
	 * @return A map of tool codes to objects containing the rest of the tool's information.
	 */
	public static Map<ToolCode, Tool> generateToolMap() {
		Map<ToolCode, Tool> toolCodeToToolMap = new HashMap<ToolCode, Tool>();
		toolCodeToToolMap.put(ToolCode.CHNS, new Tool(ToolCode.CHNS, "Chainsaw", "Stihl"));
		toolCodeToToolMap.put(ToolCode.LADW, new Tool(ToolCode.LADW, "Ladder", "Werner"));
		toolCodeToToolMap.put(ToolCode.JAKD, new Tool(ToolCode.JAKD, "Jackhammer", "DeWalt"));
		toolCodeToToolMap.put(ToolCode.JAKR, new Tool(ToolCode.JAKR, "Jackhammer", "Ridgid"));
		return toolCodeToToolMap;
	}
	
	/**
	 * Same as above, essentially generating the pricing information for each tool type.
	 * @return A map of tool type to pricing information for that tool
	 */
	public static Map<String, PricingInfo> generatePricingInfoMap() {
		Map<String, PricingInfo> pricingInfoMap = new HashMap<String, PricingInfo>();
		pricingInfoMap.put("Ladder", new PricingInfo("Ladder", BigDecimal.valueOf(1.99), true, true, false));
		pricingInfoMap.put("Chainsaw", new PricingInfo("Chainsaw", BigDecimal.valueOf(1.49), true, false, true));
		pricingInfoMap.put("Jackhammer", new PricingInfo("Jackhammer", BigDecimal.valueOf(2.99), true, false, false));
		return pricingInfoMap;
	}
	
	/**
	 * Finds a specific tool object given the tool code
	 * @param toolCode: the code of the tool
	 * @return an object containing the rest of the tool's information
	 */
	public static Tool findTool(ToolCode toolCode) {
		Map<ToolCode, Tool> toolMap = generateToolMap();
		return toolMap.get(toolCode);
	}
	
	/**
	 * Finds a specific pricing information object for a given tool type
	 * @param toolType: the type of the tool
	 * @return an object containing the pricing information for that specific tool type
	 */
	public static PricingInfo findPricingInfo(String toolType) {
		Map<String, PricingInfo> pricingInfoMap = generatePricingInfoMap();
		return pricingInfoMap.get(toolType);
	}
}
