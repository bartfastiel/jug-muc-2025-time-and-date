import static java.time.temporal.ChronoUnit.MINUTES;

void main() {
	var balloonPopped = OffsetDateTime.of(2025, 1, 13, 20, 0, 0, 0, ZoneOffset.ofHours(1));
	System.out.println(balloonPopped);

	var now = OffsetDateTime.now();
	System.out.println(now);

	var passed = MINUTES.between(balloonPopped, now);
	System.out.println("(vor " + passed + " Minuten)");
}
