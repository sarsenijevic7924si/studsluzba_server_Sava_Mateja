package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class IspitniRok {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDate pocetakRoka;
    private LocalDate zavrsetakRoka;
    @OneToMany
    private List<Ispit> ispiti;


}
