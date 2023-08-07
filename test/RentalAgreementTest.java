import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import models.PricingInfo;
import models.RentalAgreement;
import models.Tool;
import models.ToolCode;
import util.ToolUtil;

/**
 * Test class for {@link RentalAgreement}
 */
public class RentalAgreementTest {
	@Test
	public void testCase1() {
		ToolCode toolCode = ToolCode.JAKR;
		LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
		int rentalDays = 5;
		int discountPercent = 100;
		RentalAgreement rentalAgreementActual = new RentalAgreement.Builder()
				.setToolCode(toolCode)
				.setCheckOutDate(checkoutDate)
				.setRentalDays(rentalDays)
				.setDiscountPercent(discountPercent)
				.build();
		
		Tool tool = ToolUtil.findTool(toolCode);
		PricingInfo pricingInfo = ToolUtil.findPricingInfo(tool.getType());
		RentalAgreement rentalAgreementExpected = new RentalAgreement(
				ToolUtil.findTool(toolCode),
				rentalDays,
				checkoutDate,
				discountPercent,
				LocalDate.of(2015, 9, 8),
				pricingInfo,
				2,
				BigDecimal.valueOf(5.98).setScale(4),
				BigDecimal.valueOf(5.98).setScale(4),
				BigDecimal.valueOf(0).setScale(4)
			);
		
		assertEquals(rentalAgreementActual, rentalAgreementExpected);
	}
	
	@Test
	public void testCase2() {
		ToolCode toolCode = ToolCode.LADW;
		LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
		int rentalDays = 3;
		int discountPercent = 10;
		RentalAgreement rentalAgreementActual = new RentalAgreement.Builder()
				.setToolCode(toolCode)
				.setCheckOutDate(checkoutDate)
				.setRentalDays(rentalDays)
				.setDiscountPercent(discountPercent)
				.build();
		
		Tool tool = ToolUtil.findTool(toolCode);
		PricingInfo pricingInfo = ToolUtil.findPricingInfo(tool.getType());
		RentalAgreement rentalAgreementExpected = new RentalAgreement(
				ToolUtil.findTool(toolCode),
				rentalDays,
				checkoutDate,
				discountPercent,
				LocalDate.of(2020, 7, 5),
				pricingInfo,
				2,
				BigDecimal.valueOf(3.98).setScale(4),
				BigDecimal.valueOf(0.398).setScale(4),
				BigDecimal.valueOf(3.582).setScale(4)
			);
		
		assertEquals(rentalAgreementActual, rentalAgreementExpected);
	}
	
	@Test
	public void testCase3() {
		ToolCode toolCode = ToolCode.CHNS;
		LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
		int rentalDays = 5;
		int discountPercent = 25;
		RentalAgreement rentalAgreementActual = new RentalAgreement.Builder()
				.setToolCode(toolCode)
				.setCheckOutDate(checkoutDate)
				.setRentalDays(rentalDays)
				.setDiscountPercent(discountPercent)
				.build();
		
		Tool tool = ToolUtil.findTool(toolCode);
		PricingInfo pricingInfo = ToolUtil.findPricingInfo(tool.getType());
		RentalAgreement rentalAgreementExpected = new RentalAgreement(
				ToolUtil.findTool(toolCode),
				rentalDays,
				checkoutDate,
				discountPercent,
				LocalDate.of(2015, 7, 7),
				pricingInfo,
				3,
				BigDecimal.valueOf(4.47).setScale(4),
				BigDecimal.valueOf(1.1175).setScale(4),
				BigDecimal.valueOf(3.3525).setScale(4)
			);
		
		assertEquals(rentalAgreementActual, rentalAgreementExpected);
	}
	
	@Test
	public void testCase4() {
		ToolCode toolCode = ToolCode.JAKD;
		LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
		int rentalDays = 6;
		int discountPercent = 0;
		RentalAgreement rentalAgreementActual = new RentalAgreement.Builder()
				.setToolCode(toolCode)
				.setCheckOutDate(checkoutDate)
				.setRentalDays(rentalDays)
				.setDiscountPercent(discountPercent)
				.build();
		
		Tool tool = ToolUtil.findTool(toolCode);
		PricingInfo pricingInfo = ToolUtil.findPricingInfo(tool.getType());
		RentalAgreement rentalAgreementExpected = new RentalAgreement(
				ToolUtil.findTool(toolCode),
				rentalDays,
				checkoutDate,
				discountPercent,
				LocalDate.of(2015, 9, 9),
				pricingInfo,
				3,
				BigDecimal.valueOf(8.97).setScale(4),
				BigDecimal.valueOf(0).setScale(4),
				BigDecimal.valueOf(8.97).setScale(4)
			);
		
		assertEquals(rentalAgreementActual, rentalAgreementExpected);
	}
	
	@Test
	public void testCase5() {
		ToolCode toolCode = ToolCode.JAKR;
		LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
		int rentalDays = 9;
		int discountPercent = 0;
		RentalAgreement rentalAgreementActual = new RentalAgreement.Builder()
				.setToolCode(toolCode)
				.setCheckOutDate(checkoutDate)
				.setRentalDays(rentalDays)
				.setDiscountPercent(discountPercent)
				.build();
		
		Tool tool = ToolUtil.findTool(toolCode);
		PricingInfo pricingInfo = ToolUtil.findPricingInfo(tool.getType());
		RentalAgreement rentalAgreementExpected = new RentalAgreement(
				ToolUtil.findTool(toolCode),
				rentalDays,
				checkoutDate,
				discountPercent,
				LocalDate.of(2015, 7, 11),
				pricingInfo,
				5,
				BigDecimal.valueOf(14.95).setScale(4),
				BigDecimal.ZERO.setScale(4),
				BigDecimal.valueOf(14.95).setScale(4)
			);
		
		assertEquals(rentalAgreementActual, rentalAgreementExpected);
	}
	
	@Test
	public void testCase6() {
		ToolCode toolCode = ToolCode.JAKR;
		LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
		int rentalDays = 4;
		int discountPercent = 50;
		RentalAgreement rentalAgreementActual = new RentalAgreement.Builder()
				.setToolCode(toolCode)
				.setCheckOutDate(checkoutDate)
				.setRentalDays(rentalDays)
				.setDiscountPercent(discountPercent)
				.build();
		
		Tool tool = ToolUtil.findTool(toolCode);
		PricingInfo pricingInfo = ToolUtil.findPricingInfo(tool.getType());
		RentalAgreement rentalAgreementExpected = new RentalAgreement(
				ToolUtil.findTool(toolCode),
				rentalDays,
				checkoutDate,
				discountPercent,
				LocalDate.of(2020, 7, 6),
				pricingInfo,
				1,
				BigDecimal.valueOf(2.99).setScale(4),
				BigDecimal.valueOf(1.495).setScale(4),
				BigDecimal.valueOf(1.495).setScale(4)
			);
		
		assertEquals(rentalAgreementActual, rentalAgreementExpected);
	}
}
