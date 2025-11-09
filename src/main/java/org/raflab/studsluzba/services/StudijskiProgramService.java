package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudijskiProgramReqest;
import org.raflab.studsluzba.controllers.response.StudijskiProgramResponse;
import org.raflab.studsluzba.maperi.StudijskiProgramMaper;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class StudijskiProgramService {
    private final StudijskiProgramRepository studijskiProgramRepository;

    // 🟢 SAVE
    @Transactional
    public StudijskiProgramResponse saveStudijskiProgram(StudijskiProgramReqest request) {
        StudijskiProgram sp = StudijskiProgramMaper.toEntity(request);
        StudijskiProgram saved = studijskiProgramRepository.save(sp);
        return StudijskiProgramMaper.toResponse(saved);
    }

    // 🟢 PATCH / UPDATE
    @Transactional
    public StudijskiProgramResponse updateStudijskiProgram(Long id, StudijskiProgramReqest request) {
        StudijskiProgram sp = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + id + " nije pronađen."));

        if (request.getOznaka() != null) sp.setOznaka(request.getOznaka());
        if (request.getNaziv() != null) sp.setNaziv(request.getNaziv());
        if (request.getGodinaAkreditacije() != null) sp.setGodinaAkreditacije(request.getGodinaAkreditacije());
        if (request.getZvanje() != null) sp.setZvanje(request.getZvanje());
        if (request.getTrajanjeGodina() != null) sp.setTrajanjeGodina(request.getTrajanjeGodina());
        if (request.getTrajanjeSemestara() != null) sp.setTrajanjeSemestara(request.getTrajanjeSemestara());
        if (request.getVrstaStudija() != null) sp.setVrstaStudija(request.getVrstaStudija());
        if (request.getUkupnoEspb() != null) sp.setUkupnoEspb(request.getUkupnoEspb());

        StudijskiProgram updated = studijskiProgramRepository.save(sp);
        return StudijskiProgramMaper.toResponse(updated);
    }

    // 🟢 DELETE
    @Transactional
    public void deleteStudijskiProgram(Long id) {
        StudijskiProgram sp = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + id + " nije pronađen."));

        // Ako postoje predmeti povezani sa ovim programom, bolje je logički brisati ili izbrisati veze
        if (!sp.getPredmeti().isEmpty()) {
            throw new RuntimeException("Studijski program ima predmete i ne može se obrisati.");
        }

        studijskiProgramRepository.delete(sp);
    }

    // 🟢 FIND ALL
    public List<StudijskiProgram> findAll() {
        return (List<StudijskiProgram>) studijskiProgramRepository.findAll();
    }

    // 🟢 FIND BY ID
    public StudijskiProgramResponse findById(Long id) {
        StudijskiProgram sp = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + id + " nije pronađen."));
        return StudijskiProgramMaper.toResponse(sp);
    }
}
