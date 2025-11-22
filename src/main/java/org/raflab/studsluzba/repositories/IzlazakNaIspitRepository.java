package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.IzlazakNaIspit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IzlazakNaIspitRepository extends JpaRepository<IzlazakNaIspit, Long> {
    @Query(" select i from IzlazakNaIspit i join i.prijava p join p.studentIndeks si join si.studijskiProgram sp where p.ispit.id = :ispitId order by sp.oznaka, si.godina, si.broj")
    List<IzlazakNaIspit> sortiraniRezultati(Long ispitId);
    @Query("SELECT COUNT(i) FROM IzlazakNaIspit i " +
            "WHERE i.prijava.studentIndeks.id = :studentId " +
            "AND i.prijava.ispit.predmet.id = :predmetId")
    long countByStudentAndPredmet(@Param("studentId") Long studentId,
                                  @Param("predmetId") Long predmetId);
}
