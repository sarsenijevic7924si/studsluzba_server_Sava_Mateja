package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PredmetRequest {
    @NotEmpty
    private String sifra;
    @NotEmpty
    private String naziv;
    private String opis;
    @NotNull
    private Integer espb;
    @NotNull
    private Long studProgramId;
    @NotNull
    private boolean obavezan;
}
