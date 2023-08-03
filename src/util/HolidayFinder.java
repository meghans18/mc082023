package util;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Class for finding the two holidays observed by the tool checkout system:
 * July 4th and Labor Day.
 */
public class HolidayFinder {
	
	/**
	 * Find the date that July 4th is observed on in a given year.
	 * If July 4th is on a Saturday, it is observed the Friday before.
	 * If July 4th is on a Sunday, it is observed the Monday after.
	 * @param year: The year to search in.
	 * @return The date of observance for 4th of July in the given year.
	 */
	public static LocalDate fourthOfJulyObservance(int year) {
		LocalDate julyFourth = LocalDate.of(year, 7, 4);
		DayOfWeek dayOfWeek = julyFourth.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SATURDAY) {
			return LocalDate.of(year, 7, 3);
		} else if (dayOfWeek == DayOfWeek.SUNDAY) {
			return LocalDate.of(year, 7, 5);
		} else {
			return julyFourth;
		}
	}
	
	/**
	 * Find the date that Labor Day is observed on, 
	 * which is always the first Monday of September.
	 * @param year: The year to search in.
	 * @return The date of observance of Labor Day for the given year.
	 */
	public static LocalDate laborDayObservance(int year) {
		LocalDate augustEnd = LocalDate.of(year, 8, 31);
		DayOfWeek dayOfWeek = augustEnd.getDayOfWeek();
		LocalDate laborDay;
		switch (dayOfWeek) {
			case SUNDAY: 
				laborDay = LocalDate.of(year, 9, 1);
				break;
			case MONDAY: 
				laborDay = LocalDate.of(year, 9, 7);
				break;
			case TUESDAY: 
				laborDay = LocalDate.of(year, 9, 6);
				break;
			case WEDNESDAY: 
				laborDay = LocalDate.of(year, 9, 5);
				break;
			case THURSDAY: 
				laborDay = LocalDate.of(year, 9, 4);
				break;
			case FRIDAY: 
				laborDay = LocalDate.of(year, 9, 3);
				break;
			default: 
				laborDay = LocalDate.of(year, 9, 2); // case for Saturday
				break;
		}
		return laborDay;
	}
}
