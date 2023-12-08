package models.osobe;

import java.util.ArrayList;

public class Odmori {
private ArrayList<Odmor>odmori=new ArrayList<Odmor>();

public Odmori(ArrayList<Odmor> odmori) {
	super();
	this.odmori = odmori;
}

public Odmori() {
	super();
}

public ArrayList<Odmor> getOdmori() {
	return odmori;
}

public void setOdmori(ArrayList<Odmor> odmori) {
	this.odmori = odmori;
}
public void dodajOdmor(Odmor odmor) {
	this.odmori.add(odmor);
}
public void obrisiOdmor(Odmor odmor) {
	this.odmori.remove(odmor);
}
public void obrisiOdmor(int index) {
	this.odmori.remove(index);
}
@Override
public String toString() {
	return "Odmori [odmori=" + odmori + "]";
}

}
