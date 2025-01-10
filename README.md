# bartfastiel's presentation for Java User Group Munich about date and time

## Luftballon platzen lassen

* in Java: Zeitpunkt eines Ereignisses speichern
* welchen Datentyp nehmen?
* Jemand bitte: Genauen Zeitpunkt des Platzens merken

* *Platzt*

* Wie viel Uhr war es?
* Wie speichern wir so etwas? (Sinnbildlich für Serverausfall, Klick auf "Ich stimme zu", ...)
  * Mit Zeitzone?
  * Oder nur mit Zeitverschiebung zu UTC?
  * Als Sekunden seit 1970?
  * Als long? Als Date? Als LocalDateTime?

## LocalDateTime: so schön einfach

* Lasst uns das mal in Java machen
* Ganz einfach

```java
import static java.time.temporal.ChronoUnit.MINUTES;

void main() {
  var balloonPopped = LocalDateTime.of(2025, 1, 13, 20, 0, 0, 0);
  System.out.println(balloonPopped);

  var now = LocalDateTime.now();
  System.out.println(now);

  var passed = MINUTES.between(balloonPopped, now);
  System.out.println("(vor " + passed + " Minuten)");
}
```

```markdown
# java.time.LocalDateTime
* Seit Java 8
* Datum und Uhrzeit ohne Zeitzone
* Intern gespeichert als: LocalDate + LocalTime
  * LocalDate: Jahr, Monat, Tag
  * LocalTime: Stunde, Minute, Sekunde, Nanosekunde

* Was soll da schon schiefgehen?
```

## LocalDateTime: Schattenseiten

* Starten wir es mal in Docker

```dockerfile
FROM eclipse-temurin:23
COPY src src
ENTRYPOINT ["java", "--enable-preview", "src/main/java/Baloon.java"]
```

* Keine Zeitzone
  * => Achtung, Zeitreisen möglich
* Keine Zeitumstellung
  * => Achtung, nicht eindeutig
  * => Achtung, Lücken

## OffsetDateTime for the rescue

* Speichert Datum und Uhrzeit und Offset (von UTC)
* Damit: wenn die Maschinen und User in verschiedenen Zeitzonen sind, konsistenter Blick auf Zeitpunkte
* Intern gespeichert als: LocalDateTime + ZoneOffset

```java
import static java.time.temporal.ChronoUnit.MINUTES;

void main() {
	var balloonPopped = OffsetDateTime.of(2025, 1, 13, 20, 0, 0, 0, ZoneOffset.ofHours(1));
	System.out.println(balloonPopped);

	var now = OffsetDateTime.now();
	System.out.println(now);

	var passed = MINUTES.between(balloonPopped, now);
	System.out.println("(vor " + passed + " Minuten)");
}
```

* Was soll da schon schiefgehen?



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

## Zivile Zeit

* Ein Tag dauert also nicht immer 24 Stunden
* Die Zeitzonen werden von den Regierungen der Staaten festgelegt
  * Und orientieren sich entsprechend an politischen Grenzen
* Wie wir die Zeiger unserer Uhren stellen, ist also eine politische Entscheidung
* Festgelegt durch Politiker (nicht natürlich, nicht mathematisch)
* Und die Regeln können sich in Zukunft ändern (Sommerzeit-Abschaffung, Grenzverschiebungen)

* Und entsprechend ist das super komplex
  * Zeitzonen mit halben Stunden Verschiebung zu UTC
  * Zeitzonen mit Viertelstunden Verschiebung zu UTC
  * Ethnische Gruppen, die sich nicht an die Zeitzone ihres Landes halten
  * Staaten, die von anderen Staaten nicht anerkannt werden

* Sehr praktisch ist die zivile Zeit, weil man bei Angaben:
  * Jahreszeit aus dem Datum ablesen kann
  * Man einfach nachrechnen kann, wie viel Jahre man alt war
  * Man erkennt in welchem Steuerjahr eine Zahlung fällig ist
  * Und ob es Morgens oder Abends ist

## Wissenschaftliche Zeit

> The second, symbol s, is the SI unit of time. It is defined by taking the fixed numerical
value of the caesium frequency, ∆νCs, the unperturbed ground-state hyperfine
transition frequency of the caesium 133 atom, to be 9 192 631 770 when expressed in the
unit Hz, which is equal to s⁻¹.
> International Bureau of Weights and Measures V3.01 August 2024 https://www.bipm.org/documents/20126/41483022/SI-Brochure-9-EN.pdf

* Und dieses Dokument definiert auch
  * 1 Minute = 60 Sekunden
  * 1 Stunde = 60 Minuten
  * 1 Tag = 24 Stunden

## Eine Welt in UTC

* Zeitzonen, Zeitumstellung, Schaltsekunden
* Alles kompliziert
* Wie würde die Welt aussehen, wenn wir global in UTC leben würden?
* "Was soll da schon schiefgehen?"

* Los Angeles feiert Nachmittags Silvester
* Geburtstage gehen von Nachmittag zu Nachmittag
* In einem Roman steht, dass der Protagonist bis 9 Uhr schläft. Ohne seine Position weiß man nicht, ob das früh oder spät ist.

## Beide Zeitsysteme haben ihre Berechtigung

* Wir merken: beide Zeitsysteme haben ihre Berechtigung
* Zivile Zeit für uns Menschen
  * für Politik
  * für Wirtschaft
  * für unsere Traditionen und Feste
* Wissenschaftliche Zeit für die Naturgesetze
  * für die Astronomie
  * um die Ergebnisse von Experimenten zu reproduzieren
  * um die Marathon-Bestzeit zu dokumentieren

## Wofür wir Datum und Uhrzeit nutzen

* Wir nutzen Datum und Uhrzeit um verschiedene Dinge auszudrücken
  * Zeitpunkte (Punkt auf dem Zeitstrahl)
  * Zeitspannen (Strecke auf dem Zeitstrahl)
  * Zeitdauern (Länge der Strecke)

## In Java

* Bitte bloß nicht java.sql.Date verwenden
  * (Nur für Datenbankzugriffe vorgesehen)
* Bitte auch nicht java.util.Date verwenden (und GregorianCalendar usw)
  * (Vor Java 8)
  * ... und super unpraktisch (Jahreszahl minus 1900, Monat 0-basiert)
* Sondern java.time
  * Immutables
  * Null-safe
  * Thread-safe
* Oder vielleicht auch mal `long`

## Schaltsekunde

> On days that do have a leap second, the leap second is spread equally over the last 1000 seconds of the day, maintaining the appearance of exactly 86400 seconds per day.
> [docs.oracle.com java 23 java.time.Instant](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/time/Instant.html#:~:text=On%20days%20that%20do%20have%20a%20leap%20second%2C%20the%20leap%20second%20is%20spread%20equally%20over%20the%20last%201000%20seconds%20of%20the%20day%2C%20maintaining%20the%20appearance%20of%20exactly%2086400%20seconds%20per%20day.)
* Java versucht die Schaltsekunde für Entwickler zu verstecken
* An Tagen mit Schaltsekunden sind die letzten 1000 Sekunden des Tages länger (Uhr tickt langsamer)
* `Instant.parse("2016-12-31T23:59:60Z")` entspricht `Instant.parse("2016-12-31T23:59:59Z")`

## Fun facts about 2025

* (20+25)² = 2025
* (0+1+2+3+4+5+6+7+8+9)² = 2025
* 0³ + 1³ + 2³ + 3³ + 4³ + 5³ + 6³ + 7³ + 8³ + 9³ = 2025

## Further reading

* [JavaDoc java.time in Java 23](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/time/package-summary.html)
