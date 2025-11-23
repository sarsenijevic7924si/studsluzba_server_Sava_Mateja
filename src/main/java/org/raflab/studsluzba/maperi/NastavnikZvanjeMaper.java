package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
import org.raflab.studsluzba.controllers.response.NastavnikZvanjeResponse;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.NastavnikZvanje;

public class NastavnikZvanjeMaper {

    public static NastavnikZvanje toEntity(NastavnikZvanjeRequest request, Nastavnik nastavnik) {
        if (request == null) return null;

        NastavnikZvanje zvanje = new NastavnikZvanje();
        zvanje.setDatumIzbora(request.getDatumIzbora());
        zvanje.setNaucnaOblast(request.getNaucnaOblast());
        zvanje.setUzaNaucnaOblast(request.getUzaNaucnaOblast());
        zvanje.setZvanje(request.getZvanje());
        zvanje.setAktivno(request.isAktivno());
        zvanje.setNastavnik(nastavnik);

        return zvanje;
    }


    public static NastavnikZvanjeResponse toResponse(NastavnikZvanje zvanje) {
        if (zvanje == null) return null;

        NastavnikZvanjeResponse response = new NastavnikZvanjeResponse();
        response.setId(zvanje.getId());
        response.setDatumIzbora(zvanje.getDatumIzbora());
        response.setNaucnaOblast(zvanje.getNaucnaOblast());
        response.setUzaNaucnaOblast(zvanje.getUzaNaucnaOblast());
        response.setZvanje(zvanje.getZvanje());
        response.setAktivno(zvanje.isAktivno());

        if (zvanje.getNastavnik() != null) {
            response.setNastavnikId(zvanje.getNastavnik().getId());
            response.setNastavnikIme(zvanje.getNastavnik().getIme());
            response.setNastavnikPrezime(zvanje.getNastavnik().getPrezime());
        }

        return response;
    }
}
