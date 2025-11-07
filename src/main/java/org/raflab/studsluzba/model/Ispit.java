package org.raflab.studsluzba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Ispit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDate datumOdrzavanja;
    private LocalTime vremePocetka;
    private Boolean jeZakljucen;

    @ManyToOne
    @JsonIgnore
    private Nastavnik nastavnik;
    @ManyToOne
    @JsonIgnore
    private Predmet predmet;
    @ManyToOne
    private IspitniRok ispitniRok;
    @OneToMany(mappedBy = "ispit")
    private List<PrijavaIspita> prijave;

}
