package app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Karta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idKarta;

	private Date date;
	private long idLet;
	private long idUser;
	private boolean canceled=false;

	public long getIdKarta() {
		return idKarta;
	}

	public Karta(Date date, long idLet, long idUser) {
		this.date = date;
		this.idLet = idLet;
		this.idUser = idUser;
	}

	public void setIdKarta(long idKarta) {
		this.idKarta = idKarta;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getIdLet() {
		return idLet;
	}

	public void setIdLet(long idLet) {
		this.idLet = idLet;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	

}
