package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.IspitRequest;
import org.raflab.studsluzba.controllers.response.*;
import org.raflab.studsluzba.maperi.*;
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
    private final IzlazakNaIspitRepository izlazakNaIspitRepository;
    private final PredmetRepository predmetRepository;
    private final StudentPredispitniRepository studentPredispitniRepository;
    private final StudentPredispitniRepository predispitniRepository;
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
    public IspitResponse findById(Long id)
    {
         Ispit i=ispitRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Ispit nije pronađen"));
         return IspitMaper.toResponse(i);

    }
    public List<IspitResponse> finPolozeniIpsit(int broj, int godina, String oznaka) {
        return ispitRepository.findPolozniIpsit(broj, godina, oznaka)
                .stream()
                .map(IspitMaper::toResponse)
                .collect(Collectors.toList());
    }
    public List<IspitResponse> finNePolozeniIpsit(int broj, int godina, String oznaka) {
        return ispitRepository.findNePolozniIpsit(broj, godina, oznaka)
                .stream()
                .map(IspitMaper::toResponse)
                .collect(Collectors.toList());
    }
    public List<IzlazakNaIspitResponse>getSortiranaLista(Long ispitID)
    {
        List<IzlazakNaIspit>lista=izlazakNaIspitRepository.sortiraniRezultati(ispitID);
        return lista.stream().map(i -> {

            var prijava = i.getPrijava();
            var student = prijava.getStudentIndeks();
            var predmet = prijava.getIspit().getPredmet();

            // sabiranje predispitnih poena
            double predispitni = Optional.ofNullable(
                    predispitniRepository.sumPoeniForStudentAndPredmet(student.getId(), predmet.getId())
            ).orElse(0.0);

            int ispitni = Optional.ofNullable(i.getPoeniSaIspita()).orElse(0);

            IzlazakNaIspitResponse r = new IzlazakNaIspitResponse();

            r.setId(i.getId());
            r.setPoeniSaIspita(ispitni);
            r.setPredispitniPoeni(predispitni);
            r.setUkupniPoeni((int) (predispitni + ispitni));

            r.setPonistio(i.getPonistio());
            r.setNapomena(i.getNapomena());
            r.setPrijavaIspitaID(prijava.getId());
            r.setStudentId(student.getId());

            // podaci za sortiranje / prikaz
            r.setStudProgram(student.getStudijskiProgram().getOznaka());
            r.setGodinaUpisa(student.getGodina());
            r.setBrojIndeksa(student.getBroj());

            return r;
        }).collect(Collectors.toList());
    }
    public long brojPolaganja(Long studentId, Long predmetId) {
        return izlazakNaIspitRepository.countByStudentAndPredmet(studentId, predmetId);
    }
}
