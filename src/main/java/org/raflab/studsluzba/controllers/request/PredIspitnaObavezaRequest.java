package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PredIspitnaObavezaRequest {
    @NotEmpty
    private String vrsta;// TEST, KOLOKVIJUM, SEMINARSKI
    @NotNull
    private Integer maksimalanBrojPoena;
    @NotNull
    private Long predmetId; // samo ID predmeta
    @NotNull
    private Long skolskaGodinaId;
}
