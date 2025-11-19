package org.raflab.studsluzba.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpisGodineResponse {
    private Long id;
    private Long studentIndeksId;
    private Long skolskaGodinaId;
    private Integer godinaKojaSeUpisuje;
    private LocalDate datumUpisa;
    private String napomena;
}
