package root.naucnaCentrala.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Korisnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1886225575736086842L;

	@Column
	private String ime;
	@Column
	private String prezime;
	@Column
	private String grad;
	@Column
	private String drzava;
	@Column
	private String email;

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String ime, String prezime, String grad, String drzava, String email) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.drzava = drzava;
		this.email = email;
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

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
