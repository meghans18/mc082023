package models;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import util.HolidayFinder;
import util.ToolUtil;

/**
 * Class that handles the creation of the rental agreement
 * based on the user's inputs.
 */
public class RentalAgreement {
	private Tool tool;
	private int rentalDays;
	private LocalDate checkOutDate;
	private int discountPercent;
	private LocalDate dueDate;
	private PricingInfo pricingInfo;
	private long chargeDays;
	private BigDecimal preDiscountCharge;
	private BigDecimal discountAmount;
	private BigDecimal finalCharge;
	
	public RentalAgreement(Tool tool, int rentalDays, LocalDate checkOutDate, int discountPercent, LocalDate dueDate,
			PricingInfo pricingInfo, long chargeDays, BigDecimal preDiscountCharge, BigDecimal discountAmount,
			BigDecimal finalCharge) {
		this.tool = tool;
		this.rentalDays = rentalDays;
		this.checkOutDate = checkOutDate;
		this.discountPercent = discountPercent;
		this.dueDate = dueDate;
		this.pricingInfo = pricingInfo;
		this.chargeDays = chargeDays;
		this.preDiscountCharge = preDiscountCharge;
		this.discountAmount = discountAmount;
		this.finalCharge = finalCharge;
	}

	public RentalAgreement(Builder builder) {
		this.tool = ToolUtil.findTool(builder.toolCode);
		this.rentalDays = builder.rentalDays;
		this.checkOutDate = builder.checkOutDate;
		this.discountPercent = builder.discountPercent;
		this.dueDate = checkOutDate.plusDays(rentalDays);
		this.pricingInfo = ToolUtil.findPricingInfo(tool.getType());
		this.chargeDays = calculateChargeDays(checkOutDate, dueDate, pricingInfo);
		this.preDiscountCharge = calculatePreDiscountCharge(chargeDays, pricingInfo);
		this.discountAmount = calculateDiscountAmount(preDiscountCharge, discountPercent);
		this.finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);
	}
	
	/**
	 * Calculates the number of days the user will be charged for this tool.
	 * It could be a different amount than the total number of rental days 
	 * depending on weekends/holidays.
	 * @param checkoutDate: the date the tool was checked out
	 * @param dueDate: the date the tool is due
	 * @param pricingInfo: the pricing information associated with the tool
	 * @return the number of days the user should be charged for
	 */
	private long calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, PricingInfo pricingInfo) {
		long days =  checkoutDate.plusDays(1).datesUntil(dueDate.plusDays(1))
				.filter(day -> dayFilter(day, pricingInfo)).count();
		return days;
	}
	
	/**
	 * Helper method that decides if a day should be included in the
	 * total number of days the user can be charged. It checks for whether it's
	 * a holiday, weekend, or weekday, and if that tool should be charged on that 
	 * specific type of day.
	 * @param day: the day to look at in the range of check out days
	 * @param pricingInfo: the pricing information associated with the tool
	 * @return true if the user should be charged for that day, false if not
	 */
	private boolean dayFilter(LocalDate day, PricingInfo pricingInfo) {
		Set<DayOfWeek> weekendDays = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
		return !((day.equals(HolidayFinder.fourthOfJulyObservance(day.getYear())) && !pricingInfo.hasHolidayCharge()) ||
				(day.equals(HolidayFinder.laborDayObservance(day.getYear())) && !pricingInfo.hasHolidayCharge()) || 
				(weekendDays.contains(day.getDayOfWeek()) && !pricingInfo.hasWeekendCharge()) ||
				(!weekendDays.contains(day.getDayOfWeek()) && !pricingInfo.hasWeekdayCharge()));
	}
	
	/**
	 * Calculates the charges for the tool before the discount is applied
	 * @param chargeDays: the number of days the user should be charged for
	 * @param pricingInfo: the pricing info associated with the tool
	 * @return the pre-discount charge amount
	 */
	private BigDecimal calculatePreDiscountCharge(long chargeDays, PricingInfo pricingInfo) {
		return pricingInfo.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays)).setScale(4);
	}
	
	/**
	 * Calculates the total discount amount to take off of charge price
	 * @param preDiscount: the pre-discount charge amount
	 * @param discountPercent: the percentage to take off of the amount
	 * @return the amount to subtract from the pre-discount amount
	 */
	private BigDecimal calculateDiscountAmount(BigDecimal preDiscount, int discountPercent) {
		return preDiscount.multiply(BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100))).setScale(4);
	}
	
	/**
	 * Calculates the final charge, taking into account pre-discount amount and discount amount
	 * @param preDiscount: the charges for the tool pre-discount
	 * @param discountAmount: the discount amount to take off of the price
	 * @return the final charges for the user for their tool and checkout dates
	 */
	private BigDecimal calculateFinalCharge(BigDecimal preDiscount, BigDecimal discountAmount) {
		return preDiscount.subtract(discountAmount).setScale(4);
	}

	@Override
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/YY");
		NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.US);
		return String.format("""
				Tool Code: %s
				Tool Type: %s
				Tool Brand: %s
				Rental Days: %s
				Checkout Date: %s
				Due Date: %s
				Daily Rental Charge: %S
				Charge Days: %s
				Pre-discount Charge: %s
				Discount Percent: %s
				Discount Amount: %s
				Final Charge: %s
			""",
			this.tool.getCode(),
			this.tool.getType(),
			this.tool.getBrand(),
			this.rentalDays,
			dateFormatter.format(this.checkOutDate),
			dateFormatter.format(this.dueDate),
			numberFormatter.format(this.pricingInfo.getDailyCharge()),
			this.chargeDays,
			numberFormatter.format(this.preDiscountCharge),
			this.discountPercent + "%",
			numberFormatter.format(this.discountAmount),
			numberFormatter.format(this.finalCharge));
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(chargeDays, checkOutDate, discountAmount, discountPercent, dueDate, finalCharge,
				preDiscountCharge, pricingInfo, rentalDays, tool);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentalAgreement other = (RentalAgreement) obj;
		return chargeDays == other.chargeDays && Objects.equals(checkOutDate, other.checkOutDate)
				&& Objects.equals(discountAmount, other.discountAmount) && discountPercent == other.discountPercent
				&& Objects.equals(dueDate, other.dueDate) && Objects.equals(finalCharge, other.finalCharge)
				&& Objects.equals(preDiscountCharge, other.preDiscountCharge)
				&& Objects.equals(pricingInfo, other.pricingInfo) && rentalDays == other.rentalDays
				&& Objects.equals(tool, other.tool);
	}


	public static class Builder {
		private ToolCode toolCode;
		private int rentalDays;
		private LocalDate checkOutDate;
		private int discountPercent;
		
		public Builder() { }

		public Builder setToolCode(ToolCode toolCode) {
			this.toolCode = toolCode;
			return this;
		}

		public Builder setRentalDays(int rentalDays) {
			this.rentalDays = rentalDays;
			return this;
		}

		public Builder setCheckOutDate(LocalDate checkOutDate) {
			this.checkOutDate = checkOutDate;
			return this;
		}

		public Builder setDiscountPercent(int discountPercent) {
			this.discountPercent = discountPercent;
			return this;
		}
		
		public RentalAgreement build() {
			return new RentalAgreement(this);
		}
	}
}
