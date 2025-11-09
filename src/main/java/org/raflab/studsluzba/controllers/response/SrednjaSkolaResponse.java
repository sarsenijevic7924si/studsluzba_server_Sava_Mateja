package org.raflab.studsluzba.controllers.response;

import lombok.Data;
import org.raflab.studsluzba.model.VrstaSkole;
@Data
public class SrednjaSkolaResponse {
    private Long id;
    private String naziv;
    private String mesto;
    private VrstaSkole vrsta;
}
