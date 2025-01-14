import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CalculationTest {

	@Test
	void instant_has_no_plus_days() {
		assertFalse(
				stream(Instant.class.getMethods())
						.anyMatch(method -> method.getName().equals("plusDays"))
		);
	}

	@Test
	void localDateTime_simply_adds_days() {
		assertEquals(
				LocalDateTime.parse("2025-03-30T14:00:00"),
				LocalDateTime.parse("2025-03-29T14:00:00").plusDays(1)
		);
	}

	@Test
	void localDateTime_always_takes_24_hours_as_1_day() {
		assertEquals(
				LocalDateTime.parse("2025-03-30T14:00:00"),
				LocalDateTime.parse("2025-03-29T14:00:00").plusHours(24)
		);
	}

	@Test
	void zonedDateTime_switches_time_zone() {
		assertEquals(
				ZonedDateTime.parse("2025-03-30T14:00:00+02:00[Europe/Paris]"),
				ZonedDateTime.parse("2025-03-29T14:00:00+01:00[Europe/Paris]").plusDays(1)
		);
	}

	@Test
	void zonedDateTime_takes_24_hours_as_1_day_1_hour() {
		assertEquals(
				ZonedDateTime.parse("2025-03-30T15:00:00+02:00[Europe/Paris]"),
				ZonedDateTime.parse("2025-03-29T14:00:00+01:00[Europe/Paris]").plusHours(24)
		);
	}

	@Test
	void offsetDateTime_is_unaware_of_daylight_saving() {
		assertEquals(
				OffsetDateTime.parse("2025-03-30T14:00:00+01:00"),
				OffsetDateTime.parse("2025-03-29T14:00:00+01:00").plusDays(1)
		);
	}

	@Test
	void offsetDateTime_always_takes_24_hours_as_1_day() {
		assertEquals(
				OffsetDateTime.parse("2025-03-30T14:00:00+01:00"),
				OffsetDateTime.parse("2025-03-29T14:00:00+01:00").plusHours(24)
		);
	}
}
