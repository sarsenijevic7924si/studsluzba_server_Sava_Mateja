package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.PrijavaIspitaRequest;
import org.raflab.studsluzba.controllers.response.PrijavaIspitaResponse;
import org.raflab.studsluzba.model.Ispit;
import org.raflab.studsluzba.model.PrijavaIspita;
import org.raflab.studsluzba.model.StudentIndeks;

public class PrijavaIspitaMaper {
    public static PrijavaIspita toPrijavaIspita(PrijavaIspitaRequest request, Ispit ispit, StudentIndeks si) {
        PrijavaIspita prijava = new PrijavaIspita();
        prijava.setDatumPrijave(request.getDatumPrijave());
        prijava.setIspit(ispit);
        prijava.setStudentIndeks(si);
        return prijava;
    }

    public static PrijavaIspitaResponse toPrijavaIspitaResponse(PrijavaIspita prijava) {
        PrijavaIspitaResponse response = new PrijavaIspitaResponse();
        response.setId(prijava.getId());
        response.setDatumPrijave(prijava.getDatumPrijave());
        if (prijava.getIspit() != null) {
            response.setIspitID(prijava.getIspit().getId());
        }
        if (prijava.getStudentIndeks() != null) {
            response.setStudentId(prijava.getStudentIndeks().getId());
        }
        return response;
    }
}
