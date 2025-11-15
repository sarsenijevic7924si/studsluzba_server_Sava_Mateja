package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PolozeniPredmeti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolozeniPremetRepository extends JpaRepository<PolozeniPredmeti,Long> {
    boolean existsByStudentIndeksIdAndPredmetId(Long indeksId, Long predmetId);
    List<PolozeniPredmeti> findByStudentIndeksId(Long studentIndeksId);
}
