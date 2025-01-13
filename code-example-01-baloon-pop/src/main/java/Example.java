import static java.time.temporal.ChronoUnit.MINUTES;

void main() {

	var baloon = OffsetDateTime.of(2025, 1, 13, 8,25,8, 0, ZoneOffset.ofHours(1));
	System.out.println(baloon);

	var now = OffsetDateTime.now();
	System.out.println(now);

	var diff = MINUTES.between(baloon, now);
	System.out.println(diff);


	var endOfWinter = ZonedDateTime.of(2025, 3, 30, 0,0,0, 0, ZoneId.of("Europe/Berlin"));
	System.out.println(endOfWinter);
	var startOfSummer = endOfWinter.plusDays(1);
	System.out.println(startOfSummer);

	var event = Instant.now();
	var event2 = Instant.ofEpochSecond(1817236182);

	var dur = Duration.between(event, event2);
	System.out.println(dur);
}
