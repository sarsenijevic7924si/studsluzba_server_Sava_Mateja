package org.raflab.studsluzba.controllers.request;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ObnovaGodineRequest {
    private Long studentIndeksId;   // ID studenta
    private Long skolskaGodinaId;   // ID školske godine
    private LocalDate datumObnove;  // datum obnove
    private String napomena;        // napomena
}
