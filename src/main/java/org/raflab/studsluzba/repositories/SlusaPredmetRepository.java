package org.raflab.studsluzba.repositories;

import java.util.List;

import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SlusaPredmet;
import org.raflab.studsluzba.model.StudentIndeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SlusaPredmetRepository extends JpaRepository<SlusaPredmet, Long> {
	
	@Query("select sp from SlusaPredmet sp where sp.studentIndeks.id = :indeksId1")
	List<SlusaPredmet> getSlusaPredmetForIndeksAktivnaGodina(Long indeksId);
	@Query("select sp from SlusaPredmet sp where sp.studentIndeks.broj=:broj and sp.studentIndeks.godina=:godina and sp.studentIndeks.studProgramOznaka=:studProgramOznaka")
	SlusaPredmet getSlusaPredmetForIndeksCeoAktivnaGodina(int broj, int godina, String studProgramOznaka);
	
	
	@Query("select sp.studentIndeks from SlusaPredmet sp where sp.drziPredmet.predmet.id = :idPredmeta "
			+ "and sp.drziPredmet.nastavnik.id = :idNastavnika  ")
	List<StudentIndeks> getStudentiSlusaPredmetAktivnaGodina(Long idPredmeta, Long idNastavnika);
	
	
	@Query("select sp.studentIndeks from SlusaPredmet sp where sp.drziPredmet.id = :idDrziPredmet")
	List<StudentIndeks> getStudentiSlusaPredmetZaDrziPredmet(Long idDrziPredmet);
	
	
	/*
	 * TODO dodati slicne operacije koja vracaju sve studente za stud program/ godinu studija koje ne slusaju predmet
	 */
	@Query("select si from StudentIndeks si where not exists "
			+ "(select sp from SlusaPredmet sp where sp.studentIndeks=si and sp.drziPredmet.id = :idDrziPredmet) ")
	List<StudentIndeks> getStudentiNeSlusajuDrziPredmet(Long idDrziPredmet);
	@Query("select sp from SlusaPredmet  sp where sp.studentIndeks.id=:indeks and sp.drziPredmet.predmet.id=:predmet")
	SlusaPredmet getSlusaPredmetByPredmetandIndeks(Long indeks,Long predmet);
	@Query("SELECT sp.drziPredmet.predmet FROM SlusaPredmet sp WHERE sp.id=:predmetId ")
	List<Predmet> getSlusaPredmetByPredmet(Long predmetId);

	List<SlusaPredmet> findByStudentIndeks(StudentIndeks indeks);

	@Query("select sp from SlusaPredmet sp where sp.studentIndeks.broj=:broj and sp.studentIndeks.godina=:godina and sp.studentIndeks.studProgramOznaka=:studProgramOznaka")
	List<SlusaPredmet> getSlusaPredmetForIndeksCeoAktivnaGodinalsit(int broj, int godina, String studProgramOznaka);
	

}
