package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.PolozeniPredmetRequest;
import org.raflab.studsluzba.controllers.request.SrednjaSkolaRequest;
import org.raflab.studsluzba.controllers.response.PolozenPredmetResponse;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.maperi.PolozeniPredmetMaper;
import org.raflab.studsluzba.maperi.SrednjaSkolaMaper;
import org.raflab.studsluzba.model.PolozeniPredmeti;
import org.raflab.studsluzba.model.Predmet;
import org.raflab.studsluzba.model.SrednjaSkola;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.repositories.PolozeniPremetRepository;
import org.raflab.studsluzba.repositories.PredmetRepository;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PolozeniPredmetService {
    public final PolozeniPremetRepository polozeniRepo;
    private final StudentIndeksRepository indeksRepo;
    private final PredmetRepository predmetRepo;

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

        return PolozeniPredmetMaper.toResponse(p);
    }
    public Iterable<PolozeniPredmeti> findAll(){
        return polozeniRepo.findAll();
    }

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
