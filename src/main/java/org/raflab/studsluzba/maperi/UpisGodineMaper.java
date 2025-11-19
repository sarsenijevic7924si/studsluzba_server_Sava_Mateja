package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.UpisGodineRequest;
import org.raflab.studsluzba.controllers.response.UpisGodineResponse;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.UpisGodine;
import org.springframework.stereotype.Component;

@Component
public class UpisGodineMaper {

    public UpisGodine toEntity(UpisGodineRequest request, StudentIndeks si, SkolskaGodina sg) {
        UpisGodine ug = new UpisGodine();
        ug.setStudentIndeks(si);
        ug.setSkolskaGodina(sg);
        ug.setGodinaKojaSeUpisuje(request.getGodinaKojaSeUpisuje());
        ug.setDatumUpisa(request.getDatumUpisa());
        ug.setNapomena(request.getNapomena());
        return ug;
    }

    public UpisGodineResponse toResponse(UpisGodine up) {
        UpisGodineResponse resp = new UpisGodineResponse();
        resp.setId(up.getId());
        resp.setStudentIndeksId(up.getStudentIndeks().getId());
        resp.setSkolskaGodinaId(up.getSkolskaGodina().getId());
        resp.setGodinaKojaSeUpisuje(up.getGodinaKojaSeUpisuje());
        resp.setDatumUpisa(up.getDatumUpisa());
        resp.setNapomena(up.getNapomena());
        return resp;
    }
}
