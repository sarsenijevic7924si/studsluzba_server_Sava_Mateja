package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.request.IspitniRokRequest;
import org.raflab.studsluzba.controllers.response.IspitniRokResponse;
import org.raflab.studsluzba.model.IspitniRok;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;

public class IspitniRokMaper {
    public static IspitniRok toIspitniRok(IspitniRokRequest ispitRequest, SkolskaGodina sg) {
        IspitniRok ispitniRok=new IspitniRok();
        ispitniRok.setPocetakRoka(ispitRequest.getPocetakRoka());
        ispitniRok.setZavrsetakRoka(ispitRequest.getZavrsetakRoka());
        ispitniRok.setSkolskaGodina(sg);
        return ispitniRok;
    }
    public static IspitniRokResponse toResponse(IspitniRok ispitniRok) {
        IspitniRokResponse ispitniRokResponse=new IspitniRokResponse();
        ispitniRokResponse.setId(ispitniRok.getId());
        ispitniRokResponse.setPocetakRoka(ispitniRok.getPocetakRoka());
        ispitniRokResponse.setZavrsetakRoka(ispitniRok.getZavrsetakRoka());
        if(ispitniRok.getSkolskaGodina()!=null)
        {
            ispitniRokResponse.setSkolskaGodinaId(ispitniRok.getSkolskaGodina().getId());
            ispitniRokResponse.setGodina(ispitniRok.getSkolskaGodina().getGodina());
        }
        return ispitniRokResponse;
    }
}
