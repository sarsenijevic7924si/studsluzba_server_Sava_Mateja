package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.model.NacinOcene;

import java.time.LocalDate;

@Data
public class PolozeniPredmetRequest {
    private Long studentIndeksId;
    private Long predmetId;

    private Integer ocena;

    private LocalDate datumPolaganja;
    private LocalDate datumPriznavanja;

    private String ustanova;             // za priznate predmete

    private NacinOcene nacin;
}
