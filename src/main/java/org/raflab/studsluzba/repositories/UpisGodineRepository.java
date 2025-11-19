package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PolozeniPredmeti;
import org.raflab.studsluzba.model.UpisGodine;
import org.raflab.studsluzba.model.Uplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpisGodineRepository extends JpaRepository<UpisGodine,Long> {
    @Query("select ug from UpisGodine ug " +
            "where ug.studentIndeks.broj = :broj " +
            "and ug.studentIndeks.godina = :godina " +
            "and ug.studentIndeks.studProgramOznaka = :studProgramOznaka " +
            "order by ug.godinaKojaSeUpisuje asc")
    List<UpisGodine> findByStudentIndeks(int broj, int godina, String studProgramOznaka);
}
