package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.StudentPredispitniRequest;
import org.raflab.studsluzba.controllers.response.StudentPredispitniResponse;
import org.raflab.studsluzba.model.PredIspitnaObaveza;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudentPredispitni;

public class StudentPredispitniMaper {
    public static StudentPredispitni toEntity(StudentPredispitniRequest req,
                                              StudentIndeks studentIndeks,
                                              PredIspitnaObaveza obaveza) {
        StudentPredispitni sp = new StudentPredispitni();
        sp.setIdneks(studentIndeks);
        sp.setPredIspitnaObaveza(obaveza);
        sp.setBrojPoena(req.getBrojPoena());
        return sp;
    }

    public static StudentPredispitniResponse toResponse(StudentPredispitni sp) {
        StudentPredispitniResponse resp = new StudentPredispitniResponse();
        resp.setId(sp.getId());
        resp.setStudentIndeksId(sp.getIdneks().getId());
        resp.setBrojIndeksa(sp.getIdneks().getBroj());
        resp.setGodina(sp.getIdneks().getGodina());
        resp.setStudProgramOznaka(sp.getIdneks().getStudProgramOznaka());

        resp.setPredIspitnaObavezaId(sp.getPredIspitnaObaveza().getId());
        resp.setNazivObaveze(sp.getPredIspitnaObaveza().getVrsta());

        resp.setBrojPoena(sp.getBrojPoena());
        return resp;
    }
}
