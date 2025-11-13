package org.raflab.studsluzba.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class StudijskiProgramReqest {
    @NotEmpty
    private String oznaka;
    @NotEmpty
    private String naziv;
    @NotNull
    private Integer godinaAkreditacije;
    @NotEmpty
    private String zvanje;
    @NotNull
    private Integer trajanjeGodina;
    @NotNull
    private Integer trajanjeSemestara;
    @NotEmpty
    private Long vrstaStudijaId;
    @NotNull
    private Integer ukupnoEspb;
}
