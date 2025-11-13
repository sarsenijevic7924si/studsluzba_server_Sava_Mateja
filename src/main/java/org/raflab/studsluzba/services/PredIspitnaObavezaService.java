package org.raflab.studsluzba.services;

import org.raflab.studsluzba.controllers.request.PredIspitnaObavezaRequest;
import org.raflab.studsluzba.controllers.response.PredIpsitnaObavezaResponse;
import org.raflab.studsluzba.maperi.PredIspitnaObavezaMaper;
import org.raflab.studsluzba.model.PredIspitnaObaveza;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.repositories.PredIspitnaObavezaRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PredIspitnaObavezaService {

    @Autowired
    private PredIspitnaObavezaRepository predIspitnaObavezaRepository;

    @Autowired
    private PredmetRepository predmetRepository;
    @Autowired
    private  SkolskaGodinaRepository skolskaGodinaRepository;


    public PredIpsitnaObavezaResponse create(PredIspitnaObavezaRequest request) {
        Predmet predmet = predmetRepository.findById(request.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));
        SkolskaGodina sg= skolskaGodinaRepository.findById(request.getSkolskaGodinaId())
                .orElseThrow(() -> new RuntimeException("Skolska godina  nije pronađena"));

        PredIspitnaObaveza entity = PredIspitnaObavezaMaper.toEntity(request, predmet,sg);
        PredIspitnaObaveza saved = predIspitnaObavezaRepository.save(entity);

        return PredIspitnaObavezaMaper.toResponse(saved);
    }

    public List<PredIpsitnaObavezaResponse> getAll() {
        return predIspitnaObavezaRepository.findAll()
                .stream()
                .map(PredIspitnaObavezaMaper::toResponse)
                .collect(Collectors.toList());
    }

    public PredIpsitnaObavezaResponse getById(Long id) {
        PredIspitnaObaveza entity = predIspitnaObavezaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PredIspitnaObaveza nije pronađena"));

        return PredIspitnaObavezaMaper.toResponse(entity);
    }

    public PredIpsitnaObavezaResponse update(Long id, PredIspitnaObavezaRequest request) {
        PredIspitnaObaveza entity = predIspitnaObavezaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PredIspitnaObaveza nije pronađena"));

        Predmet predmet = predmetRepository.findById(request.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));

        entity.setVrsta(request.getVrsta());
        entity.setMaksimalanBrojPoena(request.getMaksimalanBrojPoena());
        entity.setPredmet(predmet);

        PredIspitnaObaveza updated = predIspitnaObavezaRepository.save(entity);
        return PredIspitnaObavezaMaper.toResponse(updated);
    }

    public void delete(Long id) {
        if (!predIspitnaObavezaRepository.existsById(id)) {
            throw new RuntimeException("PredIspitnaObaveza nije pronađena");
        }
        predIspitnaObavezaRepository.deleteById(id);
    }
}
