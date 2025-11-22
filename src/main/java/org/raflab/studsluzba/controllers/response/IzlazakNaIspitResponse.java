package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class IzlazakNaIspitResponse {
    private Long id;
    private Integer poeniSaIspita;
    private Double predispitniPoeni;
    private Integer ukupniPoeni;
    private Boolean ponistio;
    private String napomena;
    private Long prijavaIspitaID;
    private Long studentId;

    private String studProgram;
    private Integer godinaUpisa;
    private Integer brojIndeksa;
}
