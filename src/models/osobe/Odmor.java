package models.osobe;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Odmor {
	private LocalDate pocetakOdmora;
	private LocalDate krajOdmora;

	public Odmor(LocalDate pocetakOdmora, LocalDate krajOdmora) {
		super();
		this.pocetakOdmora = pocetakOdmora;
		this.krajOdmora = krajOdmora;
	}

	public Odmor() {
		super();
	}

	public LocalDate getPocetakOdmora() {
		return pocetakOdmora;
	}

	public void setPocetakOdmora(LocalDate pocetakOdmora) {
		this.pocetakOdmora = pocetakOdmora;
	}

	public LocalDate getKrajOdmora() {
		return krajOdmora;
	}

	public void setKrajOdmora(LocalDate krajOdmora) {
		this.krajOdmora = krajOdmora;
	}

	@Override
	public String toString() {
		return "Odmor [pocetakOdmora=" + pocetakOdmora + ", krajOdmora=" + krajOdmora + "]";
	}

}
