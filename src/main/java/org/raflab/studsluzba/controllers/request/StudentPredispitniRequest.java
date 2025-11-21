package org.raflab.studsluzba.controllers.request;

import lombok.Data;

@Data
public class StudentPredispitniRequest {
    private Long studentIndeksId;
    private Long predIspitnaObavezaId;
    private Double brojPoena;
}
