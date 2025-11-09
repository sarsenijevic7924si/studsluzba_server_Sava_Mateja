package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudijskiProgram;

public class PredmetMaper {
    public static Predmet toEntity(PredmetRequest request, StudijskiProgram studProgram) {
        Predmet predmet = new Predmet();
        predmet.setSifra(request.getSifra());
        predmet.setNaziv(request.getNaziv());
        predmet.setOpis(request.getOpis());
        predmet.setEspb(request.getEspb());
        predmet.setObavezan(request.isObavezan());
        predmet.setStudProgram(studProgram);
        return predmet;
    }


    public static PredmetResponse toResponse(Predmet predmet) {
        PredmetResponse response = new PredmetResponse();
        response.setId(predmet.getId());
        response.setSifra(predmet.getSifra());
        response.setNaziv(predmet.getNaziv());
        response.setOpis(predmet.getOpis());
        response.setEspb(predmet.getEspb());
        response.setObavezan(predmet.isObavezan());

        if (predmet.getStudProgram() != null) {
            response.setStudProgramId(predmet.getStudProgram().getId());
            response.setStudProgramNaziv(predmet.getStudProgram().getNaziv());
        }

        return response;
    }
}
