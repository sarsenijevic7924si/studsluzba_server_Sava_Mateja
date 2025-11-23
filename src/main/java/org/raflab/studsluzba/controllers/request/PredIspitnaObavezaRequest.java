package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PredIspitnaObavezaRequest {
    @NotEmpty
    private String vrsta;
    @NotNull
    private Integer maksimalanBrojPoena;
    @NotNull
    private Long predmetId;
    @NotNull
    private Long skolskaGodinaId;
}
