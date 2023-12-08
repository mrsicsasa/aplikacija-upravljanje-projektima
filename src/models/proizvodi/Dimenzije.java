package models.proizvodi;

public class Dimenzije {
private double duzina;
private double sirina;
public Dimenzije(double duzina, double sirina) {
	super();
	this.duzina = duzina;
	this.sirina = sirina;
}
public Dimenzije() {
	super();
}
public double getDuzina() {
	return duzina;
}
public void setDuzina(double duzina) {
	this.duzina = duzina;
}
public double getSirina() {
	return sirina;
}
public void setSirina(double sirina) {
	this.sirina = sirina;
}
@Override
public String toString() {
	return "Dimenzije [duzina=" + duzina + ", sirina=" + sirina + "]";
}

}
