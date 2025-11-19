package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.SlusaPredmetRequest;
import org.raflab.studsluzba.controllers.response.IspitniRokResponse;
import org.raflab.studsluzba.controllers.response.PredIpsitnaObavezaResponse;
import org.raflab.studsluzba.controllers.response.PrijavaIspitaResponse;
import org.raflab.studsluzba.controllers.response.SlusaPredmetResponse;
import org.raflab.studsluzba.maperi.IspitniRokMaper;
import org.raflab.studsluzba.maperi.PredIspitnaObavezaMaper;
import org.raflab.studsluzba.maperi.PrijavaIspitaMaper;
import org.raflab.studsluzba.maperi.SlusaPredmetMaper;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.raflab.studsluzba.repositories.DrziPredmetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SlusaPredmetService {

    private final SlusaPredmetRepository slusaPredmetRepository;
    private final StudentIndeksRepository studentIndeksRepository;
    private final DrziPredmetRepository drziPredmetRepository;

    @Transactional
    public SlusaPredmetResponse saveSlusaPredmet(SlusaPredmetRequest request) {
        StudentIndeks studentIndeks = studentIndeksRepository.findById(request.getStudentIndeksId())
                .orElseThrow(() -> new RuntimeException("StudentIndeks sa ID " + request.getStudentIndeksId() + " nije pronađen."));
        DrziPredmet drziPredmet = drziPredmetRepository.findById(request.getDrziPredmetId())
                .orElseThrow(() -> new RuntimeException("DrziPredmet sa ID " + request.getDrziPredmetId() + " nije pronađen."));

        SlusaPredmet slusaPredmet = SlusaPredmetMaper.toEntity(request, studentIndeks, drziPredmet);
        SlusaPredmet saved = slusaPredmetRepository.save(slusaPredmet);
        return SlusaPredmetMaper.toResponse(saved);
    }

    @Transactional
    public SlusaPredmetResponse updateSlusaPredmet(Long id, SlusaPredmetRequest request) {
        SlusaPredmet slusaPredmet = slusaPredmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SlusaPredmet sa ID " + id + " nije pronađen."));

        if (request.getStudentIndeksId() != null) {
            StudentIndeks studentIndeks = studentIndeksRepository.findById(request.getStudentIndeksId())
                    .orElseThrow(() -> new RuntimeException("StudentIndeks sa ID " + request.getStudentIndeksId() + " nije pronađen."));
            slusaPredmet.setStudentIndeks(studentIndeks);
        }

        if (request.getDrziPredmetId() != null) {
            DrziPredmet drziPredmet = drziPredmetRepository.findById(request.getDrziPredmetId())
                    .orElseThrow(() -> new RuntimeException("DrziPredmet sa ID " + request.getDrziPredmetId() + " nije pronađen."));
            slusaPredmet.setDrziPredmet(drziPredmet);
        }

        SlusaPredmet updated = slusaPredmetRepository.save(slusaPredmet);
        return SlusaPredmetMaper.toResponse(updated);
    }

    @Transactional
    public void deleteSlusaPredmet(Long id) {
        SlusaPredmet slusaPredmet = slusaPredmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SlusaPredmet sa ID " + id + " nije pronađen."));
        slusaPredmetRepository.delete(slusaPredmet);
    }
    public List<SlusaPredmetResponse> findAll() {
        return slusaPredmetRepository.findAll()
                .stream()
                .map(SlusaPredmetMaper::toResponse)
                .collect(Collectors.toList());
    }
    public SlusaPredmetResponse findById(Long id) {
        SlusaPredmet sp = slusaPredmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slusa predmet nije pronađena"));
        return SlusaPredmetMaper.toResponse(sp);
    }


}
