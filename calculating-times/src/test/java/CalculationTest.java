import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;

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
}
