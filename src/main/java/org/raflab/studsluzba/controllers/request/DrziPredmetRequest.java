package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data

public class DrziPredmetRequest {
    @NotNull
    private Long predmetId;

    @NotNull
    private Long nastavnikId;

    @NotNull
    private Long skolskaGodinaId;

}
