package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.raflab.studsluzba.controllers.request.PredmetRequest;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.maperi.PolozeniPredmetMaper;
import org.raflab.studsluzba.maperi.PredmetMaper;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SlusaPredmet;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@AllArgsConstructor
@Service
public class PredmetService {

    final PredmetRepository predmetRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final SlusaPredmetRepository slusaPredmetRepository;

    @Transactional
    public PredmetResponse savePredmet(PredmetRequest request) {
        StudijskiProgram studProgram = studijskiProgramRepository.findById(request.getStudProgramId())
                .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen sa ID: " + request.getStudProgramId()));
        boolean postoji = predmetRepository.findAll().stream()
                .anyMatch(p -> p.getSifra().equals(request.getSifra()));

        if (postoji) {
            throw new RuntimeException("Predmet sa šifrom " + request.getSifra() + " već postoji!");
        }

        Predmet predmet = PredmetMaper.toEntity(request, studProgram);

        Predmet savedPredmet = predmetRepository.save(predmet);

        return PredmetMaper.toResponse(savedPredmet);
    }
    @Transactional
    public void deletePredmet(Long id) {

        predmetRepository.deleteById(id);
    }
    @Transactional
    public PredmetResponse updatePredmet(Long id, PredmetRequest request) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet sa ID " + id + " nije pronađen."));


        if (request.getSifra() != null) predmet.setSifra(request.getSifra());
        if (request.getNaziv() != null) predmet.setNaziv(request.getNaziv());
        if (request.getOpis() != null) predmet.setOpis(request.getOpis());
        if (request.getEspb() != null) predmet.setEspb(request.getEspb());

        predmet.setObavezan(request.isObavezan());
        if (request.getStudProgramId() != null) {
            StudijskiProgram studProgram = studijskiProgramRepository.findById(request.getStudProgramId())
                    .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen sa ID: " + request.getStudProgramId()));
            predmet.setStudProgram(studProgram);
        }

        Predmet updated = predmetRepository.save(predmet);
        return PredmetMaper.toResponse(updated);
    }

    public List<PredmetResponse> findAll(){
        return predmetRepository.findAll().stream().map(PredmetMaper::toResponse).collect(Collectors.toList());

    }

    public List<PredmetResponse> getPredmetForGodinaAkreditacije(Integer godinaAkreditacije){
        return predmetRepository.getPredmetForGodinaAkreditacije(godinaAkreditacije).stream()
                .map(PredmetMaper::toResponse)
                .collect(Collectors.toList());
    }
    public List<PredmetResponse> getPredmetForNazivStudPrograma(String studProgram){
        return predmetRepository.findByNazivStuda(studProgram).stream()
                .map(PredmetMaper::toResponse)
                .collect(Collectors.toList());
    }
    public List<PredmetResponse> getNePolozeniPredmeti(int broj, int godina, String oznaka) {

        List<SlusaPredmet> spList =
                slusaPredmetRepository.getSlusaPredmetForIndeksCeoAktivnaGodinalsit(broj, godina, oznaka);

        return spList.stream()
                .flatMap(sp -> slusaPredmetRepository
                        .getSlusaPredmetByPredmet(sp.getId()).stream())
                .map(PredmetMaper::toResponse)
                .collect(Collectors.toList());
    }


    public PredmetResponse FindById(Long id)
    {
        Predmet p=predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet sa ID " + id + " nije pronađen."));
        return PredmetMaper.toResponse(p);
    }
    public List<PredmetResponse> FindPredmetPoProgramu(String programSkraceni)
    {
        List<Predmet> predmets=predmetRepository.getPredmetiZasStudenskiProgram(programSkraceni);
        return predmets.stream().map(PredmetMaper::toResponse).collect(Collectors.toList());
    }

}
