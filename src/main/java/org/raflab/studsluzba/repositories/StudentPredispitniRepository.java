package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.StudentPredispitni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentPredispitniRepository extends JpaRepository<StudentPredispitni, Long> {
    @Query("select sum (s.brojPoena) from StudentPredispitni s where s.idneks.id = :studentId AND s.predIspitnaObaveza.predmet.id = :predmetId")
    Double sumPoeniForStudentAndPredmet(Long studentId, Long predmetId);
    List<StudentPredispitni> findByIdneksId(Long studentId);
}
