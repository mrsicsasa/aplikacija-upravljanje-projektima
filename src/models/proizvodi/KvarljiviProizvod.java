package models.proizvodi;

import java.time.LocalDateTime;

public class KvarljiviProizvod extends Proizvod {
private LocalDateTime rokTrajanja;
private int temperaturaSkladistenja;
public KvarljiviProizvod(String naziv, int cena, String zemljaPorekla, String model, String jedinicaMere,
		LocalDateTime rokTrajanja, int temperaturaSkladistenja) {
	super(naziv, cena, zemljaPorekla, model, jedinicaMere);
	this.rokTrajanja = rokTrajanja;
	this.temperaturaSkladistenja = temperaturaSkladistenja;
}
public KvarljiviProizvod(String naziv, int cena, String zemljaPorekla, String model, String jedinicaMere) {
	super(naziv, cena, zemljaPorekla, model, jedinicaMere);
}
public LocalDateTime getRokTrajanja() {
	return rokTrajanja;
}
public void setRokTrajanja(LocalDateTime rokTrajanja) {
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
	return super.toString()+ "KvarljiviProizvod [rokTrajanja=" + rokTrajanja + ", temperaturaSkladistenja=" + temperaturaSkladistenja
			+ "]";
}

}
