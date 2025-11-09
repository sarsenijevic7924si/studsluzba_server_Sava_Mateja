package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.StudentIndeksRequest;
import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.model.StudentIndeks;
import org.springframework.stereotype.Component;

@Component
public class StudentIndeksMaper {
    public static StudentIndeks toEntity(StudentIndeksRequest request) {
        if (request == null) return null;

        StudentIndeks indeks = new StudentIndeks();
        indeks.setGodina(request.getGodina());
        indeks.setNacinFinansiranja(request.getNacinFinansiranja());
        indeks.setAktivan(request.isAktivan());

        return indeks;
    }

    public static StudentIndeksResponse toResponse(StudentIndeks entity) {
        if (entity == null) return null;

        StudentIndeksResponse response = new StudentIndeksResponse();
        response.setId(entity.getId());
        response.setBroj(entity.getBroj());
        response.setGodina(entity.getGodina());
        response.setNacinFinansiranja(entity.getNacinFinansiranja());
        response.setAktivan(entity.isAktivan());
        response.setVaziOd(entity.getVaziOd());
        response.setStudent(entity.getStudent());
        response.setStudijskiProgram(entity.getStudijskiProgram());
        response.setOstvarenoEspb(entity.getOstvarenoEspb());
        if (entity.getStudijskiProgram() != null) {
            response.setStudProgramOznaka(entity.getStudProgramOznaka());
        }
        return response;
    }
}
