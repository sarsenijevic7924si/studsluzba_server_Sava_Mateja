package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.model.PrijavaIspita;

import javax.validation.constraints.NotNull;

@Data
public class IzlazakNaIspitRequest {
    @NotNull
    private Integer poeniSaIspita;
    @NotNull
    private Integer ukupniPoeni;
    @NotNull
    private Boolean ponistio;
    private String napomena;


    private Long prijavaIspitaID;
}
