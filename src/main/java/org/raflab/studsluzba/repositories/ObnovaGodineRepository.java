package org.raflab.studsluzba.repositories;

import org.raflab.studsluzba.model.ObnovaGodine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ObnovaGodineRepository extends JpaRepository<ObnovaGodine, Long> {
    List<ObnovaGodine> findByStudentIndeksId(Long indeksId);
}
