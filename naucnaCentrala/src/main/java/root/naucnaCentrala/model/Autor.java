package root.naucnaCentrala.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Autor extends Korisnik {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8705120292470765034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String username;

	@Column
	private String password;


	public Autor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Autor(String ime, String prezime, String grad, String drzava, String email) {
		super(ime, prezime, grad, drzava, email);
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
