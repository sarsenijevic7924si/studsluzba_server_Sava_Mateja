package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import org.raflab.studsluzba.model.VrstaSkole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SrednjaSkolaRequest {
    @NotEmpty
    private String naziv;
    @NotEmpty
    private String mesto;
    @NotNull
    private VrstaSkole vrsta;
}
