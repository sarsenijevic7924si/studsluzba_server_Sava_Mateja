package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Grupa;
import org.raflab.studsluzba.model.Ispit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IspitRepository extends JpaRepository<Ispit, Long> {
    @Query("select i from Ispit i where i.predmet.id = :idPredmeta and i.nastavnik.id = :idNastavnika")
    List<Ispit> findByPredmetAndNastavnik(Long idPredmeta,Long idNastavnik);

    List<Ispit> findAllByIdIn(List<Long> ids);
    @Query("select i from  Ispit i where i.nastavnik.id=:idNastavnik")
    List<Ispit> findAllByNastavnik(Long idNastavnik);
    @Query("select i from  Ispit i where i.nastavnik.id=:idNastavnik and i.jeZakljucen is true ")
    List<Ispit> findAllByNastavnikZakljucan(Long idNastavnik);




}
