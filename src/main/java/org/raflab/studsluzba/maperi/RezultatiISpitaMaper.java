package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.request.RezultatiStudentiRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.RezultatiIspita;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudijskiProgram;

public class RezultatiISpitaMaper {



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
