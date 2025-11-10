package org.raflab.studsluzba.controllers.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class IspitResponse {
    private Long id;
    private LocalDate datumOdrzavanja;
    private LocalTime vremePocetka;
    private Boolean jeZakljucen;

    private Long nastavnikId;
    private String nastavnikIme;
    private String nastavnikPrezime;

    private Long predmetId;
    private String predmetNaziv;

    private Long ispitniRokId;
    private String ispitniRokNaziv;
}
