package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PredIspitnaObaveza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vrsta; // TEST, KOLOKVIJUM, SEMINARSKI
    private Integer maksimalanBrojPoena;

    @ManyToOne
    private DrziPredmet drziPredmet;
}
