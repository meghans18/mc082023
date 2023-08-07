import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import util.HolidayFinder;

/**
 * Test class for {@link HolidayFinder}
 */
public class HolidayFinderTest {
	@Test
	void testJulyFourthWeekday() {
		assertEquals(HolidayFinder.fourthOfJulyObservance(2023), LocalDate.of(2023, 7, 4));
	}
	
	@Test
	void testJulyFourthSaturday() {
		assertEquals(HolidayFinder.fourthOfJulyObservance(2020), LocalDate.of(2020, 7, 3));
	}
	
	@Test
	void testJulyFourthSunday() {
		assertEquals(HolidayFinder.fourthOfJulyObservance(2021), LocalDate.of(2021, 7, 5));
	}
	
	@Test
	void augestEndOnMonday() {
		assertEquals(HolidayFinder.laborDayObservance(2020), LocalDate.of(2020, 9, 7));
	}
	
	@Test
	void augestEndOnThursday() {
		assertEquals(HolidayFinder.laborDayObservance(2023), LocalDate.of(2023, 9, 4));
	}
}
