package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UplataRequest {
    @NotEmpty
    private Long studentIndeksId;
    @NotNull
    private LocalDate datumUplate;
    @NotNull
    private Double iznosDinari;
    private Double srednjiKurs;
}
