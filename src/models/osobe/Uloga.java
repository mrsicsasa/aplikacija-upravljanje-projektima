package models.osobe;

import java.util.Objects;

public class Uloga {
	private String naziv;
	private String opis;
	private String odgovornosti;

	public Uloga(String naziv, String opis, String odgovornosti) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.odgovornosti = odgovornosti;
	}

	public Uloga() {
		super();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getOdgovornosti() {
		return odgovornosti;
	}

	public void setOdgovornosti(String odgovornosti) {
		this.odgovornosti = odgovornosti;
	}

	@Override
	public String toString() {
		return naziv + "|" + opis + "|" + odgovornosti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(naziv, odgovornosti, opis);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Uloga other = (Uloga) obj;
	    return Objects.equals(naziv, other.naziv)
	            && Objects.equals(opis, other.opis)
	            && Objects.equals(odgovornosti, other.odgovornosti);
	}
	
}
