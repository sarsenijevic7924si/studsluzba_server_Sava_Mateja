package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.SlusaPredmetRequest;
import org.raflab.studsluzba.controllers.response.SlusaPredmetResponse;
import org.raflab.studsluzba.model.SlusaPredmet;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.DrziPredmet;

public class SlusaPredmetMaper {

    // Pretvara request DTO u entitet
    public static SlusaPredmet toEntity(SlusaPredmetRequest request, StudentIndeks studentIndeks, DrziPredmet drziPredmet) {
        SlusaPredmet s = new SlusaPredmet();
        s.setStudentIndeks(studentIndeks);
        s.setDrziPredmet(drziPredmet);
        return s;
    }

    // Pretvara entitet u response DTO
    public static SlusaPredmetResponse toResponse(SlusaPredmet s) {
        SlusaPredmetResponse r = new SlusaPredmetResponse();
        r.setId(s.getId());
        r.setStudentIndeksId(s.getStudentIndeks().getId());
        r.setStudentIme(s.getStudentIndeks().getStudent().getIme());
        r.setStudentPrezime(s.getStudentIndeks().getStudent().getPrezime());// pretpostavka: StudentIndeks ima referencu na Student
        r.setDrziPredmetId(s.getDrziPredmet().getId());
        r.setPredmetNaziv(s.getDrziPredmet().getPredmet().getNaziv()); // pretpostavka: DrziPredmet referencira Predmet
        return r;
    }
}
