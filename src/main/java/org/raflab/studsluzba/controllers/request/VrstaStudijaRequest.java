package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VrstaStudijaRequest {
    @NotEmpty
    private String skraceniNaziv;

    @NotEmpty
    private String punNaziv;
}
