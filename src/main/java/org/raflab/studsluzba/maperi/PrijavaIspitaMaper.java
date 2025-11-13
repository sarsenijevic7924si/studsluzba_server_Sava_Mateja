package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.PrijavaIspitaRequest;
import org.raflab.studsluzba.controllers.response.PrijavaIspitaResponse;
import org.raflab.studsluzba.model.Ispit;
import org.raflab.studsluzba.model.PrijavaIspita;

public class PrijavaIspitaMaper {
    public static PrijavaIspita toPrijavaIspita(PrijavaIspitaRequest request, Ispit ispit) {
        PrijavaIspita prijava = new PrijavaIspita();
        prijava.setDatumPrijave(request.getDatumPrijave());
        prijava.setIspit(ispit);
        return prijava;
    }

    public static PrijavaIspitaResponse toPrijavaIspitaResponse(PrijavaIspita prijava) {
        PrijavaIspitaResponse response = new PrijavaIspitaResponse();
        response.setId(prijava.getId());
        response.setDatumPrijave(prijava.getDatumPrijave());
        if (prijava.getIspit() != null) {
            response.setIspitID(prijava.getIspit().getId());
        }
        return response;
    }
}
