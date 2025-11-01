package org.raflab.studsluzba.model;
import lombok.Data;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Data
public class SrednjaSkola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String mesto;

    @Enumerated(EnumType.STRING)
    private VrstaSkole vrsta;
}
