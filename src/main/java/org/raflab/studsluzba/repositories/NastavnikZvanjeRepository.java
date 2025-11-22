package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.NastavnikZvanje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NastavnikZvanjeRepository extends JpaRepository<NastavnikZvanje, Long> {
    List<NastavnikZvanje> findByNastavnikId(Long nastavnikId);


    List<NastavnikZvanje> findByNastavnikIdAndAktivnoTrue(Long nastavnikId);

    List<NastavnikZvanje> findByNastavnikIdAndAktivnoFalse(Long nastavnikId);


    NastavnikZvanje findFirstByNastavnikIdAndAktivnoTrue(Long nastavnikId);

    boolean existsByNastavnikIdAndZvanjeAndAktivnoTrue(Long nastavnikId, String zvanje);
    void deleteByNastavnikId(Long nastavnikId);
}
