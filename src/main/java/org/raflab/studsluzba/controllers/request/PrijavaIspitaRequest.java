package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.model.Ispit;

import java.time.LocalDate;

@Data
public class PrijavaIspitaRequest {
    private LocalDate datumPrijave;
    private Long ispitId;
}
