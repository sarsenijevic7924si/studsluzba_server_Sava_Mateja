package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.DrziPredmetRequest;
import org.raflab.studsluzba.controllers.response.DrziPredmetResponse;
import org.raflab.studsluzba.model.DrziPredmet;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;

public class DrziPredmetMaper {
    public static DrziPredmet toEntity(
            DrziPredmetRequest req,
            Predmet predmet,
            Nastavnik nastavnik,
            SkolskaGodina skolskaGod)
    {
        DrziPredmet dp = new DrziPredmet();
        dp.setPredmet(predmet);
        dp.setNastavnik(nastavnik);
        dp.setSkolskaGodina(skolskaGod);
        return dp;
    }

    public static DrziPredmetResponse toResponse(DrziPredmet dp) {
        DrziPredmetResponse r = new DrziPredmetResponse();
        r.setId(dp.getId());
        r.setPredmetId(dp.getPredmet().getId());
        r.setPredmetNaziv(dp.getPredmet().getNaziv());
        r.setNastavnikId(dp.getNastavnik().getId());
        r.setNastavnikIme(dp.getNastavnik().getIme());
        r.setNastavnikPrezime(dp.getNastavnik().getPrezime());
        r.setSkolskaGodina(dp.getSkolskaGodina().getGodina());

        return r;
    }
}
