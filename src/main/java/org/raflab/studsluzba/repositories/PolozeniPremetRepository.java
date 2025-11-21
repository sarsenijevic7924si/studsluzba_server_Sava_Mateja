package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Ispit;
import org.raflab.studsluzba.model.PolozeniPredmeti;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudentIndeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PolozeniPremetRepository extends JpaRepository<PolozeniPredmeti,Long> {
    boolean existsByStudentIndeksIdAndPredmetId(Long indeksId, Long predmetId);
    @Query("select pp from PolozeniPredmeti pp where pp.studentIndeks =:studentIndeksId order by pp.datumPolaganja asc ")
    List<PolozeniPredmeti> findByStudentIndeksId(Long studentIndeksId);
    @Query("select pp from PolozeniPredmeti pp " +
            "where pp.studentIndeks.broj = :broj " +
            "and pp.studentIndeks.godina = :godina " +
            "and pp.studentIndeks.studProgramOznaka = :studProgramOznaka " +
            "order by pp.datumPolaganja asc")
    List<PolozeniPredmeti> findByStudentIndeks(int broj, int godina, String studProgramOznaka);
    boolean existsByStudentIndeksAndPredmet(StudentIndeks si, Predmet predmet);
    @Query("select pp from PolozeniPredmeti pp where pp.datumPriznavanja>=:prvi and pp.datumPolaganja<=:drugi")
    List<PolozeniPredmeti> getProsecnaOcenaZaGodine(LocalDate prvi, LocalDate drugi);


}
