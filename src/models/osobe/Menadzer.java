package models.osobe;

import java.time.LocalDate;
import java.util.Objects;

public class Menadzer extends AngazovanaOsoba {
	private Uloga uloga;

	public Menadzer(String ime, String prezime, int plata, LocalDate pocetakAngazovanja,
			LocalDate krajAngazovanja, Uloga uloga) {
		super(ime, prezime, plata, pocetakAngazovanja, krajAngazovanja);
		this.uloga = uloga;
	}

	public Menadzer() {

	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	@Override
	public String toString() {
		return super.toString() + uloga;
	}

	

}
