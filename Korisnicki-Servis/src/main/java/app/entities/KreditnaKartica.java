package app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="kreditnaKartica")
public class KreditnaKartica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idKartica;
	
	private String ime;
	private String prezime;
	private String brKartice;
	private String sigurnosniBrojKartice;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	
	public KreditnaKartica() {
		
	}
	
	@Override
	public String toString() {
		return brKartice;
	}
	
	
	public KreditnaKartica(String ime, String prezime, String brKartice, String sigurnosniBrojKartice) {
		this.ime = ime;
		this.prezime = prezime;
		this.brKartice = brKartice;
		this.sigurnosniBrojKartice = sigurnosniBrojKartice;
	}



	public long getId() {
		return idKartica;
	}
	public void setId(long id) {
		this.idKartica = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrKartice() {
		return brKartice;
	}
	public void setBrKartice(String brKartice) {
		this.brKartice = brKartice;
	}
	public String getSigurnosniBrojKartice() {
		return sigurnosniBrojKartice;
	}
	public void setSigurnosniBrojKartice(String sigurnosniBrojKartice) {
		this.sigurnosniBrojKartice = sigurnosniBrojKartice;
	}

	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
