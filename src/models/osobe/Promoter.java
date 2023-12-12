package models.osobe;

import java.time.LocalDateTime;

public class Promoter extends AngazovanaOsoba {
	private Menadzer naredjeni;
	private Odmori odmori;

	public Promoter(String ime, String prezime, int plata, LocalDateTime pocetakAngazovanja,
			LocalDateTime zavrsetakAngazovanja, Menadzer naredjeni, Odmori odmori) {
		super(ime, prezime, plata, pocetakAngazovanja, zavrsetakAngazovanja);
		this.naredjeni = naredjeni;
		this.odmori = odmori;
	}

	public Promoter(String ime, String prezime, int plata, LocalDateTime pocetakAngazovanja,
			LocalDateTime zavrsetakAngazovanja) {
		super(ime, prezime, plata, pocetakAngazovanja, zavrsetakAngazovanja);
	}

	public Menadzer getNaredjeni() {
		return naredjeni;
	}

	public void setNaredjeni(Menadzer naredjeni) {
		this.naredjeni = naredjeni;
	}

	public Odmori getOdmori() {
		return odmori;
	}

	public void setOdmori(Odmori odmori) {
		this.odmori = odmori;
	}

	@Override
	public String toString() {
		return super.toString() + "Promoter [naredjeni=" + naredjeni + ", odmori=" + odmori + "]";
	}

}
