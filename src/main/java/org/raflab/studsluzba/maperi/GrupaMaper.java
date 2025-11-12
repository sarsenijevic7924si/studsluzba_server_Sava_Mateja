package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.GrupaRequest;
import org.raflab.studsluzba.controllers.response.GrupaResponse;
import org.raflab.studsluzba.model.Grupa;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupaMaper {
    public Grupa toEntity(GrupaRequest request, StudijskiProgram studijskiProgram, List<Predmet> predmeti) {
        Grupa grupa = new Grupa();
        grupa.setStudijskiProgram(studijskiProgram);
        grupa.setPredmeti(predmeti);
        return grupa;
    }

    public GrupaResponse toResponse(Grupa grupa) {
        GrupaResponse response = new GrupaResponse();
        response.setId(grupa.getId());

        if (grupa.getStudijskiProgram() != null) {
            response.setStudijskiProgramID(grupa.getStudijskiProgram().getId());
            response.setStudijskiProgramNaziv(grupa.getStudijskiProgram().getNaziv());
        }

        if (grupa.getPredmeti() != null) {
            List<String> naziviPredmeta = grupa.getPredmeti()
                    .stream()
                    .map(Predmet::getNaziv)
                    .collect(Collectors.toList());
            response.setPredmetiNazivi(naziviPredmeta);
        }

        return response;
    }
}
