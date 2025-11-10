package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.request.IspitniRokRequest;
import org.raflab.studsluzba.controllers.response.IspitniRokResponse;
import org.raflab.studsluzba.model.IspitniRok;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.Predmet;

public class IspitniRokMaper {
    public static IspitniRok toIspitniRok(IspitniRokRequest ispitRequest) {
        IspitniRok ispitniRok=new IspitniRok();
        ispitniRok.setPocetakRoka(ispitRequest.getPocetakRoka());
        ispitniRok.setZavrsetakRoka(ispitRequest.getZavrsetakRoka());
        return ispitniRok;
    }
    public static IspitniRokResponse toResponse(IspitniRok ispitniRok) {
        IspitniRokResponse ispitniRokResponse=new IspitniRokResponse();
        ispitniRokResponse.setId(ispitniRok.getId());
        ispitniRokResponse.setPocetakRoka(ispitniRok.getPocetakRoka());
        ispitniRokResponse.setZavrsetakRoka(ispitniRok.getZavrsetakRoka());
        return ispitniRokResponse;
    }
}
