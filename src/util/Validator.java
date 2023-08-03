package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import models.ToolCode;

public class Validator {

	public static Optional<ToolCode> validateToolCode(String toolCode) {
		try {
			return Optional.of(ToolCode.valueOf(ToolCode.class, toolCode));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		} catch (NullPointerException e) {
			return Optional.empty();
		}
	}
	
	public static Optional<LocalDate> validateCheckOutDate(String checkOutDate) {
		try {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate date = LocalDate.parse(checkOutDate, dateFormatter);
			return Optional.of(date);
		} catch (DateTimeParseException e) {
			return Optional.empty();
		}
	}
	
	public static Optional<Integer> validateRentalDays(String rentalDays) {
		try {
			int rentalDaysValue = Integer.valueOf(rentalDays);
			if (rentalDaysValue > 0) {
				return Optional.of(rentalDaysValue);
			} else {
				return Optional.empty();
			}
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
	
	public static Optional<Integer> validateDiscount(String discount) {
		try {
			Double discountValue = Double.valueOf(discount);
			if (discountValue % 1 == 0) {
				return Optional.of(discountValue.intValue());
			} else {
				return Optional.empty();
			}
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
	
	public static boolean validateYesNo(String input) {
		return input.toUpperCase().equals("Y") || input.toUpperCase().equals("N");
	}
}
