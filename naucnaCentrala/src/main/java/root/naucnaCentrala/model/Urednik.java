package root.naucnaCentrala.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Urednik extends Korisnik {

	/**
	 * 
	 */
	private static final long serialVersionUID = 605997952086305940L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String titula;

	@Column
	private NaucnaOblast naucnaOblast;

	@Column
	private TipUrednika tipUrednika;

	@OneToMany
	@JoinColumn(name="id")
	public List<Rad> radovi;

	@ManyToOne
	public Casopis casopis;

	public Urednik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Urednik(String ime, String prezime, String grad, String drzava, String email) {
		super(ime, prezime, grad, drzava, email);
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public NaucnaOblast getNaucnaOblast() {
		return naucnaOblast;
	}

	public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
		this.naucnaOblast = naucnaOblast;
	}

	public Casopis getCasopis() {
		return casopis;
	}

	public void setCasopis(Casopis casopis) {
		this.casopis = casopis;
	}

	public TipUrednika getTipUrednika() {
		return tipUrednika;
	}

	public void setTipUrednika(TipUrednika tipUrednika) {
		this.tipUrednika = tipUrednika;
	}

	public List<Rad> getRadovi() {
		return radovi;
	}

	public void setRadovi(List<Rad> radovi) {
		this.radovi = radovi;
	}
}
