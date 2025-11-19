package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.PolozeniPredmetRequest;
import org.raflab.studsluzba.controllers.request.SrednjaSkolaRequest;
import org.raflab.studsluzba.controllers.response.PolozenPredmetResponse;
import org.raflab.studsluzba.controllers.response.PredmetResponse;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.maperi.PolozeniPredmetMaper;
import org.raflab.studsluzba.maperi.SrednjaSkolaMaper;
import org.raflab.studsluzba.maperi.StudentPodaciMaper;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.PolozeniPremetRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolozeniPredmetService {
    public final PolozeniPremetRepository polozeniRepo;
    private final StudentIndeksRepository indeksRepo;
    private final PredmetRepository predmetRepo;
    private final SlusaPredmetRepository slusaPredmetRepository;

    public PolozenPredmetResponse dodajPolozeniIspit(PolozeniPredmetRequest request) {

        StudentIndeks indeks = indeksRepo.findById(request.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("Student indeks ne postoji"));

        Predmet predmet = predmetRepo.findById(request.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));


        boolean postoji = polozeniRepo.existsByStudentIndeksIdAndPredmetId(
                indeks.getId(),
                predmet.getId()
        );

        if (postoji) {
            throw new RuntimeException("Student je već položio ovaj predmet");
        }

        PolozeniPredmeti p = PolozeniPredmetMaper.toEntity(request, indeks, predmet);

        polozeniRepo.save(p);
        SlusaPredmet sp=slusaPredmetRepository.getSlusaPredmetByPredmetandIndeks(indeks.getId(),predmet.getId());
        slusaPredmetRepository.delete(sp);

        return PolozeniPredmetMaper.toResponse(p);
    }
    public Iterable<PolozeniPredmeti> findAll(){
        return polozeniRepo.findAll();
    }
    public List<PolozenPredmetResponse> findPolozeniByIndeks(Long studentIndeksId) {
        return polozeniRepo.findByStudentIndeksId(studentIndeksId)
                .stream()
                .map(PolozeniPredmetMaper::toResponse)
                .collect(Collectors.toList());
    }
    public List<PolozenPredmetResponse> findPolozeniByStudentIndeks(int broj, int godina, String oznaka) {
        return polozeniRepo.findByStudentIndeks(broj, godina, oznaka)
                .stream()
                .map(PolozeniPredmetMaper::toResponse)
                .collect(Collectors.toList());
    }


    //UPDATE

    @Transactional
    public PolozenPredmetResponse updatePolozeniPredmet(Long id, PolozeniPredmetRequest request) {

        PolozeniPredmeti entity = polozeniRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Položeni predmet sa ID " + id + " nije pronađen."
                ));


        if (request.getOcena() != null) entity.setOcena(request.getOcena());
        if (request.getDatumPolaganja() != null) entity.setDatumPolaganja(request.getDatumPolaganja());

        // Ako se menja student
        if (request.getStudentIndeksId() != null) {
            StudentIndeks student = indeksRepo.findById(request.getStudentIndeksId())
                    .orElseThrow(() -> new RuntimeException("Indeks nije pronađen"));
            entity.setStudentIndeks(student);
        }

        // Ako se menja predmet
        if (request.getPredmetId() != null) {
            Predmet predmet = predmetRepo.findById(request.getPredmetId())
                    .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));
            entity.setPredmet(predmet);
        }

        PolozeniPredmeti updated = polozeniRepo.save(entity);
        return PolozeniPredmetMaper.toResponse(updated);
    }

    @Transactional
    public void deletePolozeniPredmet(Long id) {
        PolozeniPredmeti entity = polozeniRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Položeni predmet sa ID " + id + " nije pronađen."
                ));

        polozeniRepo.delete(entity);
    }
}
