package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.SrednjaSkolaRequest;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.maperi.SrednjaSkolaMaper;
import org.raflab.studsluzba.model.SrednjaSkola;
import org.raflab.studsluzba.repositories.SrednjaSkolaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SrednjaSkolaService {
    private final SrednjaSkolaRepository srednjaSkolaRepository;


    @Transactional
    public SrednjaSkolaResponse saveSrednjaSkola(SrednjaSkolaRequest request) {
        SrednjaSkola skola = SrednjaSkolaMaper.toEntity(request);
        SrednjaSkola saved = srednjaSkolaRepository.save(skola);
        return SrednjaSkolaMaper.toResponse(saved);
    }


    @Transactional
    public SrednjaSkolaResponse updateSrednjaSkola(Long id, SrednjaSkolaRequest request) {
        SrednjaSkola skola = srednjaSkolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Srednja škola sa ID " + id + " nije pronađena."));

        if (request.getNaziv() != null) skola.setNaziv(request.getNaziv());
        if (request.getMesto() != null) skola.setMesto(request.getMesto());
        if (request.getVrsta() != null) skola.setVrsta(request.getVrsta());

        SrednjaSkola updated = srednjaSkolaRepository.save(skola);
        return SrednjaSkolaMaper.toResponse(updated);
    }


    @Transactional
    public void deleteSrednjaSkola(Long id) {
        SrednjaSkola skola = srednjaSkolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Srednja škola sa ID " + id + " nije pronađena."));
        srednjaSkolaRepository.delete(skola);
    }


    public List<SrednjaSkolaResponse> findAll() {
        return ((List<SrednjaSkola>) srednjaSkolaRepository.findAll())
                .stream()
                .map(SrednjaSkolaMaper::toResponse)
                .collect(Collectors.toList());
    }

    public SrednjaSkolaResponse findById(Long id) {
        SrednjaSkola skola = srednjaSkolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Srednja škola sa ID " + id + " nije pronađena."));
        return SrednjaSkolaMaper.toResponse(skola);
    }
}
