package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
public class DrziPredmetNewRequest {
    @NonNull
    private Long predmetId;
    @NotEmpty
    private String predmetNaziv;
    @NotEmpty
    private String emailNastavnik;
}
