package org.raflab.studsluzba.services;

import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.ObnovaGodineRequest;
import org.raflab.studsluzba.controllers.response.ObnovaGodineResponse;
import org.raflab.studsluzba.maperi.ObnovaGodineMaper;
import org.raflab.studsluzba.maperi.PolozeniPredmetMaper;
import org.raflab.studsluzba.model.ObnovaGodine;
import org.raflab.studsluzba.model.SkolskaGodina;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.repositories.ObnovaGodineRepository;
import org.raflab.studsluzba.repositories.SkolskaGodinaRepository;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObnovaGodineService {
    private final ObnovaGodineRepository obnovaRepo;
    private final StudentIndeksRepository studentRepo;
    private final SkolskaGodinaRepository godinaRepo;
    private final SlusaPredmetRepository slusaPredmetRepository;

    public List<ObnovaGodineResponse> getObnoveZaStudenta(Long indeksId) {
        return obnovaRepo.findByStudentIndeksId(indeksId)
                .stream()
                .map(ObnovaGodineMaper::toResponse)
                .collect(Collectors.toList());
    }
    public ObnovaGodineResponse obnovi(ObnovaGodineRequest req) {
        StudentIndeks indeks = studentRepo.findById(req.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("Student nije pronađen"));

        SkolskaGodina godina = godinaRepo.findById(req.getSkolskaGodinaId())
                .orElseThrow(() -> new RuntimeException("Školska godina nije pronađena"));

        ObnovaGodine og = new ObnovaGodine();
        og.setStudentIndeks(indeks);
        og.setSkolskaGodina(godina);
        og.setDatumObnove(LocalDate.now());
        og.setNapomena(req.getNapomena());

        obnovaRepo.save(og);

        return ObnovaGodineMaper.toResponse(og);
    }
    public ObnovaGodineResponse updateObnova(Long id, ObnovaGodineRequest req) {
        ObnovaGodine og = obnovaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Obnova nije pronađena"));

        StudentIndeks indeks = studentRepo.findById(req.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("Student indeks nije pronađen"));

        SkolskaGodina godina = godinaRepo.findById(req.getSkolskaGodinaId())
                .orElseThrow(() -> new RuntimeException("Školska godina nije pronađena"));

        og.setStudentIndeks(indeks);
        og.setSkolskaGodina(godina);
        og.setNapomena(req.getNapomena());
        og.setDatumObnove(LocalDate.now());

        obnovaRepo.save(og);
        return ObnovaGodineMaper.toResponse(og);
    }

    @Transactional
    public void deleteObnova(Long id) {
        if (!obnovaRepo.existsById(id)) {
            throw new RuntimeException("Obnova godine nije pronađena");
        }
        obnovaRepo.deleteById(id);
    }


}
