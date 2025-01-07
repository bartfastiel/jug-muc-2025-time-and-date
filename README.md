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

## Vorstellung

* Vor 20 Jahren habe ich bei einer großen Versicherung Java gelernt
* Und danach z.B. bei einem Stromnetzbetreiber ein System entwickelt um den internationalen Handelspartnern die zu übertragenden Strommengen pro Stunde zu melden
* Und für Münchens öffentliche Verkehrsmittel ein System zur Protokollierung von Unfällen geschrieben
* Derzeit arbeite ich in einem Forschungsprojekt an einem SmartHome-System
* Disclaimer: Aber dieser Vortrag ist privat und hat nicht mit meinem aktuellen oder früheren Arbeitgebern zu tun

* Oft der Gedanke: nehmen wir doch eine einfache Lösung für Datum und Uhrzeit
* "Was soll da schon schiefgehen?"

* Nehmen wir doch einfach durchgehende long
* Oder durchgehend LocalDateTime
* "Was soll da schon schiefgehen?"

* Ich sage es vor, ihr sagt es nach: "Was soll da schon schiefgehen?"
* Alle: "Was soll da schon schiefgehen?"

* Und das ist auch das Ziel des Vortrags:
  * Awareness für verschiedene Fallstricke schaffen
  * Überzeugen, dass man die Java-API nutzen sollte und nicht selbst implementieren

## Schaltsekunde

> On days that do have a leap second, the leap second is spread equally over the last 1000 seconds of the day, maintaining the appearance of exactly 86400 seconds per day.
> [docs.oracle.com java 23 java.time.Instant](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/time/Instant.html#:~:text=On%20days%20that%20do%20have%20a%20leap%20second%2C%20the%20leap%20second%20is%20spread%20equally%20over%20the%20last%201000%20seconds%20of%20the%20day%2C%20maintaining%20the%20appearance%20of%20exactly%2086400%20seconds%20per%20day.)
* Java versucht die Schaltsekunde für Entwickler zu verstecken
* An Tagen mit Schaltsekunden sind die letzten 1000 Sekunden des Tages länger (Uhr tickt langsamer)
* `Instant.parse("2016-12-31T23:59:60Z")` entspricht `Instant.parse("2016-12-31T23:59:59Z")`
