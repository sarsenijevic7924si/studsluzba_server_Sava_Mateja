package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class IzlazakNaIspitResponse {
    private Long id;
    private Integer poeniSaIspita;
    private Integer ukupniPoeni;
    private Boolean ponistio;
    private String napomena;
    private Integer ocena;
    private Long prijavaIspitaID;
}
