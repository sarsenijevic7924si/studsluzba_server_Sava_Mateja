package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StudentPredispitni {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private StudentIndeks idneks;
    @ManyToOne
    private PredIspitnaObaveza predIspitnaObaveza;
    private Double brojPoena;

}
