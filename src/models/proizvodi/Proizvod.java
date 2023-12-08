package models.proizvodi;

public abstract class Proizvod {
private String naziv;
private int cena;
private String zemljaPorekla;
private String model;
private String jedinicaMere;
public Proizvod(String naziv, int cena, String zemljaPorekla, String model, String jedinicaMere) {
	super();
	this.naziv = naziv;
	this.cena = cena;
	this.zemljaPorekla = zemljaPorekla;
	this.model = model;
	this.jedinicaMere = jedinicaMere;
}
public Proizvod() {
	super();
}
public String getNaziv() {
	return naziv;
}
public void setNaziv(String naziv) {
	this.naziv = naziv;
}
public int getCena() {
	return cena;
}
public void setCena(int cena) {
	this.cena = cena;
}
public String getZemljaPorekla() {
	return zemljaPorekla;
}
public void setZemljaPorekla(String zemljaPorekla) {
	this.zemljaPorekla = zemljaPorekla;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getJedinicaMere() {
	return jedinicaMere;
}
public void setJedinicaMere(String jedinicaMere) {
	this.jedinicaMere = jedinicaMere;
}
@Override
public String toString() {
	return "Proizvod [naziv=" + naziv + ", cena=" + cena + ", zemljaPorekla=" + zemljaPorekla + ", model=" + model
			+ ", jedinicaMere=" + jedinicaMere + "]";
}

}
