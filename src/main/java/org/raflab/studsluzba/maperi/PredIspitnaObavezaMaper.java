package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.PredIspitnaObavezaRequest;
import org.raflab.studsluzba.controllers.response.PredIpsitnaObavezaResponse;
import org.raflab.studsluzba.model.PredIspitnaObaveza;
import org.raflab.studsluzba.model.Predmet;
import org.springframework.stereotype.Component;

@Component
public class PredIspitnaObavezaMaper {
    public static PredIpsitnaObavezaResponse toResponse(PredIspitnaObaveza entity) {
        if (entity == null) return null;

        PredIpsitnaObavezaResponse response = new PredIpsitnaObavezaResponse();
        response.setId(entity.getId());
        response.setVrsta(entity.getVrsta());
        response.setMaksimalanBrojPoena(entity.getMaksimalanBrojPoena());

        Predmet predmet = entity.getPredmet();
        if (predmet != null) {
            response.setPredmetId(predmet.getId());
            response.setNazivPredmeta(predmet.getNaziv());
        }

        return response;
    }

    public static PredIspitnaObaveza toEntity(PredIspitnaObavezaRequest request, Predmet predmet) {
        if (request == null) return null;

        PredIspitnaObaveza entity = new PredIspitnaObaveza();
        entity.setVrsta(request.getVrsta());
        entity.setMaksimalanBrojPoena(request.getMaksimalanBrojPoena());
        entity.setPredmet(predmet);

        return entity;
    }
}
