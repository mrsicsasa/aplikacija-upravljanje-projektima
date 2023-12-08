package models.osobe;

import java.time.LocalDateTime;

public class Menadzer extends AngazovanaOsoba {
private Uloga uloga;

public Menadzer(String ime, String prezime, int plata, LocalDateTime pocetakAngazovanja,
		LocalDateTime zavrsetakAngazovanja, Uloga uloga) {
	super(ime, prezime, plata, pocetakAngazovanja, zavrsetakAngazovanja);
	this.uloga = uloga;
}

public Menadzer(String ime, String prezime, int plata, LocalDateTime pocetakAngazovanja,
		LocalDateTime zavrsetakAngazovanja) {
	super(ime, prezime, plata, pocetakAngazovanja, zavrsetakAngazovanja);
}

public Uloga getUloga() {
	return uloga;
}

public void setUloga(Uloga uloga) {
	this.uloga = uloga;
}

@Override
public String toString() {
	return super.toString()+ "Menadzer [uloga=" + uloga + "]";
}

}
