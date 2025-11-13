package org.raflab.studsluzba.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredIpsitnaObavezaResponse {
    private Long id;
    private String vrsta;
    private Integer maksimalanBrojPoena;
    private Long predmetId;
    private String nazivPredmeta;
}
