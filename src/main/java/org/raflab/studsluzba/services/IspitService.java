package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.response.IspitResponse;
import org.raflab.studsluzba.controllers.response.SrednjaSkolaResponse;
import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.maperi.IspitMaper;
import org.raflab.studsluzba.maperi.NastavnikMaper;
import org.raflab.studsluzba.maperi.SrednjaSkolaMaper;
import org.raflab.studsluzba.maperi.StudentIndeksMaper;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class IspitService {
    private final IspitRepository ispitRepository;
    private final IspitniRokRepository ispitniRokRepository;
    private final NastavnikRepository nastavnikRepository;
    private final PredmetRepository predmetRepository;
    public IspitResponse saveIspit(IspitRequest request) {
        Nastavnik nastavnik = nastavnikRepository.findById(request.getNastavnikId())
                .orElseThrow(() -> new RuntimeException("Nastavnik nije pronađen"));

        Predmet predmet = predmetRepository.findById(request.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));

        IspitniRok ispitniRok = ispitniRokRepository.findById(request.getIspitniRokId())
                .orElseThrow(() -> new RuntimeException("Ispitni rok nije pronađen"));

        Ispit ispit = IspitMaper.toIspit(request, nastavnik, predmet, ispitniRok);

        Ispit saved = ispitRepository.save(ispit);
        return IspitMaper.toResponse(saved);
    }
    public List<IspitResponse> findAll() {
        return ((List<Ispit>) ispitRepository.findAll())
                .stream()
                .map(IspitMaper::toResponse)
                .collect(Collectors.toList());
    }
    public void deleteIspit(Long id) {
        Ispit ispit = ispitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ispit sa ID " + id + " nije pronađen."));
        ispitRepository.delete(ispit);
    }
    @Transactional
    public IspitResponse updateIspit(Long id, IspitRequest request) {
        Ispit ispit = ispitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ispit nije pronađen"));

        ispit.setDatumOdrzavanja(request.getDatumOdrzavanja());
        ispit.setVremePocetka(request.getVremePocetka());
        ispit.setJeZakljucen(request.getJeZakljucen());

        if (!ispit.getNastavnik().getId().equals(request.getNastavnikId())) {
            Nastavnik nastavnik = nastavnikRepository.findById(request.getNastavnikId())
                    .orElseThrow(() -> new RuntimeException("Nastavnik nije pronađen"));
            ispit.setNastavnik(nastavnik);
        }

        if (!ispit.getPredmet().getId().equals(request.getPredmetId())) {
            Predmet predmet = predmetRepository.findById(request.getPredmetId())
                    .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));
            ispit.setPredmet(predmet);
        }

        if (!ispit.getIspitniRok().getId().equals(request.getIspitniRokId())) {
            IspitniRok rok = ispitniRokRepository.findById(request.getIspitniRokId())
                    .orElseThrow(() -> new RuntimeException("Ispitni rok nije pronađen"));
            ispit.setIspitniRok(rok);
        }

        Ispit updated = ispitRepository.save(ispit);
        return IspitMaper.toResponse(updated);
    }
    private final PrijavaIspitaRepository prijavaRepo;

    public List<StudentIndeksResponse> getPrijavljeniStudenti(Long PrijavaispitId) {
        return prijavaRepo.findByPrijavaIspita(PrijavaispitId)   // metoda u repository koja vraća List<PrijavaIspita>
                .stream()
                .map(StudentIndeksMaper::toResponse)
                .collect(Collectors.toList());  // kompatibilno sa Java 8+
    }
    public Double avg(Long IspitId) {
        List<Integer>ocene=ispitRepository.findOceneByIspitId(IspitId);
        double avg=0;
        double sum=0;
        double count=0;
        for (int i:ocene) {
            sum+=i;
            count++;

        }
        avg=sum/count;
        return avg;
    }
}
