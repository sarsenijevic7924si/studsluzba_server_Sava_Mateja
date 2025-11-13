package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PredIspitnaObaveza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredIspitnaObavezaRepository extends JpaRepository<PredIspitnaObaveza, Long> {
}
