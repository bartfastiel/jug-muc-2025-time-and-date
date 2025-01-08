import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see Instant
 */
class LeapSecondTest {

	@Test
	void java_parses_leap_second_to_second_59() {
		assertEquals(
				Instant.parse("2016-12-31T23:59:59Z"),
				Instant.parse("2016-12-31T23:59:60Z")
		);
	}

	@Test
	void java_jumps_over_leap_second() {
		assertEquals(
				Instant.parse("2016-12-31T23:59:59Z").plusSeconds(1),
				// you might have guessed Instant.parse("2016-12-31T23:59:60Z")
				Instant.parse("2017-01-01T00:00:00Z")
		);
	}

	@Test
	void testDurationCalculation() {
		assertEquals(1,
				Instant.parse("2017-01-01T00:00:00Z").getEpochSecond() -
						Instant.parse("2016-12-31T23:59:59Z").getEpochSecond()
		);
	}
}
