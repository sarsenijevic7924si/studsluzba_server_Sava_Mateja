package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.PrijavaIspitaRequest;
import org.raflab.studsluzba.controllers.response.PrijavaIspitaResponse;
import org.raflab.studsluzba.maperi.PrijavaIspitaMaper;
import org.raflab.studsluzba.model.Ispit;
import org.raflab.studsluzba.model.PrijavaIspita;
import org.raflab.studsluzba.repositories.IspitRepository;
import org.raflab.studsluzba.repositories.PrijavaIspitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrijavaIspitaService {
    private final PrijavaIspitaRepository prijavaIspitaRepository;
    private final IspitRepository ispitRepository;

    // 🔹 Kreiranje prijave ispita
    public PrijavaIspitaResponse createPrijavaIspita(PrijavaIspitaRequest request) {
        Ispit ispit = ispitRepository.findById(request.getIspitId())
                .orElseThrow(() -> new RuntimeException("Ispit nije pronađen"));

        PrijavaIspita prijava = new PrijavaIspita();
        prijava.setDatumPrijave(request.getDatumPrijave());
        prijava.setIspit(ispit);

        PrijavaIspita saved = prijavaIspitaRepository.save(prijava);
        return PrijavaIspitaMaper.toPrijavaIspitaResponse(saved);
    }

    // 🔹 Ažuriranje prijave
    public PrijavaIspitaResponse updatePrijavaIspita(Long id, PrijavaIspitaRequest request) {
        PrijavaIspita prijava = prijavaIspitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava ispita nije pronađena"));

        prijava.setDatumPrijave(request.getDatumPrijave());

        if (!prijava.getIspit().getId().equals(request.getIspitId())) {
            Ispit ispit = ispitRepository.findById(request.getIspitId())
                    .orElseThrow(() -> new RuntimeException("Ispit nije pronađen"));
            prijava.setIspit(ispit);
        }

        PrijavaIspita updated = prijavaIspitaRepository.save(prijava);
        return PrijavaIspitaMaper.toPrijavaIspitaResponse(updated);
    }

    // 🔹 Dohvatanje svih prijava
    public List<PrijavaIspitaResponse> findAll() {
        return prijavaIspitaRepository.findAll()
                .stream()
                .map(PrijavaIspitaMaper::toPrijavaIspitaResponse).collect(Collectors.toList());
    }

    // 🔹 Dohvatanje po ID
    public PrijavaIspitaResponse findById(Long id) {
        PrijavaIspita prijava = prijavaIspitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava ispita nije pronađena"));
        return PrijavaIspitaMaper.toPrijavaIspitaResponse(prijava);
    }

    // 🔹 Brisanje
    public void deleteById(Long id) {
        prijavaIspitaRepository.deleteById(id);
    }
}
