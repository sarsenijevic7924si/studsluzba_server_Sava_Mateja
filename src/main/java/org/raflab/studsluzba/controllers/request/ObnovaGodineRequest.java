package org.raflab.studsluzba.controllers.request;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class ObnovaGodineRequest {
    @NonNull
    private Long studentIndeksId;
    @NonNull
    private Long skolskaGodinaId;
    @NonNull
    private LocalDate datumObnove;
    private String napomena;
}
