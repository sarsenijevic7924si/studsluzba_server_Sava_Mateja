package org.raflab.studsluzba.maperi;

import org.raflab.studsluzba.controllers.request.IzlazakNaIspitRequest;
import org.raflab.studsluzba.controllers.response.IzlazakNaIspitResponse;
import org.raflab.studsluzba.model.IzlazakNaIspit;
import org.raflab.studsluzba.model.PrijavaIspita;

public class IzlazakNaIspitMaper {
    public static IzlazakNaIspit toIzlazakNaIspit(IzlazakNaIspitRequest request, PrijavaIspita prijavaIspita) {
        IzlazakNaIspit izlazak=new IzlazakNaIspit();
        izlazak.setOcena(request.getOcena());
        izlazak.setNapomena(request.getNapomena());
        izlazak.setPonistio(request.getPonistio());
        izlazak.setPoeniSaIspita(request.getPoeniSaIspita());
        izlazak.setUkupniPoeni(request.getUkupniPoeni());
        izlazak.setPrijava(prijavaIspita);
        return izlazak;
    }
    public static IzlazakNaIspitResponse toIzlazakNaIspitResponse(IzlazakNaIspit izlazakNaIspit) {
        IzlazakNaIspitResponse response=new IzlazakNaIspitResponse();
        response.setOcena(izlazakNaIspit.getOcena());
        response.setId(izlazakNaIspit.getId());
        response.setNapomena(izlazakNaIspit.getNapomena());
        response.setPonistio(izlazakNaIspit.getPonistio());
        response.setPoeniSaIspita(izlazakNaIspit.getPoeniSaIspita());
        response.setUkupniPoeni(izlazakNaIspit.getUkupniPoeni());
        if (izlazakNaIspit.getPrijava() != null) {
            response.setPrijavaIspitaID(izlazakNaIspit.getPrijava().getId());
        }
        return response;
    }
}
