package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.GrupaRequest;
import org.raflab.studsluzba.controllers.response.GrupaResponse;
import org.raflab.studsluzba.maperi.GrupaMaper;
import org.raflab.studsluzba.model.Grupa;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.GrupaRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GrupaService {
    private final GrupaRepository grupaRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final PredmetRepository predmetRepository;
    private final GrupaMaper grupaMapper;

    // CREATE
    public GrupaResponse createGrupa(GrupaRequest request) {
        StudijskiProgram studijskiProgram = studijskiProgramRepository.findById(request.getStudijskiProgramID())
                .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen sa ID: " + request.getStudijskiProgramID()));

        List<Predmet> predmeti = predmetRepository.findAllById(request.getPredmetiID());

        Grupa grupa = grupaMapper.toEntity(request, studijskiProgram, predmeti);
        Grupa sacuvana = grupaRepository.save(grupa);
        return grupaMapper.toResponse(sacuvana);
    }

    // READ ALL
    public List<GrupaResponse> getAllGrupe() {
        return grupaRepository.findAll()
                .stream()
                .map(grupaMapper::toResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public GrupaResponse getGrupaById(Long id) {
        Grupa grupa = grupaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupa nije pronađena sa ID: " + id));
        return grupaMapper.toResponse(grupa);
    }

    // UPDATE
    public GrupaResponse updateGrupa(Long id, GrupaRequest request) {
        Grupa grupa = grupaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupa nije pronađena sa ID: " + id));

        StudijskiProgram studijskiProgram = studijskiProgramRepository.findById(request.getStudijskiProgramID())
                .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen sa ID: " + request.getStudijskiProgramID()));

        List<Predmet> predmeti = predmetRepository.findAllById(request.getPredmetiID());

        grupa.setStudijskiProgram(studijskiProgram);
        grupa.setPredmeti(predmeti);

        Grupa sacuvana = grupaRepository.save(grupa);
        return grupaMapper.toResponse(sacuvana);
    }

    // DELETE
    public void deleteGrupa(Long id) {
        if (!grupaRepository.existsById(id)) {
            throw new RuntimeException("Grupa nije pronađena sa ID: " + id);
        }
        grupaRepository.deleteById(id);
    }
}
