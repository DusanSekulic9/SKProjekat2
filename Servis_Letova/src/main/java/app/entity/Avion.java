package app.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="avion")
public class Avion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAvion;

	private String naziv;
	private int kapacitetPutnika;

	@OneToMany(mappedBy="avion")
	@JsonIgnore
	private List<Let> letovi;

	public Avion() {

	}

	public Avion(String naziv, int kapacitetPutnika) {
		super();
		this.naziv = naziv;
		this.kapacitetPutnika = kapacitetPutnika;
	}

	public String getNaziv() {
		return naziv;
	}

	public int getKapacitetPutnika() {
		return kapacitetPutnika;
	}

	public List<Let> getLetovi() {
		return letovi;
	}

}
