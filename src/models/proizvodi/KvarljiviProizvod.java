package models.proizvodi;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class KvarljiviProizvod extends Proizvod {
	private LocalDate rokTrajanja;
	private int temperaturaSkladistenja;

	public KvarljiviProizvod(String naziv, int cena, String zemljaPorekla, String model, String jedinicaMere,
			LocalDate rokTrajanja, int temperaturaSkladistenja) {
		super(naziv, cena, zemljaPorekla, model, jedinicaMere);
		this.rokTrajanja = rokTrajanja;
		this.temperaturaSkladistenja = temperaturaSkladistenja;
	}

	public KvarljiviProizvod() {
		super();
	}

	public LocalDate getRokTrajanja() {
		return rokTrajanja;
	}

	public void setRokTrajanja(LocalDate rokTrajanja) {
		this.rokTrajanja = rokTrajanja;
	}

	public int getTemperaturaSkladistenja() {
		return temperaturaSkladistenja;
	}

	public void setTemperaturaSkladistenja(int temperaturaSkladistenja) {
		this.temperaturaSkladistenja = temperaturaSkladistenja;
	}

	@Override
	public String toString() {
		return super.toString() + "KvarljiviProizvod [rokTrajanja=" + rokTrajanja + ", temperaturaSkladistenja="
				+ temperaturaSkladistenja + "]";
	}

}
