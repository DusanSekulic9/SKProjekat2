package app.forms;

import app.entity.Avion;

public class PretragaLetaForm {

	
	private Avion avion;
	private String pocetnaDestinacija;
	private String krajnjaDestinacija;
	private String duzinaLeta;
	private int cena;

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public String getPocetnaDestinacija() {
		return pocetnaDestinacija;
	}

	public void setPocetnaDestinacija(String pocetnaDestinacija) {
		this.pocetnaDestinacija = pocetnaDestinacija;
	}

	public String getKrajnjaDestinacija() {
		return krajnjaDestinacija;
	}

	public void setKrajnjaDestinacija(String krajnjaDestinacija) {
		this.krajnjaDestinacija = krajnjaDestinacija;
	}

	public String getDuzinaLeta() {
		return duzinaLeta;
	}

	public void setDuzinaLeta(String duzinaLeta) {
		this.duzinaLeta = duzinaLeta;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}
}
