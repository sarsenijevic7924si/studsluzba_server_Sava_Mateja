package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.Grupa;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupaRepository extends JpaRepository<Grupa, Long> {

    List<Grupa> findByStudijskiProgram(StudijskiProgram studijskiProgram);


    List<Grupa> findByStudijskiProgram_Id(Long studijskiProgramId);


    List<Grupa> findByPredmeti_Id(Long predmetId);
}
