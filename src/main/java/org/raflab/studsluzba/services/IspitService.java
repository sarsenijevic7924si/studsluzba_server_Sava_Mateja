package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.maperi.IspitMaper;
import org.raflab.studsluzba.maperi.NastavnikMaper;
import org.raflab.studsluzba.maperi.SrednjaSkolaMaper;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.IspitRepository;
import org.raflab.studsluzba.repositories.IspitniRokRepository;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IspitService {
    private final IspitRepository ispitRepository;
    private final IspitniRokRepository ispitniRokRepository;
    private final NastavnikRepository nastavnikRepository;
    private final PredmetRepository predmetRepository;
    public IspitResponse saveIspit(IspitRequest request) {
        Nastavnik nastavnik = nastavnikRepository.findById(request.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nastavnik nije pronađen"));

        Predmet predmet = predmetRepository.findById(request.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));

        IspitniRok ispitniRok = ispitniRokRepository.findById(request.getIspitniRokId())
                .orElseThrow(() -> new RuntimeException("Ispitni rok nije pronađen"));

        Ispit ispit = IspitMaper.toIspit(request, nastavnik, predmet, ispitniRok);

        Ispit saved = ispitRepository.save(ispit);
        return IspitMaper.toResponse(saved);
    }
    public List<IspitResponse> findAll() {
        return ((List<Ispit>) ispitRepository.findAll())
                .stream()
                .map(IspitMaper::toResponse)
                .collect(Collectors.toList());
    }


}
