package org.raflab.studsluzba.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.raflab.studsluzba.model.NacinOcene;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
