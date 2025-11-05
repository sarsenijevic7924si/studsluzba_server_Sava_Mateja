package org.raflab.studsluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Ispit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDate datumOdrzavanja;
    private LocalDate vremePocetka;
    private Boolean jeZakljucen;

    @ManyToOne
    @JsonIgnore
    private Nastavnik nastavnik;

}
