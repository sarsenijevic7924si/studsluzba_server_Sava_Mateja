package org.raflab.studsluzba.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class UpisGodine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentIndeks studentIndeks;

    private Integer godinaKojaSeUpisuje;
    private LocalDate datumUpisa;
    private String napomena;

}
