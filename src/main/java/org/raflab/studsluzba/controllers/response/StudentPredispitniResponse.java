package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class StudentPredispitniResponse {
    private Long id;

    private Long studentIndeksId;
    private Integer brojIndeksa;
    private Integer godina;
    private String studProgramOznaka;

    private Long predIspitnaObavezaId;
    private String nazivObaveze;

    private Double brojPoena;
}
