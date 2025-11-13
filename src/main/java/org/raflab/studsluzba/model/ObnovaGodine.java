package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Data
@Entity
public class ObnovaGodine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentIndeks studentIndeks;

    @ManyToOne(optional = false)
    private SkolskaGodina skolskaGodina;
    private LocalDate datumObnove;
    private String napomena;

}
