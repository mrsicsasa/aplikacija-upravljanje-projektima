package models.proizvodi;

public class TehnickiProizvod extends Proizvod {
	private Dimenzije dimenzije;
	private double nominalnaSnaga;
	private double radniNapon;

	public TehnickiProizvod(String naziv, int cena, String zemljaPorekla, String model, String jedinicaMere,
			Dimenzije dimenzije, double nominalnaSnaga, double radniNapon) {
		super(naziv, cena, zemljaPorekla, model, jedinicaMere);
		this.dimenzije = dimenzije;
		this.nominalnaSnaga = nominalnaSnaga;
		this.radniNapon = radniNapon;
	}

	public TehnickiProizvod(String naziv, int cena, String zemljaPorekla, String model, String jedinicaMere) {
		super(naziv, cena, zemljaPorekla, model, jedinicaMere);
	}

	public Dimenzije getDimenzije() {
		return dimenzije;
	}

	public void setDimenzije(Dimenzije dimenzije) {
		this.dimenzije = dimenzije;
	}

	public double getNominalnaSnaga() {
		return nominalnaSnaga;
	}

	public void setNominalnaSnaga(double nominalnaSnaga) {
		this.nominalnaSnaga = nominalnaSnaga;
	}

	public double getRadniNapon() {
		return radniNapon;
	}

	public void setRadniNapon(double radniNapon) {
		this.radniNapon = radniNapon;
	}

	@Override
	public String toString() {
		return super.toString() + "TehnickiProizvod [dimenzije=" + dimenzije + ", nominalnaSnaga=" + nominalnaSnaga
				+ ", radniNapon=" + radniNapon + "]";
	}

}
