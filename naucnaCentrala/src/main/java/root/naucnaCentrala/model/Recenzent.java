package root.naucnaCentrala.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recenzent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String titula;

	@Column
	private NaucnaOblast naucnaOblast;

	@ManyToOne
	public Casopis casopis;
	
	@ManyToMany(mappedBy = "recenzenti")
    @JsonIgnore
    private List<Casopis> casopisi;
}
