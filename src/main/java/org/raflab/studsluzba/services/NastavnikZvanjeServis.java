package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
import org.raflab.studsluzba.controllers.response.NastavnikZvanjeResponse;
import org.raflab.studsluzba.maperi.NastavnikZvanjeMaper;
import org.raflab.studsluzba.model.Nastavnik;
import org.raflab.studsluzba.model.NastavnikZvanje;
import org.raflab.studsluzba.repositories.NastavnikRepository;
import org.raflab.studsluzba.repositories.NastavnikZvanjeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NastavnikZvanjeServis {
    private final NastavnikZvanjeRepository nastavnikZvanjeRepository;
    private final NastavnikRepository nastavnikRepository;

    @Transactional
    public NastavnikZvanjeResponse saveNastavnikZvanje(NastavnikZvanjeRequest request) {
        Nastavnik nastavnik = nastavnikRepository.findById(request.getNastavnikId())
                .orElseThrow(() -> new IllegalArgumentException("Nastavnik sa ID " + request.getNastavnikId() + " ne postoji."));

        NastavnikZvanje zvanje = NastavnikZvanjeMaper.toEntity(request, nastavnik);
        return NastavnikZvanjeMaper.toResponse(nastavnikZvanjeRepository.save(zvanje));
    }

    @Transactional
    public NastavnikZvanjeResponse findByIdResponse(Long id) {
        NastavnikZvanje zvanje = nastavnikZvanjeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NastavnikZvanje sa ID " + id + " ne postoji."));
        return NastavnikZvanjeMaper.toResponse(zvanje);
    }

    @Transactional
    public List<NastavnikZvanjeResponse> findAllForNastavnikResponse(Long nastavnikId) {
        return nastavnikZvanjeRepository.findAll().stream()
                .filter(zvanje -> zvanje.getNastavnik() != null && zvanje.getNastavnik().getId().equals(nastavnikId))
                .map(NastavnikZvanjeMaper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public NastavnikZvanjeResponse updateNastavnikZvanje(Long id, NastavnikZvanjeRequest request) {
        NastavnikZvanje existing = nastavnikZvanjeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NastavnikZvanje sa ID " + id + " ne postoji."));

        Nastavnik nastavnik = nastavnikRepository.findById(request.getNastavnikId())
                .orElseThrow(() -> new IllegalArgumentException("Nastavnik sa ID " + request.getNastavnikId() + " ne postoji."));

        if (request.getDatumIzbora() != null) existing.setDatumIzbora(request.getDatumIzbora());
        if (request.getNaucnaOblast() != null) existing.setNaucnaOblast(request.getNaucnaOblast());
        if (request.getUzaNaucnaOblast() != null) existing.setUzaNaucnaOblast(request.getUzaNaucnaOblast());
        if (request.getZvanje() != null) existing.setZvanje(request.getZvanje());
        existing.setAktivno(request.isAktivno());
        existing.setNastavnik(nastavnik);

        return NastavnikZvanjeMaper.toResponse(nastavnikZvanjeRepository.save(existing));
    }

    @Transactional
    public void deleteNastavnikZvanje(Long id) {
        NastavnikZvanje existing = nastavnikZvanjeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NastavnikZvanje sa ID " + id + " ne postoji."));
        nastavnikZvanjeRepository.delete(existing);
    }
}
