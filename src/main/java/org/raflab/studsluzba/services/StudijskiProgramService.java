package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudijskiProgramReqest;
import org.raflab.studsluzba.controllers.response.StudijskiProgramResponse;
import org.raflab.studsluzba.maperi.StudijskiProgramMaper;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.model.VrstaStudija;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.raflab.studsluzba.repositories.VrstaStudijaRespository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class StudijskiProgramService {
    private final StudijskiProgramRepository studijskiProgramRepository;
    private final VrstaStudijaRespository vrstaStudijaRespository;


    @Transactional
    public StudijskiProgramResponse saveStudijskiProgram(StudijskiProgramReqest request) {

        VrstaStudija vs = vrstaStudijaRespository.findById(request.getVrstaStudijaId())
                .orElseThrow(() -> new RuntimeException("Vrsta studija sa datim ID ne postoji."));

        StudijskiProgram sp = StudijskiProgramMaper.toEntity(request, vs);
        StudijskiProgram saved = studijskiProgramRepository.save(sp);

        return StudijskiProgramMaper.toResponse(saved);
    }


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
        if (request.getUkupnoEspb() != null) sp.setUkupnoEspb(request.getUkupnoEspb());

        // Ako je poslata vrsta studija — ažuriraj
        if (request.getVrstaStudijaId() != null) {
            VrstaStudija vs = vrstaStudijaRespository.findById(request.getVrstaStudijaId())
                    .orElseThrow(() -> new RuntimeException("Vrsta studija sa datim ID ne postoji."));
            sp.setVrstaStudija(vs);
        }

        StudijskiProgram updated = studijskiProgramRepository.save(sp);
        return StudijskiProgramMaper.toResponse(updated);
    }


    @Transactional
    public void deleteStudijskiProgram(Long id) {
        StudijskiProgram sp = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + id + " nije pronađen."));

        if (!sp.getPredmeti().isEmpty()) {
            throw new RuntimeException("Studijski program ima predmete i ne može se obrisati.");
        }

        studijskiProgramRepository.delete(sp);
    }


    public List<StudijskiProgram> findAll() {
        return (List<StudijskiProgram>) studijskiProgramRepository.findAll();
    }


    public StudijskiProgramResponse findById(Long id) {
        StudijskiProgram sp = studijskiProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Studijski program sa ID " + id + " nije pronađen."));
        return StudijskiProgramMaper.toResponse(sp);
    }
}
