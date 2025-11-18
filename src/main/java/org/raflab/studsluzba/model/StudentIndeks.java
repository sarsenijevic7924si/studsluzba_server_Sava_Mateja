package org.raflab.studsluzba.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"broj", "godina", "studProgramOznaka", "aktivan"}))
public class StudentIndeks {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer broj;
	private Integer godina;
	private String studProgramOznaka;
	private String nacinFinansiranja;
	private boolean aktivan; 
	private LocalDate vaziOd;
	@ManyToOne
	private StudentPodaci student;
	
	@ManyToOne
	private StudijskiProgram studijskiProgram;   // na koji studijski program je upisan
	@OneToMany(mappedBy="studentIndeks")
	private List<SlusaPredmet> slusaPredmet;

	@OneToMany(mappedBy="studentIndeks")
	private List<PrijavaIspita> prijaveIspita;

	@OneToMany(mappedBy="studentIndeks")
	private List<PolozeniPredmeti> polozeniPredmeti;
	private Integer ostvarenoEspb;

	@Override
	public String toString() {
		return "StudentIndeks{" +
				 broj +
				"/" + godina +
				"/" + studProgramOznaka + '\'' +
				'}';
	}
}
