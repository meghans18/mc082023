import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import models.ToolCode;
import util.Validator;

/**
 * Test class for {@link Validator}
 */
public class ValidatorTest {
	@Test
	public void validateToolCodeInvalid() {
		assertEquals(Validator.validateToolCode("AFEKEJJ"), Optional.empty());
	}
	
	@Test
	public void validateToolCodeValid() {
		assertEquals(Validator.validateToolCode("CHNS"), Optional.of(ToolCode.CHNS));
	}
	
	@Test
	public void validateCheckoutDateInvalid() {
		assertEquals(Validator.validateCheckOutDate("01-01-2023"), Optional.empty());
	}
	
	@Test
	public void validateCheckoutDateValid() {
		assertEquals(Validator.validateCheckOutDate("01/01/23"), Optional.of(LocalDate.of(2023, 1, 1)));
	}
	
	@Test
	public void validateRentalDaysInvalid() {
		assertEquals(Validator.validateRentalDays("AFEKEJJ"), Optional.empty());
	}
	
	@Test
	public void validateRentalDaysInvalid2() {
		assertEquals(Validator.validateRentalDays("-20"), Optional.empty());
	}
	
	@Test
	public void validateRentalDaysValid() {
		assertEquals(Validator.validateRentalDays("5"), Optional.of(5));
	}
	
	@Test
	public void validateDiscountAmountInvalid() {
		assertEquals(Validator.validateDiscount("101"), Optional.empty());
	}
	
	@Test
	public void validateDiscountAmountInvalid2() {
		assertEquals(Validator.validateDiscount("39.48"), Optional.empty());
	}
	
	@Test
	public void validateDiscountAmountValid() {
		assertEquals(Validator.validateDiscount("5"), Optional.of(5));
	}
	
	@Test
	public void validateYesNoInvalid() {
		assertEquals(Validator.validateYesNo("o"), Optional.empty());
	}
	
	@Test
	public void validateYesNoValid() {
		assertEquals(Validator.validateYesNo("y"), Optional.of(true));
	}
	
	@Test
	public void validateYesNoValid2() {
		assertEquals(Validator.validateYesNo("n"), Optional.of(false));
	}
}
