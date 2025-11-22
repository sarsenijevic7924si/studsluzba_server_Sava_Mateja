package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudentIndeksRequest;
import org.raflab.studsluzba.controllers.response.StudentIndeksResponse;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.maperi.StudentIndeksMaper;
import org.raflab.studsluzba.maperi.StudentPodaciMaper;
import org.raflab.studsluzba.model.StudentIndeks;
import org.raflab.studsluzba.model.StudentPodaci;
import org.raflab.studsluzba.model.StudijskiProgram;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.raflab.studsluzba.repositories.StudentPodaciRepository;
import org.raflab.studsluzba.repositories.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentIndeksService {

    final StudentIndeksRepository studentIndeksRepository;
    private final StudentPodaciRepository studentPodaciRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;


    @Transactional(readOnly = true)
    public int findBroj(int godina, String studProgramOznaka) {
        List<Integer> brojeviList = studentIndeksRepository.findBrojeviByGodinaAndStudProgramOznaka(godina, studProgramOznaka);
        if (brojeviList == null || brojeviList.isEmpty()) return 1;

        int expected = 1;
        for (int num : brojeviList.stream().distinct().sorted().collect(Collectors.toList())) {
            if (num != expected) return expected;
            expected++;
        }
        return expected;
    }

    private int findNextAvailableNumber(List<Integer> brojeviList) {
        if (brojeviList == null || brojeviList.isEmpty()) return 1;

        List<Integer> sorted = brojeviList.stream()
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        int expected = 1;
        for (int num : sorted) {
            if (num != expected) return expected;
            expected++;
        }
        return expected;
    }

    public StudentIndeksResponse findById(Long indeksId) {
        StudentIndeks si=studentIndeksRepository.findById(indeksId)
                .orElseThrow(() -> new RuntimeException("Student sa ID ne postoji"));
        return StudentIndeksMaper.toResponse(si);
    }

    public StudentIndeksResponse findByAktivanAndId(Long id) {
        StudentIndeks si=studentIndeksRepository.findByAktivanAndId(id)
                .orElseThrow(() -> new RuntimeException("Student sa ID ne postoji ili nema ni jednog aktivnog sa tim idom"));
        return StudentIndeksMaper.toResponse(si);

    }

    public  StudentIndeksResponse searchIndeksi(String ime, String prezime, String studProgram, Integer godina, Integer broj, Pageable pageable) {
        StudentIndeks si= studentIndeksRepository.findStudentIndeks(
                ime != null ? ime.toLowerCase() : null,
                prezime != null ? prezime.toLowerCase() : null,
                studProgram != null ? studProgram.toLowerCase() : null,
                godina,
                broj
        )
                .orElseThrow(() -> new RuntimeException("Student sa ID ne postoji"));
        return StudentIndeksMaper.toResponse(si);
    }

    public List<StudentIndeks> findIndeksiForStudentPodaciId(Long idStudentPodaci) {
        return studentIndeksRepository.findStudentIndeksiForStudentPodaciId(idStudentPodaci);

    }


    /* ---------------------- SAVE ---------------------- */

    @Transactional
    public StudentIndeksResponse saveIndeks(StudentIndeksRequest request) {
        // Mapiranje request -> entity
        StudentIndeks indeks = StudentIndeksMaper.toEntity(request);

        // Postavljanje sledećeg broja indeksa
        int nextBroj = findBroj(request.getGodina(), request.getStudProgramOznaka());
        indeks.setBroj(nextBroj);

        // Dohvatanje studenta
        StudentPodaci student = studentPodaciRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("StudentPodaci not found with id " + request.getStudentId()));
        indeks.setStudent(student);

        // Dohvatanje studijskog programa
        StudijskiProgram studProgram = studijskiProgramRepository.findByOznaka(request.getStudProgramOznaka())
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("StudijskiProgram not found with oznaka: " + request.getStudProgramOznaka()));
        indeks.setStudijskiProgram(studProgram);

        // Ako nije postavljeno vaziOd, postavi na danas
        if (indeks.getVaziOd() == null) {
            indeks.setVaziOd(java.time.LocalDate.now());
        }

        // Čuvanje u repo
       studentIndeksRepository.save(indeks);
        return StudentIndeksMaper.toResponse(indeks);
    }


    /* ---------------------- PATCH / UPDATE ---------------------- */

    @Transactional
    public StudentIndeksResponse updateIndeks(Long id, StudentIndeksRequest request) {
        StudentIndeks existing = studentIndeksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Indeks ne postoji"));

        // Godina
        if (request.getGodina() != 0) existing.setGodina(request.getGodina());

        // Broj: ako je prosleđen, može; inače ostaje trenutni
        existing.setBroj(request.getBroj());

        // Studijski program
        if (request.getStudProgramOznaka() != null) {
            StudijskiProgram studProgram = studijskiProgramRepository.findByOznaka(request.getStudProgramOznaka())
                    .stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("StudijskiProgram not found with oznaka: " + request.getStudProgramOznaka()));
            existing.setStudijskiProgram(studProgram);
        }

        // Način finansiranja
        if (request.getNacinFinansiranja() != null) existing.setNacinFinansiranja(request.getNacinFinansiranja());

        // Aktivno
        existing.setAktivan(request.isAktivan());

        // VaziOd
        if (request.getVaziOd() != null) existing.setVaziOd(request.getVaziOd());

        // Ostvareno ESPB
        if (request.getOstvarenoEspb() != null) existing.setOstvarenoEspb(request.getOstvarenoEspb());

        // Student
        if (request.getStudentId() != null) {
            StudentPodaci student = studentPodaciRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new RuntimeException("StudentPodaci not found with id " + request.getStudentId()));
            existing.setStudent(student);
        }

        studentIndeksRepository.save(existing);
        return StudentIndeksMaper.toResponse(existing);
    }

    /* ---------------------- DELETE ---------------------- */

    @Transactional
    public void deleteIndeks(Long id) {
        StudentIndeks existing = studentIndeksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Indeks ne postoji"));
        studentIndeksRepository.delete(existing);
    }



    public List<StudentIndeksResponse> findAllIndeksi() {
        return studentIndeksRepository.findAll().stream().map(StudentIndeksMaper::toResponse).collect(Collectors.toList());

    }

    public StudentPodaciResponse findStudentPodaciByIndeks(int broj, int godina, String oznaka) {
        StudentIndeks indeks = studentIndeksRepository
                .findByBrojAndGodinaAndStudProgramOznakaAndAktivan(broj, godina, oznaka, true)
                .orElseThrow(() ->
                        new RuntimeException("Indeks nije pronađen.")
                );

        return StudentPodaciMaper.toResponse(indeks.getStudent());
    }
    public List<StudentIndeksResponse> findStudentSrednja(String naziv) {
        List<StudentIndeks> studentIndeks=studentIndeksRepository.findStudentBySrednjaSkola(naziv);
        return studentIndeks.stream().map(StudentIndeksMaper::toResponse).collect(Collectors.toList());
    }

}
