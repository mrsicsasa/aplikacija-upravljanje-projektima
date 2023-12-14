package models.osobe;

import java.time.LocalDate;

public abstract class AngazovanaOsoba {
	private String ime;
	private String prezime;
	private int plata;
	private LocalDate pocetakAngazovanja;
	private LocalDate zavrsetakAngazovanja;

	public AngazovanaOsoba(String ime, String prezime, int plata, LocalDate pocetakAngazovanja,
			LocalDate zavrsetakAngazovanja) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.plata = plata;
		this.pocetakAngazovanja = pocetakAngazovanja;
		this.zavrsetakAngazovanja = zavrsetakAngazovanja;
	}

	public AngazovanaOsoba() {
		super();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public LocalDate getPocetakAngazovanja() {
		return pocetakAngazovanja;
	}

	public void setPocetakAngazovanja(LocalDate pocetakAngazovanja) {
		this.pocetakAngazovanja = pocetakAngazovanja;
	}

	public LocalDate getZavrsetakAngazovanja() {
		return zavrsetakAngazovanja;
	}

	public void setZavrsetakAngazovanja(LocalDate zavrsetakAngazovanja) {
		this.zavrsetakAngazovanja = zavrsetakAngazovanja;
	}

	@Override
	public String toString() {
		return ime + "|" + prezime + "|" + plata + "|" + pocetakAngazovanja + "|" + zavrsetakAngazovanja + "|";
	}

}
