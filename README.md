# bartfastiel's presentation for Java User Group Munich about date and time

## Opener

* Willkommen zu unserem Abend über Date und Time in Java
* Wird ca 1 Stunde dauern
* Diese Stunde hat 60 Minuten
* Jede Minute hat 60 Sekunden
* Diese Stunde ist ein 24tel des Tages
* Der Tag ist ein 31tel des Monats (weil Januar)
* Der Tag ist ein 365tel des Jahres (weil kein Schaltjahr)

* Würde ich diesen Vortag an einem anderen Tag halten:
  * Wäre der Tag vielleicht ein 366tel des Jahres
  * Wäre der Tag vielleicht ein 28tel, 29tel, 30tel des Monats
  * Wäre die Stunde vielleicht ein 23tel oder 25tel des Tages (Sommerzeit-Winterzeit-Zeitumstellung)
  * Oder hätte eine der Minuten vielleicht 61 Sekunden (Schaltsekunde)

## Schaltsekunde

> On days that do have a leap second, the leap second is spread equally over the last 1000 seconds of the day, maintaining the appearance of exactly 86400 seconds per day.
> [docs.oracle.com java 23 java.time.Instant](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/time/Instant.html#:~:text=On%20days%20that%20do%20have%20a%20leap%20second%2C%20the%20leap%20second%20is%20spread%20equally%20over%20the%20last%201000%20seconds%20of%20the%20day%2C%20maintaining%20the%20appearance%20of%20exactly%2086400%20seconds%20per%20day.)
* Java versucht die Schaltsekunde für Entwickler zu verstecken
* An Tagen mit Schaltsekunden sind die letzten 1000 Sekunden des Tages länger (Uhr tickt langsamer)
* `Instant.parse("2016-12-31T23:59:60Z")` entspricht `Instant.parse("2016-12-31T23:59:59Z")`
