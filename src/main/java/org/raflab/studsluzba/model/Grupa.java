package org.raflab.studsluzba.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

import javax.persistence.*;

@Entity
@Data
public class Grupa {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private StudijskiProgram studijskiProgram;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Predmet> predmeti;

}
