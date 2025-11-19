package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ObnovaGodineResponse {
    private Long id;
    private Long studentIndeksId;
    private String studentIme;
    private String studentPrezime;

    private Long skolskaGodinaId;
    private LocalDate datumObnove;
    private String napomena;
}

