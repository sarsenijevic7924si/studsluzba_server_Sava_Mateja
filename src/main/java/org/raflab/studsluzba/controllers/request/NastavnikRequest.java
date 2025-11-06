package org.raflab.studsluzba.controllers.request;

import lombok.Data;
import lombok.NonNull;
import org.raflab.studsluzba.model.NastavnikZvanje;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Data
public class NastavnikRequest {

    @NotEmpty
    private String ime;
    @NotEmpty
    private String prezime;
    @NotEmpty
    private String srednjeIme;
    @NotEmpty
    private String email;
    private String brojTelefona;
    private String adresa;
    private Set<NastavnikZvanje> zvanja;

    private LocalDate datumRodjenja;
    private Character pol;
    private String jmbg;
}
