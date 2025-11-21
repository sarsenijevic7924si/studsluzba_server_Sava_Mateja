package org.raflab.studsluzba.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class RezultatiIspita {
    private String ime;
    private String prezime;
    private String indeks;
    private Integer ocena;

}
