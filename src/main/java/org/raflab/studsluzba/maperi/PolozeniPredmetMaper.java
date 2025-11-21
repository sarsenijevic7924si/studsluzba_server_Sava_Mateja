package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.PolozeniPredmetRequest;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.request.SrednjaSkolaRequest;
import org.raflab.studsluzba.controllers.response.PolozenPredmetResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.model.*;

public class PolozeniPredmetMaper {
    public static PolozeniPredmeti toEntity(PolozeniPredmetRequest request, StudentIndeks studentIndeks, Predmet predmet
    ) {
        PolozeniPredmeti p = new PolozeniPredmeti();
        p.setStudentIndeks(studentIndeks);
        p.setPredmet(predmet);
        p.setNacin(request.getNacin());

        if (request.getNacin() == NacinOcene.POLAGANJE) {
            p.setDatumPolaganja(request.getDatumPolaganja());
        } else {
            p.setDatumPriznavanja(request.getDatumPriznavanja());
            p.setUstanova(request.getUstanova());
        }

        return p;
    }

    public static PolozenPredmetResponse toResponse(PolozeniPredmeti p) {
        PolozenPredmetResponse r = new PolozenPredmetResponse();

        r.setId(p.getId());
        r.setStudentIndeksId(p.getStudentIndeks().getId());


        r.setPredmetId(p.getPredmet().getId());
        r.setPredmetNaziv(p.getPredmet().getNaziv());

        r.setOcena(p.getOcena());
        r.setNacin(p.getNacin());

        r.setDatumPolaganja(p.getDatumPolaganja());
        r.setDatumPriznavanja(p.getDatumPriznavanja());
        r.setUstanova(p.getUstanova());


        return r;
    }
}
