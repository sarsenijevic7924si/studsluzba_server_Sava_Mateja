package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IzlazakNaIspitRequest;
import org.raflab.studsluzba.controllers.response.IzlazakNaIspitResponse;
import org.raflab.studsluzba.maperi.IzlazakNaIspitMaper;
import org.raflab.studsluzba.model.IzlazakNaIspit;
import org.raflab.studsluzba.model.PrijavaIspita;
import org.raflab.studsluzba.repositories.IzlazakNaIspitRepository;
import org.raflab.studsluzba.repositories.PrijavaIspitaRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IzlazakNaIspitService {
    final IzlazakNaIspitRepository ispitRepository;
    private final PrijavaIspitaRepository prijavaIspitaRepository;
    private final IzlazakNaIspitRepository izlazakNaIspitRepository;

    @Transactional
    public IzlazakNaIspitResponse saveIzlazak(IzlazakNaIspitRequest izlazakReq) {
        PrijavaIspita prijavaIspita = prijavaIspitaRepository.findById(izlazakReq.getPrijavaIspitaID()).orElseThrow(()->new RuntimeException("Prijava Ispita nije pronadjena"));
        IzlazakNaIspit izlazakNaIspit = IzlazakNaIspitMaper.toIzlazakNaIspit(izlazakReq, prijavaIspita);
        IzlazakNaIspit sacuvan= ispitRepository.save(izlazakNaIspit);
        return IzlazakNaIspitMaper.toIzlazakNaIspitResponse(sacuvan);
    }
    @Transactional
    public void deleteIzlazak(Long id) {
        IzlazakNaIspit izlaza=izlazakNaIspitRepository.findById(id).orElseThrow(() -> new RuntimeException("Izlazak sa ID " + id + " nije pronađen."));
        izlazakNaIspitRepository.delete(izlaza);
    }
    @Transactional
    public IzlazakNaIspitResponse updateIzlazakNaIspit(Long id, IzlazakNaIspitRequest request) {
        IzlazakNaIspit izlazak = izlazakNaIspitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Izlazak na ispit nije pronađen"));

        izlazak.setPoeniSaIspita(request.getPoeniSaIspita());
        izlazak.setUkupniPoeni(request.getUkupniPoeni());
        izlazak.setPonistio(request.getPonistio());
        izlazak.setNapomena(request.getNapomena());
        izlazak.setOcena(request.getOcena());

        if (izlazak.getPrijava() == null ||
                !izlazak.getPrijava().getId().equals(request.getPrijavaIspitaID())) {

            PrijavaIspita prijava = prijavaIspitaRepository.findById(request.getPrijavaIspitaID())
                    .orElseThrow(() -> new RuntimeException("Prijava ispita nije pronađena"));
            izlazak.setPrijava(prijava);
        }

        IzlazakNaIspit updated = izlazakNaIspitRepository.save(izlazak);
        return IzlazakNaIspitMaper.toIzlazakNaIspitResponse(updated);
    }
    public List<IzlazakNaIspitResponse>findall()
    {
        return izlazakNaIspitRepository.findAll().stream().map(IzlazakNaIspitMaper::toIzlazakNaIspitResponse).collect(Collectors.toList());
    }


}
