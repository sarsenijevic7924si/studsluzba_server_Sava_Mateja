package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.SkolskaGodinaRequest;
import org.raflab.studsluzba.controllers.response.SkolskaGodinaResponse;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.springframework.stereotype.Component;

@Component
public class SkolskaGodinaMaper {
    public SkolskaGodina toEntity(SkolskaGodinaRequest request) {
        SkolskaGodina sg = new SkolskaGodina();
        sg.setGodina(request.getGodina());
        sg.setAktivan(request.getAktivan());
        return sg;
    }

    public SkolskaGodinaResponse toResponse(SkolskaGodina sg) {
        SkolskaGodinaResponse response = new SkolskaGodinaResponse();
        response.setId(sg.getId());
        response.setGodina(sg.getGodina());
        response.setAktivan(sg.getAktivan());
        return response;
    }
}
