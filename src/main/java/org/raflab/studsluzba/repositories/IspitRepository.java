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


    @Query("select pp.ocena from Ispit i join PrijavaIspita pi on pi.ispit.id=i.id join PolozeniPredmeti pp on pp.studentIndeks=pi.studentIndeks")
    List<Integer> findOceneByIspitId(Long id);
    @Query("select i from PolozeniPredmeti pp join Predmet" +
            " p on pp.predmet.id=p.id join Ispit i on i.predmet.id=p.id where pp.studentIndeks.broj = :broj " +
            "and pp.studentIndeks.godina = :godina " +
            "and pp.studentIndeks.studProgramOznaka = :studProgramOznaka " +
            "order by pp.datumPolaganja asc")
    List<Ispit> findPolozniIpsit(int broj, int godina, String studProgramOznaka);

    @Query("select i from Predmet p join" +
            " Ispit i on p.id=i.predmet.id join" +
            " DrziPredmet dp on p.id=dp.predmet.id join SlusaPredmet sp on dp.id=sp.drziPredmet.id where sp.studentIndeks.broj=:broj and sp.studentIndeks.godina = :godina " +
            "and sp.studentIndeks.studProgramOznaka = :studProgramOznaka ")
    List<Ispit> findNePolozniIpsit(int broj, int godina, String studProgramOznaka);




}
