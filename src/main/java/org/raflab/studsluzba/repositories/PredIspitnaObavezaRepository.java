package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PredIspitnaObaveza;
import org.raflab.studsluzba.model.StudentPredispitni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredIspitnaObavezaRepository extends JpaRepository<PredIspitnaObaveza, Long> {
    @Query("select sp from PredIspitnaObaveza p join StudentPredispitni sp on p.id=sp.predIspitnaObaveza.id where p.predmet.naziv=:naziv and sp.idneks.broj=:broj and sp.idneks.godina=:godina and sp.idneks.studProgramOznaka=:oznaka")
    public List<StudentPredispitni> getAllByStudenskiIndeksiPredmet(String naziv,int broj,int godina,String oznaka);
}
