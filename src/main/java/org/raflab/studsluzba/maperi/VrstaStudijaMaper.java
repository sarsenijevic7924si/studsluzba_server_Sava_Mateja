package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.VrstaStudijaRequest;
import org.raflab.studsluzba.controllers.response.VrstaStudijaResponse;
import org.raflab.studsluzba.model.VrstaStudija;

public class VrstaStudijaMaper {
    public static VrstaStudijaResponse toResponse(VrstaStudija entity) {
        if (entity == null) return null;
        return new VrstaStudijaResponse(
                entity.getId(),
                entity.getSkraceniNaziv(),
                entity.getPunNaziv()
        );
    }

    public static VrstaStudija toEntity(VrstaStudijaRequest request) {
        if (request == null) return null;
        VrstaStudija entity = new VrstaStudija();
        entity.setSkraceniNaziv(request.getSkraceniNaziv());
        entity.setPunNaziv(request.getPunNaziv());
        return entity;
    }
}
