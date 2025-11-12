package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.Uplata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface UplataRepository extends JpaRepository<Uplata,Long> {
    List<Uplata> findByStudentIndeks(StudentIndeks studentIndeks);


    List<Uplata> findByStudentIndeks_Id(Long studentIndeksId);


    List<Uplata> findByDatumUplate(LocalDate datumUplate);


    List<Uplata> findByDatumUplateBetween(LocalDate startDate, LocalDate endDate);


    List<Uplata> findByIznosDinariGreaterThan(Double minIznos);

}
