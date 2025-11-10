package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitniRokRequest;
import org.raflab.studsluzba.controllers.request.SrednjaSkolaRequest;
import org.raflab.studsluzba.controllers.response.IspitniRokResponse;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.maperi.IspitniRokMaper;
import org.raflab.studsluzba.maperi.SrednjaSkolaMaper;
import org.raflab.studsluzba.model.IspitniRok;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SrednjaSkola;
import org.raflab.studsluzba.repositories.IspitniRokRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IspitniRokService {
    final IspitniRokRepository ispitniRokRepository;
    public IspitniRokResponse saveIspitniRok(IspitniRokRequest request) {
        IspitniRok ispitniRok = IspitniRokMaper.toIspitniRok(request);
        IspitniRok saved = ispitniRokRepository.save(ispitniRok);
        return IspitniRokMaper.toResponse(saved);
    }
    public List<IspitniRokResponse> findAll() {
        return ispitniRokRepository.findAll()
                .stream()
                .map(IspitniRokMaper::toResponse)
                .collect(Collectors.toList());
    }
    public IspitniRokResponse findById(Long id) {
        IspitniRok rok = ispitniRokRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ispitni rok nije pronađen"));
        return IspitniRokMaper.toResponse(rok);
    }
    public void deleteIspitniRok(Long id) {
        IspitniRok ispitniRok = ispitniRokRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ispitni rok sa ID " + id + " nije pronađen."));
        ispitniRokRepository.delete(ispitniRok);
    }
    public IspitniRokResponse updateIspitniRok(Long id, IspitniRokRequest request) {
        IspitniRok rok = ispitniRokRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ispitni rok nije pronađen"));

        // ažuriranje polja
        rok.setPocetakRoka(request.getPocetakRoka());
        rok.setZavrsetakRoka(request.getZavrsetakRoka());

        IspitniRok updated = ispitniRokRepository.save(rok);
        return IspitniRokMaper.toResponse(updated);
    }
}
