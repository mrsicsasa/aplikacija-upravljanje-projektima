package models.osobe;

import java.time.LocalDateTime;

public abstract class AngazovanaOsoba {
	private String ime;
	private String prezime;
	private int plata;
	private LocalDateTime pocetakAngazovanja;
	private LocalDateTime zavrsetakAngazovanja;

	public AngazovanaOsoba(String ime, String prezime, int plata, LocalDateTime pocetakAngazovanja,
			LocalDateTime zavrsetakAngazovanja) {
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

	public LocalDateTime getPocetakAngazovanja() {
		return pocetakAngazovanja;
	}

	public void setPocetakAngazovanja(LocalDateTime pocetakAngazovanja) {
		this.pocetakAngazovanja = pocetakAngazovanja;
	}

	public LocalDateTime getZavrsetakAngazovanja() {
		return zavrsetakAngazovanja;
	}

	public void setZavrsetakAngazovanja(LocalDateTime zavrsetakAngazovanja) {
		this.zavrsetakAngazovanja = zavrsetakAngazovanja;
	}

	@Override
	public String toString() {
		return ime + "|" + prezime + "|" + plata + "|" + pocetakAngazovanja + "|" + zavrsetakAngazovanja + "|";
	}

}
