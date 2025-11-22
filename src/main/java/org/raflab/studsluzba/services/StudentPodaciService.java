package org.raflab.studsluzba.services;

import lombok.AllArgsConstructor;
import org.raflab.studsluzba.controllers.request.StudentPodaciRequest;
import org.raflab.studsluzba.controllers.response.StudentPodaciResponse;
import org.raflab.studsluzba.controllers.response.UpisGodineResponse;
import org.raflab.studsluzba.maperi.StudentPodaciMaper;
import org.raflab.studsluzba.maperi.StudijskiProgramMaper;
import org.raflab.studsluzba.model.*;
import org.raflab.studsluzba.repositories.SlusaPredmetRepository;
import org.raflab.studsluzba.repositories.StudentIndeksRepository;
import org.raflab.studsluzba.repositories.StudentPodaciRepository;
import org.raflab.studsluzba.repositories.UpisGodineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentPodaciService {
    @Autowired
    private StudentIndeksRepository studentIndeksRepository;

    @Autowired
    private SlusaPredmetRepository slusaPredmetRepository;


    private final UpisGodineRepository upisGodineRepository;
    private final StudentPodaciRepository studentPodaciRepository;

    @Transactional
    public StudentPodaciResponse saveStudentPodaci(StudentPodaciRequest request) {
        StudentPodaci sp=StudentPodaciMaper.toEntity(request);
        studentPodaciRepository.save(sp);
        return StudentPodaciMaper.toResponse(sp);
    }
    @Transactional
    public UpisGodineResponse upisiStudentaNaGodinu(Long studentId,
                                                    SkolskaGodina skolskaGodina,
                                                    Integer godinaKojaSeUpisuje) {

        // 1. Pronađi indeks
        StudentIndeks indeks = studentIndeksRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student nije pronađen"));

        // 2. Kreiraj upis
        UpisGodine entitetUpis = new UpisGodine();
        entitetUpis.setStudentIndeks(indeks);
        entitetUpis.setSkolskaGodina(skolskaGodina);
        entitetUpis.setGodinaKojaSeUpisuje(godinaKojaSeUpisuje);
        entitetUpis.setDatumUpisa(LocalDate.now());
        entitetUpis.setNapomena("Automatski upis");

        upisGodineRepository.save(entitetUpis);

        // 3. Preuzmi stare predmete i kopiraj ih u novu godinu
        List<SlusaPredmet> stariPredmeti = slusaPredmetRepository.findByStudentIndeks(indeks);

        for (SlusaPredmet sp : stariPredmeti) {
            SlusaPredmet noviSP = new SlusaPredmet();
            noviSP.setStudentIndeks(indeks);
            noviSP.setDrziPredmet(sp.getDrziPredmet());
            slusaPredmetRepository.save(noviSP);
        }

        // 4. Formiraj response
        UpisGodineResponse upisResponse = new UpisGodineResponse();
        upisResponse.setId(entitetUpis.getId());
        upisResponse.setStudentIndeksId(indeks.getId());
        upisResponse.setSkolskaGodinaId(skolskaGodina.getId());
        upisResponse.setGodinaKojaSeUpisuje(entitetUpis.getGodinaKojaSeUpisuje());
        upisResponse.setDatumUpisa(entitetUpis.getDatumUpisa());
        upisResponse.setNapomena(entitetUpis.getNapomena());

        return upisResponse;
    }




    @Transactional
    public StudentPodaciResponse updateStudent(Long id, StudentPodaciRequest request) {
        Optional<StudentPodaci> optionalStudent = studentPodaciRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student sa ID " + id + " ne postoji");
        }

        StudentPodaci student = optionalStudent.get();


        if (request.getIme() != null) student.setIme(request.getIme());
        if (request.getPrezime() != null) student.setPrezime(request.getPrezime());
        if (request.getSrednjeIme() != null) student.setSrednjeIme(request.getSrednjeIme());
        if (request.getJmbg() != null) student.setJmbg(request.getJmbg());
        if (request.getDatumRodjenja() != null) student.setDatumRodjenja(request.getDatumRodjenja());
        if (request.getMestoRodjenja() != null) student.setMestoRodjenja(request.getMestoRodjenja());
        if (request.getMestoPrebivalista() != null) student.setMestoPrebivalista(request.getMestoPrebivalista());
        if (request.getDrzavaRodjenja() != null) student.setDrzavaRodjenja(request.getDrzavaRodjenja());
        if (request.getDrzavljanstvo() != null) student.setDrzavljanstvo(request.getDrzavljanstvo());
        if (request.getNacionalnost() != null) student.setNacionalnost(request.getNacionalnost());
        if (request.getPol() != null) student.setPol(request.getPol());
        if (request.getAdresa() != null) student.setAdresa(request.getAdresa());
        if (request.getBrojTelefonaMobilni() != null) student.setBrojTelefonaMobilni(request.getBrojTelefonaMobilni());
        if (request.getBrojTelefonaFiksni() != null) student.setBrojTelefonaFiksni(request.getBrojTelefonaFiksni());
        if (request.getEmail() != null) student.setEmail(request.getEmail());
        if (request.getBrojLicneKarte() != null) student.setBrojLicneKarte(request.getBrojLicneKarte());
        if (request.getLicnuKartuIzdao() != null) student.setLicnuKartuIzdao(request.getLicnuKartuIzdao());
        if (request.getMestoStanovanja() != null) student.setMestoStanovanja(request.getMestoStanovanja());
        if (request.getAdresaStanovanja() != null) student.setAdresaStanovanja(request.getAdresaStanovanja());

        StudentPodaci updated = studentPodaciRepository.save(student);
        return StudentPodaciMaper.toResponse(updated);
    }


    @Transactional
    public void deleteStudent(Long id) {
        studentPodaciRepository.deleteById(id);
    }


    public List<StudentPodaciResponse> getAllStudents() {
        return studentPodaciRepository.findAll().stream()
                .map(StudentPodaciMaper::toResponse)
                .collect(Collectors.toList());
    }


    public StudentPodaciResponse getStudentById(Long id) {
        StudentPodaci student = studentPodaciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student sa ID " + id + " ne postoji"));
        return StudentPodaciMaper.toResponse(student);
    }

}

