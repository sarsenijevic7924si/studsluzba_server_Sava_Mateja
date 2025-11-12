package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Grupa;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupaRepository extends JpaRepository<Grupa, Long> {
    // Sve grupe koje pripadaju određenom studijskom programu
    List<Grupa> findByStudijskiProgram(StudijskiProgram studijskiProgram);

    // Sve grupe po ID-u studijskog programa
    List<Grupa> findByStudijskiProgram_Id(Long studijskiProgramId);

    // Pretraga grupe koja sadrži predmet po ID-u
    List<Grupa> findByPredmeti_Id(Long predmetId);
}
