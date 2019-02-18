package root.naucnaCentrala.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Casopis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4793616280342947769L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String naziv;

	@Column
	private TipCasopisa tipCasopisa;

	@OneToMany(mappedBy = "casopis", cascade = CascadeType.ALL)
	@JsonIgnore
	public List<Urednik> urednici;

	@ManyToMany(targetEntity = Recenzent.class)
	@JoinTable(name = "recenzenti_casopisa", joinColumns = @JoinColumn(name = "casopis_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "recenzent_id", referencedColumnName = "id"))
	@JsonIgnore
	public List<Recenzent> recenzenti;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "Pretplatnici_Casopisa", joinColumns = { @JoinColumn(name = "autorId") }, inverseJoinColumns = {
			@JoinColumn(name = "casopisId") })
	private List<Autor> pretplatnici;

	@OneToMany(mappedBy = "casopis", cascade = CascadeType.ALL)
	@JsonIgnore
	public List<Rad> radovi;

	public Casopis() {
	}

	public Casopis(String naziv) {
		this.naziv = naziv;
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

	public TipCasopisa getTipCasopisa() {
		return tipCasopisa;
	}

	public void setTipCasopisa(TipCasopisa tipCasopisa) {
		this.tipCasopisa = tipCasopisa;
	}

	public List<Urednik> getUrednici() {
		return urednici;
	}

	public void setUrednici(List<Urednik> urednici) {
		this.urednici = urednici;
	}

	public List<Autor> getPretplatnici() {
		return pretplatnici;
	}

	public void setPretplatnici(List<Autor> pretplatnici) {
		this.pretplatnici = pretplatnici;
	}

	public List<Recenzent> getRecenzenti() {
		return recenzenti;
	}

	public void setRecenzenti(List<Recenzent> recenzenti) {
		this.recenzenti = recenzenti;
	}
}
