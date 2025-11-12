package org.raflab.studsluzba.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UplataResponse {
    private Long id;
    private String brojIndeksa;
    private LocalDate datumUplate;
    private Double iznosDinari;
    private Double srednjiKurs;
}
