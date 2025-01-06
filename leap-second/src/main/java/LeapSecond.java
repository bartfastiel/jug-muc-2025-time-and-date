void main() {
	Instant start = Instant.parse("2016-12-31T23:59:59Z");
	Instant end = Instant.parse("2016-12-31T23:59:60Z");
	System.out.println("Start: " + start);
	System.out.println("End: " + end);
	System.out.println("Duration: " + (end.getEpochSecond() - start.getEpochSecond()) + " seconds");
}
