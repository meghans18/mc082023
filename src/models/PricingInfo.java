package models;

public class PricingInfo {
	private String toolType;
	private double dailyCharge;
	private boolean hasWeekdayCharge;
	private boolean hasWeekendCharge;
	private boolean hasHolidayCharge;
	
	public PricingInfo(
			String toolType, 
			double dailyCharge, 
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

	public double getDailyCharge() {
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
	
}
