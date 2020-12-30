package app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import app.constants.Rank_Constants;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUser;

	private String ime;
	private String prezime;
	private String email;
	private String password;
	private String brojPasosa;
	private String tip = Rank_Constants.BRONZA;
	private int predjeneMilje = 0;
	
	@OneToMany(mappedBy="user")
	private List<KreditnaKartica> kreditneKartice;
	

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
	}

	public long getId() {
		return idUser;
	}

	public void setId(long id) {
		this.idUser = id;
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


	public String getTip() {
		return tip;
	}


	public void setTip(String rank) {
		this.tip = rank;
	}


	public int getPredjeneMilje() {
		return predjeneMilje;
	}


	public void setPredjeneMilje(int predjeneMilje) {
		this.predjeneMilje = predjeneMilje;
	}


	public List<KreditnaKartica> getKreditneKartice() {
		return kreditneKartice;
	}


	public void setKreditneKartice(List<KreditnaKartica> kreditneKartice) {
		this.kreditneKartice = kreditneKartice;
	}
	
	
	
	
	

}
