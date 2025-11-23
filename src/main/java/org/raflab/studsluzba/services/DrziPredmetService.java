package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.DrziPredmetRequest;
import org.raflab.studsluzba.controllers.response.DrziPredmetResponse;
import org.raflab.studsluzba.maperi.DrziPredmetMaper;
import org.raflab.studsluzba.model.DrziPredmet;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.DrziPredmetRepository;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrziPredmetService {

    final DrziPredmetRepository drziPredmetRepository;
    final PredmetRepository predmetRepository;
    final NastavnikRepository nastavnikRepository;
    final SkolskaGodinaRepository skolskaGodinaRepository;


    @Transactional
    public DrziPredmetResponse saveDrziPredmet(DrziPredmetRequest request) {

        Predmet predmet = predmetRepository.findById(request.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));

        Nastavnik nastavnik = nastavnikRepository.findById(request.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nastavnik ne postoji"));
        SkolskaGodina skolskaGodina = skolskaGodinaRepository
                .findById(request.getSkolskaGodinaId())
                .orElseThrow(() -> new RuntimeException("Nema skolske godine"));

        DrziPredmet dp = DrziPredmetMaper.toEntity(request, predmet, nastavnik,skolskaGodina);

        return DrziPredmetMaper.toResponse(drziPredmetRepository.save(dp));
    }

    @Transactional(readOnly = true)
    public DrziPredmetResponse findById(Long id) {
        DrziPredmet dp = drziPredmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DrziPredmet ne postoji"));

        return DrziPredmetMaper.toResponse(dp);
    }

    @Transactional(readOnly = true)
    public List<DrziPredmetResponse> findAll() {

        Iterable<DrziPredmet> drziPredmetiIterable = drziPredmetRepository.findAll();

        List<DrziPredmet> drziPredmeti = new ArrayList<>();
        drziPredmetiIterable.forEach(drziPredmeti::add);

        return drziPredmeti
                .stream()
                .map(DrziPredmetMaper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public DrziPredmetResponse update(Long id, DrziPredmetRequest req) {

        DrziPredmet dp = drziPredmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DrziPredmet ne postoji"));

        Predmet predmet = predmetRepository.findById(req.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));

        Nastavnik nastavnik = nastavnikRepository.findById(req.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nastavnik ne postoji"));
        SkolskaGodina skolskaGodina = skolskaGodinaRepository
                .findById(req.getSkolskaGodinaId())
                .orElseThrow(() -> new RuntimeException("Nema skolske godine"));

        dp.setPredmet(predmet);
        dp.setNastavnik(nastavnik);
        dp.setSkolskaGodina(skolskaGodina);

        return DrziPredmetMaper.toResponse(drziPredmetRepository.save(dp));
    }

    @Transactional
    public void delete(Long id) {
        if (!drziPredmetRepository.existsById(id)) {
            throw new RuntimeException("DrziPredmet ne postoji");
        }
        drziPredmetRepository.deleteById(id);
    }
}