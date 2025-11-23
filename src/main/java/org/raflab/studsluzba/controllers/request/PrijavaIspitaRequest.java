package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;
import org.raflab.studsluzba.model.Ispit;

import java.time.LocalDate;

@Data
public class PrijavaIspitaRequest {
    @NonNull
    private LocalDate datumPrijave;
    @NonNull
    private Long ispitId;
    @NonNull
    private Long studentId;
}
