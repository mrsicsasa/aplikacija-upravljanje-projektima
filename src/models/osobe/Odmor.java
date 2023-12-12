package models.osobe;

import java.time.LocalDateTime;

public class Odmor {
	private LocalDateTime pocetakOdmora;
	private LocalDateTime krajOdmora;

	public Odmor(LocalDateTime pocetakOdmora, LocalDateTime krajOdmora) {
		super();
		this.pocetakOdmora = pocetakOdmora;
		this.krajOdmora = krajOdmora;
	}

	public Odmor() {
		super();
	}

	public LocalDateTime getPocetakOdmora() {
		return pocetakOdmora;
	}

	public void setPocetakOdmora(LocalDateTime pocetakOdmora) {
		this.pocetakOdmora = pocetakOdmora;
	}

	public LocalDateTime getKrajOdmora() {
		return krajOdmora;
	}

	public void setKrajOdmora(LocalDateTime krajOdmora) {
		this.krajOdmora = krajOdmora;
	}

	@Override
	public String toString() {
		return "Odmor [pocetakOdmora=" + pocetakOdmora + ", krajOdmora=" + krajOdmora + "]";
	}

}
