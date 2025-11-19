package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class IzlazakNaIspit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer poeniSaIspita;
    private Integer ukupniPoeni; // predispitni + ispit
    private Boolean ponistio;
    private String napomena;
 //   private Integer ocena; // 6-10

    @ManyToOne
    private PrijavaIspita prijava;
}
