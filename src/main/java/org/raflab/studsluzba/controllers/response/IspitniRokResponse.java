package org.raflab.studsluzba.controllers.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class IspitniRokResponse {
    private Long id;
    private LocalDate pocetakRoka;
    private LocalDate zavrsetakRoka;

    private Long skolskaGodinaId;
    private Integer godina;
}
