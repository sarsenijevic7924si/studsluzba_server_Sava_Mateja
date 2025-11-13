package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.PrijavaIspita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita, Long> {
}
