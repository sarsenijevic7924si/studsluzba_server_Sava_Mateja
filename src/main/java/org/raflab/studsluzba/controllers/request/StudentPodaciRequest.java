package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class StudentPodaciRequest {

    @NotEmpty
    private String ime;	  // not null
    @NotEmpty
    private String prezime;  // not null
    @NotEmpty
    private String srednjeIme;   // not null

    private String jmbg;
    @NotNull
    private LocalDate datumRodjenja;

    private String mestoRodjenja;
    private String mestoPrebivalista;

    private String drzavaRodjenja;
    @NotEmpty
    private String drzavljanstvo;
    @NotEmpty
    private String nacionalnost;   // samoizjasnjavanje, moze bilo sta
    @NotEmpty
    private Character pol;    // not null
    @NotEmpty
    private String adresa;  // not null
    private String brojTelefonaMobilni;
    private String brojTelefonaFiksni;
    @Email
    @NotEmpty
    private String email;  // not null

    private String brojLicneKarte;
    private String licnuKartuIzdao;
    private String mestoStanovanja;
    private String adresaStanovanja;   // u toku studija
    private Long srednjaId;
    private double uspehSrednja;
    private double uspehPrijemni;
}
