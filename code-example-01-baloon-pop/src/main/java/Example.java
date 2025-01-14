import static java.time.temporal.ChronoUnit.MINUTES;

void main() {

	var balloonPopped = ZonedDateTime.of(2025, 1, 13, 19, 16, 45, 0, ZoneId.of("Europe/Berlin"));
	System.out.println(balloonPopped);

	var now = ZonedDateTime.now();
	System.out.println(now);
	var minutesDiff = MINUTES.between(balloonPopped, now);

	System.out.println(minutesDiff);



}
