package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StudentPredispitniRequest {
    @NotNull
    private Long studentIndeksId;
    @NotNull
    private Long predIspitnaObavezaId;
    @NotNull
    private Double brojPoena;
}
