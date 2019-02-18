package root.naucnaCentrala.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String naziv;
	@Column
	private String apstrakt;
	@Column
	private NaucnaOblast naucnaOblast;
	@Column
	private Urednik urednikNaucne;

	@ManyToOne
	public Casopis casopis;

	public Rad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rad(String naziv, String apstrakt, NaucnaOblast naucnaOblast, Urednik urednikNaucne, Casopis casopis) {
		this.naziv = naziv;
		this.apstrakt = apstrakt;
		this.naucnaOblast = naucnaOblast;
		this.urednikNaucne = urednikNaucne;
		this.casopis = casopis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getApstrakt() {
		return apstrakt;
	}

	public void setApstrakt(String apstrakt) {
		this.apstrakt = apstrakt;
	}

	public NaucnaOblast getNaucnaOblast() {
		return naucnaOblast;
	}

	public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
		this.naucnaOblast = naucnaOblast;
	}

	public Urednik getUrednikNaucne() {
		return urednikNaucne;
	}

	public void setUrednikNaucne(Urednik urednikNaucne) {
		this.urednikNaucne = urednikNaucne;
	}
}
