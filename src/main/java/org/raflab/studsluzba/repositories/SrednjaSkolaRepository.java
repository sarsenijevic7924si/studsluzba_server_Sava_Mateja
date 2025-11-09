package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.SrednjaSkola;
import org.raflab.studsluzba.model.VrstaSkole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SrednjaSkolaRepository extends CrudRepository<SrednjaSkola, Long> {
    List<SrednjaSkola> findByNaziv(String naziv);

    List<SrednjaSkola> findByMesto(String mesto);

    List<SrednjaSkola> findByVrsta(VrstaSkole vrsta);
}
