package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IzlazakNaIspitRequest;
import org.raflab.studsluzba.controllers.response.IzlazakNaIspitResponse;
import org.raflab.studsluzba.maperi.IzlazakNaIspitMaper;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.IzlazakNaIspitRepository;
import org.raflab.studsluzba.repositories.PolozeniPremetRepository;
import org.raflab.studsluzba.repositories.PrijavaIspitaRepository;
import org.raflab.studsluzba.repositories.StudentPredispitniRepository;
import org.raflab.studsluzba.utils.PredmetUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IzlazakNaIspitService {
    final IzlazakNaIspitRepository ispitRepository;
    private final PrijavaIspitaRepository prijavaIspitaRepository;
    private final IzlazakNaIspitRepository izlazakNaIspitRepository;
    private final PolozeniPremetRepository polozeniPremetRepository;
    private final StudentPredispitniRepository studentPredispitniRepository;

    @Transactional
    public IzlazakNaIspitResponse saveIzlazak(IzlazakNaIspitRequest izlazakReq) {
        PrijavaIspita prijavaIspita = prijavaIspitaRepository.findById(izlazakReq.getPrijavaIspitaID()).orElseThrow(()->new RuntimeException("Prijava Ispita nije pronadjena"));
        IzlazakNaIspit izlazakNaIspit = IzlazakNaIspitMaper.toIzlazakNaIspit(izlazakReq, prijavaIspita);
        IzlazakNaIspit sacuvan= ispitRepository.save(izlazakNaIspit);
        this.proveriIPoloziAkoTreba(sacuvan);
        return IzlazakNaIspitMaper.toIzlazakNaIspitResponse(sacuvan);
    }
    private void proveriIPoloziAkoTreba(IzlazakNaIspit izlazak) {

        PrijavaIspita prijava = izlazak.getPrijava();
        StudentIndeks indeks = prijava.getStudentIndeks();
        Predmet predmet = prijava.getIspit().getPredmet();

        // 1. Predispitni poeni
        Double predispitni = studentPredispitniRepository
                .sumPoeniForStudentAndPredmet(indeks.getId(), predmet.getId());

        if (predispitni == null) predispitni = 0.0;

        int ukupno = (int) (predispitni + izlazak.getPoeniSaIspita());
        izlazak.setUkupniPoeni(ukupno);

        // 2. Uslov za polaganje
        if (ukupno < 51) return;

        // 3. Da li već položen?
        boolean vecPolozio = polozeniPremetRepository
                .existsByStudentIndeksAndPredmet(indeks, predmet);

        if (vecPolozio) return;

        // 4. Upis u položene
        PolozeniPredmeti pp = new PolozeniPredmeti();
        pp.setStudentIndeks(indeks);
        pp.setPredmet(predmet);
        pp.setDatumPolaganja(LocalDate.now());
        pp.setOcena(PredmetUtils.izracunajOcenu(ukupno));

        polozeniPremetRepository.save(pp);
    }
    //dodavanje bodova u studentPredispitneServis
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
