package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PrijavaIspita;
import org.raflab.studsluzba.model.StudentIndeks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita, Long> {
    @Query("select pi from PrijavaIspita pi " +
            "left join pi.izlazakNaIspit izlazak " +
            "where pi.studentIndeks.broj = :broj " +
            "and pi.studentIndeks.godina = :godina " +
            "and pi.studentIndeks.studProgramOznaka = :oznaka " +

            "order by pi.datumPrijave asc")
    Page<PrijavaIspita> findPolozeniPoIndeksu(int broj, int godina, String oznaka, Pageable pageable);
    @Query("select pi from PrijavaIspita pi " +
            "left join pi.izlazakNaIspit izlazak " +
            "where pi.studentIndeks.broj = :broj " +
            "and pi.studentIndeks.godina = :godina " +
            "and pi.studentIndeks.studProgramOznaka = :oznaka " +

            "order by pi.datumPrijave asc")
    Page<PrijavaIspita> findNepolozeniPoIndeksu(int broj, int godina, String oznaka, Pageable pageable);
    @Query("Select pi.studentIndeks from PrijavaIspita pi where pi.id=:idA")
    List<StudentIndeks> findByPrijavaIspita(Long idA);
    List<PrijavaIspita> findByIspitId(Long ispitId);
}
