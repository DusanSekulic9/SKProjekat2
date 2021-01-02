package app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Let")
public class Let {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLet;
	
	
	@ManyToOne
	@JoinColumn(name="idAvion")
	private Avion avion;
	private String pocetnaDestinacija;
	private String krajnjaDestinacija;
	private String duzinaLeta;
	private int cena;
	private boolean kupiLet=true;

	
	public Let() {

	}

	public Let(Avion avion, String pocetnaDestinacija, String krajnjaDestinacija, String duzinaLeta, int cena) {
		super();
		this.avion = avion;
		this.pocetnaDestinacija = pocetnaDestinacija;
		this.krajnjaDestinacija = krajnjaDestinacija;
		this.duzinaLeta = duzinaLeta;
		this.cena = cena;
	}

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

	public boolean isKupiLet() {
		return kupiLet;
	}

	public void setKupiLet(boolean kupiLet) {
		this.kupiLet = kupiLet;
	}
	
	
	

}
