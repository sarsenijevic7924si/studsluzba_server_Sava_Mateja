package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class UpisGodineRequest {
    @NonNull
    private Long studentIndeksId;
    @NonNull
    private Long skolskaGodinaId;
    @NonNull
    private Integer godinaKojaSeUpisuje;
    @NonNull
    private LocalDate datumUpisa;
    private String napomena;
}