package org.raflab.studsluzba.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PolozeniPredmeti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentIndeks studentIndeks;

    @ManyToOne
    private Predmet predmet;

    private int ocena;

    private LocalDate datumPolaganja;
    private LocalDate datumPriznavanja;

    private String ustanova;

    @Enumerated(EnumType.STRING)
    private NacinOcene nacin;
}
