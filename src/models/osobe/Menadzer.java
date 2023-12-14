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

	@Override
	public int hashCode() {
		return Objects.hash(uloga);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menadzer other = (Menadzer) obj;
		return Objects.equals(uloga, other.uloga)&&Objects.equals(getIme(), other.getIme())
				&&Objects.equals(getPrezime(), other.getPrezime())&&Objects.equals(getPlata(), other.getPlata())&&Objects.equals(getPocetakAngazovanja(), other.getPocetakAngazovanja())
				&&Objects.equals(getZavrsetakAngazovanja(), other.getZavrsetakAngazovanja());
	}

}
