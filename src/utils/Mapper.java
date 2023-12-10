package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import models.osobe.AngazovanaOsoba;
import models.osobe.Menadzer;
import models.osobe.Odmor;
import models.osobe.Odmori;
import models.osobe.Promoter;
import models.osobe.Uloga;

public class Mapper {
public Mapper() {
	super();
}
private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
private LocalDateTime parsirajDatum(String datum, DateTimeFormatter formatter) {
    return LocalDateTime.parse(datum, FORMATTER);
}
private LocalDateTime konvertujStringUDatum(String datum) {
    return LocalDateTime.parse(datum, FORMATTER);
}
public ArrayList<Menadzer> konvertujUMenadzer(List<ArrayList<String>> podaci) {
    return podaci.stream()
            .map(this::konvertujPodatakUMenadzer)
            .collect(Collectors.toCollection(ArrayList::new));
}

private Menadzer konvertujPodatakUMenadzer(ArrayList<String> podatak) {
    return new Menadzer(
            podatak.get(0),
            podatak.get(1),
            Integer.parseInt(podatak.get(2)),
           parsirajDatum(podatak.get(3), FORMATTER),
           parsirajDatum(podatak.get(4), FORMATTER),
            new Uloga(podatak.get(5), podatak.get(6), podatak.get(7))
    );
}

public ArrayList<Promoter> konvertujUPromotera(List<ArrayList<String>> podaci) {
    return podaci.stream()
            .map(this::konvertujPodatakUPromotera)
            .collect(Collectors.toCollection(ArrayList::new));
}

private Promoter konvertujPodatakUPromotera(ArrayList<String> podatak) {
	Menadzer menazder=new Menadzer();
	 ArrayList<Odmor> odmori = new ArrayList<>();
     for (int i = 6; i < podatak.size(); i += 2) {
         odmori.add(new Odmor(parsirajDatum(podatak.get(i), FORMATTER),parsirajDatum(podatak.get(i+1), FORMATTER)));
     }
	menazder.setIme(podatak.get(5));
    return new Promoter(
            podatak.get(0),
            podatak.get(1),
            Integer.parseInt(podatak.get(2)),
            LocalDateTime.parse(podatak.get(3), FORMATTER),
            LocalDateTime.parse(podatak.get(4), FORMATTER),
            menazder,
            new Odmori(odmori)
            );
}

}
