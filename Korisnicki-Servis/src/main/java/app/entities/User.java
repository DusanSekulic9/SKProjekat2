package app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import app.enums.Rank;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String ime;
	private String prezime;
	private String email;
	private String password;
	private String brojPasosa;
	private boolean admin = false;
	//private Rank rank = Rank.BRONZE;
	private int predjeneMilje = 0;

	//private List<KreditnaKartica> kreditneKartice = new ArrayList<KreditnaKartica>();
	
	public User() {

	}

	
	public User(String ime, String prezime, String email, String password, String brojPasosa) {
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.brojPasosa = brojPasosa;
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.admin = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getBrojPasosa() {
		return brojPasosa;
	}


	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}

//
//	public List<KreditnaKartica> getKreditneKartice() {
//		return kreditneKartice;
//	}
//
//
//	public void setKreditneKartice(List<KreditnaKartica> kreditneKartice) {
//		this.kreditneKartice = kreditneKartice;
//	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


//	public Rank getRank() {
//		return rank;
//	}
//
//
//	public void setRank(Rank rank) {
//		this.rank = rank;
//	}
//
//
	public int getPredjeneMilje() {
		return predjeneMilje;
	}


	public void setPredjeneMilje(int predjeneMilje) {
		this.predjeneMilje = predjeneMilje;
	}
	
	
	
	
	

}
