package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class StudentIndeksRequest { //TODO - add notNull annotations
    @NonNull
    private int godina; //ako npr student mastera upisuje neki program, a godina presla u sledecu???
    @NotEmpty
    private String studProgramOznaka;
    private String nacinFinansiranja;
    @NotNull
    private boolean aktivan;
    private LocalDate vaziOd;//TODO - da li da setujm trenutni datum
    @NotNull
    private Long studentId;
}