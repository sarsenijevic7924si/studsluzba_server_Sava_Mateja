package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.maperi.PredmetMaper;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PredmetService {

    final PredmetRepository predmetRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;

    @Transactional
    public PredmetResponse savePredmet(PredmetRequest request) {
        StudijskiProgram studProgram = studijskiProgramRepository.findById(request.getStudProgramId())
                .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen sa ID: " + request.getStudProgramId()));

        Predmet predmet = PredmetMaper.toEntity(request, studProgram);

        Predmet savedPredmet = predmetRepository.save(predmet);

        return PredmetMaper.toResponse(savedPredmet);
    }
    @Transactional
    public void deletePredmet(Long id) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet sa ID " + id + " nije pronađen."));
        predmetRepository.delete(predmet);
    }
    @Transactional
    public PredmetResponse updatePredmet(Long id, PredmetRequest request) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet sa ID " + id + " nije pronađen."));


        if (request.getSifra() != null) predmet.setSifra(request.getSifra());
        if (request.getNaziv() != null) predmet.setNaziv(request.getNaziv());
        if (request.getOpis() != null) predmet.setOpis(request.getOpis());
        if (request.getEspb() != null) predmet.setEspb(request.getEspb());

        if (request.getStudProgramId() != null) {
            StudijskiProgram studProgram = studijskiProgramRepository.findById(request.getStudProgramId())
                    .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen sa ID: " + request.getStudProgramId()));
            predmet.setStudProgram(studProgram);
        }

        Predmet updated = predmetRepository.save(predmet);
        return PredmetMaper.toResponse(updated);
    }

    public Iterable<Predmet> findAll(){
        return predmetRepository.findAll();
    }

    public List<Predmet> getPredmetForGodinaAkreditacije(Integer godinaAkreditacije){
        return predmetRepository.getPredmetForGodinaAkreditacije(godinaAkreditacije);
    }

}
