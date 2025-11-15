package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import org.raflab.studsluzba.model.NacinOcene;

import java.time.LocalDate;

@Data
public class PolozenPredmetResponse {
    private Long id;

    private Long studentIndeksId;
    private Long predmetId;
    private String predmetNaziv;

    private int ocena;

    private LocalDate datumPolaganja;
    private LocalDate datumPriznavanja;

    private String ustanova;

    private NacinOcene nacin;

}
