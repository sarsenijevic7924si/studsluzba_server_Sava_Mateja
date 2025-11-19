package org.raflab.studsluzba.controllers.response;

import lombok.Data;

@Data
public class SlusaPredmetResponse {
    private Long id;
    private Long studentIndeksId;
    private String studentIme;
    private String studentPrezime; // opcionalno, radi lakšeg prikaza
    private Long drziPredmetId;
    private String predmetNaziv;       // opcionalno, naziv predmeta
}

