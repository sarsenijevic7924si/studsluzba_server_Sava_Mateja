package org.raflab.studsluzba.maperi;



import org.raflab.studsluzba.controllers.request.ObnovaGodineRequest;
import org.raflab.studsluzba.controllers.response.ObnovaGodineResponse;
import org.raflab.studsluzba.model.ObnovaGodine;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.SkolskaGodina;

public class ObnovaGodineMaper {

    public static ObnovaGodine toEntity(ObnovaGodineRequest request, StudentIndeks studentIndeks, SkolskaGodina skolskaGodina) {
        ObnovaGodine o = new ObnovaGodine();
        o.setStudentIndeks(studentIndeks);
        o.setSkolskaGodina(skolskaGodina);
        o.setDatumObnove(request.getDatumObnove());
        o.setNapomena(request.getNapomena());
        return o;
    }

    public static ObnovaGodineResponse toResponse(ObnovaGodine o) {
        ObnovaGodineResponse r = new ObnovaGodineResponse();
        r.setId(o.getId());
        r.setStudentIndeksId(o.getStudentIndeks().getId());
        r.setStudentIme(o.getStudentIndeks().getStudent().getIme());
        r.setStudentPrezime(o.getStudentIndeks().getStudent().getPrezime());
        r.setSkolskaGodinaId(o.getSkolskaGodina().getId());
        r.setDatumObnove(o.getDatumObnove());
        r.setNapomena(o.getNapomena());
        return r;
    }
}
