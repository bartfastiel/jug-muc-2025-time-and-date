import static java.time.temporal.ChronoUnit.MINUTES;

void main() {
	var balloonPopped = LocalDateTime.of(2025, 1, 13, 20, 0, 0, 0);
	System.out.println(balloonPopped);

	var now = LocalDateTime.now();
	System.out.println(now);

	var passed = MINUTES.between(balloonPopped, now);
	System.out.println("(vor " + passed + " Minuten)");
}
