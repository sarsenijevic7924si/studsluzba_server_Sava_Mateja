package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.NastavnikZvanjeRequest;
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
    public NastavnikZvanje saveNastavnikZvanje(NastavnikZvanjeRequest request) {
        Optional<Nastavnik> nastavnikOpt = nastavnikRepository.findById(request.getNastavnikId());
        if (nastavnikOpt.isEmpty()) {
            throw new IllegalArgumentException("Nastavnik sa ID " + request.getNastavnikId() + " ne postoji.");
        }

        NastavnikZvanje zvanje = NastavnikZvanjeMaper.toEntity(request, nastavnikOpt.get());
        return nastavnikZvanjeRepository.save(zvanje);
    }

    @Transactional
    public NastavnikZvanje findById(Long id) {
        return nastavnikZvanjeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NastavnikZvanje sa ID " + id + " ne postoji."));
    }

    @Transactional
    public List<NastavnikZvanje> findAllForNastavnik(Long nastavnikId) {
        return nastavnikZvanjeRepository.findAll().stream()
                .filter(zvanje -> zvanje.getNastavnik() != null && zvanje.getNastavnik().getId().equals(nastavnikId))
                .collect(Collectors.toList());
    }

    @Transactional
    public NastavnikZvanje updateNastavnikZvanje(Long id, NastavnikZvanjeRequest request) {
        NastavnikZvanje existing = findById(id);

        Optional<Nastavnik> nastavnikOpt = nastavnikRepository.findById(request.getNastavnikId());
        if (nastavnikOpt.isEmpty()) {
            throw new IllegalArgumentException("Nastavnik sa ID " + request.getNastavnikId() + " ne postoji.");
        }

        // Update fields
        if (request.getDatumIzbora() != null) existing.setDatumIzbora(request.getDatumIzbora());
        if (request.getNaucnaOblast() != null) existing.setNaucnaOblast(request.getNaucnaOblast());
        if (request.getUzaNaucnaOblast() != null) existing.setUzaNaucnaOblast(request.getUzaNaucnaOblast());
        if (request.getZvanje() != null) existing.setZvanje(request.getZvanje());
        existing.setAktivno(request.isAktivno());
        existing.setNastavnik(nastavnikOpt.get());

        return nastavnikZvanjeRepository.save(existing);
    }

    @Transactional
    public void deleteNastavnikZvanje(Long id) {
        NastavnikZvanje existing = findById(id);
        nastavnikZvanjeRepository.delete(existing);
    }
}
