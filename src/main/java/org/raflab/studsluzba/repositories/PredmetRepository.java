package org.raflab.studsluzba.repositories;

import java.time.LocalDate;
import java.util.List;

import org.raflab.studsluzba.model.PolozeniPredmeti;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long> {
	
	@Query("select p from Predmet p where p.studProgram.godinaAkreditacije = :godinaAkreditacije")
	List<Predmet> getPredmetForGodinaAkreditacije(Integer godinaAkreditacije);

	List<Predmet> getPredmetsByStudProgramAndObavezan(StudijskiProgram studProgram, boolean obavezan);

	List<Predmet> findByIdIn(List<Long> ids);
	@Query("select p from Predmet p where p.studProgram.naziv=:naziv")
	List<Predmet> findByNazivStuda(String naziv);
	@Query("select p from Predmet p where p.studProgram.oznaka=:studProgramOznaka")
	List<Predmet> getPredmetiZasStudenskiProgram(String studProgramOznaka);



}
