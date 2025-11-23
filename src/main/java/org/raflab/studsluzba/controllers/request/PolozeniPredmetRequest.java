package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;
import org.raflab.studsluzba.model.NacinOcene;

import java.time.LocalDate;

@Data
public class PolozeniPredmetRequest {
    @NonNull
    private Long studentIndeksId;
    @NonNull
    private Long predmetId;

    @NonNull
    private LocalDate datumPolaganja;
    @NonNull
    private LocalDate datumPriznavanja;

    private String ustanova;

    private NacinOcene nacin;
}
