package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.UplataRequest;
import org.raflab.studsluzba.controllers.response.UplataResponse;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.Uplata;
import org.springframework.stereotype.Component;

@Component
public class UplataMaper {
    public Uplata toEntity(UplataRequest request, StudentIndeks studentIndeks) {
        Uplata uplata = new Uplata();
        uplata.setStudentIndeks(studentIndeks);
        uplata.setDatumUplate(request.getDatumUplate());
        uplata.setIznosDinari(request.getIznosDinari());
        uplata.setSrednjiKurs(request.getSrednjiKurs());
        return uplata;
    }

    public UplataResponse toResponse(Uplata uplata) {
        UplataResponse response = new UplataResponse();
        response.setId(uplata.getId());
        if (uplata.getStudentIndeks() != null) {
            response.setBrojIndeksa(uplata.getStudentIndeks().toString());
        }
        response.setDatumUplate(uplata.getDatumUplate());
        response.setIznosDinari(uplata.getIznosDinari());
        response.setSrednjiKurs(uplata.getSrednjiKurs());
        return response;
    }
}
