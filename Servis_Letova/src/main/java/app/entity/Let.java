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
	private int duzinaLeta;
	private int cena;
	private boolean kupiLet=false;
	private int kupljeneKarte = 0;

	
	public Let() {

	}

	public Let(Avion avion, String pocetnaDestinacija, String krajnjaDestinacija, int duzinaLeta, int cena) {
		super();
		this.avion = avion;
		this.pocetnaDestinacija = pocetnaDestinacija;
		this.krajnjaDestinacija = krajnjaDestinacija;
		this.duzinaLeta = duzinaLeta;
		this.cena = cena;
	}

	
	
	public long getIdLet() {
		return idLet;
	}

	public void setIdLet(long idLet) {
		this.idLet = idLet;
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

	public int getDuzinaLeta() {
		return duzinaLeta;
	}

	public void setDuzinaLeta(int duzinaLeta) {
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

	public int getKupljeneKarte() {
		return kupljeneKarte;
	}

	public void setKupljeneKarte(int kupljeneKarte) {
		this.kupljeneKarte = kupljeneKarte;
	}
	
	
	

}
