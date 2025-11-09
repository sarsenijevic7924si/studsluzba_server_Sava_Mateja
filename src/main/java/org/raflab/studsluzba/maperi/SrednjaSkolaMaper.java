package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.SrednjaSkolaRequest;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.model.SrednjaSkola;

public class SrednjaSkolaMaper {
    public static SrednjaSkola toEntity(SrednjaSkolaRequest request) {
        if (request == null) return null;

        SrednjaSkola skola = new SrednjaSkola();
        skola.setNaziv(request.getNaziv());
        skola.setMesto(request.getMesto());
        skola.setVrsta(request.getVrsta());
        return skola;
    }


    public static SrednjaSkolaResponse toResponse(SrednjaSkola skola) {
        if (skola == null) return null;

        SrednjaSkolaResponse response = new SrednjaSkolaResponse();
        response.setId(skola.getId());
        response.setNaziv(skola.getNaziv());
        response.setMesto(skola.getMesto());
        response.setVrsta(skola.getVrsta());
        return response;
    }
}
