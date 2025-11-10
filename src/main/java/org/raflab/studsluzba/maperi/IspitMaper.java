package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.controllers.response.NastavnikResponse;
import org.raflab.studsluzba.model.Ispit;
import org.raflab.studsluzba.model.IspitniRok;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.Predmet;

import java.util.ArrayList;
import java.util.List;

public class IspitMaper {
    public static Ispit toIspit(IspitRequest ispitRequest, Nastavnik nastavnik, Predmet predmet, IspitniRok ispitniRok) {
        Ispit ispit=new Ispit();
        ispit.setDatumOdrzavanja(ispitRequest.getDatumOdrzavanja());
        ispit.setJeZakljucen(ispitRequest.getJeZakljucen());
        ispit.setVremePocetka(ispitRequest.getVremePocetka());
        ispit.setNastavnik(nastavnik);
        ispit.setPredmet(predmet);
        ispit.setIspitniRok(ispitniRok);
        return ispit;
    }
    public static IspitResponse toResponse(Ispit ispit)
    {
        IspitResponse ispitResponse=new IspitResponse();
        ispitResponse.setId(ispit.getId());
        ispitResponse.setDatumOdrzavanja(ispit.getDatumOdrzavanja());
        ispitResponse.setNastavnikId(ispit.getNastavnik().getId());
        ispitResponse.setPredmetId(ispit.getPredmet().getId());
        ispitResponse.setJeZakljucen(ispit.getJeZakljucen());
        ispitResponse.setNastavnikIme(ispit.getNastavnik().getIme());
        ispitResponse.setNastavnikPrezime(ispit.getNastavnik().getPrezime());
        ispitResponse.setPredmetNaziv(ispit.getPredmet().getNaziv());
        ispitResponse.setIspitniRokId(ispit.getIspitniRok().getId());
        return ispitResponse;
    }

}
