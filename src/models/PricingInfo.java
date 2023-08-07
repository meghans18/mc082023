package models;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class that details the pricing information associated with a tool type.
 */
public class PricingInfo {
	private String toolType;
	private BigDecimal dailyCharge;
	private boolean hasWeekdayCharge;
	private boolean hasWeekendCharge;
	private boolean hasHolidayCharge;
	
	public PricingInfo(
			String toolType, 
			BigDecimal dailyCharge, 
			boolean hasWeekdayCharge, 
			boolean hasWeekendCharge,
			boolean hasHolidayCharge
	) {
		this.toolType = toolType;
		this.dailyCharge = dailyCharge;
		this.hasWeekdayCharge = hasWeekdayCharge;
		this.hasWeekendCharge = hasWeekendCharge;
		this.hasHolidayCharge = hasHolidayCharge;
	}

	public String getToolType() {
		return toolType;
	}

	public BigDecimal getDailyCharge() {
		return dailyCharge;
	}

	public boolean hasWeekdayCharge() {
		return hasWeekdayCharge;
	}

	public boolean hasWeekendCharge() {
		return hasWeekendCharge;
	}

	public boolean hasHolidayCharge() {
		return hasHolidayCharge;
	}

	@Override
	public String toString() {
		return "PricingInfo [toolType=" + toolType + ", dailyCharge=" + dailyCharge + ", hasWeekdayCharge="
				+ hasWeekdayCharge + ", hasWeekendCharge=" + hasWeekendCharge + ", hasHolidayCharge=" + hasHolidayCharge
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dailyCharge, hasHolidayCharge, hasWeekdayCharge, hasWeekendCharge, toolType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PricingInfo other = (PricingInfo) obj;
		return Objects.equals(dailyCharge, other.dailyCharge) && hasHolidayCharge == other.hasHolidayCharge
				&& hasWeekdayCharge == other.hasWeekdayCharge && hasWeekendCharge == other.hasWeekendCharge
				&& Objects.equals(toolType, other.toolType);
	}

}
